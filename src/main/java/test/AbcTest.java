package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liguanghui02
 * @date 2021/3/26
 */
public class AbcTest {
    private static volatile int count = 0;

    private static final int MAX_COUNT = 60;


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        Thread thread1 = new Thread(() -> {
            while (count < MAX_COUNT) {
                lock.lock();
                try {
                    if (count % 3 == 0) {
                        System.out.println("A");
                        count++;
                        condition2.signal();
                    } else {
                        condition1.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (count < MAX_COUNT) {
                lock.lock();
                try {
                    if (count % 3 == 1) {
                        System.out.println("B");
                        count++;
                        condition3.signal();
                    } else {
                        condition2.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            while (count < MAX_COUNT) {
                lock.lock();
                try {
                    if (count % 3 == 2) {
                        System.out.println("C");
                        count++;
                        condition1.signal();
                    } else {
                        condition3.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

            lock.lock();
            try {
                condition1.signal();
                condition2.signal();
            } finally {
                lock.unlock();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
