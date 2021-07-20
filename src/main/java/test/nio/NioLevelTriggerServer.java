package test.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * NIO 水平触发测试
 * 验证java nio水平触发的办法是客户端写多个字节(比如1000个)，服务端每次都不读取字节，缓冲区一直没读完，处于非空状态。由于水平触发，读事件应当会一直触发。
 *
 * @author N3verL4nd
 * @date 2021/7/20
 */
public class NioLevelTriggerServer {
    public static void main(String[] argv) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("0.0.0.0", 8888));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }

            System.out.println("Select " + n);

            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();

                    // 不读取任何数据，缓冲区始终不为空
                }
            }
            System.out.println("休眠一秒, 减缓输出, 便于观察");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}