### lombok处理流程大致为：

- 自定义需要处理的注解

- 利用JSR269 api(Pluggable Annotation Processing API )创建注解处理器AnnotationProcessor

- 利用tools.jar中提供的javac api处理AST(抽象语法树)

- 将功能注册进jar包

## 问题：

### 1.Lombok是如何对在编译过程进行影响的？

Java提供了一个注册服务的机制。如果一个标记处理器被注册成了一个服务，

编译器就会自动的去找到这个标记处理器。注册的方法是，
在classpath中找到一个叫META-INF/services的文件夹，
然后放入一个javax.annotation.processing.Processor的文件。
文件格式是很明显的，就是要包含要注册的标记处理器的完整名称。
每个名字都要占单独的一行。

### 2.lombok是如何加载这些文件的？

通过自定义一个类加载器ShadowClassLoader，重写了loadClass方法，
用以加载当前jar包中，以SCL.lombok结尾的文件。

### 3.lombok是怎么区分不同编译器所做的操作的？

根据不同的编译器环境进行判断，不同的ProcessingEnvironment

JAVAC: com.sun.tools.javac.processing.JavacProcessingEnvironment

ECJ：Eclipse Compile For Java , startWith（org.eclipse.jdt.）

AnnotationProcessor类中 调用从ProcessorDescriptor的want方法，

根据当前的ProcessingEnvironmeng名称进行判断，

javac为“com.sun.tools.javac.processing.JavacProcessingEnvironment”，ECJ为startsWith("org.eclipse.jdt.")

判断从而确定不同的注解处理器，进行加载主注解处理器。lombok.javac.apt.LombokProcessor



### 4.lombok是如何操作寻找对应的handlers？

在lombok.javac.apt.LombokProcessor类中 new JavacTransformer(procEnv.getMessager(), trees);
过程中，通过java的SPI，从META-INF中获取所有接口，将处理器初始化，并且放到Map中。


### 5.lombok是怎么样处理注解处理顺序的？

我们知道，Lombok对不同注解的处理顺序不同，比如：一定要有Getter方法，才会进一步生成ToString方法。

new AnnotationHandlerContainer(handler, annotationClass);在加载对应Handlers过程中，会对注解进行处理顺序的排序。

通过handle注解的@HandlerPriority(value) 属性，在加载所有handle处理类时，
获取value值，从而进行顺序操作。

lombok.javac.apt.LombokProcessor 中的process()方法中进行依次处理。

### 6.lombok通过Visitor设计模式进行处理多个注解不同type的处理方式。

(Visitor 访问者模式)[https://blog.csdn.net/qq_29379115/article/details/78152002]

### 


Messager主要是用来在编译期打log用的

JavacTrees提供了待处理的抽象语法树

TreeMaker封装了创建AST节点的一些方法

Names提供了创建标识符的方法