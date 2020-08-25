<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <section class="content" style="padding-bottom:30px">
        <div class="box" style="width:70%; margin: 0 auto; float: none;">
          <el-tabs type="border-card">
            <el-tab-pane label="포스트 수정">
              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">제목
                    <el-tooltip class="item" effect="dark" content="게시물의 특징을 살릴 수 있는 기발한 제목을 적어주세요" placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
                  </p>
                </div>
                <div class="col-sm-9">
                  <el-input style="padding:10px;" placeholder="POST TITLE" v-model="postInfo.title"> </el-input>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title" style="height:490px;">
                  <p class="pull-right">내용
                    <el-tooltip class="item" effect="dark" content="많은 데블로거들이 이 부분에 오늘 배운 새로운 지식들을 정리합니다." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
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
              <el-button @click="write" style="float:right">포스트 수정</el-button>
            </el-tab-pane>
          </el-tabs>
        </div>
      </section>
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
        seq: '',
        title: '',
        content: '',
        disclosure: '전체공개',
        regtime: '',
        img_url: '',
        tags: [],
      },
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      tag: '',
      tags: [],
      htmlTag: ''
    }
  },
  mounted(){
    this.getPostInfo()
  },
  methods:{
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
        http
        .put('/post', {
          seq: this.$route.params.seq,
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
              message: '포스트 수정 완료'
            });
            this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
          })
        })
      }
    },
    handleAvatarSuccess(res, file) {

      this.postInfo.img_url = 'http://'.concat(res)

    },
    beforeAvatarUpload(file) {
      const isPNG = file.type === 'image/png';
      const isJPG = file.type === 'image/jpeg';
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
        this.tag = ' '
        this.tag = ''
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
    getPostInfo() {
      http
      .get('/post/'+this.$route.params.seq)
      .then(({data}) => {
        if(data.disclosure===1)
          this.postInfo.disclosure = '전체공개'
        else if(data.disclosure===2)
          this.postInfo.disclosure = '이웃공개'
        else
          this.postInfo.disclosure = '비공개'
        this.postInfo.title = data.title
        this.postInfo.seq = data.seq
        this.postInfo.content = data.content
        this.postInfo.regtime = data.regtime
        this.postInfo.img_url = data.img_url
        http
        .get('/posttag/'+this.$route.params.seq)
        .then(({data}) => {
          for(let i=0; i<data.length; i++){
            this.tags.push(data[i].tag)
          }
          this.renew()
        })
      })
    }
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
</style>