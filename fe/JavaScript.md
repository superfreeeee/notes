# JavaScript基礎

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