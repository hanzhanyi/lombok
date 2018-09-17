## @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor

[Constructor详解](/Constructor.md)
这几个注解分别为类自动生成了无参构造器、指定参数的构造器和包含所有参数的构造器。

这三个注解都会忽略static变量

@RequiredArgsConstructor为每个需要特殊处理的字段会生成单独的构造函数（未初始化的final字段
以及@NonNull字段）。所有未初始化的final字段都会获得一个参数，以及标记为@NonNull未声明的任何字段。
对于标记有的字段@NonNull，还会生成显式空检查。构造函数将抛出一个
NullPointerExceptionif用于标记为@NonNullcontains 的字段的任何参数null。
参数的顺序与字段在类中的显示顺序相匹配。

所有未初始化的final字段都会获得一个参数，以及标记为@NonNull的任何字段。
对于标有@NonNull的字段，还会生成显式空检查。
如果用于标记为@NonNull的字段的任何参数包含null，则构造函数将抛出
NullPointerException。参数的顺序与字段在类中的显示顺序相匹配。

@RequiredArgsConstructor（staticName =“of”）。
与普通构造函数不同，这种静态工厂方法意味着您的API用户可以编写MapEntry.of（“foo”，5）
而不是更长的new MapEntry <String，Integer>（“foo”，5）。
@RequiredArgsConstructor(staticName = “of”)会生成一个of()的静态方法，并把构造方法设置为私有的


@NoArgsConstructor将生成一个无参构造函数。如果这是final类型的，将导致编译器错误，
除非使用@NoArgsConstructor（force = true），然后使用0 / false / null初始化所有final字段。
对于具有约束的字段，例如@NonNull字段，不会生成任何检查，因此请注意，在稍后正确初始化这些字段之前，
通常不会满足这些约束。

@AllArgsConstructor为类中的每个字段作为参数的构造函数。标有@NonNull的字段会导致对这些参数进行空检查。
~~~java
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

~~~java
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
