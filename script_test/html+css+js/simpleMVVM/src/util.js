import Subject from './Subject.js'
import {currentObserver} from './Observer.js'

export function observe(data) {
  if(!data || typeof data !== 'object') {
    return
  }
  for(let key in data) {
    let value = data[key]
    if(typeof value === 'object') {
      observe(value)
    }
    let subject = new Subject()

    Object.defineProperty(data, key, {
      configurable: true,
      enumerable: true,
      get: function() {
        // console.log('get data[' + key + '] = ' + value)
        if(currentObserver) {
          currentObserver.subscribeTo(subject)
        }
        return value
      },
      set: function(newVal) {
        console.log('set data[' + key + '] = ' + value)
        value = newVal
      }
    })
  }
}