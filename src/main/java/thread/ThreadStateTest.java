package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author liguanghui02
 * @date 2021/2/23
 */
public class ThreadStateTest {

    private static Object lock = new Object();

    /**
     * thread1 进入 TIMED_WAITING 状态
     */
    private static void sleepTest() {
        Thread thread1 = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "thread1");
        thread1.start();
    }


    /**
     * thread2 进入 WAITING 状态
     * thread1 进入 TIMED_WAITING 状态
     */
    private static void joinTest() {
        Thread thread1 = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "thread1");


        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");


        thread1.start();

        thread2.start();
    }


    /**
     * thread2 进入 TIMED_WAITING 状态
     * thread1 进入 WAITING 状态
     */
    private static void waitTest() {
        Thread thread1 = new Thread(() -> {

            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");


        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                lock.notify();
            }
        }, "thread2");


        thread1.start();

        thread2.start();
    }


    /**
     * thread2 进入 BLOCKED 状态
     * thread1 进入 TIMED_WAITING 状态
     */
    private static void blockTest() {
        Thread thread1 = new Thread(() -> {

            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1");
            }
        }, "thread1");


        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("thread2");
            }
        }, "thread2");


        thread1.start();

        thread2.start();
    }



    public static void main(String[] args) {
//        sleepTest();
//        joinTest();
        waitTest();
        blockTest();
    }
}
