package jvm;

import org.junit.Test;

import java.io.IOException;

/**
 * @author N3verL4nd
 * @date 2022/3/1
 */
public class StrTest {

    @Test
    public void test0() {
        String s1 = new String("sss111");
        System.out.println(s1.intern() == s1);
        // 创建两个 String 对象
        // 第一次遇到字面量 "sss111"，会在堆中创建一个 String 对象，引用放在字符串常量池中
        // 第二个创建 s1 对象
    }

    @Test
    public void test1() {
        String s1 = "sss111";
        String s2 = "sss111";
        System.out.println(s1 == s2); //结果为true
        // 栈上两个引用
        // 堆中一个对象
        // 字符串常量池保存堆中对象的引用
    }

    @Test
    public void test2() {
        String s1 = new String("sss111"); // 创建两个对象
        String s2 = "sss111";
        System.out.println(s1 == s2); //结果为false
    }

    @Test
    public void test3() {
        String s0 = "111";                      //pool
        String s1 = new String("111");  //heap
        final String s2 = "111";                //pool
        String s3 = "sss111";                   //pool
        String s4 = "sss" + "111";              //pool
        String s5 = "sss" + s0;                 //heap
        String s6 = "sss" + s1;                 //heap
        String s7 = "sss" + s2;                 //pool
        String s8 = "sss" + s0;                 //heap

        System.out.println(s3 == s4);           //true
        System.out.println(s3 == s5);           //false
        System.out.println(s3 == s6);           //false
        System.out.println(s3 == s7);           //true
        System.out.println(s5 == s6);           //false
        System.out.println(s5 == s8);           //false
    }

    @Test
    public void test4() throws IOException {
        String a = new String("hello");
        System.out.println(a == a.intern());
        System.in.read();
    }

    @Test
    public void test5() throws IOException {
        String a = new String(new char[]{'h', 'e', 'l', 'l', 'o'});
        System.out.println(a == a.intern());
        System.in.read();

    }

    @Test
    public void test6() {
        String m = "ja" + "va";
        String n = new String(new char[]{'j', 'a', 'v', 'a'});
        System.out.println(n.intern() == n);
    }

    @Test
    public void test7() throws IOException {
        String m1 = "ja";
        String m2 = "va";
        String m = m1 + m2;
        String n = new String(new char[]{'j', 'a', 'v', 'a'});
        System.out.println(n.intern() == n);

        System.in.read();

    }


    @Test
    public void test8() {
        String s1 = new String("he") + new String("llo"); // ①
        String s2 = new String("h") + new String("ello"); // ②
        String s3 = s1.intern(); // ③
        String s4 = s2.intern(); // ④ // 还是指向 s1 的堆内存地址
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
    }

    @Test
    public void test9() {
        String n = new String(new char[]{'h', 'e', 'l', 'l', 'o'});
        System.out.println(n.intern() == n);
    }
}
