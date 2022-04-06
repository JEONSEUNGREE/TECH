"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class TodoItem {
    // private, public, protected
    constructor(id, task, complete = false) {
        this.id = id;
        this.task = task;
        this.complete = complete;
        // default false
        this.id = id;
        this.task = task;
        this.complete = complete;
    }
    printDetails() {
        console.log(`${this.id}\t ${this.task} \t ${this.complete ? "\t(complete)" : ""}`);
    }
}
exports.default = TodoItem;
