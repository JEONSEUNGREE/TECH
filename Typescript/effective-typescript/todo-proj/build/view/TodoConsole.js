"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const inquirer = __importStar(require("inquirer"));
const TodoItem_1 = __importDefault(require("../model/TodoItem"));
const TodoCollection_1 = __importDefault(require("../service/TodoCollection"));
const data_1 = require("../data");
const Commands_1 = require("../model/Commands");
// npm i inquirer @types/inquirer 입력을 받기위한 라이브러리
class TodoConsole {
    constructor() {
        this.showCompleted = true;
        const sampleTodos = data_1.data.map((item) => new TodoItem_1.default(item.id, item.task, item.complete));
        this.todoCollection = new TodoCollection_1.default("My Todo List", sampleTodos);
    }
    displayTodoList() {
        console.log(`======${this.todoCollection.userName}=======` +
            `(${this.todoCollection.getItemCounts().incomplete} items todo)`);
        this.todoCollection.getTodoItems(this.showCompleted).forEach((item) => item.printDetails());
    }
    promptUser() {
        console.clear();
        this.displayTodoList();
        inquirer
            .prompt({
            type: "list",
            name: "command",
            message: "Choose option",
            choices: Object.values(Commands_1.Commands)
        }).then((answer) => {
            switch (answer["command"]) {
                case Commands_1.Commands.Toggle:
                    this.showCompleted = !this.showCompleted;
                    this.promptUser();
                    break;
                case Commands_1.Commands.Add:
                    this.promprtAdd();
                    break;
                case Commands_1.Commands.Purge:
                    this.todoCollection.removeComplete();
                    this.promptUser();
                    break;
                case Commands_1.Commands.Complete:
                    if (this.todoCollection.getItemCounts().incomplete > 0) {
                        this.promptComplete();
                    }
                    else {
                        this.promptUser();
                    }
            }
            // // quit 입력하면 종료
            // if(answer['command'] !== Commands.Quit) {
            //     this.promptUser();
            // }
        });
    }
    promprtAdd() {
        console.clear();
        inquirer.prompt({
            type: "input",
            name: "add",
            message: "Enter task : "
        }).then((answer) => {
            if (answer["add"] !== "") {
                this.todoCollection.addTodo(answer["add"]);
            }
            this.promptUser();
        });
    }
    promptComplete() {
        console.clear();
        inquirer.prompt({
            type: "checkbox",
            name: "complete",
            message: "Mark Tasks Complete",
            choices: this.todoCollection.getTodoItems(this.showCompleted).map((item) => ({
                name: item.task,
                value: item.id,
                checked: item.complete
            }))
        }).then((answers) => {
            // 여기서 as alias가 아닌 assertion이다.
            let completedTasks = answers["complete"];
            this.todoCollection.getTodoItems(true).forEach((item) => this.todoCollection.markComplete(item.id, completedTasks.find((id) => id === item.id) != undefined));
            this.promptUser();
        });
    }
}
exports.default = TodoConsole;
