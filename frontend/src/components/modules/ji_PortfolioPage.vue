<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 포스트출력 -->
            <div class="row">
                    <div class="col-md-4" v-for="(portfolio,index) in portfolioList" :key="index">
                    <div class="well-media">
                    <a href="#">
                        <div class="vendor">
                            <img class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                            <!-- <a class="fancybox" rel="group" href="#"> <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt=""> </a> -->
                        </div>
                        <div class="video-text">
                            <!-- {{portfolio}} -->
                            <h2 style="font-weight: bold; margin-bottom:10px;">{{portfolio.title}}</h2>
                            <p style="color:black;">{{portfolio.content}} 세 줄만 보이게 만들어야되는데 아직 작업 안함!!!! 해야됨!! 세 줄만 보이게 만들어야되는데 아직 작업 안함!!!! 해야됨!!세 줄만 보이게 만들어야되는데 아직 작업 안함!!!! 해야됨!!세 줄만 보이게 만들어야되는데 아직 작업 안함!!!! 해야됨!!</p>
                        </div>
                        <div class="tag-nest" style="block:inline"> 
                            <span class="tag">#SpringBoot</span>
                            <span class="tag">#Vue.js</span>
                            <span class="tag">#css</span>
                            <span class="tag-copy" style="float:right"> <i class="ti-heart"></i> {{portfolio.like_count}} </span>
                            <!-- <span class="tag-copy" style="float:right"> <i class="ti-comment-alt"></i> {{comment[index]}} </span>  -->
                        </div>
                        <!-- <div class="video-category-bg">
                            <h3>FRONT-END</h3>
                            <a class="link-media pull-right" href="#"> <span class="fontawesome-picture"></span> </a>
                            <div class="triangle-white"></div>
                            <div class="triangle-photo-right"></div>
                        </div> -->
                    </a>
                    </div>
                </div>
            </div>
        </section>
    </transition>
</template>
<script>
  import http from '../../util/http-common'
  export default {
    name: 'ji_PortfolioPage',
    data(){
        return{
            // 방문한 블로그 일단은 무조건 현재 블로그번호로 지정, 이후에 방문 블로그 번호로 설정하는거 해야함
            seq_blog: this.$store.state.userInfo.seq,
            seq_user: this.$store.state.userInfo.seq,
            offset:0,
            limit:10,
            portfolioList: [],
            comment: [],
            counter: 0
        }
    },
    created(){	 
      this.getportfolioList();
    },
    methods:{
        getportfolioList(){
            // alert(data[0].title)
            http.get('portfolio/blog/'+this.seq_user+'/'+this.seq_blog+'/'+this.offset+'/'+this.limit)
            .then(({ data }) => {
                this.portfolioList = data;
                for(var i=0; i<this.portfolioList.length; i++){
                    // console.log(this.portfolioList[i].seq);
                    http.get('postcomment/'+this.portfolioList[i].seq)
                     .then(({data}) => {
                        // console.log(data.length);
                        this.comment.push(data.length);
                    });
                }
            })
        }
    }
  }
</script>
<style scoped>
.tag-copy{
    border-radius: 10px;
    font-size: 14px;
    margin-right: 4px;
    line-height: 35px;
    cursor: pointer;
}
</style>