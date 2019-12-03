// const header = document.querySelector('#header')
// header.innerHTML = 'hello world'

import MVVM from './components/MVVM.js'

let vm = new MVVM({
  el: '#app',
  data: {
    name: 'superfree',
    age: 20
  }
})

setInterval(function() {
  vm.data.age++
}, 2000)

