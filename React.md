# React學習筆記
## React組件組成
```html
<Clock className='board'/>
```
1. 函數組件
```js
function Clock(props) {
  return (
    <div></div>
  );
}
```
2. ES6類組件
* 繼承`React.Componenet`
* `props`不可變，使用`this.state`
* `render`包裹返回值
```js
class Clock extends React.Component {
  constructor(props) {
    super(props)
    this.state = {

    }
  }
  render() {
    return (
      <div>
      </div>
    );
  }
}
```
### Others
* 大寫字母開頭（與html標籤區分）
* 接收props參數（屬性）
* 屬性使用`小駝峰`命名
* 不直接修改`sate`
```js
// Wrong
this.state.date = new Date()
// Correct
this.setState({
  date: new Date()
})
```
* `props`與`state`可能異步更新
```js
// Wrong
this.setState({
  counter: this.state.counter + this.props.increment,
});
// Correct
this.setState((state, props) => ({
  counter: state.counter + props.increment
}));
```
* 綁定事件`this`
```js
class Component extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      clicked: false
    }
    this.handleFunction = this.handleFunction.bind(this)
  }
  handleFunction() {
    this.setState((state) => ({
      clicked: !state.clicked
    }))
  }
  render() {
    return (
      <button onClick={this.handleFunction}>
        {this.state.clicked? 'On': 'Off'}
      </button>
    );
  }
}

// 未使用class field語法（不建議
class Component extends React.Component {
  handleFunction(e) {
    console.log('this is:', this)
  }
  render() {
    return (
      <button onClick={(e) => this.handleFunction(e)}>
        {this.state.clicked? 'On': 'Off'}
      </button>
    );
  }
}
```
* 組合
```js
function Block(props) {
  return (
    <div className={'block-' + props.color}>  //類選擇
      {props.children}
    </div>
  );
}
function Paper(props) {
  return (
    <Block color='blue'>
      <p>
        paragraph 1
      </p>
      <p>
        paragraph 2
      </p>
    </Block>
  );
}

// 自行約定（特例關係）
function Wheel(props) {
  return (
    <div>
      {`A ${props.color} wheel`}
    </div>
  );
}
function Car(props) {
  return (
    <div>
      {props.leftfront}
      {props.rightfront}
      {props.leftrear}
      {props.rightrear}
    </div>
  );
}
function Show() {
  return (
    <Car
      leftfront={<Wheel color='color1'/>}
      rightfront={<Wheel color='color2'/>}
      leftrear={<Wheel color='color3'/>}
      rightrear={<Wheel color='color4'/>}
    />
  );
}
```
## 生命週期鉤子
* 組件掛載後`componentDidMount`
* 組件移除後`componentWillUnmount`
