package test;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class Car {

    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("car gc");
    }

    public static void main(String[] args) {
        Car car = new Car("baoma");
        WeakReference<Car> carWeakReference = new WeakReference<Car>(car);
        System.out.println(car);
        System.out.println(carWeakReference.get());
        car = null;
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(carWeakReference.get());
    }
}
