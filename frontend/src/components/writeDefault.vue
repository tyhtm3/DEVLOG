<template>
  <transition name="el-zoom-in-top">
    <div>
      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* 제목
            <el-tooltip class="item" effect="dark" content="게시물의 특징을 살릴 수 있는 제목을 적어주세요" placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" placeholder="POST TITLE" v-model="postInfo.title"> </el-input>
        </div>
      </div><hr>

       <div class="row">
        <div class="col-sm-2 pjt-title" style="height:490px;">
          <p class="pull-right">* 내용
            <el-tooltip class="item" effect="dark" content="오늘 배운 새로운 지식들을 정리해보세요" placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <vue-editor id="post-editor" v-model="postInfo.content" style="padding:10px;"></vue-editor>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">태그
            <el-tooltip class="item" effect="dark" content="다양한 태그를 통해 다른 데블로거들의 관심을 끌어보세요! 태그를 타고 들어온 데블로거가 함께 새로운 프로젝트를 진행하자고 요청할지도 몰라요!" placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9" style="position:relative; top:20px;">
          <span v-html="htmlTag">
          </span>
          # <input class="inputtag" v-on:keyup.enter="addTag" v-on:keydown.delete="deleteTag" v-model="tag" placeholder="태그를 입력해주세요.">
        </div>
      </div><hr>
      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">썸네일
            <el-tooltip class="item" effect="dark" content="게시물을 대표할 수 있는 사진을 첨부해주세요. 화려한 썸네일을 활용한다면 피드 페이지에서 더욱 돋보일 수 있어요." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9" style="padding:0px 0px 0px 25px">
          <el-upload action="http://i3a402.p.ssafy.io:8090/devlog/api/user/upload"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :show-file-list="false"
          list-type="picture-card"
          style="display:inline">
          <img v-if="postInfo.img_url" :src="postInfo.img_url" style="width:100%; height:100%; vertical-align:top">
          <i v-else slot="default" class="el-icon-plus"></i>
          </el-upload>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">공개 여부</p>
        </div>
        <div class="col-sm-9" style="padding:15px 0px 0px 25px">
          <el-radio-group v-model="postInfo.disclosure">
            <el-radio-button label="전체공개" ></el-radio-button>
            <el-radio-button label="이웃공개" ></el-radio-button>
            <el-radio-button label="비공개" ></el-radio-button>
          </el-radio-group>
        </div>
        <div class="col-sm-1" style="padding:15px 0px 0px 25px">
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">작성 시간</p>
        </div>
        <div class="col-sm-9" style="padding:15px 0px 0px 25px">
          <el-radio class="radio" v-model="isReserve" label="0">현재</el-radio>
          <el-radio class="radio" v-model="isReserve" label="1">예약</el-radio>
          <el-date-picker v-if="isReserve==1" style="margin-left:10px;" v-model="postInfo.regtime" type="datetime" placeholder="Select date and time">
          </el-date-picker>
        </div>
      </div><hr>
      
      <el-button @click="write" style="float:right">포스트 작성</el-button>
      
      <el-button-group style="float:right; margin-right:10px;">
      <el-button @click="save">저장</el-button>
      <el-button @click="draft"><span style="color:#3c8dbc;">{{drafts.length}}</span></el-button>
      </el-button-group>


      <!-- 임시보관함 모달창 -->
      <el-dialog
                    :visible.sync="selectDialogVisible"
                    width="40%"
                    center
                    >
                    <h2 style="margin-top:-20px;">임시저장 글</h2>
                    &nbsp; 총 <span style="color:green;">{{drafts.length}}</span> 개
                    <div @click="showDraft(index);" style="margin-bottom:-20px;" class="draft" v-for="(draft,index) in drafts" :key="index"  @mouseenter="showDeleteButton(index)" @mouseleave="hideDeleteButton(index)">
                      <hr>
                      {{draft.title}}
                       <span @click="deleteDraft(index);" class="delete-draft-button hideDeleteButton ti-trash pull-bottom pull-right" style="padding-right:20px;"></span>
                      <br>
                      <span style="font-size:12px;">{{draft.regtime}}</span>
                      <br><br>
                    </div>
     </el-dialog>

    </div>
  </transition>
</template>

<script>
import { VueEditor } from 'vue2-editor'
import { mapGetters } from 'vuex'
import http from '../util/http-common'

export default {
  components: {
    VueEditor
  },
  data: function () {
    return {
      isReserve: "0",
      postInfo: {
        title: '',
        content: '',
        tags: [

        ],
        disclosure: '전체공개',
        regtime: '',
        img_url: ''
      },
      disabled: false,
      tag: '',
      tags: [],
      // 임시저장
      drafts: [],
      selectDialogVisible: false,
    }
  },
  created() {
     http
        .get('./post/draft', {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }}).then(({data})=>{
          this.drafts = data;
        })
  },
  methods:{
    // 임시 저장글 클릭시 화면에 나타나게 함
    showDraft(index){
      http.get('post/'+this.drafts[index].seq)
      .then(({data}) => {
          this.postInfo=data
          if(this.postInfo.disclosure==1)
            this.postInfo.disclosure='전체공개'
          else if(this.postInfo.disclosure=2)
            this.postInfo.disclosure='이웃공개'
          else
            this.postInfo.disclosure='비공개'
      })
       // 태그 불러오기
        http.get('posttag/'+this.drafts[index].seq)
              .then(({data}) => {
              this.tags = []
              for(var i=0; i<data.length; i++){
              this.tags[i] = data[i].tag
              }
              this.renew()
              this.selectDialogVisible=false;
        })
        
    },
    // 임시 저장글 삭제
    deleteDraft(index){
       this
      .$confirm('선택글을 삭제 하시겠습니까?', {
          confirmButtonText: '삭제',
          cancelButtonText: '취소',
          type: 'warning'
      })
      .then(() => {
               http
               .delete('post/'+this.drafts[index].seq)
               .then(({data}) => {
                   this.drafts.splice(index,1)
                    this.$message({
                            type: 'success',
                            message: '선택한 임시 게시물이 삭제 되었습니다.'
                        });
               })
      })
    },
    draft(){
      this.selectDialogVisible=true;
    },
    save(){
      if(this.postInfo.title === ''){
        this.$message.warning('포스트 제목을 입력해 주세요.')
      }
      else if(this.postInfo.content === ''){
        this.$message.warning('포스트 내용을 입력해 주세요.')
      }
      else{
        if(this.postInfo.disclosure === "전체공개")
          this.postInfo.disclosure = 1
        else if(this.postInfo.disclosure === "이웃공개")
          this.postInfo.disclosure = 2
        else
          this.postInfo.disclosure = 3
        http
        .post('./post', {      
          seq_blog: this.$store.getters.getUserInfo.seq,
          title: this.postInfo.title,
          content: this.postInfo.content,
          disclosure: this.postInfo.disclosure,
          img_url: this.postInfo.img_url,
          status: 'draft'
        })
        .then(({data}) => {
          if(this.tags.length==0)
            this.postInfo.tags = null
          else
            this.postInfo.tags = this.tags
          http
          .post('./posttag', {
            seq_post: data,
            tag: this.postInfo.tags
          })
          .then(({data})=>{
            this.$message({
              type: 'success',
              message: '임시저장 완료.'
            });
            this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
          })
        })
      }
    },
    write(){
      if(this.postInfo.title === ''){
        this.$message.warning('포스트 제목을 입력해 주세요.')
      }
      else if(this.postInfo.content === ''){
        this.$message.warning('포스트 내용을 입력해 주세요.')
      }
      else{
        if(this.postInfo.disclosure === "전체공개")
          this.postInfo.disclosure = 1
        else if(this.postInfo.disclosure === "이웃공개")
          this.postInfo.disclosure = 2
        else
          this.postInfo.disclosure = 3
        // 임시저장글을 등록할경우
        if(this.postInfo.status=='draft'){
        http
        .put('/post', {
          seq: this.postInfo.seq,
          title: this.postInfo.title,
          content: this.postInfo.content,
          disclosure: this.postInfo.disclosure,
          img_url: this.postInfo.img_url,
          status: 'published'
        })
        .then(({data}) => {
          if(this.tags.length==0)
            this.postInfo.tags = null
          else
            this.postInfo.tags = this.tags
          http
          .post('/posttag', {
            seq_post: this.postInfo.seq,
            tag: this.postInfo.tags
          })
          .then(({data})=>{
            this.$message({
              type: 'success',
              message: '포스팅 완료'
            });
            this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
          })
        })
        }
        // 새 글을 등록할 경우
        else{
        http
        .post('./post', {      
          seq_blog: this.$store.getters.getUserInfo.seq,
          title: this.postInfo.title,
          content: this.postInfo.content,
          disclosure: this.postInfo.disclosure,
          img_url: this.postInfo.img_url,
          status: 'published'
        })
        .then(({data}) => {
          if(this.tags.length==0)
            this.postInfo.tags = null
          else
            this.postInfo.tags = this.tags
          http
          .post('./posttag', {
            seq_post: data,
            tag: this.postInfo.tags
          })
          .then(({data})=>{
            this.$message({
              type: 'success',
              message: '포스팅 완료.'
            });
            this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
          })
        })
      }}
    },
    handleAvatarSuccess(res, file) {

      this.postInfo.img_url = 'http://'.concat(res)

    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isLt10M = file.size / 1024 / 1024 < 10;

      if (!(isPNG || isJPG)) {
        this.$message.error('Image must be JPG or PNG format!1');
      }
      if (!isLt10M) {
        this.$message.error('Image size can not exceed 10MB!');
      }
      return (isJPG || isPNG) && isLt10M;
    },
    addTag() {
      if(this.tag != ''){
        for(var i=0; i<this.tags.length; i++){
          if(this.tags[i] === this.tag){
            this.tag = '';
            return
          }
        }
        this.tags.push(this.tag)
        this.tag = '';
        this.renew()
      }
    },
    deleteTag() {
      if(this.tag === ''){
        this.tags.pop()
        this.renew()
        this.tag = ' '
        this.tag = ''
      }
    },
    renew() {
      this.htmlTag = '';
      for(var i=0; i<this.tags.length; i++){
        this.htmlTag = this.htmlTag+'<span class="tag">#'+this.tags[i]+'</span>'
      }
    },
    showDeleteButton(index){
      $(".hideDeleteButton").eq(index).show();
    },
    hideDeleteButton(index){
      $(".hideDeleteButton").eq(index).hide();
    },
  }
}
</script> 

<style scoped>
.pjt-title{
  padding-top: 20px;
}
.inputtag{
  opacity:0.5;
  border:solid;
  border-top:1px;
  border-left:1px;
  border-right:1px;
  border-color: rgba(143, 143, 143, 0.432);
  border-width: 0.1px;
  width:135px;
}
.inputtag:hover{
  opacity:0.8;
}
.inputtag:focus{
  opacity:0.8;
  outline: none;
}
.hideDeleteButton{
  display:none;
}
  /* 임시저장 글 삭제 */
  .delete-draft-button{
    /* style="font-size:3px;color:#333333;padding:0px;margin-left:-30px;" */
    font-size:20px;
    color:#333333;
    padding:0px;
    margin-left:-35px;
  }
  .delete-draft-button:hover{
    color:black;
    font-weight: bold;
    font-size:20px;
  }

  .draft:hover{
    background-color:#f5f5f5;
  }

</style>