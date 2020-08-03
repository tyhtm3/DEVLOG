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
                        <li><a> <span class="ti-comment-alt"></span>&nbsp;{{comment.length}}</a></li>
                        <li><a> <span class="ti-heart"></span>&nbsp;{{post.like_count}}</a></li>
                        <li class="pull-right" v-if="post.seq_blog==seq_user"><a> &nbsp;수정</a><a > &nbsp; | </a><a href="#" @click="deletePost(post.seq)"> &nbsp;삭제</a></li>
                    </ul>
              <!-- 헤더 끝 -->               
                <div class="box">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="blog-list-nest">
                                <div class="blog-list-content"
                                >
                                     <div class="row">
                                          <div class="col-sm-12">
                                          <p class="post-content" style="margin-top:60px;" v-html="post.content"></p><hr> 
                                          </div>
                                    </div>
                                  
                                     
                                  
                                    <!-- 프스트 태그 -->
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
                                <el-input type="textarea" :rows="5" placeholder="LEAVE A COMMENT:" v-model="textarea"> </el-input>
                                <br>
                                <br>
                                <el-button class="pull-right">Submit</el-button>
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
                                                <span style="font-size:14px;"><a class="link-comment pull-right" href="#"><i class="fontawesome-share"></i>&nbsp;삭제</a></span>
                                                <span style="font-size:14px;"><a class="link-comment pull-right"><i class="fontawesome-share"></i>&nbsp; | </a></span>
                                                <span style="font-size:14px;"><a class="link-comment pull-right" href="#"><i class="fontawesome-share"></i>&nbsp;수정</a></span>
                                            </span>
                                        </h3> </div>
                                            <p>{{comment.content}}</p>
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
  export default {
    components: {
    },
    data: function () {
        return { 
          post:'',
          postUser:'',
          comment:'',
          commentUser:[],
          tag: [],
          seq_user: this.$store.state.userInfo.seq,
        }
    },
    created(){
      this.getInfo(this.$route.params.seq)
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
        // 댓글 불러오기
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
        // 태그 불러오기
         http.get('posttag/'+seq)
                .then(({data}) => {
                this.tag = data;
         })
      },
      // 포스트 삭제 미구현
      deletePost(seq){
         http.delete('post/'+seq)
        .then(({data}) => {
            if(data==="SUCCESS"){
              alert('게시글이 삭제되었습니다.')
              this.$router.push(`/blog`)
            }
         })
      },
      // 포스트 수정 미구현
      updatePost(){
        
      },
      // 포스트 삭제 미구현
      deleteComment(){

      },
      // 포스트 수정 미구현
      updateComment(){

      }
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