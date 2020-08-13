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

      <br>

      <div class="box">
        <div class="box-body" style="min-height:400px;">
          <div class="col-sm-12">
            <br>
            <div class="col-sm-8" style="margin: 0 auto; float: none;">
              <!-- 유저태그 관리(비로그인시 화면단에서만 등록,삭제 가능) -->
              <div>
                <span class="search" id="demo-2">
                  <input v-on:keyup.enter="addTag"  v-model="inputtag" class="devin-search tag" type="search" style="font-size:15px;">
                </span> 

                <div v-for="(tag, index) in tags" v-bind:key="index" style="display:inline-block;" @mouseenter="showCloseButton(index)" @mouseleave="hideCloseButton(index)">
                <!-- <span class="cover" > -->
                <span @click="tagSearch(index,tag.tag)" :class="{'active': itemsContains(index)}" class="tag" style="font-size:20px; margin:10px;">
                  #{{tag.tag}}
                </span>
                <span @click="deleteTag(index)" class="hideDeleteButton ti-close pull-top pull-right" style="font-size:3px;color:#333333;padding:0px;margin-left:-30px;"></span>
                <!-- </span> -->
                </div>

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
              <div v-if="projectList.length==0" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px; text-align:center">
                조건에 일치하는 프로젝트가 존재하지 않습니다.
              </div>
              <div v-else >
              <el-carousel  :interval="4000" type="card" height="440px">
                <el-carousel-item v-for="(project, index) in projectList" :key="index" >
                  <div class="well-media">
                    <div class="vendor">
                      <!-- 중앙일 때 : transform: translateX(125.25px) scale(1); -->
                      <img v-if="project.img_url" class="img-responsive-media" :src="project.img_url" @click="goDetailProject(project.seq)">
                      <img v-else class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" @click="goDetailProject(project.seq)">
                    </div>
                    <div class="video-text">
                      <h2 style="font-weight: bold; margin-bottom:5px;" @click="goDetailProject(project.seq)">{{ project.title }}</h2>
                    </div>
                    <div class="tag-nest" style="block:inline; padding:10px 5px 10px 5px;" >
                      <span class="tag-nest-detail">


                      <div @click="tagSearch(tag.tag)" v-for="(tag,index) in project.tags" :key="index" style="display:inline-block;" >
                      <span class="tag" :class="{'active': itemsContains(tag.tag)}" style="font-size:17px; margin-right:8px;float:left;">
                        #{{tag.tag}}
                      </span>
                      </div>
                      <span class="tag donotshow"></span>


                      </span>
                      <span class="tag-copy" @click="goDetailProject(project.seq)" style="display:inline-block;"><i class="ti-heart"></i> {{ project.like_count }} </span>
                      <span class="tag-copy" @click="goDetailProject(project.seq)" style="display:inline-block;"><i class="ti-comment-alt"></i> {{ project.comment_count }} </span>
                    </div>
                  </div>
                </el-carousel-item>
              </el-carousel>
              </div>
              <!-- end project list -->
            </div>

            <br>
            <hr>
            <br>

            <!-- start post list -->
            <!-- 미구현 목록
                1. 포스트의 어떤 태그를 호출 할지 - 일단 등록한 태그 3개로 해놓음

                추가한 부분
                1. 포스트 타이틀 1줄만 출력 : title-1line class 로 css수정
            -->
            <div v-if="postList.length>0" class="col-sm-8" style="margin: 0 auto; float: none;">
              <div class="row" v-for="(post,index) in postList" :key="index">
                <!-- 추후에 시간되면 left 전체에 @click 걸고 태그 버튼만 z인덱스 주기-->
                <div class="left">
                  <div class="left-part" @click="goDetailPost(post.seq)">
                    <h2 class="title-1line">{{ post.title }}</h2>
                    <ul class="list-inline blog-devin-tag">  
                      <li>
                      <span class="tag-copy2" ><i class="ti-pencil"></i> {{ post.regtime }}&nbsp;</span>
                      <span class="tag-copy2" >&nbsp;<i class="ti-comment-alt"></i>&nbsp;{{ post.comment_count }} </span>
                      <span class="tag-copy2" >&nbsp;<i class="ti-heart"></i>&nbsp;{{ post.like_count }} </span>
                      </li>
                      <!-- <li>
                        <a href="#"> <span class="ti-pencil"></span>{{ post.regtime }}</a>
                      </li>
                      <li>
                        <a href="#"> <span class="ti-comment-alt"></span>{{ post.comment_count }}</a>
                      </li>
                      <li>
                        <a href="#"> <span class="ti-heart"></span>{{ post.like_count }}</a>
                      </li> -->
                    </ul>
                    <p class="content-3line">{{ removeTag(post.content) }}</p>
                  </div>
                  <hr>

                  <p class="pull-left posttag-nest">
                    <span v-for="(tag, index) in post.tags" :key="index" >
                    <span @click="tagSearch(tag.tag)" :class="{'active': itemsContains(tag.tag)}" class="tag" style="font-size:17px; margin-right:8px;">#{{tag.tag}}</span>
                    </span>
                  </p>

                  <!-- <button class="btn btn-info pull-right"  @click="goDetailPost(post.seq)">Read More</button> -->
                  <div style="clear:both;"></div>
                </div>

                <div class="right" @click="goDetailPost(post.seq)">
                  <div class="vendor">
                    <img v-if="post.img_url" class="img-responsive-media" :src="post.img_url" alt="">
                    <img v-else class="img-responsive-media" style="margin-top:25px;" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png">
                  </div>
                </div>
                <!-- <hr style="clear:both"> -->
              </div>
            <infinite-loading ref="infiniteLoading" @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
            </div>
            <!-- end post list -->  
            <div v-else style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px; text-align:center">
                조건에 일치하는 글이 존재하지 않습니다.
            </div>> 
            
            <!-- infinite-loading 스피너형식 : default/spiral/circles/bubbles/waveDots-->
          
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
      fromDetailSearchTag: this.$store.state.searchTag,
      // 태그 추가
      inputtag: '',
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
    this.getFromDetailSearchTag();
    this.getPostandproject();
  },
  watch: { 
      getUserInfo() {
            this.seq_user = this.getUserInfo.seq
            this.getTags();
            this.getPostandproject();
        }
  },
  methods:{
    // 이웃 스위치 바꿀때마다 검색
    neighborSearch(){
      this.limit=0
      this.getPostandproject();
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
    }, {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }})
    .then(({data}) => {
      this.projectList = data;
      this.getPostandproject();
      // this.getprojectCommentTag(data)
    })
    http
    .post('/post/feed', {
       seq_user:this.seq_user , 
       disclosure: this.disclosure?2:1, 
       offset:0, 
       limit:this.page ,
       tag: this.searchTags.length==0?null:this.searchTags
    }, {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }})
    .then(({data}) => {
      this.postList = data
    })
    },
    // 유저태그 가져오기
    getTags(){
      this.tags=[]
      if(this.seq_user==''){
        // 모든 태그 띄워주기 or 인기 태그 띄워주기 or 최신 태그 띄워주기
        http.get('usertag/feed', {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }})
        .then(({data}) => {
          this.tags=data;
        });
      }
      else{
        http.get('usertag/', {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }})
        .then(({data}) => {
          this.tags=data;
        });
      }
    },
    //디테일 페이지로부터 넘어온 태그 검색
    getFromDetailSearchTag(){
        if(this.fromDetailSearchTag!=null){
        this.inputtag = this.fromDetailSearchTag
        this.addTag()
        this.searchTags.push(this.fromDetailSearchTag)
        this.fromDetailSearchTag = null
        this.$store.commit('setSearchTag',null)
        }
    },
    // 인피니트로딩
    infiniteHandler($state){
      http.post('post/feed', {  seq_user:this.seq_user , disclosure: this.disclosure?2:1, offset:this.limit+this.page, limit:this.page,tag: this.searchTags.length==0?null:this.searchTags }, {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }})
      .then(({ data }) => {
        // 스크롤 페이징을 띄우기 위한 시간 1초
        setTimeout(()=>{
          if(data.length){
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
    // getprojectCommentTag(data){
    //   // console.log("this.projectList");
    //   // console.log(this.projectList);
    //   // console.log("데이터길이" + datad.length)
    //   for(var i=0; i<data.length; i++){
    //     this.getProjectComments(i)
    //     this.getProjectTags(i)
    //   }
    //   this.projectComment.push(null);
    //   this.projectTag.push(null);
    // },
    // getProjectComments(i){
    //   if(i<this.projectList.length){
    //     http.get('postcomment/count/'+this.projectList[i].seq, {headers: {
    //     'Content-type': 'application/json',
    //     Authorization : this.$store.state.token,
    //     }})
    //     .then(({data}) => {
    //     // console.log(i+"번째 댓글: ");
    //     this.projectComment[i] = data;
    //     // console.log(data);
    //     // console.log(this.projectComment[i]);
    //     });
    //   }
    // },
    // getProjectTags(i){
    //   if(i<this.projectList.length){
    //   http.get('posttag/'+this.projectList[i].seq, {headers: {
    //     'Content-type': 'application/json',
    //     Authorization : this.$store.state.token,
    //     }})
    //     .then(({data}) => {
    //     // console.log(i+"번째 글 태그: ");
    //     this.projectTag[i] = data.slice(0,3);
    //     // console.log(data);
    //     // console.log(this.projectTag[i]);
    //     });
    //   }
    // },
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
    itemsContains(tag) {
      return this.searchTags.indexOf(tag) > -1
    },
    addTag(){
        var tag = this.inputtag
        if(this.inputtag.length==0){
          this.$message.warning('검색할 태그를 입력해 주세요.')
        }else{
          // 로그인 - 유저태그에 등록
          if(this.seq_user>0){
              http.post('/usertag', {
              tag: this.inputtag
              }, {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }})
              .then(({data}) => {
                this.tags.push({tag : tag, seq: data})
                })
              .catch((error) => {
                if(error.response.status=='401'){
                  if(this.fromDetailSearchTag!=null){
                  this.$message({
                    type: 'warning',
                    message: '중복되는 태그입니다.'
                  });}
                }
          })
          }
          //비로그인 - 화면에만 띄워줌
          else{
            let isDuplicatedTag = false
            for(var i=0;i<this.tags.length;i++)
              if(this.tags[i].tag == tag)
                isDuplicatedTag = true

            if(isDuplicatedTag)
              this.$message({
                    type: 'warning',
                    message: '중복되는 태그입니다.'
                  });
            else
              this.tags.push({tag : tag})
          }
          this.inputtag=''
        }
    },
    deleteTag(index) {
      // 로그인 - 유저태그에서 삭제 : 비로그인 - 화면에서만 삭제
      if(this.seq_user>0){
        http.delete('/usertag/'+this.tags[index].seq, {headers: {
        'Content-type': 'application/json',
        Authorization : this.$store.state.token,
        }}).then(({data}) => {
        this.$message({
                type: 'success',
                message: '관심 태그가 삭제되었습니다.'
              });   
        // var idx = this.searchTags.indexOf(this.tags[index].tag)
        // this.searchTags.splice(idx,1)      
        })
      }
      this.tags.splice(index,1)   
    },
    showCloseButton(index){
      $(".hideDeleteButton").eq(index).show();
    },
    hideCloseButton(index){
      $(".hideDeleteButton").eq(index).hide();
    }
  }
}
</script>
<style lang="scss" scoped>
.row {
  padding: 40px;
  // box-shadow: 2px 2px 2px rgba(226, 223, 223, 0.4);
  // margin: 2px;
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
  // &[type=search] {
  //   }
}
.well-media{
  margin:10px;
  border-radius: 3px;
  box-shadow: 7px 7px 3px rgba(204, 204, 204, 0.144);
  z-index: -3;
}

.well-media:hover{
    box-shadow: 8px 8px 6px  rgba(163, 163, 163, 0.404);
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

// .el-carousel__item:nth-child(2n) {
//   background-color: none;
//   // #d3dce6;
//   //#99a9bf
//   // box-shadow: 15px 15px 15px rgba(146, 146, 146, 0.4);
// }

// .el-carousel__item:nth-child(2n+1) {
//   // background-color: #d3dce6;
//   background-color: none;
// }
 
.el-carousel__item{
  background-color: none;
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
.tag-copy2{
  // float: right;
  padding-top: 8px;
  margin-right: 5px;
  vertical-align: middle;
}
.ul{
  float: right;
  // padding-top: 8px;
  // margin-right: 5px;
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
  margin-bottom: 0px;
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
.row:hover{
  box-shadow: 15px 15px 15px rgba(121, 106, 106, 0.4);
}
.blog-devin-tag li{
  margin-right: 0px;
  padding-top: 0px !important
}
.ti-close{
  cursor:pointer;
}
.hideDeleteButton{
  display:none;
  // visibility:hidden;
}
.cover{
  margin-bottom: 5px;
  margin-top: 5px;
}
.tag{
  line-height:50px;
}
.tag:hover{
  box-shadow: 1px 1px 3px rgba(199, 199, 199, 0.4);
}
.donotshow{
  visibility:hidden;
}
.left > .posttag-nest{
  white-space:nowrap; 
  display:inline-block; 
  width:100%; 
  overflow:scroll;
}
.posttag-nest::-webkit-scrollbar {
  width: 7px;
}
.posttag-nest::-webkit-scrollbar-thumb {
  width: 1px;
  background-color:  rgb(212, 211, 211);
  border-radius: 30px;
  background-clip: padding-box;
  border: 7px solid transparent;
}
.posttag-nest::-webkit-scrollbar-track {
  background-color: transparent;
  border-radius: 30px;
}
</style>




<style lang="scss">

.tag-copy{
  background: transparent;
  border-radius: 10px;
  padding: 3px 0px;
  font-size: 14px;
  margin-right: 5px;
  line-height: 50px;
  float: right;
}
.tag-nest-detail{
  white-space:nowrap; 
  display:inline-block; 
  width:70%; 
  overflow:scroll;
}
// .tag-nest-detail::-webkit-scrollbar {
//     display: none;
// }
.tag-nest-detail::-webkit-scrollbar {
  width: 7px;
}
.tag-nest-detail::-webkit-scrollbar-thumb {
  width: 1px;
  background-color: rgb(212, 211, 211);
  border-radius: 30px;
  background-clip: padding-box;
  border: 7px solid transparent;
}
.tag-nest-detail::-webkit-scrollbar-track {
  background-color: transparent;
  border-radius: 30px;
  // box-shadow: inset 0px 0px 3px transparent;
}
</style>