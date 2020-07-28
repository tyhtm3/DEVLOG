<template>
    <transition name="el-zoom-in-top">
        <section class="content">
        <!-- 포스트출력 -->
            <div class="row">
                    <div class="col-md-4" v-for="portfolio in portfolioList" :key="portfolio">
                    <div class="well-media">
                    <a href="#">
                        <div class="vendor">
                            <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt="">
                            <!-- <a class="fancybox" rel="group" href="#"> <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt=""> </a> -->
                        </div>
                        <div class="video-text">
                            <h2>{{portfolio}}</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        </div>
                        <div class="tag-nest"> <i>angel</i><i>dark</i><i>mistic</i> </div>
                        <div class="video-category-bg">
                            <h3>FRONT-END</h3>
                            <a class="link-media pull-right" href="#"> <span class="fontawesome-picture"></span> </a>
                            <div class="triangle-white"></div>
                            <div class="triangle-photo-right"></div>
                        </div>
                    </a>
                    </div>
                </div>
            </div>
        </section>
    </transition>
</template>
<script>
  export default {
    name: 'ji_ProjectPage',
    data(){
        return{
            seq_blog: '2',
            seq_user: '2',
            portfolioList: ['포', '트', '폴', '리', '오', '페', '이', '지', '포', '트', '폴', '리', '오', '페', '이', '지']
        }
    },
    created(){	 
      this.getportfolioList();
    },
    methods:{
        // getportfolioList(){
        //     this.$http.get('devlog/api/blog/post/'+this.seq_blog+'/' + this.seq_user)
        //     .then(({data}) => {
		// 		if(data.length != 0) {
		// 		    this.portfolioList = data;
		// 		}else{
		// 		    //this.portfolioList = null;
		// 		}
		// 	})
        // },
        getportfolioList(){
        //     alert("들어옴");
        //     this.$http.post('post/blog', { seq_user:1 , seq_blog:1, offset:0, limit:10})
        //     .then(({ data }) => {
        //     alert(data)
        // })
        }
    }
  }
</script>
