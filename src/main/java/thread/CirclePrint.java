package thread;

import java.util.concurrent.TimeUnit;

/**
 * 两个线程打印1-100
 *
 * @author liguanghui02
 * @date 2021/2/20
 */

class DemoRunnable implements Runnable {
    private int x = 1;

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                this.notify();
                System.out.println(Thread.currentThread().getName() + " " + x);
                x++;
                if (x >= 100) {
                    break;
                }
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class CirclePrint {

    public static void main(String[] args) {
        DemoRunnable demoRunnable = new DemoRunnable();
        Thread threadOne = new Thread(demoRunnable, "threadOne");
        Thread threadTwo = new Thread(demoRunnable, "threadTwo");
        threadOne.start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadTwo.start();
    }
}
