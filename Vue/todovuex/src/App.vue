<template>
  <div>
    <h1 class="text-center">Todo APP</h1>
    <completed-todo />
    <add-todo />
    <todo-list />
    <user-list />
    
  </div>
</template>

<script>
import TodoList from "../src/components/List.vue"
import AddTodo from "../src/components/AddTodo.vue"
import CompletedTodo from "../src/components/CompletedTodo.vue"
import UserList from "../src/components/UserList.vue"

export default {

  components: {
    TodoList,
    AddTodo,
    CompletedTodo,
    UserList
  },
  data() {
    return {

    }
  },
  methods: {
  }

}
</script>

<style>

</style>