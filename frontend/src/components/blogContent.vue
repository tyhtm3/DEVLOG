<template>
  <!-- start body -->
    <div class="box" style="padding:30px;">
      <div class="row">
        <div class="col-md-12">
          <!-- TABS -->
          <ul id="myTab" class="nav nav-tabs nav-tabs-shop-devin" role="tablist" style="text-align:center;">
            <li class="active" data-wow-delay="0.6s" style="width:33%"><a href="#project" role="tab" data-toggle="tab" aria-expanded="true" @click="curpage=0"><h2><strong>Project</strong></h2></a></li>
            <li class="" data-wow-delay="0.2s" style="width:33%"><a href="#portfolio" role="tab" data-toggle="tab" aria-expanded="false" @click="curpage=2"><h2><strong>Portfolio</strong></h2></a></li>
            <li class="" data-wow-delay="0.4s" style="width:33%"><a href="#post" role="tab" data-toggle="tab" aria-expanded="false" @click="curpage=1"><h2><strong>etc</strong></h2></a></li>
          </ul>

          <div class="tab-content">
            <!-- <div v-if="curpage == 0" role="tabpanel" class="tab-pane fade active in" id="project"> -->
            <!-- TAB PANEL ITEM-->
            <div role="tabpanel" class="tab-pane fade active in" id="project">
              <project-list></project-list>
            </div>
            <!--/ TAB PANEL ITEM -->

            <!-- TAB PANEL ITEM-->
            <div role="tabpanel" class="tab-pane fade" id="portfolio">
              <portfolio-list></portfolio-list>
            </div>
            <!--/ TAB PANEL ITEM -->

            <!-- TAB PANEL ITEM-->
            <div role="tabpanel" class="tab-pane fade" id="post">
              <post-list></post-list>
            </div>
            <!--/ TAB PANEL ITEM -->

          </div>
          <!-- / TABS -->
        </div>
      </div>
    </div>
    <!-- /.content -->
  <!-- end body -->
</template>

<script>
  import projectList from './projectList.vue'
  import postList from './postList.vue'
  import portfolioList from './portfolioList.vue'
  export default {
    name: 'blog-content',
    components: {
      'project-list': projectList,
      'post-list': postList,
      'portfolio-list': portfolioList
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

<style scoped>
#myTab {
  display: table;
  width: 100%;
  list-style: none;
}
#myTab li {
display: table-cell;
text-align: center;
}
</style>