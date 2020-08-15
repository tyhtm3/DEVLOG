<template>
  <transition name="el-zoom-in-top">
    <!-- <div class="content-wrapper"> -->
      <!-- <section class="content"> -->
        <div class="box">
          <div class="box-body" style="align:center; min-height:400px; max-width:800px; margin:auto; padding : 0px;">
						<div class="col-sm-12" style="padding:0px">
							<div class="col-xs-12 col-sm-12" style="padding:0px">
                <hr>
                  <div style="margin-bottom:15px; font-size:20px;"><b>개인정보 입력</b></div>
                  <!-- <div style="margin-bottom:15px; font-size:20px; text-align:center"><b>개인정보 입력</b></div> -->
                  <hr>
								<dl class="dl-horizontal-profile">
									<dt>이름</dt>
                  <dd>
                    <el-input v-model="name" style="width: 50%; border: 0px"></el-input>
                    <el-tooltip class="item" effect="dark" content="사진 비율 : 3x4" placement="right">
                    <el-upload
                    class="avatar-uploader"
                    action="http://i3a402.p.ssafy.io:8090/devlog/api/user/upload"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                    </el-tooltip>
                  </dd>
                  <dt>연락처</dt>
                  <dd><el-input v-model="tel" style="width: 50%;"></el-input></dd>
                  <dt>이메일</dt>
									<dd><el-input v-model="email" style="width: 50%;"></el-input></dd>
                  <dt>GIT 주소</dt>
									<dd>
                    <el-input v-model="giturl" style="width: 50%;">
                      <template slot="prepend">https://</template>
                    </el-input>
                  </dd>
								</dl>
                </div>
                 <div class="col-xs-12 col-sm-12" style="padding:0px">
                  <hr>
                  <div style="margin-bottom:15px; font-size:20px"><b>프로젝트 정보 입력</b></div>
                  <!-- <div style="margin-bottom:15px; font-size:20px; text-align:center"><b>프로젝트 정보 입력</b></div> -->
                  <hr>
                </div>
                <div>
                <!-- 왼쪽 프로젝트 선택 부분 -->
                <!-- <div><p id="titleid">프로젝트 선택</p></div> -->
                <section class="selectProject">
                  <el-transfer
                    style="margin-bottom: 30px"
                    :titles="['내 프로젝트', '선택된 프로젝트']"
                    filterable
                    :filter-method="filterMethod"
                    filter-placeholder="프로젝트 검색"
                    v-model="includedProject"
                    :button-texts="['','']"
                    :data="projectList">
                  </el-transfer>
                </section>
                <!-- 왼쪽 프로젝트 선택 부분 끝-->
                <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 -->
                <!-- {{projectInfoList}}<br><br>
                프로젝트 사이즈 : {{projectInfoList.length}}<br><br> -->
                <section v-if="projectInfoList.length>0" class="showProject">
                  <div class="tocenter" style="padding: 10px">
                    <div style="margin-bottom:15px; font-size:15px"><b>프로젝트 정보</b></div>
                    <div class="row pjt-margin">
                      <div class="col-sm-4">
                      <p>제목</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">{{projectInfoList[0].title}}</p>
                      </div>
                    </div>
                    <div class="row pjt-margin">
                      <div class="col-sm-4">
                      <p>개요</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">{{projectInfoList[0].summary}}</p>
                      </div>
                    </div>
                    <div class="row pjt-margin">
                      <div class="col-sm-4">
                      <p>기간</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">{{projectInfoList[0].start_date}} ~ <br>{{projectInfoList[0].finish_date}}</p>
                      </div>
                    </div>
                    <div class="row pjt-margin">
                      <div class="col-sm-4">
                      <p>스택</p>
                      </div>
                      <div class="col-sm-8">
                      <div v-for="(stack,index) in stack" :key="index">
                        <img class="media-object img-circle pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="width: 64px; height: 64px;margin-right:20px;">
                      </div>
                      </div>
                    </div>
                    <div class="row pjt-margin">
                      <div class="col-sm-4">
                      <p>역할</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">{{projectInfoList[0].role}}</p>
                      </div>
                    </div>
                    <div class="row" v-if="projectInfoList[0].github_url">
                      <div class="col-sm-4">
                      <p>Git</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content"><a href="#" @click="goUrl(projectInfoList[0].github_url)">{{projectInfoList[0].github_url}}</a></p>
                      </div>
                    </div>
                    <div class="row" v-if="projectInfoList[0].etc_url">
                      <div class="col-sm-4">
                      <p>기타 Url</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">{{projectInfoList[0].etc_url}}</p>
                      </div>
                    </div>
                    <div class="row" v-if="projectInfoList[0].rep_url">
                      <div class="col-sm-4">
                      <p>참고 Url</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">{{projectInfoList[0].rep_url}}</p>
                      </div>
                    </div>
                  </div>
                </section>
                <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 끝-->
                </div>
                 <div class="col-xs-12 col-sm-12" style="padding:0px">
                  <hr>
                  <div style="margin-bottom:15px; font-size:20px"><b>기술스택 정보 입력</b></div>
                  <!-- <div style="margin-bottom:15px; font-size:20px; text-align:center"><b>기술스택 정보 입력</b></div> -->
                  <hr>
                </div>
                <div>
                <!-- <div><p id="titleid">기술스택 선택</p></div> -->
                <div class="selectStacks">
                <!-- 왼쪽 기술스택 선택 부분 -->
                <el-transfer
                  :titles="['내 기술스택', '선택된 기술스택']"
                  filterable
                  :filter-method="filterMethod"
                  filter-placeholder="기술스택 검색"
                  v-model="includedStack"
                  :button-texts="['','']"
                  :data="stackList">
                </el-transfer>
                <!-- 왼쪽 기술스택 선택 부분 끝-->
                </div>
                <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 -->
                <!-- {{projectInfoList}}<br><br>
                프로젝트 사이즈 : {{projectInfoList.length}}<br><br> -->
                <div v-if="stackInfoList.length>0" class="showProject">
                  <div class="tocenter" style="padding: 10px">
                    <div style="margin-bottom:15px; font-size:15px"><b>기술스택 정보</b></div>
                    <div style="margin-bottom:15px; text-align:center"><img :src=stackInfoList[0].stack_img_url width="200px"/></div>
                    <div class="row pjt-margin">
                      <div class="col-sm-4">
                      <p>기술스택</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">{{stackInfoList[0].stack}}</p>
                      </div>
                    </div>
                    <div class="row pjt-margin">
                      <a href="#">
                      <div class="col-sm-4">
                      <p>활용 1)</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">abc프로젝트</p>
                      </div>
                      </a>
                    </div>
                    <div class="row pjt-margin">
                      <a href="#">
                      <div class="col-sm-4">
                      <p>활용 2)</p>
                      </div>
                      <div class="col-sm-8">
                      <p class="pjt-content">def프로젝트</p>
                      </div>
                      </a>
                    </div>
                  </div>
                </div>
                <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 끝-->
              </div>
              <hr>
              <br>
              <div class="col-xs-12 col-sm-12" style="padding:0px">
                <hr>
                <div style="margin-bottom:15px; font-size:20px"><b>포트폴리오 정보 입력</b></div>
                <!-- <div style="margin-bottom:15px; font-size:20px; text-align:center"><b>포트폴리오 정보 입력</b></div> -->
                <hr>
								<dl class="dl-horizontal-profile">
                  <dt>TITLE</dt>
                  <dd><el-input v-model="portfolioTitle" style="width: 70%;"></el-input></dd>
                  <dt>CONTENT</dt>
									<dd><el-input v-model="portfolioContent" style="width: 70%;"></el-input></dd>
                  <dt>공개 여부</dt>
                  
                  <dd>
                    <el-radio-group v-model="portfolioDisclosure">
                      <el-radio-button label="전체공개"></el-radio-button>
                      <el-radio-button label="이웃공개"></el-radio-button>
                      <el-radio-button label="비공개" ></el-radio-button>
                    </el-radio-group>
                  </dd>
								</dl>
                <hr>
              </div>
              <el-button @click="makePortfolio" style="float:right; margin-bottom: 20px">포트폴리오 생성</el-button>

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
import projectListVue from './projectList.vue'
export default {
  components: {
  },
  computed: {
    ...mapState(['userInfo']),
  },
  data: () => {
    return {
      // 포트폴리오 기본정보
      portfolioContent:'',
      portfolioTitle:'',
      portfolioSummary:'summary',
      // portfolioRole:'',
      portfolioDisclosure: '',
      portfolioSeq:'',
      // 개인정보
      seq: '',
      name: '',
      email: '',
      tel: '',
      birth: '',
      giturl: '',
      imageUrl: '',
      temp:[],
      // 플젝정보
      projectList: [],
      includedProject: [],
      projectInfoList:[],
      // 스택정보
      stackList: [],
      includedStack: [],
      stackInfoList:[],
    }
  },
  created() {
    this.$store.state.loginFormVisible = false;
    this.getProjectList(this.userInfo.seq);
    this.getStackList(this.userInfo.seq);
  },
  // watch(){
  // },
  mounted() {
    this.seq = this.userInfo.seq,
    this.name = this.userInfo.name,
    this.email = this.userInfo.email,
    this.tel = this.userInfo.tel,
    this.birth = this.userInfo.birthday,
    this.giturl = this.userInfo.github_url,
    this.imageUrl = this.userInfo.profile_img_url
    // ,$(".el-tab-pane").click(function(event){
    //   var targetElement = $(event.target);
    //   if(targetElement.is(".el-tab-pane")){
    //     alert("고침");
    //   }
    // });

    // divs.forEach(function(div) {
      //   div.addEventListener('click', logEvent, {
        //     capture: true // default 값은 false입니다.
    //   });
    // });

    // var spans = document.querySelectorAll('span');
    // spans.forEach(function(span) {
    //   span.addEventListener('click', logEvent);
    // });

    // function logEvent(event) {
    //   event.stopPropagation();
    //   console.log(event.currentTarget.className); // three
    // }


    //  ,$('.el-tab-pane').on('click', evt => {
    //   alert("클릭됨!!!");
    // })
    // var span = document.querySelector('.el-tab-pane');
    // span.addEventListener('click', alertspan);
    // function alertspan(event){
    //   // console.log(event);
    //   alert("된다");
    // }
  },
  methods: {
    filterMethod(query, item) {
        return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
    },
    getProjectList(seq){
      http
      .post('project/blog', { seq_user:seq , seq_blog:seq, offset:0, limit:99999} )
      .then(({ data }) => {
        this.projectInfoList=data;
        for(let i=0; i<data.length; i++){
          this.projectList.push({
          label: data[i].title,
          key:data[i].seq,
          initial: data[i].seq
          })
        }
      })
    },
    getStackList(seq){
      http
      .get('/stack')
      .then(({ data }) => {
        this.stackInfoList=data;
        for(let i=0; i<data.length; i++){
          this.stackList.push({
          label: data[i].stack,
          key:i,
          initial: data[i].seq
          })
        }
      })
    },
    makePortfolio() {
      // alert(this.includedStack);
      if(this.portfolioDisclosure === "전체공개")
        this.portfolioDisclosure = 1
      else if(this.portfolioDisclosure === "이웃공개")
        this.portfolioDisclosure = 2
      else
        this.portfolioDisclosure = 3
      // alert(this.includedProject);
      http
      .post('portfolio', {
        email: this.email,
        content: this.portfolioContent,
        disclosure: this.portfolioDisclosure,
        github_url: this.giturl,
        img_url: this.imageUrl,
        name: this.name,
        profile_img_url: this.imageUrl,
        seq_blog: this.seq,
        title: this.portfolioTitle
      })
      .then(({ data }) => {
        this.portfolioSeq=data;
      alert(this.imageUrl);
        http
        .post('portfoliopjt', {
          seq_post_portfolio: this.portfolioSeq,
          seq_post_project: this.includedProject
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })
        for(let i=0; i<this.includedStack.length; i++){
          http
          .post('projectstack', {
            seq_post_project: this.portfolioSeq,
            stack: this.stackInfoList[this.includedStack[i]].stack,
            stack_img_url: this.stackInfoList[this.includedStack[i]].stack_img_url,
          })
          .then(({ data }) => {
          })
          .catch((error)=>{
            console.log(error.response.status)
          })
        }
        this.$message({
            type: 'success',
            message: '포트폴리오 생성 완료.'
        });
        this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
      })
      .catch((error) => {
        console.log(error.response.status)
      })
    },
    
    validEmail: function(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    
    // 회원정보 수정전용
    handleAvatarSuccess(res, file) {
      this.imageUrl = 'http://'.concat(res)
    },
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt10M = file.size / 1024 / 1024 < 10;

        if (!isJPG) {
          this.$message.error('Image must be JPG format!');
        }
        if (!isLt10M) {
          this.$message.error('Image size can not exceed 10MB!');
        }
        return isJPG && isLt10M;
    },
  }
}
</script>

<style>
  .tocenter{
    vertical-align: middle;    
  }
  .avatar-uploader .el-upload {
    top: 120px;
    left: 75%;
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
    width: 100px;
    height: 150px;
    line-height: 200px;
    text-align: center;
  }
  .avatar {
    width: 150px;
    height: 200px;
  }
  .avatar-uploader-icon {
    transform: translate(0%, 40%);
  }
  .el-transfer{
    width: auto;
    float: left;
  }
  /* .nextToTransfer{
  } */
  /* .el-checkbox__label:hover{
    color: red;
  }
  */
  /* .el-transfer-panel__header{
    padding-left: 10px !important;
  }
  .el-transfer-panel__item{
    padding-left: 10px !important;
  } */
  .el-transfer__buttons{  
    padding: 0px 5px !important;
    /* background-color: green; */
  }
  botton.el-button.el-button--primary.is-disabled.el-transfer__button.is-with-texts{
    padding: 5px;
  }
  .showProject{
    /* display:block; */
    margin-left:20px;
    float: left;
    background-color: #d5cbe42a;
    width: 275px;
    min-height: 300px;
    border-radius: 6px;
  }
  .showStacks{
    /* display:block; */
    margin-left:20px;
    float: left;
    background-color: #d5cbe42a;
    width: 275px;
    min-height: 300px;
    border-radius: 6px;
  }
  .stacks{
    display:inline-block;
    margin-top:10px;
    margin-bottom:10px;
    min-width: 93%;
    min-height: 300px;
    /* background-color: #d5cbe42a; */
    border-radius: 6px;
  }
</style>