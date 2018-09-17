
## EqualsAndHashCode

自动生成equals&hashcode方法<br>
原文中提到的大致有以下几点：<br>
1. 此注解会生成equals(Object other) 和 hashCode()方法。<br>
2. 它默认使用non-static，non-transient参数进行构建<br>
3. 可通过参数exclude排除一些属性<br>
4. 可通过参数of指定仅使用哪些属性<br>
5. 它默认仅使用该类中定义的属性且不调用父类的方法<br>
6. 可通过callSuper=true让其生成的方法中调用父类equals的方法。<br>

把它们定义到父类中，恰好id（数据库主键）也在父类中，那么就会存在部分对象在比较时，它们并不相等，却因为lombok自动生成的equals(Object other) 和 hashCode()方法判定为相等，从而导致出错。<br>

lombok.equalsAndHashCode.doNotUseGetters 同样可以指定是否使用get语句获取对象<br>
同样这种比较时，数组使用的也是Arrays.deepHashCode()，引用不当也会造成StackOverflowErrors

~~~java
@EqualsAndHashCode
public class EqualsAndHashCodeExample {
    private transient int transientVar = 10;
    private static String boss;
    private String name;
    private String[] tags;

    @EqualsAndHashCode.Exclude
    private int id;

    @EqualsAndHashCode(callSuper = true)
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

public class EqualsAndHashCodeExample {
    private transient int transientVar = 10;
    private static String boss;
    private String name;
    private String[] tags;
    private int id;

    public EqualsAndHashCodeExample() {
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof EqualsAndHashCodeExample)) {
            return false;
        } else {
            EqualsAndHashCodeExample other = (EqualsAndHashCodeExample)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$name = this.name;
                Object other$name = other.name;
                if (this$name == null) {
                    if (other$name == null) {
                        return Arrays.deepEquals(this.tags, other.tags);
                    }
                } else if (this$name.equals(other$name)) {
                    return Arrays.deepEquals(this.tags, other.tags);
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof EqualsAndHashCodeExample;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $name = this.name;
        int result = result * 59 + ($name == null ? 43 : $name.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.tags);
        return result;
    }

    public static class Square extends Shape {
        private final int width;
        private final int height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof EqualsAndHashCodeExample.Square)) {
                return false;
            } else {
                EqualsAndHashCodeExample.Square other = (EqualsAndHashCodeExample.Square)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (!super.equals(o)) {
                    return false;
                } else if (this.width != other.width) {
                    return false;
                } else {
                    return this.height == other.height;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof EqualsAndHashCodeExample.Square;
        }

        public int hashCode() {
            int PRIME = true;
            int result = super.hashCode();
            result = result * 59 + this.width;
            result = result * 59 + this.height;
            return result;
        }
    }
}
~~~
