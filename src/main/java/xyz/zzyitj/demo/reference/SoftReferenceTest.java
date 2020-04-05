package xyz.zzyitj.demo.reference;

import java.lang.ref.SoftReference;

/**
 * xyz.zzyitj.demo.reference
 * VM Options：-Xmx20m
 * 软引用，主要用于缓存
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/5 10:56 上午
 * @since 1.0
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        // 10m
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference.get());
        // 分配了11m的堆内存，从上面可以看成堆总共有20m，已经分配了10m给软引用
        // 这里又分配11m给b，加起来21m超出堆内存，所以回收了软引用
        byte[] b = new byte[1024 * 1024 * 11];
        System.out.println(softReference.get());
    }
}
