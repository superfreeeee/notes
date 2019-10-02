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
// 函數參數
interface GenericIdentityFn {
  <T>(arg: T): T;
}

function identity<T>(arg: T): T {
  return arg;
}

let myIdentity: GenericIdentityFn = identity;

// 整個接口的參數
interface GenericIdentityFn<T> {
  (arg: T): T;
}

function identity<T>(arg: T): T {
  return arg;
}

let myIdentity: GenericIdentityFn<number> = identity;
```

### 6.4 泛型類
```ts
class GenericNumber<T> {
  zeroValue: T;
  add: (x: T, y: T) => T;
}

// 作為 number
let myGenericNumber = new GenericNumber<number>();
myGenericNumber.zeroValue = 0;
myGenericNumber.add = function(x, y) { return x + y; };

// 作為 string
let stringNumeric = new GenericNumber<string>();
stringNumeric.zeroValue = "";
stringNumeric.add = function(x, y) { return x + y; };

console.log(stringNumeric.add(stringNumeric.zeroValue, "test"));
```

### 6.5 泛型約束
```ts
interface Lengthwise {
  length: number;
}

function loggingIdentity<T extends Lengthwise>(arg: T): T {
  console.log(arg.length);  // Now we know it has a .length property, so no more error
  return arg;
}

loggingIdentity(3);  // Error, number doesn't have a .length property
loggingIdentity({length: 10, value: 3});  // 運用解構賦值，參數必須具有必要屬性
```

### 6.6 類型參數
```ts
function getProperty(obj: T, key: K) {
  return obj[key];
}

let x = { a: 1, b: 2, c: 3, d: 4 };

getProperty(x, "a"); // okay
getProperty(x, "m"); // error: Argument of type 'm' isn't assignable to 'a' | 'b' | 'c' | 'd'.
```

### 6.7 泛型使用類類型
```ts
// 工廠函數引用構造函數類型
function create<T>(c: {new(): T; }): T {
  return new c();
}

// 原型屬性推斷並約束構造函數與實例類型
class BeeKeeper {
  hasMask: boolean;
}

class ZooKeeper {
  nametag: string;
}

class Animal {
  numLegs: number;
}

class Bee extends Animal {
  keeper: BeeKeeper;
}

class Lion extends Animal {
  keeper: ZooKeeper;
}

function createInstance<A extends Animal>(c: new () => A): A {
  return new c();
}

createInstance(Lion).keeper.nametag;  // typechecks!
createInstance(Bee).keeper.hasMask;   // typechecks!
```

## 7. 枚舉

### 7.1 數字枚舉
```ts
// 初始化為 1，默認為 0
enum Direction {
  Up = 1,
  Down,
  Left,
  Right
}

// 訪問方法：枚舉類作為類型，訪問成員為值
enum Response {
  No = 0,
  Yes = 1,
}

function respond(recipient: string, message: Response): void {
  // ...
}

respond("Princess Caroline", Response.Yes)

// 常數初始化必須在前，計算式後不可有未初始化常量
enum E {
  A = getSomeValue(),
  B, // error! 'A' is not constant-initialized, so 'B' needs an initializer
}
```

### 7.2 字符串枚舉
```ts
enum Direction {
  Up = "UP",
  Down = "DOWN",
  Left = "LEFT",
  Right = "RIGHT",
}
```

### 7.3 計算和常量成員
```ts
enum FileAccess {
  // constant members
  None,
  Read    = 1 << 1,
  Write   = 1 << 2,
  ReadWrite  = Read | Write,  // 對前面成員的引用
  // computed member
  G = "123".length
}
```

### 7.4 枚舉成員類型
```ts
enum ShapeKind {
  Circle,
  Square,
}

interface Circle {
  kind: ShapeKind.Circle;
  radius: number;
}

interface Square {
  kind: ShapeKind.Square;
  sideLength: number;
}

let c: Circle = {
  kind: ShapeKind.Square,
  //    ~~~~~~~~~~~~~~~~ Error!
  radius: 100,
}

// 類似短路檢查效果
enum E {
  Foo,
  Bar,
}

function f(x: E) {
  if (x !== E.Foo || x !== E.Bar) {
    //             ~~~~~~~~~~~
    // Error! Operator '!==' cannot be applied to types 'E.Foo' and 'E.Bar'.
  }
}
```

### 7.5 運行時原型
```ts
// 枚舉類
enum E { X, Y, Z }

// 運行時原型：function
function f(obj: { X: number }) {
  return obj.X;
}
```

### 7.6 反向映射
```ts
enum Enum { A }
let a = Enum.A;
let nameOfA = Enum[a]; // "A"

// 對應原代碼
// P.S. 不会为字符串枚举成员生成反向映射
var Enum;
(function (Enum) {
  Enum[Enum["A"] = 0] = "A";
})(Enum || (Enum = {}));
var a = Enum.A;
var nameOfA = Enum[a]; // "A"
```

### 7.7 常量枚舉
```ts
// 常量枚举不允许包含计算成员
const enum Directions {
  Up,
  Down,
  Left,
  Right
}

let directions = [Directions.Up, Directions.Down, Directions.Left, Directions.Right]
```

### 7.8 外部枚舉 ???
```ts
declare enum Enum {
  A = 1,
  B,
  C = 2
}
```

## 8. 類型推論
```ts
let x = 3;  // then x: number

// 自動選擇最佳類型
let zoo: Animal[] = [new Rhino(), new Elephant(), new Snake()];   // (Rhino | Elephant | Snake)[]
```

### 8.1 上下文類型
- 可從`函數參數`、`賦值表達式的右側`、`類型斷言`、`對象成員`、`數組字面量`、`返回值語句`推斷類型
```ts
// 由 window.onmousedown 類型推論
window.onmousedown = function(mouseEvent) {
  console.log(mouseEvent.button);  //<- Error
};

// 明確指出
window.onmousedown = function(mouseEvent: any) {
  console.log(mouseEvent.button);  //<- Now, no error is given
};
```

## 9. 類型兼容性
- TypeScript 的類型是基於`結構子類型`（以成員來描述類型）
```ts
interface Named {
  name: string;
}

class Person {
  name: string;
}

let p: Named;
// OK, because of structural typing
p = new Person();

// y 兼容 x 或至少 y 擁有 x 相同的屬性
interface Named {
  name: string;
}

let x: Named;
// y's inferred type is { name: string; location: string; }
let y = { name: 'Alice', location: 'Seattle' };
x = y;
```

### 9.1 比較參數
```ts
// 比較參數類型：對應原函數類型，y需要第二參數類型而報錯
let x = (a: number) => 0;
let y = (b: number, s: string) => 0;

y = x; // OK
x = y; // Error

// 比較返回類型：：尋找比須屬性，x無法提供y第二參數的類型
let x = () => ({name: 'Alice'});
let y = () => ({name: 'Alice', location: 'Seattle'});

x = y; // OK
y = x; // Error, because x() lacks a location property
```

### 9.2 可選參數和剩餘參數
- 剩餘參數被當作無限多個可選參數
```ts
function invokeLater(args: any[], callback: (...args: any[]) => void) {
  /* ... Invoke callback with 'args' ... */
}

// Unsound - invokeLater "might" provide any number of arguments
invokeLater([1, 2], (x, y) => console.log(x + ', ' + y));

// Confusing (x and y are actually required) and undiscoverable
invokeLater([1, 2], (x?, y?) => console.log(x + ', ' + y));
```

### 9.3 枚舉兼容性
- 不同枚舉類不兼容
```ts
enum Status { Ready, Waiting };
enum Color { Red, Blue, Green };

let status = Status.Ready;
status = Color.Green;  // Error
```

### 9.4 類兼容性
- 只比較`實例成員`不比較靜態類型和構造函數
```ts
class Animal {
  feet: number;
  constructor(name: string, numFeet: number) { }
}

class Size {
  feet: number;
  constructor(numFeet: number) { }
}

let a: Animal;
let s: Size;

a = s;  // OK
s = a;  // OK
// 對於 private 和 protect 的成員必須來源於同一處定義
```

### 9.5 泛型兼容性
- 比較代入參數後的`實際結構`
```ts
// same
interface Empty<T> {
}
let x: Empty<number>;
let y: Empty<string>;

x = y;  // OK, because y matches structure of x

// different
interface NotEmpty<T> {
  data: T;
}
let x: NotEmpty<number>;
let y: NotEmpty<string>;

x = y;  // Error, because x and y are not compatible
```
- 未指定泛型參數視作 `any`
```ts
let identity = function<T>(x: T): T { }
let reverse = function<U>(y: U): U { }
identity = reverse;  // OK, because (x: any) => any matches (y: any) => any
```

## 10. 高級類型

### 10.1 交叉類型
- 使用在`混入`或是不適合典型對象模型的地方
```ts
function extend<T, U>(first: T, second: U): T & U {
  let result = <T & U>{};
  for (let id in first) {
    (<any>result)[id] = (<any>first)[id];
  }
  for (let id in second) {
    if (!result.hasOwnProperty(id)) {
      (<any>result)[id] = (<any>second)[id];
    }
  }
  return result;
}

class Person {
  constructor(public name: string) { }
}
interface Loggable {
  log(): void;
}
class ConsoleLogger implements Loggable {
  log() {
    // ...
  }
}
var jim = extend(new Person("Jim"), new ConsoleLogger());
var n = jim.name;
jim.log();
```

### 10.2 聯合類型
- 提供所有可能類型
```ts
function padLeft(value: string, padding: string | number) {
  // ...
}

let indentedString = padLeft("Hello world", true); // errors during compilation
```
- 只能訪問所有可能類型共同屬性
```ts
interface Bird {
  fly();
  layEggs();
}

interface Fish {
  swim();
  layEggs();
}

function getSmallPet(): Fish | Bird {
  // ...
}

let pet = getSmallPet();
pet.layEggs(); // okay
pet.swim();    // errors
```

### 10.3 類型保護
- 類型謂詞
```ts
function isFish(pet: Fish | Bird): pet is Fish {
  return (<Fish>pet).swim !== undefined;
}
```
- 變量類型縮減
```ts
// OK!
if (isFish(pet)) {
  pet.swim();
}
else {
  pet.fly();
}
```

#### 10.3.1 typeof
- 默認視 `typeof` 為類型保護
- type 必須是 `"number"`, `"string"`, `"boolean"`, `"symbol"`，否則不提供類型保護
```ts
// 應用
function isNumber(x: any): x is number {
  return typeof x === "number";
}

function isString(x: any): x is string {
  return typeof x === "string";
}

function padLeft(value: string, padding: string | number) {
  if (isNumber(padding)) {
    return Array(padding + 1).join(" ") + value;
  }
  if (isString(padding)) {
    return padding + value;
  }
  throw new Error(`Expected string or number, got '${padding}'.`);
}

// 簡化
function padLeft(value: string, padding: string | number) {
  if (typeof padding === "number") {
    return Array(padding + 1).join(" ") + value;
  }
  if (typeof padding === "string") {
    return padding + value;
  }
  throw new Error(`Expected string or number, got '${padding}'.`);
}
```

#### 10.3.2 instanceof
- 細化類型
- 右側必須是`構造函數`簽名
```ts
interface Padder {
  getPaddingString(): string
}

class SpaceRepeatingPadder implements Padder {
  constructor(private numSpaces: number) { }
  getPaddingString() {
    return Array(this.numSpaces + 1).join(" ");
  }
}

class StringPadder implements Padder {
  constructor(private value: string) { }
  getPaddingString() {
    return this.value;
  }
}

function getRandomPadder() {
  return Math.random() < 0.5 ?
    new SpaceRepeatingPadder(4) :
    new StringPadder("  ");
}

// 类型为SpaceRepeatingPadder | StringPadder
let padder: Padder = getRandomPadder();

if (padder instanceof SpaceRepeatingPadder) {
  padder; // 类型细化为'SpaceRepeatingPadder'
}
if (padder instanceof StringPadder) {
  padder; // 类型细化为'StringPadder'
}
```

### 10.4 null類型
- JS可任意賦值為`null`
- `--strictNullChecks`标记將不包含，使用聯合類型明確包含他們
- 區別對待`null`和`undefined`
```ts
let s = "foo";
s = null; // 错误, 'null'不能赋值给'string'
let sn: string | null = "bar";
sn = null; // 可以

sn = undefined; // error, 'undefined'不能赋值给'string | null'
```

#### 10.4.1 可選參數和可選屬性
- 使用`--strictNullChecks`將自動加上 `| undefined`
```ts
// 可選參數
function f(x: number, y?: number) {
  return x + (y || 0);
}
f(1, 2);
f(1);
f(1, undefined);
f(1, null); // error, 'null' is not assignable to 'number | undefined'

// 可選屬性
class C {
  a: number;
  b?: number;
}
let c = new C();
c.a = 12;
c.a = undefined; // error, 'undefined' is not assignable to 'number'
c.b = 13;
c.b = undefined; // ok
c.b = null; // error, 'null' is not assignable to 'number | undefined'
```

#### 10.4.2 類型保護以去除null
```ts
// 顯式，與原本的JS相同
function f(sn: string | null): string {
  if (sn == null) {
    return "default";
  }
  else {
    return sn;
  }
}

// 使用短路運算符
function f(sn: string | null): string {
  return sn || "default";
}

// 使用 ! 去除null
function broken(name: string | null): string {
  function postfix(epithet: string) {
    return name.charAt(0) + '.  the ' + epithet; // error, 'name' is possibly null
  }
  name = name || "Bob";
  return postfix("great");
}

function fixed(name: string | null): string {
  function postfix(epithet: string) {
    return name!.charAt(0) + '.  the ' + epithet; // ok
  }
  name = name || "Bob";
  return postfix("great");
}
```
### 10.5 類型別名
- 類似接口，可作用於`原始值`、`聯合類型`、`元組`
- vs 接口：不可擴展或繼承（不可 `extends` 和 `implements`）
```ts
// 不新建類型，僅創建一個名字來引用類型
type Name = string;
type NameResolver = () => string;
type NameOrResolver = Name | NameResolver;
function getName(n: NameOrResolver): Name {
  if (typeof n === 'string') {
    return n;
  }
  else {
    return n();
  }
}
```
```ts
// 泛型
type Container<T> = { value: T };

// 引用自己（遞歸聲明）
type Tree<T> = {
  value: T;
  left: Tree<T>;
  right: Tree<T>;
}

// 配合交叉類型
type LinkedList<T> = T & { next: LinkedList<T> };

interface Person {
    name: string;
}

var people: LinkedList<Person>;
var s = people.name;
var s = people.next.name;
var s = people.next.next.name;
var s = people.next.next.next.name;

// 不可作為右值
type Yikes = Array<Yikes>; // error
```
#### 10.5.1 字符串字面量
```ts
type Easing = "ease-in" | "ease-out" | "ease-in-out";
class UIElement {
  animate(dx: number, dy: number, easing: Easing) {
    if (easing === "ease-in") {
      // ...
    }else if (easing === "ease-out") {

    }else if (easing === "ease-in-out") {

    }else {
      // error! should not pass null or undefined.
    }
  }
}

let button = new UIElement();
button.animate(0, 0, "ease-in");
button.animate(0, 0, "uneasy"); // error: "uneasy" is not allowed here

// 區分函數重載
function createElement(tagName: "img"): HTMLImageElement;
function createElement(tagName: "input"): HTMLInputElement;
// ... more overloads ...
function createElement(tagName: string): Element {
    // ... code goes here ...
}
```

#### 10.5.2 數字字面量
- 可用於小範圍調試
```ts
function rollDie(): 1 | 2 | 3 | 4 | 5 | 6 {
  // ...
}

function foo(x: number) {
  if (x !== 1 || x !== 2) {
    //         ~~~~~~~
    // Operator '!==' cannot be applied to types '1' and '2'.
  }
}
```

### 10.6 可辨識聯合（標籤聯合、代數數據聯合）
- 結合`单例类型`, `联合类型`, `类型保护`, `类型别名`的高級應用
```ts
// kind 作為“可辨識特徵”or“標籤”
interface Square {  // 單例類型
  kind: "square";
  size: number;
}
interface Rectangle {
  kind: "rectangle";
  width: number;
  height: number;
}
interface Circle {
  kind: "circle";
  radius: number;
}

type Shape = Square | Rectangle | Circle; // 聯合類型、類型別名

function area(s: Shape) {
  switch (s.kind) { // 類型保護
    case "square": return s.size * s.size;
    case "rectangle": return s.height * s.width;
    case "circle": return Math.PI * s.radius ** 2;
  }
}
```
#### 10.6.1 完整性檢查
```ts
// 使用 --strictNullChecks 並指定返回類型
function area(s: Shape): number { // error: returns number | undefined
  switch (s.kind) {
    case "square": return s.size * s.size;
    case "rectangle": return s.height * s.width;
    case "circle": return Math.PI * s.radius ** 2;
  }
}

// 使用 never 類型
function assertNever(x: never): never {
  throw new Error("Unexpected object: " + x);
}
function area(s: Shape) {
  switch (s.kind) {
    case "square": return s.size * s.size;
    case "rectangle": return s.height * s.width;
    case "circle": return Math.PI * s.radius ** 2;
    default: return assertNever(s); // error here if there are missing cases
  }
}
```

### 10.7 多態 this 類型
- 連貫接口繼承，函數返回`this`以支持鏈式操作
```ts
class BasicCalculator {
  public constructor(protected value: number = 0) { }
  public currentValue(): number {
    return this.value;
  }
  public add(operand: number): this {
      this.value += operand;
    return this;
  }
  public multiply(operand: number): this {
    this.value *= operand;
    return this;
  }
  // ... other operations go here ...
}

let v = new BasicCalculator(2)
        .multiply(5)
        .add(1)
        .currentValue();

// 可直接擴展
class ScientificCalculator extends BasicCalculator {
  public constructor(value = 0) {
    super(value);
  }
  public sin() {
    this.value = Math.sin(this.value);
    return this;
  }
  // ... other operations go here ...
}

let v = new ScientificCalculator(2)
        .multiply(5)
        .sin()
        .add(1)
        .currentValue();
```

### 10.8 索引類型
```ts
function pluck<T, K extends keyof T>(o: T, names: K[]): T[K][] {
  return names.map(n => o[n]);
}

interface Person {
  name: string;
  age: number;
}
let person: Person = {
  name: 'Jarid',
  age: 35
};
let strings: string[] = pluck(person, ['name']); // ok, string[]
```



