package test;

/**
 * @author liguanghui02
 * @date 2021/3/14
 */

class StaticClass {
    static {
        System.out.println("执行 StaticClass 静态代码块");
    }
}

public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> staticClass = ClassTest.class.getClassLoader().loadClass("test.StaticClass");
//        System.out.println(staticClass);

//        Class.forName("test.StaticClass");


        Class.forName("test.StaticClass", true, ClassLoader.getSystemClassLoader());

    }
}
