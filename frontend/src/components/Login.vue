<template>
	<div class="form">
		<div class="wrap">
			<div class="header">
				<span @click="loginFormClose"><i class="material-icons">close</i></span>
			</div>
			<div class="body">
				<input v-model="id" id="id" placeholder="id" type="text"/>
				<input v-model="password" type="password" id="password" placeholder="password"/>
				<button class="normal" @click="login">login</button><p/>
				<button class="kakao" @click="kakaologin">kakao</button><button class="naver" @click="naverLogin">naver</button><p/>
				<!-- <button class="google" @click="login">google</button><button class="facebook" @click="login">facebook</button> -->
				<p class="message">Not registered?
					<router-link to="/signup">
						Create an account
					</router-link>
				</p>	
				<p class="message">Forgot password?
					<router-link to="/findpw">
						Find password
					</router-link>
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
			// naver
			CLIENT_ID: 'RSKBTL31UOSpdlckpmTt',
      		redirectURI: 'http://localhost:8090/devlog/api/user/naver',
			state: 123, 
     		naverLoginURL: 'https://nid.naver.com/oauth2.0/authorize?response_type=code'
		}
	},
	created () {
    this.naverLoginURL += '&client_id=' + this.CLIENT_ID + '&redirect_uri=' + this.redirectURI + '&state=' + this.state
  	},
	methods: {
		loginFormClose(){
			this.$store.state.loginFormVisible = false
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
					this.$store.commit('setLoginFormVisible', false)
					http
					.get('/user/me',{headers : {'Authorization' : data,}})
					.then(({data}) => {
						this.$message.success('정상적으로 로그인되었습니다.')
						this.$store.commit('setUserInfo', data)
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
		naverLogin(){
			window.open(this.naverLoginURL, '_blank');
			//var popup = window.open(this.naverLoginURL,'window_name','width=430,height=500,left=500,right=500,location=no,status=no,scrollbars=yes');
		},
		kakaoLogin(){
			Kakao.Auth.login({
				success:this.kakaoLoginStore,
			});
		},
		kakaoLoginStore(authObj){
			this.$store.dispatch('kakaoLogin', {access_token : authObj.access_token})
			this.$router.push('/')
		},
		myFunc(){
			alert("YA")
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

.form button:hover,
.form button:active,
.form button:focus {
  background: #424242;
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