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
            <div class="pull-left-search">
                <form id="demo-2">
	                <input class="devin-search" type="search">
                </form>
            </div>
           
            <div class="navbar-custom-menu">
                <span><router-link to="/blog-main">[BlogMain]</router-link></span>
                <span><router-link to="/dashboard-ju">[FeedMain]</router-link></span>
                <!-- 로그인 폼 시작 -->
                <el-button :plain="true" @click="loginFormOpen">로그인</el-button>
                <transition name='modal' appear @close="this.loginFormOpen=false">
                    <Login v-if="loginFormVisible"></Login>
                </transition>
                <!-- 로그인 폼 끝 -->
            </div>
        </nav>
    </header>
</template>
<script>
import Login from '../Login.vue'
export default {
    name: 'DashboardHeader',
    compoenets: Login,
    data: function () {
        return { 
            loginFormVisible: false
        }
    },
    methods : {
        loginFormOpen(){
            alert("되냐")
            this.loginFormVisible = true;
        },
         

        logout(){   
            this.$store.state.isLogin = false;
            this.openLoginFlag = false;
            alert("로그아웃 되었습니다.");
        },
    },
  }
</script>