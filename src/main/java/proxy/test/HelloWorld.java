package proxy.test;

import java.lang.reflect.Method;

public class HelloWorld {
    public static void main(String[] argv) {
        try {
            // Take the System class
            Class<?> systemClass = Class.forName("java.lang.System");

            // Now get the static object out
            Object outObject = systemClass.getField("out").get(null);

            // Get the println method
            Method printlnMethod = outObject.getClass()
                    .getMethod("println", String.class);

            // Invoke println dynamically
            printlnMethod.invoke(outObject, "Hello, world!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}