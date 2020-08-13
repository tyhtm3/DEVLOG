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
        <li><a :href="url" v-if="projectUser.nickname">&nbsp;&nbsp;<span class="ti-user"></span>{{projectUser.nickname}}</a>
            <a :href="url" v-else>&nbsp;&nbsp;<span class="ti-user"></span>{{projectUser.id}}</a>
        </li>
        <li><a> <span class="ti-pencil"></span>&nbsp;{{project.regtime}}</a></li>
        <li><a> <span class="ti-comment-alt"></span>&nbsp;{{commentCnt}}</a></li>
        <li>
          <i v-if="isLike" @click="cancelLike" class="material-icons">favorite</i>
          <i v-else @click="like" class="material-icons">favorite_border</i>
          &nbsp;{{project.like_count}}</li>
        <li class="pull-right" v-if="project.seq_blog==seq_user">
          <span style="cursor:pointer" @click="updateProject(project.seq)">수정 </span>&nbsp;|&nbsp;
          <span style="cursor:pointer" @click="deleteProject(project.seq)"> 삭제</span>
        </li>
      </ul>
      <!-- 헤더 끝 -->               
      <div class="box">
        <div class="row">
          <div class="col-sm-12">
            <div class="blog-list-nest">
              <div class="blog-list-content">
                <!-- 프로젝트 썸네일 -->
                <div class="pull-right" style="width:22%;margin-top:-2px;">
                  <img v-if="project.img_url" class="img-responsive-media" :src="project.img_url" alt="">
                  <img v-else class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
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
                    <div v-for="(role,index) in role" :key="index">
                      <p class="pjt-content">{{role.role}}</p>  
                    </div>
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
                  <span @click="tagSearch(tag.tag)" v-for="(tag, index) in tag" v-bind:key="index" class="tag">
                    #{{tag.tag}}
                  </span>
                </p>

                <div style="clear:both;"></div>
              </div>
            </div>
          
           <!-- 댓글 리스트 -->
              <comment v-bind:seq="seq"></comment>
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
  import Comment from '../components/detailComment'
  import http from '../util/http-common'
  export default {
    components: {
      Comment
    },
    data: function () {
        return { 
          seq:'',
          project:{},
          projectUser:'',
          isLike:'',
          commentCnt:'',
          tag: [],
          stack: [],
          role: [],
          seq_user: this.$store.state.userInfo.seq,
          basicurl: '/blog/',
          blogurl:'',
          url:'',
        }
    },
    created(){
      this.seq= this.$route.params.seq
      this.getInfo(this.seq)
    },
    methods: {
      tagSearch(tag){
        this.$store.commit('setSearchTag',tag)
        if(this.$store.state.previousUrl.indexOf('blog')>0){
            this.$router.push('/blog/'+this.blogurl)
        }else{
            this.$router.push('/')
        }
      },
      getInfo(seq){
        // 프로젝트 불러오기.
        http.get('project/'+seq)
        .then(({data}) => {
            this.project=data
            // 프로젝트 작성자 정보 불러오기.
            http.get('user/'+data.seq_blog)
            .then(({data}) => {
              this.projectUser=data
              this.blogurl = this.projectUser.id
              this.url=this.basicurl+this.blogurl
            }) 
         })
        // 댓글 개수 불러오기
         http.get('postcomment/count/'+seq)
                .then(({data}) => {
                this.commentCnt = data;
         })
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
        // 역할 불러오기
         http.get('projectrole/'+seq)
                .then(({data}) => {
                this.role = data;
         })
        // 좋아요 여부 불러오기
        http.get('postlike/'+seq)
                .then(({data}) => {
                if(data.length==0){
                  this.isLike=false
                }else{
                  this.isLike=true
                }
         })
      },
      // 좋아요
      like(){
        http.post('postlike/',{seq_post:this.seq})
                .then(({data}) => {
                this.project.like_count+=1
                this.isLike=true
         })
      },
      // 좋아요 취소
      cancelLike(){
        http.get(`postlike/${this.seq}`)
                .then(({data}) => {
                http.delete(`postlike/${data.seq}`).then(({data})=>{
                  this.project.like_count-=1
                  this.isLike=false
                })
         })
      },
      // 프로젝트 삭제
      deleteProject(seq){
        http.delete('project/'+seq)
        .then(({data}) => {
            this.$message.success('프로젝트가 삭제되었습니다.')
            this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
         })
      },
      updateProject(seq){
        this.$router.push('/blog/project-update/'+seq)
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
.material-icons{
  font-size:13px;
}
</style>