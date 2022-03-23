package thread;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class ShutDownTest {
    @Test
    public void test1() {
        ExecutorService service = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("接受中断，结束线程~~");
                    //这里响应中断
                    return;
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println("isShutdown: " + service.isShutdown());
        System.out.println("isTerminated: " + service.isTerminated());

        final List<Runnable> runnables = service.shutdownNow();

        System.out.println("isShutdown: " + service.isShutdown());
        System.out.println("isTerminated: " + service.isTerminated());
        System.out.println(runnables.size());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ExecutorService service = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        for (int i = 0; i < 1000; i++) {
            service.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("接受中断，不处理~~");
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println("isShutdown: " + service.isShutdown());
        System.out.println("isTerminated: " + service.isTerminated());

        service.shutdown();

        System.out.println("isShutdown: " + service.isShutdown());
        System.out.println("isTerminated: " + service.isTerminated());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        System.out.println(TimeUnit.SECONDS.toMillis(60));
    }

    @Test
    public void test4() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(()-> {
            while (true) {
                System.out.println(UUID.randomUUID());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();

        long timeoutMS = TimeUnit.SECONDS.toMillis(10);
        long now = System.currentTimeMillis();
        while (timeoutMS >= 0) {
            try {
                executorService.awaitTermination(timeoutMS, TimeUnit.MILLISECONDS);
                break;
            } catch (InterruptedException ix) {
                long newnow = System.currentTimeMillis();
                timeoutMS -= (newnow - now);
                now = newnow;
            }
        }
    }

    /**
     * 优雅关闭线程池
     */
    @Test
    public void test5() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(()-> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("还有任务未结束");
        }
    }
}
