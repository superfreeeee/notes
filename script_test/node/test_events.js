var events = require('events')
var emitter = new events.EventEmitter()

var callback = function() {
  console.log('this is callback()')
}

console.log('\n>>> test 1: add callback')
emitter.on('hello', callback)
emitter.emit('hello')

var callback2 = function(name) {
  console.log('this is callback2()')
  if(name)
    console.log('hello ' + name + '!')
}

console.log('\n>>> test 2: add callback2')
emitter.on('hello', callback2)
emitter.emit('hello', 'John')

var callback3 = function() {
  console.log('this is callback3()')
  console.log('emit bye')
  emitter.emit('bye')
}

console.log('\n>>> test 3: add bye hello')
emitter.on('bye', callback2)
emitter.on('hello', callback3)
emitter.emit('hello')

console.log('\n>>> test 4: remove')
emitter.removeListener('hello', callback)
emitter.emit('hello')

console.log('\n>>> test 5: removeall')
emitter.removeAllListeners()
emitter.emit('hello')
emitter.emit('bye')

console.log('\n>>> test 6: add hello hello2 hello3')
emitter.on('hello', function() {console.log('hello')})
emitter.on('hello', function() {console.log('hello-2')})
emitter.on('hello', function() {console.log('hello-3')})
emitter.on('hello2', function() {console.log('hello2')})
emitter.on('hello2', function() {console.log('hello2-2')})
emitter.on('hello2', function() {console.log('hello2-3')})
emitter.on('hello3', function() {console.log('hello3')})
emitter.on('hello3', function() {console.log('hello3-2')})
emitter.on('hello3', function() {console.log('hello3-3')})
emitter.emit('hello')
emitter.emit('hello2')
emitter.emit('hello3')

console.log('\n>>> test 7: removeall(hello2)')
emitter.removeAllListeners('hello2')
emitter.emit('hello')
emitter.emit('hello2')
emitter.emit('hello3')

console.log('\n>>> test 8: listeners')
console.log('hello listeners')
var list = emitter.listeners('hello')
for(var listener of list) {
  console.log(listener)
  listener()
}
console.log('\nhello2 listeners')
var list = emitter.listeners('hello2')
for(var listener of list) {
  console.log(listener)
  listener()
}
console.log('\nhello3 listeners')
var list = emitter.listeners('hello3')
for(var listener of list) {
  console.log(listener)
  listener()
}

console.log('\n>>> test 9: listenerCount')
var count = emitter.listenerCount('hello')
var count2 = emitter.listenerCount('hello2')
var count3 = emitter.listenerCount('hello3')
console.log('hello have ' + count + ' listeners')
console.log('hello2 have ' + count2 + ' listeners')
console.log('hello3 have ' + count3 + ' listeners')

emitter.removeAllListeners()

console.log('\n>>> test 10: error event')
emitter.on('error', function() {
  console.log('something goes wrong')
})
emitter.emit('error')
emitter.removeAllListener('error')
emitter.emit('error')