package xyz.zzyitj.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * xyz.zzyitj.demo.jvm
 * 测试堆的OutOfMemoryError
 * VM Options：-Xms20M -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+HeapDumpOnOutOfMemoryError这个参数会把当前OOM的内存映像拷贝下来
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/4 7:27 下午
 * @since 1.0
 */
public class HeapOOM {
    static class OOMObject {

    }

    /**
     * 运行结果：
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid11652.hprof ...
     * Heap dump file created [27663879 bytes in 0.149 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * at java.util.Arrays.copyOf(Arrays.java:3210)
     * at java.util.Arrays.copyOf(Arrays.java:3181)
     * at java.util.ArrayList.grow(ArrayList.java:265)
     * at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * at java.util.ArrayList.add(ArrayList.java:462)
     * at xyz.zzyitj.demo.jvm.HeapOOM.main(HeapOOM.java:21)
     *
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
