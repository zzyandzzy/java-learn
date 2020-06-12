package xyz.zzyitj.demo.concurrent;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * xyz.zzyitj.demo.concurrent
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/12 11:30 上午
 * @since 1.0
 */
public class MultiThreadTest {
    @Test
    public void test() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfoArray = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfoArray) {
            System.out.println("[" + threadInfo.getThreadId() + "] "
                    + threadInfo.getThreadName());
        }
    }
}
