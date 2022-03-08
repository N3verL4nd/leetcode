package jvm;

import org.junit.Test;

import java.io.IOException;

public class GcTest {
    private static final int _1M = 1024 * 1024;

    /**
     * -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseSerialGC -XX:-UseCompressedOops -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:+UseSerialGC：使用单线程垃圾收集器
     * -XX:-UseCompressedOops：关闭压缩指针
     * -XX:+PrintGCDetails：用于打印输出详细的GC收集日志的信息
     * -Xms20M -Xmx20M：堆区固定 20M 大小
     * -Xmn10M：新生代占用 10 M
     * -XX:+PrintGCDetails：GC 时打印日志
     * -XX:SurvivorRatio=8：Eden:From Survivor:To Survivor=8:1:1
     */
    @Test
    public void test1() throws IOException {
        byte[] buf1, buf2, buf3, buf4;

        buf1 = new byte[2 * _1M];
        buf2 = new byte[2 * _1M];
        buf3 = new byte[2 * _1M];
        buf4 = new byte[4 * _1M]; // 触发 Minor GC，survivor 不足以容纳 buf1 buf2 buf3，提前进入老年代
        // Minor GC 后 buf4 接入 新生代 Eden 区域
    }

    /**
     * -Xlog:gc*=info -XX:+UseSerialGC -XX:-UseCompressedOops -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     */
    @Test
    public void test2() {
        byte[] buf1, buf2, buf3, buf4;

        buf1 = new byte[2 * _1M];
        buf2 = new byte[2 * _1M];
        buf3 = new byte[2 * _1M];
        buf4 = new byte[4 * _1M]; // 触发 Minor GC，survivor 不足以容纳 buf1 buf2 buf3，提前进入老年代
        // Minor GC 后 buf4 接入 新生代 Eden 区域


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
