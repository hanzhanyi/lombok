## @NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor

这三个注解都会忽略static变量

## @NoArgsConstructor

此注解自动生成一个无参构造器，如果类中存在未赋值的final类型变量，则会编译报错。

但可通过@NoArgsConstructor（force = true），使用0 / false / null初始化所有final字段

```java
@NoArgsConstructor(force = true)
public class NoArgsConstructorExample {
    
    private final String name;
    
    private String studentName;

}
```
```java
public class NoArgsConstructorExample {
    private final String name = null;
    private String studentName;

    public NoArgsConstructorExample() {
    }
}
```

## @AllArgsConstructor
为类中所有参数（排除static）进行构造参数的生成。标有@NonNull的字段会导致对这些参数进行空检查。

```java
@AllArgsConstructor
public class AllArgsConstructorExample {
    private String name ;
    private static String  hanzhanyi;
    private final String iqiyi;
}
```
```java
public class AllArgsConstructorExample {
    private String name;
    private static String hanzhanyi;
    private final String iqiyi;

    public AllArgsConstructorExample(String name, String iqiyi) {
        this.name = name;
        this.iqiyi = iqiyi;
    }
}
```

## @RequiredArgsConstructor


@RequiredArgsConstructor为每个未初始化的final字段以及@NonNull字段生成构造方法。


参数的顺序与字段在类中的显示顺序相匹配。


@RequiredArgsConstructor（staticName =“of”）。
与普通构造函数不同，这种静态工厂方法意味着您的API用户可以编写MapEntry.of（“foo”，5）
而不是更长的new MapEntry <String，Integer>（“foo”，5）。

@RequiredArgsConstructor(staticName = “of”)会生成一个of()的静态方法，并把构造方法设置为私有的

~~~java
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PROTECTED)
public class RequiredArgsConstructorExample {
    private String name;

    @NonNull
    private String forestWolf;

    private final String iqiyi = "iqiyi";
    
    @NonNull
    private final String boss;
}
~~~
翻译后：

~~~java
public class RequiredArgsConstructorExample {
    private String name;
    @NonNull
    private String forestWolf;
    private final String iqiyi = "iqiyi";
    @NonNull
    private final String boss;

    private RequiredArgsConstructorExample(@NonNull String forestWolf, @NonNull String boss) {
        if (forestWolf == null) {
            throw new NullPointerException("forestWolf is marked @NonNull but is null");
        } else if (boss == null) {
            throw new NullPointerException("boss is marked @NonNull but is null");
        } else {
            this.forestWolf = forestWolf;
            this.boss = boss;
        }
    }

    protected static RequiredArgsConstructorExample of(@NonNull String forestWolf, @NonNull String boss) {
        return new RequiredArgsConstructorExample(forestWolf, boss);
    }
}
~~~
