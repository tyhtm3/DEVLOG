<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <section class="content" style="padding-bottom:30px">
        <div class="box" style="width:70%; margin: 0 auto; float: none;">
          <el-tabs type="border-card">
            <el-tab-pane label="포트폴리오 수정">
              <div class="box-body" style="align:center; min-height:400px; max-width:800px; margin:auto; padding : 0px;">
                <div class="col-xs-12 col-sm-12" style="padding:0px">
                  <hr>
                    <div style="margin-bottom:15px; font-size:20px;"><b>개인정보 입력</b></div>
                    <hr>
                  <dl class="dl-horizontal-profile">
                    <dt>이름</dt>
                    <dd>
                      <el-input v-model="name" style="width: 50%; border: 0px"></el-input>
                      <el-tooltip class="item" effect="dark" content="사진 비율 : 3x4" placement="right">
                      <el-upload
                      class="avatar-uploader"
                      action="http://i3a402.p.ssafy.io:8090/devlog/api/user/upload"
                      :show-file-list="false"
                      :on-success="handleAvatarSuccess"
                      :before-upload="beforeAvatarUpload">
                      <img v-if="imageUrl" :src="imageUrl" class="avatar2">
                      <i v-else class="el-icon-plus avatar-uploader-icon2"></i>
                      </el-upload>
                      </el-tooltip>
                    </dd>
                    <dt>연락처</dt>
                    <dd><el-input v-model="tel" style="width: 50%;"></el-input></dd>
                    <dt>이메일</dt>
                    <dd><el-input v-model="email" style="width: 50%;"></el-input></dd>
                    <dt>GIT 주소</dt>
                    <dd>
                      <el-input v-model="giturl" style="width: 50%;">
                        <template slot="prepend">https://</template>
                      </el-input>
                    </dd>
                  </dl>
                  </div>
                    <div class="col-xs-12 col-sm-12" style="padding:0px">
                    <hr>
                    <div style="margin-bottom:15px; font-size:20px"><b>프로젝트 정보 입력</b></div>
                    <hr>
                  </div>
                  <div>
                  <!-- 왼쪽 프로젝트 선택 부분 -->
                  <section class="selectProject">
                    <el-transfer
                      style="margin-bottom: 30px"
                      :titles="['내 프로젝트', '선택된 프로젝트']"
                      filterable
                      :filter-method="filterMethod"
                      filter-placeholder="프로젝트 검색"
                      v-model="includedProject"
                      :button-texts="['','']"
                      :data="projectList">
                    </el-transfer>
                  </section>
                  <!-- 왼쪽 프로젝트 선택 부분 끝-->
                  <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 -->
                  <section v-if="projectInfoList.length>0" class="showProject">
                    <div class="tocenter" style="padding: 10px">
                      <div style="margin-bottom:15px; font-size:15px"><b>프로젝트 정보</b></div>
                      <div class="row pjt-margin">
                        <div class="col-sm-4">
                        <p>제목</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">{{projectInfoList[0].title}}</p>
                        </div>
                      </div>
                      <div class="row pjt-margin">
                        <div class="col-sm-4">
                        <p>개요</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">{{projectInfoList[0].summary}}</p>
                        </div>
                      </div>
                      <div class="row pjt-margin">
                        <div class="col-sm-4">
                        <p>기간</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">{{projectInfoList[0].start_date}} ~ <br>{{projectInfoList[0].finish_date}}</p>
                        </div>
                      </div>
                      <div class="row pjt-margin">
                        <div class="col-sm-4">
                        <p>스택</p>
                        </div>
                      </div>
                      <div class="row pjt-margin">
                        <div class="col-sm-4">
                        <p>역할</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">{{projectInfoList[0].role}}</p>
                        </div>
                      </div>
                      <div class="row" v-if="projectInfoList[0].github_url">
                        <div class="col-sm-4">
                        <p>Git</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content"><a href="#" @click="goUrl(projectInfoList[0].github_url)">{{projectInfoList[0].github_url}}</a></p>
                        </div>
                      </div>
                      <div class="row" v-if="projectInfoList[0].etc_url">
                        <div class="col-sm-4">
                        <p>기타 Url</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">{{projectInfoList[0].etc_url}}</p>
                        </div>
                      </div>
                      <div class="row" v-if="projectInfoList[0].rep_url">
                        <div class="col-sm-4">
                        <p>참고 Url</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">{{projectInfoList[0].rep_url}}</p>
                        </div>
                      </div>
                    </div>
                  </section>
                  <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 끝-->
                  </div>
                    <div class="col-xs-12 col-sm-12" style="padding:0px">
                    <hr>
                    <div style="margin-bottom:15px; font-size:20px"><b>기술스택 정보 입력</b></div>
                    <hr>
                  </div>
                  <div>
                  <div class="selectStacks">
                  <!-- 왼쪽 기술스택 선택 부분 -->
                  <el-transfer
                    :titles="['내 기술스택', '선택된 기술스택']"
                    filterable
                    :filter-method="filterMethod"
                    filter-placeholder="기술스택 검색"
                    v-model="includedStack"
                    :button-texts="['','']"
                    :data="stackList">
                  </el-transfer>
                  <!-- 왼쪽 기술스택 선택 부분 끝-->
                  </div>
                  <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 -->
                  <div v-if="stackInfoList.length>0" class="showProject">
                    <div class="tocenter" style="padding: 10px">
                      <div style="margin-bottom:15px; font-size:15px"><b>기술스택 정보</b></div>
                      <div style="margin-bottom:15px; text-align:center"><img :src=stackInfoList[0].stack_img_url width="200px"/></div>
                      <div class="row pjt-margin">
                        <div class="col-sm-4">
                        <p>기술스택</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">{{stackInfoList[0].stack}}</p>
                        </div>
                      </div>
                      <div class="row pjt-margin">
                        <a href="#">
                        <div class="col-sm-4">
                        <p>활용 1)</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">abc프로젝트</p>
                        </div>
                        </a>
                      </div>
                      <div class="row pjt-margin">
                        <a href="#">
                        <div class="col-sm-4">
                        <p>활용 2)</p>
                        </div>
                        <div class="col-sm-8">
                        <p class="pjt-content">def프로젝트</p>
                        </div>
                        </a>
                      </div>
                    </div>
                  </div>
                  <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 끝-->
                </div>
                <hr>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
          
     <!-- 자격증 -->
    <div class="col-xs-12 col-sm-12" style="padding:0px">

            <hr>
              <div style="margin-bottom:15px; font-size:20px;"><b>자격증</b>
                <el-tooltip class="item" effect="dark" content="내가 취득한 자격증의 정보를 입력해주세요. 취득년월와 자격증명을 정확하게 입력해주세요." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
              <hr>

          <div v-for="(certification,index) in certifications" :key="index" >
            <span @click="deleteCertification(index)" style="cursor:pointer;">
              <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                  <div class="row" style="margin-bottom:10px;">
                  <div class="col-sm-3">
                  <el-input :value="certification.date" readonly></el-input>
                  </div>
                  <div class="col-sm-9">
                  <el-input :value="certification.certification" readonly></el-input>
                 </div>
                  </div>
                  
              </el-tooltip>
            </span>
          </div>

          <div class="pull-right" style="padding:0px 10px 0px 10px;"></div>
     
          <div class="row">
          <div class="col-sm-3">
          <el-input placeholder="취득년월" v-model="certification.date"> </el-input>
          </div>
          <div class="col-sm-9">
          <el-input placeholder="자격증을 입력해주세요" v-model="certification.certification"> </el-input>
          </div>
          </div>

          <div class="pull-right" style="padding:10px; display:inline-block; height:60px!important; margin-bottom:auto; margin-top:auto;">
            <el-button @click="addCertification" >추가</el-button>
          </div>
          </div>           
          <!-- 자격증 끝 -->

         <!-- 기술 -->
    <div class="col-xs-12 col-sm-12" style="padding:0px">

            <hr>
              <div style="margin-bottom:15px; font-size:20px;"><b>프로그래밍 언어 능력</b>
                <el-tooltip class="item" effect="dark" content="나의 프로그래밍 역량을 인사담당자에게 어필해보세요. 능력은 1~5 사이의 숫자를 입력해야 합니다." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
              <hr>

          <div v-for="(skill,index) in skills" :key="index" >
            <span @click="deleteSkill(index)" style="cursor:pointer;">
              <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                  <div class="row" style="margin-bottom:10px;">
                  <div class="col-sm-3">
                  <el-input :value="skill.level" readonly></el-input>
                  </div>
                  <div class="col-sm-9">
                  <el-input :value="skill.skill" readonly></el-input>
                 </div>
                  </div>
              </el-tooltip>
            </span>
          </div>

          <div class="pull-right" style="padding:0px 10px 0px 10px;"></div>
     
          <div class="row">
          <div class="col-sm-3">
          <el-input placeholder="능력" v-model="skill.level"> </el-input>
          </div>
          <div class="col-sm-9">
          <el-input placeholder="프로그래밍 언어" v-model="skill.skill"> </el-input>
          </div>
          </div>

          <div class="pull-right" style="padding:10px; display:inline-block; height:60px!important; margin-bottom:auto; margin-top:auto;">
            <el-button @click="addSkill" >추가</el-button>
          </div>
          </div>           
          <!-- 기술 끝 -->


          <!-- 외국어 능력 (language, level) 입력하기 -->
        <div class="col-xs-12 col-sm-12" style="padding:0px">

            <hr>
              <div style="margin-bottom:15px; font-size:20px;"><b>외국어 능력</b>
                <el-tooltip class="item" effect="dark" content="나의 외국어 역량을 인사담당자에게 어필해보세요. 능력은 1~5 사이의 숫자를 입력해야 합니다" placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
              <hr>

          <div v-for="(language,index) in languages" :key="index" >
            <span @click="deleteLanguage(index)" style="cursor:pointer;">
              <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                  <div class="row" style="margin-bottom:10px;">
                  <div class="col-sm-3">
                  <el-input :value="language.level" readonly></el-input>
                  </div>
                  <div class="col-sm-9">
                  <el-input :value="language.language" readonly></el-input>
                 </div>
                  </div>
              </el-tooltip>
            </span>
          </div>

          <div class="pull-right" style="padding:0px 10px 0px 10px;"></div>
     
          <div class="row">
          <div class="col-sm-3">
          <el-input placeholder="역량" v-model="language.level"> </el-input>
          </div>
          <div class="col-sm-9">
          <el-input placeholder="외국어" v-model="language.language"> </el-input>
          </div>
          </div>

          <div class="pull-right" style="padding:10px; display:inline-block; height:60px!important; margin-bottom:auto; margin-top:auto;">
            <el-button @click="addLanguage" >추가</el-button>
          </div>
          </div>           

          <!-- 언어 끝 -->


          <!-- 학력 -->
         
          <div class="col-xs-12 col-sm-12" style="padding:0px">

            <hr>
              <div style="margin-bottom:15px; font-size:20px;"><b>학력</b>
                <el-tooltip class="item" effect="dark" content="포트폴리오에 담길 내 학력 정보를 작성해보세요. 인사 담당자에게 보여질 항목이므로 정확한 정보를 입력해주세요." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
              <hr>

          <div v-for="(education,index) in educations" :key="index" >
            <span @click="deleteEducation(index)" style="cursor:pointer;">
              <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                  <div class="row" style="margin-bottom:10px;">
                  <div class="col-sm-3">
                  <el-input :value="education.major" readonly></el-input>
                  </div>
                  <div class="col-sm-3">
                  <el-input :value="education.name" readonly></el-input>
                 </div>
                 <div class="col-sm-3">
                  <el-input :value="education.start" readonly></el-input>
                 </div>
                 <div class="col-sm-3">
                  <el-input :value="education.end" readonly></el-input>
                 </div>
                  </div>
              </el-tooltip>
            </span>
          </div>

          <div class="pull-right" style="padding:0px 10px 0px 10px;"></div>
     
          <div class="row">
          <div class="col-sm-3">
          <el-input placeholder="전공" v-model="education.major"> </el-input>
          </div>
          <div class="col-sm-3">
          <el-input placeholder="학교명" v-model="education.name"> </el-input>
          </div>
          <div class="col-sm-3">
          <el-input placeholder="입학년월" v-model="education.start"> </el-input>
          </div>
          <div class="col-sm-3">
          <el-input placeholder="졸업년월" v-model="education.end"> </el-input>
          </div>
          </div>

          <div class="pull-right" style="padding:10px; display:inline-block; height:60px!important; margin-bottom:auto; margin-top:auto;">
            <el-button @click="addEducation" >추가</el-button>
          </div>
          </div>           

          <!-- 학력 끝 -->

          <!-- 경력 -->
          <div class="col-xs-12 col-sm-12" style="padding:0px">

            <hr>
              <div style="margin-bottom:15px; font-size:20px;"><b>경력</b>
                <el-tooltip class="item" effect="dark" content="포트폴리오에 담길 내 경력 정보를 작성해보세요. 인사 담당자에게 보여질 항목이므로 정확한 정보를 입력해주세요." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
              <hr>

          <div v-for="(experience,index) in experiences" :key="index" >
            <span @click="deleteExperience(index)" style="cursor:pointer;">
              <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                  <div class="row" style="margin-bottom:10px;">
                  <div class="col-sm-3">
                  <el-input :value="experience.position" readonly></el-input>
                  </div>
                  <div class="col-sm-3">
                  <el-input :value="experience.company" readonly></el-input>
                 </div>
                 <div class="col-sm-3">
                  <el-input :value="experience.start" readonly></el-input>
                 </div>
                 <div class="col-sm-3">
                  <el-input :value="experience.end" readonly></el-input>
                 </div>
                  </div>
              </el-tooltip>
            </span>
          </div>

          <div class="pull-right" style="padding:0px 10px 0px 10px;"></div>
     
          <div class="row">
          <div class="col-sm-3">
          <el-input placeholder="직위" v-model="experience.position"> </el-input>
          </div>
          <div class="col-sm-3">
          <el-input placeholder="회사명" v-model="experience.company"> </el-input>
          </div>
          <div class="col-sm-3">
          <el-input placeholder="입사년월" v-model="experience.start"> </el-input>
          </div>
          <div class="col-sm-3">
          <el-input placeholder="퇴사년월" v-model="experience.end"> </el-input>
          </div>
          </div>

          <div class="pull-right" style="padding:10px; display:inline-block; height:60px!important; margin-bottom:auto; margin-top:auto;">
            <el-button @click="addExperience" >추가</el-button>
          </div>
          </div>           

          <!-- 경력 끝 -->

          <!-- 소셜 -->
          <div class="col-xs-12 col-sm-12" style="padding:0px">

            <hr>
              <div style="margin-bottom:15px; font-size:20px;"><b>소셜 정보</b>
                <el-tooltip class="item" effect="dark" content="내 소셜 정보를 입력하여 개성을 어필해보세요." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
              <hr>

          <div v-for="(social,index) in socials" :key="index" >
            <span @click="deleteSocial(index)" style="cursor:pointer;">
              <el-tooltip class="item" effect="dark" content="클릭시 제거됩니다." placement="right">
                  <div class="row" style="margin-bottom:10px;">
                  <el-input :value="social.link" readonly style="padding-left:20px;padding-right:20px;"></el-input>
                  </div>
              </el-tooltip>
            </span>
          </div>

          <div class="pull-right" style="padding:0px 10px 0px 10px;"></div>
     
          <div class="row">
          <el-input placeholder="소셜정보" v-model="social.link" style="padding-left:20px;padding-right:20px;" > </el-input>
          </div>

          <div class="pull-right" style="padding:10px; display:inline-block; height:60px!important; margin-bottom:auto; margin-top:auto;">
            <el-button @click="addSocial" >추가</el-button>
          </div>
          </div> 
     
          <!-- 소셜 끝 -->






                <br>
                <div class="col-xs-12 col-sm-12" style="padding:0px">
                  <hr>
                  <div style="margin-bottom:15px; font-size:20px"><b>포트폴리오 정보 입력</b></div>
                  <hr>
                  <dl class="dl-horizontal-profile">
                    <dt>TITLE</dt>
                    <dd><el-input v-model="portfolioTitle" style="width: 70%;"></el-input></dd>
                    <dt>CONTENT</dt>
                    <dd><el-input v-model="portfolioContent" style="width: 70%;"></el-input></dd>
                    <dt>자기 소개</dt>
                    <dd><el-input v-model="introduction" style="width: 70%;"></el-input></dd>
                    <dt>주소</dt>
                    <dd><el-input v-model="address" style="width: 70%;"></el-input></dd>
                    <dt>희망 직무</dt>
                    <dd><el-input v-model="objective" style="width: 70%;"></el-input></dd>

              <dt>대표 포트폴리오 설정</dt>
              
              <dd>
              <el-switch v-model="portfolioRepresentation" on-color="#13ce66" off-color="#ff4949"  style="margin-top:10px;"  :on-value="1" :off-value="0"> </el-switch>
              </dd>

                  </dl>
                  <hr>
                </div>
                <el-button @click="updatePortfolio" style="float:right; margin-bottom: 20px">포트폴리오 수정</el-button>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </section>
    </div>
  </transition>
</template>
<script>
import http from '../util/http-common'
import App from '../App.vue'
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState(['userInfo']),
  },
  data: () => {
    return {
      // 포트폴리오 기본정보
      portfolioTitle: '',
      portfolioContent: '',
      portfolioRepresentation : 0,
      portfolioSeq: '',
      // 개인정보
      seq: '',
      name: '',
      email: '',
      tel: '',
      birth: '',
      giturl: '',
      imageUrl: '',
      regtime: '',
      // 플젝정보
      projectList: [],
      includedProject: [],
      projectInfoList:[],
      // 스택정보
      stackList: [],
      includedStack: [],
      stackInfoList:[],

      // ETC

      // 자격증정보
      certification: {
        date: '',
        certification: ''
      },
      certifications : [], // 저장할 자격증
      // 언어 기술 정보 
      language: {
        level: '',
        language: ''
      },
      languages : [], 

      // 기술 스킬 정보
      skill: {
        level: '',
        skill: ''
      },
      skills : [], 

      // 학력 정보
      education: {
        major: '',
        name: '',
        start: '',
        end: '',
      },
      educations : [], 
      
      // 경력
      experience: {
        position: '',
        company: '',
        start: '',
        end: '',
      },
      experiences : [], 

      // 소셜 정보
      social: {
        link: '',
        icon: '',
      },
      socials : [], 

      // 추가 인적사항 (예시 example tooltip쓰기)
      introduction : '',
      address : '',
      objective : '',
            
    }
  },
  created() {
    this.portfolioSeq =  this.$route.params.seq
    this.$store.state.loginFormVisible = false
    this.getStackList(this.userInfo.seq)
    this.getProjectList(this.userInfo.seq)
    
  },
  mounted(){
    this.getPortfolioInfo()
    this.getIncludedProjectList()
    this.getIncludedStackList()
    this.getCertificationList()
    this.getSkillList()
    this.getLanguageList()
    this.getEducationList()
    this.getExperienceList()
    this.getSocialList()
    this.getEtcInfo()
  },
  methods: {
    // 포트폴리오 정보
    getPortfolioInfo() {
      http
      .get('portfolio/'+this.portfolioSeq)
      .then(({data}) => {
        this.portfolioTitle = data.title
        this.portfolioContent = data.content
        this.portfolioRepresentation = data.representation

        this.seq = data.seq_blog
        this.name = data.name
        this.email = data.email
        this.tel = data.tel
        this.birth = data.birthday
        this.giturl = data.github_url
        this.imageUrl = data.profile_img_url
        this.regtime = data.regtime
      })
    },
    filterMethod(query, item) {
        return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
    },
    getProjectList(seq){
      http
      .post('project/blog', { seq_user:seq , seq_blog:seq, offset:0, limit:99999} )
      .then(({ data }) => {
        this.projectInfoList=data
        for(let i=0; i<data.length; i++){
          this.projectList.push({
            label: data[i].title,
            key: data[i].seq,
            initial: data[i].seq
          })
        }
      })
    },
    getIncludedProjectList(){
      http
      .get('portfoliopjt/'+this.portfolioSeq)
      .then(({data}) => {
        for(let i=0; i<data.length; i++){          
          this.includedProject.push(data[i].seq)
        }
      })
    },
    getStackList(seq){
      http
      .get('/stack')
      .then(({ data }) => {
        this.stackInfoList=data;
        for(let i=0; i<data.length; i++){
          this.stackList.push({
            label: data[i].stack,
            key: data[i].stack,
            initial: data[i].seq
          })
        }
      })
    },
    getIncludedStackList(){
      http
      .get('projectstack/'+this.portfolioSeq)
      .then(({data}) => {
        for(let i=0; i<data.length; i++){
          this.includedStack.push(data[i].stack)
        }
      })
    },
    getCertificationList(){
       http
      .get('portfolio/certification/'+this.portfolioSeq)
      .then(({data}) => {
        this.certifications = data

      })
    },
    getSkillList(){
       http
      .get('portfolio/skill/'+this.portfolioSeq)
      .then(({data}) => {
        this.skills = data
      })
    },
    getLanguageList(){
       http
      .get('portfolio/language/'+this.portfolioSeq)
      .then(({data}) => {
        this.languages = data
      })
    },
    getEducationList(){
       http
      .get('portfolio/info/education/'+this.portfolioSeq)
      .then(({data}) => {
        this.educations = data
      })
    },  
    getExperienceList(){
       http
      .get('portfolio/info/experience/'+this.portfolioSeq)
      .then(({data}) => {
        this.experiences = data
      })
    },      
    getSocialList(){
       http
      .get('portfolio/info/social/'+this.portfolioSeq)
      .then(({data}) => {
        this.socials = data
      })
    },  
    getEtcInfo(){
       http
      .get('portfolio/info/'+this.portfolioSeq)
      .then(({data}) => {
        this.introduction = data.introduction
        this.address = data.address
        this.objective = data.objective
      })
    },
    // 포트폴리오 업데이트
    updatePortfolio() {
      http
      .put('portfolio', {
        seq: this.portfolioSeq,
        content: this.portfolioContent,
        representation: this.portfolioRepresentation?1:0,
        birthday: null,
        email: this.email,
        github_url: this.giturl,
        img_url: null,
        name: this.name,
        profile_img_url: this.imageUrl,
        tel: this.tel,
        title: this.portfolioTitle,
      })
      .then(({ data }) => { 
        // 선택된 프로젝트 목록 업데이트
        http
        .post('portfoliopjt', {
          seq_post_portfolio: Number(this.portfolioSeq),
          seq_post_project: this.includedProject,
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
        })
        http
        .post('portfolio/certification', {
          seq_post_portfolio: this.portfolioSeq,
          certification: this.certifications
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })

        http
        .post('portfolio/info', {
          seq_post_portfolio : this.portfolioSeq ,
          introduction : this.introduction,
          address : this.address,
          objective : this.objective
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })

        http
        .post('portfolio/info/education', {
          seq_post_portfolio : this.portfolioSeq ,
          education : this.educations
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })

        http
        .post('portfolio/language', {
          seq_post_portfolio : this.portfolioSeq ,
          language : this.languages
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })

        http
        .post('portfolio/info/experience', {
          seq_post_portfolio : this.portfolioSeq,
          experience : this.experiences
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })

        http
        .post('portfolio/info/social', {
          seq_post_portfolio : this.portfolioSeq ,
          social : this.socials
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })

        http
        .post('portfolio/skill', {
          seq_post_portfolio : this.portfolioSeq ,
          skill : this.skills
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
        })

        http.delete('./projectstack/'+this.portfolioSeq)
        .then(()=>{
            for(var i=0; i<this.includedStack.length; i++){
            http.post('./projectstack', {
              seq_post_project: Number(this.portfolioSeq),
              stack: this.includedStack[i]
            })
          }
        })
        this.$message({
            type: 'success',
            message: '포트폴리오 수정 완료.'
        });
        this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
      })
      .catch((error) => {
      })
    },
    
    validEmail: function(email) {
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    
    // 회원정보 수정전용
    handleAvatarSuccess(res, file) {
      this.imageUrl = 'http://'.concat(res)
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
    addCertification(){
      this.certifications.push(this.certification)
      this.certification = {}
    },
    deleteCertification(index){
      this.certifications.splice(index,1)
    },
    addLanguage(){
      this.languages.push(this.language)
      this.language = {}
    },
    deleteLanguage(index){
      this.languages.splice(index,1)
    },
    addSkill(){
      this.skills.push(this.skill)
      this.skill = {}
    },
    deleteSkill(index){
      this.skills.splice(index,1)
    },
    addEducation(){
      this.educations.push(this.education)
      this.education = {}
    },
    deleteEducation(index){
      this.educations.splice(index,1)
    },
    addExperience(){
      this.experiences.push(this.experience)
      this.experience = {}
    },
    deleteExperience(index){
      this.experiences.splice(index,1)
    },
    addSocial(){
      if(this.social.link.includes('facebook',0) )
        this.social.icon = 'ti-facebook'
      else if(this.social.link.includes('instagram',0) )
        this.social.icon = 'ti-instagram'
      else if(this.social.link.includes('google',0) )
        this.social.icon = 'ti-google'
      else if(this.social.link.includes('github',0) )
        this.social.icon = 'ti-github'
      else if(this.social.link.includes('twitter',0) )
        this.social.icon = 'ti-twitter-alt'
      else if(this.social.link.includes('tumblr',0) )
        this.social.icon = 'ti-tumblr-alt'
      this.socials.push(this.social)
      this.social = {}
    },
    deleteSocial(index){
      this.socials.splice(index,1)
    },
  }
}
</script>
<style>
  .tocenter{
    vertical-align: middle;    
  }
  .avatar-uploader .el-upload {
    top: 120px;
    left: 75%;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: absolute;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon2 {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 150px;
    line-height: 200px;
    text-align: center;
  }
  .avatar2 {
    width: 150px;
    height: 200px;
  }
  .avatar-uploader-icon {
    transform: translate(0%, 40%);
  }
  .el-transfer{
    width: auto;
    float: left;
  } 
  .el-transfer__buttons{  
    padding: 0px 5px !important;
    /* background-color: green; */
  }
  botton.el-button.el-button--primary.is-disabled.el-transfer__button.is-with-texts{
    padding: 5px;
  }
  .showProject{
    /* display:block; */
    margin-left:20px;
    float: left;
    background-color: #d5cbe42a;
    width: 275px;
    min-height: 300px;
    border-radius: 6px;
  }
  .showStacks{
    /* display:block; */
    margin-left:20px;
    float: left;
    background-color: #d5cbe42a;
    width: 275px;
    min-height: 300px;
    border-radius: 6px;
  }
  .stacks{
    display:inline-block;
    margin-top:10px;
    margin-bottom:10px;
    min-width: 93%;
    min-height: 300px;
    /* background-color: #d5cbe42a; */
    border-radius: 6px;
  }
</style>