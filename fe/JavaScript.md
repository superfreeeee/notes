# JavaScript基礎(ES6特性)

## 1. Symbol
- 繼 `undefined`, `null`, `boolean`, `string`, `number`, `object` 六種基本類型後第七種基本類型 `Symbol`
- 是一種獨一無二的字符串

### 定義
```js
var s1 = Symbol('foo');
var s2 = Symbol('bar');

s1 // Symbol(foo)
s2 // Symbol(bar)

s1.toString() // "Symbol(foo)"
s2.toString() // "Symbol(bar)"
```

## 2. Class 類聲明方法

### 定義
```js
// ES5
function Point(x, y) {
  this.x = x
  this.y = y
}

Point.prototype.toString = function() {
  return '(' + this.x + ', ' + this.y + ')'
}

var point = new Point(0, 0)

// ES6
class Point {
  constructor(x, y) {
    this.x = x
    this.y = y
  }

  toString() {
    return '(' + this.x + ', ' + this.y + ')'
  }
}
let P = class {
  // ...
}

typeof Point // "function"
Point === Point.prototype.constructor // true

// 立即調用
let person = new class {
  constructor(name, id) {
    this.name = name
    this.id = id
  }
}
```
```js
class Point {
  constructor() {
    // ...
  }

  toString() {
    // ...
  }

  valueOf() {
    // ...
  }
}
// 等價於 =>
Point.prototype = {
  toString(){},
  valueOf(){}
}
```

### 內部定義方法不可枚舉
```js
// ES6
class Point {
  constructor(x, y) {
    // ...
  }

  toString() {
    // ...
  }
}

Object.keys(Point.prototype)
// []
Object.getOwnPropertyNames(Point.prototype)
// ["constructor","toString"]

// ES5
var Point = function (x, y) {
  // ...
};

Point.prototype.toString = function() {
  // ...
};

Object.keys(Point.prototype)
// ["toString"]
Object.getOwnPropertyNames(Point.prototype)
// ["constructor","toString"]
```

### 計算是函數名
```js
let method = "getArea"
class Square {
  [method]() {
    console.log(0)
  }
}
new Square().getArea()
```

### 可返回非自身類(默認返回this)
```js
class Foo {
  constructor() {
    return Object.create(null)
  }
}
new Foo() instanceof Foo  // false
```

### 除非顯式定義this，否則皆定義在原型對象上
```js
class Point {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

  toString() {
    return '(' + this.x + ', ' + this.y + ')';
  }
}
var point = new Point(2, 3);
point.toString() // (2, 3)
point.hasOwnProperty('x') // true
point.hasOwnProperty('y') // true
point.hasOwnProperty('toString') // false
point.__proto__.hasOwnProperty('toString') // true
```

### 所有類實例共享一個原型對象
```js
var p1 = new Point(2,3);
var p2 = new Point(3,2);

p1.__proto__ === p2.__proto__  //true

// 同時代表可從一個實例的 __proto__ 擴展一個類
var p1 = new Point(2,3);
var p2 = new Point(3,2);

p1.__proto__.printName = function () { return 'Oops' };

p1.printName() // "Oops"
p2.printName() // "Oops"

var p3 = new Point(4,2);
p3.printName() // "Oops"
```

## 3. Set

### 定義
```js
let set = new Set()
[1,2,3,4,5,4,3,2,1].map(x => set.add(x)) // set.add() 添加元素
console.log(set)  // Set {1,2,3,4,5}

// 以數組做參數
let set = new Set([1,2,3,4,5,4,3,2,1])
console.log(set)  // Set {1,2,3,4,5}
```

### 常用方法
```js
// 基本操作
add(value)  // 添加，返回Set結構本身
delete(value)  // 刪除，成功返回true
has(value)  // 返回布林值，代表是否存在此值
clear()  // 清除所有成員

// Set 轉數組
let array = Array.from(set)
// 
```
```js
// 去除數組重複成員
array = [...new Set(array)]
Array.from(new Set(array))

// 遍歷操作
keys()  // 返回鍵名遍歷器
values()  // 返回鍵值遍歷器
entries() // 返回鍵值對遍歷器
forEach() // 使用回調函數遍歷成員

```

## 4. Map


## 5. `一般函數`與`箭頭函數`差異

### 定義
```js
// 一般函數
function func1(){}
const func2 = function(){}

// 箭頭函數
const func3 = () => {}
```

### this指向
```js
// 一般函數
const func = function() {
  console.log(this)
}
const obj = {f: func}
func()  // Window
obj.f() // obj

// 箭頭函數
const func = () => {
  console.log(this)
}
const obj = {f: func}
func()  // Window
obj.f() // Window
```

### 構造函數
```js
// 一般函數
const Person = function(name, age) {
  this.name = name
  this.age = age
}
const person = new Person('john', 25)
console.log(person) // Person {name: "john", age: 25}

// 箭頭函數
const Person = (name, age) => {
  this.name = name
  this.age = age
}
const person = new Person('john', 25)
console.log(person) // Uncaught TypeError: Person is not a constructor
```

### 變量提升
```js
// 一般函數
f() // 'function f()'
function f() {
  console.log('function f()')
}

// 箭頭函數
f() // Uncaught ReferenceError: Cannot access 'f' before initialization
const f = () => {
  console.log('function f()')
}

```

## 6. Event Loop 事件循環機制
- JavaScript為非阻塞（單線程）腳本語言，`Web Worker`作為多線程替代技術

## 7. Promise 對象
```js
let ajax = function(...args) {
  // ...
  return new Promise(function(resolve, reject){
    // ...
    let response
    resolve(response)

    let error
    reject(error)
  })
}

const a, b, c
ajax(a, b, c)
  // when resolve is triggered
  .then(response => console.log(response))
  // when reject is triggered
  .catch(error => console.log(error))
```

# Airbnb JavaScript 編程風格規範

## 1. `let` 取代 `var`
- let 沒有副作用，沒有變量提升，有作用域的概念，可完全取代var
```js
'use strict'
if(true) {
  let x = 'hello'
}
for(let i=0 ; i<10 ; i++)
  console.log(i)
```
```js
'use strict';

if(true) {
  console.log(x); // ReferenceError
  let x = 'hello';
}
```

## 2. 全局常量 & 線程安全
- const 相較於 let 更好(不變性)
- 全局只聲明const
- const 有利於分佈式運算和編譯優化
```js
// bad
var a = 1, b = 2, c = 3;

// good
const a = 1;
const b = 2;
const c = 3;

// best
const [a, b, c] = [1, 2, 3];
```

## 3. 靜態字符串單引號，動態反引號，不使用雙引號
```js
// bad
const a = "foobar";
const b = 'foo' + a + 'bar';

// acceptable
const c = `foobar`;

// good
const a = 'foobar';
const b = `foo${a}bar`;
const c = 'foobar';
```

## 4. 解構賦值優先
- 數組
```js
const arr = [1, 2, 3, 4];

// bad
const first = arr[0];
const second = arr[1];

// good
const [first, second] = arr;
```
- 對象
```js
// bad
function getFullName(user) {
  const firstName = user.firstName;
  const lastName = user.lastName;
}

// good
function getFullName(obj) {
  const { firstName, lastName } = obj;
}

// best
function getFullName({ firstName, lastName }) {
}
```
- 函數返回值：使用對象解構
```js
// bad
function processInput(input) {
  return [left, right, top, bottom];
}

// good
function processInput(input) {
  return { left, right, top, bottom };
}

const { left, right } = processInput(input);
```

## 5. 單行對象不以逗號結尾，多行對象以逗號結尾
```js
// bad
const a = { k1: v1, k2: v2, };
const b = {
  k1: v1,
  k2: v2
};

// good
const a = { k1: v1, k2: v2 };
const b = {
  k1: v1,
  k2: v2,
};
```

## 6. 對象靜態化
```js
// bad
const a = {};
a.x = 3;

// if reshape unavoidable
const a = {};
Object.assign(a, { x: 3 });

// good
const a = { x: null };
a.x = 3;
```

## 7. 使用擴展運算符(...)拷貝數組
```js
// bad
const len = items.length;
const itemsCopy = [];
let i;

for (i = 0; i < len; i++) {
  itemsCopy[i] = items[i];
}

// good
const itemsCopy = [...items];
```
```js
// 類數組對象轉數組
const foo = document.querySelectorAll('.foo');
const nodes = Array.from(foo);
```

## 8. 



