"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const data_1 = require("./data");
const TodoItem_1 = __importDefault(require("./TodoItem"));
const TodoCollection_1 = __importDefault(require("./TodoCollection"));
const sampleTodos = data_1.data.map((item) => new TodoItem_1.default(item.id, item.task, item.complete));
const myTodoCollection = new TodoCollection_1.default("My Todo List", sampleTodos);
myTodoCollection.addTodo("Javascript 학습하기");
myTodoCollection.addTodo("친구 만나기");
myTodoCollection.markComplete(2, true);
console.log(`${myTodoCollection.userName}`);
myTodoCollection.removeComplete();
myTodoCollection.getTodoItems(true).forEach((item) => item.printDetails());
console.log("================================");
myTodoCollection.getTodoItems(false).forEach((item) => item.printDetails());
