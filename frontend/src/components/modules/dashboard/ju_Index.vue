<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <section class="content">
        <div class="box">
          <div class="box-body" style="min-height:400px;">
            
            <div class="col-sm-12">
              <!-- start banner carousel --> 
              <!-- 메인 피드의 인포메이션 캐러셀 (2초마다 자동 슬라이드) 
              https://mdbootstrap.com/docs/vue/advanced/carousel/ 참고
              사용시 npm install mdbvue 설치 필요 -->
              <!-- <mdb-carousel :items='imgs' :interval='2000' indicators controlls></mdb-carousel> -->

              <el-carousel indicator-position="outside" height='500px'>
                <el-carousel-item v-for="item in 3" :key="item">

                </el-carousel-item>
              </el-carousel>
              <!-- end banner carousel -->
            
              <br><br><br>
              
              <!-- start tag search bar -->
              <div>
                <span class="search" id="demo-2">
                  <input class="devin-search" type="search">
                </span>
                <!-- tags에 태그 리스트 받아온뒤 포문으로 출력-->          
                <span v-for="(tag, index) in tags" v-bind:key="index" class="tag" style="font-size:20px; margin:10px;">
                  #{{tag}}
                </span>
              </div>
              <!-- end tag search bar -->

              <br><br><br>
              
              <!-- start project list -->
              <div class="col-sm-8" style="margin: 0 auto; float: none;">
              <el-carousel :interval="4000" type="card" height="400px">
                <el-carousel-item v-for="project in projectList" :key="project.seq">
                  <div class="well-media">
                    <div class="vendor">
                      <img v-if="post.img_url" class="img-responsive-media" src=post.img_url alt="">
                      <img v-else class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png">
                    </div>
                    <div class="video-text">
                      <h2 style="font-weight: bold; margin-bottom:5px;">{{ project.title }}</h2>
                    </div>
                    <div class="tag-nest" style="block:inline; padding:10px 5px 10px 5px;" > 
                      <span class="tag">#SpringBoot</span>
                      <span class="tag">#Vue.js</span>
                      <span class="tag">#css</span> 
                    </div>
                  </div>
                </el-carousel-item>
              </el-carousel>
              </div>
              <!-- end project list -->

              <br><br><br>

              <!-- 미구현 목록
                  1. 포스트의 주요 태그 호출
                  2. 포스트 댓글 수 표시
                  3. 본문 3~4줄 넘어가면 줄이기
                  4. Read More 클릭시 연결
              -->
              <!-- start post list -->
              <div class="col-sm-8" style="margin: 0 auto; float: none;">
                <div class="blog-list-nest" v-for="post in this.postList" :key="post.seq">
                  <div class="blog-list-content">
                    <div class="left">
                      <h2>{{ post.title }}</h2>
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
                      <p>{{ post.content }}</p>
                      <hr>
                      <p class="pull-left">
                        <el-tag type="gray">#Vue.js</el-tag>
                        <el-tag type="gray">#Spring</el-tag>
                        <el-tag type="gray">#Django</el-tag>
                      </p>
                      <button class="btn btn-info pull-right">Read More</button>
                      <div style="clear:both;"></div>
                    </div>
                    <div class="right">
                      <div class="vendor">
                        <img v-if="post.img_url" class="img-responsive-media" src=post.img_url alt="">
                        <img v-else class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png">
                      </div>
                    </div>
                  </div>
                </div>
                <br><hr><br>
              </div>
              <!-- end post list -->
            </div>
          </div>
        </div>
      </section>
    </div>
  </transition>
</template>
<script>
import http from '../../../util/http-common'
export default {
  name: 'Main',
  data () {
    return {
      postList: [],
      projectList: [],
      projectComment: [],
      postComment: [],
      tags: ['java', 'spring', 'python', 'aws', 'ml', 'database', 'blockchain', 'javascript', 'tensorflow'],
    }
  },
  created(){
    http
    .post('/project/feed', {
      seq_user: 0,
      disclosure: 1,
      offset: 0,
      limit: 10
    })
    .then(({data}) => {
      this.projectList = data
      console.log(this.projectList)
    })
    http
    .post('/post/feed', {
      seq_user: 0,
      disclosure: 1,
      offset: 0,
      limit: 10
    })
    .then(({data}) => {
      this.postList = data
      for(var i=0; i<data.length; i++){
        http
        .get('postcomment/'+this.postList[i].seq)
        .then(({data}) => {
            this.postComment.push(data.length);
        });
      }
    })
  }
}
</script>
<style lang="scss">

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
    top: 2px;
    left: 14px;
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

input.devin-search {
  outline: none;
  &[type=search] {
    -webkit-appearance: textfield;
    -webkit-box-sizing: content-box;
    font-family: inherit;
    font-size: 100%;
  }
  &::-webkit-search-decoration,
  &::-webkit-search-cancel-button {
      display: none;
  }
  &[type=search] {
    background: #ddd;
    border: solid 0px #ddd;
    padding: 10px 10px 10px 32px;
    width: 55px;
    -webkit-border-radius: 10em;
    -moz-border-radius: 10em;
    border-radius: 10em;
    -webkit-transition: all .5s;
    -moz-transition: all .5s;
    transition: all .5s;
    position: relative;
    &:focus {
      width: 130px;
      background-color: #eee;
      border: solid 1px #eee;
    }
  }
  &:-moz-placeholder,
  &::-webkit-input-placeholder {
    color: #999;
  }
}

#demo-2 input {
  &[type=search] {
    width: 20px;
    padding-left: 10px;
    color: transparent;
    cursor: pointer;
    &:hover {
        background-color: #ccc;
    }
    &:focus {
      width: 330px;
      padding-left: 32px;
      color: #000;
      background-color: #eee;
      cursor: auto;
    }
  }
  &:-moz-placeholder,
  &::-webkit-input-placeholder {
    color: transparent;
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

</style>