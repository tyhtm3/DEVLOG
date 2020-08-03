<template>
  <div v-loading="loading">
    <app-header></app-header>

    <!-- Left side column. contains the sidebar -->
    <!-- <app-sidebar></app-sidebar> -->

    <!-- Content Wrapper. Contains page content -->
    <router-view></router-view>
    <!-- /.content-wrapper -->

    <app-footer></app-footer>

    <!-- <control-sidebar></control-sidebar> -->
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
    }
  }
</script>
