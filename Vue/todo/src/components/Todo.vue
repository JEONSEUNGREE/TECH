<template>
  <div>
    <input type="checkbox" @change="toggleBoolean">
    <span :class="this.todo.checked ? 'text-muted' : '' " :style="this.todo.checked ? 'text-decoration: line-through' : '' "> {{ todo.text }}</span>
    <button v-on:click="deleteToggle">DELETE</button>

  </div>
</template>

<script>
export default {
  props: {
    todo: {
      type: Object,
      required: true
    }
  },
  methods: {
    deleteToggle () {
        this.$emit("deleteTodo", this.todo.id )
    },
    toggleBoolean() {
      this.todo.checked = !this.todo.checked
      this.$emit("toggleBoolean", { checked: this.todo.checked, id: this.todo.id })
    }
  },


}
</script>

<style>

</style>