<template>
  <transition name="el-zoom-in-top">
      <div class="content-wrapper">
      <section class="content" style="padding-bottom:30px">
        <div class="box" style="width:70%; margin: 0 auto; float: none;">
          <el-tabs type="border-card">
            <el-tab-pane label="프로젝 트 수정">
              <!-- 프로젝트 정보 시작 -->
              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">* 제목</p>
                </div>
                <div class="col-sm-9">
                  <el-input style="padding:10px;" placeholder="PROJECT TITLE" v-model="title"> </el-input>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">* 프로젝트 개요</p>
                </div>
                <div class="col-sm-9">
                  <el-input style="padding:10px;" type="textarea" :rows="5" placeholder="PROJECT SUMMARY" v-model="summary"> </el-input>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">* 개발 기간</p>
                </div>
                <div class="col-sm-9" style="padding-top:15px;padding-left:25px;">
                  <el-date-picker v-model="start_date" type="date"></el-date-picker><span style="font-size:20px;">&nbsp; ~ &nbsp;</span>
                  <el-date-picker v-model="finish_date" type="date"></el-date-picker>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">* 사용 스택</p>
                </div>
                <div class="col-sm-9">
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
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">* 역할</p>
                </div>
                <div class="col-sm-9">
                  <el-input style="padding:10px;" type="textarea" :rows="5" placeholder="PROJECT ROLE" v-model="role"> </el-input>
                </div>
              </div><hr>

                <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">* Github url</p>
                </div>
                <div class="col-sm-9">
                  <el-input style="padding:10px;" v-model="github_url"> </el-input>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">참고 url</p>
                </div>
                <div class="col-sm-9">
                  <el-input style="padding:10px;" v-model="etc_url"> </el-input>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">참조 url</p>
                </div>
                <div class="col-sm-9">
                  <el-input style="padding:10px;" v-model="rep_url"> </el-input>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title" style="height:490px;">
                  <p class="pull-right">추가 설명</p>
                </div>
                <div class="col-sm-9">
                  <vue-editor id="project-editor" v-model="content" style="padding:10px;"></vue-editor>
                </div>
              </div><hr>
              <!--  프로젝트 정보 끝 -->

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
                  <el-dialog :visible.sync="isImgVisible">
                    <img width="100%" :src="img_url" alt="">
                  </el-dialog>
                  </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">공개 여부</p>
                </div>
                <div class="col-sm-9" style="padding:15px 0px 0px 25px">
                  <el-radio-group v-model="disclosure">
                    <el-radio-button label="전체공개"></el-radio-button>
                    <el-radio-button label="이웃공개"></el-radio-button>
                    <el-radio-button label="비공개"></el-radio-button>
                  </el-radio-group>
                </div>
              </div><hr>

              <div class="row">
                <div class="col-sm-2 pjt-title">
                  <p class="pull-right">작성 시간</p>
                </div>
                <div class="col-sm-9" style="padding:15px 0px 0px 25px">
                  현재 <el-switch v-model="isReserve" on-text=true off-text=false></el-switch> 예약
                  <el-date-picker v-if="isReserve" v-model="regtime" type="datetime" placeholder="Select date and time">
                  </el-date-picker>
                </div>
              </div><hr>
              <el-button @click="write" style="float:right">프로젝트 수정</el-button>
            </el-tab-pane>
          </el-tabs>
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
      // 포스트 기본 정보
      seq : '',
      seq_blog : '',
      title : '',
      disclosure : '',
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

      start_date_temp: '',
      finish_date_temp: '',
      regtime_temp: '',
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
  mounted(){
    this.getProjectInfo()
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
      else if(this.stack.length===0)
        this.$message.warning('사용 스택을 입력해 주세요.')
      else if(this.role==='')
        this.$message.warning('프로젝트 역할을 입력해 주세요.')
      else if(this.github_url==='')
        this.$message.warning('깃허브 주소를 입력해주세요.')
      else if(this.img_url==='')
        this.$message.warning("썸네일을 등록해 주세요")
      else
      {
        if(this.start_date_temp != this.start_date)
          this.start_date = this.date_to_str(this.start_date)
        if(this.finish_date_temp != this.finish_date)
          this.finish_date = this.date_to_str(this.finish_date)
        if(this.regtime_temp = this.regtime)
          this.setRegtime()
        this.setDisclosure()
        this.setTag()
        
        console.log(this.seq)
        console.log(this.seq_blog)
        console.log(this.title)
        console.log(this.disclosure)
        console.log(this.img_url)
        console.log(this.regtime)
        console.log(this.summary)
        console.log(this.start_date)
        console.log(this.finish_date)
        console.log(this.role)  
        console.log(this.github_url)
        console.log(this.etc_url)  
        console.log(this.rep_url)  
        console.log(this.content)  
        http
        .put('project', {
          content: this.content,
          disclosure: this.disclosure,
          etc_url: this.etc_url,
          github_url: this.github_url,
          img_url: this.img_url,
          regtime: this.regtime,
          role: this.role,
          seq: this.seq,
          start_date: this.start_date,
          summary: this.summary,
          title: this.title,
          finish_date: this.finish_date,
          rep_url: this.rep_url,
        })
        .then(({data}) => {
          // 프로젝트 태그 등록하기
          http
          .post('./posttag', {
            seq_post: this.seq,
            tag: this.tags
          })
          // 프로젝트 스택 등록하기
          for(var i=0; i<this.stack.length; i++){
            http.post('./projectstack', {
              seq_post_project: this.seq,
              stack: this.stack[i]
            })
          }
          this.$message({
            type: 'success',
            message: '프로젝트 수정 완료.'
          });
          this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)  
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
          this.regtime =  year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
      }
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
        this.tag = ''
        this.renew()
        this.tag = ' '
        this.tag = ''
      }
    },
    deleteTag() {
      if(this.tag === ''){
        this.tags.pop()
        this.renew()
        this.tag=' '
        this.tag=''
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
          return year + "-" + month + "-" + date + " ";
      }
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
    getProjectInfo(){
      http
      .get('/project/'+this.$route.params.seq)
      .then(({data}) => {
        console.log(data)
        if(data.disclosure===1)
          this.disclosure = '전체공개'
        else if(data.disclosure===2)
          this.disclosure = '이웃공개'
        else
          this.disclosure = '비공개'
        this.seq = data.seq
        this.seq_blog = data.seq_blog
        this.title = data.title
        this.content = data.content
        this.regtime = data.regtime
        this.regtime_temp = data.regtime
        this.img_url = data.img_url
        this.summary = data.summary
        this.start_date = data.start_date
        this.start_date_temp = data.start_date
        this.finish_date = data.finish_date
        this.finish_date_temp = data.finish_date
        this.role = data.role
        this.github_url = data.github_url
        this.etc_url = data.etc_url
        this.rep_url = data.rep_url
        this.content = data.content
     
        http
        .get('/posttag/'+this.$route.params.seq)
        .then(({data}) => {
          for(let i=0; i<data.length; i++){
            this.tags.push(data[i].tag)
          }
          this.renew()
        })
        http
        .get('/projectstack/'+this.$route.params.seq)
        .then(({data}) => {
          for(let i=0; i<data.length; i++){
            this.stack.push(data[i].stack)
          }
        })
      })
    }
  }
}
</script>

<style>
#project-editor .ql-editor{
  min-height: 400px;
}
.pjt-title{
  padding-top: 20px;
}
</style>