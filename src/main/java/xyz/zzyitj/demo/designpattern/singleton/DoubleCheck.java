package xyz.zzyitj.demo.designpattern.singleton;

/**
 * xyz.zzyitj.demo.designpattern.singleton
 * 单例模式之双重检查
 *
 * @author intent zzy.main@gmail.com
 * @date 2020/4/5 10:20 上午
 * @since 1.0
 */
public class DoubleCheck {

    /**
     * 必须加volatile，防止指令重排序
     * 如果没加volatile，指令如果发生重排序，可能对象在半初始化的时候就返回了
     * （半初始化就是JVM初始化对象分配完堆内存，后面原本是调用构造函数，因为发生了指令重排序，对象直接指向堆内存实例，
     * 则当时对象在Java层还没完成初始化就直接返回了，返回了一个半初始化状态的对象）
     */
    private static volatile DoubleCheck INSTANCE;

    /**
     * 防止构造函数生成实例
     */
    private DoubleCheck() {
    }

    /**
     * 是否 Lazy 初始化：是
     * 是否多线程安全：是
     *
     * @return
     */
    public DoubleCheck getInstance() {
        if (INSTANCE == null) {
            // 上锁，防止多线程出现多个单例
            synchronized (DoubleCheck.class) {
                // 双重检查，防止线程A在上锁时线程B获得了锁并且创建了对象释放了锁后线程A又拿到锁
                // 判断了INSTANCE == null就能防止线程A出现上面这种情况
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheck();
                }
            }
        }
        return INSTANCE;
    }
}
