<template>
  <div>
    <h1 class="text-center">Todo APP</h1>
    <completed-todo :todos="todos"/>
    <add-todo @addTodo="inputTodo"/>
    <todo-list  @toggleBoolean="togglecheck" @deleteTodo="delTodo" :todos="todos"/>
    
  </div>
</template>

<script>
import TodoList from "../src/components/List.vue"
import AddTodo from "../src/components/AddTodo.vue"
import CompletedTodo from "../src/components/CompletedTodo.vue"

export default {

  components: {
    TodoList,
    AddTodo,
    CompletedTodo
  },
  data() {
    return {
    todos:[
    { id: 1, text: 'Buy computer', checked: false }
    ]
    }
  },
  methods: {
    inputTodo(e) {
      this.todos.push(
        {
          id: Math.random(),
          text: e,
          checked: false
        }
        )
        
    },

    delTodo(id) {
      const index = this.todos.findIndex(v => {
        return v.id === id
      })
      this.todos.splice(index, 1)
    },

    togglecheck({checked , id}) {
      const index = this.todos.findIndex(v => {
        return v.id === id
      })
      this.todos[index].checked = checked
      console.log(checked)
    }
    
  }

}
</script>

<style>

</style>