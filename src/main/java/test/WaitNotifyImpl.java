package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class ProducerConsumerPattern {
    private int maxSize;
    private final Queue<Integer> queue;
    private Random random = new Random();

    public ProducerConsumerPattern(Queue<Integer> queue, int maxSize) {
        this.maxSize = maxSize;
        this.queue = queue;
    }

    public void consume() {
        for (; ; ) {
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

    public void produce() {
        for (; ; ) {
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

/**
 * 消费者
 */
class Consumer implements Runnable {
    private ProducerConsumerPattern cp;

    public Consumer(ProducerConsumerPattern cp) {
        this.cp = cp;
    }

    @Override
    public void run() {
        cp.consume();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    private ProducerConsumerPattern cp;

    public Producer(ProducerConsumerPattern cp) {
        this.cp = cp;
    }

    @Override
    public void run() {
        cp.produce();
    }
}

public class WaitNotifyImpl {
    public static void main(String[] args) {
        int maxSize = 10;
        Queue<Integer> queue = new LinkedList<>();
        ProducerConsumerPattern cp = new ProducerConsumerPattern(queue, maxSize);

        for (int i = 0; i < 2; i++) {
            new Thread(new Consumer(cp), "Consumer").start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer(cp), "Producer").start();
        }
    }
}