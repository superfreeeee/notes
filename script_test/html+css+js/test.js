'use strict'
function MyRequest() {
  this.readyState = 0
  this.status = 0
  this.responseText = ''
}

MyRequest.prototype.open = function(method, url) {
  console.log(`open method: ${method}, url: ${url}`)
}
MyRequest.prototype.send = function(data) {
  console.log('data: ' + data)
}

function ajax(id, method, url, data, delay) {
  console.log('start id: ' + id)
  let request = new MyRequest()
  return new Promise(function( resolve, reject ) {
    setTimeout(() => {
      let i = Math.random() * 2
      if(i > 1) {
        request.status = 200
        request.responseText = 'request success'
        resolve(id, request.responseText)
      }
      else {
        request.status = 404
        reject(id, request.status)
      }

      request.open()
      request.send()
    }, delay)
  })
}

function sleep(delay) {
  let time = new Date().getTime()
  while(true) {
    let dt = new Date().getTime() - time
    // console.log(dt)
    if(dt > delay)
      break
  }
}

ajax(1, 'GET', 'localhost:8999', {id: 0, message: 'hello world'}, 4000)
  .then(response => console.log(response))
  .catch(error => console.log(error))

ajax(2, 'GET', 'localhost:8999', {id: 0, message: 'hello world'}, 1000)
  .then(response => console.log(response))
  .catch(error => console.log(error))


// ajax('GET', 'localhost:8999', {id: 0, message: 'hello world'})
//   .then(response => console.log(response))
//   .catch(error => console.log(error))