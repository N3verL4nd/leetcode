import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;


public class T {
    @Test
    public void test() {
        System.out.println(ClassLayout.parseClass(Integer.class).toPrintable());
    }
}
