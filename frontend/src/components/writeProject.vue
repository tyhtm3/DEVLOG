<template>
 <transition name="el-zoom-in-top">
<div>

        <!-- 프로젝트 정보 :  프로젝트 명 / 프로젝트 개요 / 개발기간 / 기술스택 / 역할 / URL / 설명-->
             <h2> <input placeholder="PROJECT TITLE" style="border:none;padding-left:13px;margin-top:5px;" v-model="title"></h2>
            <hr>
                <div class="row">
                  <div class="col-sm-3 pjt-title ">
                    <p class="pull-right">* 프로젝트 개요</p>
                  </div>
                  <div class="col-sm-9">
                    <el-input style="padding:10px;" type="textarea" :rows="5" placeholder="PROJECT SUMMARY" v-model="summary"> </el-input>
                  </div>
                </div>
                    <div class="row">
                  <div class="col-sm-3 pjt-title " style="height:120px;">
                    <p class="pull-right">* 개발 기간</p>
                  </div>
                  <div class="col-sm-9" style="padding-top:15px;padding-left:25px;">
                    <el-date-picker v-model="start_date" type="date"></el-date-picker><span style="font-size:20px;">&nbsp; ~ &nbsp;</span>
                    <el-date-picker v-model="finish_date" type="date"></el-date-picker>
                  </div>
                </div>

                <div class="row">
                  <div class="col-sm-3 pjt-title " style="height:400px;">
                    <p class="pull-right">* 사용 스택</p>
                  </div>
                  <div class="col-sm-9">
                   <!-- <div v-for="(stack,index) in all_stack" :key="index">
                      <img class="media-object img-circle pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="width: 64px; height: 64px;margin-right:20px;">
                    </div> -->

                 <section class="selectProject" style="padding:15px;">
                  <el-transfer
                    :titles="['ALL STACK', 'YOUR STACK']"
                    filterable
                    :filter-method="filterMethod"
                    filter-placeholder="기술스택 검색"
                    v-model="stack"
                    :button-texts="['','']"
                    :data="all_stack">
                  </el-transfer>
                </section>

                  </div>
                </div>

                  <div class="row">
                  <div class="col-sm-3 pjt-title ">
                    <p class="pull-right">* 역할</p>
                  </div>
                  <div class="col-sm-9">
                    <el-input style="padding:10px;" type="textarea" :rows="5" placeholder="PROJECT ROLE" v-model="role"> </el-input>
                  </div>
                </div>

                 <div class="row">
                  <div class="col-sm-3 pjt-title " style="height:60px;">
                    <p class="pull-right">* Github url</p>
                  </div>
                  <div class="col-sm-9">
                    <input class="pjt-content" v-model="github_url" style="font-size:20px;">
                  </div>
                </div>

                 <div class="row">
                  <div class="col-sm-3 pjt-title " style="height:60px;">
                    <p class="pull-right">참고 url</p>
                  </div>
                  <div class="col-sm-9">
                    <input class="pjt-content" v-model="etc_url" style="font-size:20px;">
                  </div>
                </div>

                 <div class="row">
                  <div class="col-sm-3 pjt-title " style="height:120px;">
                    <p class="pull-right">참조 url</p>
                  </div>
                  <div class="col-sm-9">
                    <input class="pjt-content" v-model="rep_url" style="font-size:20px;">
                  </div>
                </div>

                 <div class="row">
                  <div class="col-sm-3 pjt-title " style="height:490px;padding-top:0px;">
                    <p class="pull-right">추가 설명</p>
                  </div>
                  <div class="col-sm-9">
                   <vue-editor id="project-editor" v-model="content"></vue-editor>
                  </div>
                </div>
                <!--  프로젝트 정보 끝 -->
      
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
        <el-upload action="https://jsonplaceholder.typicode.com/posts/" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" list-type="picture-card" style="display:inline">
          <i slot="default" class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="isImgVisible">
        <img width="100%" :src="img_url" alt="">
        </el-dialog>
      </div>
      <hr>
      <div style="margin-bottom: 15px">
        <span style="display:inline-block; width:100px;">공개 여부</span> 
        <el-radio-group v-model="disclosure">
          <el-radio-button label="전체공개" ></el-radio-button>
          <el-radio-button label="이웃공개" ></el-radio-button>
          <el-radio-button label="비공개" ></el-radio-button>
        </el-radio-group>
      </div>
      <hr>
      <div style="margin-bottom: 15px;">
        <span style="display:inline-block; width:100px">작성 시간</span> 
        현재 <el-switch v-model="isReserve" on-text=true off-text=false></el-switch> 예약
        <el-date-picker v-if="isReserve" v-model="regtime" type="datetime" placeholder="Select date and time">
        </el-date-picker>
      </div>
      <hr>
      <el-button @click="write" style="float:right">프로젝트 등록</el-button>
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
      // 포스트 기본 정보
      seq_blog : this.$store.state.userInfo.seq,
      title : '',
      disclosure : "전체공개",
      tag : '',
      tags : [],
      isReserve : false,
      regtime : null,
      img_url : null,
      isImgVisible: false,
      // 프로젝트 특화 정보
      summary : '',
      start_date : '',
      finish_date : null,
      role : '',
      github_url : '',
      etc_url : null,
      rep_url : null,
      content : null,
      stack : [],
      all_stack : [],
    }
  },

 created(){
      http.get('stack')
        .then(({data}) => {
          for(let i=0; i<data.length; i++){
          this.all_stack.push({
          label: data[i].stack,
          key:data[i].stack,
          initial: data[i].seq
          })
        }

     })
 },
  methods : {
     write(){
      // 필수 입력 확인받기
        if(this.title==='')
          this.$message.warning('프로젝트 제목을 입력해 주세요.')
        else if(this.summary=='')
          this.$message.warning('프로젝트 개요를 입력해 주세요.')
        else if(this.start_date==='')
          this.$message.warning('프로젝트 시작 날짜를 입력해 주세요.')
        else if(this.stack.length==0)
          this.$message.warning('사용 스택을 입력해 주세요.')
        else if(this.role==='')
          this.$message.warning('프로젝트 역할을 입력해 주세요.')
        else if(this.github_url==='')
          this.$message.warning('깃허브 주소를 입력해주세요.')
         else if(this.img_url==='')
          this.$message.warning("썸네일을 등록해 주세요")
        else
      {
       
       this.setDisclosure()
       this.setRegtime()
       this.setTag()
      
      // 프로젝트 등록하기
      http.post('project', { 
                          seq_blog : this.seq_blog,
                          title:this.title,
                          disclosure:this.disclosure, 
                          img_url : this.img_url, 
                          regtime:this.regtime,
                          summary : this.summary, 
                          start_date : this.date_to_str(this.start_date),
                          finish_date : this.date_to_str(this.finish_date),
                          role : this.role,
                          github_url : this.github_url,
                          etc_url : this.etc_url,
                          rep_url : this.rep_url,
                          content : this.content,
                        })
        .then(({data}) => {
        // data = project의 seq

        // 프로젝트 태그 등록하기
        http
        .post('./posttag', {
          seq_post: data,
          tag: this.tags
        })
        // 프로젝트 스택 등록하기
        
        for(var i=0; i<this.stack.length; i++){
         http.post('./projectstack', {
          seq_post_project: data,
          stack: this.stack[i]
        })
        }

      

        this.$message({
            type: 'success',
            message: '프로젝트 등록 완료.'
          });
         this.$router.push('/blog')  

      })
      
      }
     },
      // 공개 여부 셋팅
     setDisclosure(){
      if(this.disclosure === "전체공개")
        this.disclosure = 1
      else if(this.disclosure === "이웃공개")
        this.disclosure = 2
      else
        this.disclosure = 3
     },
     // 날짜 셋팅
     setRegtime(){
        if(this.isReserve){
        var year = this.regtime.getFullYear();
        var month = this.regtime.getMonth() + 1;
        if(month<10) month = '0' + month;
        var date = this.regtime.getDate();
        if(date<10) date = '0' + date;
        var hour = this.regtime.getHours();
        if(hour<10) hour = '0' + hour;
        var min = this.regtime.getMinutes();
        if(min<10) min = '0' + min;
        var sec = this.regtime.getSeconds();
        if(sec<10) sec = '0' + sec;
        this.regtime =  year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;}
        else
        this.regtime = null
      },
     // 태그 셋팅
     setTag(){
       if(this.tags.length==0)
          this.tags=null
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
    // 날짜 포맷 변경
    date_to_str(format){
        if(format!==null){
        var year = format.getFullYear();
        var month = format.getMonth() + 1;
        if(month<10) month = '0' + month;
        var date = format.getDate();
        if(date<10) date = '0' + date;
        var hour = format.getHours();
        if(hour<10) hour = '0' + hour;
        var min = format.getMinutes();
        if(min<10) min = '0' + min;
        var sec = format.getSeconds();
        if(sec<10) sec = '0' + sec;
        return year + "-" + month + "-" + date + " ";}
        else{
          return null
        }
      },

     // 썸네일 사진 업로드
     handleAvatarSuccess(res, file) {
        
      var frm = new FormData();
      frm.append("upload_file", file.raw);

        http.post('user/upload',frm,{headers: {
          'Content-Type': 'multipart/form-data'
        }})
      .then(({data}) => {
        this.img_url = 'http://'.concat(data)
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
    filterMethod(query, item) {
        return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
    },
  }
}
</script>

<style>
#project-editor .ql-editor{
  min-height: 400px;
}
</style>