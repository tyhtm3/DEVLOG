<template>
</template>
<script>
  export default {
  created() {
        var access_token = window.location.href.split('=')[1].split('&')[0]
        opener.app.$root.$store.dispatch('naverLogin', {access_token : access_token})
        // opener.location.reload();
        window.close()
    },
    
  }
</script>
