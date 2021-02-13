# synchronized关键字
- [对象布局](./ObjectLayout.md)
- [轻量级锁CAS](./CompareAndSwap.md)
- [volatile关键字](./Volatile.md)
- [synchronized关键字](./Synchronized.md)
- [锁升级](./LockUpgrade.md)
- [测试](../../src/test/java/cool/zzy/demo/concurrent/LockTest.java)
- [locks](../../src/main/java/cool/zzy/source/java/util/concurrent/locks)
    - [ReentrantLock](./ReentrantLock.md)

锁的级别分为：无锁 --- 偏向锁 --- 轻量级锁 --- 重量级锁
锁只能升级但不能降级（GC除外），这种只能升级不能降级的策略是为了提高获得锁和释放锁的效率

synchronized可能升级为重量级锁

重量级锁是用户态和内核态的切换，所以比较慢

    在JDK 1.6之前,synchronized只有传统的锁机制，
    因此给开发者留下了synchronized关键字相比于其他同步机制性能不好的印象。
    
    在JDK 1.6引入了两种新型锁机制：
    偏向锁和轻量级锁，
    它们的引入是为了解决，
    在没有多线程竞争或基本没有竞争的场景下因使用传统锁机制带来的性能开销问题。
    
# 实现
Java层

    加了synchronized关键字就是在编译的.class中代码块开头加monitorenter标识
    在finally处加上monitorexit标识，它们总是成对出现的，如果原本代码没有加上异常，
    编译的时候会自动加上

JVM层
    
加上synchronized关键字的方法或对象的锁会在执行过程中自动升级（参照[锁升级](./LockUpgrade.md)）
    
汇编层
```java
// 在底层实现是用
// lock cmpxchg
// 指令
```
参照[轻量级锁CAS](./CompareAndSwap.md)

# synchronized VS CAS

在高可用，高耗时的环境下，synchronized效率更高
在低可用，低耗时的环境下，cas效率更高

synchronized升级到重量级锁后会进入一个等待队列（不消耗CPU）
CAS在等待期间是消耗CPU的