package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author liguanghui02
 * @date 2021/2/20
 */

class ABCRunnable implements Runnable {
    private String currentThreadName = "threadA";

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                this.notifyAll();
                if ("threadA".equals(currentThreadName) && Thread.currentThread().getName().equals(currentThreadName)) {
                    System.out.println(Thread.currentThread().getName() + " " + "A");
                    currentThreadName = "threadB";
                } else if ("threadB".equals(currentThreadName) && Thread.currentThread().getName().equals(currentThreadName)) {
                    System.out.println(Thread.currentThread().getName() + " " + "B");
                    currentThreadName = "threadC";
                } else if ("threadC".equals(currentThreadName) && Thread.currentThread().getName().equals(currentThreadName)) {
                    System.out.println(Thread.currentThread().getName() + " " + "C");
                    currentThreadName = "threadA";
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

public class ABCTest {
    public static void main(String[] args) {
        ABCRunnable abcRunnable = new ABCRunnable();
        Thread threadOne = new Thread(abcRunnable, "threadA");
        Thread threadTwo = new Thread(abcRunnable, "threadB");
        Thread threadThree = new Thread(abcRunnable, "threadC");
        threadOne.start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadTwo.start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadThree.start();
    }
}
