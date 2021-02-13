# 数据结构
## 线性表(LinearList)
  - [顺序存储.md](readme/datastructure/linearlist/ZArrayList.md)

## 树(Tree)
  - [红黑树.md](readme/datastructure/tree/RBTree.md)

# 算法
## [排序.md](./readme/algorithm/Sort.md)

# 设计模式
## [创建型](./readme/designpattern/creational)

- [工厂方法.md](./readme/designpattern/creational/FactoryMethod.md)
- [抽象工厂.md](./readme/designpattern/creational/AbstractFactory.md)
- [建造者.md](./readme/designpattern/creational/Builder.md)
- [单例.md](readme/designpattern/creational/Singleton.md)

## [结构型](./readme/designpattern/structural)

- [装饰者.md](./readme/designpattern/structural/Decorator.md)
- [代理模式.md](./readme/designpattern/structural/Proxy.md)

# util
- Collection
  - List
    - [ArrayList源码](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/util/ArrayList.java)
      - [测试用例](./src/test/java/cool/zzy/java/util/ArrayListTest.java)
    - [LinkedList源码](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/util/LinkedList.java)
      - [测试用例](./src/test/java/cool/zzy/java/util/LinkedListTest.java)
  - Set
    - [HashSet源码](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/util/HashSet.java)
      - [测试用例](./src/test/java/cool/zzy/java/util/HashSetTest.java)
      - [LinkedHashSet源码](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/util/LinkedHashSet.java)
        - [测试用例](./src/test/java/cool/zzy/java/util/LinkedHashSetTest.java)
    - [TreeSet源码](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/util/TreeSet.java)
      - [测试用例](./src/test/java/cool/zzy/java/util/TreeSetTest.java)
  
- Map
  - [HshMap.md](./readme/util/HashMap.md)
    - [LinkedHashMap源码](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/util/LinkedHashMap.java)
      - [测试用例](./src/test/java/cool/zzy/java/util/LinkedHashMapTest.java)
      - [结构图](其他/util/LinkedHashMap/LinkedHashMap.png)
  - [TreeMap源码](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/util/TreeMap.java)
    - [测试用例](./src/test/java/cool/zzy/java/util/TreeMapTest.java)
  
## concurrent

- [ConcurrentHashMap.md](./readme/util/concurrent/ConcurrentHashMap.md)
- [FutureTask.md](./readme/util/concurrent/FutureTask.md)

# 并发
## [锁](./readme/concurrent/Lock.md)

# JVM

## 深入理解Java虚拟机（第三版）

- [第二章 自动内存管理](./readme/jvm/Chapter2.md)

# 引用类型
- [强](./readme/reference/Normal.md)
- [软](./readme/reference/Soft.md)
- [弱](./readme/reference/Weak.md)
- [虚](./readme/reference/Phantom.md)

# lang
- [String](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/String.java)
- [StringBuffer](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/StringBuffer.java)
- [StringBuilder](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/StringBuilder.java)
- [Number](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/Number.java)
  - [Integer](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/Integer.java)
  - [Long](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/Long.java)
  - [Float](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/Float.java)
  - [Double](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/Double.java)
  - [Byte](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/Byte.java)
  - [Short](https://github.com/zzyandzzy/jdk11/blob/main/src/java.base/share/classes/java/lang/Short.java)
  
# LICENSE
    MIT License
    
    Copyright (c) 2020 intent
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
