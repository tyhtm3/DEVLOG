<template>
  <div id="app" class="wrapper">
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
<style lang="scss">
  @import "sass/main"
</style>