const util = require('util')

function Base() {
  this.name = 'base'
  this.base = 1991
  this.sayHello = function() {
    console.log('Hello' + this.name)
  }
}

Base.prototype.showName = function() {
  console.log(this.name)
}

function Sub() {
  this.name = 'sub'
}

util.inherits(Sub, Base)
const objBase = new Base()
objBase.showName()
objBase.sayHello()
console.log(objBase)

const objSub = new Sub()
objSub.showName()
// objSub.sayHello()
console.log(objSub)

const subString = util.inspect(objSub)
const BaseString = util.inspect(objBase)
console.log(subString)
console.log(BaseString)
const BaseString2 = util.inspect(objBase, true)
console.log(BaseString2)

console.log(util.isArray([]))
console.log(util.isArray(new Array))
console.log(util.isArray({}))

