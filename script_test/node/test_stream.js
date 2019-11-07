const fs = require('fs')

// read stream
let data = ''
const readStream = fs.createReadStream('input2.txt')
const writeStream = fs.createWriteStream('output.txt')
readStream.setEncoding('UTF8')

readStream.on('data', function(chunk) {
  console.log('emit data')
  data += chunk
})

readStream.on('end', function() {
  console.log('emit end')
  console.log(data)
  write(data)
})

readStream.on('error', function(err) {
  console.log(err.stack)
})

console.log('reading over')

// write stream

writeStream.on('finish', function() {
  console.log('finish write')
})

writeStream.on('error', function(err) {
  console.log(err.stack)
})

function write(data) {
  writeStream.write(data, 0, 12, 'UTF8')
  writeStream.end()
}

// writeStream.on('data', function() {

// })

console.log('writing over')

