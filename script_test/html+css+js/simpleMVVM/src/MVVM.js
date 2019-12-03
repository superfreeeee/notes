import {observe} from './util.js'
import Observer from './Observer.js'

export default class MVVM {
  constructor(options) {
    this.init(options)
    observe(this.data)
    this.compile()
  }

  init(options) {
    // console.log(this)
    console.log("MVVM init")
    this.el = document.querySelector(options.el)
    this.data = options.data
  }

  compile() {
    console.log("MVVM compile")
    this.traverse(this.el)
  }

  traverse(node) {
    node.childNodes.forEach(childNode => {
      if(childNode.nodeType === 1) {
        this.traverse(childNode)
      } else if(childNode.nodeType === 3) {
        this.renderText(childNode)
      }
    })
  }

  renderText(textNode) {
    console.log('trigger renderText')
    const reg = /{{(.+?)}}/g
    let match
    while(match = reg.exec(textNode.textContent)) {
      // console.log(match)
      let row = match[0]
      let key = match[1].trim()
      textNode.textContent = textNode.textContent.replace(row, this.data[key])
      new Observer(this, key, function(val, oldVal) {
        textNode.textContent = textNode.textContent.replace(oldVal, val)
      })
    }
  }
}