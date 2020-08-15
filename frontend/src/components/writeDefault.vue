<template>
  <transition name="el-zoom-in-top">
    <div>
      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">제목</p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" placeholder="POST TITLE" v-model="postInfo.title"> </el-input>
        </div>
      </div><hr>

       <div class="row">
        <div class="col-sm-2 pjt-title" style="height:490px;">
          <p class="pull-right">내용</p>
        </div>
        <div class="col-sm-9">
          <vue-editor id="project-editor" v-model="postInfo.content" style="padding:10px;"></vue-editor>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">태그</p>
        </div>
        <div class="col-sm-9" style="padding:15px 0px 0px 25px">
          <span v-html="htmlTag">
          </span>
          # <input class="inputtag" v-on:keyup.enter="addTag" v-on:keydown.delete="deleteTag" v-model="tag" placeholder="태그를 입력해주세요.">
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">썸네일</p>
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
          현재 <el-switch v-model="isReserve" on-text=true off-text=false></el-switch> 예약
          <el-date-picker v-if="isReserve" v-model="postInfo.regtime" type="datetime" placeholder="Select date and time">
          </el-date-picker>
        </div>
      </div><hr>
      <el-button @click="write" style="float:right">포스트 작성</el-button>
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
      isReserve: '',
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
    }
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
        .post('./post', {      
          seq_blog: this.$store.getters.getUserInfo.seq,
          title: this.postInfo.title,
          content: this.postInfo.content,
          disclosure: this.postInfo.disclosure,
          img_url: this.postInfo.img_url
        })
        .then(({data}) => {
          console.log(data)
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
      }
    },
    handleAvatarSuccess(res, file) {

      this.postInfo.img_url = 'http://'.concat(res)

    },
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt10M = file.size / 1024 / 1024 < 10;

        if (!isJPG) {
          this.$message.error('Image must be JPG format!');
        }
        if (!isLt10M) {
          this.$message.error('Image size can not exceed 10MB!');
        }
        return isJPG && isLt10M;
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