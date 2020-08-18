<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <!-- 틀 잡는중 -->
      <div class="content"> 
        <div class="content-left"> 
          .
        </div>
         <div class="content-right"> 
           .
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
          basicinfo:'',
          portfolioUser:'',
          projects:[],
          tag: [],
          stack: [],
          seq_user: this.$store.state.userInfo.seq,
        }
    },
    created(){
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
         })
      }
    }
  }
</script>
<style scoped>
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