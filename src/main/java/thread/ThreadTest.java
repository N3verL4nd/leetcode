package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadTest {
    private static int task = 0;

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        Thread th1 = new Thread(() -> {
            while (true) {
                lock.lock();
                if (task % 3 == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + "1");
                    task++;
                }
                lock.unlock();
            }
        }, "th1");

        Thread th2 = new Thread(() -> {
            while (true) {
                lock.lock();
                if (task % 3 == 1) {
                    System.out.println(Thread.currentThread().getName() + " " + "2");
                    task++;
                }
                lock.unlock();
            }
        }, "th2");


        Thread th3 = new Thread(() -> {
            while (true) {
                lock.lock();
                if (task % 3 == 2) {
                    System.out.println(Thread.currentThread().getName() + " " + "3");
                    task++;
                }
                lock.unlock();
            }
        }, "th3");

        th1.start();
        th2.start();
        th3.start();
    }
}