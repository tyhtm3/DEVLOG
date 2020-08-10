<template>
    <!-- 댓글 입력 창 -->
        <div class="comment-nest">
              <div v-if="updateSeq==0">
              <div style="display:inline-block; height:100px;;width:100%;">
              <vue-editor class="comment-editor" v-model="insertContent" :placeholder="placeholderMessage"></vue-editor>
              </div>
              <br>
              <br>
              <el-button class="pull-right" @click="insertComment">Comment</el-button>
              </div>
              <div style="margin-bottom:70px;"></div>
              <!-- 댓글 리스트 -->
              <div v-for="(comment,index) in comment" :key="index">
                <ul class="media-list">
                  <li class="media">

                    <!-- 프로필 사진이 있으면 -->
                    <a class="pull-left" style="margin-top:10px;" href="#" v-if="commentUser[index].profile_img_url" > <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" :src="commentUser[index].profile_img_url" style="width: 64px; height: 64px;"> </a>
                    <!-- 프로필 사진이 없으면 -->
                    <a class="pull-left" style="margin-top:10px;" href="#" v-else > <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" src="http://api.randomuser.me/portraits/thumb/women/21.jpg" style="width: 64px; height: 64px;"> </a>
                    
                    <div class="media-body">
                      <div class="social-profile">
                        <h3> <a class="link-comment" href="#">{{commentUser[index].nickname}}</a>
                          <span style="font-size:12px;"><i class="entypo-globe"></i>&nbsp;{{comment.regtime}}</span>
                          <span v-if="commentUser[index].seq==seq_user">
                            <span style="font-size:13px;"><a class="link-comment pull-right"  @click="deleteComment(comment.seq)"><i class="fontawesome-share"></i>&nbsp;삭제</a></span>
                            <span style="font-size:13px;"><a class="link-comment pull-right"><i class="fontawesome-share"></i>&nbsp; | </a></span>
                            <span style="font-size:13px;"><a class="link-comment pull-right" @click="showCommentEditor(comment.seq,comment.content)"><i class="fontawesome-share"></i>&nbsp;수정</a></span>
                          </span>
                        </h3>
                      </div>
                      
                      <!-- 댓글 수정 클릭시 updateContent 정보가 담겨진 vue editor가 나타난다.-->
                      <div v-if="updateSeq==comment.seq" style="margin-top:20px;">
                      <vue-editor class="comment-editor" v-model="updateContent" style="display:inline-block;width:100%;"></vue-editor><br><br>
                      <el-button class="pull-right" @click="updateComment">Comment</el-button>
                      </div>
                      <p v-else v-html="comment.content" style="margin-left:0px;"></p>
                      
                    </div>
                    <br>
                  </li>
                </ul>
                <hr>
              </div>
            </div>
            <!-- 댓글 창 끝 -->
</template>
<script>
  import http from '../util/http-common'
  import { VueEditor } from 'vue2-editor'
  import { mapGetters } from 'vuex'
  export default {
    components: {
      VueEditor,
    },
    computed: {
    ...mapGetters([
      'getUserInfo',
      'getIsLogin'
    ])
  },
    props: ['seq'],
    data: function () {
        return { 
          comment:'',
          commentUser:[], 
          seq_user: this.$store.state.userInfo.seq,
          insertContent: '', // 댓글 입력 내용물
          updateContent: '', // 댓글 수정 내용물
          updateSeq: 0,
          placeholderMessage:'댓글을 입력해주세요.'
        }
    },
    watch:{
      getIsLogin: function(){
        this.placeholderMessage='댓글을 입력해주세요.';
        this.seq_user = this.$store.state.userInfo.seq;
        // 유저정보는 왜 안들어가지?
        // 이거 왜 실시간 반영 안되는거지
      },
      // seq_user:function(){
      //   this.placeholderMessage='댓글을 입력해주세요.';
      // }
    },
    mounted(){
      this.getComment(this.seq)
    },
    methods: {
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
        if(this.getIsLogin){
          http.post('postcomment',{content:this.insertContent,seq_post:this.seq,seq_user:this.seq_user})
                  .then(({data}) => {
                //댓글 입력하고 리스트 업데이트
                this.getComment(this.seq)
          })
          this.insertContent = ''
        }else{
          this.$message({
            type: 'error',
            message: '댓글 작성 기능은 로그인 후 이용 가능합니다.'
          })
        }
         
      },
      // 댓글 삭제
      deleteComment(seq){
         http.delete('postcomment/'+seq)
                .then(({data}) => {
              //댓글 삭제하고 리스트 업데이트
               this.getComment(this.seq)
         })
      },
      // 댓글 수정창
      showCommentEditor(seq,content){
        // 댓글 수정창 닫기
        if(this.updateSeq==seq){
          this.updateSeq=0
          this.updateContent =''
          this.insertContent =''
        }
        // 댓글 수정창 열기
        else{
          this.updateSeq=seq
          this.updateContent = content
        }
      },
      // 댓글 수정
      updateComment(){
         http.put('postcomment',{seq:this.updateSeq,content:this.updateContent})
                .then(({data}) => {
              //댓글 수정하고 리스트 업데이트
               this.updateSeq=0
               this.updateContent = ''
               this.getComment(this.seq)
         })
      },
   },
  }
</script>
<style scoped>
span:link { color: #B1B0AC; text-decoration: none;}
span:visited { color: #B1B0AC;; text-decoration: none;}
span:hover { color: black; text-decoration: bold;}
</style>
<style>
.comment-editor .ql-editor{
  min-height: 100px;
}
</style>