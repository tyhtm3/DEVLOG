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
<!-- <h2>탭</h2>
	<ul id="myTab" class="nav nav-tabs" role="tablist">
	  <li role="presentation" class="active"><a data-target="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">Home</a></li>
	  <li role="presentation" class=""><a data-target="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">Profile</a></li>
	  <li role="presentation" class="dropdown">
	    <a data-target="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown" aria-controls="myTabDrop1-contents">Dropdown <span class="caret"></span></a>
	    <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1" id="myTabDrop1-contents">
	      <li><a data-target="#dropdown1" tabindex="-1" role="tab" id="dropdown1-tab" data-toggle="tab" aria-controls="dropdown1">@fat</a></li>
	      <li><a data-target="#dropdown2" tabindex="-1" role="tab" id="dropdown2-tab" data-toggle="tab" aria-controls="dropdown2">@mdo</a></li>
	    </ul>
	  </li>
	</ul>
	<div id="myTabContent" class="tab-content">
	  <div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
	    <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui irure terry richardson ex squid. Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui.</p>
	  </div>
	  <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
	    <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>
	  </div>
	  <div role="tabpanel" class="tab-pane fade" id="dropdown1" aria-labelledby="dropdown1-tab">
	    <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr.</p>
	  </div>
	  <div role="tabpanel" class="tab-pane fade" id="dropdown2" aria-labelledby="dropdown2-tab">
	    <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral, mustache readymade thundercats keffiyeh craft beer marfa ethical. Wolf salvia freegan, sartorial keffiyeh echo park vegan.</p>
	  </div>
	</div> -->


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
                  <!-- 
                    부트스트랩 깔려있어야 중앙 정렬 되네요
                    npm install bootstrap-vue bootstrap
                  -->
                  <ul id="myTab" class="nav nav-tabs nav-tabs-shop-devin nav-justified" role="tablist">
                    <li class="active" data-wow-delay="0.6s"><a href="#project" role="tab" data-toggle="tab" aria-expanded="true"><h2><strong>Project</strong></h2></a></li>
                    <li class="" data-wow-delay="0.4s"><a href="#post" role="tab" data-toggle="tab" aria-expanded="false"><h2><strong>Post</strong></h2></a></li>
                    <li class="" data-wow-delay="0.2s"><a href="#portfolio" role="tab" data-toggle="tab" aria-expanded="false"><h2><strong>Portfolio</strong></h2></a></li>
                  </ul>

                  <div class="tab-content">
                    <!-- TAB PANEL ITEM-->
                    <div role="tabpanel" class="tab-pane fade active in" id="project">
                      <project-page></project-page>
                    </div>
                    <!--/ TAB PANEL ITEM -->

                    <!-- TAB PANEL ITEM-->
                    <div role="tabpanel" class="tab-pane fade" id="post">
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
            formLabelWidth: '120px',
            curpage:'0'
        }
    },
    created(){
      $(document).ready(function() {
          $("#myTab a[href='#project']").on('show.bs.tab', function(e){
            this.curpage=0;
            // alert(this.curpage);
          });
      });
      $(document).ready(function() {
          $("#myTab a[href='#post']").on('show.bs.tab', function(e){
            this.curpage=1;
            // alert(this.curpage);
          });
      });
      $(document).ready(function() {
        $("#myTab a[href='#portfolio']").on('show.bs.tab', function(e){
          this.curpage=2;
          // alert(this.curpage);
        });
      });
    },
    methods:{
      
      // $(“#myTab li:eq(2) a”).tab(“show“);   // 3번째 탭
    },
  }
</script>