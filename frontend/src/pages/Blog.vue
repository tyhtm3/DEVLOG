<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <!-- start container-movie : 블로그 배너-->
      <div class="container-movie" style="top:65px;background: #9ebbcd35;">
        <!-- start profile -->
        <div class="details-profile" >
          <div class="title1" style="display:inline;">
            <input id="title"  type="text" style="font-size:32px; color:#333333; width:100%;" v-model="blogInfo.blog_name" v-on:keyup.13="updateBlog"  :placeholder=titleplaceholder readonly /><br>
            <input id="detail" type="text" style="font-size:15px; color:#959595; padding-top:5px; width:100%;" v-model="blogInfo.blog_detail" v-on:keyup.13="updateBlog"  :placeholder=contentplaceholder readonly />
          </div>
          <div v-if="!isAdmin" class="title2" style="font-size: 15px; color:#959595; padding-top:5px;">
              <span class="show-blog-owner-nickname">by {{blogOwnerInfo.nickname}}</span>
              <img :src="blogOwnerInfo.profile_img_url" alt="cover" class="cover-profile" style="width:25px; height:25px; border:none" />
              
              </div>
          <router-link v-if="isAdmin" to="/myinfo">
            <div class="title2" style="font-size: 15px; color:#959595; padding-top:5px;">
              <span class="show-blog-owner-nickname">by {{blogOwnerInfo.nickname}}</span>
              <img :src="blogOwnerInfo.profile_img_url" alt="cover" class="cover-profile" style="width:25px; height:25px; border:none" />
               </div>
          </router-link>
          
          <div class="description-profile" style="max-width:700px">
            <div class="column2">
              <div class="row"> 
                <div class="col-xs-12 col-sm-3 emphasis">
                  <h2 style="margin:0px">{{blogOwnerNumOfProject}}</h2>
                  <p> <small>프로젝트</small> </p>
                </div>
                <div class="col-xs-12 col-sm-3 emphasis">
                  <h2 style="margin:0px" >{{blogOwnerNumOfPost}}</h2>
                  <p> <small>포스트</small> </p>
                </div>
                <div v-if="isAdmin" class="col-sm-3 emphasis" id="follower" style="cursor:pointer;" @click="follower" @mouseenter="changeFollowText" @mouseleave="undoFollowText">
                  <h2 style="margin:0px">{{blogOwnerNumOfNeighbor}}</h2>
                  <p><small id="followertext" v-text="followtext"></small></p>
                </div>
                <div v-else class="col-sm-3 emphasis">
                  <h2 style="margin:0px">{{blogOwnerNumOfNeighbor}}</h2>
                  <p><small id="followertext" v-text="followtext"></small></p>
                </div>
               
              </div>
            </div>
          </div>


          <div class="tag-nest-detail" style="padding-top:10px;  width:100%; overflow:auto">              
            <div id="blogmaintag-block" style="display:inline-block;">
              <!-- 태그 입력 받는곳 -->
              <span v-if="getIsAdminMode&&addIcon" id="blogmaintag" class="search tag ti-plus " @click="tagInputVisible" style="border:solid; padding:3px!important; margin-left:18px; background-color:transparent!important; color:lightgray">
              </span>
              <span v-if="getIsAdminMode&&searchBar" id="blogmaintag" class="search tag ti-close" @click="tagInputNotVisible" style="border:solid; padding:3px!important; margin-left:18px; background-color:transparent!important; color:lightgray">
              </span>
              <span v-if="getIsAdminMode&&searchBar" class="search" id="demo-2" style="font-size:18px;">
                # <input v-on:keyup.enter="addTag" v-model="tag" class="inputtag" placeholder="태그를 입력해주세요."  style="width:170px">
              </span>
              <!-- + 버튼 -->
              
            </div>
            <div id="blogmaintag-block" v-for="(tag, index) in blogOwnerMainTags" v-bind:key="index" style="display:inline-block;">
              <span :class="{'active': itemsContains(tag.tag)}" id="blogmaintag" class="tag" @click="tagSearch(tag.tag)" >
                #{{tag.tag}}
              </span>
              <span v-show="getIsAdminMode" @click="deleteTag(tag.seq, tag.tag)" class="delete-tag-button hideDeleteButton ti-close pull-top pull-right"></span>
            </div>
          </div>
          <div class="column4" v-if= "getIsLogin">
            <span v-if="isAdmin">
              <router-link to="../writePost">
                <span>포스팅<i class="ti-pencil" style="display:inline"></i></span>&nbsp;
              </router-link>
              <span id="setting" @click="toggleAdminMode">관리<i class="ti-settings" style="display:inline"></i></span>&nbsp;
            </span>
            <span v-else @click="subscribe(true)">{{subscribemessage}}<i v-if="subscribemessage.length>3" class="ti-unlink"></i><i v-else class="ti-link"></i></span>&nbsp;
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
            <follower v-else ></follower>
            </div>
          </div>
        </div>
      </div>
      <el-tooltip class="item" effect="dark" content="상단으로" placement="bottom" popper-class="draw_share_atooltip">
        <a href="#" class="topimg"><img src="../assets/top3.png" height="48px"></a>
      </el-tooltip>
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
      isSubscribing: '',
      subscribemessage:'',
      // 태그 검색
      searchTags: [],
      activeIndex: [],
      searchBar: false,
      addIcon: true,
      tag: '',
      titleplaceholder:'',
      contentplaceholder:'',
      followtext:''
    }
  },
  created() {
    this.blogOwnerId= this.$route.params.id;
    this.getBlogOwnerInfo();
    this.getFromDetailSearchTag();
    this.followtext = '구독자';
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
    getFromDetailSearchTag(){
        var tag = this.$store.state.searchTag
        if(tag!=null){
        this.tag = tag
        this.searchTags.push(tag)
        var index = this.searchTags.indexOf(tag)
        this.activeIndex.push(index)
        this.$store.commit('setSearchTag',null)
        }
    },
    changeFollowText(){
      this.followtext = '구독관리';
    },
    undoFollowText(){
      this.followtext = '구독자';
    },
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
      http.get('blogtag/'+this.seq_blog)
      .then(({ data }) => {
        this.blogOwnerMainTags = data;
      });
      // 이웃여부 불러올 때 함
      this.subscribe(false);
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
        this.searchBar = false
        this.addIcon = true
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
    subscribe(bool) {
      http.get('user/id/'+this.$route.params.id)
      .then(({data})=>{
        http.get('/userneighbor/check/'+data.seq)
        .then(({data}) => {
          if(data.length === 0){
            this.subscribemessage="구독";
            // 구독하기 버튼 눌러서 함수에 들어온거라면
            if(bool==true){
              http.post('/userneighbor', {
                seq_neighbor: this.blogOwnerInfo.seq,
              })
              .then(({ data }) => {
                this.$message({
                  type: 'success',
                  message: this.blogOwnerInfo.nickname+'님의 블로그를 구독합니다.',
                });
                this.subscribemessage="구독 해제"
                this.getBlogOwnerInfo()
              })
            }
            // 구독완료
          }else{
            this.subscribemessage="구독 해제"
            // 구독취소 버튼 눌러서 함수에 들어온거라면
            if(bool==true){
              http.delete('/userneighbor', {
                data:{
                  seq_neighbor: this.blogOwnerInfo.seq
                }
              })
              .then(({ data }) => {
                this.$message({
                  type: 'error',
                  message: '구독을 취소합니다.',
                });
                this.subscribemessage="구독"
                this.getBlogOwnerInfo()
              })
              .catch(({ error }) => {
                console.log(error)
              })
            }
            // 구독취소완료
          }
        })
        this.getBlogOwnerNumOfNeighbor(data.seq);
      })
    },
    getBlogOwnerNumOfNeighbor(seq){
       http.get('userneighbor/'+seq)
      .then(({ data }) => {
          this.blogOwnerNumOfNeighbor = data.length;
      });
    },
    tagInputVisible(){
      this.searchBar = true
      this.addIcon = false
    },
    tagInputNotVisible(){
      this.searchBar = false
      this.addIcon = true
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
    deleteTag(seq, tag){
      http
      .delete('blogtag/'+seq)
      .then(({data}) => {
        this.$message({
          type: 'success',
          message: '태그가 삭제되었습니다.',
        });
        // 지워진 태그가 search에 포함되어있으면 search도 삭제
        if(this.searchTags.indexOf(tag)>-1){
          this.tagSearch(tag);
        }
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
  /* 말풍선 css 약간 수정*/
  .el-tooltip__popper[x-placement^=right] .popper__arrow::after {
      border-right-color: #9ebbcd  !important;
      /* border-bottom-color: #11212E  !important; */
    }
  .el-tooltip__popper[x-placement^=right] .popper__arrow{
      border-right-color: #9ebbcd  !important;
      /* border-bottom-color: #11212E  !important; */
  }
  .draw_share_atooltip{
      /* background: transparent !important; */
      background: #9ebbcd !important;
      /* background: #11212E !important; */
  }
</style>


<style lang="scss" scoped>

  .col-md-12{
    padding-right: 10%;
    padding-left: 10%;
  }
  .container-movie{  
    height: 425px;
    font-family: 'Noto Sans KR', sans-serif;
    background-color: #9ebbcd70;
    }
  .column4{
    float: right;
    margin-right: 40px;
  }
  .column4 span{
    cursor: pointer;
  }
  #title{
    font-family: 'Noto Sans KR', sans-serif;
    font-weight: 400;
    margin-left:-3px;
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
  /* 블로그 메인 상단 profile box UI */
  .details-profile .title1{
    font-size:0px !important;
    margin:0px !important;
  }
  .details-profile .title2 span{
    all: unset;
    color: rgb(149, 149, 149);
    font-family: 'Noto Sans KR', sans-serif;
  }
  #show-blog-owner-nickname:hover{
    box-shadow: 3px 3px 3px rgba(177, 177, 177, 0.527);
    font-family: 'Noto Sans KR', sans-serif;
  }
  .column2{
    padding-left:0px !important;
  }
  #follower:hover #followertext{ 
    font-weight: bold;
    color:gray;
  }

  /* 블로그 메인 관리 UI */
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
    margin-left:10px;
    padding:3px 8px !important;
  }
  #blogmaintag-block:first-child{
    margin-left:-13px;
  }
  .tag{
    color:gray;
    border-radius:40px;
    height:20px!important;
  }
  .tag:hover{
    height:16px;
    background-color:#ddd !important;
    /* background-color:#9EBBCD !important; */
    box-shadow: 1px 1px 3px rgba(199, 199, 199, 0.4);
  }
  .active{
    background-color: #ddd;
  }
  .tag-copy{
    background: transparent;
    padding: 3px 0px;
    line-height: 50px;
    font-size:20px; 
    color:#B1B0AC;
    float: left;
    margin:0px;
  }
  




  /* 관리자모드 UI */
  .inputtag{
  opacity:0.5;
  border:solid;
  border-top:1px;
  border-left:1px;
  border-right:1px;
  border-color: rgba(143, 143, 143, 0.432);
  border-width: 0.1px;
  width:135px;
  }
  .inputtag:hover{
    opacity:0.8;
  }
  .inputtag:focus{
    opacity:0.8;
    outline: none;
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
  url(/fonts/NotoSans-Light.woff) format('woff');
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
