<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <section class="content" style="padding-bottom:30px">
        <div class="box" style="width:70%; margin: 0 auto; float: none;">
          <el-tabs type="border-card">
            <el-tab-pane label="포스트 수정">
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
                  <input v-on:keyup.enter="addTag" v-on:keydown.delete="deleteTag" v-model="tag" placeholder="태그 입력 ">
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">썸네일</p>
                </div>
                <div class="col-sm-9" style="padding:0px 0px 0px 25px">
                  <el-upload action="https://jsonplaceholder.typicode.com/posts/" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" list-type="picture-card" style="display:inline">
                  <i slot="default" class="el-icon-plus"></i>
                  </el-upload>
                  <el-dialog>
                    <img width="100%" :src="img_url" alt="">
                  </el-dialog>
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
      isReserve: '',
      postInfo: {
        seq: '',
        title: '',
        content: '',
        disclosure: '전체공개',
        regtime: '',
        img_url: '',
        tags: [

        ],
        
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
    },
    handleAvatarSuccess(res, file) {
        
      var frm = new FormData();
      frm.append("upload_file", file.raw);

        http.post('user/upload',frm,{headers: {
          'Content-Type': 'multipart/form-data'
        }})
      .then(({data}) => {
        this.dialogImageUrl = 'http://'.concat(data)
        })
    },
    beforeAvatarUpload(file) {

        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('Avatar picture must be JPG format!');
        }
        if (!isLt2M) {
          this.$message.error('Avatar picture size can not exceed 2MB!');
        }
        return isJPG && isLt2M;
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

<style>
#editor .ql-editor{
  min-height: 400px !important;
}
</style>