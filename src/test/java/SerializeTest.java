import com.caucho.hessian.io.Hessian2Output;
import com.google.protobuf.CodedOutputStream;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author N3verL4nd
 * @date 2022/2/22
 */
public class SerializeTest {
    @Test
    public void test1() {
        byte[] data = null;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);

            output.writeInt(1024);
            output.flush();

            data = os.toByteArray();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));
    }


    @Test
    public void test2() throws IOException {
        byte[] data = null;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            CodedOutputStream output = CodedOutputStream.newInstance(os);
            output.writeInt32NoTag(1024);
            data = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));
    }
}
