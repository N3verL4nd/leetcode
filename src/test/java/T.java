import org.junit.Test;

class Demo {
    private String name = get();
    {
        System.out.println("非静态代码块");
    }

    public String get() {
        System.out.println("get");
        return "lgh";
    }
}

public class T {
    @Test
    public void test1() {
        Demo demo = new Demo();
    }
}