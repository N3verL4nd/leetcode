package study.proto;

import com.google.protobuf.CodedOutputStream;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author N3verL4nd
 * @date 2022/2/16
 */
public class Test3 {
    public static void main(String[] args) throws IOException {
        System.out.println(Integer.toBinaryString(CodedOutputStream.computeStringSizeNoTag("hello")));
        System.out.println(Integer.toBinaryString(CodedOutputStream.computeStringSize(1, "hello")));


        byte[] buf = new byte[100];
        CodedOutputStream codedOutputStream = CodedOutputStream.newInstance(buf);

        codedOutputStream.writeInt32(1, 1024);

        System.out.println(Arrays.toString(buf));
    }
}
