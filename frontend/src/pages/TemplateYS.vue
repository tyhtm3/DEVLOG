<template>

<section id="ysTemplate">

<div id="container">
  <div id ="main">
    <br><br><br><br>
    <hr>
    <br>
    <br>
    <ul style="margin-left : 20%">
      <li>
    <div id="profile_img">
    <img class="pull-left" alt="profile_img" :src='blogOwnerInfo.profile_img_url' width="160px" height="160px">
    </div>  
      </li>
    <br><br>
    <li>&emsp;이름 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: {{blogOwnerInfo.name}}( {{blogOwnerInfo.birthday.substr(0,11)}} )</li>
    <li>&emsp;연락처 &nbsp;&nbsp;: {{blogOwnerInfo.tel}}</li>
    <li>&emsp;Github    : {{blogOwnerInfo.github_url}}</li>
    </ul>
    <br>
    
      <!-- Main content -->
    <section class="content">
      <hr>
      <div class="box-body no-pad padding-fifthin" style="display: block;">
        <div class="events-nest ">
          <div class="wrap-no-pad padding-fifthin">
            <div class="row">
              <div class="col-sm-6 bor-right bor-bottom">
                <div class="devin-table-dash centered pad-box">
                  <div class="palette-Pink-500 text value">{{projects.length}}<strong>개</strong></div>
                  <div class="label">프로젝트</div>
                </div>
              </div>
              <div class="col-sm-6 bor-bottom">
                <div class="devin-table-dash centered pad-box"> 
                  <div class="palette-Purple-500 text value">{{totaldays}}<strong>일</strong></div>
                  <div class="label">개발 기간</div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-6 bor-right">
                <div class="devin-table-dash centered pad-box"> 
                  <div class="value palette-Deep-Purple-500 text">{{chartData.rows.length}}<strong>개</strong></div>
                  <div class="label">기술 스택</div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="devin-table-dash centered pad-box"> 
                  <div class="value palette-Light-Blue-500 text">{{likeCount}}<strong>개</strong></div>
                  <div class="label">좋아요</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <hr>
      <div class="row">
          <div class="col-md-12">
              <!-- Default box -->
              <div class="box">
                  <div class="box-body" id="box-pie">
                      <ve-pie :data="chartData" :settings="chartSettings" :id="box-pie"></ve-pie>
                      <h4 style="text-align : center">Project TechStack</h4>
                  </div>
                  <!-- /.box-body -->
              </div>
          </div>
      </div>
    </section>
  
  <div class="box-body" style="min-height:400px;">
    <div class="container-timeline">
      <div class="header-timeline">
        <img src="http://i3a402.p.ssafy.io/images/project.png" height="64" width="64">
        <h1>프로젝트</h1>
        <h2>A timeline that shows my project history</h2>
        <br>
        <br>
        <hr>
      </div>
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
                    <li v-for="(role,index) in project.roles" :key="index">
                      {{role.role}}
                    </li>
                  </ul>
                </section>
                <section>
                  <h4>기술스택</h4>
                  <ul>
                    <li>
                      <img v-for="(stack,index) in project.stacks" :key="index" class="media-object img-circle pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="width: 64px; height: 64px;margin-right:20px;">
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
      blogOwnerInfo : {},
      likeCount : 0,
      totaldays : 0,
    }
  },
   mounted(){
    $(".main-header").css('display','none')
    $(".main-footer"). css('display','none')
  },
  created() {
      this.chartData = {
        rows: [
          // { 'stack': 'Spring', 'rate': 5 },
          // { 'stack': 'Java', 'rate': 4 },
          // { 'stack': 'MariaDB', 'rate': 3 },
          // { 'stack': 'AWS', 'rate': 3 },
          // { 'stack': 'Vue.js', 'rate': 1 }
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
      //포폴 정보 가져오기
      getProjectInfo(){
        http.get('portfoliopjt/'+this.seq_portfolio)
        .then(({data}) =>{
          this.projects = data
          // 프로젝트 개수 반복
          for(var i=0;i<data.length;i++){
            //좋아요 개수 추가
            this.likeCount += data[i].like_count
            console.log(this.likeCount)
            //개발기간 추가
            this.totaldays += (dateDiff(data[i].finish_date, data[i].start_date))
            //스택 개수 반복
            for(var j=0;j<data[i].stacks.length;j++){
              var rows = new Object()
              rows.stack = data[i].stacks[j].stack
              rows.rate = 1
              var found = false;
              //이미 들어온 데이터 확인하면서 중복이면 숫자 증가
              for(var k = 0; k < this.chartData.rows.length; k++) {
                if (this.chartData.rows[k].stack == rows.stack) {
                  found = true;
                 this.chartData.rows[k].rate += 1
                break;
                }
              }
              //새로 들어온거면 1로 추가
              if(!found)
              this.chartData.rows.push(rows)
            } 
          }
          http.get('/user/'+this.projects[0].seq_blog)
          .then(({data})=>{
            this.blogOwnerInfo = data;
          })
          this.projects.sort(this.customSort)
        })
      },
      customSort(a, b) {
        if(a.start_date == b.start_date){
          return 0
          }
          return a.start_date > b.start_date ? 1 : -1; }             
      },
  }
  function dateDiff(_date1, _date2) {
    var diffDate_1 = _date1 instanceof Date ? _date1 :new Date(_date1);
    var diffDate_2 = _date2 instanceof Date ? _date2 :new Date(_date2);
    diffDate_1 =new Date(diffDate_1.getFullYear(), diffDate_1.getMonth()+1, diffDate_1.getDate());
    diffDate_2 =new Date(diffDate_2.getFullYear(), diffDate_2.getMonth()+1, diffDate_2.getDate());
    var diff = Math.abs(diffDate_2.getTime() - diffDate_1.getTime());
    diff = Math.ceil(diff / (1000 * 3600 * 24));
    return diff;
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

// #box-pie{
//   margin-left: 15%;
//   margin-right: 15%;
//   width: 500px;
//   height: 500px;
// }
</style>