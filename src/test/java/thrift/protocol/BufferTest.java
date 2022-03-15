package thrift.protocol;

import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BufferTest {
    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        System.out.println(buffer.remaining());
        buffer.put((byte) 1);
        buffer.put((byte) 1);
        buffer.put((byte) 1);
        buffer.put((byte) 1);
        buffer.put((byte) 1); // BufferOverflowException
    }

    @Test
    public void test2() throws TTransportException {
        byte[] buf = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer buffer = ByteBuffer.allocate(4);

        TMemoryInputTransport transport = new TMemoryInputTransport(buf);

        transport.read(buffer);
        System.out.println(Arrays.toString(buffer.array()));

        transport.close();
    }


    @Test
    public void test3() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(1024);

        buffer = ByteBuffer.allocate(2048);

        System.out.println(buffer);
    }
}
