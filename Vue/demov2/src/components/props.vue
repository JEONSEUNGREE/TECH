<template>
<div>
  {{ meme }}
</div>
</template>

<script>
export default {
    props: {
        meme: {
            type: String,
            required: true,
        }
    },
    data() {
        return {
     
        }
    }

}
</script>

<style>

</style>