package concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable {
    private Random random = new Random();
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 不定期生产，模拟随机的用户请求
                Thread.sleep((long) (Math.random() * 1000));
                int x = random.nextInt(100);
                System.out.println("produce: " + x);
                queue.put(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int x = queue.take();
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume: " + x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BlockingQueueImpl {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
        for (int i = 0; i < 2; i++) {
            new Thread(new Consumer(queue)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer(queue)).start();
        }
    }
}