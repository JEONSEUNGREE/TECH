import TodoItem from "./TodoItem";

class TodoCollection {
    private nextId : number = 1;

    private itemMap : Map<number, TodoItem>;

    constructor (public userName: string, public todoItems : TodoItem[] = []) {
        this.itemMap = new Map<number, TodoItem>();
        todoItems.forEach((item) => this.itemMap.set(item.id, item));
    }

    //  TodoItem을 리턴해주거나 없는경우 undefind를 리턴해준다.
    getTodoById(id: number) : TodoItem | undefined {
        // find 메서드는 배열에서 사용할수있으며 ES6문법부터 지원 함
        return this.itemMap.get(id);
    }

    addTodo(task : string) : number {
        while(this.getTodoById(this.nextId)) {
            this.nextId++;
        }
        this.itemMap.set(this.nextId, new TodoItem(this.nextId, task));
        return this.nextId;
    }

    // includeComplete -> true : 모든 할인 목록 반환 
    // includeComplete -> false : 완료 목록 제외한 할일 목록 반환
    getTodoItems(includeComplete: boolean): TodoItem[] {
        return [...this.itemMap.values()].filter( (item) => includeComplete || !item.complete)
    }

    removeComplete() : void {
        this.itemMap.forEach((item) => {
            if(item.complete) {
                this.itemMap.delete(item.id)
            }
        })
    }

    markComplete(id : number, complete : boolean) : void {
        const todoItem = this.getTodoById(id);
        if(todoItem) {
            todoItem.complete = complete;
        }
    }
}

export default TodoCollection;