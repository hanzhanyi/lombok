## @ToString

默认的toString格式为：ClassName(fieldName= fieleValue ,fieldName1=fieleValue)。


不过可以通过设置lombok.toString.includeFieldNames=false使输出隐藏fieldName为
ClassName( fieleValue ,fieleValue)


默认情况下，ToString将打印所有非static字段,不过可以通过@ToString.Include注解添加将static属性添加上。


同时可以通过将@ToString(callSuper = true)设置为true，可以将toString的父类实现的输出到实现中。

```java
@ToString
public class ToStringExample {
    private static String BOSS;
    @ToString.Include
    private static String IQIYI;
    private String name;
    private String[] tags;

    @ToString(callSuper = true, includeFieldNames = false)
    public static class Square extends Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
```
```java
public class ToStringExample {
    private static String BOSS;
    private static String IQIYI;
    private String name;
    private String[] tags;

    public ToStringExample() {
    }

    public String toString() {
        return "ToStringExample(IQIYI=" + IQIYI + ", name=" + this.name + ", tags=" + Arrays.deepToString(this.tags) + ")";
    }

    public static class Square extends Shape {
        private final int width;
        private final int height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public String toString() {
            return "ToStringExample.Square(super=" + super.toString() + ", " + this.width + ", " + this.height + ")";
        }
    }
}
```


任何类定义都可以用@ToString注释，让lombok生成toString（）方法的实现。
默认情况下，它会按顺序打印您的类名以及每个字段，并以逗号分隔。
同时可以通过@ToString.Include（rank = -1）更改成员的打印顺序，
默认是0，更高等级的成员被首先打印，相同等级按照出现顺序打印。

可以使用@ToString(onlyExplicitlyIncluded = true)，可以使lombok只生成标注了@ToString.Include注解的字段。

@ToString.Include（name =“some other name”）<br>
更改用于标识成员的名称

lombok.toString.doNotUseGetters<br>
如果设置为true，则在生成toString方法时，lombok将直接访问字段，而不是使用getter（如果可用）

```java
@Getter
@ToString(doNotUseGetters = true)
public class ToStringExample {

    @ToString.Include(rank = -1)
    private String name;

    @ToString.Include(rank = 0)
    private String[] tags;

    @ToString.Include(rank = 1, name = "boss")
    private String iqiyi;

    @Getter
    @ToString
    public static class ToStringGetterExample{
        private String student;
    }
}
```
```java
public class ToStringExample {
    private String name;
    private String[] tags;
    private String iqiyi;

    public ToStringExample() {
    }

    public String getName() {
        return this.name;
    }

    public String[] getTags() {
        return this.tags;
    }

    public String getIqiyi() {
        return this.iqiyi;
    }

    public String toString() {
        return "ToStringExample(boss=" + this.iqiyi + ", tags=" + Arrays.deepToString(this.tags) + ", name=" + this.name + ")";
    }

    public static class ToStringGetterExample {
        private String student;

        private ToStringGetterExample(ToStringExample var1) {
            this.this$0 = var1;
        }

        public String getStudent() {
            return this.student;
        }

        public String toString() {
            return "ToStringExample.ToStringGetterExample(student=" + this.getStudent() + ")";
        }
    }
}
```

数组通过Arrays.deepToString打印，这意味着包含自身的数组将导致StackOverflowErrors。
这与ArrayList类似。

在属性上同时使用@ ToString.Exclude和@ ToString.Include，该字段属性将被排除在外。



~~~java
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

~~~java
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