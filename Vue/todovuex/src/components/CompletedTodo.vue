<template>
  <div>
      Completed Todo: {{ numberOfComletedTodo }}
  </div>
</template>

<script>

export default {
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