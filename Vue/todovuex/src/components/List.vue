<template>
  <div>
      <todo @toggleBoolean="togglecheck" v-for="todo in todos" 
      :todo="todo" :key="todo.id" @deleteTodo="delTodo"/>
  </div>
</template>

<script>
import Todo from "../components/Todo.vue"

export default {
    components: {
        Todo
    },
    computed: {
        todos() {
            return this.$store.state.todos;
        }
    },
    methods: {
        togglecheck(value) {
         this.$emit("toggleBoolean", value)
         },
    delTodo(id) {
        this.$emit("deleteTodo", id )
    }
 }
}
</script>

<style>

</style>