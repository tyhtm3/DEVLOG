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
				<span @click="test" style="cursor:pointer">test</span>
        <span><router-link to="/blog-main">[BlogMain]</router-link></span>
        <span><router-link to="/dashboard-ju">[FeedMain]</router-link></span>
				<span v-if="this.$store.state.isLogin">
					<el-dropdown class="header-dropdown-devin" trigger="click" style="top: 0px;">
						<span class="el-dropdown-link">
							<img src="static/img/profile.png" alt="cover" class="cover-profile" />
						</span>
						<el-dropdown-menu slot="dropdown">
							<ul class="dropdown-menu-devin">
								<!-- <li class="header">welcome devlog!</li> -->
								<li>
									<!-- inner menu: contains the actual data -->
									<ul class="menu">
										<li> <i class="ti-settings "></i>
											<router-link to="/myinfo">
												<h3>{{ this.$store.state.userInfo.name }}<span class="text-green fontello-record"></span></h3>
												<p>sub message</p>
											</router-link>
										</li>
										<li> <i class="ti-user "></i>
											<router-link to="/blog-main">
												<h3>내 블로그<span class="text-yellow fontello-record"></span></h3>
												<p>sub message</p>
											</router-link>
										</li>
										<li> <i class="ti-power-off "></i>
											<a style="cursor:pointer;" @click="logout">
												<h3>로그아웃<span class="text-yellow fontello-record"></span></h3>
												<p>sub message</p>
											</a>
										</li>
									</ul>
								</li>
							</ul>
						</el-dropdown-menu>
					</el-dropdown>
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
import router from '../../routes'
import http from '../../util/http-common'
import store from '../../store'
export default {
	name: 'DashboardHeader',
	components: {
		Login
	},
  methods: {
    loginFormOpen(){
     this.$store.state.loginFormVisible = true;
		},
		logout(){
		 this.$store.state.isLogin = false
		 this.$store.state.userInfo = {}
		 alert("로그아웃 되었습니다.")
		},
		test(){
			console.log(this.$store.state.userInfo)
			http
			.get('/user')
			.then(({data}) => {
				console.log(data)
      })
		}
  },
}
</script>