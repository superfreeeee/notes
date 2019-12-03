class Subject {
  constructor() {
    this.observers = []
  }

  addObserver(observer) {
    if(this.observers.indexOf(observer) !== -1) {
      return
    }
    this.observers.push(observer)
  }

  removeObserver(observer) {
    let index = this.observers.indexOf(observer)
    if(index != -1) {
      this.observers.splice(index, 1)
    }
  }

  notify() {
    this.observers.forEach(observer => {
      observer.update()
    })
  }
}

class Observer {
  constructor(name) {
    this.name = name
  }

  update() {
    console.log(this.name + ' update...')
  }

  subscribeTo(subject) {
    subject.addObserver(this)
  }

  unsubscribeFrom(subject) {
    subject.removeObserver(this)
  }
}


let subject = new Subject()
let observer1 = new Observer('Observer 1')
observer1.subscribeTo(subject)
let observer2 = new Observer('Observer 2')
observer2.subscribeTo(subject)
subject.notify()

console.log('\nremove observer 1\n')
subject.removeObserver(observer1)

observer1 = new Observer('Observer 1-new')
observer1.subscribeTo(subject)
observer1.subscribeTo(subject)
observer1.subscribeTo(subject)
observer1.subscribeTo(subject)
subject.notify()