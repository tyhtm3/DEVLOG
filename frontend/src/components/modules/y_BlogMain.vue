<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <!-- start Main content -->
      <section class="content">
        <div class="box">
          <div class="box-body" style="min-height:400px;">
            <div class="movie-card">
              <!-- start profile -->
              <div class="container-movie">
                <div class="hero">
                  <div class="details-profile">
                    <div class="title1">
                      {{blogInfo.blog_name}}
                    </div>
                    <div class="title2">
                      by {{blogOwnerInfo.nickname}}
                      <!-- 일단은 블로그 주인 프로필 이미지 주소로 받아오게 함. 바꿔야돼-->
                      <a href="#"><img :src="blogOwnerInfo.profile_img_url" alt="cover" class="cover-profile" /></a>
                      <!-- <span>Web Designer</span> -->
                    </div>
                  </div>
                </div>
                <!-- end hero -->
                <div class="description-profile">
                  <div class="column2">
                    <div class="row">
                      <div class="col-xs-12 col-sm-4 emphasis">
                        <h2><strong>{{blogOwnerNumOfProject}}</strong></h2>
                        <p> <small>Projects</small> </p>
                      </div>
                      <div class="col-xs-12 col-sm-4 emphasis">
                        <h2><strong>{{blogOwnerNumOfPost}}</strong></h2>
                        <p> <small>Post</small> </p>
                      </div>
                      <div class="col-sm-4 emphasis">
                        <h2><strong>{{blogOwnerNumOfNeighbor}}</strong></h2>
                        <p> <small>Follower</small> </p>
                      </div>
                    </div>
                  </div>
                  <!-- 태그 2개 이상이면 500에러 뜸!-->
                  <!-- {{blogOwnerMainTags.tag}} -->
                  <div class="column1">
                    <!-- <span v-for="(blogOwnerMainTag, index) in blogOwnerMainTags" v-bind:key="index">
                    <span class="tagspecial" style="font-size:20px; ">#{{blogOwnerMainTag}}</span>
                    </span> -->
                    <span v-for="(blogOwnerMainTag, index) in blogOwnerMainTags" v-bind:key="index">
                    <span class="tag">#{{blogOwnerMainTag}}</span>
                    </span>
                  </div>
                  <!-- <div class="column1">
                    <span class="tag">#SpringBoot</span>
                    <span class="tag">#Vue.js</span>
                    <span class="tag">#css</span>
                    <span class="tag">#python</span>
                    <span class="tag">#photoshop</span>
                    <span class="tag">#Node.js</span>
                  </div> -->
                </div>
              </div>
              <!-- end profile -->
              <blog-main-list></blog-main-list>
            </div>
            <!-- end movie card -->
          </div>
        </div>
      </section>
      <!-- end Main content -->
    </div>
  </transition>
</template>
<script>
  import BlogMainList from '../BlogMainList'
  import http from '../../util/http-common'
  export default {
    name: 'y_blog-main',
    components: {
     'blog-main-list': BlogMainList
    },
    data(){
        return{
            // 방문한 블로그 일단은 무조건 현재 블로그번호로 지정, 이후에 방문 블로그 번호로 설정하는거 해야함
            seq_blog: this.$store.state.userInfo.seq,
            seq_user: this.$store.state.userInfo.seq,
            blogInfo:[],
            blogOwnerInfo:[],
            blogOwnerNumOfProject:'',
            blogOwnerNumOfPost:'',
            blogOwnerNumOfNeighbor:'',
            blogOwnerMainTags:[],
            tags: ['java', 'spring', 'python', 'aws', 'ml', 'database', 'blockchain', 'javascript', 'tensorflow'],
        }
    },
    created(){	 
      this.getBlogInfo();
    },
    methods:{
        getBlogInfo(){
            http.get('blog/'+this.seq_blog)
            .then(({ data }) => {
              this.blogInfo=data;
            });
            http.get('user/'+this.seq_blog)
            .then(({ data }) => {
              this.blogOwnerInfo=data;
            });
            http.post('project/blog/count', {seq_user:this.seq_user , seq_blog:this.seq_blog})
            .then(({ data }) => {
                this.blogOwnerNumOfProject = data;
            });
            http.post('post/blog/count', {seq_user:this.seq_user , seq_blog:this.seq_blog})
            .then(({ data }) => {
                this.blogOwnerNumOfPost = data;
            });
            http.get('userneighbor/'+this.seq_blog)
            .then(({ data }) => {
                this.blogOwnerNumOfNeighbor = data.length;
            });
            http.get('blogtag/'+this.seq_blog)
            .then(({ data }) => {
                this.blogOwnerMainTags = data;
                // alert( this.blogOwnerMainTags);
            });
        }
    }
  }
</script>
<style scoped>

.tagspecial {
    font-family: 'Montserrat';
    font-weight: 300;
    background: white;
    border-radius: 10px;
    padding: 3px 8px;
    font-size: 20px;
    margin-right: 4px;
    line-height: 35px;
    cursor: pointer;
}

.tagspecial:hover {
    background: #ddd;
}
</style>