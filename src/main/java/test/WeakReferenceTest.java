package test;

import java.lang.ref.WeakReference;

class Demo {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("i will be destroyed");
    }
}

public class WeakReferenceTest {
    public static void main(String[] args) {
        Demo demo = new Demo();
        /**
         * 如果只存在弱引用指向当前对象，则垃圾回收时一定回收该对象
         */
        WeakReference<Demo> carWeakReference = new WeakReference<>(demo);

        int i = 0;
        while (true) {
            if (carWeakReference.get() != null) {
                i++;
                System.out.println("Object is alive for " + i + " loops - " + carWeakReference);
            } else {
                System.out.println("Object has been collected.");
                break;
            }
        }
    }
}