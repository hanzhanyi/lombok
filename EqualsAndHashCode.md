
## EqualsAndHashCode

自动生成equals&hashcode方法<br>
原文中提到的大致有以下几点：<br>
1. 此注解会生成equals(Object other) 和 hashCode()方法。<br>
2. 它默认使用非静态，非瞬态的属性<br>
3. 可通过参数exclude排除一些属性<br>
4. 可通过参数of指定仅使用哪些属性<br>
5. 它默认仅使用该类中定义的属性且不调用父类的方法<br>
6. 可通过callSuper=true解决上一点问题。让其生成的方法中调用父类的方法。<br>

另：@Data相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5个注解的合集。


通过官方文档，可以得知，当使用@Data注解时，则有了@EqualsAndHashCode注解，那么就会在此类中存在equals(Object other) 和 hashCode()方法，且不会使用父类的属性，这就导致了可能的问题。<br>
比如，有多个类有相同的部分属性，把它们定义到父类中，恰好id（数据库主键）也在父类中，那么就会存在部分对象在比较时，它们并不相等，却因为lombok自动生成的equals(Object other) 和 hashCode()方法判定为相等，从而导致出错。<br>

修复此问题的方法很简单：<br>
1. 使用@Getter @Setter @ToString代替@Data并且自定义equals(Object other) 和 hashCode()方法，比如有些类只需要判断主键id是否相等即足矣。<br>
2. 或者使用在使用@Data时同时加上@EqualsAndHashCode(callSuper=true)注解。<br>

lombok.equalsAndHashCode.doNotUseGetters同样可以指定是否使用get语句获取对象
同样这种比较时，数组使用的也是Arrays.deepHashCode()，引用不当也会造成StackOverflowErrors

对于float和double类型保留两位浮点数进行判断
~~~java
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EqualsAndHashCodeExample {
  private transient int transientVar = 10;
  private String name;
  private double score;
  @EqualsAndHashCode.Exclude private Shape shape = new Square(5, 10);
  private String[] tags;
  @EqualsAndHashCode.Exclude private int id;
  
  public String getName() {
    return this.name;
  }
  
  @EqualsAndHashCode(callSuper=true)
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
import java.util.Arrays;

public class EqualsAndHashCodeExample {
  private transient int transientVar = 10;
  private String name;
  private double score;
  private Shape shape = new Square(5, 10);
  private String[] tags;
  private int id;
  
  public String getName() {
    return this.name;
  }
  
  @Override public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof EqualsAndHashCodeExample)) return false;
    EqualsAndHashCodeExample other = (EqualsAndHashCodeExample) o;
    if (!other.canEqual((Object)this)) return false;
    if (this.getName() == null ? other.getName() != null : !this.getName().equals(other.getName())) return false;
    if (Double.compare(this.score, other.score) != 0) return false;
    if (!Arrays.deepEquals(this.tags, other.tags)) return false;
    return true;
  }
  
  @Override public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long temp1 = Double.doubleToLongBits(this.score);
    result = (result*PRIME) + (this.name == null ? 43 : this.name.hashCode());
    result = (result*PRIME) + (int)(temp1 ^ (temp1 >>> 32));
    result = (result*PRIME) + Arrays.deepHashCode(this.tags);
    return result;
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof EqualsAndHashCodeExample;
  }
  
  public static class Square extends Shape {
    private final int width, height;
    
    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
    
    @Override public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof Square)) return false;
      Square other = (Square) o;
      if (!other.canEqual((Object)this)) return false;
      if (!super.equals(o)) return false;
      if (this.width != other.width) return false;
      if (this.height != other.height) return false;
      return true;
    }
    
    @Override public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      result = (result*PRIME) + super.hashCode();
      result = (result*PRIME) + this.width;
      result = (result*PRIME) + this.height;
      return result;
    }
    
    protected boolean canEqual(Object other) {
      return other instanceof Square;
    }
  }
}
