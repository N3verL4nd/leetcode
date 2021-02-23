package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author liguanghui02
 * @date 2021/2/23
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println(thread.getState());


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (Test.class) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
