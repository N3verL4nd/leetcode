package study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MyStack {
    private List<String> list = new ArrayList<>();

    public void push(String value) {
        synchronized (this) {
            list.add(value);
            notify();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String pop() throws InterruptedException {
        synchronized (this) {
            while (list.size() <= 0) {
                wait();
            }
            return list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        Thread pushThread = new Thread(() -> {
            String uuid = UUID.randomUUID().toString();
            stack.push(uuid);
            System.out.println(Thread.currentThread().getName() + " push " + uuid);
        }, "pushThread");


        Thread popThread1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " pop " + stack.pop());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "popThread1");

        popThread1.start(); // 进入 WAITING 状态

        Thread popThread2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " pop " + stack.pop());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "popThread2");


        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pushThread.start(); // 进入 TIMED_WAITING 状态

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        popThread2.start(); // 进入 BLOCK 状态
    }
}

/*

状况1：
1.  假设有三个线程： A,B,C.  A 负责放入数据到list,就是调用push操作， B,C分别执行Pop操作，移除数据。
2.  首先B先执行，于pop中的wait()方法处，进入waiting状态，进入等待队列，释放锁。
3.  A首先执行放入数据push操作到List，在调用notify()之前； 同时C执行pop()，由于synchronized，被阻塞，进入Blocked状态，放入基于锁的阻塞队列。注意，这里的队列和2中的waiting等待队列是两个不同的队列。
4.    A线程调用notify()，唤醒等待中的线程A。
5.    如果此时， C获取到基于对象的锁，则优先执行，执行pop方法，获取数据，从list移除一个元素。
6.  然后，A获取到竞争锁，A中调用list.remove(list.size() - 1)，则会报数据越界exception。

状况2：
1.  相同于状况1
2.  B、C都处于等待waiting状态，释放锁。等待notify()、notifyAll()操作的唤醒。
3.  存在被虚假唤醒的可能。
何为虚假唤醒？
虚假唤醒就是一些obj.wait()会在除了obj.notify()和obj.notifyAll()的其他情况被唤醒，而此时是不应该唤醒的。
 */