process.on('exit', function(code) {

  setTimeout(() => {
    console.log('timer after process exit')
  }, 0);

  console.log('return code ' + code)
})

console.log('process test over')

process.stdout.write('hello stdout\n')
process.stderr.write('hello stderr\n')
console.log(process.argv)
console.log(process.execPath)
console.log(process.execArgv)
console.log(process.env)

