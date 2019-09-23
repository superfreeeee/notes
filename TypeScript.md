# TypeScript
TypeScipt副檔名：`.ts` , `.d.ts`

## 1. Basic Types
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

## 2. 解構賦值
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

## 3. 接口
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

### 3.1 函數接口
```ts
interface SearchFunc {
  (source: string, subString: string): boolean;
}
```

### 3.2 可索引接口
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

### 3.3 構造函數接口
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

### 3.4 繼承接口
```ts
interface Shape {
  color: string;
}

interface Square extends Shape {
  sideLength: number;
}
```

### 3.5 合成接口
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

### 3.6 混合類型（同時是對象又是方法）
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

## 4. 類
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

### 4.1 繼承
```ts
class Animal {
  move(distanceInMeters: number = 0) {
    console.log(`Animal moved ${distanceInMeters}m.`);
  }
}

class Dog extends Animal {
  bark() {
    console.log('Woof! Woof!');
  }
}
```
- 派生類（子類）構造函數必須先調用 `super()`
```ts
class Animal {
  name: string;
  constructor(theName: string) { this.name = theName; }
  move(distanceInMeters: number = 0) {
    console.log(`${this.name} moved ${distanceInMeters}m.`);
  }
}

class Snake extends Animal {
  constructor(name: string) { super(name); }
  move(distanceInMeters = 5) {
    console.log("Slithering...");
    super.move(distanceInMeters);
  }
}

class Horse extends Animal {
  constructor(name: string) { super(name); }
  move(distanceInMeters = 45) {
    console.log("Galloping...");
    super.move(distanceInMeters);
  }
}
```

### 4.2 類型兼容
- 成員變量默認 `public`, 可選 `private`, `protected`
- `readonly` 相當於 java的 `final`
- `private` 成員變量必須一樣且來自同一定義稱為"兼容"
```ts
class Animal {
  private name: string;
  constructor(theName: string) { this.name = theName; }
}

class Rhino extends Animal {
  constructor() { super("Rhino"); }
}

class Employee {
  private name: string;
  constructor(theName: string) { this.name = theName; }
}

let animal = new Animal("Goat");
let rhino = new Rhino();
let employee = new Employee("Bob");

animal = rhino;
animal = employee; // 错误: Animal 与 Employee 不兼容.
```

### 4.3 存取器 getter, setter
```ts
let passcode = "secret passcode";

class Employee {
  private _fullName: string;

  get fullName(): string {
    return this._fullName;
  }

  set fullName(newName: string) {
    if (passcode && passcode == "secret passcode") {
      this._fullName = newName;
    }else {
      console.log("Error: Unauthorized update of employee!");
    }
  }
}
```
### 4.4 靜態屬性 static
```ts
class Grid {
  static origin = {x: 0, y: 0};
  calculateDistanceFromOrigin(point: {x: number; y: number;}) {
    let xDist = (point.x - Grid.origin.x);
    let yDist = (point.y - Grid.origin.y);
    return Math.sqrt(xDist * xDist + yDist * yDist) / this.scale;
  }
  constructor (public scale: number) { }
}
```
### 4.5 抽象類 abstract
```ts
abstract class Department {

  constructor(public name: string) {
  }

  printName(): void {
    console.log('Department name: ' + this.name);
  }

  abstract printMeeting(): void; // 必须在派生类中实现
}

class AccountingDepartment extends Department {

  constructor() {
    super('Accounting and Auditing'); // 在派生类的构造函数中必须调用 super()
  }

  printMeeting(): void {
    console.log('The Accounting Department meets each Monday at 10am.');
  }

  generateReports(): void {
    console.log('Generating accounting reports...');
  }
}
```
### 4.6 構造函數細節
```ts
class Greeter {
    static standardGreeting = "Hello, there";
    greeting: string;
    greet() {
        if (this.greeting) {
            return "Hello, " + this.greeting;
        }
        else {
            return Greeter.standardGreeting;
        }
    }
}

let greeter1: Greeter;  // 聲明變量類型
greeter1 = new Greeter();
console.log(greeter1.greet());

let greeterMaker: typeof Greeter = Greeter;   // 類原型作為變量類型
greeterMaker.standardGreeting = "Hey there!";

let greeter2: Greeter = new greeterMaker();
console.log(greeter2.greet());
```

### 4.7 類當作接口
```ts
class Point {
    x: number;
    y: number;
}

interface Point3d extends Point {
    z: number;
}
```

## 5. 函數
```ts
// 定義類型, 搭配類型推斷
function add(x: number, y: number): number {
    return x + y;
}

let myAdd = function(x: number, y: number): number { return x + y; };

// 完整類型
let myAdd: (x: number, y: number) => number =
  function(x: number, y: number): number { return x + y; };

// 修改聲明變量名增加可讀性, 只關注類型匹配
let myAdd: (baseValue: number, increment: number) => number =
  function(x: number, y: number): number { return x + y; };
```

### 5.1 類型推斷
```ts
// myAdd has the full function type
let myAdd = function(x: number, y: number): number { return x + y; };

// The parameters `x` and `y` have the type number
let myAdd: (baseValue: number, increment: number) => number =
  function(x, y) { return x + y; };
```

### 5.2 可選參數
```ts
// param?: [type]
// 可選參數必須在後面
function buildName(firstName: string, lastName?: string) {
  if (lastName)
    return firstName + " " + lastName;
  else
    return firstName;
}
```

### 5.3 默認參數
```ts
// param = [default value]
// 默認參數不必在後面
function buildName(firstName: string, lastName = "Smith") {
  return firstName + " " + lastName;
}
```

### 5.4 剩餘參數
```ts
// ...param, 置於末尾
// 在 JavaScripte 使用 arguments 捕獲參數
function buildName(firstName: string, ...restOfName: string[]) {
  return firstName + " " + restOfName.join(" ");
}

// 函數類型定義
function buildName(firstName: string, ...restOfName: string[]) {
  return firstName + " " + restOfName.join(" ");
}

let buildNameFun: (fname: string, ...rest: string[]) => string = buildName;
```

### 5.5 this 綁定
```ts
// 定義一： 報錯，頂層方法 this 綁定在window上
let deck = {
  suits: ["hearts", "spades", "clubs", "diamonds"],
  cards: Array(52),
  createCardPicker: function() {
    return function() {
      let pickedCard = Math.floor(Math.random() * 52);
      let pickedSuit = Math.floor(pickedCard / 13);

      return {suit: this.suits[pickedSuit], card: pickedCard % 13};
    }
  }
}

// 定義二： 使用箭頭函數在返回函數時綁定到 deck 對象上
let deck = {
  suits: ["hearts", "spades", "clubs", "diamonds"],
  cards: Array(52),
  createCardPicker: function() {
    // NOTE: the line below is now an arrow function, allowing us to capture 'this' right here
    return () => {
      let pickedCard = Math.floor(Math.random() * 52);
      let pickedSuit = Math.floor(pickedCard / 13);

      return {suit: this.suits[pickedSuit], card: pickedCard % 13};
    }
  }
}

// 調用
let cardPicker = deck.createCardPicker();
let pickedCard = cardPicker();

alert("card: " + pickedCard.card + " of " + pickedCard.suit);
```

### 5.6 this 參數
```ts
interface Card {
  suit: string;
  card: number;
}
interface Deck {
  suits: string[];
  cards: number[];
  // 顯示定義 this 假參數（不必傳值）以聲明類型，在參數列表首位
  createCardPicker(this: Deck): () => Card;
}
let deck: Deck = {
  suits: ["hearts", "spades", "clubs", "diamonds"],
  cards: Array(52),
  // NOTE: The function now explicitly specifies that its callee must be of type Deck
  createCardPicker: function(this: Deck) {
    return () => {
      let pickedCard = Math.floor(Math.random() * 52);
      let pickedSuit = Math.floor(pickedCard / 13);

      return {suit: this.suits[pickedSuit], card: pickedCard % 13};
    }
  }
}
```

### 5.7 this 作回調參數
```ts
// 接口聲明
interface UIElement {
  addClickListener(onclick: (this: void, e: Event) => void): void;
}

// 聲明一：報錯，this需綁定為void
class Handler {
    info: string;
    onClickBad(this: Handler, e: Event) {
        // oops, used this here. using this callback would crash at runtime
        this.info = e.message;
    }
}

// 聲明二：使用 this 假參數
class Handler {
    info: string;
    onClickGood(this: void, e: Event) {
        // can't use this here because it's of type void!
        console.log('clicked!');
    }
}

// 聲明三：使用箭頭函數綁定 this
class Handler {
    info: string;
    onClickGood = (e: Event) => { this.info = e.message }
}

// 調用
let h = new Handler();
uiElement.addClickListener(h.onClickGood);
```

### 5.8 重載 Overload
```ts
function pickCard(x: {suit: string; card: number; }[]): number;
function pickCard(x: number): {suit: string; card: number; };
function pickCard(x): any {
  // Check to see if we're working with an object/array
  // if so, they gave us the deck and we'll pick the card
  if (typeof x == "object") {
    let pickedCard = Math.floor(Math.random() * x.length);
    return pickedCard;
  }
  // Otherwise just let them pick the card
  else if (typeof x == "number") {
    let pickedSuit = Math.floor(x / 13);
    return { suit: suits[pickedSuit], card: x % 13 };
  }
}
```

## 6. 泛型
```ts
// 不同於 any, 保留了類型信息 T
function identity<T>(arg: T): T {
  return arg;
}

// 調用一：明確聲明參數類型
let output = identity<string>("myString");  // type of output will be 'string'

// 調用二：使用類型推論
let output = identity("myString");  // type of output will be 'string'
```

### 6.1 泛型變量
```ts
function loggingIdentity<T>(arg: Array<T>): Array<T> {
  console.log(arg.length);  // Array has a .length, so no more error
  return arg;
}
```

### 6.2 泛型函數
```ts
// 可使用不同簽名，只要數量與調用方法一致
function identity<T>(arg: T): T {
  return arg;
}

let myIdentity: <U>(arg: U) => U = identity;

// 使用帶有調用簽名的字面量對象
function identity<T>(arg: T): T {
  return arg;
}

let myIdentity: {<T>(arg: T): T} = identity;
```

### 6.3 泛型接口
```ts
interface GenericIdentityFn {
  <T>(arg: T): T;
}

function identity<T>(arg: T): T {
  return arg;
}

let myIdentity: GenericIdentityFn = identity;
```


