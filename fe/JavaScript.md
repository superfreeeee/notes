# JavaScript基礎(ES6特性)

## Class 類聲明方法
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

## `一般函數`與`箭頭函數`差異

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

## Event Loop 事件循環機制
- JavaScript為非阻塞（單線程）腳本語言，`Web Worker`作為多線程替代技術

## Promise 對象
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