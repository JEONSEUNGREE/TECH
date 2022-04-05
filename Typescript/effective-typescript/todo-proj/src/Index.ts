import { data } from "./data";
import TodoItem from "./TodoItem";
import TodoCollection from "./TodoCollection";


const sampleTodos : TodoItem [] = data.map(
    (item) => new TodoItem(item.id, item.task, item.complete)
);

const myTodoCollection = new TodoCollection("My Todo List", sampleTodos);

myTodoCollection.addTodo("Javascript 학습하기");
myTodoCollection.addTodo("친구 만나기");
myTodoCollection.markComplete(2, true);

console.log(`${myTodoCollection.userName}`);

myTodoCollection.removeComplete()

myTodoCollection.getTodoItems(true).forEach((item) => item.printDetails());

console.log("================================")

myTodoCollection.getTodoItems(false).forEach((item) => item.printDetails());

