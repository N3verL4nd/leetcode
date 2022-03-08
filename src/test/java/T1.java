import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * @author liguanghui02
 * @date 2020/12/25
 */
public class T1 {
    @Test
    public void name() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.computeIfPresent(1, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer2 * 2;
            }
        });

        System.out.println(map);
    }

    @Test
    public void test1() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + " " + threadInfo.getThreadName());
        }
    }
}
