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
          <h2 class="select-template-title" v-if="template.title">{{ template.title }}
          <!-- <i @click="copyurl" class="material-icons" style="position:relative; top:2px; cursor:pointer; transform: rotate(45deg); z-index:999;">link</i> -->
          </h2>
          <small class="select-template-summary" v-if="template.summary" v-html="template.summary">
          </small>
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
          summary: '<u style="color:#b68c2c;">지원하는 직무와 연관된 <span style="color:#b68c2c; font-weight:500;">3개의 핵심 프로젝트</span></u>를 보여주는 데 최적화된 템플릿',
          url: "/blog/portfolio1/"+this.clickedSeq
        },
        {
          image: '../../static/img/template02.JPG',
          title: "통계 & 타임라인",
          summary: '프로젝트를 중심으로 데이터를 <span style="color:#b68c2c; font-weight:500;">통계화</span>하여 <span style="color:#b68c2c; font-weight:500;"><u style="color:#b68c2c;">시각화된 정보</span>와 <span style="color:#b68c2c; font-weight:500;">타임라인</span>을 보여주는 템플릿</u>',
          url: "/blog/portfolio2/"+this.clickedSeq
        },
        {
          image: '../../static/img/template03.png',
          title: "인포그래픽",
          summary: '지원자의 <span style="color:#b68c2c; font-weight:500;">프로젝트</span>, <span style="color:#b68c2c; font-weight:500;">자격증</span>, <span style="color:#b68c2c; font-weight:500;">일상</span> 등에 대해 더 알아보고 싶은 <u style="color:#b68c2c;">인사 담당자의 번거로움을 덜어주는 <span style="color:#b68c2c; font-weight:500;">링크형 인포그래픽 템플릿</span></u>',
          url: "/blog/portfolio3/"+this.clickedSeq
        },
        {
          image: '../../static/img/template05.png',
          title: "Simple",
          summary:'<span style="color:#b68c2c; font-weight:500;">심플</span>한 디자인으로 <u style="color:#b68c2c;"><span style="color:#b68c2c; font-weight:500;">프로젝트를 강조하고 싶은 개발자</span></u>를 위한 템플릿',
          url: "/blog/portfolio5/"+this.clickedSeq
        }
      ]
    }
  },
  methods:{
    templateDetail(url){
      window.open(url, '_blank');
    },
    copyurl(){
      var url = window.location.href
      var dummy = document.createElement("textarea");
      document.body.appendChild(dummy);
      dummy.value = url;
      dummy.select();
      document.execCommand("copy");
      document.body.removeChild(dummy);
      this.$message.success('주소가 복사되었습니다.')
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
