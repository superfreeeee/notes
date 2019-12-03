import MVVM from './MVVM.js'

let vm = new MVVM({
  el: '#root',
  data: {
    name: 'username',
    age: 20
  }
})

setInterval(function() {
  vm.data.age++
}, 2000)