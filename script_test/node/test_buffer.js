const buf = Buffer.from('hello world123456', 'ascii')

console.log(buf.toString())
console.log(buf.toString('hex'))
console.log(buf.toString('base64'))

const buf2 = Buffer.alloc(10)
const buf3 = Buffer.alloc(10, '11', 'ascii')
const buf4 = Buffer.alloc(10, '1', 'ascii')
console.log(buf2)
console.log(buf3)
console.log(buf3.toString('ascii'))
console.log(buf4)
console.log(buf4.toString('ascii'))
