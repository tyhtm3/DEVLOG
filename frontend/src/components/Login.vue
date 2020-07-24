<template>
    <div class="form">
        <div class="wrap">
            <input v-model="email"
                        id="email"
                        placeholder="이메일을 입력해주세요"
                        type="text"/>
            <input v-model="password" type="password"
                        id="password"
                        placeholder="영문, 숫자 혼용 8자 이상"/>
            <button @click="login">login</button>
            <p class="message">
            Not registered?
            <!-- <router-link v-bind:to=""> -->
                Create an account
            <!-- </router-link> -->
            </p>
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
            login() {
                if(this.email==='')
                    alert('이메일을 입력해주세요.')
                else if (!this.validEmail(this.email))
                    alert("이메일 형식을 확인하세요.")
                else if(this.password==='')
                    alert('비밀번호를 입력해주세요.')
                else
                    this.$store.dispatch('login', {email: this.email, password: this.password})
            },
            validEmail: function(email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }
        },
        data: () => {
            return {
                email: '',
                password: '',
            }
        }
    }

</script>
<style>
 @import url("https://fonts.googleapis.com/css?family=Roboto:300");
 .wrap {
  position: relative;
  z-index: 1;
  background: #ffffff;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
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