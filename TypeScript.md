# TypeScript
TypeScipt副檔名：`.ts` , `.d.ts`

## Basic Types
```ts
// 布爾
boolean := true | false 
// 數字
number
// 字符串
string
// 數組
Array<T> or T[]
// 元組，指定類型的數組
Tuple := [type1, type2, ...]
// 枚舉
enum := enum Enum {Type1, Type2}
// 任意類型
any
// js原生
Object, Function
// 空
void, null, undefined
// never，永遠到不了終點
never

// 類型聲明
type newType = { a: number, b?: string }
```

### 類型斷言（強類型轉換）
```ts
let aString: any = "this is a sentence";
// 尖括號
let len: number = (<string> aString).length;
// as語法，JSX限定使用as
let len2: number = (aString as string).length;
```

## 解構賦值
使用`const` `let`進襲變量賦值
```ts
// 數組解構
const [num1, num2]: [number, number] = [1, 2];

const o = {id: 1, name: "john"};
// 對象解構
const {id, name}: {id: number, name: string}
```

### 重定名、默認值
```ts
// 重定名
const { id: newId, name: newName } = { id: 1, name: "123" }

// 類型推斷、默認值
function func({a, b=0} = {a: ""}): void { }   // b=0用作類行推論與默認值，a:""為默認對象
f({ a: "yes"})  // success,  { a: "yes", b: 0 }
f()   // success, { a: "" }
f({})  // error
```

## 接口
```ts
// 基本定義
interface Student {
  id: number;
  readonly name: string;  // 只讀屬性
  grade?: number;    // 可選屬性
}

// 只讀數組
let a: number[] = [1, 2, 3, 4, 5];
let ra: ReadonlyArray<number> = a;
a = ra;  // error
a = ra as number[];   // 使用類型斷言復原

// 額外屬性
interface Config {
  height: number;
  width: number;
  color?: string;
  [propName: string]: any;
}
```

### 函數接口
```ts
interface SearchFunc {
  (source: string, subString: string): boolean;
}
```

### 可索引接口
```ts
interface StringArray {
  [index: number]: string;
}
let myArray: StringArray = ["John", "Bob"];
let myStr: string = myArray[0];

// 只讀數組
interface ReadonlyArray {
  readonly [index: number]: string;
}
```

### 構造函數接口
```ts
interface ClockConstructor {
  new (hour: number, minute: number): ClockInterface;
}

interface ClockInterface {
  tick();
}

function createClock(ctor: ClockConstructor, hour: number, minute: number): ClockInterface {
  return new ctor(hour, minute);
}

class DigitalClock implements ClockInterface {
  constructor(h: number, minute: number) { }
  tick() {
    console.log("beep beep");
  }
}

class AnalogClock implements ClockInterface {
  constructor(h: number, minute: number){ }
  tick() {
    console.log("tick tock");
  }
}

let digital = createClock(DigitalClock, 12, 17);
let analog = createClock(AnalogClock, 7, 32);
```

### 繼承接口
```ts
interface Shape {
  color: string;
}

interface Square extends Shape {
  sideLength: number;
}
```

### 合成接口
```ts
interface Shape {
  color: string;
}

interface PenStroke {
  penWidth: number;
}

interface Square extends Shape, PenStroke {
  sideLength: number;
}
```

### 混合類型（同時是對象又是方法）
```ts
interface Clock {
  (start: number): string;
  interval: number;
  reset(): void;
}

interface getCounter(): Clock {
  let counter = <Clock>function(start: number) { };
  counter.interval = 123;
  counter.reset = function() { }
  return counter;
}

let c = getCounter();
c(10);
c.reset();
c.interval = 5;
```

## 類
```ts
class Greeting {
  greeting: string;
  constructor(message: string) {
    this.greeting = message;
  }
  greet() {
    return 'Hello, ' + this.greeting;
  }
}
```