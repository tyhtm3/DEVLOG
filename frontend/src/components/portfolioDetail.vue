<template>
<div class="html">
    <div class="media-gallery">
      <figure class="media-post" v-for="(template, index) in templates" :key="index" @click="templateDetail(template.url)">
        <img :src="template.image" :alt="template.title" width="250" height="150" />
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
          image: 'https://source.unsplash.com/collection/190727/400x400?3',
          title: "템플릿이름",
          summary: "설명",
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
  max-width: 80vmin;
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
</style>
