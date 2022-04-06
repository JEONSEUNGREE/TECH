"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const TodoConsole_1 = __importDefault(require("./view/TodoConsole"));
const todoConsole = new TodoConsole_1.default();
todoConsole.promptUser();
// inquirer 와 노드몬 같이사용시 화살표 입력오류 발생하기에 node로 실행해야함
// import { data } from "./data";
// import TodoItem from "./model/TodoItem";
// import TodoCollection from "./service/TodoCollection";
// const sampleTodos : TodoItem [] = data.map(
//     (item) => new TodoItem(item.id, item.task, item.complete)
// );
// const myTodoCollection = new TodoCollection("My Todo List", sampleTodos);
// myTodoCollection.addTodo("Javascript 학습하기");
// myTodoCollection.addTodo("친구 만나기");
// myTodoCollection.markComplete(2, true);
// console.log(`${myTodoCollection.userName}`);
// myTodoCollection.removeComplete()
// myTodoCollection.getTodoItems(true).forEach((item) => item.printDetails());
// console.log("================================")
// myTodoCollection.getTodoItems(false).forEach((item) => item.printDetails());
