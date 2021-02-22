package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liguanghui02
 * @date 2021/2/22
 */
public class SimpleDateFormatTest {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(16);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

    public static String date(int seconds) {
        Date date = new Date(1000L * seconds);
        return dateFormat.format(date);
    }

    public synchronized static String dateSafe(int seconds) {
        Date date = new Date(1000L * seconds);
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                String date = date(finalI);
                System.out.println(date);
            });
        }
        threadPool.shutdown();
    }
}
