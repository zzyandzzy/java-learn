# 锁升级过程
- [对象布局](./ObjectLayout.md)
- [轻量级锁CAS](./CompareAndSwap.md)
- [volatile关键字](./Volatile.md)
- [synchronized关键字](./Synchronized.md)
- [锁升级](./LockUpgrade.md)
- [测试](../../src/test/java/cool/zzy/demo/concurrent/LockTest.java)
- [locks](../../src/main/java/cool/zzy/source/java/util/concurrent/locks)
    - [ReentrantLock](./ReentrantLock.md)

# 资料
- [锁优点和缺点](../../其他/concurrent/锁的优点和缺点.png)
- [测试用例](../../src/test/java/cool/zzy/demo/concurrent/ObjectLayoutTest.java)

锁的级别分为：无锁 --- 偏向锁 --- 轻量级锁 --- 重量级锁
锁只能升级但不能降级（GC除外），这种只能升级不能降级的策略是为了提高获得锁和释放锁的效率

# 偏向锁

    当一个线程访问同步块并获取锁时,
    会在对象头和栈帧中的锁记录里存储锁偏向的线程ID,
    以后该线程在进入和退出同步块时不需要进行CAS操作来加锁和解锁，
    只需要简单的测试一下对象头的Mark Word里是否有存储着指向当前线程的偏向锁。
    如果测试成功,表示线程已经获得了锁。
    如果测试失败,则需要再测试一下Mark Word中偏向锁的标识是否设置成1(表示当前是偏向锁):
    如果没有设置,则使用CAS竞争锁;
    如果设置了,则尝试使用CAS将对象头的偏向锁指向当前线程。

# 轻量级锁（CAS）
[CAS](./CompareAndSwap.md)

    线程在执行同步块之前，JVM会先在当前线程的栈帧中创建用于存储锁记录的空间，
    并将对象头的MarkWord复制到锁记录中，即Displaced Mark Word。
    然后线程会尝试使用CAS将对象头中的Mark Word替换为指向锁记录的指针。
    如果成功，当前线程获得锁。
    如果失败，表示其他线程在竞争锁，当前线程使用自旋来获取锁。
    当自旋次数达到一定次数时，锁就会升级为重量级锁。
    
    轻量级锁解锁时，会使用CAS操作将Displaced Mark Word替换回到对象头，
    如果成功，表示没有竞争发生。
    如果失败，表示当前锁存在竞争，锁已经被升级为重量级锁，则会释放锁并唤醒等待的线程。
    
# 重量级锁(synchronized)

重量级锁是用户态和内核态的切换，所以比较慢

    在JDK 1.6之前,synchronized只有传统的锁机制，
    因此给开发者留下了synchronized关键字相比于其他同步机制性能不好的印象。
    
    在JDK 1.6引入了两种新型锁机制：
    偏向锁和轻量级锁，
    它们的引入是为了解决，
    在没有多线程竞争或基本没有竞争的场景下因使用传统锁机制带来的性能开销问题。
    
# 其他

JDK8偏向锁默认有时延，可用
```shell script
-XX:BiasedLockingStartupDelay=0
```
关闭时延
