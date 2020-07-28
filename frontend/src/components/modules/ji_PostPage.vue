<template>
    <transition name="el-zoom-in-top">
        <section class="content">
        <!-- 포스트출력 -->
            <div class="row">
                    <div class="col-md-4" v-for="post in postList" :key="post">
                    <div class="well-media">
                    <a href="#">
                        <div class="vendor">
                            <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt="">
                            <!-- <a class="fancybox" rel="group" href="#"> <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt=""> </a> -->
                        </div>
                        <div class="video-text">
                            <h2>{{post}}</h2>
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
            seq_user: '0',
            postList: ['java', 'spring', 'python', 'aws', 'ml', 'database', 'blockchain', 'javascript', 'tensorflow']
        }
    },
    created(){	 
	  this.getpostList();
    },
    methods:{
        getpostList(){
            // this.$http.get('devlog/api/post/'+this.seq_blog+'/' + this.seq_user)
            // .then(({data}) => {
			// 	if(data.length != 0) {
			// 	    this.postList = data;
			// 	}else{
			// 	    this.postList = null;
			// 	}
			// })
		},
    }
  }
</script>
