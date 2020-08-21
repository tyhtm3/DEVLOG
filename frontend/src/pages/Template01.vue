<template>
<section id="timelineTemplate" v-loading="loading">

  <!-- about me 시작 -->
  <div class="tl-item" @click="aboutmedetail=true">
    <div class="tl-bg" style="background-image: url('../../static/img/template01_01.png')"></div>
    <div class="tl-year">
      <p class="f2 heading--sanSerif">about me</p>
    </div>
    <div class="tl-content">
      <h1>{{ portfolioInfo.name }}</h1>
      <p>{{ portfolioInfo.introduction }}</p>
    </div>
  </div>
  <!-- about me 끝 -->
  
  <!-- project 시작 -->
  <div class="tl-item" v-for="(project, index) in projectInfo" :key="index" @click="projectDetailVisible(project)">
    <div class="tl-bg" :style="'background-image: url('+project.img_url+')'"  ></div>
    <div class="tl-year">
      <p class="f2 heading--sanSerif">{{ project.title }}</p>
    </div>
    <div class="tl-content">
      <h1 class="f3 text--accent ttu">{{ project.summary }}</h1>
      <p>개발 기간 : {{ project.start_date }} ~ {{ project.finish_date }}</p>
      <p>역할 : 
        <span v-for="(role, index) in project.roles" :key="index">
          {{ role.role }}
        </span>
      </p>
      <p>기술스택 : 
        <span v-for="(stack, index) in project.stacks" :key="index">
          #{{ stack.stack }}
        </span>
      </p>
    </div>
  </div>
  <!-- project 끝 -->

  <!-- about me modal 시작 -->
  <el-dialog class="template01detail" title="About Me" :visible.sync="aboutmedetail">
    <div class="row">
      <div class="col-lg-1"></div>
      <div class="col-lg-10" style="margin-top:30px">
        <span class="col-lg-3">
          <img :src="this.portfolioInfo.profile_img_url" style="width:150px; height:200px;">
          <h3>{{ portfolioInfo.name }} </h3>
          <p><i class="ti-mobile" style="margin-right:10px;"></i>{{ portfolioInfo.tel }}</p>
          <p><i class="ti-email" style="margin-right:10px;"></i>{{ portfolioInfo.email }}</p>
          <p><i class="ti-link" style="margin-right:10px;"></i>{{ portfolioInfo.github_url }}</p>
        </span>
        <span class="col-lg-4">
          <span>CERTIFICATION</span>
          <span style="float:right">
            <i class="ti-plus" style="cursor:pointer" @click="addCertificationVisible"></i>
          </span>
          <el-card class="box-card" style="width:100%; margin-top:10px">
            <div v-for="(item, index) in certifications" :key="index" class="text item">
              <span style="width:30%; display:inline-block;">{{ item.date }}</span>
              <span>{{ item.certification }}</span>
              <span class="pull-right" @click="deleteCertification(index)"><i class="ti-trash"></i></span>
            </div>
            <div v-show="inputCertification">
              <span><input size="4" placeholder="취득년도" v-model="certification.date"></span>
              <span><input placeholder="자격증" v-on:keyup.enter="addCertification" v-model="certification.certification"></span>
            </div>
          </el-card>
          <br><br>
          <span>DEVELOMENT SKILLS</span>
          <span style="float:right">
            <i class="ti-plus" style="cursor:pointer" @click="addSkillVisible"></i>
          </span>
          <ul class="listProgram" style="padding:0px">
            <li v-for="(item, index) in skills" :key="index">
              <span style="width:100px">{{ item.skill }}</span>
              <div class="bar">
                <div :class="item.level"></div>
              </div>
              <span class="pull-right" @click="deleteSkill(index)"><i class="ti-trash"></i></span>
            </li>
          </ul>
          <div v-show="inputSkill">
              <span><input placeholder="개발 스킬" v-model="skill.skill"></span>
              <span><input size="10" placeholder="숙련도(1~10)" v-on:keyup.enter="addSkill" v-model="skill.level"></span>
            </div>
        </span>
        <span class="col-lg-5">
          <ve-pie :data="chartData" :settings="chartSettings"></ve-pie>
        </span>
      </div>
      <div class="col-lg-1"></div>
    </div>
    <div class="row">
      <div class="col-lg-1"></div>
      <div class="col-lg-10" style="margin-top:1%">
      </div>
      <div class="col-lg-1"></div>
    </div>
  </el-dialog>
  <!-- about me modal 끝 -->

  <!-- project modal 시작 -->
  <el-dialog class="template01detail" :title="title" :visible.sync="projectdetail" style="overflow:auto">
    <div v-html="content" style="text-align:center">
    </div>
  </el-dialog>
  <!-- project modal 끝 -->
</section>

</template>

<script>
import http from '../util/http-common'
export default {
  data(){
    return{
      seq_portfolio: '',
      portfolioInfo: [],
      projectInfo: [],
      aboutmedetail: false,
      projectdetail: false,
      title: '',
      content: '',
      certifications : [],
      skills: [],
      certification: {
        date: '',
        certification: ''
      },
      skill: {
        name: '',
        value: ''
      },
      loading: true,
      inputCertification: false,
      inputSkill: false,
    }
  },
  created() {
    this.seq_portfolio = this.$route.params.seq
    this.chartData = {
        columns: ['Stack'],
        rows: [
        ]
      }
      this.chartSettings = {
        dimension: 'stack',
        metrics: 'share'
      }
  },
  mounted() {
    $(".main-header").css('display','none')
    $(".main-footer"). css('display','none')
    this.getPortFolioInfo()
    setTimeout(this.stopLoading, 2000);
  },
  methods:{
    getPortFolioInfo() {
      http
      .get('portfolio/'+this.seq_portfolio)
      .then(({data}) => {
        this.portfolioInfo = data
        http
        .get('portfolio/certification/'+this.seq_portfolio)
        .then(({data}) => {
          this.certifications = data
        })
        http
        .get('portfolio/skill/'+this.seq_portfolio)
        .then(({data}) => {
          this.skills = data
        })
        http
        .get('portfoliopjt/'+this.seq_portfolio)
        .then(({data}) => {
          this.projectInfo = data.reverse()
          for(let i=0; i<this.projectInfo.length; i++){
            L:for(let j=0; j<this.projectInfo[i].stacks.length; j++){
              for(let k=0; k<this.chartData.rows.length; k++){
                if(this.chartData.rows[k].stack === this.projectInfo[i].stacks[j].stack){
                  this.chartData.rows[k].share++
                  continue L
                }
              }
              this.chartData.rows.push({'stack': this.projectInfo[i].stacks[j].stack, 'share': 1})
            }
          }
        })
      })
    },
    projectDetailVisible(data){
      this.projectdetail = !this.projectdetail
      this.title = data.title
      this.content = data.content
    },
    addCertificationVisible(){
      this.inputCertification = !this.inputCertification
    },
    addCertification(){
      this.certifications.push(this.certification)
      this.certification = {}
      this.addCertificationVisible()
      http
      .post('/portfolio/certification', {
        seq_post_portfolio: this.portfolioInfo.seq,
        certification: this.certifications
      })
    },
    deleteCertification(index){
      this.certifications.splice(index, 1)
      http
      .post('/portfolio/certification', {
        seq_post_portfolio: this.portfolioInfo.seq,
        certification: this.certifications
      })
    },
    addSkillVisible(){
      this.inputSkill = !this.inputSkill
    },
    addSkill(){
      this.skill.level = 'value p'+this.skill.level+'0'
      this.skills.push(this.skill)
      this.skill = {}
      this.addSkillVisible()
      http
      .post('portfolio/skill', {
        seq_post_portfolio: this.portfolioInfo.seq,
        skill: this.skills
      })
    },
    deleteSkill(index){
      this.skills.splice(index, 1)
      http
      .post('portfolio/skill', {
        seq_post_portfolio: this.portfolioInfo.seq,
        skill: this.skills
      })
    },
    stopLoading(){
      this.loading = false;
    },
  }
}
</script>

<style lang="scss" scoped>

#timelineTemplate {
  display: flex;
  background-color: #031625;

  &:hover {
    .tl-item {
      width: 23.3333%;
    }
  }
}

.tl-item {
  transform: translate3d(0, 0, 0);
  position: relative;
  width: 25%;
  height: 100vh;
  min-height: 600px;
  color: #fff;
  overflow: hidden;
  transition: width 0.5s ease;
  
  &:before, &:after {
    transform: translate3d(0, 0, 0);
    content: '';
    position: absolute;
    left: 0; top: 0;
    width: 100%; height: 100%;
  }

  &:after {
    background: transparentize(#031625, 0.15);
    opacity: 1;
    transition: opacity 0.5s ease;
  }

  &:before {
    background: linear-gradient(to bottom, rgba(0,0,0,0) 0%,rgba(0,0,0,1) 75%);
    z-index: 1;
    opacity: 0;
    transform: translate3d(0, 0, 0) translateY(50%);
    transition: opacity 0.5s ease, transform 0.5s ease;
  }

  &:hover {
    width: 30% !important;
    
    &:after {
      opacity: 0;
    }

    &:before {
      opacity: 1;
      transform: translate3d(0, 0, 0) translateY(0);
      transition: opacity 1s ease, transform 1s ease 0.25s;
    }
    
    .tl-content {
      opacity: 1;
      transform: translateY(0);
      transition: all 0.75s ease 0.5s;
    }

    .tl-bg {
      filter: grayscale(0);
    }
  }
}

.tl-content {
  transform: translate3d(0, 0, 0) translateY(25px);
  position: relative;
  z-index: 1;
  text-align: center;
  margin: 0 1.618em;
  top: 55%;
  opacity: 0;
  
  h1 {
    font-family: 'Pathway Gothic One',Helvetica Neue,Helvetica,Arial,sans-serif;
    text-transform: uppercase;
    color: #1779cf;
    font-size: 1.44rem;
    font-weight: normal;
  }
}

.tl-year {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
  z-index: 1;
  border-top: 1px solid #fff;
  border-bottom: 1px solid #fff;
  
  p {
    font-family: 'Pathway Gothic One',Helvetica Neue,Helvetica,Arial,sans-serif;
    font-size: 1.728rem;
    line-height: 1.4;
    margin: 0px;
  }
}

.tl-bg {
  transform: translate3d(0, 0, 0);
  position: absolute;
  width: 100%; height: 100%;
  top: 0; left: 0;
  background-size: cover;
  background-position: center center;
  transition: filter 0.5s ease;
  filter: grayscale(100%);
}
</style>
<style scoped>
.listProgram .bar {
  width: 150px;
  height: 10px;
  border: 2px solid #FF7168;
  border-radius: 5px;
}

.listProgram li {
  display: -webkit-box;
  display: flex;
  -webkit-box-pack: justify;
          justify-content: space-between;
  -webkit-box-align: center;
          align-items: center;
  padding-top: 10px;
}
.value {
  width: 0%;
  height: 100%;
  background-color: #FF7168;
}
.value.p10 {
  width: 10%;
}
.value.p20 {
  width: 20%;
}
.value.p30 {
  width: 30%;
}
.value.p40 {
  width: 40%;
}
.value.p50 {
  width: 50%;
}
.value.p60 {
  width: 60%;
}
.value.p70 {
  width: 70%;
}
.value.p80 {
  width: 80%;
}
.value.p90 {
  width: 90%;
}
.value.p100 {
  width: 100%;
}
</style>
<style>
.template01detail .el-dialog{
  width: 1200px !important;
  height: 600px !important;
  margin-top: 5% !important;
  overflow: auto;
}
</style>