<template>
  <header class="main-header">
    <!-- Logo -->
    <nav class="navbar navbar-static-top">
		<router-link to="/">
		<span class="imgswap logo-mini">
				<img src="../../assets/logo.png" height="40px" style="margin: 15px; margin-left:30px;">		
				<img src="../../assets/logo_hover.png" height="40px" style="margin: 15px; margin-left:30px;">
		</span>
		</router-link>
      <div class="navbar-custom-menu">
				<span v-if="this.$store.state.isLogin">
					<el-dropdown class="header-dropdown-devin" trigger="click" style="top: 0px;">
						<span class="el-dropdown-link">
							<img :src="this.$store.state.userInfo.profile_img_url" class="cover-profile" style="width:50px; height:50px; border:0px; cursor:pointer;" alt="cover"/>
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
											<a :href="$router.resolve({name: 'blog',params:{id:this.getUserInfo.id}}).href">
												<h3 style="margin-top: 9px;">내 블로그<span class="text-yellow fontello-record"></span></h3>
												<p></p>
											</a>
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
<style> 
.imgswap img:last-child{display:none} 
.imgswap:hover img:first-child{display:none} 
.imgswap:hover img:last-child{display:inline-block} 
.cover-profile:hover{
  box-shadow: 3px 3px 3px rgba(177, 177, 177, 0.527);
}
.menu li:first-child:hover{
	background-color: #9ebbcd21;
}
.menu li:nth-child(2):hover{
	background-color: #9ebbcd21;
}
.menu li:nth-child(3):hover{
	background-color: #9ebbcd21;
}
.el-button.is-plain:focus, .el-button.is-plain:hover{
	color: #11212E !important;
	border-color: #9ebbcd !important;
	box-shadow: 3px 3px 3px #9ebbcd34;
}
div.slimScrollDiv{
	height: 160px !important;
}
</style>

