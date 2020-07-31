<template>



            <!-- 썸네일 연결 안됨..! -->
            <!-- 썸네일 연결 안됨..! -->


            
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <section class="content" style="margin-bottom:20px">
        <div class="box" style="width:70%; margin: 0 auto; float: none;">
          <div class="box-header">
          </div>
          <div class="box-body">
            <div style="margin-bottom: 15px">
              <span style="display:inline-block; width:100px;">제목</span>
              <input class="title" v-model="postInfo.title" placeholder="제목을 입력해 주세요." style="width:50%; border:0px;">
            </div>
            <hr>
            <div style="margin-bottom: 15px">
              <vue-editor v-model="postInfo.content"></vue-editor>
            </div>
            <hr>
            <div style="margin-bottom: 15px">
              <span style="display:inline-block; width:100px">태그</span>
              <span v-html="htmlTag">
              </span>
              <input v-on:keyup.enter="addTag" v-on:keydown.delete="deleteTag" v-model="tag" placeholder="태그 입력 ">
            </div>
            <hr>
            <div style="margin-bottom: 15px">
              <span style="display:inline-block; width:100px">썸네일</span> 
              <el-upload action="#" list-type="picture-card" :auto-upload="false" style="display:inline">
                <i slot="default" class="el-icon-plus"></i>
                <div slot="file" slot-scope="{file}">
                  <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
                  <span class="el-upload-list__item-actions">
                    <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                      <i class="el-icon-download"></i>
                    </span>
                    <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                      <i class="el-icon-delete"></i>
                    </span>
                  </span>
                </div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
              </el-dialog>
            </div>
            <hr>
            <div style="margin-bottom: 15px">
              <span style="display:inline-block; width:100px;">공개 여부</span> 
              <el-radio-group v-model="postInfo.disclosure">
                <el-radio-button label="전체공개"></el-radio-button>
                <el-radio-button label="이웃공개"></el-radio-button>
                <el-radio-button label="비공개" ></el-radio-button>
              </el-radio-group>
            </div>
            <hr>
            <div style="margin-bottom: 15px;">
              <span style="display:inline-block; width:100px">작성 시간</span> 
              현재 <el-switch v-model="isReserve" on-text=true off-text=false></el-switch> 예약
              <el-date-picker v-if="isReserve" v-model="postInfo.regtime" type="datetime" placeholder="Select date and time">
              </el-date-picker>
            </div>
            <hr>
            <el-button @click="write" style="float:right">포스트 작성</el-button>
          </div>
        </div>
      </section>
    </div>
  </transition>
</template>

<script>
import { VueEditor } from 'vue2-editor'
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
        disclosure: '',
        regtime: '',
      },
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      tag: '',
      tags: []
    }
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
      .post('./post', {
        seq: this.$store.state.userInfo.seq,
        seq_blog: this.$store.state.userInfo.seq,
        title: this.postInfo.title,
        content: this.postInfo.content,
        disclosure: this.postInfo.disclosure,
        img_url: this.dialogImageUrl
      })
      
      this.postInfo.img_url = this.dialogImageUrl
      this.postInfo.tags = this.tags
      console.log(this.dialogImageUrl)
      console.log(this.postInfo)
    },
    handleRemove(file) {
      console.log(file);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleDownload(file) {
      console.log(file);
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

<style>
.ql-editor{
  min-height: 400px;
  width: 50%;
}
.tag{
  background:gray;
}
</style>>