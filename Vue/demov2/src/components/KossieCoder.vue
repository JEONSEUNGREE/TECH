<template>
  <div>
      <h1> {{ title }} </h1>
      <p>{{ name }}></p>
      <button @click="update">변경</button>
  </div>
</template>

<script>
export default {
    props: {
        title: {
            type: Number,
            required: true,
        }
    },
    data() {
        return {

        name: "kossieCoder",

        }
    },

    methods: {
        update() {
            return this.name = "kossieCoder Update";
        }
    },



}
</script>

<style scoped>
* {
    color: aqua;
    background-color: black;
}


</style>