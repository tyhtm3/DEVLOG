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
							<div class="col-xs-12 col-sm-12">
								<div style="text-align: center"><h2>포트폴리오 작성 페이지</h2></div>
								<hr>
								<dl class="dl-horizontal-profile">
									<dt>이름</dt>
                  <dd>
                    <el-input v-model="name" style="width: 40%;"></el-input>
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
                  <dt>연락처</dt>
                  <dd><el-input v-model="tel" style="width: 40%;"></el-input></dd>
                  <dt>이메일</dt>
									<dd><el-input v-model="email" style="width: 40%;"></el-input></dd>
                  <dt>GIT 주소</dt>
									<dd>
                    <el-input v-model="url" style="width: 70%;">
                      <template slot="prepend">https://</template>
                    </el-input>
                  </dd>
								</dl>
                <el-transfer
                  :titles="['내 프로젝트', '선택된 프로젝트']"
                  filterable
                  :filter-method="filterMethod"
                  filter-placeholder="프로젝트 검색"
                  v-model="value"
                  :data="data">
                </el-transfer>
            	</div>
              <el-button :plain="true" @click="modify">수정</el-button>
              <el-button :plain="true" @click="signout">탈퇴</el-button>
						</div>
            
                <div>
                {{projectList}}
                </div>
          </div>
          <!--/myCarousel-->
        </div>
      <!--/well-->
      </section>
    </div>
	</transition>
</template>

<script>
import http from '../util/http-common'
import { mapState } from 'vuex'
export default {
  components: {
  },
  computed: {
    ...mapState(['userInfo']),
  },
  data: () => {
    // var projectList = ['서울','부산'];
    var generateData = _input => {
      var data = [];
      var states = ['California', 'Illinois', 'Maryland', 'Texas', 'Florida', 'Colorado', 'Connecticut '];
      var initials = ['CA', 'IL', 'MD', 'TX', 'FL', 'CO', 'CT'];
      var projectList = ['서울','부산'];
      // var projectList = [];
      // alert(_input);
      http
      .post('project/blog', { seq_user:_input , seq_blog:_input, offset:0, limit:100} )
      .then(({ data }) => {
          projectList = data;
          alert("안에서" + projectList);
      })
      projectList.push('gogo');
      alert("나와서" + projectList);
      states.forEach((city, index) => {
        data.push({
          label: city,
          key: index,
          initial: initials[index]
        });
      });
      return data;
    };

    return {
      seq: '',
      name: '',
      email: '',
      tel: '',
      birth: '',
      giturl: '',
      imageUrl: '',
      // projects:['서울'],
      // projectList: ['서울'],
      // data:generateData('4'),
      data: generateData(4),
      value: [],
      filterMethod(query, item) {
        return item.initial.toLowerCase().indexOf(query.toLowerCase()) > -1;
      },
    }
  },
  created() {
    this.$store.state.loginFormVisible = false;
    // this.generateData();
    // this.getProjectInfo();
  },
  mounted() {
    this.seq = this.userInfo.seq,
    this.name = this.userInfo.name,
    this.email = this.userInfo.email,
    this.tel = this.userInfo.tel,
    this.birth = this.userInfo.birthday,
    this.giturl = this.userInfo.github_url,
    this.imageUrl = this.userInfo.profile_img_url
  },
  methods: {
    // getProjectInfo(){
    //   http
    //   .post('project/blog', { seq_user:this.userInfo.seq , seq_blog:this.userInfo.seq, offset:0, limit:100} )
    //   .then(({ data }) => {
    //       this.projectList = data;
    //       // alert(this.projectList);
    //   })
    // },
    // generateData(){
    //   alert("들어옴")
    //   http.post('project/blog', { seq_user:4, seq_blog:4, offset:0, limit:6 } )
    //   .then(({ data }) => {
    //       this.projectList = data;
    //       alert(data)
    //   })  
    // },
    
    generateData(){
      let data = [];
      let projectSeq = ['CA'];
      let temp=[];
      // let projects=['California'];  
      http
      .post('project/blog', { seq_user:4 , seq_blog:4, offset:0, limit:100} )
      .then(({ data }) => {
          temp = data;
          // alert(temp[0].seq);
          alert(data.size);
      })
      projects.forEach((project, index) => {
        data.push({
          label: project,
          key: index,
          initial: projectSeq[index]
        });
      });
      return data;
    },
    modify() {
      this.$store.dispatch('modify', {
      password: this.password,
      name: this.name,
      nickname: this.nickname,
      email: this.email,
      tel: this.tel,
      birth: this.birth,
      url: this.url,
      imageUrl: this.imageUrl
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
          this.imageUrl = 'http://'.concat(data)
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
  }
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
