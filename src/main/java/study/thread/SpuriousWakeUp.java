package study.thread;

public class SpuriousWakeUp {
    static Object lock = new Object();
    static int remainTicketNum = 0;

    public void buyTicket() {
        synchronized (lock) {
            while (remainTicketNum <= 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            remainTicketNum--;
            System.out.println(Thread.currentThread().getName() + "购买成功");
        }
    }

    public void returnTicket() {
        synchronized (lock) {
            remainTicketNum++;
            lock.notify();
            System.out.println(Thread.currentThread().getName() + "退票成功");
        }
    }
}