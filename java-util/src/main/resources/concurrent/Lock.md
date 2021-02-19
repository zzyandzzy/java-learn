# 锁

- [对象布局](./ObjectLayout.md)
- [轻量级锁CAS](./CompareAndSwap.md)
- [volatile关键字](./Volatile.md)
- [synchronized关键字](./Synchronized.md)
- [锁升级](./LockUpgrade.md)
- [locks](../../src/main/java/cool/zzy/source/java/util/concurrent/locks)
  - [AQS](./AQS.md)
  - [ReentrantLock](./ReentrantLock.md)

# 资料

- [测试用例](../../../test/java/cool/zzy/java/util/concurrent/LockTest.java)

进程和线程的区别

    进程是系统进行资源分配和调度的基本单位。
    
    线程是CPU进行资源分配和调度的最小单位。

锁消除

```java
    /**
 * 锁消除
 * 我们都知道StringBuffer是线程安全的，因为它关键的方法都被synchronized修饰过
 * 但看下面这段代码{@link LockTest#testLock1()}，stringBuffer这个引用只会在testLock1方法中使用，
 * 不可能被其他线程引用（因为是局部变量，栈私有）
 * 因此stringBuffer不可能是共享资源，所以JVM自动消除StringBuffer内部的锁
 */
```

锁粗化

- [测试](../../../test/java/cool/zzy/java/util/concurrent/LockTest.java)

```java
    /**
 * {@link LockTest#testLock2()}
 * JVM对检测到这样一连串的操作都对同一个对象加锁，
 * 此时JVM就会将锁的范围粗化到这一连串操作的外部，
 * 使得这一连串操作只需要一次加锁即可
 */
```