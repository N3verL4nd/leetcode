package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liguanghui02
 * @date 2021/2/21
 */

class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Hello World";
    }
}

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableDemo());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}
