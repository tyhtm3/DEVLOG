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
                <div class="details-profile">
                  <div class="title1" style="display:inline">
                    <!-- 명묭이의 코딩일기 -->
                    <input type="text" id="title" value="명묭이의 코딩일기" disabled>
                  </div>
                  <i class="ti-pencil-alt" v-if="this.$store.state.settingButtonVisible" @click="alterTitle" style="cursor:pointer;"></i>
                  <div class="title2">
                    by 명묭
                    <a href="#"><img src="static/img/profile.png" alt="cover" class="cover-profile" /></a>
                    <!-- <span>Web Designer</span> -->
                  </div>
                </div>
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
                      <div class="col-sm-4 emphasis" style="cursor:pointer;">
                        <h2><strong @click="follower">81</strong></h2>
                        <p> <small @click="follower">Follower</small> </p>
                      </div>
                    </div>
                  </div>
                  <!-- end column2 -->
                  <!-- <div class="column1">
                    <span class="tag">#SpringBoot</span>
                    <span class="tag">#Vue.js</span>
                    <span class="tag">#css</span>
                    <span class="tag">#python</span>
                    <span class="tag">#photoshop</span>
                    <span class="tag">#Node.js</span>
                  </div> -->
                  <!-- end column1 -->
                  <div class="column4" v-if="this.$store.state.isLogin">
                    <!-- <router-link to="글작성 페이지"> -->
                    <span>글 작성<i class="ti-pencil" style="display:inline"></i></span>&nbsp;
                    <!-- </router-link> -->
                    <span id="setting" @click="set">설정<i class="ti-settings" style="display:inline"></i></span>
                  </div>
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
  export default {
    name: 'y_blog-main',
    components: {
     'blog-main-list': BlogMainList
    },
    data: function () {
        return { 
          alterTitleFlag: false
        }
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
</style>