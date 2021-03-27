package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbcPrinter2 {
    private final Lock lock = new ReentrantLock();

    private final Condition con1 = lock.newCondition();
    private final Condition con2 = lock.newCondition();
    private final Condition con3 = lock.newCondition();

    private int no = 1;

    public void process1() {
        lock.lock();
        try {
            if (no != 1) {
                con1.await();
            }
            System.out.println(Thread.currentThread().getName() + " a");
            no = 2;
            con2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void process2() {
        lock.lock();
        try {
            if (no != 2) {
                con2.await();
            }
            System.out.println(Thread.currentThread().getName() + " b");
            no = 3;
            con3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void process3() {
        lock.lock();
        try {
            if (no != 3) {
                con3.await();
            }
            System.out.println(Thread.currentThread().getName() + " c");
            no = 1;
            con1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        AbcPrinter2 p = new AbcPrinter2();
        new Thread(() -> {
            p.process1();
        }).start();


        new Thread(() -> {
            p.process2();
        }).start();


        new Thread(() -> {
            p.process3();
        }).start();
    }
}