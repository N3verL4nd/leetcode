package jvm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;

import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
class Demo {
    private int age;
    private long id;
}

/**
 * @author N3verL4nd
 * @date 2022/2/23
 */
public class ObjTest {
    /**
     * -XX:+UseSerialGC -XX:-UseCompressedOops -Xms10m -Xmx10m
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        Demo demo = new Demo(1, 1);

        System.in.read();
    }
}
