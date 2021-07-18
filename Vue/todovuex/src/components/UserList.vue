<template>
  <div>
      <div v-for="user in users" :key="user.id">
          {{ user.name }} </div>
  </div>
</template>

<script>

export default {

    created() {
        this.getUser();
      
    },
    computed: {
        users() {
        return this.$store.state.users
        }
    },
    methods: {
        getUser() {
            this.$store.dispatch('getUsers');
        }
    },


}
</script>

<style>

</style>