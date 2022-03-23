package test.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;

/**
 * @author N3verL4nd
 * @date 2022/3/23 13:57
 */


public class NoInterestTest {
    private static Thread acceptThread;
    private static List<Thread> selectorThreads;
    private static List<Selector> selectors;
    private static Queue<SocketChannel> clients;

    public static void init() {
        selectorThreads = new ArrayList<>();
        selectors = new ArrayList<>();
        clients = new LinkedList<>();

        acceptThread = acceptThread = new Thread(() -> {
            Selector selector = null;
            try {
                selector = SelectorProvider.provider().openSelector();
                ServerSocketChannel server = ServerSocketChannel.open();
                server.bind(new InetSocketAddress("0.0.0.0", 8888));
                server.configureBlocking(false);
                server.register(selector, SelectionKey.OP_ACCEPT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                try {

                    selector.select();

                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();

                        // skip if not valid
                        if (!key.isValid()) {
                            continue;
                        }

                        if (key.isAcceptable()) {
                            System.out.println(Thread.currentThread().getName() + " " + "accept");

                            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                            SocketChannel accept = channel.accept();
                            accept.configureBlocking(false);

                            clients.offer(accept);

                            Selector subSelector = selectors.stream().findAny().get();
                            subSelector.wakeup();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        selectorThreads = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Selector selector = null;
            try {
                selector = SelectorProvider.provider().openSelector();
                selectors.add(selector);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Selector finalSelector = selector;
            Thread selectorThread = new Thread(() -> {
                while (true) {
                    try {
                        finalSelector.select();

                        Iterator<SelectionKey> keys = finalSelector.selectedKeys().iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            keys.remove();

                            // skip if not valid
                            if (!key.isValid()) {
                                continue;
                            }

                            if (key.isReadable()) {
                                SocketChannel client = (SocketChannel) key.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(256);
                                client.read(buffer);
                                String output = new String(buffer.array()).trim();
                                System.out.println("Message read from client: " + output);
                                if (output.equals("close")) {
                                    client.close();
                                    System.out.println("The Client messages are complete; close the session.");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    SocketChannel client = clients.poll();
                    if (client == null) {
                        continue;
                    }

                    try {
                        client.register(finalSelector, SelectionKey.OP_READ);
                    } catch (ClosedChannelException e) {
                        e.printStackTrace();
                    }
                }
            });
            selectorThreads.add(selectorThread);
        }

        acceptThread.start();

        for (Thread selectorThread : selectorThreads) {
            selectorThread.start();
        }
    }

    public static void main(String[] args) throws Exception {
        init();
    }
}
