function Subject() {
  this.observers = []
}

Subject.prototype.addObserver = function(observer) {
  this.observers.push(observer)
}

Subject.prototype.removeObserver = function(observer) {
  let index = this.observers.indexOf(observer)
  if(index != -1) {
    this.observers.splice(index, 1)
  }
}

Subject.prototype.notify = function() {
  this.observers.forEach(observer => {
    observer.update()
  })
}

function Observer(name) {
  this.name = name
  this.update = function() {
    console.log(name + ' update...')
    console.log(this)
  }
}

let subject = new Subject()
let observer1 = new Observer('Observer 1')
subject.addObserver(observer1)
let observer2 = new Observer('Observer 2')
subject.addObserver(observer2)
subject.notify()

console.log('\nremove observer 1\n')
subject.removeObserver(observer1)

observer1 = new Observer('Observer 1-new')
subject.addObserver(observer1)
subject.notify()