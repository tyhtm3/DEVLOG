<template>

<section id="ysTemplate">

<div id="container">
  <div id ="main">
    <br><br><br><br>
    <ul>
      <li>
    <div id="profile_img">
    <img class="pull-left" alt="profile_img" src='http://i3a402.p.ssafy.io/images/20200803062316_profile.jpg'>
    </div>
      <li>
    <li>&emsp;이름       : 차윤석( 24세 )</li>
    <li>&emsp;관심분야   : Backend, Server</li>
    <li>&emsp;Github    : https://github.com/C9Boom7</li>
    </ul>
    <br>
      <!-- Main content -->
    <section class="content">
      <div class="row">
          <div class="col-md-12">
              <!-- Default box -->
              <div class="box">
                  <div class="box-body" id="box-pie">
                      <ve-pie :data="chartData" :settings="chartSettings"></ve-pie>
                  </div>
                  <!-- /.box-body -->
              </div>
          </div>
      </div>
      <div class="box-body no-pad padding-fifthin" style="display: block;">
        <div class="events-nest ">
          <div class="wrap-no-pad padding-fifthin">
            <div class="row">
              <div class="col-sm-6 bor-right bor-bottom">
                <div class="devin-table-dash centered pad-box"> <i class="palette-Pink-500 bg ti-shopping-cart-full"></i>
                  <div class="palette-Pink-500 text value">648</div>
                  <div class="label">Items Sold</div>
                </div>
              </div>
              <div class="col-sm-6 bor-bottom">
                <div class="devin-table-dash centered pad-box"> <i class="palette-Purple-500 bg ti-link"></i>
                  <div class="palette-Purple-500 text value">47.5<strong>K</strong></div>
                  <div class="label">Followers</div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-6 bor-right">
                <div class="devin-table-dash centered pad-box"> <i class="palette-Deep-Purple-500 bg ti-wallet"></i>
                  <div class="value palette-Deep-Purple-500 text"><strong>$</strong>764</div>
                  <div class="label">Daily Earnings</div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="devin-table-dash centered pad-box"> <i class="ti-bag palette-Light-Blue-500 bg"></i>
                  <div class="value palette-Light-Blue-500 text">256</div>
                  <div class="label">Products</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  
  <div class="box-body" style="min-height:400px;">
    <div class="container-timeline">
      <div class="header-timeline">
        <img src="http://i3a402.p.ssafy.io/images/project.png" height="64" width="64">
        <h1>프로젝트</h1>
        <h2>A timeline that shows my project history</h2> </div>
        <div class="item-timeline">
          <div id="timeline">
            <div>
              <section class="year" v-for="(project,index) in projects" :key="index">
                <h3>{{project.start_date.substr(0,7)}}</h3>
                <section>
                  <ul>
                  <li><img :src="project.img_url" height="250" width="400"></li>
                  </ul>
                </section>
                <section>
                  <h4>프로젝트명</h4>
                  <ul>
                    <li>{{project.title}}</li>
                  </ul>
                </section>
                <section>
                  <h4>개발기간</h4>
                  <ul>
                    <li>{{project.start_date}}~{{project.finish_date}}</li>
                  </ul>
                </section>
                <section>
                  <h4>프로젝트요약</h4>
                  <ul>
                    <li>{{project.summary}}</li>
                  </ul>
                </section>
                <section>
                  <h4>역할</h4>
                  <ul>
                    <li>to-do 페이지 제작</li>
                    <li>데이터베이스 설계</li>
                    <li>FireBase 연동</li>
                  </ul>
                </section>
                <section>
                  <h4>기술스택</h4>
                  <ul>
                    <li>
                      <img v-for="(stack,index) in stacks" :key="index" class="media-object img-circle pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="width: 64px; height: 64px;margin-right:20px;">
                    </li>
                  </ul>
                </section>
                <br>
                <section>
                  <h4>관련링크</h4>
                  <ul>
                    <li><a :href="project.github_url">{{project.github_url}}</a> </li>
                  </ul>
                </section>
                <section>
                  <h4>프로젝트 더보기</h4>
                  <ul>
                    <li><a :href="project.etc_url">{{project.etc_url}}</a> </li>  
                  </ul>
                </section>
              </section>
            </div>
          </div>
        </div>
      </div>  
    </div>
  </div>
  </div>
</section>
 
</template>

<script>

import http from '../util/http-common'
export default {
  data: function () {
    return {
      seq_portfolio: 395,
      chartData : {},
      chartSettings : {},
      projects : [],
      stacks : [],
    }
  },
   mounted(){
    $(".main-header").css('display','none')
    $(".main-footer"). css('display','none')
  },
  created() {
      this.chartData = {
        rows: [
          { 'stack': 'Spring', 'rate': 5 },
          { 'stack': 'Java', 'rate': 4 },
          { 'stack': 'MariaDB', 'rate': 3 },
          { 'stack': 'AWS', 'rate': 3 },
          { 'stack': 'Vue.js', 'rate': 1 }
        ]
      }
      this.chartSettings = {
        dimension: 'stack',
        metrics: 'rate'
      }
      this.stacks = [
      ]
      this.getProjectInfo()
    },
    methods: {
      getProjectInfo(){
        http.get('portfoliopjt/'+this.seq_portfolio)
        .then(({data}) =>{
          this.projects = data
          for(var i=0;i<data.length;i++){
            http.get('projectstack/'+this.projects[i].seq)
            .then(({data})=>{
              for(var j=0;j<data.length;j++){
                this.stacks.push(data[j])
              }
            })
          //   http.get('projectrole/'+this.projects[i].seq)
          //   .then(({data})=>{
          //     console.log(data[0])
          //     console.log(data[0].role)
          //     this.projects[i].role = data[0].role
          //  })
          //   console.log(this.projects[i])
          }
          
        })
      }
    }
}
</script>

<style lang="scss" scoped>

#container{
  background-color: #E6E6E6;
}
#main{
  position: relative;
  margin-left : 20%;
  margin-right : 20%;
  background-color :white;
}
ul{
  list-style: none;
  list-style-type: none;
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
    list-style: none;
    list-style-type: none;
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
.padding-fifthin{
  padding: 15px!important;
}

#box-pie{
  margin-left: 15%;
  margin-right: 15%;
}
</style>