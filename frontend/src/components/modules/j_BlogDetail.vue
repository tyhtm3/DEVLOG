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
                    <h1>깃 브랜치 사용 방법</h1>
                  </div>
                </div>
                <!-- end hero -->
                <div class="description-profile">
                <!-- <span class="author">명묭</span> -->
                <span>
                  <span> <i class="el-icon-date"></i> {{postedDate}} </span>&#124;
                  <span> <i class="el-icon-s-comment"></i> {{comments}} </span>&#124;
                  <span> <i class="el-icon-chicken"></i> {{likes}}</span>
                </span>
								
                <span style="float: right;">
                  <span><a href="#">수정</a></span> &#124;
                  <span><a href="#" onclick="deleteEntry(); return false;">삭제</a></span>
                </span>
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
          postedDate: '2020. 7. 24. 15:42',
          comments: '11',
          likes: '5',
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