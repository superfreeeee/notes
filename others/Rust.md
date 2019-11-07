# Rust

## 編譯和運行
```sh
$ rustc [filename].rs && ./[filename]
```

## 變量與常量
- 可出現同名變量，採用後者覆蓋前者策略(類型可改變)
- 不可出現同名常量

### 變量
- 默認不可變(只讀)，加 `mut` 變為可用
- 不允許類型自動轉換
- `_` 下劃線提升數據可讀性，不影響實際值
```rust
let variable_name = value;            // 不指定变量类型，自動類型推斷
let variable_name:dataType = value;   // 指定变量类型
```
```rust
let a = 1;
a = 0;  // Error

let mut b = 1;
b = 2;  // Success
```

### 常量
- 必須指定常量類型
- 慣用大寫字母，使用`const`關鍵字
```rust
const VARIABLE_NAME:dataType = value;
```
```rust
const USER_LIMIT:i32 = 100;    // 定义了一个 i32 类型的常量
const PI:f32 = 3.14;           // 定义了一个 float 类型的常量
```

### 基本類型
```rust
let s = "123456";  // 字符串類型
let i = 123456;  // 整數類型
let f = 123.456;  // 浮點數類型
let b = true;  // 布林類型
let c = 'c';  // 字符類型
```

### 整數類型
大小 | 有符号 | 无符号
-|-|-
8 bit | i8 | u8
16 bit | i16 | u16
32 bit | i32 | u32
64 bit | i64 | u64
128 bit | i128 | u128
Arch | isize | usize
```rust
let result = 10;    // i32 默认
let age:u32 = 20;
let sum:i32 = 5-15;
let mark:isize = 10;
let count:usize = 30;
```

### 浮點數類型
- `f32`單精度浮點型, `f64(默認)`雙精度浮點型兩種類型
```rust
let result = 10.00;        // 默认是 f64 
let interest:f32 = 8.35;
let cost:f64 = 15000.600;  // 双精度浮点型
```

### 布爾類型
```rust
let isfun:bool = true;
```

### 字符類型
- 默認使用unicode編碼
```rust
let special_character = '@'; //default
let alphabet:char = 'A';
let emoji:char = 'c'; // 笑脸的那个图
```

## 條件語句(if, else-if, else)

### 形式
```rust
// if
if condition
{
  // statements;
}

// if-else
if condition
{
  // statements;
} else {
  // statements;  
}

// else-if
if condition1
{
  // statements;  
} else if condition2
{
  // statements;  
}
```
### 示例
```rust
fn main() {

  let a=1;  
  if a==1  
  {  
    println!("a is equal to 1");  
  }
}

fn main() {  
  let a=3;  
  let b=4;  
  if a>b  
  {  
    println!("a is greater than b");  
  }  
  else  
  {  
    println!("a is smaller than b");   
  }  
}
```

## 條件賦值 If in a let

### 形式
```rust
let var = if condition {
  // statement;
} else {
  // statement;
}
```

### 示例
```rust
let e = if true {1} else {2};
```

## 循環語句

### Loop 無限循環
- 形式
```rust
loop{
  // run infinitely
}
```
- 示例
```rust
let mut count = 0;
loop {
  println!("count={}", count);
  count = count + 1;
  if count>10
  {
    break;
  }
}
```

### for 循環
- 形式
```rust
for var in expression {
  // statement
}
```
- 示例
```rust
for i in 1..11 {
  println!("i={}", i);
}

for i in 1..11 {
  let result = 2*i;
  println!("2*{}={}",i,result);
}
```







