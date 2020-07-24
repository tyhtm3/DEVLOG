<template>
	<div class="form">
		<div class="wrap">
			<div class="header">
				<span @click="loginFormClose"><i class="material-icons">close</i></span>
			</div>
			<div class="body">
				<input v-model="id" id="id" placeholder="id" type="text"/>
				<input v-model="password" type="password" id="password" placeholder="password"/>
				<button @click="login">login</button>
				<p class="message">Not registered?
					<router-link to="/signup">
						Create an account
					</router-link>
				</p>
				<p class="message">Forgot password?
					<router-link to="/findpassword">
						Find password
					</router-link>
				</p>
			</div>
		</div> 
	</div>
</template>
<script>
	export default {
		name: 'Login',
		components: {
		},
		created(){
		},
		watch: {
		},
		methods: {
				loginFormClose(){
						this.$store.state.loginFormVisible = false
				},
				login() {
						this.$store.state.isLogin = true
						this.$store.state.loginFormVisible = false
						alert("로그인 되었습니다(임시)")

						// if(this.id==='')
						// 		alert('아이디을 입력해주세요.')
						// else if(this.password==='')
						// 		alert('비밀번호를 입력해주세요.')
						// else
						// 		this.$store.dispatch('login', {id: this.id, password: this.password})
				}
		},
		data: () => {
				return {
						id: '',
						password: '',
				}
		}
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
  width: 100%;
  border: 0;
  padding: 15px;
  color: #ffffff;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
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