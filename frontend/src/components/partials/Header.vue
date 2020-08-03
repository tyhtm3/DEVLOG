<template>
  <header class="main-header">
    <!-- Logo -->
    <nav class="navbar navbar-static-top">
      <span class="logo-mini">
        <router-link to="/">
        <img src="../../assets/logo2.png" height="50px" style="margin-left:30px; margin-top:15px">
        </router-link>
      </span>
      <div class="navbar-custom-menu">
				<span v-if="this.$store.state.isLogin">
					<el-dropdown class="header-dropdown-devin" trigger="click" style="top: 0px;">
						<span class="el-dropdown-link">
							<img :src="this.$store.state.userInfo.profile_img_url" class="cover-profile" style="width:45px; height:45px; border:0px; cursor:pointer;" alt="cover"/>
							<!-- <img src="static/img/profile.png" class="cover-profile" style="width:45px; height:45px; border:0px; cursor:pointer;" alt="cover"/> -->
						</span>
						<el-dropdown-menu slot="dropdown">
							<ul class="dropdown-menu-devin">
								<li>
									<ul class="menu">
										<li> <i class="ti-user"></i>
											<router-link to="/myinfo">
												<h3 style="margin-top: 9px;">{{ this.$store.state.userInfo.name }}<span class="text-green fontello-record"></span></h3>
												<p></p>
											</router-link>
										</li>
										<li> <i class="ti-home"></i>
											<router-link to="/blog">
												<h3 style="margin-top: 9px;">내 블로그<span class="text-yellow fontello-record"></span></h3>
												<p></p>
											</router-link>
										</li>
										<li> <i class="ti-power-off"></i>
											<a style="cursor:pointer;" @click="logout">
												<h3 style="margin-top: 9px;">로그아웃<span class="text-yellow fontello-record"></span></h3>
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
		 this.$store.state.userInfo = {seq:0}
		 alert("로그아웃 되었습니다.")
		 localStorage.clear();
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