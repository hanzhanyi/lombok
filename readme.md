
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

@AllArgsConstructor：注解在类上；为类提供一个全参的构造方法

@Clearup: 自动管理资源

@ToString: 用在类上可以自动撰写toString方法

@EqualsAndHashCode: 用在类上自动生成equal方法和hashcode方法

@Synchronized: 用在方法上 ,则方法声明是同步的,并自动加上锁为为一个私有属性同步锁

@Builder: 可以用在类,构造,方法上,指定生成复杂的Builder模式的API方法

@val: 声明局部变量为final类型
~~~

# Lombok使用

## val 
[val详情](example/val.md)
~~~
public String example() {
    val example = new ArrayList<String>();
    example.add("Hello, World!");
    val foo = example.get(0);
    return foo.toLowerCase();
}
~~~
翻译成 Java 程序是：

~~~
public String example() {
    final ArrayList<String> example = new ArrayList<String>();
    example.add("Hello, World!");
    final String foo = example.get(0);
    return foo.toLowerCase();
}
~~~
也就是类型推导啦。

## @NonNull

您可以在方法的参数或构造函数的参数上使用@NonNull让lombok为您生成null-check语句。

null-check语句类似
~~~
if (param == null) throw new NullPointerException("param is marked @NonNull but is null");
~~~
并将插入到方法的最顶层。对于构造函数，将在任何显式this（）或super（）调用之后立即插入空检查。

~~~
public class NonNullExample extends Something {
  private String name;
  
  public NonNullExample(@NonNull Person person) {
    super("Hello");
    this.name = person.getName();
  }
}
~~~
翻译成 Java 程序是：

~~~
public class NonNullExample extends Something {
  private String name;
  
  public NonNullExample(@NonNull Person person) {
    super("Hello");
    if (person == null) {
      throw new NullPointerException("person");
    }
    this.name = person.getName();
  }
}
~~~

## @Cleanup
自动化才是生产力

Automatic resource management: Call your close() methods safely with no hassle.

自动资源管理：安全地调用close（）方法

可以使用 @Cleanup 注解注释任何局部变量，类似：

@Cleanup InputStream in = new FileInputStream("some/file");
 
（自动化的安全调用close()方法）


~~~
public class CleanupExample {
  public static void main(String[] args) throws IOException {
    @Cleanup InputStream in = new FileInputStream(args[0]);
    @Cleanup OutputStream out = new FileOutputStream(args[1]);
    byte[] b = new byte[10000];
    while (true) {
      int r = in.read(b);
      if (r == -1) break;
      out.write(b, 0, r);
    }
  }
}
~~~
翻译成 Java 程序是：

~~~
public class CleanupExample {
  public static void main(String[] args) throws IOException {
    InputStream in = new FileInputStream(args[0]);
    try {
      OutputStream out = new FileOutputStream(args[1]);
      try {
        byte[] b = new byte[10000];
        while (true) {
          int r = in.read(b);
          if (r == -1) break;
          out.write(b, 0, r);
        }
      } finally {
        if (out != null) {
          out.close();
        }
      }
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }
}
~~~

 
## @Getter/@Setter 很实用的注解
再也不写 `public int getFoo() {return foo;}`

您可以使用@Getter和/或@Setter注释任何字段，让lombok自动生成默认的getter / setter。
默认的getter只返回字段，如果字段名为foo，则命名为getFoo（如果字段的类型为boolean，则命名为isFoo）。

除非您明确指定AccessLevel，否则生成的getter / setter方法将是公共的，如下面的示例所示。合法访问级别为PUBLIC，PROTECTED，PACKAGE和PRIVATE。

您还可以在类上放置@Getter和/或@Setter注释。在这种情况下，就像使用注释注释该类中的所有非静态字段一样。

您始终可以使用特殊的AccessLevel.NONE访问级别手动禁用任何字段的getter / setter生成。这使您可以覆盖类上的@Getter，@ Setter或@Data注释的行为。

~~~
public class GetterSetterExample {

  @Getter @Setter private int age = 10;
  
  @Setter(AccessLevel.PROTECTED) private String name;
  
  @Override public String toString() {
    return String.format("%s (age: %d)", name, age);
  }
}
~~~
翻译成 Java 程序是：

~~~
public class GetterSetterExample {

  private int age = 10;

  private String name;
  
  @Override public String toString() {
    return String.format("%s (age: %d)", name, age);
  }
  
  public int getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
 
  protected void setName(String name) {
    this.name = name;
  }
}
~~~~

## @ToString

默认的toString格式为：ClassName(fieldName= fieleValue ,fieldName1=fieleValue)。

任何类定义都可以用@ToString注释，让lombok生成toString（）方法的实现。
默认情况下，它会按顺序打印您的类名以及每个字段，并以逗号分隔。

通过将callSuper设置为true，可以将toString的父类实现的输出包含到输出中。

可以通过@ ToString.Include（rank = -1）更改成员的打印顺序。
没有等级的成员被认为具有等级0，更高等级的成员被首先打印，
并且相同等级的成员以它们在源文件中出现的相同顺序被打印。

~~~
@ToString(exclude="id")
public class ToStringExample {
  private static final int STATIC_VAR = 10;
  private String name;
  private Shape shape = new Square(5, 10);
  private String[] tags;
  private int id;
  
  public String getName() {
    return this.getName();
  }
  
  @ToString(callSuper=true, includeFieldNames=true)
  public static class Square extends Shape {
    private final int width, height;
    
    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
  }
}
~~~
翻译后：

~~~
public class ToStringExample {
  private static final int STATIC_VAR = 10;
  private String name;
  private Shape shape = new Square(5, 10);
  private String[] tags;
  private int id;
  
  public String getName() {
    return this.getName();
  }
  
  public static class Square extends Shape {
    private final int width, height;
    
    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
    
    @Override public String toString() {
      return "Square(super=" + super.toString() + ", width=" + this.width + ", height=" + this.height + ")";
    }
  }
  
  @Override public String toString() {
    return "ToStringExample(" + this.getName() + ", " + this.shape + ", " + Arrays.deepToString(this.tags) + ")";
  }
}
~~~

## @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
这几个注解分别为类自动生成了无参构造器、指定参数的构造器和包含所有参数的构造器。


@RequiredArgsConstructor为每个需要特殊处理的字段会生成单独的构造函数。
所有未初始化的final字段都会获得一个参数，以及标记为@NonNull的任何字段。
对于标有@NonNull的字段，还会生成显式空检查。
如果用于标记为@NonNull的字段的任何参数包含null，则构造函数将抛出
NullPointerException。参数的顺序与字段在类中的显示顺序相匹配。

@RequiredArgsConstructor（staticName =“of”）。
与普通构造函数不同，这种静态工厂方法意味着您的API用户可以编写MapEntry.of（“foo”，5）
而不是更长的new MapEntry <String，Integer>（“foo”，5）。

@NoArgsConstructor将生成一个无参构造函数。如果这是final类型的，将导致编译器错误，
除非使用@NoArgsConstructor（force = true），然后使用0 / false / null初始化所有final字段。
对于具有约束的字段，例如@NonNull字段，不会生成任何检查，因此请注意，在稍后正确初始化这些字段之前，
通常不会满足这些约束。

@AllArgsConstructor为类中的每个字段作为参数的构造函数。标有@NonNull的字段会导致对这些参数进行空检查。
~~~
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ConstructorExample<T> {
  private int x, y;
  @NonNull private T description;
  
  @NoArgsConstructor
  public static class NoArgsExample {
    @NonNull private String field;
  }
}
~~~
翻译后：

~~~
public class ConstructorExample<T> {
  private int x, y;
  @NonNull private T description;
  
  private ConstructorExample(T description) {
    if (description == null) throw new NullPointerException("description");
    this.description = description;
  }
  
  public static <T> ConstructorExample<T> of(T description) {
    return new ConstructorExample<T>(description);
  }
  
  @java.beans.ConstructorProperties({"x", "y", "description"})
  protected ConstructorExample(int x, int y, T description) {
    if (description == null) throw new NullPointerException("description");
    this.x = x;
    this.y = y;
    this.description = description;
  }
  
  public static class NoArgsExample {
    @NonNull private String field;
    
    public NoArgsExample() {
    }
  }
}
~~~

## EqualsAndHashCode
自动生成equals&hashcode方法
~~~
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EqualsAndHashCodeExample {
  private transient int transientVar = 10;
  private String name;
  private double score;
  @EqualsAndHashCode.Exclude private Shape shape = new Square(5, 10);
  private String[] tags;
  @EqualsAndHashCode.Exclude private int id;
  
  public String getName() {
    return this.name;
  }
  
  @EqualsAndHashCode(callSuper=true)
  public static class Square extends Shape {
    private final int width, height;
    
    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
  }
}
~~~
翻译后：
~~~
import java.util.Arrays;

public class EqualsAndHashCodeExample {
  private transient int transientVar = 10;
  private String name;
  private double score;
  private Shape shape = new Square(5, 10);
  private String[] tags;
  private int id;
  
  public String getName() {
    return this.name;
  }
  
  @Override public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof EqualsAndHashCodeExample)) return false;
    EqualsAndHashCodeExample other = (EqualsAndHashCodeExample) o;
    if (!other.canEqual((Object)this)) return false;
    if (this.getName() == null ? other.getName() != null : !this.getName().equals(other.getName())) return false;
    if (Double.compare(this.score, other.score) != 0) return false;
    if (!Arrays.deepEquals(this.tags, other.tags)) return false;
    return true;
  }
  
  @Override public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long temp1 = Double.doubleToLongBits(this.score);
    result = (result*PRIME) + (this.name == null ? 43 : this.name.hashCode());
    result = (result*PRIME) + (int)(temp1 ^ (temp1 >>> 32));
    result = (result*PRIME) + Arrays.deepHashCode(this.tags);
    return result;
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof EqualsAndHashCodeExample;
  }
  
  public static class Square extends Shape {
    private final int width, height;
    
    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
    
    @Override public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof Square)) return false;
      Square other = (Square) o;
      if (!other.canEqual((Object)this)) return false;
      if (!super.equals(o)) return false;
      if (this.width != other.width) return false;
      if (this.height != other.height) return false;
      return true;
    }
    
    @Override public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      result = (result*PRIME) + super.hashCode();
      result = (result*PRIME) + this.width;
      result = (result*PRIME) + this.height;
      return result;
    }
    
    protected boolean canEqual(Object other) {
      return other instanceof Square;
    }
  }
}
~~~
## @Data
这个一个注解就相当于@RequiredArgsConstructor，@ToString, @EqualsAndHashCode, @Getter,@Setter@Value 的集合

~~~
 * @see Getter
 * @see Setter
 * @see RequiredArgsConstructor
 * @see ToString
 * @see EqualsAndHashCode
 * @see lombok.Value
~~~

## @Builder

提供了一种构建对象的方式。
~~~
@Builder
public class BuilderExample {
  private String name;
  private int age;
  @Singular private Set<String> occupations;
}
~~~
翻译后：

~~~
public class BuilderExample {
  private String name;
  private int age;
  private Set<String> occupations;
  
  BuilderExample(String name, int age, Set<String> occupations) {
    this.name = name;
    this.age = age;
    this.occupations = occupations;
  }
  
  public static BuilderExampleBuilder builder() {
    return new BuilderExampleBuilder();
  }
  
  public static class BuilderExampleBuilder {
    private String name;
    private int age;
    private java.util.ArrayList<String> occupations;
    
    BuilderExampleBuilder() {
    }
    
    public BuilderExampleBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public BuilderExampleBuilder age(int age) {
      this.age = age;
      return this;
    }
    
    public BuilderExampleBuilder occupation(String occupation) {
      if (this.occupations == null) {
        this.occupations = new java.util.ArrayList<String>();
      }
      
      this.occupations.add(occupation);
      return this;
    }
    
    public BuilderExampleBuilder occupations(Collection<? extends String> occupations) {
      if (this.occupations == null) {
        this.occupations = new java.util.ArrayList<String>();
      }

      this.occupations.addAll(occupations);
      return this;
    }
    
    public BuilderExampleBuilder clearOccupations() {
      if (this.occupations != null) {
        this.occupations.clear();
      }
      
      return this;
    }

    public BuilderExample build() {
      // complicated switch statement to produce a compact properly sized immutable set omitted.
      // go to https://projectlombok.org/features/Singular-snippet.html to see it.
      Set<String> occupations = ...;
      return new BuilderExample(name, age, occupations);
    }
    
    @java.lang.Override
    public String toString() {
      return "BuilderExample.BuilderExampleBuilder(name = " + this.name + ", age = " + this.age + ", occupations = " + this.occupations + ")";
    }
  }
}
~~~

**生成器模式**

## @Synchronized
类似Java中的Synchronized 关键字，可以通过@Synchronized("value)显示指定锁的对象

Synchronized关键字通过this锁定，但注释锁定在名为$lock的字段上，该字段是私有的。

如果注释静态方法，则注释会锁定名为$ LOCK的静态字段。

避免暴露你的锁，这样会避免不受你控制的其他代码也锁定这个对象，造成竞争条件 造成相关线程错误


~~~

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

~~~
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

## @Log
再也不用写那些差不多的LOG啦

~~~
@Log
public class LogExample {
  
  public static void main(String... args) {
    log.error("Something's wrong here");
  }
}
~~~
~~~
@Slf4j
public class LogExampleOther {
  
  public static void main(String... args) {
    log.error("Something else is wrong here");
  }
}
~~~
~~~
@CommonsLog(topic="CounterLog")
public class LogExampleCategory {

  public static void main(String... args) {
    log.error("Calling the 'CounterLog' with a message");
  }
}
~~~
翻译后：

~~~
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