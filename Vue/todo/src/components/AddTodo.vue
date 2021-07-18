<template>
  <div>
      <input type="text" placeholder="Type todo" class="w-100" 
     :v-model="todoType" @keydown.enter="inputTodo">
  </div>
</template>

<script>
export default {
    data() {
        return {
            todoType: ''
        }
    },
    methods: {
        inputTodo(e) {
        this.$emit('addTodo', e.target.value)
    }
    },

}
</script>

<style>

</style>