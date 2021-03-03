## ClassLoader是做什么的

    顾名思义，它是用来加载 Class 的。
    它负责将 Class 的字节码形式转换成内存形式的 Class 对象。
    字节码可以来自于磁盘文件 *.class，也可以是 jar 包里的 *.class，也可以来自远程服务器提供的字节流，
    字节码的本质就是一个字节数组 []byte，它有特定的复杂的内部格式。

## 延迟加载

    JVM 运行并不是一次性加载所需要的全部类的，它是按需加载，也就是延迟加载。
    程序在运行的过程中会逐渐遇到很多不认识的新类，这时候就会调用 ClassLoader 来加载这些类。
    加载完成后就会将 Class 对象存在 ClassLoader 里面，下次就不需要重新加载了。

    比如你在调用某个类的静态方法时，首先这个类肯定是需要被加载的，但是并不会触及这个类的实例字段，
    那么实例字段的类别 Class 就可以暂时不必去加载，但是它可能会加载静态字段相关的类别，
    因为静态方法会访问静态字段。而实例字段的类别需要等到你实例化对象的时候才可能会加载。

## 各司其职

    JVM 运行实例中会存在多个 ClassLoader，不同的 ClassLoader 会从不同的地方加载字节码文件。
    它可以从不同的文件目录加载，也可以从不同的 jar 文件中加载，也可以从网络上不同的服务地址来加载。

    JVM 中内置了三个重要的 ClassLoader，分别是BootstrapClassLoader、ExtensionClassLoader和AppClassLoader。

## BootstrapClassLoader

    负责加载 JVM 运行时核心类，这些类位于 JAVA_HOME/lib/rt.jar 文件中，我们常用内置库 java.xxx.* 都在里面，
    比如 java.util.*、java.io.*、java.nio.*、java.lang.* 等等。
    这个 ClassLoader 比较特殊，它是由 C 代码实现的，我们将它称之为「根加载器」。

## ExtensionClassLoader

    负责加载 JVM 扩展类，比如 swing 系列、内置的 js 引擎、xml 解析器 等等，这些库名通常以 javax 开头，
    它们的 jar 包位于 JAVA_HOME/lib/ext/*.jar 中，有很多 jar 包。

## AppClassLoader

    AppClassLoader 才是直接面向我们用户的加载器，它会加载 Classpath 环境变量里定义的路径中的 jar 包和目录。
    我们自己编写的代码以及使用的第三方 jar 包通常都是由它来加载的。

    那些位于网络上静态文件服务器提供的 jar 包和 class文件，jdk 内置了一个 URLClassLoader，用户只需要传递规范的网络路径给构造器，
    就可以使用 URLClassLoader 来加载远程类库了。URLClassLoader 不但可以加载远程类库，还可以加载本地路径的类库，取决于构造器中不同的地址形式。
    ExtensionClassLoader 和 AppClassLoader 都是 URLClassLoader 的子类，它们都是从本地文件系统里加载类库。

    AppClassLoader 可以由 ClassLoader 类提供的静态方法 getSystemClassLoader() 得到，它就是我们所说的「系统类加载器」，
    我们用户平时编写的类代码通常都是由它加载的。当我们的 main 方法执行的时候，这第一个用户类的加载器就是 AppClassLoader。

## 双亲委派

    前面我们提到 AppClassLoader 只负责加载 Classpath 下面的类库，如果遇到没有加载的系统类库怎么办，
    AppClassLoader 必须将系统类库的加载工作交给 BootstrapClassLoader 和 ExtensionClassLoader 来做，这就是我们常说的「双亲委派」。

```java
class ClassLoader {
    private final ClassLoader parent;

    protected Class<?> loadClass() {
        if (parent != null) {
            c = parent.loadClass(name, false);
        } else {
            c = findBootstrapClassOrNull(name);
        }
    }
}
```

    AppClassLoader 在加载一个未知的类名时，它并不是立即去搜寻 Classpath，它会首先将这个类名称交给 ExtensionClassLoader 来加载，
    如果 ExtensionClassLoader 可以加载，那么 AppClassLoader 就不用麻烦了。否则它就会搜索 Classpath 。
    
    而 ExtensionClassLoader 在加载一个未知的类名时，它也并不是立即搜寻 ext 路径，它会首先将类名称交给 BootstrapClassLoader 来加载，
    如果 BootstrapClassLoader 可以加载，那么 ExtensionClassLoader 也就不用麻烦了。否则它就会搜索 ext 路径下的 jar 包。
    
    这三个 ClassLoader 之间形成了级联的父子关系，每个 ClassLoader 都很懒，尽量把工作交给父亲做，父亲干不了了自己才会干。
    每个 ClassLoader 对象内部都会有一个 parent 属性指向它的父加载器。