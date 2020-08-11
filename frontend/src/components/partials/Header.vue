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
												<h3 style="margin-top: 9px;" v-if="this.getUserInfo.nickname==''">{{ this.getUserInfo.name }}<span class="text-green fontello-record"></span></h3>
												<h3 style="margin-top: 9px;" v-else>{{ this.getUserInfo.nickname }}<span class="text-green fontello-record"></span></h3>
												<p></p>
											</router-link>
										</li>
										<li> <i class="ti-home"></i>
											<router-link :to="{ name: 'blog', params: { id: this.getUserInfo.id }}">
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
        <login v-if="this.getLoginFormVisible"></login>
      </div>
    </nav>
    
    </header>
</template>
<script>
import Login from '../../components/Login.vue'
import http from '../../util/http-common'
import { mapGetters } from 'vuex'
export default {
	name: 'Header',
	components: {
		Login
	},
	computed: {
		...mapGetters([
			'getUserInfo',
			'getLoginFormVisible'
		])
	},
	data: ()=>{
		return{
			basicurl: '/blog/',
			blogurl:'',
			url:'',
		}
	},
	mounted() {
		this.blogurl = this.getUserInfo.seq
		this.url=this.basicurl+this.blogurl
		// alert(this.url);
	},
	methods: {
		loginFormOpen(){
			this.$store.commit('setLoginFormVisible', true)
		},
		logout(){
			this.$store.commit('setIsLogin', false)
			this.$store.commit('setUserInfo', {seq:0})
			this.$store.commit('setToken', null)
			this.$message.success('로그아웃 되었습니다.');
			localStorage.clear();
			this.$router.push('/')
		}
	},
}
</script>