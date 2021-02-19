package test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liguanghui02
 * @date 2021/2/18
 */
public class LockSupportDemo2 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("执行 LockSupport.park");
            LockSupport.park();
            System.out.println("结束执行 LockSupport.park");
        });
        thread.start();

        System.out.println("sleep 3 秒");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行 LockSupport.unpark");
        LockSupport.unpark(thread);
    }
}
