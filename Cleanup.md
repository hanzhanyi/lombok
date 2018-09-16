## @Cleanup

Automatic resource management: Call your close() methods safely with no hassle.


自动资源管理：安全地调用close（）方法


可以使用 @Cleanup 注解注释任何局部变量，类似：


@Cleanup InputStream in = new FileInputStream("some/file");

 
这样注释，@Cleanup确保在退出当前代码的作用域之前，自动清除给定资源。<br>
可以在作用域的末尾，通过try / finally进行构造，并调用in.close（），清除资源。


如果要清理的对象类型没有close（）方法，而是其他一些无参数方法，则可以通过@Cleanup（“dispose”）指定此方法的名称，如下所示：<br>
不过不能通过@Cleanup调用带有1个或多个参数的清理方法，也就是说只能调用无参清除方法。

~~~java
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

~~~java
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

 