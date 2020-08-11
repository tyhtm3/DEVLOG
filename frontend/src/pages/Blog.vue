<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <!-- start container-movie : 블로그 배너-->
      <div class="container-movie" style="top:65px;">
        <!-- start profile -->
        <div class="details-profile" >
          <div class="title1" style="display:inline">
            <!-- input length 바꾸게 하는것 -->
            <input type="text" style="font-size:40px;" id="title" v-model="blogInfo.blog_name" v-on:keyup.13="updateBlog" readonly />
            <br>
            <input type="text" style="font-size:15px;" id="detail" v-model="blogInfo.blog_detail" v-on:keyup.13="updateBlog" readonly />
          </div>
                
          <i class="ti-pencil-alt" v-if="this.getIsAdminMode" @click="alterBlog" style="cursor:pointer;"></i>
                
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
              <div class="col-sm-4 emphasis" style="cursor:pointer;" @click="follower">
                <h2><strong >{{blogOwnerNumOfNeighbor}}</strong></h2>
                <p><small>Follower </small></p>
              </div>
            </div>
          </div>
              
          <span style="margin-left:60px;"></span>  
          <!-- 블로그 태그 -->
          <span class = "tag" v-for="(tag, index) in blogOwnerMainTags" v-bind:key="index" style="margin-right:20px;">
            #{{tag.tag}}
          </span>

          <div class="column4" v-if= "getIsLogin">
            <span v-if="isAdmin">
              <router-link to="../writePost">
                <span>포스팅<i class="ti-pencil" style="display:inline"></i></span>&nbsp;
              </router-link>
              <span id="setting" @click="toggleAdminMode">관리<i class="ti-settings" style="display:inline"></i></span>&nbsp;
            </span>
            <span v-else @click="subscribe">이웃추가 <i class="ti-link"></i></span>&nbsp;
          </div>
        </div>
        <!-- end profile -->
      </div>
      <!-- end container-movie -->
      <div class="box">
        <blog-content v-if="!followerpage"></blog-content>
        <follower v-else></follower>
      </div>
    </div>
  </transition>
</template>
<script>
import blogContent from '../components/blogContent'
import follower from '../components/follower'
import http from '../util/http-common'
import { mapGetters } from 'vuex'
export default {
  components: {
    'blog-content': blogContent,
    'follower': follower,
  }, 
  computed: {
    ...mapGetters([
      'getUserInfo',
      'getIsAdminMode',
      'getIsLogin'
    ])
  },
  data: () => {
    return { 
      alterTitleFlag: false,
      seq_blog: '',
      blogInfo:[],
      blogOwnerId: '',
      blogOwnerInfo:[],
      blogOwnerNumOfProject:'',
      blogOwnerNumOfPost:'',
      blogOwnerNumOfNeighbor:'',
      blogOwnerMainTags:[],
      tags: ['java', 'spring', 'python', 'aws', 'ml', 'database', 'blockchain', 'javascript', 'tensorflow'],
      followerpage: false,
      isAdmin: '',
    }
  },
  created() {
    this.blogOwnerId= this.$route.params.id;
    this.getBlogOwnerInfo();
  },
  mounted() {
    if(this.blogOwnerId === this.getUserInfo.id)
      this.isAdmin = true
    var value = $('#title').val();
    $('.title1').append('<div id="virtual_dom" style="display:inline;">' + value + '</div>');
    var inputWidth =  $('#virtual_dom').width() + 400;
    $('#title').css('width', inputWidth); 
    $('#detail').css('width', inputWidth-200); 
    $('#virtual_dom').remove();

    $('#title').on('keydown', function(e){
      var value = $('#title').val();
      $('.title1').append('<div id="virtual_dom" style="display:inline;">' + value + '</div>');
      var inputWidth =  $('#virtual_dom').width() + 10;
      $('#title').css('width', inputWidth); 
      $('#virtual_dom').remove();
    })
  },
  methods: {
    getBlogOwnerInfo(){
      http.get('user/id/'+this.blogOwnerId)
      .then(({data})=>{
        if(data){
          this.blogOwnerInfo=data;
          this.seq_blog = this.blogOwnerInfo.seq;
          this.getBlogInfo();
        }
        else{
          this.$message({
            type: 'error',
            message: '존재하지 않는 블로그입니다.'
          });
          this.$router.push('/')
        }
      })
    },
    getBlogInfo() {
      http.get('blog/'+this.seq_blog)
      .then(({ data }) => {
        this.blogInfo=data;
      });
      http.get('project/blog/'+this.seq_blog)
      .then(({ data }) => {
          this.blogOwnerNumOfProject = data;
      });
      http.get('post/blog/'+this.seq_blog)
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
      });
    },
    toggleAdminMode() {
      if(this.getIsAdminMode){
        this.$message({
          type: 'info',
          message: '관리모드가 비활성화 되었습니다.'
        });
        $('#setting').css('color','#B1B0AC');
        this.$store.commit('setIsAdminMode', false)
      }
      else{
        this.$message({
          type: 'info',
          message: '관리모드가 활성화 되었습니다.',
        });
        $('#setting').css('color', 'black');
        this.$store.commit('setIsAdminMode', true)
      }
    },
    follower() {
      if(this.isAdmin){
        this.followerpage =!this.followerpage
      }
    },
    updateBlog(){
      http.put('blog',this.blogInfo)
      .then(({ data }) => {
        if(data=="success"){
          this.$message({
            type: 'info',
            message: '블로그 정보 수정이 완료되었습니다.'
          })
        }
        else{
          this.$message({
            type: 'danger',
            message: '블로그 정보 수정에 실패하였습니다.'
          })
        }
      })
    },
    alterBlog() {
      $('#title').attr('readonly', false);
      $('#title').focus();
      $('#title').keypress(function (e) {
        if(e.which == 13){
          $('#title').attr('readonly', true);
        }
      })
      $('#detail').attr('readonly', false);
      $('#detail').focus();
      $('#detail').keypress(function (e) {
        if(e.which == 13){
          $('#detail').attr('readonly', true);
        }
      })
    },  
    subscribe() {
      http.get('user/id/'+this.$route.params.id)
      .then(({data})=>{
        // console.log(data)
        http.get('/userneighbor/check/'+data.seq)
        .then(({data}) => {
          console.log(data)
          if(data.length === 0){
            alert("추가안된이웃")
            console.log(this.blogOwnerInfo.seq)
            console.log(this.$store.getters.getUserInfo.seq)
            http.post('/userneighbor', {
              seq_neighbor: this.blogOwnerInfo.seq,
              seq_user: this.$store.getters.getUserInfo.seq
            })
            .then(({ data }) => {
              this.$message({
                type: 'success',
                message: '이웃에 추가 되었습니다.',
              });
            })
          }
          else{
            http.delete('/userneighbor/'+this.blogOwnerInfo.seq)
            .then(({ data }) => {
              this.$message({
                type: 'success',
                message: '이웃목록에서 삭제 되었습니다.',
              });
            })
          }
        })
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
  #title, #detail{
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