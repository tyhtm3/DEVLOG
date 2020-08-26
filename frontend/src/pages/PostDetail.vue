<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background-color:transparent;">
      <!-- start banner carousel -->
      <div class="header-block" style="background: black;">
        <div class="header-image">
          <!-- post.img_url -->
          <img v-if="post.img_url" class="img-responsive-media" :src="post.img_url" alt="" style="opacity:0.2!important; margin-top: -20px;">
          <img v-else src="../assets/project-banner2.jpg" height="100%" style=" top:50%; left:50%; max-height: initial;margin-top: -20px;"/>
        </div>
        <div class="header-text"><p>{{post.title}}</p></div>
      </div>
      <!-- end banner carousel -->
      <!-- 헤더 끝 -->               
      <div class="box">
        <!-- 헤더 : 프로젝트 작성시간, 댓글수, 좋아요 수, 수정|삭제 -->
        <!-- 프로젝트 관리 헤더 시작 -->
        <div class="row" style="padding-top:20px; padding-left:15px;">
          <div class="col-sm-6">
            <a :href="url" v-if="postUser.nickname" style="color:black;"><i class="material-icons" style="position:relative; top:2px;">person</i>{{postUser.nickname}}</a>
            <a :href="url" v-else> <i  class="material-icons" style="position:relative; top:1px;">person</i> {{postUser.id}}</a><span> | </span> 
            <span><i  class="material-icons" style="position:relative; top:2px;">date_range</i></span>&nbsp;<span style="min-">{{post.regtime}}</span><span> | </span> 
            <span><i  class="material-icons" style="position:relative; top:2px;">insert_comment</i></span>&nbsp;<span>{{commentCnt}}</span><span> | </span> 
            <span>
            <i v-if="isLike" @click="cancelLike" class="material-icons" style="color:red; position:relative; top:2px; cursor:pointer" >favorite</i> 
            <i v-else @click="like" class="material-icons" style="position:relative; top:2px; cursor:pointer">favorite_border</i> 
            </span>&nbsp;{{post.like_count}}
          </div>
          <div class="col-sm-6">
            <div class="pull-right" style="padding-right:15px;" v-if="post.seq_blog==seq_user">
              <span style="cursor:pointer" @click="updatePost(post.seq)">수정 </span>&nbsp;|&nbsp;
              <span style="cursor:pointer" @click="deletePost(post.seq)"> 삭제</span>
            </div>
          </div>
        </div>
        <!-- 프로젝트 관리 헤더 끝 -->

        <hr>

        <div class="row">
          <div class="col-sm-12">
            <div class="blog-list-nest">
              <div class="blog-list-content">
                <p class="post-content" style="margin-top:60px;" v-html="post.content"></p><hr> 
                <!-- 포스트 태그 -->
                <!-- <div style="clear:both;"></div> -->
              </div>
            </div>
          </div>
        </div>
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
        <comment v-bind:seq="seq" v-bind:updateCommentCount="updateCommentCount"></comment>
        <!-- 댓글 창 끝 -->
        
      </div>
    </div>
  </transition>
</template>
<script>
import Comment from '../components/detailComment'
import http from '../util/http-common'
import html2canvas from 'html2canvas'
import jsPDF from 'jspdf'
export default {
  components: {
    Comment
  },
  data: function () {
      return { 
        seq: '',
        post:'',
        postUser:'',
        isLike:'',
        commentCnt:'',
        tag: [],
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
      // 블로그 메인 -> 디테일페이지 -> 태그검색 -> 블로그메인
      if(this.$store.state.previousUrl.indexOf('blog')>0){
          this.$router.push('/blog/'+this.blogurl)
      }
      // 피드 -> 디테일페이지 -> 태그검색 -> 피드
      else{
          this.$router.push('/')
      }
    },
    getInfo(seq){
      // 포스트 불러오기.
      http.get('post/'+seq)
      .then(({data}) => {
          this.post=data
          // 포스트 작성자 정보 불러오기.
          http.get('user/'+data.seq_blog)
          .then(({data}) => {
            this.postUser=data
            this.blogurl = this.postUser.id
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
        // 좋아요 여부 불러오기
      http.get(`postlike/${seq}`)
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
                this.post.like_count-=1
                this.isLike=false
              })
        })
        this.$message.info('좋아요 취소')
    },
    // 포스트 삭제
    deletePost(seq){
        http.delete('post/'+seq)
      .then(({data}) => {
          this.$message.success('게시글이 삭제되었습니다.')
          this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
        })
    },
    // 포스트 수정
    updatePost(seq){
      this.$router.push('/blog/post-update/'+seq)
    },
    savePost(){
      //Vue.js 는 window 객체에 직접 할당해야 함.
      window.html2canvas = html2canvas
      html2canvas($('.content-wrapper')[0])
      .then(function(canvas) {
        let pdf = new jsPDF('p', 'mm', 'a4')
        let imgData = canvas.toDataURL('image/png');
        pdf.addImage(imgData, 'PNG', 0, 0)
        pdf.save('sample-file.pdf')
      })
    }
  },
}
</script>
<style scoped>

a:link { color: #B1B0AC; text-decoration: none;}
a:visited { color: #B1B0AC;; text-decoration: none;}
a:hover { color: black; text-decoration: bold;}

.header-block{
  height: 425px!important;;
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
  font-family: "Noto Sans KR";
  font-weight: 500;
}
.box{
  /* padding-left:300px;
  padding-right:300px; */
  padding-left:10%;
  padding-right:10%;
}
.pjt-title{
  font-size:18px;
}
.post-content{
  margin-top:-2px;
  font-size:14px;
  line-height:30px;
}
.material-icons{
  font-size:13px;
}

</style>