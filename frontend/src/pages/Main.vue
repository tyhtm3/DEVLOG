<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper" style="background: white;">
      <!-- start banner carousel -->
      <div class="carousel">
        <el-carousel indicator-position="outside" height='500px'>
          <el-carousel-item class="img-resize">
            <img class="img-resize" src="../../docs/static/img/banner1.jpg">
          </el-carousel-item>
          <el-carousel-item>
            <img class="img-resize" src="../../docs/static/img/banner2.jpg">
          </el-carousel-item>
          <el-carousel-item>
            <img class="img-resize" src="../../docs/static/img/banner3.jpg">
          </el-carousel-item>
        </el-carousel>
      </div>
      <!-- end banner carousel -->

      <br><br><br>

      <div class="box">
        <div class="box-body" style="min-height:400px;">
          <div class="col-sm-12">
            <br><br><br>

            <div class="col-sm-8" style="margin: 0 auto; float: none;">
                        

              <!-- start tag search bar -->
              <!-- 미구현 목록
                  1. 태그 클릭시 기능 ??
                  2. 태그 입력시 추가되기
              --> 

              <div>
                <span class="search" id="demo-2">
                  <input class="devin-search tag" type="search" style="font-size:15px;">
                </span>   
                <span @click="tagSearch(index,tag.tag)" :class="{'active': itemsContains(index)}" v-for="(tag, index) in tags" v-bind:key="index" class="tag" style="font-size:20px; margin:10px;">
                  #{{tag.tag}}
                </span>
              </div>
              <!-- end tag search bar -->
           

              <!-- 로그인했을때 전체/이웃글 스위칭 버튼 (1:전체글보기/ 2:이웃글보기)-->
              <el-tooltip  class="pull-right" v-if="seq_user>0" :content="disclosure?'전체 글 보기':'이웃 글 보기'" placement="right">
              <el-switch @change="neighborSearch" v-model="disclosure" on-color="#13ce66" off-color="#ff4949" :on-value="2" :off-value="1"> </el-switch>
              </el-tooltip>

              <br><br><br>

              <!-- start project list -->
              <!-- 미구현 목록
                  1. 프로젝트의 어떤 태그를 가져올지
              --> 
              <el-carousel :interval="4000" type="card" height="400px">
                <el-carousel-item v-for="(project, index) in projectList" :key="index">
                  <div class="well-media">
                    <div class="vendor">
                      <img v-if="project.img_url" class="img-responsive-media" :src="project.img_url">
                      <img v-else class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png">
                    </div>
                    <div class="video-text">
                      <h2 style="font-weight: bold; margin-bottom:5px;" @click="goDetailProject(project.seq)">{{ project.title }}</h2>
                    </div>
                    <div class="tag-nest" style="block:inline; padding:10px 5px 10px 5px;" > 

                      <span v-for="(tag,index) in projectTag[index]" :key="index">
                      <span class="tag" style="font-size:17px; margin-right:8px;">#{{tag.tag}}</span>
                     </span>
                      <!-- 여백 -->
                      <span class="tag"></span>

                      <span class="tag-copy"><i class="ti-comment-alt"></i> {{ projectComment[index] }} </span>
                      <span class="tag-copy"><i class="ti-heart"></i> {{ project.like_count }} </span>
                    </div>
                  </div>
                </el-carousel-item>
              </el-carousel>
              <!-- end project list -->
            
            </div>

            <br><br><br>

            <!-- start post list -->
            <!-- 미구현 목록
                1. 포스트의 어떤 태그를 호출 할지 - 일단 등록한 태그 3개로 해놓음

                추가한 부분
                1. 포스트 타이틀 1줄만 출력 : title-1line class 로 css수정
            -->
            <div class="col-sm-8" style="margin: 0 auto; float: none;">
              <div class="row" v-for="(post,index) in this.postList" :key="index">
                <div class="left">
                  <h2 class="title-1line">{{ post.title }}</h2>
                  <ul class="list-inline blog-devin-tag">
                    <li>
                      <a href="#"> <span class="ti-pencil"></span>&nbsp;{{ post.regtime }}</a>
                    </li>
                    <li>
                      <a href="#"> <span class="ti-comment-alt"></span>&nbsp;{{ postComment[index] }}</a>
                    </li>
                    <li>
                      <a href="#"> <span class="ti-heart"></span>&nbsp;{{ post.like_count }}</a>
                    </li>
                  </ul>
                  <p class="content-3line">{{ removeTag(post.content) }}</p>
                  <hr>
                  <p class="pull-left">
                    <span v-for="(tag, index) in postTag[index]" :key="index">
                    <span class="tag" style="font-size:17px; margin-right:8px;">#{{tag.tag}}</span>
                    </span>
                  </p>
                  <button class="btn btn-info pull-right"  @click="goDetailPost(post.seq)">Read More</button>
                  <div style="clear:both;"></div>
                </div>
                <div class="right">
                  <div class="vendor">
                    <img v-if="post.img_url" class="img-responsive-media" :src="post.img_url" alt="">
                    <img v-else class="img-responsive-media" style="margin-top:25px;" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png">
                  </div>
                </div>
                <!-- <hr style="clear:both"> -->
              </div>
            </div>
            <!-- end post list -->          
            <!-- infinite-loading 스피너형식 : default/spiral/circles/bubbles/waveDots-->

            <infinite-loading ref="infiniteLoading" @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
          
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>
<script>
import http from '../util/http-common'
import InfiniteLoading from 'vue-infinite-loading'
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
export default {
  name: 'Main',
  components: {
      InfiniteLoading
  },
  data () {
    return {
      seq_user: this.$store.state.userInfo.seq,
      postList: [],
      projectList: [],
      projectComment: [], 
      postComment: [],
      projectTag: [],
      postTag: [],
      tags: [],
      // 페이지네이션
      limit: 0,
      page: 6, //한 페이지에 불러올 카드 숫자. 추후 수정 가능(3배수)
      // 태그 검색
      searchTags: [],
      activeIndex: [],
      // 이웃 검색
      disclosure: false,
    }
  },
  computed: {
    ...mapGetters([
      'getUserInfo',
      'getIsAdminMode',
      'getIsLogin'
    ]),
    ...mapMutations([
      'setUserInfo',
      'setIsAdminMode',
      'setIsLogin'
    ])
  },
  created(){
    this.getTags();
    this.getPostandproject();
  },
  methods:{
    // 이웃 스위치 바꿀때마다 검색
    neighborSearch(){
      this.limit=0
      this.getPostandproject();
    },
    // 태그 누를때마다 검색
    tagSearch(selected, tag){

      // 태그 선택시 css 바꾸고 searchTags에 추가 (토글)
      var index = this.searchTags.indexOf(tag)
      var idx = this.activeIndex.indexOf(selected)
      if(index<0){
        this.searchTags.push(tag)
        this.activeIndex.push(selected)
      }else{
        this.searchTags.splice(index,1)
        this.activeIndex.splice(idx,1)
      }
     // 선택한 태그로 재검색 (합집합)
    this.limit=0
    this.getPostandproject();
    },
    // 프로젝트와 포스트 검색 초기화
    getPostandproject(){
      this.projectComment= [] 
      this.postComment= []
      this.projectTag= []
      this.postTag= []
      if(this.$refs.infiniteLoading){
        this.$refs.infiniteLoading.stateChanger.reset(); 
      }
      http.post('/project/feed', {
      seq_user:this.seq_user ,
      disclosure: this.disclosure?2:1,
      offset: 0,
      limit: 10,
      tag: this.searchTags.length==0?null:this.searchTags
    })
    .then(({data}) => {
      this.projectList = data
      this.getprojectCommentTag(data)
    })
    http
    .post('/post/feed', {
       seq_user:this.seq_user , 
       disclosure: this.disclosure?2:1, 
       offset:0, 
       limit:this.page ,
       tag: this.searchTags.length==0?null:this.searchTags
    })
    .then(({data}) => {
      this.postList = data
      this.getpostCommentTag(data)
    })
    },
    getTags(){
      // if(this.seq_user==''){
      //   // 모든 태그 띄워주기 or 인기 태그 띄워주기 or 최신 태그 띄워주기
      //   http.get('usertag/feed')
      //   .then(({data}) => {
      //     this.tags=data;
      //   });
      // }
      // else{
        http.get('usertag/')
        .then(({data}) => {
          this.tags=data;
        });
      // }
    },
    // 인피니트로딩
    infiniteHandler($state){
      http.post('post/feed', {  seq_user:this.seq_user , disclosure: this.disclosure?2:1, offset:this.limit+this.page, limit:this.page,tag: this.searchTags.length==0?null:this.searchTags })
      .then(({ data }) => {
        // 스크롤 페이징을 띄우기 위한 시간 1초
        setTimeout(()=>{
          if(data.length){
            this.getpostCommentTag(data)
            this.postList = this.postList.concat(data);
            $state.loaded()
            this.limit +=this.page
            if(this.postList.length/this.page == 0){
              $state.complete();
            }
          }
          else{
            $state.complete();
          }
        },1000)
      })
    },
    // 프로젝트로부터 코멘트 개수와 태그 불러오기
    getprojectCommentTag(data){
      for(var i=0; i<data.length; i++){
      // 코멘트
        http.get('postcomment/count/'+data[i].seq)
        .then(({data}) => {
          this.projectComment.push(data);
        });
        // 태그
        http.get('posttag/'+data[i].seq)
        .then(({data}) => {
          this.projectTag.push(data.slice(0,3));
        });
      }
    },
    // 포스트로부터 코멘트 개수와 태그 불러오기
    getpostCommentTag(data){
      for(var i=0; i<data.length; i++){
      // 코멘트
        http.get('postcomment/count/'+data[i].seq)
        .then(({data}) => {
          this.postComment.push(data);
        });
        // 태그
        http.get('posttag/'+data[i].seq)
        .then(({data}) => {
          this.postTag.push(data.slice(0,3));
        });
      }
    },
    removeTag(text){
      text = text.replace(/<br\/>/ig, "\n")
      text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")
      // text = text.replace(/<(\/b|b)([^>]*)>/gi,""); 
      return text
    },
    goDetailProject(seq){
      this.$router.push(`/blog/project/${seq}`)
    },
    goDetailPost(seq){
      this.$router.push(`/blog/post/${seq}`)
    },
    itemsContains(n) {
      return this.activeIndex.indexOf(n) > -1
    }
  }
}
</script>
<style lang="scss" scoped>
.row {
  padding-top: 40px;
  padding-bottom: 40px;
}
.search {
  float: none;
  position: relative;
  top: 0px;
  left: 0px;
  z-index: 0;
  &:after {
    content: "\E610";
    z-index: 0;
    display: block;
    color: #fff;
    position: absolute;
    top: 3px;
    left: 21px;
    font-family: 'themify';
    speak: none;
    font-style: normal;
    font-weight: normal;
    font-variant: normal;
    font-size: 14px;
    text-transform: none;
    line-height: 1;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.left{
  width: 60%;
  float: left;
  box-sizing: border-box;
}
.right{
  width: 35%;
  float: right;
  box-sizing: border-box;
}
.blog-list-nest{
  float: left;
}
.tag-copy{
  float: right;
  padding-top: 8px;
  margin-right: 5px;
}
.content{
  padding: 0px;
}
.carousel{
  top: 65px
}
.img-resize{
  width:100%;
  height:100%;
}
.title-1line{
  /* 한 줄 자르기 */
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  /* 1줄만 보이게 */
  white-space: normal;
  line-height: 2;
  height: 2em;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.content-3line{
  /* 한 줄 자르기 */
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  /* 3줄만 보이게 */
  white-space: normal;
  line-height: 2;
  height: 6em;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.active {
  background-color:    #DDDDDD;
}
</style>