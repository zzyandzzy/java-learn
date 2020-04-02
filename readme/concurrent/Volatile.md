# volatile关键字
- [对象布局](./ObjectLayout.md)
- [轻量级锁CAS](./CompareAndSwap.md)
- [synchronized关键字](./Synchronized.md)
- [锁升级](./LockUpgrade.md)

# 特征
- [测试用例](../../src/test/java/xyz/zzyitj/demo/concurrent/VolatileTest.java)

- 保证多线程之间的可见性
- 防止指令重排序

# 实现

    为了提高处理速度,处理器不直接和内存进行通信,
    而是先将系统内存的数据读到内部缓存(L1,L2或其他)后再进行操作,
    但操作完不知道何时会写到内存。
    如果对声明了volatile的变量进行写操作,
    JVM 就会向处理器发送一条Lock前缀的指令,将这个变量所在缓存行的数据写回到系统内存。
    但是,就算写回到内存,如果其他处理器缓存的值还是旧的,再执行计算操作就会有问题。
    所以,在多处理器下,为了保证各个处理器的缓存是一致的,就会实现缓存一致性协议,
    每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了,
    当处理器发现自己缓存行对应的内存地址被修改,就会将当前处理器的缓存行设置成无效状态,
    当处理器对这个数据进行修改操作的时候,会重新从系统内存中把数据读到处理器缓存里。

汇编
```java
// lock add
// 指令
```
可参考[轻量级锁CAS](./CompareAndSwap.md)和[synchronized关键字](./Synchronized.md)的实现（实现都是lock指令）

# 原则
- Lock前缀指令会引起处理器缓存回写到内存
- 一个处理器的缓存回写到内存会导致其他处理器的缓存无效

# 扩展
缓存行(cache line)对齐优化，具体描述在[Java并发编程的艺术]()

# 参考
[就是要你懂Java中volatile关键字实现原理](https://www.cnblogs.com/xrq730/p/7048693.html)

[Java并发编程的艺术]()