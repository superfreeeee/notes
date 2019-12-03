let id = 0

export default class Subject {
  constructor() {
    this.id = id++
    this.observers = []
  }

  addObserver(observer) {
    this.observers.push(observer)
  }

  // removeObserver(observer) {
    
  // }

  notify() {
    this.observers.forEach(observer => {
      observer.update()
    })
  }
}