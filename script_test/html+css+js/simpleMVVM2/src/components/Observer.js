export let currentObserver = null

class Observer {
    constructor(vm, key, callback) {
        this.subjects = {}
        this.vm = vm
        this.key = key
        this.callback = callback
        this.value = this.getValue()
    }

    getValue() {
        // console.log('get value')
        currentObserver = this
        let value = this.vm.data[this.key]
        currentObserver = null
        // console.log('get value')
        // console.log(value)
        return value
    }

    subscribeTo(subject) {
        if(!this.subjects[subject.id]) {
            subject.addObserver(this)
            this.subjects[subject.id] = subject
        }
    }

    update() {
        // console.log('trigget update')
        // console.log(this.key)
        let oldVal = this.value
        let value = this.getValue()
        if(value !== oldVal) {
            // console.log(oldVal + '___' + value)
            this.value = value
            this.callback(value, oldVal)
        }
    }
}

export default Observer