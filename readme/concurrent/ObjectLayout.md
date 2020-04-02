# 对象布局
- [对象布局](../../其他/concurrent/对象布局.png)
- [markword布局](../../其他/concurrent/对象markword.png)
- [测试用例](../../src/test/java/xyz/zzyitj/demo/concurrent/ObjectLayoutTest.java)

在`pom.xml`中导入
```xhtml
        <dependency>
            <groupId>org.openjdk.jol</groupId>
            <artifactId>jol-core</artifactId>
            <version>0.9</version>
        </dependency>
```
# 分析
    对象内存布局分为对象头和实例数据(instance data)和填充数据。
    对象头分为markword、类型指针(class pointer)、(数组还有数组长度)
    其中在64位JVM中，markword占8个字节，类型指针如果开启压缩占4个字节，实例数据看情况
    填充数据分为两种情况：
      1、如果对象大小恰好是8的倍数，填充数据为0
      2、如果对象大小不是8的倍数，填充数据 = 大于对象大小的8的倍数 - 对象大小
        例如对象大小为12，大于对象大小的8的倍速为16，则填充数据就位16 - 12 = 4
    数组长度占4个字节

# 其他
查看指针压缩开启情况
```shell script
java -XX:+PrintCommandLineFlags -version
```
打印
```
-XX:InitialHeapSize=268435456 -XX:MaxHeapSize=4294967296 -XX:+PrintCommandLineFlags
// 开启指针压缩，会把8个字节的指针压缩成4个字节 
-XX:+UseCompressedClassPointers
// 普通对象指针，会把8个字节的普通对象指针压缩成4个字节 
-XX:+UseCompressedOops
-XX:+UseParallelGC 
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
```