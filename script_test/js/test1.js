let obj = {name: 'superfree'}

Object.defineProperty(obj, 'age', {
  value: 14
})

Object.defineProperty(obj, 'job', {
  get: function() {
    return job
  },
  set: function(val) {
    job = 'My job is ' + val
  }
})
obj.job = 'hello'

console.log('obj' + obj)
console.log('obj.name: ' + obj.name)
console.log('obj.age: ' + obj.age)
console.log('obj.job: ' + obj.job)

console.log()
console.log('attrs:')
for(let key in obj) {
  console.log(key)
}
