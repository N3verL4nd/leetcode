package proxy.asm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class MyTest {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        InputStream resourceAsStream = MyTest.class.getResourceAsStream("AsmDemo.class");

        byte[] result = new byte[1024];

        int count = resourceAsStream.read(result);

        // 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
        MyClassLoader loader = new MyClassLoader();
        Class<?> clazz = loader.defineMyClass(result, 0, count);
        //测试加载是否成功，打印class 对象的名称
        System.out.println(clazz.getCanonicalName());

        //实例化一个Programmer对象
        Object o = clazz.newInstance();
        try {
            //调用Programmer的code方法
            clazz.getMethod("code", null).invoke(o, null);
        } catch (IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }
}