package study.thread;

import java.util.concurrent.TimeUnit;

public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
           while (true) {
               System.out.println("test");
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        thread.setDaemon(true);

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
