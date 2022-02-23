package test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author liguanghui02
 * @date 2021/2/18
 */
public class UnSafeTest {

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
        long ageOffset = unsafe.objectFieldOffset(Person.class.getDeclaredField("age"));
        Person person = new Person(28, "lgh");
        System.out.println("ageOffset = " + ageOffset);
        int age = unsafe.getInt(person, ageOffset);
        System.out.println(age);
    }
}
