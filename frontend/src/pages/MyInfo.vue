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
								<h2>정보 수정</h2>
								<hr>
								<dl class="dl-horizontal-profile">
									<dt>*아이디</dt>
                  <dd>
                    <el-input v-model="getUserInfo.id" style="width: 40%;" disabled></el-input>
                    <el-upload
                    class="avatar-uploader2"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img v-if="getUserInfo.profile_img_url" :src="getUserInfo.profile_img_url" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                  </dd>
                  <dt>*비밀번호</dt>
                  <dd><el-input v-model="password" type="password" style="width: 40%;"></el-input></dd>
									<dt>*비밀번호 확인</dt>
                  <dd><el-input v-model="confirm" type="password" style="width: 40%;"></el-input></dd>
									<dt>*이름</dt>
                  <dd><el-input v-model="getUserInfo.name" style="width: 70%;"></el-input></dd>
									<dt>닉네임</dt>
                  <dd><el-input v-model="getUserInfo.nickname" style="width: 70%;"></el-input></dd>
                  <dt>*이메일</dt>
									<dd><el-input v-model="getUserInfo.email" style="width: 70%;"></el-input></dd>
									<dt>연락처</dt>
                  <dd><el-input v-model="getUserInfo.tel" style="width: 70%;"></el-input></dd>
                  <dt>생년월일</dt>
                  <dd><el-date-picker v-model="getUserInfo.birthday" type="date"></el-date-picker></dd>
                  <dt>url</dt>
									<dd>
                    <el-input v-model="getUserInfo.github_url" style="width: 70%;">
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
    </div>
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
    ...mapGetters(['getUserInfo']),
    ...mapMutations(['setLoginFormVisible'])
  },
  data: () => {
    return {
      password: '',
      confirm: '',
      id: '',
      name: '',
      nickname: '',
      email:'',
      tel: '',
      birthday: '',
      github_url: '',
      profile_img_url: ''
    }
  },
  created() {
    this.$store.state.loginFormVisible = false;
    this.id=  this.userInfo.id
    this.name = this.userInfo.name
    this.nickname=  this.userInfo.nickname
    this.email=  this.userInfo.email
    this.tel=  this.userInfo.tel
    this.birthday=  this.userInfo.birthday
    this.github_url=  this.userInfo.github_url
    this.profile_img_url=  this.userInfo.profile_img_url
  },
  mounted() {
    this.$store.commit('setLoginFormVisible', false)
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
          birthday: this.birthday,
          github_url: this.github_url,
          profile_img_url: this.profile_img_url
        })
        .then(({ data }) => {
          // 
          this.$store.commit('mutateUserInfo', {
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
          })
          this.$message({
            type: 'success',
            message: '정보가 수정되었습니다.'
          })
          this.$router.push('/blog')
        })
        .catch((error) => {
          if(error.response.status=='404'){
            this.$message({
              type: 'error',
              message: '정보 수정 도중 발생했습니다.'
            })
          }
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
        .delete('/user/')
        .then(({ data }) => {
          this.$store.commit('mutateIsLogin', false)
          this.$message({
            type: 'success',
            message: '탈퇴가 완료되었습니다.'
          });
          this.$router.push(`/`)
        }) 
      })
    },
    validEmail: function(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    handleAvatarSuccess(res, file) {
      var frm = new FormData();
      frm.append("upload_file", file.raw);

      http.post('user/upload',frm,{headers: {
            'Content-Type': 'multipart/form-data'
          }})
        .then(({data}) => {
          this.profile_img_url = 'http://'.concat(data)
      })
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
