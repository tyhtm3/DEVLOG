<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <br><br><br>
       <!-- start banner carousel -->
      <div class="header-block">

          <ul class="list-inline blog-devin-tag" style="padding-left:300px;padding-right:300px;font-size:13px;">
              <li><a></a></li>
              <li class="pull-right" v-if="basicinfo.seq_blog==seq_user"><span class="ti-pencil"></span>&nbsp;{{basicinfo.regtime}} &nbsp; | <a href="#"> &nbsp;수정</a><a > &nbsp; | </a><a href="#" @click="deleteProject(project.seq)"> &nbsp;삭제</a></li>
          </ul>
          <div v-if="basicinfo.profile_img_url" class="header-image">
          <img style="block:inline;" :src='basicinfo.profile_img_url' class="avatar">
          </div>
          <div class="nextToAvater" >
            <div class="header-text" :class="{ 'header-text-noImage' : !basicinfo.profile_img_url }" style="padding-left:30px!important">
            <p style="font-size:50px;"><b>{{basicinfo.name}}</b></p>
            <i class="ti-mobile" style="font-size:30px;"/> {{basicinfo.tel}}<br>
            <i class="ti-email" style="font-size:30px;"/> {{basicinfo.email}}<br>
            <i class="ti-link" style="font-size:30px;"/> {{basicinfo.github_url}}<br>
            </div>
          </div>
      </div>
      <div class="main-block">
        <hr><div style="margin-bottom:15px; font-size:20px; text-align:center">SUMMARY</div><hr>
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
          <hr><div style="font-size:20px; text-align:center">프로젝트 기술 스택</div><hr>
          <div class="row">
              <div class="col-md-12">
                      <div class="box-body" id="box-pie">
                          <ve-pie :data="chartData" :settings="chartSettings" :id="box-pie"></ve-pie>
                      </div>
              </div>
          </div>
          <hr><div style="margin-bottom:15px; font-size:20px; text-align:center">프로젝트</div><hr>
            <div class="container-timeline" style="margin: auto; width:900px">
                  <div id="timeline">
                    <div>
                      <section class="year" v-for="(project,index) in projects" :key="index">
                        <h3>{{project.start_date.substr(0,7)}}</h3>
                        <section >
                          <ul>
                            <li><img :src="project.img_url" height="200"></li>
                            <li><span style="margin-right:15px;">프로젝트명&nbsp;&nbsp;&nbsp;&nbsp;</span><span>{{project.title}}</span></li>
                            <li><span style="margin-right:15px">개발기간&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span>{{project.start_date}}~{{project.finish_date}}</span></li>
                            <li><span style="margin-right:15px">프로젝트요약</span><span>{{project.summary}}</span></li>
                            <li v-if="project.roles.length>0"><span style="margin-right:15px">역할
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                              <span v-for="(role,index) in project.roles" :key="index"> {{role.role}}</span></li>
                            <li v-if="project.stacks.length>0"><span style="margin-right:15px">기술스택&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                            <li v-if="project.stacks.length>0" style="height: 64px">
                            <span v-for="(stack,index) in project.stacks" :key="index" >
                              <img class="media-object img-circle pull-left" :alt="stack.stack" :src="stack.stack_img_url" style="width: 64px; height: 64px;margin-right:20px;">
                            </span>
                            </li>
                            <li><span style="margin-right:15px">GIT 주소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span><a :href="project.github_url">{{project.github_url}}</a></span></li>
                            <li v-if="project.etc_url"><span style="margin-right:15px">관련링크&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span><a :href="project.etc_url">{{project.etc_url}}</a> </span></li>
                            <li><span style="margin-right:15px;"><a href="#"><b>프로젝트 더보기</b></a></span></li>
                          </ul>
                        </section>
                      </section>
                    </div>
                  </div>
                </div>
              </div>
    </div>
  </transition>
</template>
<script>
  import http from '../util/http-common'
  import { VueEditor } from 'vue2-editor'
  export default {
    components: {
      VueEditor
    },
    data: function () {
        return { 
          totaldays:0,
          likeCount:0,
          projects:[],
          chartData : {},
          chartSettings : {},

          basicinfo:'',
          portfolioUser:'',
          tag: [],
          stack: [],
          seq_user: this.$store.state.userInfo.seq,
        }
    },
    created(){ 
      this.chartData = {
        rows: [
        ]
      }
      this.chartSettings = {
        dimension: 'stack',
        metrics: 'rate'
      }
      this.stacks = [
      ]
      this.getBasicInfo(this.$route.params.seq);
      this.getPortfolioInfo(this.$route.params.seq);
    },
    methods: {
      getBasicInfo(seq){
        http.get('portfolio/'+seq)
        .then(({data}) => {
            this.basicinfo=data
            // 프로젝트 작성자 정보 불러오기.
            http.get('user/'+data.seq_blog)
            .then(({data}) => {
              this.portfolioUser=data
              // alert(data.seq);
            }) 
         })
      },
      getPortfolioInfo(seq){
        // 프로젝트 불러오기.
        http.get('portfoliopjt/'+seq)
        .then(({data}) => {
            this.projects=data
            for(var i=0;i<data.length;i++){
            //좋아요 개수 추가
            this.likeCount += data[i].like_count
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
         })
      },
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
#timeline h3{
  right: 14rem;
}
#timeline{
  margin:auto;
}
#timeline section.year section ul li:not(:first-child){
  width: 600px;
}
.header-block{
  height: 500px;
  overflow: hidden;
  position: relative;
  background-color: transparent;
}
.main-block{
  position: relative;
  margin-left : 20%;
  margin-right : 20%;
  background-color :transparent;
}
.label{
  font-size: 14px !important;
}
























a:link { color: #B1B0AC; text-decoration: none;}
a:visited { color: #B1B0AC;; text-decoration: none;}
a:hover { color: black; text-decoration: bold;}

.header-image{
  float:left; 
  text-align: center;
  position: absolute;
  top: 50%;
  left: 30%;
  transform: translate( 0%, -50% );
  color:black;
  font-size:80px;
}
.header-text{
  vertical-align:middle;
  float:right; 
  /* margin-left:100px; */
  text-align: left;
  position: absolute;
  top: 50%;
  left: 45%;
  transform: translate( 0%, -50% );
  color:black;
  font-size:40px;
  min-width: 500px;
}
.header-text-noImage{
  left: 50%;
  margin-left: 0px;
  transform: translate( -50%, -50% );
}
.box{
   padding-left:300px;
   padding-right:300px;
}
.pjt-margin{
  margin-bottom:60px;

}
.pjt-title{
  font-size:14px;
}
.pjt-content{
  margin-top:-2px;
  font-size:12px;
  word-spacing: 2px;
  line-height:30px;
}
.avatar{
  width:auto;
  height:250px;
}
.nextToAvater{
  min-width: 500px;
  height:250px;
}
</style>