# Design Pattern

## Proxy
```java
// dependency
// Proxy.newProxyInstance
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)

// InvocationHandler
public interface InvocationHandler {
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```
```java
// interface
public interface Speak {
  String say(String content);
}

// target class
@Data
public class Person implements Speak {

  private String name;

  private int age;

  public String say(String content) {
    System.out.println("hi " + name + " " + age + "content: " + content);
    return "say return"
  }

}

// Proxy Factory
public class ProxyFactory {

  private Object target;
  public ProxyFactory(Object target) {
    this.target = target;
  }

  public Object getProxyInstance() {
    return Proxy.newProxyInstance(
      target.getClass().getClassLoader(),
      target.getClass().getInterfaces,
      new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          System.out.println("before method: " + method.getName());
          Object returnValue = method.invoke(proxy, args);
          System.out.println("end method: " + method.getName());
          return returnValue;
        }
      }
    );
  }
}

```
### Proxy implements - CGLIB
```java
// dependency
public interface MethodInterceptor extends Callback {
  Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable;
}
```
```java
// target class, no need to implements the interface
@Data
public class Person {

  private String name;

  private int age;

  public String say(String content) {
    System.out.println("hi " + name + " " + age + "content: " + content);
    return "say return"
  }

}

// Proxy Factory
public class CgProxyFactory<T> {
  private T target;
  public CgProxyFactory(T target) {
    this.target = target;
  }

  public T getProxyInstance() {
    Enhancer en = new Enhancer();
    en.setSuperClass(this.target.getClass());
    en.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method:" + method.getName());
        Object returnValue = method.invoke(target, objects);
        System.out.println("end method:" + method.getName());
        return returnValue;
      }
    });
    return (T)en.create();
  }
}
```
