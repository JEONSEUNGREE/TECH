<template>
    <div>
        
        <p> {{ name }} </p>
        <button @click="updateName">Change Name</button>
        <h1> {{ test }} </h1>
    </div>  
</template>

<script>
export default {
    props: ['test'],
    data() {
        return {
            name: 'reeCoder',
        }
    },
    

    methods: {
        updateName(){
            this.name = "reeCoderUpdated";
        }
    },

}
</script>

<style scoped>
p {
    color: blue;
}
</style>