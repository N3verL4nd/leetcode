package concurrent;

class InterruptedTask implements Runnable {

    @Override
    public void run() {

        Thread currentThread = Thread.currentThread();
        while (true) {
            if (currentThread.isInterrupted()) {
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 捕获 InterruptedException 会导致 interrupted 重置为 false
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class InterruptedTest {
    public static void main(String[] args) {
        InterruptedTask interruptedTask = new InterruptedTask();
        Thread interruptedThread = new Thread(interruptedTask);
        interruptedThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        interruptedThread.interrupt();
    }
}