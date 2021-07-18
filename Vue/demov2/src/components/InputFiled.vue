<template>
    <form action="">
    <label for="">name</label>
  <input type="text" :value="somthignName" 
  style="padding: 30px; boder: 2px; solid green;"
  @input="updateName">
  {{ name }}

  </form>

</template>
<script>
export default {
    props: {
        somthignName: {
            type: String,
            required: true

        }
    },
    data() {
        return {
            name: "heheh"
        }
    },
    methods: {
        updateName(e) {
            console.log(e.target.value)
            this.$emit('update-Name', e.target.value)
        }
    },
}
</script>