const http = require('http')
const fs = require('fs')
const path = require('path')
const url = require('url')

let root = path.resolve()

const sever = http.createServer(function(request,response){
  const pathname = url.parse(request.url).pathname
  const filepath = path.join(root, 'public', pathname)
  // 获取文件状态
  fs.stat(filepath, function(err,stats){
    console.log('err:')
    console.log(err)
    console.log('stats: ')
    console.log(stats)
    if(err){
      // 发送404响应
      console.log('err')
      response.writeHead(404)
      response.end("404 Not Found.")
    } else {
      console.log('success')
      // 发送200响应
      response.writeHead(200)
      // response是一个writeStream对象，fs读取html后，可以用pipe方法直接写入
      fs.createReadStream(filepath).pipe(response)
    }
  })
})
sever.listen(8080)
console.log('Sever is running at http://127.0.0.1:8080/')