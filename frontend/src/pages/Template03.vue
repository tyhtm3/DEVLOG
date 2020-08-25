<template>
  <transition name="el-zoom-in-top">
    <section id="timelineTemplate" v-loading="loading" >
    <div class="content-wrapper" style="background: white;">
      <br><br><br>

      <div class="resume">
            <el-button data-html2canvas-ignore="true" class="pull-top pull-left" type="primary" style="position:absolute;margin-top:-50px;" @click="PDF">PDF</el-button>
            <el-button data-html2canvas-ignore="true" class="pull-top pull-left" type="primary" style="position:absolute;margin-top:-50px; margin-left: 80px; height:40px;" @click="copyurl"><i class="material-icons" style="position:relative; transform: rotate(45deg); font-size:16px;" >link</i></el-button>
        <div class="base">
          <div class="profile">
            <div class="photo">
              <img :src="portfolioInfo.profile_img_url" style="text-align:center"/>
            </div>
            <div class="info">
              <h1 class="name" style="font-weight:bold;" v-text="portfolioInfo.name" ></h1>
              <!-- 지원 직무 : portfolioInfo.position"-->
              <h2 class="job" v-text="portfolioMoreInfo.objective"></h2>
            </div>
          </div>
          <div class="about">
            <h3>About Me</h3>
            <!-- 간단한 자기소개 : portfolioInfo.introduction"-->
            <p v-text="portfolioMoreInfo.introduction"></p>
          </div>
          <div class="contact">
            <h3>Contact Me</h3>
            <div class="call"><a :href=telstr><i class="ti-mobile"></i><span v-text="portfolioInfo.tel" ></span></a></div>
             <div class="address"><a :href=addstr target="_blank"><i class="ti-map-alt"></i><span>{{portfolioMoreInfo.address}}</span></a></div>
            <div class="email"><a ><i class="ti-email"></i><span>{{portfolioInfo.email}}</span></a></div>
            <div class="website"><a :href=gitstr target="_blank"> <i class="ti-github"></i><span>{{portfolioInfo.github_url}}</span></a></div>
             </div>
          <div class="follow">
            <h3>Technical Stacks</h3>
            <div class="box" style="background-color:transparent; ">
              <a v-for="(stack,index) in portfolioStack" :key="index" :alt="stack.stack"  href="#">
                    <div class="thumbnail-stack-img">
                      <img class="stack-img" :src="stack.stack_img_url" :alt="stack.stack" > 
                    </div>
              </a>

              </div>
          </div>
          <div class="follow" >
            <h3>Follow Me</h3>
            <div class="box" style="background-color:transparent;">
              <a :href=devlogstr><img src="../assets/logo_icon.png"></a>
              <a v-for="(social,index) in socials" v-bind:key="index" :href=social.link target="_blank"><i :class=social.icon></i></a>
            </div>
          </div>
        </div>
        <div class="func">
          <div class="work">
            <h3><i class="fa fa-briefcase"></i>Experience</h3>
            <ul>
             <li  v-for="(experience,index) in experiences" v-bind:key="index">
                <span v-text=experience.position />
                <small v-text=experience.company />
                <small><span v-text=experience.start /> <span>~</span> <span v-if="experience.end" v-text=experience.end /><span v-else>재직중</span></small>
              </li>
            </ul>
          </div>
          <div class="edu">
            <h3><i class="fa fa-graduation-cap"></i>Education</h3>
            <ul>
              <li  v-for="(education,index) in educations" v-bind:key="index">
                <span v-text=education.major />
                <small v-text=education.name />
                <small><span v-text=education.start /> - <span v-text=education.start /></small>
              </li>
            </ul>
          </div>

          <div class="skills-prog">
            <h3><i class="material-icons">code</i>Programming Skills</h3>
            <ul >
              <li v-for="(programmingSkill,index) in programmingSkills" v-bind:key="index" >
                <span>{{programmingSkill.skill}}</span>
                <div class="skills-bar" :data-percent=programmingSkill.level>
                  <!-- hover하면 바 옆에 % 나오게 하면 더 좋을듯 -->
                  <div class="bar"/>
                </div>
                <small></small>
              </li>
            </ul>
          </div>


          <div class="skills-soft">
            <h3><i class="material-icons">language</i>Language Skills</h3>
            <ul>
              <li v-for="(languageSkill,index) in languageSkills" v-bind:key="index" :data-percent=languageSkill.level>
                <svg viewbox="0 0 100 100" height="100">
                  <circle cx="50" cy="50" r="45"></circle>
                  <circle class="cbar" cx="50" cy="50" r="45"></circle>
                </svg><span v-text=languageSkill.language /><small></small>
              </li>
            </ul>
          </div> 

          <div class="skills-soft" v-if="certifications">
            <h3><i class="material-icons" v-text=certificationicon></i>Certification</h3>
                <tr v-for="(certification, index) in certifications" v-bind:key="index">
                  <td style="width:30%"><span class="tag" style="font-size:20px; margin:10px; line-height:40px;" @click="goToWebsite(certification.certification)">#{{certification.certification}}</span></td>
                  <td style="width:60%; text-align:right">{{certification.date}}</td>
                </tr>
          </div>
          
          <!-- 프로젝트 경험 -->
          <div class="project">
            <h3><i class="fa fa-briefcase"></i>Projects</h3>
            <ul>
              <li v-for="(project,index) in projects" v-bind:key="index" >
                <a style="width:70%; display:inline-block;"><span v-text=project.title /></a>
                <a style="width:29%; display:inline-block;"><small style="float:right; vertical-align:middle;"><span v-text=project.start_date /> <span>~</span> <span v-if="project.finish_date" v-text=project.finish_date /><span v-else>진행중</span></small></a>

                <small v-text=project.summary style="width:100%"/>
                
                
                <i @click="goDetailProject(project.seq)" class="ti-info-alt " style="float:right;">&nbsp;</i>
                <a :href=project.etc_url><i v-if="project.etc_url"  class="ti-link" style="float:right;">&nbsp;</i></a>
                <a :href=project.github_url> <i v-if="project.github_url"  class="ti-github" style="float:right;">&nbsp;</i></a>
                <div style="">
                <span class="tag" v-for="(stack,i) in project.stacks" v-bind:key="i" style="font-size:small; font-weight:300;">#{{stack.stack}} </span>
                </div>
           
                
              </li>
            </ul>
          </div>
         

        </div>
      </div>
    </div>
  </section>
  </transition>
</template>
<script>  
  import http from '../util/http-common'
  import { VueEditor } from 'vue2-editor'
  import html2canvas from 'html2canvas'
  import jsPDF from 'jspdf'  
  export default {
    name: 'pdf',
    components: {
      VueEditor
    },
    data: function () {
        return { 
          loading: true,
          portfolioOwnerInfo:'',
          portfolioInfo:[],
          portfolioStack:[],
          portfolioMoreInfo:[],
          projects:[],
          socials:[],
          programmingSkills:[],
          languageSkills:[],
          experiences:[],
          educations:[],
          certifications:[],
          certificationicon:[],

          testCertificationLink:[
{key:'금형기술사',value:'0012'},
{key:'유체기계기술사',value:'0030'},
{key:'기계제작기술사',value:'0041'},
{key:'차량기술사',value:'0050'},
{key:'산업기계기술사',value:'0060'},
{key:'공조냉동기계기술사',value:'0071'},
{key:'건설기계기술사',value:'0080'},
{key:'철야금기술사',value:'0090'},
{key:'비철야금기술사',value:'0100'},
{key:'금속재료기술사',value:'0110'},
{key:'표면처리기술사',value:'0120'},
{key:'금속가공기술사',value:'0130'},
{key:'고분자제품기술사',value:'0190'},
{key:'공업화학기술사',value:'0201'},
{key:'화학장치설비기술사',value:'0210'},
{key:'화학공장설계기술사',value:'0220'},
{key:'발송배전기술사',value:'0230'},
{key:'전기응용기술사',value:'0250'},
{key:'철도신호기술사',value:'0261'},
{key:'공업계측제어기술사',value:'0270'},
{key:'전자계산기기술사',value:'0280'},
{key:'전자응용기술사',value:'0301'},
{key:'선박설계기술사',value:'0320'},
{key:'선박건조기술사',value:'0330'},
{key:'선박기계기술사',value:'0351'},
{key:'항공기체기술사',value:'0360'},
{key:'항공기관기술사',value:'0370'},
{key:'토질및기초기술사',value:'0390'},
{key:'토목구조기술사',value:'0400'},
{key:'항만및해안기술사',value:'0410'},
{key:'도로및공항기술사',value:'0420'},
{key:'철도기술사',value:'0430'},
{key:'철도차량기술사',value:'0431'},
{key:'전기철도기술사',value:'0432'},
{key:'수자원개발기술사',value:'0451'},
{key:'상하수도기술사',value:'0460'},
{key:'농어업토목기술사',value:'0470'},
{key:'토목시공기술사',value:'0480'},
{key:'토목품질시험기술사',value:'0481'},
{key:'건축구조기술사',value:'0490'},
{key:'건축기계설비기술사',value:'0501'},
{key:'건축전기설비기술사',value:'0502'},
{key:'건축시공기술사',value:'0510'},
{key:'건축품질시험기술사',value:'0511'},
{key:'방사기술사',value:'0520'},
{key:'방적기술사',value:'0530'},
{key:'제포기술사',value:'0540'},
{key:'염색가공기술사',value:'0550'},
{key:'생사기술사',value:'0560'},
{key:'의류기술사',value:'0561'},
{key:'지하자원개발기술사',value:'0570'},
{key:'탐사기술사',value:'0580'},
{key:'지하자원처리기술사',value:'0590'},
{key:'화약류관리기술사',value:'0591'},
{key:'정보관리기술사',value:'0601'},
{key:'전자계산조직응용기술사',value:'0620'},
{key:'정보통신기술사',value:'0621'},
{key:'도시계획기술사',value:'0650'},
{key:'조경기술사',value:'0670'},
{key:'측량및지형공간정보기술사',value:'0680'},
{key:'지적기술사',value:'0690'},
{key:'해양기술사',value:'0700'},
{key:'수산양식기술사',value:'0701'},
{key:'어로기술사',value:'0702'},
{key:'기계안전기술사',value:'0710'},
{key:'화공안전기술사',value:'0720'},
{key:'전기안전기술사',value:'0730'},
{key:'건설안전기술사',value:'0740'},
{key:'소방설비기술사',value:'0750'},
{key:'산업위생관리기술사',value:'0751'},
{key:'가스기술사',value:'0752'},
{key:'공장관리기술사',value:'0760'},
{key:'품질관리기술사',value:'0770'},
{key:'포장기술사',value:'0780'},
{key:'세라믹기술사',value:'0790'},
{key:'식품기술사',value:'0800'},
{key:'산림기술사',value:'0801'},
{key:'비파괴검사기술사',value:'0802'},
{key:'수산제조기술사',value:'0810'},
{key:'응용지질기술사',value:'0820'},
{key:'지구물리기술사',value:'0830'},
{key:'종자기술사',value:'0840'},
{key:'시설원예기술사',value:'0841'},
{key:'축산기술사',value:'0851'},
{key:'임산가공기술사',value:'0860'},
{key:'기계공정설계기술사',value:'0870'},
{key:'원자력발전기술사',value:'0880'},
{key:'핵연료기술사',value:'0900'},
{key:'방사선관리기술사',value:'0910'},
{key:'대기관리기술사',value:'0920'},
{key:'수질관리기술사',value:'0930'},
{key:'소음진동기술사',value:'0940'},
{key:'농화학기술사',value:'0950'},
{key:'교통기술사',value:'0951'},
{key:'제품디자인기술사',value:'0960'},
{key:'폐기물처리기술사',value:'0970'},
{key:'일반기계기사',value:'1021'},
{key:'용접기사',value:'1022'},
{key:'농업기계기사',value:'1023'},
{key:'사출금형설계기사',value:'1024'},
{key:'프레스금형설계기사',value:'1025'},
{key:'철도차량기사',value:'1030'},
{key:'건설기계기사',value:'1040'},
{key:'건설기계정비기사',value:'1050'},
{key:'메카트로닉스기사',value:'1060'},
{key:'자동차정비기사',value:'1070'},
{key:'기계공정설계기사',value:'1080'},
{key:'치공구설계기사',value:'1090'},
{key:'정밀측정기사',value:'1093'},
{key:'금속기사(재료분야)',value:'1101'},
{key:'금속기사(제련분야)',value:'1102'},
{key:'금속기사(가공분야)',value:'1103'},
{key:'화공기사',value:'1110'},
{key:'공업화학기사',value:'1120'},
{key:'화약류제조기사',value:'1130'},
{key:'화약류관리기사',value:'1140'},
{key:'전기기사',value:'1150'},
{key:'전기공사기사',value:'1160'},
{key:'전자기사',value:'1170'},
{key:'전파통신기사',value:'1190'},
{key:'전파전자기사',value:'1191'},
{key:'정보통신기사',value:'1192'},
{key:'무선설비기사',value:'1200'},
{key:'조선기사',value:'1220'},
{key:'항공기사',value:'1240'},
{key:'토목기사',value:'1250'},
{key:'실내건축기사',value:'1282'},
{key:'방직기사',value:'1291'},
{key:'염색가공기사',value:'1292'},
{key:'방사기사',value:'1293'},
{key:'의류기사',value:'1301'},
{key:'정보처리기사',value:'1320'},
{key:'전자계산기기사',value:'1321'},
{key:'전자계산기조직응용기사',value:'1322'},
{key:'원자력기사',value:'1330'},
{key:'열관리기사',value:'1340'},
{key:'도시계획기사',value:'1350'},
{key:'조경기사',value:'1370'},
{key:'측량및지형공간정보기사',value:'1380'},
{key:'지적기사',value:'1390'},
{key:'인쇄기사',value:'1391'},
{key:'해양환경기사',value:'1401'},
{key:'해양자원개발기사',value:'1402'},
{key:'해양공학기사',value:'1403'},
{key:'해양생산관리기사',value:'1404'},
{key:'산업안전기사',value:'1431'},
{key:'건설안전기사',value:'1440'},
{key:'광산보안기사',value:'1450'},
{key:'지하수기사',value:'1460'},
{key:'자동차검사기사',value:'1470'},
{key:'가스기사',value:'1471'},
{key:'산업위생관리기사',value:'1472'},
{key:'공정관리기사',value:'1490'},
{key:'품질관리기사',value:'1500'},
{key:'포장기사',value:'1511'},
{key:'제품디자인기사',value:'1512'},
{key:'광학기사',value:'1513'},
{key:'세라믹기사',value:'1520'},
{key:'식품기사',value:'1530'},
{key:'수산제조기사',value:'1540'},
{key:'응용지질기사',value:'1550'},
{key:'승강기기사',value:'1555'},
{key:'종자기사',value:'1560'},
{key:'식물보호기사　　　',value:'1562'},
{key:'산림공학기사　　　',value:'1564'},
{key:'산림경영기사',value:'1565'},
{key:'임업종묘기사',value:'1570'},
{key:'축산기사',value:'1581'},
{key:'임산가공기사',value:'1590'},
{key:'기상기사',value:'1600'},
{key:'방사선비파괴검사기사',value:'1611'},
{key:'초음파비파괴검사기사',value:'1612'},
{key:'자기비파괴검사기사',value:'1613'},
{key:'침투비파괴검사기사',value:'1614'},
{key:'와전류비파괴검사기사',value:'1615'},
{key:'누설비파괴검사기사',value:'1617'},
{key:'수산양식기사',value:'1625'},
{key:'어병기사',value:'1626'},
{key:'건축기사',value:'1630'},
{key:'어로기사',value:'1631'},
{key:'건축설비기사',value:'1632'},
{key:'공업계측제어기사',value:'1640'},
{key:'농화학기사',value:'1650'},
{key:'대기환경기사',value:'1661'},
{key:'수질환경기사',value:'1662'},
{key:'소음진동기사',value:'1663'},
{key:'공조냉동기계기사',value:'1730'},
{key:'철도신호기사',value:'1740'},
{key:'전기철도기사',value:'1741'},
{key:'건설재료시험기사',value:'1750'},
{key:'교통기사',value:'1751'},
{key:'철도보선기사',value:'1760'},
{key:'시설원예기사',value:'1780'},
{key:'시각디자인기사',value:'1790'},
{key:'생물공학기사',value:'1800'},
{key:'제판기사',value:'1810'},
{key:'소방설비기사(기계분야)',value:'1900'},
{key:'소방설비기사(전기분야)',value:'1910'},
{key:'폐기물처리기사',value:'1950'},
{key:'기계조립산업기사',value:'2011'},
{key:'판금산업기사',value:'2012'},
{key:'제관산업기사',value:'2013'},
{key:'생산기계산업기사',value:'2025'},
{key:'용접산업기사',value:'2026'},
{key:'농업기계산업기사',value:'2027'},
{key:'사출금형산업기사',value:'2028'},
{key:'프레스금형산업기사',value:'2029'},
{key:'철도차량산업기사',value:'2030'},
{key:'기계설계산업기사',value:'2031'},
{key:'전산응용가공산업기사',value:'2032'},
{key:'윤활관리산업기사',value:'2033'},
{key:'생산자동화산업기사',value:'2034'},
{key:'기계정비산업기사',value:'2035'},
{key:'건설기계산업기사',value:'2040'},
{key:'배관설비산업기사',value:'2041'},
{key:'철도동력차기관정비산업기사',value:'2042'},
{key:'철도동력차전기정비산업기사',value:'2043'},
{key:'객화차정비산업기사',value:'2044'},
{key:'열차조작산업기사',value:'2045'},
{key:'보일러산업기사',value:'2046'},
{key:'영사산업기사',value:'2047'},
{key:'건설기계정비산업기사',value:'2050'},
{key:'메카트로닉스산업기사',value:'2060'},
{key:'자동차정비산업기사',value:'2070'},
{key:'치공구설계산업기사',value:'2090'},
{key:'금속재료산업기사',value:'2101'},
{key:'금속제련산업기사',value:'2102'},
{key:'정밀측정산업기사',value:'2104'},
{key:'주조산업기사',value:'2105'},
{key:'표면처리산업기사',value:'2106'},
{key:'공업화학산업기사',value:'2112'},
{key:'고분자제품제조산업기사',value:'2113'},
{key:'화약류제조산업기사',value:'2120'},
{key:'위험물관리산업기사',value:'2121'},
{key:'화약류관리산업기사',value:'2130'},
{key:'광산보안산업기사',value:'2135'},
{key:'지하수산업기사',value:'2136'},
{key:'전기산업기사',value:'2140'},
{key:'전기공사산업기사',value:'2150'},
{key:'전기기기산업기사',value:'2151'},
{key:'철도신호산업기사',value:'2160'},
{key:'전자산업기사',value:'2170'},
{key:'전파통신산업기사',value:'2190'},
{key:'전파전자산업기사',value:'2191'},
{key:'정보통신산업기사',value:'2192'},
{key:'사무자동화산업기사',value:'2193'},
{key:'방송통신산업기사',value:'2194'},
{key:'무선설비산업기사',value:'2200'},
{key:'통신선로산업기사',value:'2210'},
{key:'조선산업기사',value:'2220'},
{key:'항공산업기사',value:'2230'},
{key:'토목산업기사',value:'2240'},
{key:'건축일반시공산업기사',value:'2251'},
{key:'조적산업기사',value:'2252'},
{key:'건축목공산업기사',value:'2253'},
{key:'목재창호산업기사',value:'2254'},
{key:'방직산업기사',value:'2261'},
{key:'섬유가공산업기사',value:'2262'},
{key:'방사산업기사',value:'2263'},
{key:'섬유디자인산업기사',value:'2264'},
{key:'섬유기계산업기사',value:'2265'},
{key:'편물산업기사',value:'2266'},
{key:'양복산업기사',value:'2267'},
{key:'한복산업기사',value:'2268'},
{key:'굴착산업기사',value:'2279'},
{key:'실내건축산업기사',value:'2282'},
{key:'정보처리산업기사',value:'2290'},
{key:'정보기술산업기사',value:'2291'},
{key:'패션디자인산업기사',value:'2301'},
{key:'조경산업기사',value:'2320'},
{key:'전자계산기산업기사',value:'2321'},
{key:'전자계산기조직응용산업기사',value:'2322'},
{key:'지적기능산업기사',value:'2323'},
{key:'측량및지형공간정보산업기사',value:'2330'},
{key:'지적산업기사',value:'2340'},
{key:'해양조사산업기사',value:'2350'},
{key:'산업안전산업기사',value:'2381'},
{key:'건설안전산업기사',value:'2390'},
{key:'인쇄산업기사',value:'2391'},
{key:'승강기산업기사',value:'2395'},
{key:'자동차검사산업기사',value:'2410'},
{key:'공정관리산업기사',value:'2420'},
{key:'포장산업기사',value:'2431'},
{key:'제품디자인산업기사',value:'2432'},
{key:'시각디자인산업기사',value:'2434'},
{key:'세라믹산업기사',value:'2440'},
{key:'식품산업기사',value:'2450'},
{key:'수산제조산업기사',value:'2460'},
{key:'종자산업기사',value:'2470'},
{key:'가스산업기사',value:'2471'},
{key:'산업위생관리산업기사',value:'2472'},
{key:'시설원예산업기사',value:'2473'},
{key:'임업종묘산업기사',value:'2480'},
{key:'산림산업기사',value:'2481'},
{key:'축산산업기사',value:'2491'},
{key:'임산가공산업기사',value:'2500'},
{key:'수산양식산업기사',value:'2520'},
{key:'잠수산업기사',value:'2521'},
{key:'건축산업기사',value:'2530'},
{key:'건축설비산업기사',value:'2531'},
{key:'공업계측제어산업기사',value:'2540'},
{key:'품질관리산업기사',value:'2550'},
{key:'피아노조율산업기사',value:'2551'},
{key:'식물보호산업기사　　　',value:'2562'},
{key:'산림공학산업기사',value:'2564'},
{key:'산림경영산업기사',value:'2565'},
{key:'대기환경산업기사',value:'2571'},
{key:'수질환경산업기사',value:'2572'},
{key:'소음진동산업기사',value:'2573'},
{key:'공조냉동기계산업기사',value:'2590'},
{key:'건설재료시험산업기사',value:'2600'},
{key:'철도보선산업기사',value:'2610'},
{key:'방사선비파괴검사산업기사',value:'2611'},
{key:'초음파비파괴검사산업기사',value:'2612'},
{key:'자기비파괴검사산업기사',value:'2613'},
{key:'침투비파괴검사산업기사',value:'2614'},
{key:'와전류비파괴검사산업기사',value:'2615'},
{key:'누설비파괴검사산업기사',value:'2616'},
{key:'어로산업기사',value:'2631'},
{key:'계량전기산업기사',value:'2703'},
{key:'계량기계산업기사',value:'2713'},
{key:'계량물리산업기사',value:'2720'},
{key:'교통산업기사',value:'2751'},
{key:'귀금속가공산업기사',value:'2760'},
{key:'목공예산업기사',value:'2770'},
{key:'도자기공예산업기사',value:'2771'},
{key:'자수산업기사',value:'2772'},
{key:'소방설비산업기사(기계분야)',value:'2900'},
{key:'소방설비산업기사(전기분야)',value:'2910'},
{key:'폐기물처리산업기사',value:'2950'},
{key:'열관리산업기사',value:'2960'},
{key:'조리산업기사',value:'2970'},
{key:'직업상담사1급',value:'9510'},
{key:'직업상담사2급',value:'9511'},
{key:'사회조사분석사1급',value:'9520'},
{key:'사회조사분석사2급',value:'9521'},
{key:'기계가공기능장',value:'3021'},
{key:'금형제작기능장',value:'3061'},
{key:'배관기능장',value:'3081'},
{key:'판금제관기능장',value:'3095'},
{key:'용접기능장',value:'3100'},
{key:'자동차정비기능장',value:'3110'},
{key:'건설기계정비기능장',value:'3120'},
{key:'기계정비기능장',value:'3121'},
{key:'항공정비기능장',value:'3122'},
{key:'철도차량정비기능장',value:'3150'},
{key:'보일러기능장',value:'3170'},
{key:'섬유기계기능장',value:'3185'},
{key:'주조기능장',value:'3200'},
{key:'표면처리기능장',value:'3210'},
{key:'금속재료기능장',value:'3221'},
{key:'제선기능장',value:'3230'},
{key:'제강기능장',value:'3240'},
{key:'압연기능장',value:'3270'},
{key:'위험물관리기능장',value:'3360'},
{key:'가스기능장',value:'3375'},
{key:'전기기기기능장',value:'3380'},
{key:'전기공사기능장',value:'3390'},
{key:'전자기기기능장',value:'3410'},
{key:'건축목재시공기능장',value:'3611'},
{key:'건축일반시공기능장',value:'3621'},
{key:'염색기능장',value:'3671'},
{key:'귀금속가공기능장',value:'3770'},
{key:'산림기능장',value:'3861'},
{key:'통신설비기능장',value:'3920'},
{key:'조리기능장',value:'3922'},
{key:'제과기능장',value:'3923'},
{key:'미용장',value:'3924'},
{key:'이용장',value:'3925'},
{key:'선반기능사',value:'6010'},
{key:'수치제어선반기능사',value:'6011'},
{key:'밀링기능사',value:'6030'},
{key:'수치제어밀링기능사',value:'6031'},
{key:'연삭기능사',value:'6040'},
{key:'기계조립기능사',value:'6042'},
{key:'프레스금형기능사',value:'6100'},
{key:'사출금형기능사',value:'6110'},
{key:'정밀측정기능사',value:'6120'},
{key:'기계제도기능사',value:'6150'},
{key:'전산응용기계제도기능사',value:'6151'},
{key:'일반판금기능사',value:'6160'},
{key:'타출판금기능사',value:'6170'},
{key:'제관기능사',value:'6190'},
{key:'철골구조물기능사',value:'6200'},
{key:'건축배관기능사',value:'6213'},
{key:'공업배관기능사',value:'6214'},
{key:'전기용접기능사',value:'6220'},
{key:'특수용접기능사',value:'6222'},
{key:'가스용접기능사',value:'6230'},
{key:'시계수리기능사',value:'6240'},
{key:'자동차정비기능사',value:'6281'},
{key:'자동차차체수리기능사',value:'6285'},
{key:'항공기체정비기능사',value:'6291'},
{key:'항공기관정비기능사',value:'6292'},
{key:'항공장비정비기능사',value:'6293'},
{key:'항공전자정비기능사',value:'6294'},
{key:'건설기계기관정비기능사',value:'6296'},
{key:'건설기계차체정비기능사',value:'6297'},
{key:'농기계정비기능사',value:'6300'},
{key:'농기계운전기능사',value:'6301'},
{key:'공조냉동기계기능사',value:'6320'},
{key:'가스기능사',value:'6335'},
{key:'철도동력차기관정비기능사',value:'6340'},
{key:'철도동력차전기정비기능사',value:'6350'},
{key:'열차조작기능사',value:'6360'},
{key:'객화차정비기능사',value:'6370'},
{key:'축로기능사',value:'6381'},
{key:'인발기능사',value:'6382'},
{key:'보일러취급기능사',value:'6391'},
{key:'열처리기능사',value:'6420'},
{key:'주조기능사',value:'6461'},
{key:'전기도금기능사',value:'6480'},
{key:'특수도금기능사',value:'6481'},
{key:'금속재료시험기능사',value:'6490'},
{key:'제선기능사',value:'6500'},
{key:'제강기능사(전로작업)',value:'6511'},
{key:'제강기능사(전기로작업)',value:'6513'},
{key:'제강기능사(연속주조작업)',value:'6514'},
{key:'원형기능사',value:'6530'},
{key:'열간압연기능사',value:'6541'},
{key:'냉간압연기능사',value:'6542'},
{key:'단조기능사',value:'6550'},
{key:'화학분석기능사',value:'6560'},
{key:'고분자제품제조기능사',value:'6642'},
{key:'플라스틱성형가공기능사',value:'6651'},
{key:'플라스틱성형가공기능사',value:'6652'},
{key:'세라믹기능사',value:'6655'},
{key:'위험물관리기능사(1류)',value:'6691'},
{key:'위험물관리기능사(2류)',value:'6692'},
{key:'위험물관리기능사(3류)',value:'6693'},
{key:'위험물관리기능사(4류)',value:'6694'},
{key:'위험물관리기능사(5류)',value:'6695'},
{key:'위험물관리기능사(6류)',value:'6696'},
{key:'전기공사기능사',value:'6760'},
{key:'철도신호기능사',value:'6770'},
{key:'전기철도기능사',value:'6771'},
{key:'전자기기기능사',value:'6790'},
{key:'석공예기능사',value:'6792'},
{key:'전자계산기기능사',value:'6801'},
{key:'무선설비기능사',value:'6880'},
{key:'전파통신기능사',value:'6890'},
{key:'전파전자기능사',value:'6891'},
{key:'정보기기운용기능사',value:'6892'},
{key:'전산응용조선제도기능사',value:'6910'},
{key:'선체건조기능사',value:'6920'},
{key:'정보처리기능사',value:'6921'},
{key:'선체의장기능사',value:'6950'},
{key:'선박기관정비기능사',value:'6960'},
{key:'토목제도기능사',value:'6970'},
{key:'측량기능사',value:'6980'},
{key:'석공기능사',value:'6990'},
{key:'보선기능사',value:'7010'},
{key:'콘크리트기능사',value:'7020'},
{key:'방수기능사',value:'7030'},
{key:'포장기능사',value:'7040'},
{key:'건축제도기능사',value:'7060'},
{key:'전산응용건축제도기능사',value:'7061'},
{key:'조적기능사',value:'7070'},
{key:'철근기능사',value:'7080'},
{key:'목재창호기능사',value:'7101'},
{key:'금속재창호기능사',value:'7102'},
{key:'미장기능사',value:'7110'},
{key:'비계기능사',value:'7120'},
{key:'기계정비기능사',value:'7121'},
{key:'건축목공기능사',value:'7130'},
{key:'건설재료시험기능사',value:'7132'},
{key:'가구제작기능사',value:'7140'},
{key:'건축도장기능사',value:'7150'},
{key:'유리시공기능사',value:'7151'},
{key:'실내건축기능사',value:'7152'},
{key:'온수온돌기능사',value:'7161'},
{key:'거푸집기능사',value:'7170'},
{key:'도배기능사',value:'7180'},
{key:'타일기능사',value:'7210'},
{key:'방적기능사',value:'7241'},
{key:'직기조정기능사',value:'7282'},
{key:'염색기능사(침염)',value:'7301'},
{key:'염색기능사(날염)',value:'7302'},
{key:'직물가공기능사',value:'7310'},
{key:'섬유제도디자인기능사',value:'7311'},
{key:'시추기능사',value:'7330'},
{key:'광산차량기계운전기능사',value:'7371'},
{key:'광산환경기능사',value:'7372'},
{key:'광산보안기능사(기계분야)',value:'7383'},
{key:'광산보안기능사(전기분야)',value:'7384'},
{key:'광산보안기능사(화약분야)',value:'7385'},
{key:'광산보안기능사(채광분야)',value:'7388'},
{key:'패세공기능사',value:'7431'},
{key:'칠기기능사',value:'7432'},
{key:'금속공예기능사',value:'7440'},
{key:'금속도장기능사',value:'7450'},
{key:'귀금속가공기능사',value:'7460'},
{key:'방사선비파괴검사기능사',value:'7471'},
{key:'초음파비파괴검사기능사',value:'7472'},
{key:'자기비파괴검사기능사',value:'7473'},
{key:'침투비파괴검사기능사',value:'7474'},
{key:'와전류비파괴검사기능사',value:'7475'},
{key:'누설비파괴검사기능사',value:'7477'},
{key:'목공예기능사',value:'7480'},
{key:'축산기능사',value:'7481'},
{key:'식육처리기능사',value:'7482'},
{key:'평판인쇄기능사',value:'7524'},
{key:'전자조판기능사',value:'7532'},
{key:'사진제판기능사　　　　　　',value:'7534'},
{key:'스크린인쇄기능사',value:'7536'},
{key:'농산식품가공기능사',value:'7591'},
{key:'축산식품가공기능사',value:'7592'},
{key:'수산식품가공기능사',value:'7593'},
{key:'어로기능사',value:'7611'},
{key:'잠수기능사',value:'7612'},
{key:'종자기능사',value:'7620'},
{key:'임업종묘기능사',value:'7630'},
{key:'버섯종균기능사',value:'7631'},
{key:'산림기능사',value:'7632'},
{key:'식물보호기능사',value:'7633'},
{key:'목재가공기능사',value:'7641'},
{key:'펄프제지기능사',value:'7642'},
{key:'목질재료기능사',value:'7643'},
{key:'지적기능사',value:'7650'},
{key:'도화기능사',value:'7660'},
{key:'항공사진기능사',value:'7670'},
{key:'광학기능사',value:'7671'},
{key:'지도제작기능사',value:'7680'},
{key:'수산양식기능사',value:'7700'},
{key:'통신선로기능사',value:'7720'},
{key:'한복기능사',value:'7723'},
{key:'방송통신기능사',value:'7730'},
{key:'통신기기기능사',value:'7745'},
{key:'보일러시공기능사',value:'7760'},
{key:'전기기기기능사',value:'7780'},
{key:'공업계측제어기능사',value:'7790'},
{key:'축소사진기능사',value:'7792'},
{key:'사진기능사',value:'7795'},
{key:'컴퓨터그래픽스운용기능사',value:'7796'},
{key:'편물기능사(수편물)',value:'7801'},
{key:'편물기능사(기계편물)',value:'7802'},
{key:'자수기능사(수자수)',value:'7811'},
{key:'자수기능사(기계자수)',value:'7812'},
{key:'계량기계기능사',value:'7831'},
{key:'계량물리기능사',value:'7832'},
{key:'계량전기기능사',value:'7833'},
{key:'자동차검사기능사',value:'7860'},
{key:'기중기운전기능사',value:'7861'},
{key:'굴삭기운전기능사',value:'7862'},
{key:'불도우저운전기능사',value:'7863'},
{key:'천정기중기운전기능사',value:'7864'},
{key:'로우더운전기능사',value:'7866'},
{key:'아스팔트믹싱플랜트운전기능사',value:'7867'},
{key:'준설선운전기능사',value:'7868'},
{key:'로울러운전기능사',value:'7871'},
{key:'모우터그레이더운전기능사',value:'7872'},
{key:'아스팔트피니셔운전기능사',value:'7873'},
{key:'지게차운전기능사',value:'7875'},
{key:'공기압축기운전기능사',value:'7876'},
{key:'가구도장기능사',value:'7887'},
{key:'광고도장기능사',value:'7888'},
{key:'보석가공기능사',value:'7889'},
{key:'도자기공예기능사',value:'7890'},
{key:'인장공예기능사',value:'7891'},
{key:'제과기능사',value:'7892'},
{key:'제빵기능사',value:'7893'},
{key:'양복기능사',value:'7894'},
{key:'양장기능사',value:'7895'},
{key:'채소재배기능사',value:'7897'},
{key:'과수재배기능사',value:'7898'},
{key:'화훼재배기능사',value:'7899'},
{key:'조경기능사',value:'7900'},
{key:'시설원예기능사',value:'7901'},
{key:'조화공예기능사',value:'7903'},
{key:'신발류제조기능사',value:'7904'},
{key:'미용사',value:'7907'},
{key:'이용사',value:'7908'},
{key:'한식조리기능사',value:'7910'},
{key:'양식조리기능사',value:'7911'},
{key:'일식조리기능사',value:'7912'},
{key:'중식조리기능사',value:'7913'},
{key:'복어조리기능사',value:'7914'},
{key:'조주기능사',value:'7916'},
{key:'피아노조율기능사',value:'7917'},
{key:'환경기능사',value:'7918'},
{key:'영사기능사',value:'7920'},
{key:'양화장치운전기능사',value:'7930'},
{key:'승강기기능사',value:'7940'},
{key:'표구기능사',value:'7950'},
{key:'세탁기능사',value:'7960'},
{key:'화약취급기능사',value:'7970'},
{key:'보석감정사',value:'7980'}],
          introstr:'',
          telstr:'',
          addstr:'',
          gitstr:'',
          devlogstr:'',
          devlogurl:'',
          certistr:'',
        }
    },
    created(){ 
      $('#element').on('scroll touchmove mousewheel', function(e) {
        e.preventDefault();
        e.stopPropagation();
      });
      this.getPortfolioInfo(this.$route.params.seq);
      this.getPortfolioStack(this.$route.params.seq)
      this.getPortfolioMoreInfo(this.$route.params.seq);
      this.getSocials(this.$route.params.seq);
      this.getExperiences(this.$route.params.seq);
      this.getEducations(this.$route.params.seq);
      this.getProgrammingSkills(this.$route.params.seq);
      this.getLanguageSkills(this.$route.params.seq);
      this.getCertifications(this.$route.params.seq);
      this.getProjectsInfo(this.$route.params.seq);
    },
    mounted(){
      $(".main-header").css('display','none')
      $(".main-footer"). css('display','none')
      setTimeout(this.stopLoading, 1);
    },
    updated(){
      $(".skills-prog li").find(".skills-bar").each(function(i) {
        var percent;
        percent = $(this)
          .data("data-percent")*20;

        $(this)
          .find(".bar")
          .delay(i * 150)
          .animate({width:$(this).attr("data-percent")*20 + "%"}, 
            2000,
            "linear",
            function() {
              return $(this).css({
                "transition-duration": "10000s"
              });
            }
          );
          // 퍼센트 숫자로 띄워보기
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
          .data("percent")*20;
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
      copyurl(){
      var url = window.location.href
      var dummy = document.createElement("textarea");
      document.body.appendChild(dummy);
      dummy.value = url;
      dummy.select();
      document.execCommand("copy");
      document.body.removeChild(dummy);
      this.$message.success('주소가 복사되었습니다.')
    },
      stopLoading(){
        this.loading = false;
      },
      getPortfolioInfo(seq){
        http.get('portfolio/'+seq)
        .then(({data}) => {
            this.portfolioInfo=data;
            // 입력받은 데이터 가공
            this.telstr="tel:"+data.tel;
            this.gitstr="https://"+data.github_url;
            this.getPortfolioOwnerInfo(data.seq_blog);
         })
      },
      getPortfolioStack(seq){
        http
        .get('/projectstack/'+seq)
        .then(({ data }) => {
          this.portfolioStack=data;
        })
      },
      getPortfolioOwnerInfo(seq){
        http.get('user/'+seq)
        .then(({data}) => {
            this.portfolioOwnerInfo=data;
            // 입력받은 데이터 가공
            this.devlogstr="/blog/"+data.id;
            this.devlogurl="http://i3a402.p.ssafy.io/blog/"+data.id;
         })
      },
      getPortfolioMoreInfo(seq){
        http.get('portfolio/info/'+seq)
        .then(({data}) => {
            this.portfolioMoreInfo=data;
            this.addstr="http://maps.google.com/maps?q="+data.address;
         })
      },
      getSocials(seq){
         http.get('portfolio/info/social/'+seq)
        .then(({data}) => {
            this.socials=data;
         })
      },
      getExperiences(seq){
         http.get('portfolio/info/experience/'+seq)
        .then(({data}) => {
            this.experiences=data;
         })
      },
      getEducations(seq){
         http.get('portfolio/info/education/'+seq)
        .then(({data}) => {
            this.educations=data;
         })
      },
      getProgrammingSkills(seq){
        http.get('portfolio/skill/'+seq)
        .then(({data}) => {
            this.programmingSkills=data;
         })
      },
      getLanguageSkills(seq){
        http.get('portfolio/language/'+seq)
        .then(({data}) => {
            this.languageSkills=data;
         })
      },
      getCertifications(seq){
        http.get('portfolio/certification/'+seq)
        .then(({data}) => {
            this.certifications=data;
            if(data.length>9)
              this.certificationicon = 'filter_9_plus';
            else
              this.certificationicon = 'filter_'+data.length;            
         })
      },
      getProjectsInfo(seq){
        http.get('portfoliopjt/'+seq)
        .then(({data}) => {
            this.projects=data
         })
      },
      goToWebsite(certi){
        for(var i=0; i<this.testCertificationLink.length; i++)
        {
          if(this.testCertificationLink[i].key==certi){
            child = window.open("http://www.q-net.or.kr/crf005.do?id=crf00505&gSite=Q&gId=&jmCd="+this.testCertificationLink[i].value+"&examInstiCd=1","child");
          }
        }
      },
      goDetailProject(seq){
            this.$router.push(`/blog/project/${seq}`)
      },
      goBlog(id){
            this.$router.push(`/blog/${id}`)
      },
      PDF(){
      window.scrollTo(0, 0);
      window.html2canvas = html2canvas
      const pageWidth = 210 //캔버스 너비 mm
      const pageHeight = 295 //캔버스 높이 mm
      let ele = document.querySelector(".resume")
			let width = ele.offsetWidth // 셀렉트한 요소의 px 너비
      let height = ele.offsetHeight // 셀렉트한 요소의 px 높이
      let imgHeight = pageWidth * height/width 
      
      html2canvas($('.resume')[0]).then(function(canvas) {
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

* {
  outline: none;
}

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
  // text-transform: lowercase;
  font-size: 1.25em;
}

.resume {
  width: 1080px;
  // width: 960px;
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
  width: 40%;
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
  // text-transform: lowercase;
  color: $darkest-blue;
}
.resume .base .profile .info .job {
  margin-top: 10px;
  margin-bottom: 0;
  font-size: 1.5em;
  // text-transform: lowercase;
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
.resume .base .follow .box a:hover img {
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
.resume .base .follow .box img {
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
.resume .base .follow .box .thumbnail-stack-img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  background-color:rgba(255, 255, 255, 0.39);
  opacity: 1;
  margin:0px 10px 10px 10px;
  transition-duration: 0.3s;
}
.resume .base .follow .box a.thumbnail-stack-img img{
  background-color: transparent!important;
}
.resume .base .follow .box a:hover .thumbnail-stack-img {
  opacity: 0.6;
  border-radius: 5px;
  transform: rotate(45deg) scale(0.8);
}
.resume .base .follow .box a:hover .thumbnail-stack-img img{
  transform: rotate(0);
}
.resume .base .follow .box .thumbnail-stack-img .stack-img {
  display: inline-block;
  font-size: 30px;
  background: transparent;
  max-width: 40px;
  max-height: 40px;
  border-radius:0px;
  // border-radius: 100%;
  line-height: 60px;
  color: $darker-blue;
  margin: 10px 10px 10px 10px;
  transition-duration: 0.3s;
  vertical-align: middle;
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
.resume .func .edu,
.resume .func .project  {
  float: left;
}
.resume .func .work small,
.resume .func .edu small,
.resume .func .project small {
  display: block;
  opacity: 0.7;
}
.resume .func .work ul li,
.resume .func .edu ul li,
.resume .func .project ul li  {
  position: relative;
  margin-left: 15px;
  padding-left: 25px;
  padding-bottom: 15px;
}
.resume .func .work ul li span,
.resume .func .edu ul li span{
  font-weight: bold;
}
.resume .func .work ul li small span,
.resume .func .edu ul li small span{
  font-weight: 300;
}

.resume .func .project ul li span{
  font-weight: bold;
  font-size: 20px;
}
.resume .func .project ul li small span{
  font-weight: 300;
  font-size: small;
}

.resume .func .work ul li:hover::before,
.resume .func .edu ul li:hover::before,
.resume .func .project ul li:hover::before {
  animation: circle 1.2s infinite;
}
.resume .func .work ul li:hover span,
.resume .func .edu ul li:hover span,
.resume .func .project ul li:hover span {
  color: $yellow;
  font-weight: bold;
}
.resume .func .project ul li:hover .tag{
  color: $white;
  font-weight: 400;
}
.resume .func .work ul li:hover small span ,
.resume .func .edu ul li:hover small span,
.resume .func .project ul li:hover small span {
  color: $white;
  font-weight: 300;
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
.resume .func .edu ul li:first-of-type::before,
.resume .func .project ul li:first-of-type::before {
  width: 10px;
  height: 10px;
  left: 1px;
}
.resume .func .work ul li:last-of-type,
.resume .func .edu ul li:last-of-type,
.resume .func .project ul li:last-of-type {
  padding-bottom: 3px;
}
.resume .func .work ul li:last-of-type::after,
.resume .func .edu ul li:last-of-type::after,
.resume .func .project ul li:last-of-type::after {
  border-radius: 1.5px;
}
.resume .func .work ul li::before,
.resume .func .work ul li::after,
.resume .func .edu ul li::before,
.resume .func .edu ul li::after,
.resume .func .project ul li::before,
.resume .func .project ul li::after {
  content: "";
  display: block;
  position: absolute;
}
.resume .func .work ul li::before,
.resume .func .edu ul li::before,
.resume .func .project ul li::before {
  width: 7px;
  height: 7px;
  border: 3px solid $white;
  background: $orange;
  border-radius: 50%;
  left: 3px;
  top:5px;
  z-index: 1;
}
.resume .func .project ul li::before {
  top:10px;
}
.resume .func .work ul li::after,
.resume .func .edu ul li::after,
.resume .func .project ul li::after {
  width: 3px;
  height: 100%;
  background: $white;
  left: 5px;
  top: 0;
}
.resume .func .work ul li span,
.resume .func .edu ul li span,
.resume .func .project ul li span {
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
.resume .func .project {
  width: 100%;
  background: $darker-blue;
  padding: 15px;
  margin-top: 15px;
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
.resume .func .certifications {
  background: $darker-blue;
  margin: 15px 0 0;
  padding: 15px;
}
.resume .func .certifications-items {
  box-sizing: border-box;
  padding: 0 0 15px;
  width: 100%;
  text-align: center;
  display: flex;
  justify-content: space-between;
}
.resume .func .certifications-items div {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
.resume .func .certifications-items div:hover i {
  transform: scale(1.2);
}
.resume .func .certifications-items div:hover span {
  color: $yellow;
  transition-duration: 0.3s;
}
.resume .func .certifications-items div i {
  font-size: 45px;
  width: 60px;
  height: 60px;
  line-height: 60px;
  color: $orange;
  transition-duration: 0.3s;
}
.resume .func .certifications-items div span {
  display: block;
}
.tag{
  margin-left:-8px;
}
.tag:hover{
  background-color: $yellow;
  color:rgb(30, 59, 82)!important;
}


.a:hover .tag{
  color:white;
}

.project ul li i:hover{
  transform: rotate(25deg) scale(1.1);
  transition-duration: 0.3s;
}

</style>