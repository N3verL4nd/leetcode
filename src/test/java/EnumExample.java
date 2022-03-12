import com.caucho.hessian.io.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

enum Color {
    WHITE,
    BLACK
}

public class EnumExample {

    @Test
    public void enumTest() {
        System.out.println(Color.WHITE.name());
        System.out.println(Color.BLACK.name());

        System.out.println(Color.WHITE.ordinal());
        System.out.println(Color.BLACK.ordinal());

        System.out.println(Color.WHITE);
        System.out.println(Color.BLACK);
    }

    @Test
    public void test2() {
        byte[] data = null;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);

            output.writeObject(Color.WHITE);
            output.flush();

            data = os.toByteArray();
            output.close();



            ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
            Hessian2Input input = new Hessian2Input(bis);
            System.out.println(input.readObject());

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));
    }
}
