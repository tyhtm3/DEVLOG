<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;padding-left:50px;padding-right:50px">
      <!-- start container-movie : 블로그 배너-->
      <div class="container-movie" style="top:65px;background: white;">
        <!-- start profile -->
        <div class="details-profile" >
          <div class="title1" style="display:inline;">
            <!-- input length 바꾸게 하는것 -->
            <input type="text" style="width:100%; font-size:32px;color:#333333;" id="title" v-model="blogInfo.blog_name" v-on:keyup.13="updateBlog" readonly />
            <input type="text" style="width:100%; font-size:13px;color:#959595;" id="detail" v-model="blogInfo.blog_detail" v-on:keyup.13="updateBlog" readonly />
          </div>
                
          <div class="title2" style="color:#959595;font-size:18px">
            by {{blogOwnerInfo.nickname}}
            <!-- 일단은 블로그 주인 프로필 이미지 주소로 받아오게 함. 바꿔야돼-->
            <a href="#"><img :src="blogOwnerInfo.profile_img_url" alt="cover" class="cover-profile" /></a>
            <!-- <span>Web Designer</span> -->
          </div>
        </div>
        <div class="description-profile">
          <div class="column2">
            <div class="row"> 
              <div class="col-xs-12 col-sm-3 emphasis">
                <h2><strong>{{blogOwnerNumOfProject}}</strong></h2>
                <p> <small>Projects</small> </p>
              </div>
              <div class="col-xs-12 col-sm-3 emphasis">
                <h2><strong>{{blogOwnerNumOfPost}}</strong></h2>
                <p> <small>Post</small> </p>
              </div>
              <div class="col-sm-3 emphasis" style="cursor:pointer;" @click="follower">
                <h2><strong >{{blogOwnerNumOfNeighbor}}</strong></h2>
                <p><small>Follow </small></p>
              </div>
            </div>
          </div>

          <div>    
          <span style="margin-left:60px;"></span>  
          
           <!-- 태그 3개만 갖고오기--> 
            <div v-for="(tag,index) in blogOwnerMainTags" :class="{'tag-active': itemsContains(tag.tag)}"  :key="index" style="display:inline-block;" >
               <span style="margin-right:20px;" class="tag" @click="tagSearch(tag.tag)"  >#{{tag.tag}}</span>
            </div>

          </div>
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
        <blog-content v-bind:searchTags="searchTags" v-if="!followerpage"></blog-content>
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
      // 태그 검색
      searchTags: [],
      activeIndex: [],
    }
  },
  created() {
    this.blogOwnerId= this.$route.params.id;
    this.getBlogOwnerInfo();
  },
  mounted() {
    if(this.blogOwnerId === this.getUserInfo.id)
      this.isAdmin = true
    this.$store.commit('setIsAdminMode', false)
    $('#title').attr('readonly', true);
    $('#detail').attr('readonly', true);

    $('#title').on('keydown', function(e){
      var value = $('#title').val();
      $('.title1').append('<div id="virtual_dom" style="display:inline; font-size:40px">' + value + '</div>');
      var inputWidth =  $('#virtual_dom').width();
      $('#title').css('width', inputWidth); 
      $('#virtual_dom').remove();
    })

    $('#detail').on('keydown', function(e){
      var value2 = $('#detail').val();
      $('.title1').append('<div id="virtual_dom2" style="display:inline; font-size:15px">' + value2 + '</div>');
      var inputWidth2 =  $('#virtual_dom2').width();
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
            console.log(this.blogOwnerInfo.seq)
            http.delete('/userneighbor', {
              data:{
                seq_neighbor: this.blogOwnerInfo.seq
              }
            })
            .then(({ data }) => {
              console.log(data)
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
    }
  }
}
</script>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
.container-movie{  font-family: 'Noto Sans KR', sans-serif;}

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