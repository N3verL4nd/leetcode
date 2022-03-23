package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class WeakUpTest {
    private static volatile boolean stopped = false;

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("0.0.0.0", 8888));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            selector.wakeup();
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stopped = true;
        }).start();

        while (!stopped) {
            int n = selector.select();
            if (n == 0) {
                // 此处存在 epoll 假唤醒问题
                continue;
            }

            System.out.println("Select " + n);

            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                // skip if not valid
                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    channel.read(buffer);
                    String output = new String(buffer.array()).trim();
                    System.out.println("Message read from client: " + output);
                    if ("close".equals(output)) {
                        channel.close();
                        System.out.println("The Client messages are complete; close the session.");
                    }
                }
            }
        }
    }
}
