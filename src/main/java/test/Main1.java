package test;

public class Main1 {
    public static void main(String[] args) {
        Integer a = 127;Integer b = 127;
        Integer c = new Integer(127);
        Integer d = new Integer(127);
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(b == c);
    }
}