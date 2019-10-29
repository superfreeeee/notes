# C 語言基礎

## 基本類型
```c
// number & char & void & pointer(type*) & struct & function
[description]? [type] [name] = [value]?;

// 聲明  定義：聲明（有這東西），定義（這東西是什麼）
// 聲明
int num;
[return_type] [func_name]([paramaters]);
// 定義
int num = 0;
num = 3;  // 賦值
[return_type] [func_name]([paramaters]) {
  // statements
}

// 代碼塊 scope {}
```

## 整數表示
- n位補碼：2^(n-1) - 1 ~ -2^(n-1)
- n位無符號正數：0 ~ 2^n - 1

## 字符集
- ascii
- utf-8
- unicode

## 三位運算符
```c
[left_value] = (expression) ? [valueA] : [valueB];
```

## 條件
```c
if([condition]) {
  // do while condition retrun false
}
```

## 循環
```c
for(0[initialization] ; 1[condition] ; 2[statement_each_time]) {
// 3
}
// 0 -> 1 -> 3 -> 2 -> 1 -> 3

while([condition]) {

}

do {
  
}while([condition]);
```

## 形參、實參
- 調用函數傳遞實際參數
- 函數操作形式參數

## 指針
- `&` 取地址
- `*` 取值

- 所有變量都代表計算機中某一個寄存器
- `一般變量`存`值`，`指針變量`存`地址`

## 結構體

### 定義
```c
// 使用struct
struct [tag/new_type_name] {
  // members
}[var_def] = {[members]}?;

// 使用typedef
typedef struct {
  // members
}[new_type_name]
```
```c
struct Point {
  int x;
  int y;
}p1 = {0, 0}, p2;  // 可在聲明時初始化
struct Point p3 = {10, 10}, *p4;

typedef struct {
  int id;
  char name[20];
}Person;
Person p1 = {181250003, "略略略"};

struct Node {
  int id;
  Node* next;
};
```

### 位域
```c
// IEEEFloat佔四個字節32位
struct IEEEFloat {
  int sign:1;
  int exp:8;
  int frac:23;
}

// 含空域
struct InnerNull {
  int part1:10;
  int :4;
  int part2:22;
}
```