package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 消费者
 */
class Consumer implements Runnable {
    private Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int x = queue.poll();
                System.out.println("consume --> " + x);
                try {
                    Thread.sleep(500 + (long) (Math.random() * 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.notifyAll();
            }
        }
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    private Random random = new Random();
    private Queue<Integer> queue;
    private int maxSize;

    public Producer(Queue<Integer> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int x = random.nextInt(100);
                System.out.println("produce --> " + x);
                queue.add(x);
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.notifyAll();
            }
        }
    }
}

public class WaitNotifyImpl {
    public static void main(String[] args) {
        int maxSize = 10;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < 2; i++) {
            new Thread(new Consumer(queue), "Consumer").start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer(queue, maxSize), "Producer").start();
        }
    }
}