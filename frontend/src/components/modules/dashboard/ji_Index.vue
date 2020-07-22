<template>
    <transition name="el-zoom-in-top">
        <!-- <div class="content-wrapper dashboard-wrapper">
            <div class="col-sm-12">
                <br><br><br>
                <div class="row">
                  <div class="col-xs-12 col-sm-4 emphasis_custom">
                    <a href="#"><h2><strong>Project</strong></h2></a>
                  </div>
                  <div class="col-xs-12 col-sm-4 emphasis_custom">
                    <a href="#"><h2><strong>Post</strong></h2></a>
                  </div>
                  <div class="col-sm-4 emphasis_custom">
                    <a href="#"><h2><strong>Portfolio</strong></h2></a>
                  </div>  
                </div>
                <blog-mansory></blog-mansory>
              </div>
        </div> -->
        <div class="content-wrapper dashboard-wrapper">
            <section class="content">
            <div class="box" style="padding:30px;">
            <div class="row">
						<div class="col-md-12">
							<!-- TABS -->
							<ul class="nav nav-tabs nav-tabs-shop-devin" role="tablist">
								<li class="active" data-wow-delay="0.6s"><a href="#project" data-toggle="tab" aria-expanded="false"><h2><strong>Project</strong></h2></a></li>
								<li class="" data-wow-delay="0.4s"><a href="#post" data-toggle="tab" aria-expanded="true"><h2><strong>Post</strong></h2></a></li>
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
            </section>
            <!-- /.content -->
        </div>
    </transition>
</template>
<script>
  import ProjectPage from '../../modules/ji_ProjectPage.vue'
  import PostPage from '../../modules/ji_PostPage.vue'
  import PortfolioPage from '../../modules/ji_PortfolioPage.vue'
  export default {
    name: 'ji-index',
    components: {
      'project-page': ProjectPage,
      'post-page': PostPage,
      'portfolio-page': PortfolioPage
    }
  }
</script>