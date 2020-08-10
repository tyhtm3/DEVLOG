<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <!-- start banner carousel -->
      <div class="header-block">
        <div class="header-image">
          <img src="../assets/post-banner.jpg" width="100%" style=" max-height: initial;margin-top: -15%;opacity:0.5;"/>
        </div>
        <div class="header-text"><p>{{post.title}}</p></div>
      </div>
      <!-- end banner carousel -->

      <!-- 헤더 : 프로젝트 작성시간, 댓글수, 좋아요 수, 수정|삭제 -->
      <ul class="list-inline blog-devin-tag" style="padding-left:300px;padding-right:300px;font-size:13px;">
        <li><a>&nbsp;&nbsp;<span class="ti-user"></span>{{postUser.nickname}}</a></li>
        <li><a> <span class="ti-pencil"></span>&nbsp;{{post.regtime}}</a></li>
        <li><a> <span class="ti-comment-alt"></span>&nbsp;{{commentCnt}}</a></li>
        <li>
          <i v-if="isLike" @click="cancelLike" class="material-icons">favorite</i>
          <i v-else @click="like" class="material-icons">favorite_border</i>
          &nbsp;{{post.like_count}}
        </li>
        <li class="pull-right" v-if="post.seq_blog==seq_user">
          <span style="cursor:pointer" @click="updatePost(post.seq)">수정 </span>&nbsp;|&nbsp;
          <span style="cursor:pointer" @click="deletePost(post.seq)"> 삭제</span>
        </li>
      </ul>
      <!-- 헤더 끝 -->               
      <div class="box">
        <div class="row">
          <div class="col-sm-12">
            <div class="blog-list-nest">
              <div class="blog-list-content">
                <div class="row">
                  <div class="col-sm-12">
                    <p class="post-content" style="margin-top:60px;" v-html="post.content"></p><hr> 
                  </div>
                </div>
                <!-- 포스트 태그 -->
                <p class="pull-left">
                  <span v-for="(tag, index) in tag" v-bind:key="index" class="tag">
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
          seq: '',
          post:'',
          postUser:'',
          isLike:'',
          commentCnt:'',
          tag: [],
          seq_user: this.$store.state.userInfo.seq,
        }
    },
    created(){
      this.seq= this.$route.params.seq
      this.getInfo(this.seq)
    },
    methods: {
      getInfo(seq){
        // 포스트 불러오기.
        http.get('post/'+seq)
        .then(({data}) => {
            this.post=data
            // 포스트 작성자 정보 불러오기.
            http.get('user/'+data.seq_blog)
            .then(({data}) => {
              this.postUser=data
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
        http.post('postlike/',{seq_post:this.seq})
                .then(({data}) => {
                this.post.like_count+=1
                this.isLike=true
         })
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
      },
      // 포스트 삭제
      deletePost(seq){
         http.delete('post/'+seq)
        .then(({data}) => {
            this.$message.success('게시글이 삭제되었습니다.')
            this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
         })
      },
      // 포스트 수정 미구현
      updatePost(seq){
        this.$router.push('/blog/modify/'+seq)
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
  top: 55%;
  left: 50%;
  transform: translate( -50%, -50% );
  color:black;
  font-size:35px;
}
.box{
  padding-left:300px;
  padding-right:300px;
}
.pjt-title{
  font-size:18px;
}
.post-content{
  margin-top:-2px;
  font-size:14px;
  line-height:30px;
}
</style>