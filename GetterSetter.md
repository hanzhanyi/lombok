## @Getter/@Setter 很实用的注解
@Getter and @Setter

此注解可标注在类和成员变量上。标注在类上时，表示自动生成所有非静态字段，final也会自动生成get方法，但不会生成Set方法。
如果是boolean类型，方法命名isFoo开头,如果变量以isXXX开头，则生成的get方法为isXXX(),set方法为setXXX(),所以不要以is开头设置变量
也可以通过设置lombok.getter.noIsPrefix = true
将方法名改成getFoo();


当类中已经存在对应的Getter/Setter方法则不会再次生成，可以对特殊的实现方式进行手动定制化。

您始终可以使用特殊的AccessLevel.NONE访问级别手动禁用任何字段的getter / setter生成。这使您可以覆盖类上的@Getter，@ Setter或@Data注解的行为。
PUBLIC，PROTECTED，PACKAGE和PRIVATE

```java
@Setter
@lombok.Getter
public class Getter {

    @Setter(AccessLevel.PROTECTED)
    private String iqiyi;

    @Setter(AccessLevel.NONE)
    private boolean boss;

    public String getIqiyi() {
        return "getIqiyi";
    }
}
```
```java
public class Getter {
    private String iqiyi;
    private boolean boss;

    public Getter() {
    }

    public String getIqiyi() {
        return "getIqiyi";
    }

    public boolean isBoss() {
        return this.boss;
    }

    protected void setIqiyi(String iqiyi) {
        this.iqiyi = iqiyi;
    }
}
```

扩展配置：<br>


lombok.accessors.chain = [true | false] (default: false)如果设置为true，生成的setter将返回this(而不是void)，即可通过User user=new User().setAge(31).setName("pollyduan")，类似这种形式，通过这个配置我们可以像jquery一样愉快的链式编程了。可以在类加增加一个@Accessors 注解 配置chain属性，优先于全局配置。


lombok.accessors.fluent = [true | false] (default: false)不再使用Set/Get前缀，只是使用字段名作为方法名。


```java
@Getter
@Setter
@Accessors(chain = true)
public class GetterSetter {

    private String name;

    private String iqiyi;

    @Accessors(fluent = true)
    private String boss;

}
```
```java
public class GetterSetter {
    private String name;
    private String iqiyi;
    private String boss;

    public GetterSetter() {
    }

    public String getName() {
        return this.name;
    }

    public String getIqiyi() {
        return this.iqiyi;
    }

    public String boss() {
        return this.boss;
    }

    public GetterSetter setName(String name) {
        this.name = name;
        return this;
    }

    public GetterSetter setIqiyi(String iqiyi) {
        this.iqiyi = iqiyi;
        return this;
    }

    public GetterSetter boss(String boss) {
        this.boss = boss;
        return this;
    }
}
```

