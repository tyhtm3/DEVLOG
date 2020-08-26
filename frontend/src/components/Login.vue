<template>
	<div class="form">
		<div class="wrap">
			<div class="header">
				<span @click="loginFormClose"><i class="material-icons">close</i></span>
			</div>
			<div class="body">
				<input v-model="id" id="id" placeholder="id" type="text"/>
				<input v-model="password" type="password" id="password" placeholder="password"/>
				<button class="normal" @click="login">로그인</button><p/>
				<button class="kakao" @click="kakaoLogin">kakao</button><a id="naverIdLogin_loginButton" href="#"><button id="naverIdLogin" class="naver">NAVER</button></a><p/>
				<!-- <button class="google" @click="login">google</button><button class="facebook" @click="login">facebook</button> -->
				<p class="message">
					<span class="pull-left">
					<router-link to="/findpw" style="color:#11212E;"><i class="dripicons-lock"></i> 아이디·비밀번호 찾기
					</router-link>
					</span>
					<span class="pull-right">
					<router-link to="/signup" style="color:rgb(74 127 161);"><i class="dripicons-user"></i> 회원가입
					</router-link>
					</span>
				</p>
			</div>
		</div> 
	</div>
</template>
<script>
import http from '../util/http-common'
export default {
	data: () => {
		return {
			id: '',
			password: '',
		}
	},
  mounted() {
    const that = this
    const naverLogin = new naver.LoginWithNaverId({
		clientId: 'RSKBTL31UOSpdlckpmTt',
		callbackUrl:'http://i3a402.p.ssafy.io/naver/',
		  isPopup: true,
		  loginButton:{
			  color:'green',
			  type:1,
			  height:15,
		  },
		  callbackHandle: true
    	})
	  naverLogin.init()
  },
	methods: {
		loginFormClose(){
			this.$store.commit('setLoginFormVisible', false)
		},
		login() {
			if(this.id==='')
				this.$message.warning('아이디를 입력해주세요')
			else if(this.password==='')
				this.$message.warning('비밀번호를 입력해주세요')
			else{
				http
				.post('/user/login', { 
					id: this.id,
					password: this.password
				})
				.then(({data}) => {
					this.$store.commit('setIsLogin', true)
					this.$store.commit('setToken', data)
					http
					.get('/user/me',{headers : {'Authorization' : data,}})
					.then(({data}) => {
						this.$message.success('정상적으로 로그인되었습니다.')
						this.$store.commit('setUserInfo', data)
						this.$store.commit('setLoginFormVisible', false)
					})
					.catch((error) => {
						this.$message.error('로그인 도중 오류가 발생했습니다.')
					})
				})
				.catch((error) =>  {
					if(error.response.status == '404')
						this.$message.error('존재하지 않는 아이디입니다.')
					else if(error.response.status == '401')
						this.$message.error('비밀번호가 틀렸습니다.')
					else
						this.$message.error('로그인 도중 오류가 발생했습니다.')
				})
			}
		},
		kakaoLogin(){
			Kakao.Auth.login({
				success:this.kakaoLoginStore,
			});
		},
		kakaoLoginStore(authObj){
			this.$store.dispatch('kakaoLogin', {access_token : authObj.access_token})
			this.$router.push('/')
		}
	},
}

</script>
<style>
 @import url("https://fonts.googleapis.com/css?family=Roboto:300");
 .wrap{
  position: absolute;
  right: 80px;
  z-index: 1;
  background: #ffffff;
  max-width: 360px;
  margin: 20px auto 100px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.wrap .header{
    text-align: right;
    margin: 20px 20px;
 }
.wrap .body{
  padding: 10px 45px 45px 45px;
}
.wrap span{
    cursor: pointer;
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
	font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #6E6E6E;
  border: 0;
  padding: 15px;
  color: #ffffff;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form .normal {
	width: 100%;
}
.form .naver {
  background: #2DB400;
	color: #ffffff;
	width: 50%;
	display: inline;
}
.form .kakao {
  background: #F7E600;
	color: #443731;
	width: 50%;
	display: inline;
}
.form .facebook {
  background: #3B5998;
	color: #ffffff;
	width: 50%;
	display: inline;
}
.form .google {
  background: #EA4335;
	color: #ffffff;
	width: 50%;
	display: inline;
}

.form .normal:hover,
.form .normal:active,
.form .normal:focus {
  background: #424242;
}

.form .kakao:hover,
.form .kakao:active,
.form .kakao:focus {
  background: #d8ca00;
}

.form .naver:hover,
.form .naver:active,
.form .naver:focus {
  background: #238d00;
}
.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}

.form .message a {
  color: #4caf50;
  text-decoration: none;
}
#space{
  margin-bottom: 10px;
}
</style>