<template>
    <!-- 댓글 입력 창 -->
        <div class="comment-nest">
              <div v-if="updateSeq==0">
              <div style="display:inline-block; height:100px;;width:100%;">
              <vue-editor class="comment-editor" v-model="insertContent" v-bind:placeholder="placeholderMessage"></vue-editor>
              </div>
              <br>
              <br>
              <el-button class="pull-right" @click="insertComment">Comment</el-button>
              </div>
              <div style="margin-bottom:70px;"></div>
              <!-- 댓글 리스트 -->
              <div v-for="(comment,index) in comments" :key="index">
                <ul class="media-list">
                  <li class="media">
                    <!-- 프로필 사진이 있으면 -->
                    <a class="pull-left" style="margin-top:10px;" href="#" v-if="comment.profile_img_url" > <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" :src="comment.profile_img_url" style="width: 64px; height: 64px;"> </a>
                    <!-- 프로필 사진이 없으면 -->
                    <a class="pull-left" style="margin-top:10px;" href="#" v-else > <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" src="http://api.randomuser.me/portraits/thumb/women/21.jpg" style="width: 64px; height: 64px;"> </a>
                    
                    <div class="media-body">
                      <div class="social-profile">
                        <h3>
                          <span v-if="comment.nickname" class="link-comment" @click="moveBlog(comment.id)" style="cursor:pointer;">{{comment.nickname}}</span>
                          <span v-else class="link-comment" @click="moveBlog(comment.id)" style="cursor:pointer;">{{comment.name}}</span>
                          <span style="font-size:12px;"><i class="entypo-globe"></i>&nbsp;{{comment.regtime}}</span>
                          <span v-if="comment.seq_user==seq_user">
                            <span class="link-comment pull-right" style="font-size:13px; cursor:pointer;">
                              <span @click="showCommentEditor(comment.seq,comment.content)">수정</span>
                              &nbsp;|&nbsp;
                              <span @click="deleteComment(comment.seq)">삭제</span>
                            </span>
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
    props: ['seq', 'updateCommentCount'],
    data: function () {
        return { 
          comments:'',
          commentUser:[], 
          seq_user: this.$store.state.userInfo.seq,
          insertContent: ' ', // 댓글 입력 내용물
          updateContent: '', // 댓글 수정 내용물
          updateSeq: 0,
          placeholderMessage:'댓글',
        }
    },
    watch:{
      getUserInfo: function(){
        this.seq_user = this.$store.state.userInfo.seq;
      }
    },
    mounted(){
      this.getComment(this.seq);
    }
    ,
    methods: {
      // 댓글 리스트 불러오기
       getComment(seq){
        this.commentUser = []
        http.get('postcomment/'+seq)
        .then(({data}) => {
          this.comments=data;
         })
      },

      // 댓글 입력
      insertComment(){
        if(!this.getIsLogin){
          this.$message({
            type: 'error',
            message: '댓글 작성 기능은 로그인 후 이용 가능합니다.'
          })
        }else if(this.insertContent==''){
           this.$message({
            type: 'error',
            message: '메세지를 입력해주세요.'
          })
        }
        else if(!this.isBlank(this.insertContent)){
          this.$message({
            type: 'error',
            message: '메세지를 입력해주세요.'
          })
        }
        else{
          http.post('postcomment',{
            content: this.insertContent,
            seq_post:this.seq,seq_user:this.seq_user
          },
          {
            headers: {
              'Authorization': this.$store.state.token
            }
          })
          .then(({data}) => {
            //댓글 입력하고 리스트 업데이트
            this.updateCommentCount()
            this.getComment(this.seq)
          })
          this.insertContent = ''
        }
         
      },
      // 댓글 삭제
      deleteComment(seq){
        this
        .$confirm('삭제하시겠습니까?', {
          confirmButtonText: '삭제',
          cancelButtonText: '취소',
          type: 'warning'
        }).then(()=>{
          http.delete('postcomment/'+seq)
                .then(({data}) => {
              //댓글 삭제하고 리스트 업데이트
              this.updateCommentCount()
              this.getComment(this.seq)
         })
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
      moveBlog(id){
        this.$router.push('../'+id)
      },
      isBlank(text){
        text = text.replace(/<(\/br|br)([^>]*)>/gi,"");
        text = text.replace(/<(\/p|p)([^>]*)>/gi,"");
        return text
      }
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