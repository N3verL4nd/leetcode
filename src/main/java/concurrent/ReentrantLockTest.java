package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liguanghui02
 * @date 2021/3/26
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);

        new Thread(() -> {
            lock.lock();

            try {
                System.out.println("lock1");
                TimeUnit.SECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "thread-1").start();

        new Thread(() -> {
            lock.lock();

            try {
                System.out.println("lock2");
                TimeUnit.SECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "thread-2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();


        try {
            System.out.println("lock3");
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
