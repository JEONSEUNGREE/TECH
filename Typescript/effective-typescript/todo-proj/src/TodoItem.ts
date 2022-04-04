class TodoItem {
    public id : number; // private, public, protected
    public task : string;
    public complete : boolean;

    constructor(id : number, task : string, complete : boolean) {
        this.id = id;
        this.task = task;
        this.complete = complete;
    }
}