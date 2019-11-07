const fs = require('fs')

const r = fs.createReadStream('input.txt')
const w = fs.createWriteStream('output2.txt')
r.pipe(w)

const zlib = require('zlib')
fs.createReadStream('input.txt')
  .pipe(zlib.createGzip())
  .pipe(fs.createWriteStream('input.txt.gz'))