<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background-color:transparent;">
      <!-- 배너 시작 -->
      <div class="header-block" style="background: black;">
        <div class="header-image">
          <img v-if="project.img_url" class="img-responsive-media" :src="project.img_url" alt="" style="opacity:0.2; margin-top: -20px;">
          <img v-else src="../assets/project-banner2.jpg" height="100%" style=" top:50%; left:50%; max-height: initial;margin-top: -20px;"/>
        </div>
        <div class="header-text"><p>{{project.title}}</p></div>
      </div>
      <!-- 배너 끝 -->
      <div class="box">
        <!-- 프로젝트 관리 헤더 시작 -->
        <div class="row" style="padding-top:20px; padding-left:15px;">
          <div class="col-sm-6">
            <a :href="url" v-if="projectUser.nickname" style="color:black;"><i class="material-icons" style="position:relative; top:2px;">person</i>{{projectUser.nickname}}</a>
            <a :href="url" v-else> <i  class="material-icons" style="position:relative; top:1px;">person</i> {{projectUser.id}}</a><span> | </span> 
            <span><i  class="material-icons" style="position:relative; top:2px;">date_range</i></span>&nbsp;<span style="min-">{{project.regtime}}</span><span> | </span> 
            <span><i  class="material-icons" style="position:relative; top:2px;">insert_comment</i></span>&nbsp;<span>{{commentCnt}}</span><span> | </span> 
            <span>
            <i v-if="isLike" @click="cancelLike" class="material-icons" style="color:red; position:relative; top:2px; cursor:pointer" >favorite</i> 
            <i v-else @click="like" class="material-icons" style="position:relative; top:2px; cursor:pointer">favorite_border</i> 
            </span>&nbsp;{{project.like_count}}
          </div>
          <div class="col-sm-6">
            <div class="pull-right" style="padding-right:15px;" v-if="project.seq_blog==seq_user">
              <span style="cursor:pointer" @click="updateProject(project.seq)">수정 </span>&nbsp;|&nbsp;
              <span style="cursor:pointer" @click="deleteProject(project.seq)"> 삭제</span>
            </div>
          </div>
        </div>
        <!-- 프로젝트 관리 헤더 끝 -->
        <hr>
        <!-- 프로젝트 출력 시작 -->
        <!-- 프로젝트 정보 -->
        <div class="row" id="project-data">
          <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-3">
                <p class="pjt-title">프로젝트 제목</p>
                </div>
                <div class="col-sm-9">
                  <p class="pjt-content" style="font-size:16px;">{{project.title}}</p>
                </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <p class="pjt-title">프로젝트 개요</p>
              </div>
              <div class="col-sm-9">
                <p class="pjt-content" style="font-size:16px;">{{project.summary}}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <p class="pjt-title">개발 기간</p>
              </div>
              <div class="col-sm-9">
                <p class="pjt-content" style="font-size:16px;">{{project.start_date}} ~ {{project.finish_date}}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <p class="pjt-title">기술 스택</p>
              </div>
              <div class="col-sm-9" >
                <div v-for="(stack,index) in stack" :key="index" style="padding:5px; display: inline-block; ">
                  <img class="media-object pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="height: 64px;">
                </div>
              </div>
            </div>
            <div class="row" v-if="role.length>0">
              <div class="col-sm-3" >
                <p class="pjt-title">역할</p>
              </div>
              <div class="col-sm-9">
                <div v-for="(role,index) in role" :key="index">
                  <p class="pjt-content" style="font-size:16px; margin-bottom:0px;" >{{role.role}}</p>  
                </div>
              </div>
            </div>
            <div class="row" v-if="project.github_url">
              <div class="col-sm-3" >
                <p class="pjt-title">Github Url</p>
              </div>
              <div class="col-sm-9">
                <p class="pjt-content" style="font-size:16px;"><a href="#" @click="goUrl(project.github_url)">{{project.github_url}}</a></p>
              </div>
            </div>
            <div class="row" v-if="project.etc_url">
              <div class="col-sm-3" >
                <p class="pjt-title">참고 Url</p>
              </div>
              <div class="col-sm-9">
                <p class="pjt-content" style="font-size:16px;">{{project.etc_url}}</p>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-3" v-if="project.rep_url">
                <p class="pjt-title">참조 Url</p>
              </div>
              <div class="col-sm-9" v-if="project.rep_url">
                <p class="pjt-content" style="font-size:16px;">{{project.rep_url}}</p>
              </div>
            </div>
          </div>
          <div class="col-sm-3 pull-right">
            <div class="pull-right" style="min-height:200px;">
              <img v-if="project.img_url" class="img-responsive-media" :src="project.img_url" alt="">
              <img v-else class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
            </div>
          </div>
        </div>
        
        <!-- 상세정보 -->
        <div class="row" v-if="project.content">
          <hr>
          <div class="col-sm-12"  >
             <div class="blog-list-nest">
              <div class="blog-list-content">
                <p class ="post-content" v-html="project.content"></p>
              </div>
             </div>
          </div>
        </div>
        <hr>

        <!-- 프로젝트 태그 -->
        <div class="row">
          <div class="col-sm-9">
            <div class="blog-list-nest">
              <div class="blog-list-content">
                <p class="pull-left">
                  <span @click="tagSearch(tag.tag)" v-for="(tag, index) in tag" v-bind:key="index" class="tag" style="line-height:38px;">
                    #{{tag.tag}}
                  </span>
                </p>
              </div>
            </div>
          </div>
           <div class="col-sm-3">
             <span class="pull-right" style="line-height:38px;">
              좋아요<i v-if="isLike" @click="cancelLike" class="material-icons" style="color:red; position:relative; top:2px; cursor:pointer" >favorite</i> 
              <i v-else @click="like" class="material-icons" style="position:relative; top:2px; cursor:pointer">favorite_border</i> 
              </span>
              <span class="pull-right" style="line-height:38px;">&nbsp;&nbsp;|&nbsp;&nbsp;</span> 
              <span class="pull-right" style="line-height:38px;">
              <span @click="copyurl" style="cursor:pointer; ">URL 복사<i class="material-icons" style="position:relative; top:2px;transform: rotate(45deg)" >link</i></span>
              </span>
           </div>
        </div>
        
        <!-- 댓글 리스트 -->
        <div class="row" style="margin-top : 20px;">
          <div class="col-sm-12">
            <comment v-bind:seq="seq" v-bind:updateCommentCount="updateCommentCount"></comment>
            <!-- 댓글 창 끝 -->
          </div>
        </div>

      </div>
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
      updateCommentCount(){
        http.get('postcomment/count/'+this.seq)
              .then(({data}) => {
              this.commentCnt = data;
        }) 
      },
      copyurl(){
        var url = window.location.href
        var dummy = document.createElement("textarea");
        document.body.appendChild(dummy);
        dummy.value = url;
        dummy.select();
        document.execCommand("copy");
        document.body.removeChild(dummy);
        this.$message.success('주소가 복사되었습니다.')
      },
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
        if(this.$store.getters.getIsLogin){ 
          http.post('postlike/',{seq_post:this.seq})
          .then(({data}) => {
            this.post.like_count+=1
            this.isLike=true
          })
          this.$message.info('좋아요')
        }
        else{
          this.$message.warning('로그인이 필요한 서비스입니다.')
        }
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
        if(url.indexOf('http')<0)
          url = 'http://'+url
        window.open(url, '_blank');
      },
       removeTag(text){
      text = text.replace(/<br\/>/ig, "\n")
      text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")
      return text
    },
    copyurl(){
      var url = window.location.href
      var dummy = document.createElement("textarea");
      document.body.appendChild(dummy);
      dummy.value = url;
      dummy.select();
      document.execCommand("copy");
      document.body.removeChild(dummy);
      this.$message.success('주소가 복사되었습니다.')
    }
   },
  }
</script>
<style scoped>
a:link { color: #B1B0AC; text-decoration: none;}
a:visited { color: #B1B0AC;; text-decoration: none;}
a:hover { color: black; text-decoration: bold;}

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
  font-family: "Noto Sans KR";
  font-weight: 500;
}

/* .pjt-margin{
  margin-bottom:60px;
} */

.pjt-content{
  
  /* margin-top:-2px;
  font-size:14px;
  word-spacing: 2px;
  line-height:30px; */
}
p.ql-align-center{
  text-align: center;
}
.pjt-content p.ql-align-center {
    text-align: center;
}
.pjt-content > p.ql-align-right {
    text-align: right;
}
.pjt-content p.ql-align-justify {
    text-align: justify;
}
#project-data .row{
  margin-bottom:20px;
}


/* 내가 적용한 css */
.header-block{
  height: 425px!important;;
  overflow: hidden;
  position: relative;
}

.box{
   padding-left:10%;
   padding-right:10%;
}
.pjt-title{
  font-size:18px;
}
.material-icons{
  font-size:13px;
}

</style>
