# volatile关键字

- [对象布局](./ObjectLayout.md)
- [轻量级锁CAS](./CompareAndSwap.md)
- [volatile关键字](./Volatile.md)
- [synchronized关键字](./Synchronized.md)
- [锁升级](./LockUpgrade.md)
- [locks](../../src/main/java/cool/zzy/source/java/util/concurrent/locks)
  - [AQS](./AQS.md)
  - [ReentrantLock](./ReentrantLock.md)

# 资料

- [测试用例](../../../test/java/cool/zzy/java/util/concurrent/VolatileTest.java)

- 保证多线程之间的可见性
- 防止指令重排序

# 实现

    为了提高处理速度,处理器不直接和内存进行通信,
    而是先将系统内存的数据读到内部缓存(L1,L2或其他)后再进行操作,
    但操作完不知道何时会写到内存。
    如果对声明了volatile的变量进行写操作,
    JVM 就会向处理器发送一条Lock前缀的指令,将这个变量所在缓存行的数据写回到系统内存。
    但是,就算写回到内存,如果其他处理器缓存的值还是旧的,再执行计算操作就会有问题。
    所以,在多处理器下,为了保证各个处理器的缓存是一致的,就会实现缓存一致性协议（MESI协议）,
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

# MESI协议（缓存一致性协议）

`MESI`该名称来自4个状态的首字母的缩写，协议中最重要的内容有两部分：`cache line的状态`以及`消息通知机制`。

cache line的状态有4个：

- Invalid，表明该cache line已失效，它要么已经不在cache中，要么它的内容已经过时。处于该状态下的cache line等同于它从来没被加载到cache中。
- Shared，表明该cache line是内存中某一段数据的拷贝，处于该状态下的cache line只能被cpu读取，不能写入，因为此时还没有独占。不同cpu的cache line都可以拥有这段内存数据的拷贝。
- Exclusive，和 Shared 状态一样，表明该cache line是内存中某一段数据的拷贝。区别在于，该cache line独占该内存地址，其他处理器的cache line不能同时持有它，如果其他处理器原本也持有同一cache
  line，那么它会马上变成“Invalid”状态。
- Modified，表明该cache line已经被修改，cache line只有处于Exclusive状态才能被修改。此外，已修改cache line如果被丢弃或标记为Invalid，那么先要把它的内容回写到内存中。

# 扩展

缓存行(cache line)对齐优化，具体描述在[Java并发编程的艺术]()

# 参考

> [就是要你懂Java中volatile关键字实现原理](https://www.cnblogs.com/xrq730/p/7048693.html)

> [Java并发编程的艺术]()

> [【并发编程】MESI--CPU缓存一致性协议](https://www.cnblogs.com/z00377750/p/9180644.html)

> [MESI协议 - Wiki](https://zh.wikipedia.org/wiki/MESI%E5%8D%8F%E8%AE%AE)