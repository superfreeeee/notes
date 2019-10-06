# JavaScript基礎

## Promise
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