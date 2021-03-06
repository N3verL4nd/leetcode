package thread;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author liguanghui02
 * @date 2021/3/26
 */
public class InterruptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println(LocalDateTime.now());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

    }
}

/*

作者：Intopass
链接：https://www.zhihu.com/question/41048032/answer/89431513
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

首先，一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止。
所以，Thread.stop, Thread.suspend, Thread.resume 都已经被废弃了。
而 Thread.interrupt 的作用其实也不是中断线程，而是「通知线程应该中断了」，具体到底中断还是继续运行，应该由被通知的线程自己处理。
具体来说，当对一个线程，调用 interrupt() 时，
① 如果线程处于被阻塞状态（例如处于sleep, wait, join 等状态），那么线程将立即退出被阻塞状态，并抛出一个InterruptedException异常。仅此而已。
② 如果线程处于正常活动状态，那么会将该线程的中断标志设置为 true，仅此而已。
被设置中断标志的线程将继续正常运行，不受影响。interrupt() 并不能真正的中断线程，需要被调用的线程自己进行配合才行。也就是说，一个线程如果有被中断的需求，那么就可以这样做。
① 在正常运行任务时，经常检查本线程的中断标志位，如果被设置了中断标志就自行停止线程。
② 在调用阻塞方法时正确处理InterruptedException异常。（例如，catch异常后就结束线程。）

 */
