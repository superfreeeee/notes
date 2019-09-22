# Java基礎

## 類構造順序

### 測試代碼
```java
public class Test {
  public static void main(String[] args) {
    System.out.println("Test start: ");
    System.out.println(ClassA.propertyString);
    System.out.println(new ClassA());
  }
}

class ClassA {
  static String propertyString = "propertyString";
  {
    System.out.println("instance code block of ClassA");
  }

  static {
    System.out.println("static code block of ClassA");
  }

  public ClassA() {
    System.out.println("constructor of ClassA");    
  }
}
```
```
$ java Test.java
Test start: 
static code block of ClassA
propertyString
instance code block of ClassA
constructor of ClassA
ClassA@70dea4e
```

## 原子類
`Unsafe類`提供底層操作  
### 基本類  
`java.util.concurrent.atomic.AtomicBoolean`
`java.util.concurrent.atomic.AtomicInteger`
`java.util.concurrent.atomic.AtomicLong`

- 常用方法
```java
public interface AtomicT {

  addAndGet(T delta);   // add then return new value

  incrementAndGet();    // +1 then return new value

  getAndSet(T newValue);  // set new value then return old value

  getAndIncrement();    // +1 return old value
}
```

### 數組類  
`java.util.concurrent.atomic.AtomicIntegerArray`
`java.util.concurrent.atomic.AtomicLongArray`
`java.util.concurrent.atomic.AtomicReferenceArray`

- 常用方法

### 更新引用類
`java.util.concurrent.atomic.AtomicReference`
`java.util.concurrent.atomic.AtomicReferenceFieldUpdater`
`java.util.concurrent.atomic.AtomicMarkableReference`

- 常用方法

### 更新字段類
`java.util.concurrent.atomic.AtomicIntegerFieldUpdater`
`java.util.concurrent.atomic.AtomicLongFieldUpdater`
`java.util.concurrent.atomic.AtomicStampedReference`

- 常用方法