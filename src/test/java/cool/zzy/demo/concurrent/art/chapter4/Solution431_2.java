package cool.zzy.demo.concurrent.art.chapter4;

/**
 * xyz.zzyitj.demo.concurrent.art.chapter4
 * 这个文件是对《Java并发编程的艺术》第四章第3节"线程间的通讯"的第一个小标题
 * synchronized的验证
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/6/19 8:14 下午
 * @since 1.0
 */
public class Solution431_2 {
    /**
     * 书中打码4-10
     * 使用：javac Solution431_2.java 编译文件
     * 使用：javap -v Solution431_2.class 查看编译后的字节码
     *
     * @param args
     */
    public static void main(String[] args) {
        synchronized (Solution431_2.class) {
        }
        m();
    }

    /**
     * 书中的JDK版本是7编译出来查看字节码发现synchronized关键字加在方法上时，会自动加上ACC_SYNCHRONIZED标识
     * 但我的JDK8，已经没有这个标识了，根据之前的知识发现在assess_tag（二进制文件）变为了0x22（普通方法为0x2），
     * 也就是0010 0010，根据markword布局发现，10为重量级锁，这也就解释了加了synchronized的方法在JVM层是怎么处理的
     */
    private static synchronized void m() {
    }
}
