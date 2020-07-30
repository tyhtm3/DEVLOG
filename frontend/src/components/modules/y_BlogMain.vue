<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <!-- start Main content -->
      <section class="content">
        <!-- start box -->
        <div class="box">
          <!-- start box-body -->
          <div class="box-body" style="min-height:400px;">
            <!-- start movie-card -->
            <div class="movie-card">
              <!-- start container-movie -->
              <div class="container-movie">
              <!-- <div class="hero">-->
                <!-- start profile -->
                <div class="details-profile">
                <!-- <div class="details-profile"> -->
                  <div class="title1" style="display:inline">
                    <input type="text" id="title" value="명묭이의 코딩일기" disabled>
                  </div>
                  <!-- 
                  <div class="title1">
                        {{blogInfo.blog_name}}
                  </div> 
                  -->
                  <i class="ti-pencil-alt" v-if="this.$store.state.settingButtonVisible" @click="alterTitle" style="cursor:pointer;"></i>
                  <div class="title2">
                    by {{blogOwnerInfo.nickname}}
                      <!-- 일단은 블로그 주인 프로필 이미지 주소로 받아오게 함. 바꿔야돼-->
                      <a href="#"><img :src="blogOwnerInfo.profile_img_url" alt="cover" class="cover-profile" /></a>
                    <!-- <span>Web Designer</span> -->
                  </div>
                </div>
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
                      <div class="col-sm-4 emphasis" style="cursor:pointer;">
                        <h2><strong @click="follower">{{blogOwnerNumOfNeighbor}}</strong></h2>
                        <p> <small @click="follower">Follower</small> </p>
                      </div>
                    </div>
                  </div>
                  <!-- end column2 -->
                  <!-- 태그 2개 이상이면 500에러 뜸!-->
                  <!-- {{blogOwnerMainTags.tag}} -->
                  <!-- <div class="column1">
                    <span v-for="(blogOwnerMainTag, index) in blogOwnerMainTags" v-bind:key="index">
                    <span class="tag">#{{blogOwnerMainTag}}</span>
                    </span>
                  </div> -->
                  <!-- <div class="column1">
                    <span v-for="(blogOwnerMainTag, index) in blogOwnerMainTags" v-bind:key="index">
                    <span class="tagspecial" style="font-size:20px; ">#{{blogOwnerMainTag}}</span>
                    </span>
                  </div> -->
                  <div class="column4" v-if="this.$store.state.isLogin">
                  <router-link to="writePost">
                  <span>글 작성<i class="ti-pencil" style="display:inline"></i></span>&nbsp;
                  </router-link>
                  <span id="setting" @click="set">설정<i class="ti-settings" style="display:inline"></i></span>
                  </div>
                </div>
              </div>
                <!-- end profile -->
              <!-- end container-movie -->
            </div>
            <!-- end movie card -->
          </div>
          <!-- end box-body -->
          <blog-main-list></blog-main-list>
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
    data: function () {
        return { 
          alterTitleFlag: false,
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
    mounted(){
      var value = $('#title').val();
      $('.title1').append('<div id="virtual_dom" style="display:inline;">' + value + '</div>');
      var inputWidth =  $('#virtual_dom').width() + 10;
      $('#title').css('width', inputWidth); 
      $('#virtual_dom').remove();

      $('#title').on('keydown', function(e){
        var value = $('#title').val();
        $('.title1').append('<div id="virtual_dom" style="display:inline;">' + value + '</div>');
        var inputWidth =  $('#virtual_dom').width() + 10;
        $('#title').css('width', inputWidth); 
        $('#virtual_dom').remove();
      })
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
            http.get('project/blog/'+this.seq_user+'/'+this.seq_blog)
            .then(({ data }) => {
                this.blogOwnerNumOfProject = data;
            });
            http.get('post/blog/'+this.seq_user+'/'+this.seq_blog)
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
      },
      set(){
        if(this.$store.state.settingButtonVisible){
          $('#setting').css('color','#B1B0AC');
          this.$store.state.settingButtonVisible = false;
        }
        else{
          $('#setting').css('color', 'black');
          this.$store.state.settingButtonVisible = true;
        }
      },
      follower(){
        alert("이웃관리 페이지로 이동")
      },
      alterTitle(){
        $('#title').attr('disabled', false);
        $('#title').focus();
        $('#title').keypress(function (e) {
          if(e.which == 13)
            $('#title').attr('disabled', true);
        })
      }
    }
  }
</script>
<style>
.column4{
    float: right;
    margin-right: 40px;
  }
  .column4 span{
    cursor: pointer;
  }
  #title{
    padding: 0px;
    border: none;
    background: transparent;
  }
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