<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <!-- start banner carousel -->
      <div class="header-block">
        <div class="header-image">
        <img src="../assets/project-banner.jpg" width="100%" style=" max-height: initial;margin-top: -15%;"/>
        </div>
        <div class="header-text"><p>{{project.title}}</p></div>
      </div>
      <!-- end banner carousel -->

    
      <!-- 헤더 : 프로젝트 작성시간, 댓글수, 좋아요 수, 수정|삭제 -->
      <ul class="list-inline blog-devin-tag" style="padding-left:300px;padding-right:300px;font-size:13px;">
        <li><a>&nbsp;&nbsp;<span class="ti-user"></span>{{projectUser.nickname}}</a></li>
        <li><a> <span class="ti-pencil"></span>&nbsp;{{project.regtime}}</a></li>
        <li><a> <span class="ti-comment-alt"></span>&nbsp;{{comment.length}}</a></li>
        <li><a> <span class="ti-heart"></span>&nbsp;{{project.like_count}}</a></li>
        <li class="pull-right" v-if="project.seq_blog==seq_user"><a> &nbsp;수정</a><a > &nbsp; | </a><a href="#" @click="deleteProject(project.seq)"> &nbsp;삭제</a></li>
      </ul>
      <!-- 헤더 끝 -->               
      <div class="box">
        <div class="row">
          <div class="col-sm-12">
            <div class="blog-list-nest">
              <div class="blog-list-content">
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
            <!-- 댓글 입력 창 -->
            <div class="comment-nest">
              <!-- 어떻게 사이즈를 줄이는지 전혀 모르겠다 -->
              <vue-editor v-model="commentContent" style="display:inline-block;max-height:200px;"></vue-editor>
              <br>
              <br>
              <el-button class="pull-right" @click="insertComment">Submit</el-button>
              <div style="margin-bottom:70px;"></div>
              <!-- 댓글 리스트 -->
              <div v-for="(comment,index) in comment" :key="index">
                <ul class="media-list">
                  <li class="media">
                    <a class="pull-left" href="#"> <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" src="http://api.randomuser.me/portraits/thumb/women/21.jpg" style="width: 64px; height: 64px;"> </a>
                    <div class="media-body">
                      <div class="social-profile">
                        <h3> <a class="link-comment" href="#">{{commentUser[index].nickname}}</a>
                          <span style="font-size:12px;"><i class="entypo-globe"></i>&nbsp;{{comment.regtime}}</span>
                          <span v-if="commentUser[index].seq==seq_user">
                            <span style="font-size:14px;"><a class="link-comment pull-right" href="#" @click="deleteComment(comment.seq)"><i class="fontawesome-share"></i>&nbsp;삭제</a></span>
                            <span style="font-size:14px;"><a class="link-comment pull-right"><i class="fontawesome-share"></i>&nbsp; | </a></span>
                            <span style="font-size:14px;"><a class="link-comment pull-right" href="#"><i class="fontawesome-share"></i>&nbsp;수정</a></span>
                          </span>
                        </h3>
                      </div>
                      <p>{{ removeTag(comment.content) }}</p>
                    </div>
                    <br>
                  </li>
                </ul>
                <hr>
              </div>
            </div>
            <!-- 댓글 창 끝 -->

            <ul class="pager success">
              <li class="previous"><a href="#">← Older</a> </li>
              <li class="next disabled"><a href="#">Newer →</a> </li>
            </ul>
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
          project:'',
          projectUser:'',
          comment:'',
          commentUser:[], 
          tag: [],
          stack: [],
          seq_user: this.$store.state.userInfo.seq,
          // 댓글 작성,수정,삭제
          commentContent: '',

        }
    },
    created(){
      this.getInfo(this.$route.params.seq)
    },
    methods: {
      getInfo(seq){
        // 프로젝트 불러오기.
        http.get('project/'+seq)
        .then(({data}) => {
            this.project=data
            // 프로젝트 작성자 정보 불러오기.
            http.get('user/'+data.seq_blog)
            .then(({data}) => {
              this.projectUser=data
            }) 
         })
        // 댓글 불러오기
        this.getComment(seq)
        // 태그 불러오기
         http.get('posttag/'+seq)
                .then(({data}) => {
                this.tag = data;
         })
        // 스택 불러오기
        http.get('projectstack/'+seq)
                .then(({data}) => {
                this.stack = data;
         })
      },
      // 프로젝트 삭제
      deleteProject(seq){
        http.delete('project/'+seq)
        .then(({data}) => {
            if(data==="SUCCESS"){
              this.$message.success('프로젝트가 삭제되었습니다.')
              this.$router.push(`/blog`)
            }
         })
      },
      // 프로젝트 수정 미구현
      updateProject(){
        
      },
      // 댓글 리스트 불러오기
       getComment(seq){
        this.commentUser = []
        http.get('postcomment/'+seq)
        .then(({data}) => {
            // 댓글을 작성한 사용자 정보를 불러오기
            for(var i=0; i<data.length; i++){
                http.get('user/'+data[i].seq_user)
                .then(({data}) => {
                    this.commentUser.push(data);
                });
            }
            this.comment=data
         })
      },
      // 댓글 입력
      insertComment(){
         http.post('postcomment',{content:this.commentContent,seq_post:this.project.seq,seq_user:this.seq_user})
                .then(({data}) => {
              //댓글 입력하고 리스트 업데이트
               this.getComment(this.project.seq)
         })
      },
      // 댓글 삭제
      deleteComment(seq){
         http.delete('postcomment/'+seq)
                .then(({data}) => {
              //댓글 삭제하고 리스트 업데이트
               this.getComment(this.project.seq)
         })
      },
      // 댓글 수정 미구현
      updateComment(){

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
  max-height: 500px;
  overflow: hidden;
  position: relative;
}
.header-image{
  	vertical-align: middle;
}
.header-text{
  text-align: center;
  position: absolute;
  top: 53%;
  left: 50%;
  transform: translate( -50%, -50% );
  color:white;
  font-size:80px;
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
</style>