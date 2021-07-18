<template>
  <div>
      Completed Todo: {{ numberOfComletedTodo }}
      {{ todos }}
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
        todos() {
            return this.$store.state.todos;
        },
        numberOfComletedTodo() {
            return this.todos.filter(v => v.checked).length;
        }
    }

}
</script>

<style>

</style>