const w = 'hello world'
console.log(w)
console.info(w)
console.error(w)
console.warn(w)
console.trace()

console.time('timer1')
console.time('timer2')
console.time('timer3')

setTimeout(() => {
  console.timeEnd('timer1')
}, 100)

setTimeout(() => {
  console.timeEnd('timer2')
}, 500)

setTimeout(() => {
  console.timeEnd('timer3')
}, 1000)