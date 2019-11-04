# Lombok

## Maven依賴
```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.10</version>
  <scope>provided</scope>
</dependency>
```

# 常用註釋
Annotation | Description
-|-
`@Getter` | `getter` 方法
`@Setter` | `setter` 方法
`@NoArgsConstructor` | 無參數構造器
`@AllArgsConstructor` | 全參數構造器
`@RequiredArgsConstructor` | 必要參數構造器
`@EqualsAndHashCode` | `equals` 和 `hashcode` 方法
`@ToString` | `toString` 方法
`@Data` | 多方法整合
`@Log` | `日誌`輸出
`@Synchronized` | `同步方法`變體
`@Builder` | 構造`建造者模式方法`
`@SneakyThrows` | 自動拋出受檢查異常
`@NonNull` | 自動生成非空校驗語句
`@Clear` | 自動清理資源
`@With` | 
`val` | 自動`final`和`類型推斷`

## 1. @Setter & @Getter

### 定義
```java
// Getter
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Getter {
  // 若getter方法非public的话，可以设置可访问级别
  lombok.AccessLevel value() default lombok.AccessLevel.PUBLIC;
  AnyAnnotation[] onMethod() default {};
  // 是否启用延迟初始化
  boolean lazy() default false;
}

// Setter
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Setter {
  // 若setter方法非public的话，可以设置可访问级别
  lombok.AccessLevel value() default lombok.AccessLevel.PUBLIC;
  AnyAnnotation[] onMethod() default {};
  AnyAnnotation[] onParam() default {};
}
```

### 實例
- 編譯前
```java
@Getter
@Setter
public class GetterAndSetterDemo {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
}
```
- 編譯後
```java
public class GetterAndSetterDemo {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;

  public GetterAndSetterDemo() {}

  // 省略其它setter和getter方法
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}
```

### Lazy Getter
- 編譯前
```java
public class LazyGetterDemo {
  public static void main(String[] args) {
    LazyGetterDemo m = new LazyGetterDemo();
    System.out.println("Main instance is created");
    m.getLazy();
  }

  @Getter
  private final String notLazy = createValue("not lazy");

  @Getter(lazy = true)
  private final String lazy = createValue("lazy");

  private String createValue(String name) {
    System.out.println("createValue(" + name + ")");
    return null;
  }
}
```
- 編譯後
```java
public class LazyGetterDemo {
  private final String notLazy = this.createValue("not lazy");
  private final AtomicReference<Object> lazy = new AtomicReference();

  // 已省略部分代码
  public String getNotLazy() {
      return this.notLazy;
  }

  public String getLazy() {
    Object value = this.lazy.get();
    if (value == null) {
      synchronized(this.lazy) {
        value = this.lazy.get();
        if (value == null) {
          String actualValue = this.createValue("lazy");
          value = actualValue == null ? this.lazy : actualValue;
          this.lazy.set(value);
        }
      }
    }

    return (String)((String)(value == this.lazy ? null : value));
  }
}
```

## 2. @NoArgsConstructor

### 定義
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NoArgsConstructor {
  // 若设置该属性，将会生成一个私有的构造函数且生成一个staticName指定的静态方法
  String staticName() default "";
  AnyAnnotation[] onConstructor() default {};
  // 设置生成构造函数的访问级别，默认是public
  AccessLevel access() default lombok.AccessLevel.PUBLIC;
  // 若设置为true，则初始化所有final的字段为0/null/false
  boolean force() default false;
}
```

### 實例
- 編譯前
```java
@NoArgsConstructor(staticName = "getInstance")
public class NoArgsConstructorDemo {
  private long id;
  private String name;
  private int age;
}
```
- 編譯後
```java
public class NoArgsConstructorDemo {
  private long id;
  private String name;
  private int age;

  private NoArgsConstructorDemo() {}

  public static NoArgsConstructorDemo getInstance() {
    return new NoArgsConstructorDemo();
  }
}
```

## 3. @AllArgsConstructor

### 定義
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AllArgsConstructor {
  // 若设置该属性，将会生成一个私有的构造函数且生成一个staticName指定的静态方法
  String staticName() default "";
  AnyAnnotation[] onConstructor() default {};
  // 设置生成构造函数的访问级别，默认是public
  AccessLevel access() default lombok.AccessLevel.PUBLIC;
}

```

### 實例
- 編譯前
```java
@AllArgsConstructor
public class AllArgsConstructorDemo {
  private long id;
  private String name;
  private int age;
}
```
- 編譯後
```java
public class AllArgsConstructorDemo {
  private long id;
  private String name;
  private int age;

  public AllArgsConstructorDemo(long id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
}
```

## 4. @RequiredArgsConstructor

### 定義
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface RequiredArgsConstructor {
  // 若设置该属性，将会生成一个私有的构造函数且生成一个staticName指定的静态方法
  String staticName() default "";
  AnyAnnotation[] onConstructor() default {};
  // 设置生成构造函数的访问级别，默认是public
  AccessLevel access() default lombok.AccessLevel.PUBLIC;
}
```

### 實例
- 編譯前
```java
@RequiredArgsConstructor
public class RequiredArgsConstructorDemo {
  private final long id;
  private String name;
  private int age;
}
```
- 編譯後
```java
public class RequiredArgsConstructorDemo {
  private final long id;
  private String name;
  private int age;

  public RequiredArgsConstructorDemo(long id) {
    this.id = id;
  }
}
```

## 5. @EqualsAndHashCode

### 定義
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EqualsAndHashCode {
  // 指定在生成的equals和hashCode方法中需要排除的字段列表
  String[] exclude() default {};
    
  // 显式列出用于identity的字段，一般情况下non-static,non-transient字段会被用于identity
  String[] of() default {};
    
  // 标识在执行字段计算前，是否调用父类的equals和hashCode方法
  boolean callSuper() default false;
  
  boolean doNotUseGetters() default false;
  
  AnyAnnotation[] onParam() default {};
  
  @Deprecated
  @Retention(RetentionPolicy.SOURCE)
  @Target({})
  @interface AnyAnnotation {}
  
  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.SOURCE)
  public @interface Exclude {}
  
  @Target({ElementType.FIELD, ElementType.METHOD})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Include {
    String replaces() default "";
  }
}
```

### 實例
- 編譯前
```java
@EqualsAndHashCode
public class EqualsAndHashCodeDemo {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
}
```
- 編譯後
```java
public class EqualsAndHashCodeDemo {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;

  public EqualsAndHashCodeDemo() {}

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof EqualsAndHashCodeDemo)) {
      return false;
    } else {
      EqualsAndHashCodeDemo other = (EqualsAndHashCodeDemo)o;
      if (!other.canEqual(this)) {
        return false;
      } else {
        // 已省略大量代码
      }
    }
  }

  public int hashCode() {
    int PRIME = true;
    int result = 1;
    Object $firstName = this.firstName;
    int result = result * 59 + ($firstName == null ? 43 : $firstName.hashCode());
    Object $lastName = this.lastName;
    result = result * 59 + ($lastName == null ? 43 : $lastName.hashCode());
    Object $dateOfBirth = this.dateOfBirth;
    result = result * 59 + ($dateOfBirth == null ? 43 : $dateOfBirth.hashCode());
    return result;
  }
}
```

## 6. @ToString

### 定義
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ToString {
  // 打印输出时是否包含字段的名称
  boolean includeFieldNames() default true;
    
  // 列出打印输出时，需要排除的字段列表
  String[] exclude() default {};
    
  // 显式的列出需要打印输出的字段列表
  String[] of() default {};
    
  // 打印输出的结果中是否包含父类的toString方法的返回结果
  boolean callSuper() default false;
  
  boolean doNotUseGetters() default false;
  
  boolean onlyExplicitlyIncluded() default false;
  
  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.SOURCE)
  public @interface Exclude {}
  
  @Target({ElementType.FIELD, ElementType.METHOD})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Include {
    int rank() default 0;
    String name() default "";
  }
}
```

### 實例
- 編譯前
```java
@ToString(exclude = {"dateOfBirth"})
public class ToStringDemo {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
}
```
- 編譯後
```java
public class ToStringDemo {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;

  public ToStringDemo() {}

  public String toString() {
    return "ToStringDemo(firstName=" + this.firstName + ", lastName=" + this.lastName + ")";
  }
}
```

## 7. @Data

### 等價定義
```java
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
```
### 定義
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Data {
  String staticConstructor() default "";
}
```

### 實例
- 編譯前
```java
@Data
public class DataDemo {
  private Long id;
  private String summary;
  private String description;
}
```
- 編譯後
```java
public class DataDemo {
  private Long id;
  private String summary;
  private String description;

  public DataDemo() {}

  // from @Getter & @Setter
  // 省略summary和description成员属性的setter和getter方法
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  // from @EqualsAndHashCode
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof DataDemo)) {
      return false;
    } else {
      DataDemo other = (DataDemo)o;
      if (!other.canEqual(this)) {
        return false;
      } else {
        // 已省略大量代码
      }
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof DataDemo;
  }

  // from @EqualsAndHashCode
  public int hashCode() {
    int PRIME = true;
    int result = 1;
    Object $id = this.getId();
    int result = result * 59 + ($id == null ? 43 : $id.hashCode());
    Object $summary = this.getSummary();
    result = result * 59 + ($summary == null ? 43 : $summary.hashCode());
    Object $description = this.getDescription();
    result = result * 59 + ($description == null ? 43 : $description.hashCode());
    return result;
  }

  // from @ToString
  public String toString() {
    return "DataDemo(id=" + this.getId() + ", summary=" + this.getSummary() + ", description=" + this.getDescription() + ")";
  }
}
```

## 8. @Log

### 定義
```java

```

### 實例
- 編譯前
```java

```
- 編譯後
```java

```

## 9. @Synchronized

### 定義
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Synchronized {
  // 指定锁定的字段名称
  String value() default "";
}
```

### 實例
- 編譯前
```java
public class SynchronizedDemo {
  private final Object readLock = new Object();

  @Synchronized
  public static void hello() {
    System.out.println("world");
  }

  @Synchronized
  public int answerToLife() {
    return 42;
  }

  @Synchronized("readLock")
  public void foo() {
    System.out.println("bar");
  }
}
```
- 編譯後
```java
public class SynchronizedDemo {
  private static final Object $LOCK = new Object[0];
  private final Object $lock = new Object[0];
  private final Object readLock = new Object();

  public SynchronizedDemo() {}

  public static void hello() {
    synchronized($LOCK) {
      System.out.println("world");
    }
  }

  public int answerToLife() {
    synchronized(this.$lock) {
      return 42;
    }
  }

  public void foo() {
    synchronized(this.readLock) {
      System.out.println("bar");
    }
  }
}
```

## 10. @Builder

### 定義
```java
@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(SOURCE)
public @interface Builder {
  @Target(FIELD)
  @Retention(SOURCE)
  public @interface Default {}

  // 创建新的builder实例的方法名称
  String builderMethodName() default "builder";
  // 创建Builder注解类对应实例的方法名称
  String buildMethodName() default "build";
  // builder类的名称
  String builderClassName() default "";
  
  boolean toBuilder() default false;
  
  AccessLevel access() default lombok.AccessLevel.PUBLIC;
  
  @Target({FIELD, PARAMETER})
  @Retention(SOURCE)
  public @interface ObtainVia {
    String field() default "";
    String method() default "";
    boolean isStatic() default false;
  }
}
```

### 實例
- 編譯前
```java
@Builder
public class BuilderDemo {
  private final String firstname;
  private final String lastname;
  private final String email;
}
```
- 編譯後
```java
public class BuilderDemo {
  private final String firstname;
  private final String lastname;
  private final String email;

  BuilderDemo(String firstname, String lastname, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
  }

  public static BuilderDemo.BuilderDemoBuilder builder() {
    return new BuilderDemo.BuilderDemoBuilder();
  }

  public static class BuilderDemoBuilder {
    private String firstname;
    private String lastname;
    private String email;

    BuilderDemoBuilder() {}

    public BuilderDemo.BuilderDemoBuilder firstname(String firstname) {
      this.firstname = firstname;
      return this;
    }

    public BuilderDemo.BuilderDemoBuilder lastname(String lastname) {
      this.lastname = lastname;
      return this;
    }

    public BuilderDemo.BuilderDemoBuilder email(String email) {
      this.email = email;
      return this;
    }

    public BuilderDemo build() {
      return new BuilderDemo(this.firstname, this.lastname, this.email);
    }

    public String toString() {
      return "BuilderDemo.BuilderDemoBuilder(firstname=" + this.firstname + ", lastname=" + this.lastname + ", email=" + this.email + ")";
    }
  }
}
```

## 11. @SneakyThrows

### 定義
```java
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
public @interface SneakyThrows {
  // 设置你希望向上抛的异常类
  Class<? extends Throwable>[] value() default java.lang.Throwable.class;
}
```

### 實例
- 編譯前
```java
public class SneakyThrowsDemo {
  @SneakyThrows
  @Override
  protected Object clone() {
      return super.clone();
  }
}
```
- 編譯後
```java
public class SneakyThrowsDemo {
  public SneakyThrowsDemo() {}

  protected Object clone() {
    try {
      return super.clone();
    } catch (Throwable var2) {
      throw var2;
    }
  }
}
```

## 12. @NonNull

### 定義
```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface NonNull {}
```

### 實例
- 編譯前
```java
public class NonNullDemo {
  @Getter
  @Setter
  @NonNull
  private String name;
}
```
- 編譯後
```java
public class NonNullDemo {
  @NonNull
  private String name;

  public NonNullDemo() {}

  @NonNull
  public String getName() {
    return this.name;
  }

  public void setName(@NonNull String name) {
    if (name == null) {
      throw new NullPointerException("name is marked non-null but is null");
    } else {
      this.name = name;
    }
  }
}
```

## 13. @Clear

### 定義
```java
@Target(ElementType.LOCAL_VARIABLE)
@Retention(RetentionPolicy.SOURCE)
public @interface Cleanup {
  // 设置用于执行资源清理/回收的方法名称，对应方法不能包含任何参数，默认名称为close。
  String value() default "close";
}
```

### 實例
- 編譯前
```java
public class CleanupDemo {
  public static void main(String[] args) throws IOException {
    @Cleanup InputStream in = new FileInputStream(args[0]);
    @Cleanup OutputStream out = new FileOutputStream(args[1]);
    byte[] b = new byte[10000];
    while (true) {
      int r = in.read(b);
      if (r == -1) break;
      out.write(b, 0, r);
    }
  }
}
```
- 編譯後
```java
public class CleanupDemo {
  public CleanupDemo() {}

  public static void main(String[] args) throws IOException {
    FileInputStream in = new FileInputStream(args[0]);

    try {
      FileOutputStream out = new FileOutputStream(args[1]);
      try {
        byte[] b = new byte[10000];
        while(true) {
          int r = in.read(b);
          if (r == -1)
              return;
          out.write(b, 0, r);
        }
      } finally {
        if (Collections.singletonList(out).get(0) != null) {
          out.close();
        }
      }
    } finally {
      if (Collections.singletonList(in).get(0) != null) {
        in.close();
      }
    }
  }
}
```

## 14. @With

### 定義
```java
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface With {
  AccessLevel value() default AccessLevel.PUBLIC;

  With.AnyAnnotation[] onMethod() default {};

  With.AnyAnnotation[] onParam() default {};

  @Deprecated
  @Retention(RetentionPolicy.SOURCE)
  @Target({})
  public @interface AnyAnnotation {}
}
```

### 實例
- 編譯前
```java
public class WithDemo {
  @With(AccessLevel.PROTECTED)
  @NonNull
  private final String name;
  @With
  private final int age;

  public WithDemo(String name, int age) {
    if (name == null) throw new NullPointerException();
    this.name = name;
    this.age = age;
  }
}
```
- 編譯後
```java
public class WithDemo {
  @NonNull
  private final String name;
  private final int age;

  public WithDemo(String name, int age) {
    if (name == null) {
      throw new NullPointerException();
    } else {
      this.name = name;
      this.age = age;
    }
  }

  protected WithDemo withName(@NonNull String name) {
    if (name == null) {
      throw new NullPointerException("name is marked non-null but is null");
    } else {
      return this.name == name ? this : new WithDemo(name, this.age);
    }
  }

  public WithDemo withAge(int age) {
    return this.age == age ? this : new WithDemo(this.name, age);
  }
}
```

## 15. val
- 特別用法，自動設定為 `final` 與 `類型推斷`

### 實例
- 編譯前
```java
public class ValExample {
  public String example() {
    val example = new ArrayList<String>();
    example.add("Hello, World!");
    val foo = example.get(0);
    return foo.toLowerCase();
  }
  
  public void example2() {
    val map = new HashMap<Integer, String>();
    map.put(0, "zero");
    map.put(5, "five");
    for (val entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
}
```
- 編譯後
```java
public class ValExample {
  public String example() {
    final ArrayList<String> example = new ArrayList<String>();
    example.add("Hello, World!");
    final String foo = example.get(0);
    return foo.toLowerCase();
  }
  
  public void example2() {
    final HashMap<Integer, String> map = new HashMap<Integer, String>();
    map.put(0, "zero");
    map.put(5, "five");
    for (final Map.Entry<Integer, String> entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
}
```
