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
              <h2>정보 수정</h2>
              <hr>
              <dl class="dl-horizontal-profile">
                <dt>*아이디</dt>
                <dd>
                  <el-input v-model="id" style="width: 40%;" disabled></el-input>
                  <el-upload
                  class="avatar-uploader2"
                  action="http://i3a402.p.ssafy.io:8090/devlog/api/user/upload"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload">
                  <img v-if="profile_img_url" :src="profile_img_url" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </dd>
                <div v-if="isSocialLogin">
                <dt>*비밀번호</dt>
                <dd><el-input v-model="password" type="password" style="width: 40%;" readonly></el-input></dd>
                <dt>*비밀번호 확인</dt>
                <dd><el-input v-model="confirm" type="password" style="width: 40%;" readonly></el-input></dd>
                </div>
                <div v-else>
                <dt>*비밀번호</dt>
                <dd><el-input v-model="password" type="password" style="width: 40%;"></el-input></dd>
                <dt>*비밀번호 확인</dt>
                <dd><el-input v-model="confirm" type="password" style="width: 40%;"></el-input></dd>
                </div>
                <dt>*이름</dt>
                <dd><el-input v-model="name" style="width: 70%;"></el-input></dd>
                <dt>닉네임</dt>
                <dd><el-input v-model="nickname" style="width: 70%;"></el-input></dd>
                <dt>*이메일</dt>
                <dd><el-input v-model="email" style="width: 70%;"></el-input></dd>
                <dt>연락처</dt>
                <dd><el-input v-model="tel" style="width: 70%;"></el-input></dd>
                <dt>생년월일</dt>
                <dd><el-date-picker v-model="birthday" type="date"></el-date-picker></dd>
                <dt>url</dt>
                <dd>
                  <el-input v-model="github_url" style="width: 70%;">
                    <template slot="prepend">https://</template>
                  </el-input>
                </dd>
              </dl>
            </div>
            <el-button :plain="true" @click="modify">수정</el-button>
            <el-button :plain="true" @click="signout">탈퇴</el-button>
          </div>
        </div>
      </div>
    </section>
	</transition>
</template>

<script>
import http from '../util/http-common'
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
export default {
  components: {
  },
  computed: {
    ...mapGetters(['getUserInfo'])
  },
  data: () => {
    return {
      id: '',
      name: '',
      nickname: '',
      email:'',
      tel: '',
      birthday: '',
      github_url: '',
      profile_img_url: '',
      password: '',
      confirm: '',
      isSocialLogin: '',
    }
  },
  mounted() {
    this.isSocialLogin = (this.getUserInfo.id.indexOf('Naver')>0|| this.getUserInfo.id.indexOf('Kakao')>0)?true:false
    this.id=  this.getUserInfo.id
    this.name = this.getUserInfo.name
    this.nickname=  this.getUserInfo.nickname
    this.email=  this.getUserInfo.email
    this.tel=  this.getUserInfo.tel
    this.birthday=  this.getUserInfo.birthday
    this.birthday_temp=  this.getUserInfo.birthday
    this.github_url=  this.getUserInfo.github_url
    this.profile_img_url=  this.getUserInfo.profile_img_url
    if(this.isSocialLogin){
      this.password = this.getUserInfo.password
      this.confirm = this.getUserInfo.password
    }
  },
  methods: {
    modify() {
      if(this.password===''){
        this.$message({
          type: 'warning',
          message: '비밀번호를 입력해 주세요.'
        })
      }
      else if(this.confirm===''){
        this.$message({
          type: 'warning',
          message: '비밀번호를 한번 더 입력해 주세요.'
        })
      }
      else if(this.password!==this.confirm){
          this.$message({
          type: 'error',
          message: '비밀번호가 일치하지 않습니다.'
        })
      }
      else if(this.name===''){
          this.$message({
          type: 'warning',
          message: '이름을 입력해 주세요.'
        })
      }
      else if (!this.validEmail(this.email)){
          this.$message({
          type: 'error',
          message: '이메일 형식을 확인해 주세요.'
        })
      }
      else{
        http
        .put('/user', {
          id: this.id,
          password: this.password,
          name: this.name,
          nickname: this.nickname,
          email: this.email,
          tel: this.tel,
          birthday: this.birthday!=this.birthday_temp?this.date_to_str(this.birthday):this.birthday,
          github_url: this.github_url,
          profile_img_url: this.profile_img_url
        },{headers: { Authorization : this.$store.state.token,}})
        .then(({ data }) => {
          this.$store.commit('setUserInfo',
          {
          seq: this.$store.state.userInfo.seq,
          id: this.id,
          password: this.password,
          name: this.name,
          nickname: this.nickname,
          email: this.email,
          tel: this.tel,
          birthday: this.birthday,
          github_url: this.github_url,
          profile_img_url: this.profile_img_url
          }
          )
          this.$message({
            type: 'success',
            message: '정보가 수정되었습니다.'
          })
          this.$router.push('/')
        })
        .catch((error) => {
          this.$message({
            type: 'error',
            message: '정보 수정 도중 에러가 발생했습니다.'
          })
        })
      }
    },
    signout() {
      this
      .$confirm('탈퇴하시겠습니까?', {
          confirmButtonText: '탈퇴',
          cancelButtonText: '취소',
          type: 'warning'
      })
      .then(() => {
        http
        .delete('/user/'
        ,{headers: { Authorization : this.$store.state.token,}})
        .then(({ data }) => {
          this.$store.commit('setIsLogin', false)
          this.$store.commit('setUserInfo', {seq:0})
          this.$store.commit('setToken', null)
          this.$message({
            type: 'success',
            message: '탈퇴가 완료되었습니다.'
          });
          this.$router.push('/')
        }) 
      })
    },
    validEmail: function(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    handleAvatarSuccess(res, file) {
      this.profile_img_url = 'http://'.concat(res)
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
    date_to_str(format){
      if(format == null || format == "")
      return ""
      var year = format.getFullYear();
      var month = format.getMonth() + 1;
      if(month<10) month = '0' + month;
      var date = format.getDate();
      if(date<10) date = '0' + date;
      return year + "-" + month + "-" + date
    },
  },
}
</script>