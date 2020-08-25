<template>
  <transition name="el-zoom-in-top">
    <div class="box">
      <div class="box-body" style="align:center; min-height:400px; max-width:800px; margin:auto; padding : 0px;">
        <div class="col-sm-12" style="padding:0px">
          <div class="col-xs-12 col-sm-12" style="padding:0px">
            <hr>
              <div style="margin-bottom:15px; font-size:20px;"><b>개인정보 입력</b>
                <el-tooltip class="item" effect="dark" content="포트폴리오에 담길 내 정보를 작성해보세요. 인사 담당자에게 보여질 항목이므로 정확한 정보를 입력해주세요." placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
              <hr>
            <dl class="dl-horizontal-profile">
              <dt>이름 
                <el-tooltip class="item" effect="dark" content="포트폴리오에 보여줄 이름을 입력해주세요. 인사담당자에게 보여줄 포트폴리오라면 실명을 입력해주세요." placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
              </dt>
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
              <dt>연락처
                <el-tooltip class="item" effect="dark" content="잘못된 연락처를 기입해 취업시 불이익을 받는 사례가 많습니다. 연락처를 다시한번 확인해주세요." placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
              </dt>
              <dd><el-input v-model="tel" style="width: 50%;"></el-input></dd>
              <dt>이메일
                <el-tooltip class="item" effect="dark" content="자주 사용하는 이메일을 등록해주세요. 해외 기업 지원자라면 gmail로 작성하시는 것을 추천드립니다." placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
              </dt>
              <dd><el-input v-model="email" style="width: 50%;"></el-input></dd>
              <dt>GIT 주소
                <el-tooltip class="item" effect="dark" content="깃 주소를 입력해 인사담당자의 흥미를 끌어보세요! Git은 프로젝트 경험과 더불어 성실함까지 어필할 수 있는 수단입니다." placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
              </dt>
              <dd>
                <el-input v-model="giturl" style="width: 50%;">
                  <template slot="prepend">https://</template>
                </el-input>
              </dd>
            </dl>
            </div>
              <div class="col-xs-12 col-sm-12" style="padding:0px">
              <hr>
              <div style="margin-bottom:15px; font-size:20px"><b>프로젝트 정보 입력</b>
                <el-tooltip class="item" effect="dark" content="지금까지 작성했던 프로젝트 중 포트폴리오에 첨부하고 싶은 프로젝트를 선택해보세요!" placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
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
                <div style="margin-bottom:15px; text-align:center"><img :src=hoveredProjectData.img_url width="200px"></div>
                <div class="row pjt-margin">
                  <div class="col-sm-3">
                  <p>제목</p>
                  </div>
                  <div class="col-sm-9">
                  <p class="pjt-content">{{hoveredProjectData.title}}</p>
                  </div>
                </div>
                <div class="row pjt-margin">
                  <div class="col-sm-3">
                  <p>개요</p>
                  </div>
                  <div class="col-sm-9">
                  <p class="pjt-content">{{hoveredProjectData.summary}}</p>
                  </div>
                </div>
                <div class="row pjt-margin">
                  <div class="col-sm-3">
                  <p>기간</p>
                  </div>
                  <div class="col-sm-9">
                  <p class="pjt-content">{{hoveredProjectData.start_date}} ~ {{hoveredProjectData.finish_date}}</p>
                  </div>
                </div>
                <div class="row pjt-margin">
                  <div class="col-sm-3">
                  <p>스택</p>
                  </div>
                  <div class="col-sm-9">
                  <div v-for="(stack,index) in projectStackList" :key="index">
                    <img class="media-object img-circle pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="height: 32px;margin-right:10px;">
                  </div>
                  </div>
                </div>
                <div class="row pjt-margin">
                  <div class="col-sm-3">
                  <p>역할</p>
                  </div>
                  <div class="col-sm-9">
                  <p class="pjt-content" v-for="(role,index) in hoveredProjectData.roles" :key="index" >{{role.role}}</p>
                  </div>
                </div>
                <div class="row" v-if="hoveredProjectData.github_url">
                  <div class="col-sm-3">
                  <p>Git</p>
                  </div>
                  <div class="col-sm-9">
                  <a href="#" @click="goUrl(hoveredProjectData.github_url)"><p class="pjt-content">{{hoveredProjectData.github_url}}</p></a>
                  </div>
                </div>
                <div class="row" v-if="hoveredProjectData.etc_url">
                  <div class="col-sm-3">
                  <p>기타 </p>
                  </div>
                  <div class="col-sm-9">
                  <p class="pjt-content">{{hoveredProjectData.etc_url}}</p>
                  </div>
                </div>
                <div class="row" v-if="hoveredProjectData.rep_url">
                  <div class="col-sm-3">
                  <p>참고 Url</p>
                  </div>
                  <div class="col-sm-9">
                  <p class="pjt-content">{{hoveredProjectData.rep_url}}</p>
                  </div>
                </div>
              </div>
            </section>

            <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 끝-->
            </div>
              <div class="col-xs-12 col-sm-12" style="padding:0px">
              <hr>
              <div style="margin-bottom:15px; font-size:20px"><b>기술스택 정보 입력</b>
                <el-tooltip class="item" effect="dark" content="포트폴리오 생성시, 이곳에서 선택한 기술 스택을 통해 포트폴리오를 시각화해드립니다. 자신있는 기술스택을 골라보세요! " placement="right"><span class="ti-help-alt" style="position:relative;top:2px;"/></el-tooltip>
              </div>
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
                <div style="margin-bottom:15px; text-align:center"><img :src=hoveredStackData.stack_img_url width="200px"/></div>
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
            <!-- 오른쪽 hover 하면 데이터 띄워주는 부분 끝-->
          </div>
          <hr><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
          

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



          <!-- 포트폴리오 정보 입력 -->
          <br>
          <div class="col-xs-12 col-sm-12" style="padding:0px">
            <hr>
            <div style="margin-bottom:15px; font-size:20px"><b>포트폴리오 정보 입력</b>
              <el-tooltip class="item" effect="dark" content="지금부터 입력받는 부분은 회원님에게만 보이는 정보입니다. 다른 데블로거는 회원님이 선택한 대표 포트폴리오만 열람이 가능합니다." placement="right"><span class="ti-help-alt" style="position:relative;top:1px;"/></el-tooltip>
            </div>
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
              <el-switch v-model="portfolioRepresentation" on-color="#13ce66" off-color="#ff4949" style="margin-top:10px;" :on-value="1" :off-value="0"> </el-switch>
              </dd>

            </dl>
            <hr>
           
          </div>
          <el-button @click="makePortfolio" style="float:right; margin-bottom: 20px">포트폴리오 생성</el-button>
        </div> 
      </div>
    </div>
	</transition>
</template>
<script>
import http from '../util/http-common'
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState(['userInfo']),
  },
  data: () => {
    return {
      // 포트폴리오 기본정보
      portfolioContent:'',
      portfolioTitle:'',
      portfolioRepresentation : 0,
      portfolioSeq:'',
      // 개인정보
      seq: '',
      name: '',
      email: '',
      tel: '',
      birth: '',
      giturl: '',
      imageUrl: '',
      temp:[],
      // 플젝정보
      projectList: [],
      includedProject: [],
      projectInfoList:[],
      projectStackList:[],
      // 스택정보
      stackList: [],
      includedStack: [],
      stackInfoList:[],

      // 자격증정보
      certification: {
        date: '',
        certification: ''
      },
      certifications : [], // 저장할 자격증

      test:[],

      hoveredProject:'',
      hoveredProjectData:'',
      hoveredStack:'',
      hoveredStackData:'',

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
    this.$store.state.loginFormVisible = false;
    this.getProjectList(this.userInfo.seq);
    this.getStackList(this.userInfo.seq);

  },
  mounted() {
    this.seq = this.userInfo.seq,
    this.name = this.userInfo.name,
    this.email = this.userInfo.email,
    this.tel = this.userInfo.tel,
    this.birth = this.userInfo.birthday,
    this.giturl = this.userInfo.github_url,
    this.imageUrl = this.userInfo.profile_img_url
    var vm = this;
    $(".el-transfer-panel__body").mouseover(function(event) {
      event.preventDefault();
      if(event.target.className==''){
        if($(this).parent().parent().parent()[0].className=='selectProject'){
          vm.putHoveredProject(event.target.innerText);
        }else if($(this).parent().parent().parent()[0].className=='selectStacks'){
          vm.putHoveredStack(event.target.innerText);
        }
      }
    });
  },
  methods: {
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
    putHoveredProject(input){
      this.hoveredProject=input;
      // project list for문 돌리기
      for(var i=0; i<this.projectInfoList.length; i++){
        if(this.projectInfoList[i].title==this.hoveredProject){
          this.hoveredProjectData=this.projectInfoList[i];
          this.getProjectStack(this.hoveredProjectData.seq);
        }
      }
    },
    getProjectStack(seq){
      http
      .get('/projectstack/'+seq)
      .then(({ data }) => {
        this.projectStackList=data;
      })
    },
    putHoveredStack(input){
      this.hoveredStack=input;
      for(var i=0; i<this.stackInfoList.length; i++){
        if(this.stackInfoList[i].stack==this.hoveredStack){
          this.hoveredStackData=this.stackInfoList[i];
        }
      }
    },
    filterMethod(query, item) {
        return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
    },
    getProjectList(seq){
      http
      .post('project/blog', { seq_user:seq , seq_blog:seq, offset:0, limit:99999} )
      .then(({ data }) => {
        this.projectInfoList=data;
        this.hoveredProjectData=this.projectInfoList[0];
        for(let i=0; i<data.length; i++){
          this.projectList.push({
          label: data[i].title,
          key:data[i].seq,
          initial: data[i].seq
          })
        }
      })
    },
    getStackList(seq){
      http
      .get('/stack')
      .then(({ data }) => {
        this.stackInfoList=data;
        this.hoveredStackData=this.stackInfoList[0];
        for(let i=0; i<data.length; i++){
          this.stackList.push({
          label: data[i].stack,
          key:i,
          initial: data[i].seq
          })
        }
      })
    },
    makePortfolio() {
      if(this.includedProject.length==0){
        this.$message.warning('프로젝트를 한 개 이상 선택해야 합니다.')
      }else if(this.portfolioTitle.length==0){
        this.$message.warning('포트폴리오 제목을 등록해야 합니다.')
      }else{
      http
      .post('portfolio', {
        email: this.email,
        content: this.portfolioContent,
        representation: this.portfolioRepresentation?1:0,
        github_url: this.giturl,
        img_url: this.imageUrl,
        name: this.name,
        profile_img_url: this.imageUrl,
        seq_blog: this.seq,
        title: this.portfolioTitle,
        tel: this.tel
      })
      .then(({ data }) => {
        this.portfolioSeq=data;
        http
        .post('portfoliopjt', {
          seq_post_portfolio: this.portfolioSeq,
          seq_post_project: this.includedProject
        })
        .then(({ data }) => {
        })
        .catch((error)=>{
          console.log(error.response.status)
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

        for(let i=0; i<this.includedStack.length; i++){
          http
          .post('projectstack', {
            seq_post_project: this.portfolioSeq,
            stack: this.stackInfoList[this.includedStack[i]].stack,
            stack_img_url: this.stackInfoList[this.includedStack[i]].stack_img_url,
          })
          .then(({ data }) => {
          })
          .catch((error)=>{
            console.log(error.response.status)
          })
        }
        this.$message({
            type: 'success',
            message: '포트폴리오 생성 완료.'
        });
        this.$router.push('/blog/'+this.$store.getters.getUserInfo.id)
      })
      .catch((error) => {
        console.log(error.response.status)
      })
      }
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

  .el-transfer-panel__item.el-checkbox .el-checkbox__label:hover{
    color:#9EBBCD;
  }
  
  .pjt-content{
    word-break:break-all;
  }

  /* 프로젝트 및 스택 정보 출력 */
  .only-for-show{
    height:100%;
    width:100%; 
    background-color:transparent; 
    border:none;
  } 

  .row .pjt-margin p{
    margin:0px;
    margin-bottom: 10px;
  }

</style>