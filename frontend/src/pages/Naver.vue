<template>
</template>
<script>
  export default {
  created() {
        this.$store.dispatch('naverLogin', this.$route.params.jwt)
        this.$router.push('/')
    },
    
  }
</script>
