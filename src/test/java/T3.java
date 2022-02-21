import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author N3verL4nd
 * @date 2022/2/18
 */
public class T3 {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING = -1 << COUNT_BITS;    // 111 00000000000000000000000000000
    private static final int SHUTDOWN = 0 << COUNT_BITS;    // 000 00000000000000000000000000000
    private static final int STOP = 1 << COUNT_BITS;        // 001 00000000000000000000000000000
    private static final int TIDYING = 2 << COUNT_BITS;     // 010 00000000000000000000000000000
    private static final int TERMINATED = 3 << COUNT_BITS;  // 011 00000000000000000000000000000

    @Test
    public void test1() {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();

        lock.unlock();
    }

    @Test
    public void test2() {
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
    }

    @Test
    public void test3() {
    }
}
