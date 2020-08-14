<template>
 <transition name="el-zoom-in-top">
    <div class="content-wrapper" >
      <div class="box">
        <div class="box-body" style="min-height:400px;padding-left:300px;padding-right:300px;">


                    <div  id="portfolio" style="margin-top:50px;background-color:white;box-shadow: 3px 3px 33px 4px  rgba(163, 163, 163, 0.404);">

<el-button data-html2canvas-ignore="true" type="primary" style="position:absolute;" @click="PDF">PDF</el-button>

                      <!-- 1. 배너 시작-->
                      <div style="height:200px;background-color:#51555c;padding-top:30px;">
                         

                          <div style="text-align: center;font-family: 'Noto Sans KR', sans-serif;color:white;">
                              <!-- 1.1 이름 -->
                              <p style="font-size:33px;">{{portfolio.name}}</p>
                              <!-- 1.2 신상 -->
                              <p style="font-size:15px;">{{portfolio.title}} 지원 포트폴리오</p>
                          </div>

                      </div>
                      <!-- 1. 배너 끝 -->

                      <!-- 2. 얼굴 시작 -->
                      <div style="text-align: center;position: absolute;top: 295px;left: 50%;transform: translate( -50%, -50% );">
                          <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" :src="portfolio.profile_img_url" style="width: 160px; height: 160px;border:10px solid white;box-shadow: 3px 3px 33px 4px  rgba(163, 163, 163, 0.404);">
                      </div>
                      <!-- 2. 얼굴 끝 -->

<!-- 좌우여백 -->
<div style="margin-left:100px;margin-right:100px;">

                      <!-- 3. 소개 -->
                      <div style="background-color:white;height:250px;margin-top:30px;padding:80px 70px 70px 70px;box-shadow: 3px 3px 33px 4px  rgba(163, 163, 163, 0.404);">
                              
                          <!-- 3-1. 깃허브주소, 이메일주소, 연락처 -->   
                          <div class="row" style="margin-bottom:50px;">
                            <div class="col-sm-4" style="display:inline-block;cursor:pointer;">
                              <img class="media-object img-circle" src="../assets/github_icon.png" style="width: 18px; height: 18px;float:left;">
                              <span style="float:left;">&nbsp;&nbsp;{{portfolio.github_url}}</span>
                            </div>
                            <div class="col-sm-4" style="cursor:pointer;"><i class="ti-email "></i>&nbsp;&nbsp;{{portfolio.email}}</div>
                               <div class="col-sm-4" style="cursor:pointer;"><i class="ti-mobile "></i>&nbsp;&nbsp;{{portfolio.tel}}</div>
                            </div>
                          <!-- 3-1. -->
                          
                          <!-- 3-2. portfolio.content -->
                          <span style="text-align: center;font-family: 'Noto Sans KR', sans-serif;">
                                희망 직무 {{portfolio.content}}
                          </span> 
                          <!-- 3-2. -->
                         <br>

                      </div>
                       <!-- 3. 소개 끝 -->



              <!-- 5. 기술스택 -->
              <div class="header-timeline" style="margin-top:60px;margin-bottom:30px;">
                                <h1>Tech Stack</h1>
              </div>
                         
                 <div style="background-color:white;padding:0px 30px 30px 30px;
                 box-shadow: 3px 3px 33px 4px  rgba(163, 163, 163, 0.404);">
                 <br> <br>
                 <radar-chart v-bind:label="label"></radar-chart>
                 <div style="margin-bottom:40px;"></div>
                 </div>
               <!-- 5. 기술스택 끝 -->

</div>

                             

          <!-- 4. 프로젝트 타임라인 -->
          <div class="row" style="border:none;padding:30px;margin-top:40px;">
          
          <!-- 4-0. 타임라인 헤더 -->
                            <div class="header-timeline">
                                <h1>EXPERIENCE</h1>
                                <h2>A timeline that shows my project History</h2>
                            </div>
                            
                         <div v-for="(project,index) in portfoliopjt" :key="index" >
                           
                              <div class="col-sm-1"></div>

                            <!-- 4-1. 프로젝트 시작-끝 날짜 -->
                              <div class="col-sm-2" style="margin-top:60px;">
                                            <span style="font-size:16px;">{{parseDateYearMonth(project.start_date)}} - </span><br>
                                            <span style="font-size:16px;">{{parseDateYearMonth(project.finish_date)}}</span>
                              </div>
                            <!-- 4-1. --> 

                              <div class="col-sm-9" style="margin-top:60px; border-left: 1px solid gray;">
                                            <div style="box-shadow: 3px 3px 33px 4px   rgba(163, 163, 163, 0.404);margin-right:100px;;margin-left:30px;padding:20px;">
                                                        
                                              <div class="well-media">

                                              <!-- 4-2. 프로젝트 이미지 -->
                                                  <div class="vendor">
                                                       <a class="fancybox" rel="group" href="#">
                                                       <img @click="goDetailProject(project.seq)" class="img-responsive-media" style="height: 140px;" :src="project.img_url" alt="">
                                                       </a>
                                                  </div>
                                              <!-- 4-2. -->

                                              <!-- 4-3. 프로젝트 제목, 요약, 역할 -->
                                                  <div @click="goDetailProject(project.seq)" class="video-text" style="font-family: 'Noto Sans KR', sans-serif;cursor:pointer;">
                                                                    <p style="font-size:18px;">{{project.title}}</p>
                                                                    <p style="font-size:13px;">{{project.summary}}</p>

                                                                    <hr>
                                                                    <div v-for="(role,index) in project.roles" :key="index" >
                                                                    <li style="font-size:13px;margin-bottom:10px;">{{role.role}}</li>
                                                                    </div>
                                                  </div>
                                              <!-- 4-3. -->

                                              <!-- 4-4. 프로젝트 기술스택 -->
                                              <div class="tag-nest" v-for="(stack,index) in project.stacks" style="display:inline-block;margin-right:4px;" :key="index">
                                                <i> <img class="img-responsive-media" style="width:40px;height:40px;" :src="stack.stack_img_url" alt=""></i>
                                              </div>
                                              <!-- 4-4. -->

                                              <!-- 4-5. 프로젝트 깃허브 주소 -->
                                              <i class="ti-link" style="margin-left:10px;"></i><span style="cursor: pointer;" @click="goUrl(project.github_url)">&nbsp;{{project.github_url}}</span>
                                              <!-- 4-5. -->

                                                    
                                              </div>
                                              </div>
                               
                                           </div>
                                          </div>
                                       <!--  -->

           </div>
       <!-- 4. 프로젝트 타임라인 끝 -->

                           
                            <div style="height:140px;background-color:white"></div>
                            
                            </div>
                            
                            

                            
                            </div>

                           
                       
              </div>
              
           </div>
        
    </transition>
</template>
<script>
  import http from '../util/http-common'
  import { VueEditor } from 'vue2-editor'
  import html2canvas from 'html2canvas'
  import jsPDF from 'jspdf'  
  import Radar from '../components/RadarChart'
  export default {
    name: 'pdf',
    components: {
      VueEditor,
      'radar-chart': Radar
    },
    data: function () {
        return { 
          seq : this.$route.params.seq,
          portfolio : '',
          portfoliopjt : '',
          portfoliostack : '',
          label : [],
        }
    },
    created(){
          http.get(`portfolio/${this.seq}`).then(({data}) => {
          this.portfolio=data
      });
      http.get(`portfoliopjt/${this.seq}`).then(({data}) => {
          this.portfoliopjt=data
      });
      http.get(`projectstack/${this.seq}`).then(({data}) => {
          for(var i=0;i<data.length;i++){
            this.label[i] = data[i].stack
          }
      });


    
    },  
    methods : {
       // Url로 이동
      goUrl(url){
        window.open('http://'.concat(url), '_blank');
      },
      goDetailProject(seq){
            this.$router.push(`/blog/project/${seq}`)
      },
      parseDateYearMonth(date){
        return date.substr(0,7)
      },
      PDF(){
      window.scrollTo(0, 0);
      window.html2canvas = html2canvas
      const pageWidth = 210 //캔버스 너비 mm
      const pageHeight = 295 //캔버스 높이 mm
      let ele = document.querySelector("#portfolio")
			let width = ele.offsetWidth // 셀렉트한 요소의 px 너비
      let height = ele.offsetHeight // 셀렉트한 요소의 px 높이
      let imgHeight = pageWidth * height/width 
      
      html2canvas($('#portfolio')[0]).then(function(canvas) {
        let position = 0
        let pdf = new jsPDF('p', 'mm', 'a4')
        let imgData = canvas.toDataURL('image/jpeg');
        pdf.addImage(imgData, 'jpeg', 0, position, pageWidth, imgHeight, undefined, 'slow')

        //Paging 처리
					let heightLeft = imgHeight //페이징 처리를 위해 남은 페이지 높이 세팅.
					heightLeft -= pageHeight
					while (heightLeft >= 0) {
						position = heightLeft - imgHeight
						pdf.addPage();
						pdf.addImage(imgData, 'jpeg', 0, position, pageWidth, imgHeight)
						heightLeft -= pageHeight
          }
          
        pdf.save('포트폴리오.pdf')
      })
     

      }
    
    },
  }
</script>
<style scoped>
@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
@font-face {
font-family: 'NotoKrL';
font-style: normal;
font-weight: 100;
src: local('Noto Sans Light'), local('NotoSans-Light'), url(/fonts/NotoSans-Light.eot);
src: url(/fonts/NotoSans-Light.eot?#iefix) format('embedded-opentype'),
url(/fonts/NotoSans-Light.woff2) format('woff2'),
url(/fonts/NotoSans-Light.woff) format('woff');;
}
@font-face {
font-family: 'NotoKrR';
font-style: normal;
font-weight: 300;
src: local('Noto Sans Regular'), local('NotoSans-Regular'), url(/fonts/NotoSans-Regular.eot);
src: url(/fonts/NotoSans-Regular.eot?#iefix) format('embedded-opentype'),
url(/fonts/NotoSans-Regular.woff2) format('woff2'),
url(/fonts/NotoSans-Regular.woff) format('woff');
}
@font-face {
font-family: 'NotoKrM';
font-style: normal;
font-weight: 500;
src: local('Noto Sans Medium'), local('NotoSans-Medium'), url(/fonts/NotoSans-Medium.eot);
src: url(/fonts/NotoSans-Medium.eot?#iefix) format('embedded-opentype'),
url(/fonts/NotoSans-Medium.woff2) format('woff2'),
url(/fonts/NotoSans-Medium.woff) format('woff');
}
@font-face {
font-family: 'NotoKrB';
font-style: normal;
font-weight: 700;
src: local('Noto Sans Bold'), local('NotoSans-Bold'), url(/fonts/NotoSans-Bold.eot);
src: url(/fonts/NotoSans-Bold.eot?#iefix) format('embedded-opentype'),
url(/fonts/NotoSans-Bold.woff2) format('woff2'),
url(/fonts/NotoSans-Bold.woff) format('woff');
}
</style>