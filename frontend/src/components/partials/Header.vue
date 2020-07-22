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
                <!-- 로그인 버튼 들어갈 자리 -->
                <button type="button" class="el-button el-button--default"> 
                    <span>Login</span>
                </button>
            </div>
        </nav>
    </header>
</template>
<script>
  export default {
    name: 'DashboardHeader',
    mounted: function () {

    }
  }
</script>