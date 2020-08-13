<template>

<!--  지원하는 직무에 적합한 3개의
핵심 프로젝트를 어필하는데 적합한 템플릿 -->

<section id="timelineTemplate">

  <!-- about me 시작 -->
  <div class="tl-item">
    <div class="tl-bg" style="background-image: url('../../static/img/template01_01.png')"></div>
    <div class="tl-year">
      <p class="f2 heading--sanSerif">about me</p>
    </div>
    <div class="tl-content">
      <h1>{{ portfolioInfo.name }}</h1>
      <p>자기 소개가 들어가야 하는데 포트폴리오 입력 페이지에서 입력 항목에 없음</p>
    </div>
    <el-dialog style="z-index:100" class="template01detail" title="About Me" :visible.sync="aboutmedetail">
      <img src="../../static/img/template01_09.jpg" style="position:absolute; top:0; left:0; height:100%; width:100%;">
    </el-dialog>
  </div>
  <!-- about me 끝 -->
  
  <!-- project 시작 -->
  <div class="tl-item" v-for="(project, index) in projectInfo" :key="index">
    <div class="tl-bg" :style="'background-image: url('+project.img_url+')'"  ></div>
    <div class="tl-year">
      <p class="f2 heading--sanSerif">{{ project.title }}</p>
    </div>
    <div class="tl-content">
      <h1 class="f3 text--accent ttu">한줄 설명</h1>
      <p>{{ project.summary }}</p>
      <p></p>
      <p></p>
      <p></p>
      <p></p>
      <p></p>

    </div>
  </div>
  <!-- project 끝 -->
</section>

</template>

<script>
import http from '../util/http-common'
export default {
  data(){
    return{
      isFullScreen: false,
      seq_portfolio: '',
      portfolioInfo: [],
      projectInfo: [],
    }
  },
  created() {
    this.seq_portfolio = this.$route.params.seq
  },
  mounted() {
    $(".main-header").css('display','none')
    $(".main-footer"). css('display','none')
    $(document).ready(function(){
      document.body.requestFullscreen()
      this.isFullScreen = true
      if(this.FullScreen){
        $(window).keydown(function(event) {
          if (event.keyCode == 27) {
            document.exitFullscreen();
          }
        })
      }
    })
    this.getPortFolioInfo()    
  },
  methods:{
    getPortFolioInfo() {
      http
      .get('portfolio/'+this.seq_portfolio)
      .then(({data}) => {
        console.log(data)
        this.portfolioInfo = data
        http
        .get('portfoliopjt/'+this.seq_portfolio)
        .then(({data}) => {
          console.log(data)
          this.projectInfo = data.reverse()
        })
      })
    }
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
</style>>
<style>
.template01detail .el-dialog{
  width: 90% !important;
  height: 80% !important;
  margin-top: 50px !important;
}
</style>