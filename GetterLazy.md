## @Getter(lazy=true)

这个注解的作用相当于缓存，就是我在第一次调用后这个值会一直存在，不在浪费资源去重复生成了
  
  
使用了getter这个annotation可以在实际使用到cached的时候生成cached，同时，Lombok会自动去管理线程安全的问题，不会存在重复赋值的问题,
可以替代经典的Double Check Lock样板代码。


```java
import lombok.Getter;

public class GetterLazyExample {
  @Getter(lazy=true) private final double[] cached = expensive();
  
  private double[] expensive() {
    double[] result = new double[1000000];
    for (int i = 0; i < result.length; i++) {
      result[i] = Math.asin(i);
    }
    return result;
  }
}
```
翻译后：
```java

 public class GetterLazyExample {
  private final java.util.concurrent.AtomicReference<java.lang.Object> cached = new java.util.concurrent.AtomicReference<java.lang.Object>();
  
  public double[] getCached() {
    java.lang.Object value = this.cached.get();
    if (value == null) {
      synchronized(this.cached) {
        value = this.cached.get();
        if (value == null) {
          final double[] actualValue = expensive();
          value = actualValue == null ? this.cached : actualValue;
          this.cached.set(value);
        }
      }
    }
    return (double[])(value == this.cached ? null : value);
  }
  
  private double[] expensive() {
    double[] result = new double[1000000];
    for (int i = 0; i < result.length; i++) {
      result[i] = Math.asin(i);
    }
    return result;
  }
}
```
