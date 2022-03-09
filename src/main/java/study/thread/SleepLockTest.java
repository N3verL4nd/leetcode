package study.thread;

import java.util.concurrent.TimeUnit;

public class SleepLockTest {
    private static Object lock = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(()->{
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2");


        thread1.start();
        thread2.start();
    }
}
