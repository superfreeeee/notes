# Java基礎

## 元注解

### 樣例
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MthCache {
  String key();
}
```
### `@Target`
- 說明注解使用範圍

#### ElementType取值
value | description
---|---
TYPE | 类，接口或者枚举
FIELD | 域，包含枚举常量
METHOD | 方法
PARAMETER | 参数
CONSTRUCTOR | 构造方法
LOCAL_VARIABLE | 局部变量
ANNOTATION_TYPE | 注解类型
PACKAGE | 包

```java
public enum ElementType {
  /**用于描述类、接口(包括注解类型) 或enum声明 Class, interface (including annotation type), or enum declaration */
  TYPE,

  /** 用于描述域 Field declaration (includes enum constants) */
  FIELD,

  /**用于描述方法 Method declaration */
  METHOD,

  /**用于描述参数 Formal parameter declaration */
  PARAMETER,

  /**用于描述构造器 Constructor declaration */
  CONSTRUCTOR,

  /**用于描述局部变量 Local variable declaration */
  LOCAL_VARIABLE,

  /** Annotation type declaration */
  ANNOTATION_TYPE,

  /**用于描述包 Package declaration */
  PACKAGE,

  /**
    * 用来标注类型参数 Type parameter declaration
    * @since 1.8
    */
  TYPE_PARAMETER,

  /**
    *能标注任何类型名称 Use of a type
    * @since 1.8
    */
  TYPE_USE
```

### `@Retention`
- 說明注解生存週期

#### RetentionPolicy取值
value | description
---|---
SOURCE | 源码级别保留，编译后即丢弃。
CLASS | 编译级别保留，编译后的class文件中存在，在jvm运行时丢弃，这是默认值。
RUNTIME | 运行级别保留，编译后的class文件中存在，在jvm运行时保留，可以被反射调用。

### `@Documented`
- 標記可被文檔化

### `Inherited`
- 允許子類繼承父類注解


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