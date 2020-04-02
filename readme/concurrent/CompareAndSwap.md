# 轻量级锁CAS
- [对象布局](./ObjectLayout.md)
- [volatile关键字](./Volatile.md)
- [synchronized关键字](./Synchronized.md)
- [锁升级](./LockUpgrade.md)

# 资料
- [流程图](../../其他/concurrent/cas流程图.png)
- [测试用例](../../src/test/java/xyz/zzyitj/demo/concurrent/CompareAndSwapTest.java)
- [锁优点和缺点图](../../其他/concurrent/锁的优点和缺点.png)

锁的级别分为：无锁 --- 偏向锁 --- 轻量级锁 --- 重量级锁
锁只能升级但不能降级（GC除外），这种只能升级不能降级的策略是为了提高获得锁和释放锁的效率

CAS自旋锁(乐观锁)是轻量级锁

CAS可能产生`ABA`问题

# 原理

    CAS机制当中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。
    更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。
    CAS的自旋。循环体当中做了三件事：
    1.获取当前值。
    2.当前值+1，计算出目标值。
    3.进行CAS操作，如果成功则跳出循环，如果失败则重复上述步骤。

# 实现
JDK层面：
```java
/**
 *{@link sun.misc.Unsafe#compareAndSwapObject(Object, long, Object, Object)}
 */
```
JVM层面：
```c
UNSAFE_ENTRY(jboolean, Unsafe_CompareAndSwapInt(JNIEnv *env, jobject unsafe, jobject obj, jlong offset, jint e, jint x))
  UnsafeWrapper("Unsafe_CompareAndSwapInt");
  oop p = JNIHandles::resolve(obj);
  jint* addr = (jint *) index_oop_from_field_offset_long(p, offset);
  return (jint)(Atomic::cmpxchg(x, addr, e)) == e;
UNSAFE_END
```
```c
inline jint     Atomic::cmpxchg    (jint     exchange_value, volatile jint*     dest, jint     compare_value) {
  int mp = os::is_MP();
  // 汇编语言       如果是多个CPU      cmpxchg指令
  __asm__ volatile (LOCK_IF_MP(%4) "cmpxchgl %1,(%3)"
                    : "=a" (exchange_value)
                    : "r" (exchange_value), "a" (compare_value), "r" (dest), "r" (mp)
                    : "cc", "memory");
  return exchange_value;
}
```
所以最终的实现就是
```java
// lock cmpxchg
// cmpxchg 并不是原子性的，而是lock指令
// lock指令在指cmpxchg指令在修改一块内存的值的时候另外的CPU不能修改这块内存的这个值
```
补充知识：
```java
// LOCK指令前缀只能用于以下这些指令：
// ADD, ADC, AND, BTC, BTR, BTS, CMPXCHG
// CMPXCH8B, DEC, INC, NEG, NOT, OR, SBB, SUB, XOR, XADD, XCHG
// LOCK指令只有在目标操作数为内存地址时LOCK指令才会将该指令变为原子指令；
// 如果目标操作数不为内存则会产生UD(Undefined Opcode，未定义的指令)错误。
// lock指令的作用
// 1、锁总线，其它CPU对内存的读写请求都会被阻塞，直到锁释放，
//  不过实际后来的处理器都采用锁缓存替代锁总线，因为锁总线的开销比较大，锁总线期间其他CPU没法访问内存
// 2、lock后的写操作会回写已修改的数据，同时让其它CPU相关缓存行失效，从而重新从主存中加载最新的数据
// 3、不是内存屏障却能完成类似内存屏障的功能，阻止屏障两遍的指令重排序
```
硬件层面
```java
// lock指令在执行后面指令的时候会锁定一个北桥信号，不采用锁总线的方式
```

# 缺点

    CAS缺点：
    1、CPU开销较大，多线程反复尝试更新某一个变量的时候容易出现；
    2、不能保证代码块的原子性，只能保证变量的原子性操作；
    3、ABA问题。
    
# synchronized VS CAS

在高可用，高耗时的环境下，synchronized效率更高
在低可用，低耗时的环境下，cas效率更高

synchronized升级到重量级锁后会进入一个等待队列（不消耗CPU）
CAS在等待期间是消耗CPU的