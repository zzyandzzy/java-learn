# 数据结构

- 树(Tree)
  - [红黑树](./readme/tree/RBTree.md)
    
# 并发
- [对象布局](./readme/concurrent/ObjectLayout.md)
- [CAS](./readme/concurrent/CompareAndSwap.md)

# lang
- [String](./src/main/java/xyz/zzyitj/source/java/lang/String.java)
- [StringBuffer](./src/main/java/xyz/zzyitj/source/java/lang/StringBuffer.java) 同步的
- [StringBuilder](./src/main/java/xyz/zzyitj/source/java/lang/StringBuilder.java) 不是同步的
- [Number](./src/main/java/xyz/zzyitj/source/java/lang/Number.java)
  - [Integer](./src/main/java/xyz/zzyitj/source/java/lang/Integer.java)
  - [Long](./src/main/java/xyz/zzyitj/source/java/lang/Long.java)
  - [Float](./src/main/java/xyz/zzyitj/source/java/lang/Float.java)
  - [Double](./src/main/java/xyz/zzyitj/source/java/lang/Double.java)
  - [Byte](./src/main/java/xyz/zzyitj/source/java/lang/Byte.java)
  - [Short](./src/main/java/xyz/zzyitj/source/java/lang/Short.java)

# util
- Collection
  - List
    - [ArrayList](./src/main/java/xyz/zzyitj/source/java/util/ArrayList.java)
      - [测试用例](./src/test/java/xyz/zzyitj/java/util/ArrayListTest.java)
    - [LinkedList](./src/main/java/xyz/zzyitj/source/java/util/LinkedList.java)
      - [测试用例](./src/test/java/xyz/zzyitj/java/util/LinkedListTest.java)
  - Set
    - [HashSet](./src/main/java/xyz/zzyitj/source/java/util/HashSet.java)
      - [测试用例](./src/test/java/xyz/zzyitj/java/util/HashSetTest.java)
      - [LinkedHashSet](./src/main/java/xyz/zzyitj/source/java/util/LinkedHashSet.java)
        - [测试用例](./src/test/java/xyz/zzyitj/java/util/LinkedHashSetTest.java)
    - [TreeSet](./src/main/java/xyz/zzyitj/source/java/util/TreeSet.java)
      - [测试用例](./src/test/java/xyz/zzyitj/java/util/TreeSetTest.java)
  
- Map
  - [HashMap](./src/main/java/xyz/zzyitj/source/java/util/HashMap.java)
    - [热门问题](./readme/util/HashMap.md)
    - [测试用例](./src/test/java/xyz/zzyitj/java/util/HashMapTest.java)
    - [插入流程图](其他/util/HashMap/插入流程图.png)
    - [LinkedHashMap](./src/main/java/xyz/zzyitj/source/java/util/LinkedHashMap.java)
      - [测试用例](./src/test/java/xyz/zzyitj/java/util/LinkedHashMapTest.java)
      - [结构图](其他/util/LinkedHashMap/LinkedHashMap.png)
  - [TreeMap](./src/main/java/xyz/zzyitj/source/java/util/TreeMap.java)
    - [测试用例](./src/test/java/xyz/zzyitj/java/util/TreeMapTest.java)
  
## concurrent

- [ConcurrentHashMap](./src/main/java/xyz/zzyitj/source/java/util/concurrent/ConcurrentHashMap.java)
  - [测试用例](./src/test/java/xyz/zzyitj/java/util/concurrent/ConcurrentHashMapTest.java)
  
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
