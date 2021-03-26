import org.junit.Test;

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
}
