<template>
  <div>
  <kossie-coder :title="title"/>
  <input-filed :name="name" @update-Name="updateName" />
  <button type="submit">submit</button>
  <props-practice :meme="somthing"/>





  </div>
</template>

<script>
import KossieCoder from '../components/KossieCoder.vue'
import InputFiled from '../components/InputFiled.vue'
import propsPractice from '../components/props.vue';

export default {
  data() {
    return {
      name: "hehehehehe",
      title: 5,
      somthing: "himain"
    }
  },
  components: { 
    'kossie-coder': KossieCoder,
    'input-filed': InputFiled,
    'props-practice': propsPractice
    },
    methods: {
      updateName(name) {
        this.name = name;
      }
    },


}
</script>

<style>

</style>