<template>
	<transition name="el-zoom-in-top">
    <section class="content">
      <div class="box">
        <div class="box-body" style="align:center; min-height:400px; max-width:800px; margin:auto;">
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
                  class="avatar-uploader2"
                  action="http://i3a402.p.ssafy.io:8090/devlog/api/user/upload"
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
                <dt>생년월일</dt>
                <dd><el-date-picker v-model="birth" type="date"></el-date-picker></dd>
                <dt>Giturl</dt>
                <dd>
                  <el-input v-model="url" style="width: 70%;">
                    <template slot="prepend">https://</template>
                  </el-input>
                  
                  <el-tooltip  class="pull-right"  content="포트폴리오 생성시 자동으로 데이터를 입력해줍니다!" placement="right">
                    <img src="http://i3a402.p.ssafy.io/images/question.png" alt="?" width="15px" height="15px" style="margin-top:1px;">
                  </el-tooltip>
                </dd>
              </dl>
            </div>
            <el-button :plain="true" @click="signup">가입하기</el-button>
          </div>
        </div>
      </div>
    </section>
	</transition>
</template>

<script>
import http from '../util/http-common'
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
    methods: {
      signup() {
        if(this.id==='')
          this.$message.warning('아이디를 입력해 주세요.')
        else if(!this.validId(this.id))
          this.$message.warning('아이디는 2자이상 영문과 숫자만 포함할 수 있습니다.')
        else if(this.password==='')
          this.$message.warning('비밀번호를 입력해 주세요.')
        else if(this.confirm==='')
          this.$message.warning('비밀번호를 한번 더 입력해 주세요.')
        else if(this.password!==this.confirm)
          this.$message.error('비밀번호가 일치하지 않습니다.')
        else if(this.name==='')
          this.$message.warning('이름을 입력해 주세요.')
        else if(this.email==='')
          this.$message.warning('이메일을 입력해주세요.')
        else if (!this.validEmail(this.email))
          this.$message.error("이메일 형식을 확인해 주세요")
        else{
          http
          .post('/user', {
            id: this.id,
            password: this.password,
            name: this.name,
            nickname: this.nickname,
            email: this.email,
            tel: this.tel,
            birthday: this.date_to_str(this.birth),
            github_url: this.url,
            profile_img_url: this.imageUrl
          })
          .then(({ data }) => {
            this.$message({
              type: 'success',
              message: '회원 가입이 완료되었습니다.'
            });
            this.$router.push('/')
          })
          .catch((error) => {
            console.log(error.response.status)
            if(error.response.status=='404'){
              this.$message({
                type: 'warning',
                message: '이미 존재하는 아이디입니다.'
              });
            }
          })
        }
      },
      validId: function(id) {
          return /^[a-z0-9]{2,20}$/.test(id)
      },

      validEmail: function(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);

      },
      date_to_str(format){
        if(format!==''){
        var year = format.getFullYear();
        var month = format.getMonth() + 1;
        if(month<10) month = '0' + month;
        var date = format.getDate();
        if(date<10) date = '0' + date;
        return year + "-" + month + "-" + date}
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = 'http://'.concat(res)
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
        const isLt10M = file.size / 1024 / 1024 < 10;

        if (!(isPNG || isJPG)) {
          this.$message.error('Image must be JPG or PNG format!1');
        }
        if (!isLt10M) {
          this.$message.error('Image size can not exceed 10MB!');
        }
        return (isJPG || isPNG) && isLt10M;
      },
    },
  }

</script>

<style>
.content{
  padding-top:110px;
}
.avatar-uploader2 .el-upload {
  top: 90px;
  left: 500px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: absolute;
  overflow: hidden;
}
.avatar-uploader2 .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 168px;
  height: 168px;
  line-height: 34px;
  text-align: center;
}
.avatar {
    width: 168px;
    height: 168px;
  }
</style>
