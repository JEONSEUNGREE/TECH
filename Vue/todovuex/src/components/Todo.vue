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
        this.$store.dispatch('deleteTodo', this.todo.id)
        // this.$store.commit('DELETE_TODO', this.todo.id);
        
    },
    toggleBoolean(e) {
      this.$store.dispatch('toggleTodo', { id: this.todo.id, checked: e.target.checked })
    //  this.$store.commit('TOGGEL_TODO', { id: this.todo.id, checked: e.target.checked });
    }
  },


}
</script>

<style>

</style>