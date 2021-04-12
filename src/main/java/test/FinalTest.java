package test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author liguanghui02
 * @date 2021/4/12
 */

class FinalClass {
    public final int age = 100;
}

public class FinalTest {

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = reflectGetUnsafe();
        assert unsafe != null;
        long ageOffset = unsafe.objectFieldOffset(FinalClass.class.getDeclaredField("age"));
        FinalClass finalClass = new FinalClass();
        System.out.println(unsafe.getInt(finalClass, ageOffset)); // 100
        System.out.println(finalClass.age); // 100

        unsafe.putInt(finalClass, ageOffset, 200);
        System.out.println(finalClass.age); // 100
        System.out.println(unsafe.getInt(finalClass, ageOffset)); // 200
    }
}
