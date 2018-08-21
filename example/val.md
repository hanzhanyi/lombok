# val

您可以使用val作为局部变量声明的类型，而不是实际写入类型。 
执行此操作时，将从初始化表达式推断出类型。 
本地变量也将成为最终变量。 此功能仅适用于局部变量和foreach循环，
而不适用于成员变量。 初始化表达式是必需的。

val实际上是一种“类型” ，您必须导入它才能使val工作
（或使用lombok.val作为类型）。 
在本地变量声明中存在这种类型会触发添加final关键字以及复制
初始化表达式的类型，该类型会覆盖'伪'val类型。

~~~
import java.util.ArrayList;
import java.util.HashMap;
import lombok.val;

public class ValExample {
  public String example() {
    val example = new ArrayList<String>();
    example.add("Hello, World!");
    val foo = example.get(0);
    return foo.toLowerCase();
  }
  
  public void example2() {
    val map = new HashMap<Integer, String>();
    map.put(0, "zero");
    map.put(5, "five");
    for (val entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
}

~~~

编译后

~~~
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValExample {
  public String example() {
    final ArrayList<String> example = new ArrayList<String>();
    example.add("Hello, World!");
    final String foo = example.get(0);
    return foo.toLowerCase();
  }
  
  public void example2() {
    final HashMap<Integer, String> map = new HashMap<Integer, String>();
    map.put(0, "zero");
    map.put(5, "five");
    for (final Map.Entry<Integer, String> entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
}
~~~