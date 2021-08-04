## 源码调试

### IntelliJ IDEA

- 第一步：
  ```bash
  # 克隆本项目
  git clone clone https://github.com/zzyandzzy/java-learn.git --depth 1
  # 克隆本项目调试所需jdk源码
  git clone clone https://github.com/zzyandzzy/openjdk-jdk11u.git --depth 1
  ```

- 第二步：编译本项目所需jdk，参考[jdk11的README](https://github.com/zzyandzzy/openjdk-jdk11u/blob/main/README.md)

- 第三步：IDEA打开本项目，新建JDK，选择编译好的jdk，如：`jdk11/build/xxx/images/jdk`，IDEA选择完JDK后Sourcepath选择`jdk11/src`目录

- 第四步：IDEA设置本项目的SDK为上面选择的JDK，然后开始愉快的调试吧~~~

`注意一`：当你改变了jdk11里面的源码的时候（如多加了一行），就需要用`make jdk`重新编译jdk11的源码，IDEA Debug才不错位

## [算法](algorithm/src/main/resources/README.md)

## [数据结构](datastructure/src/main/resources/README.md)

## [设计模式](designpattern/src/main/resources/README.md)

## [java.lang](java-lang/src/main/resources/README.md)

## [java.util](java-util/src/main/resources/README.md)

## [java并发编程艺术](book-java-concurrent-art/src/main/resources/README.md)

## LICENSE

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
