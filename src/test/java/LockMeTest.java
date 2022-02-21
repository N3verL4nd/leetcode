import org.junit.Test;
import study.thread.LockMe;

import java.util.concurrent.TimeUnit;

/**
 * @author N3verL4nd
 * @date 2022/2/18
 */
public class LockMeTest {
    @Test
    public void test1() {
        LockMe lock = new LockMe();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放锁");
                }
            }, "线程 " + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
