<template>
  <div>
      Completed Todo: {{ numberOfComletedTodo }}
      {{ todoss }}
  </div>
</template>

<script>

export default {
    props: {
        todos: {
            type: Array,
            required: true
        }
    },
    computed: {
        todoss() {
            return this.$store.state.todos;
        },
        numberOfComletedTodo() {
            return this.todos.filter(v => v.checked).length
        }
    }

}
</script>

<style>

</style>