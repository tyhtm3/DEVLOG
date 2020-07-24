<template>
  <header class="main-header">
    <!-- Logo -->
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- 로고 들어갈 자리 -->
      <!-- Sidebar toggle button -->
      <!-- <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
        <span class="icon-bar"></span> <span class="icon-bar"></span>
      </a> -->
      <span class="logo-mini">
        <router-link to="/dashboard-ju">
        <img src="../../assets/logo.png" height="50px" style="margin-left:20px; margin-top:5px">
        </router-link>
      </span>

      <div class="pull-left-search">
        <form id="demo-2">
            <input class="devin-search" type="search">
        </form>
      </div>
       
      <div class="navbar-custom-menu">
        <span><router-link to="/blog-main">[BlogMain]</router-link></span>
        <span><router-link to="/dashboard-ju">[FeedMain]</router-link></span>
				<span v-if="this.$store.state.isLogin">
					<el-button :plain="true" @click="logout">로그아웃</el-button>
				</span>
				<span v-else>
					<el-button :plain="true" @click="loginFormOpen">로그인</el-button>
				</span>
        <Login v-if="this.$store.state.loginFormVisible"></Login>
      </div>
    </nav>
    
    </header>
</template>
<script>
import Login from '../../components/Login.vue'
export default {
  name: 'DashboardHeader',
  components: {
    Login
  },
  methods : {
    loginFormOpen(){
     this.$store.state.loginFormVisible = true;
		},
		logout(){
     this.$store.state.isLogin = false;
    }
  },
}
</script>