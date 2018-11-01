package test;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for(;;) {
                    System.out.println(1);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notifyAll();// 唤醒 thread2 线程，使 thread2 线程从等待队列(不可竞争锁)加入同步队列(可竞争锁)
                    try {
                        lock.wait();// 使当前线程 thread1(释放锁) 进入同步队列(等待从 wait 方法返回)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                for(;;) {
                    System.out.println(2);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notifyAll();// 唤醒 thread1 线程，使 thread1 线程从等待队列(不可竞争锁)加入同步队列(可竞争锁)
                    try {
                        lock.wait();// 使当前线程 thread2(释放锁) 进入同步队列(等待从 wait 方法返回)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread2");
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
