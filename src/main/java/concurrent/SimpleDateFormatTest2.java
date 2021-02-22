package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liguanghui02
 * @date 2021/2/22
 */
public class SimpleDateFormatTest2 {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(16);

    private static final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

    public static String date(int seconds) {
        Date date = new Date(1000L * seconds);
        SimpleDateFormat dateFormat = dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                try {
                    String date = date(finalI);
                    System.out.println(date);
                } finally {
                    dateFormatThreadLocal.remove();
                }
            });
        }
        threadPool.shutdown();
    }
}