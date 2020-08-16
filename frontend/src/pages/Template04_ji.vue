<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <br><br><br>
      <div class="resume">
        <div class="base">
          <div class="profile">
            <div class="photo">
              <img :src="portfolioInfo.profile_img_url" style="text-align:center"/>
              <!-- <i class="fas fa-rocket"></i> -->
            </div>
            <div class="info">
              <h1 class="name" v-text="portfolioInfo.name"></h1>
              <!-- 지원 직무 : portfolioInfo.position"-->
              <h2 class="job"> Frontend Web Designer</h2>
              <!-- <h2 class="job" v-text="portfolioInfo.position"></h2> -->
            </div>
          </div>
          <div class="about">
            <h3>About Me</h3>
            <!-- 간단한 자기소개 : portfolioInfo.introduction"-->
            <!-- <p v-text="portfolioInfo.introduction"></p> -->
            <p v-text=tempintroduction />
          </div>
          <div class="contact">
            <h3>Contact Me</h3>
            <div class="call"><a :href=telstr><i class="ti-mobile"></i><span v-text="portfolioInfo.tel" ></span></a></div>
            <!-- <address id="getaddress"/> -->
            <!-- <div class="address"><a :href=addstr><i class="ti-map-alt"></i><span>{{portfolioInfo.address}}</span></a> -->
            <div class="address"><a :href=addstr target="_blank"><i class="ti-map-alt"></i><span>{{tempaddress}}</span></a></div>
            <div class="email"><a :href=portfolioInfo.email><i class="ti-email"></i><span>{{portfolioInfo.email}}</span></a></div>
            <div class="website"><a :href=gitstr target="_blank"> <i class="ti-github"></i><span>{{portfolioInfo.github_url}}</span></a></div>
          </div>
          <div class="follow">
            <h3>Follow Me</h3>
            <div class="box" style="background-color:transparent; ">
              <!-- <a href="#" target="_blank"><i class="ti-facebook"></i></a>
              <a href="#" target="_blank"><i class="ti-instagram "></i></a>
              <a href="#" target="_blank"><i class="ti-pinterest-alt "></i></a>
              <a href="#" target="_blank"><i class="ti-linkedin "></i></a> -->

              <!-- 기타 주소로 입력받은 주소 앞부분이 https://www.facebook.com/ 랑 일치하면 facebook으로 인식해서 넣기! 이런식으로 아래 4개 넣어주면 될듯.. -->
              <!-- 
                https://www.facebook.com/  
                https://www.instagram.com/
                https://www.pinterest.com/
                https://www.linkedin.com/in/
              -->
              
              <!-- <a v-for="(social,index) in socials" v-bind:key="index" :href=social.link target="_blank"><i :class=social.icon></i></a>\ -->
              <a v-for="(social,index) in tempsocials" v-bind:key="index" :href=social.link target="_blank"><i :class=social.icon></i></a>
            </div>
          </div>
        </div>
        <div class="func">
          <div class="work">
            <h3><i class="fa fa-briefcase"></i>Experience</h3>
            <ul>
              <!-- <li><span>Technical Consultant -<br>Web Design</span><small>Fiserv</small><small>Apr 2018 - Now</small></li>
              <li><span>Web Designer</span><small>Lynden</small><small>Jan 2018 - Apr 2018</small></li>
              <li><span>Intern - Web Design</span><small>Lynden</small><small>Aug 2017 - Dec 2017</small></li> -->
              <li  v-for="(experience,index) in tempexperiences" v-bind:key="index">
                <span v-text=experience.position />
                <small v-text=experience.company />
                <small><span v-text=experience.start /> - <span v-text=experience.start /></small>
              </li>
            </ul>
          </div>
          <div class="edu">
            <h3><i class="fa fa-graduation-cap"></i>Education</h3>
            <ul>
              <!-- <li><span>Bachelor of Science<br>Web Design and Development</span><small>BYU-Idaho</small><small>Jan. 2016 - Apr. 2018</small></li>
              <li><span>Computer Science</span><small>Edmonds Community College</small><small>Sept. 2014 - Dec. 2015</small></li>
              <li><span>High School</span><small>Henry M. Jackson High School</small><small>Jan. 2013 - Jun. 2015</small></li> -->
              <li  v-for="(education,index) in tempeducations" v-bind:key="index">
                <span v-text=education.major />
                <small v-text=education.name />
                <small><span v-text=education.start /> - <span v-text=education.start /></small>
              </li>
            </ul>
          </div>

          <!-- /api/portfolio/skill/{seq_post_portfolio} 에 이미지 url 추가 가능하면 ㄱㄱ  -->
          <div class="skills-prog">
            <h3><i class="fas fa-code"></i>Programming Skills</h3>
            {{programmingSkills}}
            <ul>
              <li><span>HTML5</span>
                <div class="skills-bar" data-percent="100">
                  <div class="bar"/>
                </div>
              </li>
              <li v-for="(programmingSkill,index2) in programmingSkills" v-bind:key="index2" >
                <span>{{programmingSkill.skill}}</span>
                <div class="skills-bar" data-percent="1">
                  <div class="bar"/>
                </div>
              </li>
              <!-- <li><span>{{programmingSkills[1].skill}}</span>
                <div class="skills-bar" data-percent="10">
                  <div class="bar"/>
                </div>
              </li> -->





              <!-- <li data-percent="95"><span>HTML5</span>
                <div class="skills-bar">
                  <div class="bar"></div>
                </div>
              </li>
              <li data-percent="90"><span>CSS3 & SCSS</span>
                <div class="skills-bar">
                  <div class="bar"></div>
                </div>
              </li>
              <li data-percent="60"><span>JavaScript</span>
                <div class="skills-bar">
                  <div class="bar"></div>
                </div>
              </li>
              <li data-percent="50"><span>jQuery</span>
                <div class="skills-bar">
                  <div class="bar"></div>
                </div>
              </li>
              <li data-percent="40"><span>JSON</span>
                <div class="skills-bar">
                  <div class="bar"></div>
                </div>
              </li>
              <li data-percent="55"><span>PHP</span>
                <div class="skills-bar">
                  <div class="bar"></div>
                </div>
              </li>
              <li data-percent="40"><span>MySQL</span>
                <div class="skills-bar">
                  <div class="bar"></div>
                </div>
              </li> -->
            </ul>
          </div>
          <div class="skills-soft">
            <h3><i class="fas fa-bezier-curve"></i>Software Skills</h3>
            <ul>
              <li data-percent="90">
                <svg viewbox="0 0 100 100">
                  <circle cx="50" cy="50" r="45"></circle>
                  <circle class="cbar" cx="50" cy="50" r="45"></circle>
                </svg><span>Illustrator</span><small></small>
              </li>
              <li data-percent="75">
                <svg viewbox="0 0 100 100">
                  <circle cx="50" cy="50" r="45"></circle>
                  <circle class="cbar" cx="50" cy="50" r="45"></circle>
                </svg><span>Photoshop</span><small></small>
              </li>
              <li data-percent="85">
                <svg viewbox="0 0 100 100">
                  <circle cx="50" cy="50" r="45"></circle>
                  <circle class="cbar" cx="50" cy="50" r="45"></circle>
                </svg><span>InDesign</span><small></small>
              </li>
              <li data-percent="65">
                <svg viewbox="0 0 100 100">
                  <circle cx="50" cy="50" r="45"></circle>
                  <circle class="cbar" cx="50" cy="50" r="45"></circle>
                </svg><span>Dreamweaver</span><small></small>
              </li>
            </ul>
          </div>
          <!-- <div class="interests">
            <h3><i class="fas fa-star"></i>Interests</h3>
            <div class="interests-items">
              <div class="art"><i class="fas fa-palette"></i><span>Art</span></div>
              <div class="art"><i class="fas fa-book"></i><span>Books</span></div>
              <div class="movies"><i class="fas fa-film"></i><span>Movies</span></div>
              <div class="music"><i class="fas fa-headphones"></i><span>Music</span></div>
              <div class="games"><i class="fas fa-gamepad"></i><span>Games</span></div>
            </div>
          </div> -->
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
          portfolioInfo:'',
          projects:[],
          programmingSkills:[],




          introstr:'',
          telstr:'',
          addstr:'',
          gitstr:'',
          // 임시변수
          tempintroduction: '풀스택 개발자를 꿈꾸는 SSAFY 교육생입니다. 주 언어는 Java이고 학부생시절 C와 C++을 활용한 프로젝트도 다수 진행했습니다. 호주 인턴 경험이 있으며 QA 직무를 수행했습니다.',
          tempaddress:'경기도 용인시 기흥구',
          tempsocials:[{
                        link: 'https://www.facebook.com/ ',
                        icon: 'ti-facebook '
                      },{
                        link: 'https://www.instagram.com/',
                        icon: 'ti-instagram '
                      },{
                        link: 'https://www.pinterest.com/',
                        icon: 'ti-pinterest-alt '
                      },{
                        link: 'https://www.linkedin.com/in/',
                        icon: 'ti-linkedin '
                      }
                      ],
          tempexperiences:[{
                          position: '웹디자인',
                          company: '삼성전자',
                          start:  '2020-04',
                          end:  '현재', // 이거 표현 어케해야될지 모르겠음 2999-99로 되어있으면 현재?
                        },{
                          position: '개발자',
                          company: '구글',
                          start:  '2018-04',
                          end:  '2020-03', 
                        },{
                          position: '웹디자인',
                          company: '삼성전자',
                          start:  '2020-04',
                          end:  '현재', // 이거 표현 어케해야될지 모르겠음 2999-99로 되어있으면 현재?
                        },{
                          position: '개발자',
                          company: '구글',
                          start:  '2018-04',
                          end:  '2020-03', 
                        }
                        ],
          tempeducations:[{
                          major: '컴퓨터소프트웨어학과',
                          name: '광운대학교',
                          start:  '2018-02',
                          end:  '2013-03',
                        },{
                          major: '고등학교',
                          name: '분당고등학교',
                          start:  '2013-02',
                          end:  '2010-03',
                          },{
                          major: '중학교',
                          name: '내정중학교',
                          start:  '2010-02',
                          end:  '2008-07',
                        },{
                          major: '중학교',
                          name: 'AOBA JAPAN INTERNATIONAL SCHOOL',
                          start:  '2008-06',
                          end:  '2005-05',
                          }
                        ]
        }
    },
    created(){ 
      this.getPortfolioInfo(this.$route.params.seq);
      this.getProgrammingSkills(this.$route.params.seq);
      this.getProjectsInfo(this.$route.params.seq);
    },
    mounted(){
      // 주소 입력, portfolioInfo.address
      // var link = $('#getaddress').append("<a href='http://maps.google.com/maps?q="+this.portfolioInfo.address+"' target='_blank'>"+ this.portfolioInfo.address+"</a>")
      // 임시
      var link = $('#getaddress').append("<a href='http://maps.google.com/maps?q="+this.tempaddress+"' target='_blank'>"+ this.tempaddress+"</a>")
      $(".skills-prog li").find(".skills-bar").each(function(i) {
        alert("들어옴");
        $(this)
          .find(".bar")
          .delay(i * 150)
          .animate({width:$(this).attr("data-percent") + "%"},
            1000,
            "linear",
            function() {
              return $(this).css({
                "transition-duration": ".5s"
              });
            }
          );
      });
      $(".skills-soft li")
      .find("svg")
      .each(function(i) {
        var c, cbar, circle, percent, r;
        circle = $(this).children(".cbar");
        r = circle.attr("r");
        c = Math.PI * (r * 2);
        percent = $(this)
          .parent()
          .data("percent");
        cbar = (100 - percent) / 100 * c;
        circle.css({
          "stroke-dashoffset": c,
          "stroke-dasharray": c
        });
        circle.delay(i * 150).animate(
          {
            strokeDashoffset: cbar
          },
          1000,
          "linear",
          function() {
            return circle.css({
              "transition-duration": ".3s"
            });
          }
        );
        $(this)
          .siblings("small")
          .prop("Counter", 0)
          .delay(i * 150)
          .animate(
            {
              Counter: percent
            },
            {
              duration: 1000,
              step: function(now) {
                return $(this).text(Math.ceil(now) + "%");
              }
            }
          );
      });
    },
    methods: {
      getPortfolioInfo(seq){
        http.get('portfolio/'+seq)
        .then(({data}) => {
            this.portfolioInfo=data;

            // 입력받은 데이터 가공
            this.telstr="tel:"+data.tel;
            this.gitstr="https://"+data.github_url;

            //임시
            this.addstr="http://maps.google.com/maps?q="+this.tempaddress; 
            // this.addstr="http://maps.google.com/maps?q="+data.address;
         })
      },
      getProgrammingSkills(seq){
        http.get('portfolio/skill/'+seq)
        .then(({data}) => {
            this.programmingSkills=data;
         })
      },
      getProjectsInfo(seq){
        http.get('portfoliopjt/'+seq)
        .then(({data}) => {
            this.projects=data
         })
      },
   },
  }

  

</script>
<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css?family=Montserrat");

// $darkest-blue: #1a237e;
// $darker-blue: #283593;
// $dark-blue: #303f9f;
// $medium-blue: #3949ab;
// $light-blue: #3f51b5;
// $lighter-blue: #5c6bc0;
// $lightest-blue: #9fa8da;
// $white: #ffffff;
// $black: #0e1442;
// $orange: #ffb300;
// $yellow: #fdd835;
// $dark-pink: #ad1457;
// $light-pink: #d81b60;

$darkest-blue: #11212E;
$darker-blue: #1c374d;
$dark-blue: rgb(30, 59, 82);
$medium-blue: rgb(46, 88, 122);
$light-blue: rgb(63, 118, 163);
$lighter-blue: rgb(107, 124, 221);
$lightest-blue: rgb(137, 153, 241);
$white: #ffffff;
$black: #0e1442;
$orange: #b68c2c;
$yellow: #bea231;
// $orange: #9ec2cd;
// $yellow: #9EBBCD;

// @charset "UTF-8";
// * {
//   outline: none;
// }

*,
*:before,
*:after {
  -webkit-box-sizing: inherit;
  -moz-box-sizing: inherit;
  box-sizing: inherit;
}

html,
body {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  background: $white;
  cursor: default;
  font-family: "Montserrat", sans-serif;
  font-size: 16px;
}

a {
  text-decoration: none;
  color: $white;
  display: block;
  transition-duration: 0.3s;
}

ul {
  list-style-type: none;
  padding: 0;
}

h2 {
}

h3 {
  color: $orange;
  margin: 10px 0;
  text-transform: lowercase;
  font-size: 1.25em;
}

.resume {
  width: 960px;
  background: $darkest-blue;
  color: $white;
  margin: 20px auto;
  box-shadow: 10px 10px $black;
  position: relative;
  display: flex;
}
.resume .base,
.resume .func {
  box-sizing: border-box;
  float: left;
}
.resume .base > div,
.resume .func > div {
  padding-bottom: 10px;
}
.resume .base > div:last-of-type,
.resume .func > div:last-of-type {
  padding-bottom: 0;
}
.resume .base {
  width: 30%;
  padding: 30px 15px;
  background: $darker-blue;
  color: $white;
}
.resume .base .profile {
  background: $orange;
  padding: 30px 15px 40px 15px;
  margin: -30px -15px 45px -15px;
  position: relative;
  z-index: 2;
}
.resume .base .profile::after {
  content: "";
  position: absolute;
  background: $dark-blue;
  width: 100%;
  height: 30px;
  bottom: -15px;
  left: 0;
  transform: skewY(-5deg);
  z-index: -1;
}
.resume .base .profile .photo img {
  width: 100%;
  border-radius: 50%;
}
.resume .base .profile .photo {
  display: flex;
  justify-content: center;
  align-items: center;
}
.resume .base .profile .fa-rocket {
  font-size: 100px;
  text-align: center;
  margin: auto;
  color: $darker-blue;
}
.resume .base .profile .info {
  text-align: center;
  color: $white;
}
.resume .base .profile .info .name {
  margin-top: 10px;
  margin-bottom: 0;
  font-size: 1.75em;
  text-transform: lowercase;
  color: $darkest-blue;
}
.resume .base .profile .info .job {
  margin-top: 10px;
  margin-bottom: 0;
  font-size: 1.5em;
  text-transform: lowercase;
  color: $darker-blue;
}
.resume .base .contact div {
  line-height: 24px;
}
.resume .base .contact div a:hover {
  color: $yellow;
}
.resume .base .contact div a:hover span::after {
  width: 100%;
}
.resume .base .contact div:hover i {
  color: $yellow;
}
.resume .base .contact div i {
  color: $orange;
  width: 20px;
  height: 20px;
  font-size: 20px;
  text-align: center;
  margin-right: 15px;
  transition-duration: 0.3s;
}
.resume .base .contact div span {
  position: relative;
}
.resume .base .contact div span::after {
  content: "";
  position: absolute;
  background: $yellow;
  height: 1px;
  width: 0;
  bottom: 0;
  left: 0;
  transition-duration: 0.3s;
}
.resume .base .follow .box {
  text-align: center;
  vertical-align: middle;
}
.resume .base .follow .box a {
  display: inline-block;
  vertical-align: text-bottom;
}
.resume .base .follow .box a:hover i {
  background: $yellow;
  border-radius: 5px;
  transform: rotate(45deg) scale(0.8);
}
.resume .base .follow .box a:hover i::before {
  transform: rotate(-45deg) scale(1.5);
}
.resume .base .follow .box i {
  display: inline-block;
  font-size: 30px;
  background: $orange;
  width: 60px;
  height: 60px;
  border-radius: 50%;
  line-height: 60px;
  color: $darker-blue;
  margin: 0 10px 10px 10px;
  transition-duration: 0.3s;
}
.resume .base .follow .box i::before {
  transition-duration: 0.3s;
}
.resume .base .follow .box i.fa::before {
  display: block;
}
.resume .func {
  width: 100%;
  padding: 30px;
}
.resume .func:hover > div {
  transition-duration: 0.5s;
}
.resume .func:hover > div:hover h3 i {
  transform: scale(1.25);
}
.resume .func:hover > div:not(:hover) {
  opacity: 0.5;
}
.resume .func h3 {
  transition-duration: 0.3s;
  margin-top: 0;
}
.resume .func h3 i {
  color: $darker-blue;
  background: $orange;
  width: 42px;
  height: 42px;
  font-size: 20px;
  line-height: 42px;
  border-radius: 50%;
  text-align: center;
  vertical-align: middle;
  margin-right: 8px;
  transition-duration: 0.3s;
}
.resume .func .work,
.resume .func .edu {
  float: left;
}
.resume .func .work small,
.resume .func .edu small {
  display: block;
  opacity: 0.7;
}
.resume .func .work ul li,
.resume .func .edu ul li {
  position: relative;
  margin-left: 15px;
  padding-left: 25px;
  padding-bottom: 15px;
}
.resume .func .work ul li:hover::before,
.resume .func .edu ul li:hover::before {
  animation: circle 1.2s infinite;
}
.resume .func .work ul li:hover span,
.resume .func .edu ul li:hover span {
  color: $yellow;
}
@keyframes circle {
  from {
    box-shadow: 0 0 0 0px $yellow;
  }
  to {
    box-shadow: 0 0 0 6px rgba(255, 255, 255, 0);
  }
}
.resume .func .work ul li:first-of-type::before,
.resume .func .edu ul li:first-of-type::before {
  width: 10px;
  height: 10px;
  left: 1px;
}
.resume .func .work ul li:last-of-type,
.resume .func .edu ul li:last-of-type {
  padding-bottom: 3px;
}
.resume .func .work ul li:last-of-type::after,
.resume .func .edu ul li:last-of-type::after {
  border-radius: 1.5px;
}
.resume .func .work ul li::before,
.resume .func .work ul li::after,
.resume .func .edu ul li::before,
.resume .func .edu ul li::after {
  content: "";
  display: block;
  position: absolute;
}
.resume .func .work ul li::before,
.resume .func .edu ul li::before {
  width: 7px;
  height: 7px;
  border: 3px solid $white;
  background: $orange;
  border-radius: 50%;
  left: 3px;
  z-index: 1;
}
.resume .func .work ul li::after,
.resume .func .edu ul li::after {
  width: 3px;
  height: 100%;
  background: $white;
  left: 5px;
  top: 0;
}
.resume .func .work ul li span,
.resume .func .edu ul li span {
  transition-duration: 0.3s;
}
.resume .func .work {
  width: 48%;
  background: $darker-blue;
  padding: 15px;
  margin: 0 4% 15px 0;
}
.resume .func .edu {
  width: 48%;
  background: $darker-blue;
  padding: 15px;
}
.resume .func .skills-prog {
  clear: both;
  background: $darker-blue;
  padding: 15px;
}
.resume .func .skills-prog ul {
  margin-left: 15px;
}
.resume .func .skills-prog ul li {
  margin-bottom: 8px;
  display: flex;
  align-items: center;
  transition-duration: 0.3s;
}
.resume .func .skills-prog ul li:hover {
  color: $yellow;
}
.resume .func .skills-prog ul li:hover .skills-bar .bar {
  background: $yellow;
  box-shadow: 0 0 0 1px $yellow;
}
.resume .func .skills-prog ul li span {
  display: block;
  width: 120px;
}
.resume .func .skills-prog ul li .skills-bar {
  background: $white;
  height: 2px;
  width: calc(100% - 120px);
  position: relative;
  border-radius: 2px;
}
.resume .func .skills-prog ul li .skills-bar .bar {
  position: absolute;
  top: -1px;
  height: 4px;
  background: $orange;
  box-shadow: 0 0 0 $orange;
  border-radius: 5px;
}
.resume .func .skills-soft {
  clear: both;
  background: $darker-blue;
  padding: 15px;
  margin: 15px 0 0;
}
.resume .func .skills-soft ul {
  display: flex;
  justify-content: space-between;
  text-align: center;
}
.resume .func .skills-soft ul li {
  position: relative;
}
.resume .func .skills-soft ul li:hover svg .cbar {
  stroke: $yellow;
  stroke-width: 4px;
}
.resume .func .skills-soft ul li:hover span,
.resume .func .skills-soft ul li:hover small {
  transform: scale(1.2);
}
.resume .func .skills-soft ul li svg {
  width: 95%;
  fill: transparent;
  transform: rotate(-90deg);
}
.resume .func .skills-soft ul li svg circle {
  stroke-width: 1px;
  stroke: $white;
}
.resume .func .skills-soft ul li svg .cbar {
  stroke-width: 3px;
  stroke: $orange;
  stroke-linecap: round;
}
.resume .func .skills-soft ul li span,
.resume .func .skills-soft ul li small {
  position: absolute;
  display: block;
  width: 100%;
  top: 52%;
  transition-duration: 0.3s;
}
.resume .func .skills-soft ul li span {
  top: 40%;
}
.resume .func .interests {
  background: $darker-blue;
  margin: 15px 0 0;
  padding: 15px;
}
.resume .func .interests-items {
  box-sizing: border-box;
  padding: 0 0 15px;
  width: 100%;
  text-align: center;
  display: flex;
  justify-content: space-between;
}
.resume .func .interests-items div {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
.resume .func .interests-items div:hover i {
  transform: scale(1.2);
}
.resume .func .interests-items div:hover span {
  color: $yellow;
  transition-duration: 0.3s;
}
.resume .func .interests-items div i {
  font-size: 45px;
  width: 60px;
  height: 60px;
  line-height: 60px;
  color: $orange;
  transition-duration: 0.3s;
}
.resume .func .interests-items div span {
  display: block;
}

</style>>
.content{
  padding-top:65px;
  width:80%;
  min-height:1000px;
  background-color: beige;
}
.content-left{
  float: left;
  background-color: rgb(179, 179, 158);
  width: 60%;
  min-height:1000px;
}
.content-right{
  float: right;
  background-color: rgb(245, 245, 186);
  width: 40%;
  min-height:1000px;
}
</style>