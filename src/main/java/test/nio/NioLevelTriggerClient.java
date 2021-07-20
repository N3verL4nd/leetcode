package test.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author N3verL4nd
 * @date 2021/7/20
 */
public class NioLevelTriggerClient {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        InetAddress ia = InetAddress.getLocalHost();
        InetSocketAddress isa = new InetSocketAddress(ia, 8888);
        socketChannel.connect(isa);
        socketChannel.configureBlocking(false);
        System.out.println("与服务器的连接建立成功！");
        socketChannel.write(StandardCharsets.UTF_8.encode("hello world"));

        socketChannel.close();
    }
}
