<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <br><br><br><br>
      <!-- <div>
        <br>{{basicinfo.seq}}: 71,
        <br>{{basicinfo.seq_blog}}: 4,
        <br>{{basicinfo.title}}: "삼성 제출 포트폴리오",
        <br>{{basicinfo.regtime}}: "2020-07-31 02:45:10",
        <br>{{basicinfo.disclosure}}: 1,
        <br>{{basicinfo.like_count}}: 101,
        <br>{{basicinfo.img_url}}: "",
        <br>{{basicinfo.name}}: "손명지",
        <br>{{basicinfo.profile_img_url}}: null,
        <br>{{basicinfo.github_url}}: "github.com",
        <br>{{basicinfo.birthday}}: "1994-04-03",
        <br>{{basicinfo.content}}: "입력예정" 
        <br><br><br>
        <br>{{portfolioUser.seq}}: 4,
        <br>{{portfolioUser.id}}: "ssafy",
        <br>{{portfolioUser.password}}: "ssafy",
        <br>{{portfolioUser.email}}: "ssafy@gmail.com",
        <br>{{portfolioUser.nickname}}: "명묭",
        <br>{{portfolioUser.name}}: "손명지",
        <br>{{portfolioUser.tel}}: "01040393449",
        <br>{{portfolioUser.profile_img_url}}: "static/img/profile.png",
        <br>{{portfolioUser.github_url}}: "github.com",
        <br>{{portfolioUser.birthday}}: null
      </div>   -->
       <!-- start banner carousel -->
      <div class="header-block">
         <!-- <ul class="list-inline blog-devin-tag" style="padding-left:300px;padding-right:300px;font-size:13px;">
              <li><a>&nbsp;&nbsp;<span class="ti-user"></span>닉네임{{portfolioUser.nickname}}</a></li>
              <li><a> <span class="ti-pencil"></span>&nbsp;{{basicinfo.regtime}}</a></li>
              <li><a> <span class="ti-comment-alt"></span>&nbsp;{{comment.length}}</a></li>
              <li><a> <span class="ti-heart"></span>&nbsp;{{project.like_count}}</a></li>
              <li class="pull-right" v-if="project.seq_blog==seq_user"><a> &nbsp;수정</a><a > &nbsp; | </a><a href="#" @click="deleteProject(project.seq)"> &nbsp;삭제</a></li>
          </ul> -->
          <ul class="list-inline blog-devin-tag" style="padding-left:300px;padding-right:300px;font-size:13px;">
              <li><a></a></li>
              <li class="pull-right" v-if="basicinfo.seq_blog==seq_user"><span class="ti-pencil"></span>&nbsp;{{basicinfo.regtime}} &nbsp; | <a href="#"> &nbsp;수정</a><a > &nbsp; | </a><a href="#" @click="deleteProject(project.seq)"> &nbsp;삭제</a></li>
          </ul>
          <div class="header-image">
          <img v-if="true" style="block:inline;" src="http://api.randomuser.me/portraits/women/22.jpg" class="avatar">
          <img v-else src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" class="avatar">
          </div>
          <div class="header-text" >
            <p style="font-size:70px;"><b>{{basicinfo.name}}</b></p>
            <i class="ti-mobile" style="font-size:40px;"/> {{portfolioUser.tel}}<br>
            <i class="ti-email" style="font-size:40px;"/> {{portfolioUser.email}}<br>
            <i class="ti-link" style="font-size:40px;"/> {{portfolioUser.github_url}}<br>
          </div>
      </div>
      <!-- end banner carousel -->

              
              <!-- 헤더 : 프로젝트 작성시간, 댓글수, 좋아요 수, 수정|삭제 -->
                    <!-- <ul class="list-inline blog-devin-tag" style="padding-left:300px;padding-right:300px;font-size:13px;">
                        <li><a>&nbsp;&nbsp;<span class="ti-user"></span>{{projectUser.nickname}}</a></li>
                        <li><a> <span class="ti-pencil"></span>&nbsp;{{project.regtime}}</a></li>
                        <li><a> <span class="ti-comment-alt"></span>&nbsp;{{comment.length}}</a></li>
                        <li><a> <span class="ti-heart"></span>&nbsp;{{project.like_count}}</a></li>
                        <li class="pull-right" v-if="project.seq_blog==seq_user"><a> &nbsp;수정</a><a > &nbsp; | </a><a href="#" @click="deleteProject(project.seq)"> &nbsp;삭제</a></li>
                    </ul> -->
              <!-- 헤더 끝 -->               
                <div class="box">
                  <div class="row">
                    <div class="col-sm-12">
                      <div class="blog-list-nest">
                        <div v-for="(project,index) in projects" v-bind:key="index" class="blog-list-content">

                            <!-- 프로젝트 썸네일 -->
                            <div class="pull-right" style="width:22%;margin-top:-2px;">
                            <img class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                            </div>
                            <!-- 프로젝트 정보 :  프로젝트 명 / 프로젝트 개요 / 개발기간 / 기술스택 / 역할 / URL / 설명-->
                                
                            <div class="row pjt-margin" style="margin-top:100px;">
                              <div class="col-sm-3">
                              <p class="pjt-title">프로젝트 제목</p>
                              </div>
                              <div class="col-sm-6">
                              <p class="pjt-content" style="font-size:20px;">{{project.title}}</p>
                              </div>
                            </div>
                            <div class="row pjt-margin">
                              <div class="col-sm-3">
                              <p class="pjt-title">프로젝트 개요</p>
                              </div>
                              <div class="col-sm-9">
                              <p class="pjt-content">{{project.summary}}</p>
                              </div>
                            </div>
                            <div class="row pjt-margin">
                              <div class="col-sm-3">
                              <p class="pjt-title">개발 기간</p>
                              </div>
                              <div class="col-sm-9">
                              <p class="pjt-content" style="font-size:20px;">{{project.start_date}} ~ {{project.finish_date}}</p>
                              </div>
                            </div>

                            <div class="row pjt-margin">
                              <div class="col-sm-3">
                              <p class="pjt-title">기술 스택</p>
                              </div>
                              <div class="col-sm-9">
                              <div v-for="(stack,index) in stack" :key="index">
                                <img class="media-object img-circle pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="width: 64px; height: 64px;margin-right:20px;">
                              </div>
                              </div>
                            </div>

                            <div class="row pjt-margin">
                              <div class="col-sm-3">
                              <p class="pjt-title">역할</p>
                              </div>
                              <div class="col-sm-9">
                              <p class="pjt-content">{{project.role}}</p>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-sm-3">
                              <p class="pjt-title">Github Url</p>
                              </div>
                              <div class="col-sm-9">
                              <p class="pjt-content"><a href="#" @click="goUrl(project.github_url)">{{project.github_url}}</a></p>
                              </div>
                            </div>
                            <div class="row" v-if="project.etc_url">
                              <div class="col-sm-3">
                              <p class="pjt-title">참고 Url</p>
                              </div>
                              <div class="col-sm-9">
                              <p class="pjt-content">{{project.github_url}}</p>
                              </div>
                            </div>
                            <div class="row" v-if="project.rep_url">
                              <div class="col-sm-3">
                              <p class="pjt-title">참조 Url</p>
                              </div>
                              <div class="col-sm-9">
                              <p class="pjt-content">{{project.rep_url}}</p>
                              </div>
                            </div>

                            <p style="margin-top:50px;margin-bottom:50px;" v-html="project.content"></p><hr>  

                            <!--  프로젝트 정보 끝 -->
                            <!-- 프로젝트 태그 -->
                            <p class="pull-left">
                              <span v-for="(tag, index) in tag" v-bind:key="index" class="tag">
                              #{{tag.tag}}
                              </span>
                            </p>
                            <div style="clear:both;"></div>
                        </div>
                      </div>
                    </div>
                      <!--  END OF BLOG CONTENT -->
                  </div>
                </div>
            <!-- /.content -->
    </div>
  </transition>
</template>
<script>
  import http from '../util/http-common'
  import { VueEditor } from 'vue2-editor'
  export default {
    components: {
      VueEditor
    },
    data: function () {
        return { 
          basicinfo:'',
          portfolioUser:'',
          projects:[],
          tag: [],
          stack: [],
          seq_user: this.$store.state.userInfo.seq,
        }
    },
    created(){
      this.getBasicInfo(this.$route.params.seq);
      this.getPortfolioInfo(this.$route.params.seq);
    },
    methods: {
      getBasicInfo(seq){
        http.get('portfolio/'+seq)
        .then(({data}) => {
            this.basicinfo=data
            // 프로젝트 작성자 정보 불러오기.
            http.get('user/'+data.seq_blog)
            .then(({data}) => {
              this.portfolioUser=data
              // alert(data.seq);
            }) 
         })
      },
      getPortfolioInfo(seq){
        // 프로젝트 불러오기.
        http.get('portfoliopjt/'+seq)
        .then(({data}) => {
            this.projects=data
         })
      },
      // 프로젝트 삭제
      deleteProject(seq){
        http.delete('portfolio/'+seq)
        .then(({data}) => {
            alert('프로젝트가 삭제되었습니다.')
            this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
         })
      },
      // 프로젝트 수정 미구현
      updateProject(){
        
      },
      // Url로 이동
      goUrl(url){
        window.open(url, '_blank');
      },
       removeTag(text){
      text = text.replace(/<br\/>/ig, "\n")
      text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")
      // text = text.replace(/<(\/b|b)([^>]*)>/gi,""); 
      return text
    },
   },
  }
</script>
<style scoped>

a:link { color: #B1B0AC; text-decoration: none;}
a:visited { color: #B1B0AC;; text-decoration: none;}
a:hover { color: black; text-decoration: bold;}
.header-block{
  height: 500px;
  overflow: hidden;
  position: relative;
  background-color: #F0F0ED;
}
.header-image{
  float:left; 
  text-align: center;
  position: absolute;
  top: 50%;
  left: 20%;
  transform: translate( 0%, -50% );
  color:black;
  font-size:80px;
}
.header-text{
  float:right; 
  margin-left:100px;
  text-align: left;
  position: absolute;
  top: 50%;
  left: 40%;
  transform: translate( 0%, -50% );
  color:black;
  font-size:40px;
}
.box{
   padding-left:300px;
   padding-right:300px;
}
.pjt-margin{
  margin-bottom:60px;

}
.pjt-title{
  font-size:18px;
}
.pjt-content{
  margin-top:-2px;
  font-size:14px;
  word-spacing: 2px;
  line-height:30px;
}
.avatar{
  width:250%;
  height:250%;
}
</style>