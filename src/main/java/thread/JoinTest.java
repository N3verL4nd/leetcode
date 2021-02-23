package thread;

import java.util.concurrent.TimeUnit;

public class JoinTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(-1);
    }
}