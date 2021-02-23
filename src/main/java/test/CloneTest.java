package test;

public class CloneTest implements Cloneable {
    private byte[] a = {1, 2, 3, 4, 5};
    private byte[] b = {5, 4, 3, 2, 1};

    @Override
    public CloneTest clone() {
        CloneTest that = null;
        try {
            that = (CloneTest) super.clone();
            that.b = this.b.clone();
            return that;
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return that;
    }

    public byte[] getA() {
        return this.a;
    }

    public byte[] getB() {
        return this.b;
    }

    public static void main(String[] args) {
        CloneTest original = new CloneTest();
        CloneTest cloned = original.clone();

        System.out.println("original.a == cloned.a : " + (original.getA() == cloned.getA()));
        System.out.println("cloned.a[2] = " + cloned.getA()[2]);

        original.getA()[2] = 10;
        System.out.println("cloned.a[2] = " + cloned.getA()[2]);

        System.out.println("original.b == cloned.b : " + (original.getB() == cloned.getB()));
        System.out.println("cloned.b[2] = " + cloned.getB()[2]);

        original.getB()[2] = 10;
        System.out.println("cloned.b[2] = " + cloned.getB()[2]);
    }
}