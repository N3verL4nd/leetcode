package concurrent;

/**
 * @author liguanghui02
 * @date 2021/2/22
 */
public class SynchronizedTest {

    public synchronized void test1() {

    }

    public synchronized static void test2() {

    }

    public void test3() {
        synchronized (SynchronizedTest.class) {

        }
    }

    public void test4() {
        synchronized (this) {

        }
    }

    public static void main(String[] args) {

    }
}


