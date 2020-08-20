<template>
<div class="html">
    <div class="media-gallery">
      <figure class="media-post" v-for="(template, index) in templates" :key="index" @click="templateDetail(template.url)">
        
        <div class="thumbnail-wrapper"> 
          <div class="thumbnail"> 
            <div class="centered"> 
              <img :src="template.image" :alt="template.title" /> 
            </div> 
          </div> 
        </div>

        <figcaption>
          <div class="abuot-template">
          <h2 class="select-template-title" v-if="template.title">{{ template.title }}</h2>
          <small class="select-template-summary" v-if="template.summary" v-html="template.summary"></small>
          </div>
        </figcaption>
      </figure>
    </div>
</div>
</template>

<script>
export default {
  props: ['clickedSeq'],
  data() {
    return {
      templates: [
        {
          image: '../../static/img/template01.jpg',
          title: "4 card",
          summary: "지원하는 직무와 연관된 소수의 프로젝트를 보여주는 데 적합한 템플릿",
          url: "/blog/portfolio1/"+this.clickedSeq
        },
        {
          image: 'https://source.unsplash.com/collection/190727/400x400?2',
          title: "템플릿이름",
          summary: "설명",
          url: "/blog/portfolio2/"+this.clickedSeq
        },
        {
          image: '../../static/img/template03.png',
          title: "인포그래픽 템플릿",
          summary: '당신의 <span style="color:#b68c2c; font-weight:500;">이력</span>과 <span style="color:#b68c2c; font-weight:500;">포트폴리오</span>를 한눈에! <br>더이상의 구글링은 필요없다! <br>지원자의 <span style="color:#b68c2c; font-weight:500;">프로젝트</span>, <span style="color:#b68c2c; font-weight:500;">자격증</span>, <span style="color:#b68c2c; font-weight:500;">일상</span> 등에 대해 더 알아보고 싶은 <u style="color:#b68c2c;">인사 담당자의 번거로움을 덜어주는 <span style="color:#b68c2c; font-weight:500;">링크형 인포그래픽 템플릿</span></u>.',
          url: "/blog/portfolio3/"+this.clickedSeq
        },
        {
          image: '../../static/img/template05.png',
          title: "심플 템플릿",
          summary: "심플한 디자인으로 프로젝트를 강조하고 싶은 개발자에게 이 템플릿을 추천합니다.",
          url: "/blog/portfolio5/"+this.clickedSeq
        }
      ]
    }
  },
  methods:{
    templateDetail(url){
      this.$router.push(url)
    }
  }
}
</script>
<style lang="scss" scoped>
@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
.media-gallery {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-gap: 2rem;
  margin: 10px;
  max-width: 180vmin;
}

.media-post {
  display: block;
  padding: 1rem;
  border-radius: 0.3rem;
  box-shadow: 0 5px 5px rgba(0,0,0,.2);
  margin: 0;
  background: #FFF;
  cursor: pointer;
  
  img { border-radius: .5rem; display: block; }
  figcaption { margin: 1rem 1rem 0; }
  
}
.thumbnail-wrappper { 
  width: 35%; 
  } 
.thumbnail { 
  position: relative; 
  padding-top: 100%; /* 1:1 ratio */ 
  overflow: hidden; 
  border: transparent;
  } 
.thumbnail .centered { 
  position: absolute; 
  top: 0; 
  left: 0; 
  right: 0; 
  bottom: 0; 
  -webkit-transform: translate(50%,50%); 
  -ms-transform: translate(50%,50%); 
  transform: translate(50%,50%); 
  } 
.thumbnail .centered img { 
  position: absolute; 
  top: 0; 
  left: 0; 
  border-radius: 0px;
  max-width: 100%; 
  height: auto; 
  -webkit-transform: translate(-50%,-50%); 
  -ms-transform: translate(-50%,-50%); 
  transform: translate(-50%,-50%); 
  }
// 이렇게하고 hover하면 좌우로 이미지 왔다갔다하게되는거
// .thumbnail-wrappper { 
//   width: 300px; height: 300px; overflow: hidden; 
// }
// .portfolio-thumbnail{
//   max-height:100%; width:auto;
// }
</style>
<style>
  .media-post h2 { 
    font-size: 1.7rem; 
    line-height: 1; 
    font-family: "Noto Sans KR";
  }

  .media-post small { 
    font-size: 1.3rem;
    font-family: "Noto Sans KR";
  }
  
  .el-dialog__body div:hover > .media-post{
    opacity: 0.6;
  }
  .el-dialog__body div:hover .media-post:hover{
    opacity: 1;
  }
  .about-template{
    margin:10px;
  }
</style>
