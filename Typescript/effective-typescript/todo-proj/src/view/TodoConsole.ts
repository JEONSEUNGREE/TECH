import * as inquirer from "inquirer";
import TodoItem from "../model/TodoItem";
import TodoCollection from "../service/TodoCollection";
import { data } from "../data";
import { Commands } from "../model/Commands";
// npm i inquirer @types/inquirer 입력을 받기위한 라이브러리

class TodoConsole {
    private todoCollection : TodoCollection;

    private showCompleted : boolean;

    constructor() {
        this.showCompleted = true;

        const sampleTodos : TodoItem[] = data.map(
            (item) => new TodoItem( item.id, item.task, item.complete)
        );

        this.todoCollection = new TodoCollection("My Todo List", sampleTodos);
    }

    displayTodoList():void {
        console.log(
            `======${this.todoCollection.userName}=======` + 
            `(${this.todoCollection.getItemCounts().incomplete} items todo)`
        )

        this.todoCollection.getTodoItems(this.showCompleted).forEach( (item) => item.printDetails());


    }

    promptUser(): void {
        console.clear()
        this.displayTodoList();

        inquirer
            .prompt({
            type: "list",
            name: "command",
            message: "Choose option",
            choices: Object.values(Commands) 

        }).then((answer) => {
            switch(answer["command"]){
                case Commands.Toggle:
                    this.showCompleted = !this.showCompleted;
                    this.promptUser();
                    break;
                case Commands.Add:
                    this.promprtAdd();
                    break;
                case Commands.Purge:
                    this.todoCollection.removeComplete();
                    this.promptUser();
                    break;
                case Commands.Complete:
                    if(this.todoCollection.getItemCounts().incomplete > 0) {
                        this.promptComplete();
                    } else {
                        this.promptUser();
                    }
            }
            // // quit 입력하면 종료
            // if(answer['command'] !== Commands.Quit) {
            //     this.promptUser();
            // }
        });
    }

    promprtAdd() : void {
        console.clear();
        inquirer.prompt({
            type:"input",
            name:"add",
            message:"Enter task : "
        }).then((answer) => {
            if(answer["add"] !== "") {
                this.todoCollection.addTodo(answer["add"]);
            }
            this.promptUser();
        })
    }

    promptComplete() : void {
        console.clear()
        inquirer.prompt({
            type:"checkbox",
            name:"complete",
            message:"Mark Tasks Complete",
            choices:this.todoCollection.getTodoItems(this.showCompleted).map((item) => ({
                name: item.task, 
                value: item.id,
                checked: item.complete
            }))
        }).then((answers) => {
            // 여기서 as alias가 아닌 assertion이다.
            let completedTasks = answers["complete"] as number [];
            this.todoCollection.getTodoItems(true).forEach((item) => 
                this.todoCollection.markComplete(
                    item.id,
                    completedTasks.find((id) => id === item.id) != undefined
                )
            )
            this.promptUser();
        })
    }
}

export default TodoConsole;