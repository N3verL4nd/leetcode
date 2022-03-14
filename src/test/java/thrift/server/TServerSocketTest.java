package thrift.server;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * TServerSocket 测试
 *
 * @author N3verL4nd
 * @date 2022/3/14 17:43
 */
public class TServerSocketTest {
    @Test
    public void test1() {
        try (TServerSocket tServerSocket = new TServerSocket(8080)) {
            TSocket tSocket = tServerSocket.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            tSocket.read(byteBuffer);

            System.out.println(new String(byteBuffer.array()));

        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }
}
