class TodoItem {
    // private, public, protected
    constructor(public id : number, public task : string, public complete : boolean = false) {
                                                          // default false
        this.id = id;
        this.task = task;
        this.complete = complete;
    }

    printDetails() {
        console.log(`${this.id}\t ${this.task} \t ${this.complete?"\t(complete)":""}`)
    }
}

export default TodoItem;