import org.junit.Test;

import java.util.Date;

/**
 * @author liguanghui02
 * @date 2021/3/19
 */
public class T2 {

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void test1() {
        System.out.println(tableSizeFor(15));
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(17));
    }

    @Test
    public void test2() {
        ThreadLocal<Date> dateThreadLocal1 = ThreadLocal.withInitial(Date::new);
        ThreadLocal<Date> dateThreadLocal2 = ThreadLocal.withInitial(Date::new);
        System.out.println(dateThreadLocal1.get());
        System.out.println(dateThreadLocal2.get());
    }
}
