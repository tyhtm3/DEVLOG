<template>
    
</template>

<script>
    export default {
        components: {
        },
        created(){
          this.$store.state.loginFormVisible = false;
        },
        computed: {
            emailValidation() {
                let re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
                return re.test(this.email);
            }
        },
        methods: {
            join() {
                if(this.nickname==='')
                    alert('닉네임을 입력해주세요.')
                else if(this.email==='')
                    alert('이메일을 입력해주세요.')
                else if (!this.validEmail(this.email))
                    alert("이메일 형식을 확인하세요.")
                else if(this.password==='')
                    alert('비밀번호를 입력해주세요.')
                else if(this.passwordConfirm==='')
                    alert('비밀번호를 한번 더 입력해주세요.')
                else if(this.password!==this.passwordConfirm)
                    alert('비밀번호가 일치하지 않습니다.')
               
                else if(this.isTerm===false)
                    alert('약관에 동의해주세요')
                else{
                    http
                        .post('/account/signup', { email: this.email, password: this.password, nickname: this.nickName })
                        .then(({ data }) => {
                            if((data.data)==="emailError")
                                alert("이미 사용중인 이메일입니다.")
                            else if(data.data==="nicknameError")
                                alert("이미 사용중인 닉네임입니다.")
                            else{
                                this.$store.state.authData = data
                                http
                                .get('/account/email?email='+this.email+'&nickname='+this.nickName)
                                .then(({ data })=> {
                                    this.$store.state.auth = data.data
                                })
                                this.$router.push('/user/joinsuccess')
                            }
                        })
                        .catch((error) =>  {
                            if(error.response.status == '400'){
                                alert('비밀번호는 8자리이상 영문과 숫자조합이여야 합니다.')
                            }
                        });
                }
            },
            validEmail: function(email) {
                var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(email);
            }
        },
        watch: {
            
        },
        data: () => {
            return {
                email: '',
                nickName: '',
                password: '',
                passwordConfirm: '',
                isTerm: false,
                passwordType:"password",
                passwordConfirmType:"password",
            }
        }

    }

</script>