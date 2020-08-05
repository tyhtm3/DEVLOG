<template>
  <transition name="el-zoom-in-top">
    <!-- <div class="content-wrapper"> -->
      <!-- <section class="content"> -->
        <div class="box">
          <div class="box-body" style="align:center; min-height:400px; max-width:800px; margin:auto; padding : 0px;">
						<div class="col-sm-12" style="padding:0px">
							<div class="col-xs-12 col-sm-12" style="padding:0px">
								<dl class="dl-horizontal-profile">
									<dt>이름</dt>
                  <dd>
                    <el-input v-model="name" style="width: 40%; border: 0px"></el-input>
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
            {{projectList}}<br>
            둘다보이기<br>
            {{data}}
            </div>
          </div>
          <!--/myCarousel-->
        </div>
      <!--/well-->
      <!-- </section> -->
    <!-- </div> -->
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
    const generateData = _ => {
      const data = [];
      const states = ['California', 'Illinois', 'Maryland', 'Texas', 'Florida', 'Colorado', 'Connecticut '];
      const initials = ['CA', 'IL', 'MD', 'TX', 'FL', 'CO', 'CT'];
      // http
      // .post('project/blog', { seq_user:_input , seq_blog:_input, offset:0, limit:100} )
      // .then(({ data }) => {
      //   alert(data[0].title)
      //     // states.push('ABC');
      //     // alert("안에서 " + states);
      // })
      // alert("출력" + _input);
      alert("불러옴");
      states.forEach((city, index) => {
          data.push({
            label: city,
            key: index,
            initial: initials[index]
          });
      });
      return data;
    };
    // const fromDB = _input => {
    //   http
    //   .post('project/blog', { seq_user:_input , seq_blog:_input, offset:0, limit:100} )
    //   .then(({ data }) => {
    //   // alert(data[0].title);
    //   return data;
    //   })
    // };
    return {
      seq: '',
      name: '',
      email: '',
      tel: '',
      birth: '',
      giturl: '',
      imageUrl: '',
      data: generateData(),
      value: [],
      filterMethod(query, item) {
        return item.initial.toLowerCase().indexOf(query.toLowerCase()) > -1;
      },
      projectList: [],
    }
  },
  created() {
    this.$store.state.loginFormVisible = false;
    // this.generateData();
    // this.getProjectInfo();
    this.data.push(this.generateData()[0]);
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
    //   })
    // },
    generateData(){
      // alert("함수들어옴")
      http.post('project/blog', { seq_user:4, seq_blog:4, offset:0, limit:6 } )
      .then(({ data }) => {
          this.projectList = data;
          // alert(data)
      })  
      return data;
    },
    
    // generateData(){
    //   alert("들어옴")
    //   http.post('project/blog', { seq_user:4, seq_blog:4, offset:0, limit:6 } )
    //   .then(({ data }) => {
    //       this.projectList = data;
    //       alert(data)
    //   })  
    // },
    
    // generateData(){
    //   let data = [];
    //   let projectSeq = ['CA'];
    //   let temp=[];
    //   // let projects=['California'];  
    //   http
    //   .post('project/blog', { seq_user:4 , seq_blog:4, offset:0, limit:100} )
    //   .then(({ data }) => {
    //       temp = data;
    //       // alert(temp[0].seq);
    //       alert(data.size);
    //   })
    //   projects.forEach((project, index) => {
    //     data.push({
    //       label: project,
    //       key: index,
    //       initial: projectSeq[index]
    //     });
    //   });
    //   return data;
    // },
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
    top: 0px;
    left: 70%;
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