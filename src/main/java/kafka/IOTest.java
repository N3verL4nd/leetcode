package kafka;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author liguanghui02
 * @date 2021/4/8
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
        String filePath = "/private/tmp/kafka-logs/test-0/00000000000000000000.log";
        File file = new File(filePath);

        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        System.out.println(inputStream.readLong());
        System.out.println(inputStream.readInt());
        inputStream.close();
    }
}
