<template>
  <transition name="el-zoom-in-top">
    <div>
      <!-- 프로젝트 정보 시작 -->
      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* 제목
            <el-tooltip class="item" effect="dark" content="프로젝트의 특징을 살릴 수 있는 제목을 적어주세요" placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" placeholder="프로젝트 제목을 입력하세요." v-model="title"> </el-input>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* 개요
            <el-tooltip class="item" effect="dark" content="프로젝트의 요약을 작성하는 칸입니다." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" type="textarea" :rows="5" placeholder="프로젝트 개요를 입력하세요." v-model="summary"> </el-input>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* 개발 기간
            <el-tooltip class="item" effect="dark" content="포트폴리오 생성시, 이곳에 입력된 개발 기간 데이터를 활용해 가장 최신 프로젝트부터 과거의 프로젝트까지 정렬해드립니다." placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-2" style="padding-top:15px;">
          <el-date-picker v-model="start_date" type="date"></el-date-picker>
        </div>
        <div class="col-sm-1" style="position:relative;padding-top:20px;margin-left:60px;">
          <span style="font-size:20px;">~</span>
        </div>
        <div class="col-sm-2" style="padding-top:15px;margin-left:-60px;">
          <el-date-picker v-model="finish_date" type="date"></el-date-picker>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* 사용 스택
            <el-tooltip class="item" effect="dark" content="포트폴리오 생성시, 이곳에서 선택한 기술 스택을 통해 포트폴리오를 시각화해드립니다." placement="right"><span class="ti-help-alt" style="position:relative;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-6">
          <section class="selectProject" style="padding-top:15px;">
            <el-transfer
              :titles="['기술스택 목록', '사용한 기술스택']"
              filterable
              :filter-method="filterMethod"
              filter-placeholder="기술스택 검색"
              v-model="stack"
              :button-texts="['','']"
              :data="all_stack">
            </el-transfer>
          </section>
        </div>
        <div class="col-sm-3 showProject" style="margin-top:16px;" >
        <div v-if="initial_all_stack" class="tocenter" style="padding-top:10px;margin-left:-5px;min-height:250px">
         <div style="margin-bottom:15px; font-size:15px"><b>기술스택 정보</b></div>
                <div style="margin-bottom:15px; text-align:center"><img :src=hoveredStackData.stack_img_url width="150px"/></div>
                <div class="row pjt-margin" style="padding-top:10px;">
                  <div class="col-sm-4">
                  <p>기술스택</p>
                  </div>
                  <div class="col-sm-8">
                  <p class="pjt-content">{{hoveredStackData.stack}}</p>
                  </div>
                </div>
        </div>
        </div>
      </div>
      
      <hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* 역할 
            <el-tooltip class="item" effect="dark" content="본인이 맡은 역할을 구체적으로 작성해 신뢰도를 높여주세요. 텍스트박스를 클릭하면 입력한 역할이 제거됩니다." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9" id="role-input" >
          <div v-for="(role2,index) in roles" :key="index" >
            <span @click="deleteRole(index)" style="cursor:pointer;">
              <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                  <el-input style="padding:10px;" type="textarea" :rows="2" :value="role2" readonly></el-input>
              </el-tooltip>
            </span>
            <div class="pull-right" style="padding:0px 10px 0px 10px;"></div>
          </div>
          <el-input style="padding:10px; display:inline-block;" type="textarea" :rows="4" placeholder="수행한 역할을 입력해주세요." v-model="role"> </el-input>
          <div class="pull-right" style="padding:10px; display:inline-block; height:60px!important; margin-bottom:auto; margin-top:auto;">
            <el-button @click="addRole" >추가</el-button>
          </div>
        </div>
        <div class="col-sm-2">
          
        </div>
      </div>
      <hr>

      

        <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">* Git url
            <el-tooltip class="item" effect="dark" content="프로젝트를 증명해주세요! 첨부한 Git을 통해 인사 담당자가 프로젝트에 대해 더욱 정확히 이해할 수 있습니다." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" v-model="github_url"> 
            <template slot="prepend">https://</template>
          </el-input>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">기타 url
            <el-tooltip class="item" effect="dark" content="프로젝트를 생동감있게 소개해주세요! 많은 데블로거들이 이곳에 시연 동영상 주소, 직접 제작한 웹페이지 주소 등을 첨부합니다." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" v-model="etc_url"> 
            <template slot="prepend">https://</template>
          </el-input>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title">
          <p class="pull-right">참고 url
            <el-tooltip class="item" effect="dark" content="프로젝트에 사용된 자료의 출처를 밝혀주세요! 프로젝트 자료의 출처가 명확하면 프로젝트에 대한 신뢰도가 높아집니다." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <el-input style="padding:10px;" v-model="rep_url"> 
            <template slot="prepend">https://</template>
          </el-input>
        </div>
      </div><hr>

      <div class="row">
        <div class="col-sm-2 pjt-title" style="height:490px;">
          <p class="pull-right">추가 설명
            <el-tooltip class="item" effect="dark" content="프로젝트에 대해 더 자세한 설명을 남기고싶다면? 이곳을 이용해주세요." placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
          </p>
        </div>
        <div class="col-sm-9">
          <vue-editor id="project-editor" v-model="content" style="padding:10px;"></vue-editor>
        </div>
      </div><hr>
      <!--  프로젝트 정보 끝 -->

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
            <el-tooltip class="item" effect="dark" content="포트폴리오 생성시, 썸네일을 통해 포트폴리오를 시각화해드립니다. 화려한 썸네일을 활용한다면 피드 페이지에서 더욱 돋보일 수 있어요." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
          </p>
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
          <el-radio class="radio" v-model="isReserve" label="0">현재</el-radio>
          <el-radio class="radio" v-model="isReserve" label="1">예약</el-radio>
          <el-date-picker v-if="isReserve==1"  style="margin-left:10px;" v-model="regtime" type="datetime" placeholder="Select date and time">
          </el-date-picker>
        </div>
      </div><hr>

      
      
      <el-button @click="write" style="float:right">프로젝트 등록</el-button>
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
                    <div @click="showDraft(index);"  @mouseenter="showDeleteButton(index);" @mouseleave="hideDeleteButton(index);" style="margin-bottom:-20px;" class="draft" v-for="(draft,index) in drafts" :key="index">
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
      isReserve : "0",
      regtime : null,
      img_url : null,
      isImgVisible: false,
      status : null,
      // 프로젝트 특화 정보
      summary : '',
      start_date : '',
      finish_date : null,
      github_url : '',
      etc_url : null,
      rep_url : null,
      content : null,
      // 기술스택
      initial_all_stack: [],
      stack : [],
      all_stack : [],
      // 호버된 기술스택
      hoveredStackData:'',



      // 역할
      roles : [],
      role: '',
      
      start_date_temp: '',
      finish_date_temp: '',
      regtime_temp: '',

      // 임시저장
      drafts: [],
      selectDialogVisible: false,

    
    }
  },

 created(){
      http.get('stack')
        .then(({data}) => {
          this.initial_all_stack=data;
          this.hoveredStackData = this.initial_all_stack[0]
          for(let i=0; i<data.length; i++){
          this.all_stack.push({
          label: data[i].stack,
          key:data[i].stack,
          initial: data[i].seq
          })
        }

     })
     http
        .get('./project/draft', {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }}).then(({data})=>{
          this.drafts = data;
     })
 },
 mounted(){
   var vm = this;
    $(".el-transfer-panel__body").mouseover(function(event) {
      event.preventDefault();
      if(event.target.className==''){
        if($(this).parent().parent().parent()[0].className=='selectProject'){
          vm.putHoveredStack(event.target.innerText);
        }
      }
    });
 },
  methods : {
    putHoveredStack(input){
      this.hoveredStack=input;
      for(var i=0; i<this.initial_all_stack.length; i++){
        if(this.initial_all_stack[i].stack==this.hoveredStack){
          this.hoveredStackData=this.initial_all_stack[i];
        }
      }
    },


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
    // 임시 저장글 클릭시 화면에 나타나게 함
    showDraft(index){
      
      http
      .get('/project/'+this.drafts[index].seq)
      .then(({data}) => {
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
        this.github_url = data.github_url
        this.etc_url = data.etc_url
        this.rep_url = data.rep_url
        this.status = data.status
        http
        .get('/posttag/'+this.drafts[index].seq)
        .then(({data}) => {
          this.tags = []
          for(let i=0; i<data.length; i++){
            this.tags[i] = data[i].tag
          }
          this.renew()
        })
        http
        .get('/projectstack/'+this.drafts[index].seq)
        .then(({data}) => {
          this.stack = []
          for(let i=0; i<data.length; i++){
            this.stack.push(data[i].stack)
          }
        })
        http
        .get('/projectrole/'+this.drafts[index].seq)
        .then(({data}) => {
          for(var i=0;i<data.length;i++){
              this.roles[i] = data[i].role
          }
           this.selectDialogVisible=false;
        })
        
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
               .delete('project/'+this.drafts[index].seq)
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
       
          if(this.start_date_temp != this.start_date)
          this.start_date = this.date_to_str(this.start_date)
          if(this.finish_date_temp != this.finish_date)
          this.finish_date = this.date_to_str(this.finish_date)
          if(this.regtime_temp = this.regtime)
          this.setRegtime()
          
          this.setDisclosure()
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
            start_date : this.start_date,
            finish_date : this.finish_date,
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
            message: '프로젝트 임시 저장 완료.'
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
          this.setTag()

          // 임시저장글을 등록할경우
          if(this.status=='draft'){  
          if(this.start_date_temp != this.start_date)
          this.start_date = this.date_to_str(this.start_date)
          if(this.finish_date_temp != this.finish_date)
          this.finish_date = this.date_to_str(this.finish_date)
          if(this.regtime_temp = this.regtime)
          this.setRegtime()
          http
        .put('project', {
          content: this.content,
          disclosure: this.disclosure,
          etc_url: this.etc_url,
          github_url: this.github_url,
          img_url: this.img_url,
          regtime: this.regtime,
          seq: this.seq,
          start_date: this.start_date,
          summary: this.summary,
          title: this.title,
          finish_date: this.finish_date,
          rep_url: this.rep_url,
          status : 'published',
        })
        .then(({data}) => {
          // 프로젝트 태그 등록하기
          http
          .post('./posttag', {
            seq_post: this.seq,
            tag: this.tags
          })

          http.delete('./projectstack/'+this.seq)
          .then(()=>{
              for(var i=0; i<this.stack.length; i++){
              http.post('./projectstack', {
                seq_post_project: this.seq,
                stack: this.stack[i]
              })
            }
          })

          // 프로젝트 역할 등록하기
          http
          .post('./projectrole', {
            seq_post_project: this.seq,
            role: this.roles
            })
          })
         
          this.$message({
            type: 'success',
            message: '프로젝트 등록 완료.'
          });
          this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)   
          }
          else{
          // 프로젝트 등록하기
          
          this.setRegtime()
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
            message: '프로젝트 등록 완료.'
          });
          this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)  
        })     
      }
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
      if(this.isReserve==1){
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
     showDeleteButton(index){
      $(".hideDeleteButton").eq(index).show();
    },
    hideDeleteButton(index){
      $(".hideDeleteButton").eq(index).hide();
    },
  }
}
</script>

<style>
#post-editor .ql-editor{
  min-height: 400px;
}
#project-editor .ql-editor{
  min-height: 400px;
}
#role-input .el-textarea__inner{
    height:40px !important;
  }

  #role-input .el-textarea{
    width: 80%;
  }

</style>
<style scoped>
  .showProject{
    /* display:block; */
    margin-left:-25px;
    float: left;
    background-color: #d5cbe42a;
    width: 255px;
    min-height: 300px;
    border-radius: 6px;
  }
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