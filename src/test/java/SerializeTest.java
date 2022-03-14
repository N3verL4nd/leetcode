import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import org.junit.Test;
import study.proto.PersonModel;
import test.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author N3verL4nd
 * @date 2022/2/22
 */
public class SerializeTest {
    /**
     * hessian序列化
     */
    @Test
    public void test1() {
        byte[] data = null;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);

            Person person = new Person(1024, "lgh");

            output.writeObject(person);
            output.flush();

            data = os.toByteArray();
            output.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
            Hessian2Input input = new Hessian2Input(bis);
            System.out.println(input.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));
    }


    /**
     * protobuf 序列化
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        byte[] data = new byte[10];

        try {
            CodedOutputStream output = CodedOutputStream.newInstance(data);

            PersonModel.Person person = PersonModel.Person.newBuilder().setName("lgh").setAge(1024).build();
            output.writeMessageNoTag(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));

        CodedInputStream inputStream = CodedInputStream.newInstance(data);

        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        inputStream.readMessage(builder, null);

        System.out.println(builder.build());

    }
}
