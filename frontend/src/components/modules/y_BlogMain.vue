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
                      명묭이의 코딩일기
                    </div>
                    <div class="title2">
                      by 명묭
                      <a href="#"><img src="static/img/profile.png" alt="cover" class="cover-profile" /></a>
                      <!-- <span>Web Designer</span> -->
                    </div>
                  </div>
                </div>
                <!-- end hero -->
                <div class="description-profile">
                  <div class="column2">
                    <div class="row">
                      <div class="col-xs-12 col-sm-4 emphasis">
                        <h2><strong>7</strong></h2>
                        <p> <small>Projects</small> </p>
                      </div>
                      <div class="col-xs-12 col-sm-4 emphasis">
                        <h2><strong>57</strong></h2>
                        <p> <small>Post</small> </p>
                      </div>
                      <div class="col-sm-4 emphasis">
                        <h2><strong>81</strong></h2>
                        <p> <small>Follower</small> </p>
                      </div>
                    </div>
                  </div>
                  <!-- end column2 -->
                  <div class="column1">
                    <span class="tag">#SpringBoot</span>
                    <span class="tag">#Vue.js</span>
                    <span class="tag">#css</span>
                    <span class="tag">#python</span>
                    <span class="tag">#photoshop</span>
                    <span class="tag">#Node.js</span>
                  </div>
                  <!-- end column1 -->
                </div>
              </div>
              <!-- end profile -->
              <!-- start body -->
                <div class="box" style="padding:30px;">
                <div class="row">
                <div class="col-md-12">
                  <!-- TABS -->
                  <ul class="nav nav-tabs nav-tabs-shop-devin" role="tablist">
                    <li class="" data-wow-delay="0.6s"><a href="#project" data-toggle="tab" aria-expanded="false"><h2><strong>Project</strong></h2></a></li>
                    <li class="active" data-wow-delay="0.4s"><a href="#post" data-toggle="tab" aria-expanded="true"><h2><strong>Post</strong></h2></a></li>
                    <li class="" data-wow-delay="0.2s"><a href="#portfolio" data-toggle="tab" aria-expanded="false"><h2><strong>Portfolio</strong></h2></a></li>
                  </ul>

                  <div class="tab-content">
                    <!-- TAB PANEL ITEM-->
                    <div role="tabpanel" class="tab-pane fade" id="project">
                      <project-page></project-page>
                    </div>
                    <!--/ TAB PANEL ITEM -->

                    <!-- TAB PANEL ITEM-->
                    <div role="tabpanel" class="tab-pane fade active in" id="post">
                      <post-page></post-page>
                    </div>
                    <!--/ TAB PANEL ITEM -->

                    <!-- TAB PANEL ITEM-->
                    <div role="tabpanel" class="tab-pane fade" id="portfolio">
                      <portfolio-page></portfolio-page>
                    </div>
                    <!--/ TAB PANEL ITEM -->
                  </div>
                  <!-- / TABS -->
                </div>
                </div>
                </div>
                <!-- /.content -->
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
  import ProjectPage from '../modules/ji_ProjectPage.vue'
  import PostPage from '../modules/ji_PostPage.vue'
  import PortfolioPage from '../modules/ji_PortfolioPage.vue'
  export default {
    name: 'y_blog-main',
    components: {
      'project-page': ProjectPage,
      'post-page': PostPage,
      'portfolio-page': PortfolioPage
    },
    data: function () {
        return { 
            loginFormVisible: false,
            form: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            formLabelWidth: '120px'
        }
    }
  }
</script>