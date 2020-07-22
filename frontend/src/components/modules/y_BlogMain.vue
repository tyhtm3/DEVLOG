<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <!-- start header -->
      <section class="content-header">
        <h1>Profile Page<small>It's all start from here <i class="ti-heart"></i><i class="ti-export"></i><i class="ti-printer"></i></small></h1>
        <ol class="breadcrumb">
          <li>
            <router-link to="/"> <i class="ti-home"></i></router-link>
          </li>
          <li><a href="#">Apps</a></li>
          <li class="active">Profile</li>
        </ol>
      </section>
      <!-- end header -->
      <!-- start Main content -->
      <section class="content">
        <div class="box">
          <div class="box-body" style="min-height:400px;">
            <div class="movie-card">
              <!-- start profile -->
              <div class="container-movie">
                <a href="#"><img src="http://api.randomuser.me/portraits/men/32.jpg" alt="cover" class="cover-profile" /></a>
                <div class="hero">
                  <div class="details-profile">
                    <div class="title1">명묭이의 코딩일기 <span>Web Designer</span></div>
                    <div class="title2">by 명묭</div>
                  </div>
                </div>
                <!-- end hero -->
                <div class="description-profile">
                  <div class="column1 text-center">
                    <span class="tag">#SpringBoot</span>
                    <span class="tag">#Vue.js</span>
                    <span class="tag">#css</span>
                    <span class="tag">#python</span>
                    <span class="tag">#photoshop</span>
                    <span class="tag">#Node.js</span>
                  </div>
                  <!-- end column1 -->
                  <div class="column2">
                    <div class="row">
                      <div class="col-xs-12 col-sm-4 emphasis">
                        <h2><strong>7</strong></h2>
                        <p> <small>Projects</small> </p>
                        <button class="btn btn-success "> <span class="fa fa-plus-circle"></span>&nbsp;&nbsp;Follow</button>
                      </div>
                      <div class="col-xs-12 col-sm-4 emphasis">
                        <h2><strong>57</strong></h2>
                        <p> <small>Post</small> </p>
                        <!-- <button class="btn btn-info "> <span class="fa fa-user"></span>&nbsp;&nbsp;View Profile</button> -->
                      </div>
                      <div class="col-sm-4 emphasis">
                        <h2><strong>81</strong></h2>
                        <p> <small>Follower</small> </p>
                        <button class="btn btn-success "><span class="fa fa-plus-circle"></span>&nbsp;&nbsp;Follow</button>
                        <!-- <button class="btn btn-default"> <span class="fa fa-user"></span>&nbsp;&nbsp;Likes</button> -->
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- end profile -->
              <!-- start body -->
              <div class="col-sm-12">
                <br><br><br>
                <div class="row">
                  <div class="col-xs-12 col-sm-4 emphasis_custom">
                    <a href="#"><h2><strong>Project</strong></h2></a>
                    <!-- <router-link to="project"><h2><strong>Project</strong></h2></router-link> -->
                  </div>
                  <div class="col-xs-12 col-sm-4 emphasis_custom">
                    <a href="#"><h2><strong>Post</strong></h2></a>
                  </div>
                  <div class="col-sm-4 emphasis_custom">
                    <a href="#"><h2><strong>Portfolio</strong></h2></a>
                  </div>  
                </div>
                <blog-mansory></blog-mansory>
                <!-- <router-view></router-view> -->
              </div>
              <!-- end body -->
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
  import BlogMansory from '../modules/y_BlogMansory.vue'
  export default {
    name: 'y_blog-main',
    components: {
      'blog-mansory': BlogMansory
    }
  }
</script>