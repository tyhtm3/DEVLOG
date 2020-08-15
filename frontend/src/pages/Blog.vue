<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <!-- start container-movie : 블로그 배너-->
      <div class="container-movie" style="top:65px;background: #9ebbcd35;">
        <!-- start profile -->
        <div class="details-profile" >
          <div class="title1" style="display:inline;">
            <!-- input length 바꾸게 하는것, font-size 수정시 자바스크립트 input box 수정하는 부분도 수정해야함.-->
            <input id="title"  type="text" style="font-size:32px; color:#333333;" v-model="blogInfo.blog_name" v-on:keyup.13="updateBlog"  :placeholder=titleplaceholder readonly /><br>
            <input id="detail" type="text" style="font-size:15px; color:#959595; padding-top:5px;" v-model="blogInfo.blog_detail" v-on:keyup.13="updateBlog"  :placeholder=contentplaceholder readonly />
          </div>
          <div class="title2" style="font-size: 15px; color:#959595; padding-top:5px;">
            by {{blogOwnerInfo.nickname}}
            <img :src="blogOwnerInfo.profile_img_url" alt="cover" class="cover-profile" style="width:25px; height:25px; border:none" />
            <!-- <span>Web Designer</span> -->
          </div>
          <div class="description-profile" style="max-width:700px">
          <div class="column2">
            <div class="row"> 
              <div class="col-xs-12 col-sm-3 emphasis">
                <h2 style="margin:0px">{{blogOwnerNumOfProject}}</h2>
                <p> <small>Project</small> </p>
              </div>
              <div class="col-xs-12 col-sm-3 emphasis">
                <h2 style="margin:0px" >{{blogOwnerNumOfPost}}</h2>
                <p> <small>Post</small> </p>
              </div>
              <div class="col-sm-3 emphasis" style="cursor:pointer;" @click="follower">
                <h2 style="margin:0px">{{blogOwnerNumOfNeighbor}}</h2>
                <p><small>Follow</small></p>
              </div>
            </div>
          </div>
        </div>

        <div>
          <!-- <span class="tag-copy material-icons">local_offer</span> -->
          <div v-for="(tag, index) in blogOwnerMainTags" v-bind:key="index" style="display:inline-block;">
          <!-- :class="{'tag-active': itemsContains(tag.tag)}" -->
          <span class="tag" id="blogmaintag" @click="tagSearch(tag.tag)" :class="{'active': itemsContains(tag.tag)}" >
            #{{tag.tag}}
          </span>
          <span v-show="getIsAdminMode" @click="deleteTag(tag.seq)" class="hideDeleteButton ti-close pull-top pull-right" 
          style="font-size:3px;color:#333333;padding:0px;margin-left:-35px;"></span>
          </div>
        </div>
 
            <!-- 블로그 태그 -->
        <!-- <span @click="tagSearch(tag.tag)" :class="{'tag-active': itemsContains(tag.tag)}"
        style="padding:5px; margin:2px; ">
          #{{tag.tag}}
          <span v-show="getIsAdminMode" @click="deleteTag(tag.seq)" class="ti-close" style="position:absolute; font-size:3px; color:#333333;"></span>
        </span> -->
        
        <span v-if="getIsAdminMode">
          <span v-if="searchBar">
            <input v-on:keyup.enter="addTag" v-model="tag" placeholder="#">
          </span>
          <span v-if="addIcon" style="position: absolute; cursor:pointer;" @click="tagInputVisible">
            <i class="material-icons">add_circle_outline</i>
          </span>
        </span>


          <div class="column4" v-if= "getIsLogin">
            <span v-if="isAdmin">
              <router-link to="../writePost">
                <span>포스팅<i class="ti-pencil" style="display:inline"></i></span>&nbsp;
              </router-link>
              <span id="setting" @click="toggleAdminMode">관리<i class="ti-settings" style="display:inline"></i></span>&nbsp;
            </span>
            <span v-else @click="subscribe">이웃 <i class="ti-link"></i></span>&nbsp;
          </div>
        </div>
        <!-- end profile -->
      </div>
      <!-- end container-movie -->
      <div class="box">
        <div class="box-body" style="min-height:400px;">
          <div class="col-sm-12">
            <div class="col-sm-10" style="margin: 0 auto; float: none;">
            <blog-content v-bind:searchTags="searchTags" v-if="!followerpage"></blog-content>
            <follower v-else></follower>
            </div>
          </div>
        </div>
      </div>
    <a href="#" class="top"><i class="material-icons" id="to-top">arrow_upward</i></a>
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
  watch:{
    // 관리자버튼 활성화시, 블로그명, 블로그 소개에 아무 내용 없을 경우 placeholder 표시
    // 관리자버튼 비활성화시 placeholder 없애기
    getIsAdminMode:function(){
      if(this.getIsAdminMode){
        this.titleplaceholder='블로그명을 입력하세요.'
        this.contentplaceholder='블로그 소개를 입력하세요.'
      }else{
        this.titleplaceholder='블로그 제목 무조건 있어야됨. 예외처리돼서 이 메세지가 보이면 안됨. '
        this.contentplaceholder=''
      }
    }
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
      followerpage: false,
      isAdmin: '',
      // 태그 검색
      searchTags: [],
      activeIndex: [],
      searchBar: false,
      addIcon: true,
      tag: '',
      titleplaceholder:'',
      contentplaceholder:''
    }
  },
  created() {
    this.blogOwnerId= this.$route.params.id;
    this.getBlogOwnerInfo();
  },
  mounted() {
    /* 블로그 상단 input박스 길이 조절 */
    if(this.blogOwnerId === this.getUserInfo.id)
      this.isAdmin = true
    this.$store.commit('setIsAdminMode', false)
    $('#title').attr('readonly', true);
    $('#detail').attr('readonly', true);
    $('#title').on('keydown', function(e){
      var value = $('#title').val();
      $('.title1').append('<div id="virtual_dom" style="display:inline; font-size:34px">' + value + '</div>');
      var inputWidth =  $('#virtual_dom').width();
      if(inputWidth<34)
        inputWidth=310;
      $('#title').css('width', inputWidth); 
      $('#virtual_dom').remove();
    })
    $('#detail').on('keydown', function(e){
      var value2 = $('#detail').val();
      $('.title1').append('<div id="virtual_dom2" style="display:inline; font-size:17px">' + value2 + '</div>');
      var inputWidth2 =  $('#virtual_dom2').width();
      if(inputWidth2<17)
        inputWidth2=180;
      $('#detail').css('width', inputWidth2); 
      $('#virtual_dom2').remove();
    })
  },
  methods: {
     // 태그 누를때마다 검색
    tagSearch(tag){
      // 태그 선택시 css 바꾸고 searchTags에 추가 (토글)
      var index = this.searchTags.indexOf(tag)
      var idx = this.activeIndex.indexOf(index)
      if(index<0){
        this.searchTags.push(tag)
        this.activeIndex.push(index)
      }else{
        this.searchTags.splice(index,1)
        this.activeIndex.splice(idx,1)
      }
    },
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
        this.$store.commit('setIsAdminMode', false)
        $('#setting').css('color','#B1B0AC');
        $('#title').attr('readonly', true);
        $('#detail').attr('readonly', true);
        
      }
      else{
        this.$message({
          type: 'info',
          message: '관리모드가 활성화 되었습니다.',
        });
        this.$store.commit('setIsAdminMode', true)
        $('#setting').css('color', 'black');
        $('#title').attr('readonly', false);
        $('#detail').attr('readonly', false);
        
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
    itemsContains(tag) {
      return this.searchTags.indexOf(tag) > -1
    },
    subscribe() {
      http.get('user/id/'+this.$route.params.id)
      .then(({data})=>{
        http.get('/userneighbor/check/'+data.seq)
        .then(({data}) => {
          if(data.length === 0){
            http.post('/userneighbor', {
              seq_neighbor: this.blogOwnerInfo.seq,
            })
            .then(({ data }) => {
              this.$message({
                type: 'success',
                message: '이웃 목록에 추가 되었습니다.',
              });
            })
          }
          else{
            http.delete('/userneighbor', {
              data:{
                seq_neighbor: this.blogOwnerInfo.seq
              }
            })
            .then(({ data }) => {
              this.$message({
                type: 'error',
                message: '이웃 목록에서 삭제 되었습니다.',
              });
            })
            .catch(({ error }) => {
              console.log(error)
            })
          }
        })
      })
    },
    tagInputVisible(){
      this.searchBar = true
      this.addIcon = false
    },
    addTag(){
      http
      .post('blogtag', {
        tag: this.tag
      })
      .then(({data}) => {
        this.blogOwnerMainTags.push(this.tag)
        this.searchBar = false
        this.addIcon = true
        this.tag = ''
        this.getBlogOwnerInfo()
      })
    },
    deleteTag(seq){
      http
      .delete('blogtag/'+seq)
      .then(({data}) => {
        this.$message({
          type: 'success',
          message: '태그가 삭제되었습니다.',
        });
        this.getBlogOwnerInfo()
      })
    }
  }
}

// jQuery
  $( window ).scroll( function() {
    if ( $( this ).scrollTop() > 200 ) {
      $( '.top' ).fadeIn();
    } else {
      $( '.top' ).fadeOut();
    }
  } );

  $('.top').click(function() {
    $( 'html, body' ).animate( { scrollTop : 0}, 400 );
    return false;
  });

</script>
<style scoped>
  html {
    scroll-behavior: smooth;
  }
  a.top {
    position: fixed;
    right: 7%;
    bottom: 20%;
    display: none;
    background-color: #9EBBCD;
    border-radius: 50%;
    width:48px;
    height:48px;
    text-align: center;
    line-height: 44px;
  }
  #to-top{
    vertical-align: middle;
    font-size: 36px;
  }
  .col-md-12{
    padding-right: 10%;
    padding-left: 10%;
  }
  .container-movie{  
    height: 400px;
    font-family: 'Noto Sans KR', sans-serif;
    background-color: #9ebbcd70;
    }
  .tag-active {
    background: #DDDDDD;
    border-radius: 20px;
  }
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
  .tag-active {
      background: #ddd;
  }

  /* 블로그 메인 상단 profile box UI */
  .details-profile .title1{
    font-size:0px !important;
    margin:0px !important;
  }
  .column2{
    padding-left:0px !important;
  }

  /* 블로그 메인 상단 수정 UI */
  .details-profile{
    padding-right:15% !important;
    padding-left:15% !important;
    padding-bottom:60px;
  }
  .description-profile{
    height:auto !important;
  }
  #blogmaintag{
    font-size:18px; 
    line-height:40px; 
    cursor:pointer; 
    /* margin-left:-8px; 
    margin-right:12px;  */
    padding:3px 8px !important;
  }
  .tag{
    background-color: transparent!important;
    color:gray;
    border-radius:40px;
    height:20px!important;
    /* background-color: #9EBBCD !important; */
  }
  .tag:hover{
    /* background-color:#9EBBCD !important; */
    height:16px;
    background-color:#ddd !important;
    box-shadow: 1px 1px 3px rgba(199, 199, 199, 0.4);
  }
  /* .tag-copy{
    background: transparent;
    padding: 3px 0px;
    line-height: 50px;
     font-size:20px; 
     color:#B1B0AC;
     float: left;
     margin:0px;
  } */

  /* chrome input에 자동 채워지는 배경 색상 글자 색상 변경 */
  input:-webkit-autofill,
  input:-webkit-autofill:hover,
  input:-webkit-autofill:focus,
  input:-webkit-autofill:active {
  transition: background-color 5000s ease-in-out 0s;
  -webkit-transition: background-color 9999s ease-out;
    -webkit-box-shadow: 0 0 0px 1000px transparent inset !important;
    -webkit-text-fill-color: #333333 !important;
  }
  



  /* font */
  @import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
  @font-face {
  font-family: 'NotoKrL';
  font-style: normal;
  font-weight: 100;
  src: local('Noto Sans Light'), local('NotoSans-Light'), url(/fonts/NotoSans-Light.eot);
  src: url(/fonts/NotoSans-Light.eot?#iefix) format('embedded-opentype'),
  url(/fonts/NotoSans-Light.woff2) format('woff2'),
  url(/fonts/NotoSans-Light.woff) format('woff');;
  }
  @font-face {
  font-family: 'NotoKrR';
  font-style: normal;
  font-weight: 300;
  src: local('Noto Sans Regular'), local('NotoSans-Regular'), url(/fonts/NotoSans-Regular.eot);
  src: url(/fonts/NotoSans-Regular.eot?#iefix) format('embedded-opentype'),
  url(/fonts/NotoSans-Regular.woff2) format('woff2'),
  url(/fonts/NotoSans-Regular.woff) format('woff');
  }
  @font-face {
  font-family: 'NotoKrM';
  font-style: normal;
  font-weight: 500;
  src: local('Noto Sans Medium'), local('NotoSans-Medium'), url(/fonts/NotoSans-Medium.eot);
  src: url(/fonts/NotoSans-Medium.eot?#iefix) format('embedded-opentype'),
  url(/fonts/NotoSans-Medium.woff2) format('woff2'),
  url(/fonts/NotoSans-Medium.woff) format('woff');
  }
  @font-face {
  font-family: 'NotoKrB';
  font-style: normal;
  font-weight: 700;
  src: local('Noto Sans Bold'), local('NotoSans-Bold'), url(/fonts/NotoSans-Bold.eot);
  src: url(/fonts/NotoSans-Bold.eot?#iefix) format('embedded-opentype'),
  url(/fonts/NotoSans-Bold.woff2) format('woff2'),
  url(/fonts/NotoSans-Bold.woff) format('woff');
  }
</style>