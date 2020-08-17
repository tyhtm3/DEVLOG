<template>
  <transition name="el-zoom-in-top">
    <div>
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
          <div v-for="(role2,index) in roles" :key="index">
              <span @click="deleteRole(index)" style="cursor:pointer;">
            <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                <el-input style="padding:10px;" type="textarea" :rows="2" :value="role2" readonly></el-input>
            </el-tooltip>
              </span>
          <div class="pull-right" style="padding:0px 10px 0px 10px;">
          </div>
          </div>
          <el-input style="padding:10px;" type="textarea" :rows="4" placeholder="PROJECT ROLE" v-model="role"> </el-input>
          <div class="pull-right" style="padding:0px 10px 0px 10px;">
            <el-button @click="addRole">추가</el-button>
          </div>
        </div>
      </div>
      <hr>

      

        <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* Git url</p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" v-model="github_url"> </el-input>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">기타 url</p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" v-model="etc_url"> </el-input>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">참고 url</p>
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
          <vue-editor  v-model="content" style="padding:10px;"></vue-editor>
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
          <img v-if="img_url" :src="img_url" style="width:100%; height:100%; vertical-align:top">
          <i v-else slot="default" class="el-icon-plus"></i>
          </el-upload>
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

      
      
      <el-button @click="write" style="float:right">프로젝트 등록</el-button>
      <el-button @click="drafts" style="float:right">임시 보관</el-button>
      <el-button @click="save" style="float:right">임시 저장</el-button>
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
      github_url : '',
      etc_url : null,
      rep_url : null,
      content : null,
      // 기술스택
      stack : [],
      all_stack : [],
      // 역할
      roles : [],
      role: '',
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
    addRole(){
        if(this.role==''){
          this.$message.warning('프로젝트 역할을 입력해 주세요.')
        }else{
          this.roles.push(this.role)
          this.role=''
        }
    },
    deleteRole(index){
        this.roles.splice(index,1)
    },
    save(){
     // 필수 입력 확인받기
        if(this.title==='')
          this.$message.warning('프로젝트 제목을 입력해 주세요.')
        else if(this.summary=='')
          this.$message.warning('프로젝트 개요를 입력해 주세요.')
        else if(this.start_date==='')
          this.$message.warning('프로젝트 시작 날짜를 입력해 주세요.')
        else if(this.stack.length==0)
          this.$message.warning('사용 스택을 입력해 주세요.')
        else if(this.roles.length==0)
          this.$message.warning('프로젝트 역할을 입력해 주세요.')
        else if(this.github_url==='')
          this.$message.warning('깃허브 주소를 입력해주세요.')
         else if(this.img_url==='')
          this.$message.warning("썸네일을 등록해 주세요")
        else{
       
          this.setDisclosure()
          this.setRegtime()
          this.setTag()
      
          // 프로젝트 등록하기
          http.post('project', { 
            seq_blog : this.seq_blog,
            title:this.title,
            disclosure:this.disclosure, 
            img_url : this.img_url, 
            status : 'draft',
            regtime:this.regtime,
            summary : this.summary, 
            start_date : this.date_to_str(this.start_date),
            finish_date : this.date_to_str(this.finish_date),
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
          // 프로젝트 역할 등록하기
          http
          .post('./projectrole', {
            seq_post_project: data,
            role: this.roles
          })

          this.$message({
            type: 'success',
            message: '프로젝트 등록 완료.'
          });
          this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)  
        })     
      }
    },
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
        else if(this.roles.length==0)
          this.$message.warning('프로젝트 역할을 입력해 주세요.')
        else if(this.github_url==='')
          this.$message.warning('깃허브 주소를 입력해주세요.')
         else if(this.img_url==='')
          this.$message.warning("썸네일을 등록해 주세요")
        else{
       
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
            status : 'published',
            summary : this.summary, 
            start_date : this.date_to_str(this.start_date),
            finish_date : this.date_to_str(this.finish_date),
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
          // 프로젝트 역할 등록하기
          http
          .post('./projectrole', {
            seq_post_project: data,
            role: this.roles
          })

          this.$message({
            type: 'success',
            message: '임시저장 완료.'
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

      this.img_url = 'http://'.concat(res)

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