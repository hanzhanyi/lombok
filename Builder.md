
## @Builder

[@Builder详解](/Builder.md)

@Builder使用构建者模式，允许您使用以下代码自动生成使您的类可实例化所需的代码
Person.builder().name("Adam Savage").city("San Francisco").job("Mythbusters").job("Unchained Reaction").build();

它把我们的Bean类包装为一个构建者模式，编译时增加了一个Builder内部类和全字段的构造器。
@Builder应用于名为com.yoyodyne.FancyList <T>的类，则构建器名称将为FancyListBuilder <T>
如果@Builder应用于返回void的方法，则构建器将命名为VoidBuilder。
提供了一种构建对象的方式。
@Builder允许您使用以下代码自动生成使您的类可实例化所需的代码
Person.builder().name("Adam Savage").city("San Francisco").job("Mythbusters").job("Unchained Reaction").build();

@Builder可以放在类，构造函数或方法上。放在构造参数中指，生成对应构造参数中的字段的Builder方法。
最后，将@Builder应用于类就好像您将@AllArgsConstructor（access = AccessLevel.PACKAGE）添加到类中并将@Builder注释应用于此all-args构造函数。
这仅适用于您自己没有编写任何显式构造函数的情况。如果你有一个显式的构造函数，请将@Builder注释放在构造函数而不是类上，不然会报错。

@Builder可以为集合参数/字段生成所谓的“@singular”方法
Person.builder().job("Mythbusters").job("Unchained Reaction").build(); 可以使用这种方式对集合进行赋值

构建器的可配置方面是：

构建器的类名（默认值：返回类型+'Builder'）
build（）方法的名称（默认值：“build”）
builder（）方法的名称（默认值：“builder”）
如果你想要toBuilder（）（默认值：no）

@Builder(builderClassName = "HelloWorldBuilder", buildMethodName = "execute", builderMethodName = "helloWorld", toBuilder = true)

如果一个参数没有在初始化时进行赋值，则为 it always gets 0 / null / false.
注意：如果在字段上进行初始化赋值，则需要添加@Builder.Default:

@Singular只能应用于lombok已知的集合类型。目前，支持的类型是：

java.util:
Iterable, Collection, and List (backed by a compacted unmodifiable ArrayList in the general case).
Set, SortedSet, and NavigableSet (backed by a smartly sized unmodifiable HashSet or TreeSet in the general case).
Map, SortedMap, and NavigableMap (backed by a smartly sized unmodifiable HashMap or TreeMap in the general case).
Guava's com.google.common.collect:
ImmutableCollection and ImmutableList (backed by the builder feature of ImmutableList).
ImmutableSet and ImmutableSortedSet (backed by the builder feature of those types).
ImmutableMap, ImmutableBiMap, and ImmutableSortedMap (backed by the builder feature of those types).
ImmutableTable (backed by the builder feature of ImmutableTable).

 if your collection is called statuses, then the add-one method will
 automatically be called status. You can also specify the singular form of your identifier explictly by passing the singular form as argument to the annotation like so: @Singular("axis") List<Line> axes;.
 
 https://projectlombok.org/features/BuilderSingular
 
 lombok.singular.useGuava = [true | false] 
 如果为true，lombok将使用guava的ImmutableXxx构建器和类型来实现java.util集合接口，而不是基于Collections.unmodifiableXxx创建实现。 如果使用此设置，则必须确保guava实际上在类路径和构建路径上可用。
 
 如果您的字段/参数具有guava的 ImmutableXxx类型之一，则会自动使用Guava。
 
 @Singular对java.util.NavigableMap / Set的支持仅在使用JDK1.8或更高版本进行编译时才有效。
 
 已排序的集合（java.util：SortedSet，NavigableSet，SortedMap，NavigableMap和guava：ImmutableSortedSet，ImmutableSortedMap）要求集合的type参数具有自然顺序（实现java.util.Comparable）。无法传递显式比较器以在构建器中使用。


~~~java
@Builder
public class BuilderExample {
  private String name;
  private int age;
  @Singular private Set<String> occupations;
}
~~~
翻译后：


~~~java
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
