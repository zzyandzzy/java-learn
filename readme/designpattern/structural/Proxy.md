# 代理模式

- 定义

      为其他对象提供一种代理，以控制对这个对象的访问
      
      代理对象在客户端和目标对象之间起到中介的作用
      
- 类型

        结构性

- 适用场景

      保护目标对象
      
      增强目标对象
         
## 优缺点

- 优点

      代理模式能将代理对象与真实被调用的目标分离
      
      一定程度上降低了系统的耦合度，扩展性好
      
      保护目标对象
      
      增强目标对象

- 缺点

      代理模式会照成系统设计中类的数目增加
      
      在客户端和目标对象增加一个代理对象，会造成请求处理速度变慢
      
      增加系统复杂度
      
## 代码

- 静态代理
  
  - [代码](../../../src/main/java/xyz/zzyitj/demo/designpattern/structural/proxy/static)

- 动态代理

  - [代码](../../../src/main/java/xyz/zzyitj/demo/designpattern/structural/proxy/dynamic)

## 结构类图

## JDK使用到的代理模式

