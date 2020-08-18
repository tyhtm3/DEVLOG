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
          <h2 v-if="template.title">{{ template.title }}</h2>
          <em v-if="template.summary">{{ template.summary }}</em>
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
          summary: "당신의 이력과 포트폴리오를 한눈에! 더이상의 구글링은 필요없다! 지원자의 프로젝트, 자격증, 일상 등에 대해 더 알아보고 싶은 인사담당자의 번거로움을 덜어주는 링크형 인포그래픽 템플릿.",
          url: "/blog/portfolio3/"+this.clickedSeq
        },
        {
          image: 'https://source.unsplash.com/collection/190727/400x400?4',
          title: "템플릿이름",
          summary: "설명",
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
.media-gallery {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-gap: 2rem;
  margin: 10px;
  max-width: 160vmin;
}

.media-post {
  display: block;
  padding: 1rem;
  border-radius: 1rem;
  box-shadow: 0 5px 5px rgba(0,0,0,.2);
  margin: 0;
  background: #FFF;
  cursor: pointer;
  
  img { border-radius: .5rem; display: block; }
  figcaption { margin: 1rem 1rem 0; }
  h2 { font-size: 1.6rem; line-height: 1; }
  em { font-size: 1rem; }
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
