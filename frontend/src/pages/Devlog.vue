<template>
  <div>
    <app-header></app-header>

    <router-view></router-view>

    <app-footer></app-footer>

  </div>
</template>

<script>
  import Header from '../components/partials/Header.vue'
  import Footer from '../components/partials/Footer.vue'

  export default {
    name: 'Devlog',
    components: {
      'app-header': Header,
      'app-footer': Footer,
    },
  }
</script>
