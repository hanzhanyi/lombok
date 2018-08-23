### lombok处理流程大致为：

- 定义编译期的注解

- 利用JSR269 api(Pluggable Annotation Processing API )创建编译期的注解处理器

- 利用tools.jar的javac api处理AST(抽象语法树)

- 将功能注册进jar包

## 问题：
### 1.Lombok是如何对在编译过程进行影响的？

Java提供了一个注册服务的机制。如果一个标记处理器被注册成了一个服务，

编译器就会自动的去找到这个标记处理器。注册的方法是，在classpath中找到一个叫META-INF/services的文件夹，然后放入一个javax.annotation.processing.Processor的文件。文件格式是很明显的，就是要包含要注册的标记处理器的完整名称。每个名字都要占单独的一行。

### 2.lombok是如何加载这些文件的？

通过自定义一个类加载器ShadowClassLoader，重写了loadClass方法，判断加载的class是否为
当前jar包下，如果是则加载对应的*.SCL.lombok文件。

### 3.lombok是怎么区分不同编译器所做的操作的？

根据不同的编译器环境进行判断，不同的ProcessingEnvironment

JAVAC: com.sun.tools.javac.processing.JavacProcessingEnvironment
ECJ：startWith（org.eclipse.jdt.）
从而确定不同的注解处理器，

### 4.lombok是如何操作寻找对应的handlers？

通过java的SPI，从META-INF中获取所有接口，将处理器初始化，并且放到Map中。

### 5.lombok是怎么样处理注解处理顺序的？

通过handle注解的@HandlerPriority(value) 属性，在加载所有handle处理类时，
获取value值，从而进行顺序操作。



### 
Messager主要是用来在编译期打log用的

JavacTrees提供了待处理的抽象语法树

TreeMaker封装了创建AST节点的一些方法

Names提供了创建标识符的方法