import Observer from './Observer.js'
import {observe} from './util.js'

class MVVM {
    constructor(options) {
        this.init(options)
        observe(this.data)
        this.compile()
    }

    init(options) {
        this.el = document.querySelector(options.el)
        this.data = options.data
    }

    compile() {
        this.traverse(this.el)
    }

    traverse(node) {
        node.childNodes.forEach(childNode => {
            if(childNode.nodeType === 1) {
                this.traverse(childNode)
            }else if(childNode.nodeType === 3) {
                this.renderText(childNode)
            }
        });
    }

    renderText(textNode) {
        // console.log('render text')
        // console.log(textNode)
        const regex = /{{(.+?)}}/g
        let match
        while(match = regex.exec(textNode.textContent)) {
            // console.log(match)
            const raw = match[0]
            const key = match[1].trim()
            textNode.textContent = textNode.textContent.replace(raw, this.data[key])    //页面渲染
            new Observer(this, key, function(val, oldVal){
                // console.log('update document')
                // console.log(`oldVal=${oldVal}, val=${val}`)
                // console.log(textNode.textContent)
                textNode.textContent = textNode.textContent.replace(oldVal+'', val+'')
                // console.log(textNode.textContent)
            })
        }
    }
}

export default MVVM