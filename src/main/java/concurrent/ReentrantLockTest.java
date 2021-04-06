package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liguanghui02
 * @date 2021/3/26
 */
public class ReentrantLockTest {
    private static int threadCount = 5;
    private static int sleepSeconds = 5;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("lock" + finalI);
                    TimeUnit.SECONDS.sleep(sleepSeconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }, "thread-" + i).start();

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        try {
            System.out.println("lock main");
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
