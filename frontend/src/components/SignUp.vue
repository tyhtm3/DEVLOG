<template>
	<transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <!-- start Main content -->
      <section class="content">
        <div class="box">
          <div class="box-body" style="align:center; min-height:400px; max-width:800px; margin:auto">
						<div class="col-sm-12">
							<br>
							<br>
							<br>
							<div class="col-xs-12 col-sm-12 profile-name">
								<h2>회원 가입</h2>
								<hr>
								<dl class="dl-horizontal-profile">
									<dt>*아이디</dt>
                  <dd>
                    <el-input v-model="id" style="width: 40%;"></el-input>
                    <el-upload
                    class="avatar-uploader"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                  </dd>
                  <dt>*비밀번호</dt>
                  <dd><el-input v-model="password" type="password" style="width: 40%;"></el-input></dd>
									<dt>*비밀번호 확인</dt>
                  <dd><el-input v-model="confirm" type="password" style="width: 40%;"></el-input></dd>
									<dt>*이름</dt>
                  <dd><el-input v-model="name" style="width: 70%;"></el-input></dd>
									<dt>닉네임</dt>
                  <dd><el-input v-model="nickname" style="width: 70%;"></el-input></dd>
                  <dt>*이메일</dt>
									<dd><el-input v-model="email" style="width: 70%;"></el-input></dd>
									<dt>연락처</dt>
                  <dd><el-input v-model="tel" style="width: 70%;"></el-input></dd>
                  <dt>생년원일</dt>
                  <dd><el-date-picker v-model="birth" type="date"></el-date-picker></dd>
                  <dt>url</dt>
									<dd>
                    <el-input v-model="url" style="width: 70%;">
                      <template slot="prepend">https://</template>
                    </el-input>
                  </dd>
								</dl>
            	</div>
              <el-button :plain="true" @click="join">가입하기</el-button>
						</div>
          </div>
        </div>
      </section>
    </div>
	</transition>
</template>

<script>
  export default {
    components: {
    },
    data: () => {
      return {
        id: '',
        password: '',
        confirm: '',
        name: '',
        nickname: '',
        email: '',
        tel: '',
        birth: '',
        url: '',
        imageUrl: ''
      }
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
        if(this.id==='')
          alert('아이디를 입력해 주세요.')
        else if(this.email==='')
          alert('이메일을 입력해주세요.')
        else if (!this.validEmail(this.email))
          alert("이메일 형식을 확인해 주세요")
        else if(this.password==='')
          alert('비밀번호를 입력해 주세요.')
        else if(this.passwordConfirm==='')
          alert('비밀번호를 한번 더 입력해 주세요.')
        else if(this.password!==this.passwordConfirm)
          alert('비밀번호가 일치하지 않습니다.')
        else{
          http
          .post('/???', {
            id: this.id,
            password: this.password,
            name: this.name,
            nickname: this.nickname,
            email: this.email,
            tel: this.tel,
            birth: this.birth,
            url: this.url,
            imageUrl: this.imageUrl
          })
          .then(({ data }) => {
            if((data.data)==="emailError")
              alert("이미 사용중인 이메일입니다.")
            else if(data.data==="nicknameError")
              alert("이미 사용중인 닉네임입니다.")
            else{
              http
              .get('/account/email?email='+this.email+'&nickname='+this.nickName)
              .then(({ data })=> {
                this.$store.state.auth = data.data
              })
              this.$router.push('/blog-main')
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
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('Avatar picture must be JPG format!');
        }
        if (!isLt2M) {
          this.$message.error('Avatar picture size can not exceed 2MB!');
        }
        return isJPG && isLt2M;
      }
    },
  }

</script>

<style>
  .avatar-uploader .el-upload {
    top: 90px;
    left: 500px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: absolute;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 168px;
    height: 168px;
    line-height: 168px;
    text-align: center;
  }
  .avatar {
    width: 168px;
    height: 168px;
  }
  .avatar-uploader-icon {
    transform: translate(0%, 40%);
  }
</style>
