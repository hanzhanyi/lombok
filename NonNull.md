## @NonNull

可以在方法参数或者构造参数的参数上使用@NonNull注解，让lombok为您生成null-chekc语句，并将插入到方法的最顶层.
如果为空，则抛出NullPointerException。
null-check语句类似

~~~java
if (param == null) throw new NullPointerException("param is marked @NonNull but is null");
~~~

对于构造函数，将在任何显式this（）或super（）调用之后立即插入空检查。<br>
请注意：lombok始终将为字段名标注@NonNull注释视为空检查的信号，会将所有自动生成的构造函数或方法参数都进行null-check语句检查
如使用@Data

~~~java
@Setter
@AllArgsConstructor
public class NonNullExample extends Something {
    @NonNull
    private String name;

    public NonNullExample(@NonNull Person person) {
        super("HelloWorld");
        this.name = person.getName();
    }
}
~~~
翻译成 Java 程序是：

~~~java
public class NonNullExample extends Something {
    @NonNull
    private String name;

    public NonNullExample(@NonNull Person person) {
        super("HelloWorld");
        if (person == null) {
            throw new NullPointerException("person is marked @NonNull but is null");
        } else {
            this.name = person.getName();
        }
    }

    public void setName(@NonNull String name) {
        if (name == null) {
            throw new NullPointerException("name is marked @NonNull but is null");
        } else {
            this.name = name;
        }
    }

    public NonNullExample(@NonNull String name) {
        if (name == null) {
            throw new NullPointerException("name is marked @NonNull but is null");
        } else {
            this.name = name;
        }
    }
}
~~~