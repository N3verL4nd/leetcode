package jvm;

import org.junit.Test;

public class GcTest {
    private static final int _1M = 1024 * 1024;

    /**
     * -XX:+UseSerialGC -XX:-UseCompressedOops -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:+UseSerialGC：使用单线程垃圾收集器
     * -XX:-UseCompressedOops：关闭压缩指针
     * -verbose:gc：打印详细 GC 日志
     * -Xms20M -Xmx20M：堆区固定 20M 大小
     * -Xmn10M：新生代占用 10 M
     * -XX:+PrintGCDetails：GC 时打印日志
     * -XX:SurvivorRatio=8：Eden:From Survivor:To Survivor=8:1:1
     */
    @Test
    public void test1() {
        byte[] buf1, buf2, buf3, buf4;

        buf1 = new byte[2 * _1M];
        buf2 = new byte[2 * _1M];
        buf3 = new byte[2 * _1M];
        buf4 = new byte[4 * _1M]; // 触发 Minor GC，survivor 不足以容纳 buf1 buf2 buf3，提前进入老年代
        // Minor GC 后 buf4 接入 新生代 Eden 区域
    }
}
