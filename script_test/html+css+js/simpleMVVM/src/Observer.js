export let currentObserver = null

export default class Observer {
  constructor(vm, key, callback) {
    this.subjects = {}
    this.vm = vm
    this.key = key
    this.callback = callback
    this.value = this.getValue()
  }

  getValue() {
    currentObserver = this
    let value = this.vm.data[this.key]
    currentObserver = null
    return value
  }

  subscribeTo(subject) {
    if(!this.subjects[subject.id]) {
      subject.addObserver(this)
      this.subjects[subject.id] = subject
    }
  }

  update() {
    let oldVal = this.value
    let value = this.getValue()
    if(oldVal !== value) {
      this.value = value
      this.callback(this.vm, value, oldVal)
    }
  }
}