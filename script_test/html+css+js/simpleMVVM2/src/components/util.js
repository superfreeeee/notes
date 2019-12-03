import Subject from "./Subject.js"
import {currentObserver} from './Observer.js'

export function observe(data) {
    if(!data || typeof data !== 'object') {
        return
    }
    for(let key in data) {
        let val = data[key]
        let subject = new Subject()    //创建主题
        if(typeof val === 'object'){
            observe(val)
        }
        Object.defineProperty(data, key, {
            configurable: true,
            enumerable: true,
            get(){
                // console.log('trigger ' + key + ' getter')
                // console.log(currentObserver)
                if(currentObserver) {
                    // console.log('subscribeTo')
                    // console.log(subject)
                    currentObserver.subscribeTo(subject)
                }
                return val
            },
            set(newVal){
                console.log(key + ' changed from ' + val + " to " + newVal)
                val = newVal
                subject.notify()
            }
        })
    }
}