<template>

<!--  지원하는 직무에 적합한 3개의
핵심 프로젝트를 어필하는데 적합한 템플릿 -->

<section id="timelineTemplate">

  <!-- about me 시작 -->
  <div class="tl-item" @click="aboutmeVisible=true">
    <div class="tl-bg" style="background-image: url('../../static/img/template01_01.png')"></div>
    <div class="tl-year">
      <p class="f2 heading--sanSerif">about me</p>
    </div>
    <div class="tl-content">
      <h1>김유창 / Kim-Yuchang</h1>
      <p>센스있는 개발자 김유창입니다.</p>
      <p>가장 자신있는 언어는 Java 입니다.</p>
      <p>기획부터 디자인, 프론트엔드, 백엔드까지 관심있습니다.</p>
      <p>항상 많이 배우겠습니다.</p>
    </div>
  </div>

  <el-dialog class="template01detail" title="About Me" :visible.sync="aboutmeVisible">
    <img src="../../static/img/template01_09.jpg" style="position:absolute; top:0; left:0; height:100%; width:100%;">
  </el-dialog>
  <!-- about me 끝 -->

  <!-- project1 시작 -->
  <div class="tl-item" @click="project1Visible=true">
    <div class="tl-bg" style="background-image: url('../../static/img/template01_02.jpg')"></div>
    <div class="tl-year">
      <p class="f2 heading--sanSerif">졸업가능?</p>
    </div>
    <div class="tl-content">
      <h1 class="f3 text--accent ttu">졸업 가능 여부를 판단해주는 졸업자가진단시스템</h1>
      <p>4학년 재학생을 대상으로 자신의 학사 정보를 입력하면 졸업 가능 여부와
        부족한 이수 학점을 알려주고 과목추천 등 추가기능을 제공하는 졸업자가진단 시스템입니다.</p>
      <p>개발 기간 : 2014.05 ~ 2014.06</p>
      <p>개발 환경 : C#, MS Access</p>
      <p>역할 : 인터페이스 및 졸업 여부를 계산하는 알고리즘 구현</p>
    </div>
  </div>
  <el-dialog class="template01detail" title="졸업 가능?" :visible.sync="project1Visible">
  </el-dialog>
  <!-- project1 끝 -->

  <!-- project2 시작 -->
  <div class="tl-item" @click="project2Visible=true">
    <div class="tl-bg" style="background-image: url(../../static/img/template01_07.png)"></div>
    <div class="tl-year">
      <p class="f2 heading--sanSerif">HAPPY HOUSE</p>
    </div>
    <div class="tl-content">
      <h1 class="f3 text--accent ttu">주택 실거래가 내역 제공 서비스</h1>
      <p>공공데이터를 기반으로 주택 거래 내역을 제공하는 서비스입니다.
        웹크롤링을 이용한 부동산 관련 뉴스와 관심지역 설정 등 부가서비스를 제공합니다.</p>
      <p>개발 기간 : 2020.05 ~ 2020.07</p>
      <p>개발 환경 : Vue.js, SpringBoot, MySQL</p>
      <p>역할 : 웹 디자인, 주요 서비스 프론트엔드 백엔드 구현</p>
    </div>
  </div>
  <el-dialog class="template01detail" title="HAPPY HOUSE" :visible.sync="project2Visible">
    <div class="row-fluid">
      <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
        <div class="short-div" style="height:350px">
          <img :src="project2MainImage" style="height:98%; width:97%; padding:1%">
        </div>
        <div class="short-div" style="height:140px; text-align:center">
          <img :src="project2subImage1" style="height:80%;cursor:pointer" @click="changeImage(project2subImage1)">
          <img :src="project2subImage2" style="height:80%;cursor:pointer" @click="changeImage(project2subImage2)">
          <img :src="project2subImage3" style="height:80%;cursor:pointer" @click="changeImage(project2subImage3)">
          <img :src="project2subImage4" style="height:80%;cursor:pointer" @click="changeImage(project2subImage4)">
        </div>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" style="height:490px">
        <p>프로젝트명 : HAPPY HOUSE</p>
        <p>개발 인원 : 2</p>
        <p>개발 기간 : 2020.06 ~ 2020.07</p>
        <p>개발 환경 : Vue.js SpringBoot, MySQL</p>
        <p>역할 : 프론트 엔드 전체적인 디자인 및 주요기능 구현</p>
        <p>git : http://www.github.com/cl08</p>
        <p>개요 : 공공데이터를 기반으로 주택 거래 내역을 제공하는 서비스입니다.
        웹크롤링을 이용한 부동산 관련 뉴스와 관심지역 설정 등 부가서비스를 제공합니다.</p>
        <p>기술 스택 : Vue-CLI, Node.js, Java, SpringBoot, RESTful API, MyBtis, Git, MySQL</p>
      </div>
    </div>
  </el-dialog>
  <!-- project2 끝 -->

  <!-- project3 시작 -->
  <div class="tl-item" @click="project3Visible=true">
    <div class="tl-bg" style="background-image: url(../../static/img/template01_04.png)"></div>  
    <div class="tl-year">
      <p class="f2 heading--sanSerif">DEVLOG</p>
    </div>
    <div class="tl-content">
      <h1 class="f3 text--accent ttu">개발자를 위한 포트폴리오용 블로그</h1>
      <p>사용자가 작성한 포스트를 데이터화하여 다양한 템플릿의 포트폴리오로 제공하는 블로그 서비스입니다.</p>
      <p>개발 기간 : 2020.07 ~ 진행중</p>
      <p>개발 환경 : Vue.js SpringBoot MariaDB</p>
      <p>역할 : 프론트엔드</p>
    </div>
  </div>
  <el-dialog class="template01detail" title="DEVLOG" :visible.sync="project3Visible">

  </el-dialog>
  <!-- project3 끝 -->
</section>

</template>

<script>
export default {
  data(){
    return{
      aboutmeVisible: false,
      project1Visible: false,
      project2Visible: false,
      project3Visible: false,
      project2MainImage: "../../static/img/template01_05.png",
      project2subImage1: "../../static/img/template01_05.png",
      project2subImage2: "../../static/img/template01_06.png",
      project2subImage3: "../../static/img/template01_07.png",
      project2subImage4: "../../static/img/template01_08.png",
    }
  },
  mounted(){
    $(".main-header").css('display','none')
    $(".main-footer"). css('display','none')
  },
  methods:{
    changeImage(src){
      console.log(src)
      this.project2MainImage = src;
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