
## 问题：
### 1.Lombok是如何对在编译过程进行影响的？

Java提供了一个注册服务的机制。如果一个标记处理器被注册成了一个服务，
编译器就会自动的去找到这个标记处理器。注册的方法是，在classpath中找到一个叫META-INF/services的文件夹，然后放入一个javax.annotation.processing.Processor的文件。文件格式是很明显的，就是要包含要注册的标记处理器的完整名称。每个名字都要占单独的一行。

### 2.lombok是怎么区分不同编译器所做的操作的？

### 3.lombok是如何加载这些文件的？

### 4.lombok是如何操作寻找对应的handlers？

### 5.lombok是怎么样处理注解处理顺序的？
