import Model from './Model'

class Component {

    container: HTMLDivElement

    model: Model

    constructor(container, model) {
        this.container = container
        this.model = model
    }
}
