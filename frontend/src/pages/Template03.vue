<template>
 <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <div class="box">
        <div class="box-body" style="min-height:400px;padding-left:300px;padding-right:300px;">


                    <div  style="margin-top:50px;background-color:lightgray;">

                      <!-- 1. 배너 시작-->
                      <div style="height:220px;background-color:darkgray;padding-top:30px;">
                         

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
                      <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" :src="portfolio.profile_img_url" style="width: 160px; height: 160px;border:10px solid white;">
                      </div>
                      <!-- 2. 얼굴 끝 -->

                            <div style="margin-left:100px;margin-right:100px;">

                            <!-- 3. 소개 -->
                            <div style="background-color:white;height:300px;margin-top:15px;padding:80px 70px 70px 70px;">
                              <!-- 깃허브주소 -->
                              <span style="text-align: center;font-family: 'Noto Sans KR', sans-serif;">
                                희망 직무 {{portfolio.content}}
                              </span> 
                              <p> {{portfolio.github_url}}</p>
                              <p>{{user.email}}</p>
                              <p>{{user.tel}}</p>
                            </div>
                            <!-- 3. 소개 끝 -->

                          
                            <!-- 4. 프로젝트 타임라인 -->
                            <div class="box" style="margin-top:50px;">
                                <div class="box-body" style="min-height:400px;">
                                    <div class="container-timeline">
                                        <div class="header-timeline">
                                            <h1>Experience</h1>
                                            <h2>A timeline that shows project History</h2> </div>

                                        <div class="item-timeline">
                                            <div id="timeline">
                                                <div>
                                                    <section v-for="(project,index) in portfoliopjt" :key="index"  class="year">
                                                        <h4>2020</h4>
                                                        <section>
                <ul>
                          <div id="weather">
                            <div class="panelLeft" style="background: rgba(50, 170, 250, 0.5);display:inline-block;">
                                <div class="top-cal-devin" style="float:left;">
                                    <p>{{project.title}}</p>
                                </div> 
                                <br>
                                <div class="pull-bottom">
                                     <p>{{project.summary}}</p>
                                </div>
                                 <span v-html="project.content"></span>
                            </div>
                            <div class="panelRight">
                                <div class="customHeight">
                                    <p>Tech Stack</p><img :src="project.img_url" alt="" />
                                    <hr/>
                                        <span v-for="(stack,index) in project.stacks" :key="index" style="display:inline-block;"><img class="media-object img-circle pull-left" :src="stack.stack_img_url" style="width: 40px; height: 40px;margin-right:10px;float:left;"></span>
                                </div>
                                <div class="remainingHeight">
                                    <p>Role</p>
                                    <div class="forecast">
                                        <li v-for="(role,index) in project.roles" :key="index">{{role.role}}</li>
                                    </div>
                                </div>
                            </div>
                        </div> 
                </ul>
                                                       
                                                          
                                                        </section>
                                                    </section>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 프로젝트 타임라인 끝 -->

                            <!-- 5.기술스택 -->
                            <div style="background-color:white;height:300px;margin-top:50px;">

                              <div class="header-timeline">
                                <h1>Tech Stack</h1>
                              </div>
                                <!-- bar start -->
                                <div class="box">
                                          <div class="box-body">
                                              <bars :data="[1, 2, 5, 9, 5, 10, 3, 5, 2, 5, 1, 8, 2, 9, 0]" :gradient="['#000000', '#000000']">
                                              </bars>
                                          </div>
                                          
                                </div>
                              <!-- bar end -->
                            
                            <!-- 기술스택 끝 -->
                            </div>

                            <!-- 6. 기타 -->
                             <div style="background-color:white;height:300px;margin-top:50px;">

                             </div>
                            
                            <!-- 끝 -->
                            <div style="height:100px;">

                            </div>

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
          seq : this.$route.params.seq,
          user : '',
          portfolio : '',
          portfoliopjt : '',
        }
    },
    created(){
      http.get(`portfolio/${this.seq}`).then(({data}) => {
          this.portfolio=data
           http.get(`user/${data.seq_blog}`).then(({data}) => {
           this.user=data
           });
      });
      http.get(`portfoliopjt/${this.seq}`).then(({data}) => {
          this.portfoliopjt=data
      });
    },  
    methods: {
      
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