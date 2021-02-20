package test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author N3verL4nd
 */
public class VolatileTest {
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(() -> {
            try {
                flag = !flag;
            } catch (Exception e) {
                // ignore this exception
            }
        }, 1, 1, TimeUnit.SECONDS);

        while (true) {
            if (flag) {
                System.out.println("flag = true");
            } else {
                System.out.println("flag = false");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
