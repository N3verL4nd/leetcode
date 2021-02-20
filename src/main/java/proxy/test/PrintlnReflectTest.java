package proxy.test;

import java.lang.reflect.Method;

public class PrintlnReflectTest {
    public static void main(String[] argv) {
        try {
            Class<?> systemClass = Class.forName("java.lang.System");

            Object outObject = systemClass.getField("out").get(null);

            Method printlnMethod = outObject.getClass().getMethod("println", String.class);

            printlnMethod.invoke(outObject, "Hello, world!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}