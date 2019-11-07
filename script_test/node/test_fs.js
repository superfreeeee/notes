var fs = require('fs');

// sync
console.log('\ntest sync')
var data = fs.readFileSync('input.txt');
console.log('read input.txt:');
console.log(data.toString());

// not sync
console.log('\ntest not sync')
fs.readFile('input.txt', function(err, data) {
  if(err) {
    console.log(err)
    return
  }
  console.log(data.toString())
})
console.log('running end')

