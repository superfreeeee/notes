let id = 0

class Subject {
    constructor() {
        this.id = id++
        this.observers = []
        // console.log('new Subject')
    }

    addObserver(observer) {
        // console.log('add obaserver')
        // console.log(this.observers)
        this.observers.push(observer)
    }

    notify() {
        // console.log('triiger notify')
        // console.log(this.observers)
        this.observers.forEach(observer => {
            observer.update()
        })
    }
}

export default Subject