
# Lombok的简介
Lombok是一款Java开发插件，使得Java开发者可以通过其定义的一些注解来消除业务工程中冗长和繁琐的代码，
尤其对于简单的Java模型对象（POJO）。在开发环境中使用Lombok插件后，Java开发人员可以节省出重复构建，
诸如hashCode和equals这样的方法以及各种业务对象模型的accessor和ToString等方法的大量时间。对于这些方法，
它能够在编译源代码期间自动帮我们生成这些方法，并没有如反射那样降低程序的性能。

# IDEA安装Lombok的插件
如何在Intellij中安装上Lombok插件。

通过IntelliJ的插件中心寻找Lombok

![](https://box.kancloud.cn/42406103f19cdc034f57cea2bdc064f2_1296x1022.png)

另外需要注意的是，在使用lombok注解的时候记得要导入lombok.jar包到工程，在其pom.xml中添加：

~~~
<dependency>
    <groupId>org.projectlombok</groupId> 
    <artifactId>lombok</artifactId>
    <version>1.16.8</version>
</dependency>
~~~

# 注解使用

常用注解

[官方文档](https://www.projectlombok.org/features/all)

~~~
@Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals
、canEqual、hashCode、toString 方法

@Setter：注解在属性|类上；为属性提供 setting 方法

@Getter：注解在属性|类上；为属性提供 getting 方法

@Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象

@NoArgsConstructor：注解在类上；为类提供一个无参的构造方法

@RequiredArgsConstructor :注解在类上；为类的特殊字段提供一个构造方法

@AllArgsConstructor：注解在类上；为类提供一个全参的构造方法

@Clearup: 自动管理资源

@ToString: 用在类上可以自动撰写toString方法

@EqualsAndHashCode: 用在类上自动生成equal方法和hashcode方法

@Synchronized: 用在方法上 ,则方法声明是同步的,并自动加上锁为为一个私有属性同步锁

@Builder: 可以用在类,构造,方法上,指定生成复杂的Builder模式的API方法

@val: 声明局部变量为final类型
~~~

# Lombok使用

## @NonNull
[NonNull详解](/NonNull.md)

您可以在方法的参数或构造函数的参数上使用@NonNull让lombok为您生成null-check语句。

---

## @Cleanup
[Cleanup详解](/Cleanup.md)

自动化即生产力，安全地调用close（）方法
 
---

## @Getter/@Setter 很实用的注解

[Getter/Setter详解](/GetterSetter.md)

自动生成@Getter/@Setter注解

---

## @ToString

[ToString详解](/ToString.md)

自动生成ToString方法。

---

## @Getter(lazy=true)

[GetterLazy详解](/GetterLazy.md)

这个注解的作用相当于缓存，就是我在第一次调用后这个值会一直存在，不在浪费资源去重复生成了

---

## @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor

[Constructor详解](/Constructor.md)

这几个注解分别生成无参构造器、指定参数构造器、包含所有参数的构造器。

---

## @EqualsAndHashCode

[EqualsAndHashCode详解](/EqualsAndHashCode.md)

自动生成equals&hashcode方法

---

## @Data
这个一个注解就相当于@RequiredArgsConstructor，@ToString, @EqualsAndHashCode, @Getter,@Setter @Value 的集合

---

## @Builder

[@Builder详解](/Builder.md)

@Builder使用构建者模式，允许您使用以下代码自动生成使您的类可实例化所需的代码
Person.builder().name("Adam Savage").city("San Francisco").job("Mythbusters").job("Unchained Reaction").build();

---
## @Value

相当于final类型的Data注解
 @Value: @ToString, @EqualsAndHashCode, @AllArgsConstructor, @FieldDefaults, and @Getter.
```java

@Value public class ValueExample {
  String name;
  @Wither(AccessLevel.PACKAGE) @NonFinal int age;
  double score;
  protected String[] tags;
  
  @ToString(includeFieldNames=true)
  @Value(staticConstructor="of")
  public static class Exercise<T> {
    String name;
    T value;
  }
}
```
翻译后：

```java
public final class ValueExample {
  private final String name;
  private int age;
  private final double score;
  protected final String[] tags;
  
  @java.beans.ConstructorProperties({"name", "age", "score", "tags"})
  public ValueExample(String name, int age, double score, String[] tags) {
    this.name = name;
    this.age = age;
    this.score = score;
    this.tags = tags;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getAge() {
    return this.age;
  }
  
  public double getScore() {
    return this.score;
  }
  
  public String[] getTags() {
    return this.tags;
  }
  
  @java.lang.Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof ValueExample)) return false;
    final ValueExample other = (ValueExample)o;
    final Object this$name = this.getName();
    final Object other$name = other.getName();
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
    if (this.getAge() != other.getAge()) return false;
    if (Double.compare(this.getScore(), other.getScore()) != 0) return false;
    if (!Arrays.deepEquals(this.getTags(), other.getTags())) return false;
    return true;
  }
  
  @java.lang.Override
  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $name = this.getName();
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    result = result * PRIME + this.getAge();
    final long $score = Double.doubleToLongBits(this.getScore());
    result = result * PRIME + (int)($score >>> 32 ^ $score);
    result = result * PRIME + Arrays.deepHashCode(this.getTags());
    return result;
  }
  
  @java.lang.Override
  public String toString() {
    return "ValueExample(name=" + getName() + ", age=" + getAge() + ", score=" + getScore() + ", tags=" + Arrays.deepToString(getTags()) + ")";
  }
  
  ValueExample withAge(int age) {
    return this.age == age ? this : new ValueExample(name, age, score, tags);
  }
  
  public static final class Exercise<T> {
    private final String name;
    private final T value;
    
    private Exercise(String name, T value) {
      this.name = name;
      this.value = value;
    }
    
    public static <T> Exercise<T> of(String name, T value) {
      return new Exercise<T>(name, value);
    }
    
    public String getName() {
      return this.name;
    }
    
    public T getValue() {
      return this.value;
    }
    
    @java.lang.Override
    public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof ValueExample.Exercise)) return false;
      final Exercise<?> other = (Exercise<?>)o;
      final Object this$name = this.getName();
      final Object other$name = other.getName();
      if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
      final Object this$value = this.getValue();
      final Object other$value = other.getValue();
      if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
      return true;
    }
    
    @java.lang.Override
    public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final Object $name = this.getName();
      result = result * PRIME + ($name == null ? 43 : $name.hashCode());
      final Object $value = this.getValue();
      result = result * PRIME + ($value == null ? 43 : $value.hashCode());
      return result;
    }
    
    @java.lang.Override
    public String toString() {
      return "ValueExample.Exercise(name=" + getName() + ", value=" + getValue() + ")";
    }
  }
}
```
---

**生成器模式**

## @Synchronized
类似Java中的Synchronized 关键字，可以通过@Synchronized("value)显示指定锁的对象

Synchronized关键字通过this锁定，但注释锁定在名为$lock的字段上，该字段是私有的。

如果是一个普通方法的话会生成一个普通常量，类型为Object

如果注释static方法，则注释会锁定名为$ LOCK的静态字段。

避免暴露你的锁，这样会避免不受你控制的其他代码也锁定这个对象，造成竞争条件 造成相关线程错误


~~~java

public class SynchronizedExample {
  private final Object readLock = new Object();
  
  @Synchronized
  public static void hello() {
    System.out.println("world");
  }
  
  @Synchronized
  public int answerToLife() {
    return 42;
  }
  
  @Synchronized("readLock")
  public void foo() {
    System.out.println("bar");
  }
}
~~~
翻译后

~~~java
public class SynchronizedExample {
  private static final Object $LOCK = new Object[0];
  private final Object $lock = new Object[0];
  private final Object readLock = new Object();
  
  public static void hello() {
    synchronized($LOCK) {
      System.out.println("world");
    }
  }
  
  public int answerToLife() {
    synchronized($lock) {
      return 42;
    }
  }
  
  public void foo() {
    synchronized(readLock) {
      System.out.println("bar");
    }
  }
}
~~~
这个就比较简单直接添加了synchronized关键字就Ok啦。不过现在JDK也比较推荐的是 Lock 对象，这个可能用的不是特别多。

---
## @SneakyThrows

自动化抛出异常，官网不建议使用，不作讲解

---
## @Log

再也不用写那些差不多的LOG啦


可以手动指定对应的topicname。


@CommonsLog
Creates private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogExample.class);


@Flogger
Creates private static final com.google.common.flogger.FluentLogger log = com.google.common.flogger.FluentLogger.forEnclosingClass();


@JBossLog
Creates private static final org.jboss.logging.Logger log = org.jboss.logging.Logger.getLogger(LogExample.class);


@Log
Creates private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());


@Log4j
Creates private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class);


@Log4j2
Creates private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);


@Slf4j
Creates private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);


@XSlf4j
Creates private static final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);


~~~java
@Log
public class LogExample {
  
  public static void main(String... args) {
    log.error("Something's wrong here");
  }
}
~~~
~~~java
@Slf4j
public class LogExampleOther {
  
  public static void main(String... args) {
    log.error("Something else is wrong here");
  }
}
~~~
~~~java
@CommonsLog(topic="CounterLog")
public class LogExampleCategory {

  public static void main(String... args) {
    log.error("Calling the 'CounterLog' with a message");
  }
}
~~~
翻译后：

~~~java
public class LogExample {
  private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());
  
  public static void main(String... args) {
    log.error("Something's wrong here");
  }
}
public class LogExampleOther {
  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExampleOther.class);
  
  public static void main(String... args) {
    log.error("Something else is wrong here");
  }
}
public class LogExampleCategory {
  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog("CounterLog");

  public static void main(String... args) {
    log.error("Calling the 'CounterLog' with a message");
  }
}
~~~

# 原理

Lombok这款插件正是依靠可插件化的Java自定义注解处理API（JSR 269: Pluggable Annotation Processing API）来实现在Javac编译阶段利用“Annotation Processor”对自定义的注解进行预处理后生成真正在JVM上面执行的“Class文件”。有兴趣的同学反编译带有Lombok注解的类文件也就一目了然了。其大致执行原理图如下：

![](https://box.kancloud.cn/8cb5cf004c01aa7f341602ee1d3fcc7e_600x130.png)

从上面的这个原理图上可以看出Annotation Processing是编译器在解析Java源代码和生成Class文件之间的一个步骤。其中Lombok插件具体的执行流程如下：

![](https://box.kancloud.cn/a09e8f2534663f4dc474d4867f4d9365_278x689.png)

从上面的Lombok执行的流程图中可以看出，在Javac 解析成AST抽象语法树之后, Lombok 根据自己编写的注解处理器，动态地修改 AST，增加新的节点（即Lombok自定义注解所需要生成的代码），最终通过分析生成JVM可执行的字节码Class文件。使用Annotation Processing自定义注解是在编译阶段进行修改，而JDK的反射技术是在运行时动态修改，两者相比，反射虽然更加灵活一些但是带来的性能损耗更加大。

### [JSR269与AST介绍](example/jsr269.md)