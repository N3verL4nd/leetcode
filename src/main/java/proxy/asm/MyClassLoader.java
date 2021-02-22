package proxy.asm;

/**
 * @author liguanghui02
 * @date 2021/2/20
 */
public class MyClassLoader extends ClassLoader {
    public Class<?> defineMyClass(byte[] b, int off, int len) {
        return super.defineClass(null, b, off, len);
    }
}