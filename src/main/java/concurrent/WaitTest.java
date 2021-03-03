package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author liguanghui02
 * @date 2021/2/23
 */
public class WaitTest {
    private static Object lock = new Object();
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
