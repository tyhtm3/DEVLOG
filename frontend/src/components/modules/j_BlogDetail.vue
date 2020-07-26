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
                    <h1>{{title}}</h1>
                  </div>
                </div>
                <!-- end hero -->
                <div class="description-profile">
                  <div class="column1">
                  <span>
                    <span> <i class="ti-calendar"></i> {{regtime}} </span>&#124;
                    <!-- <span> <i class="el-icon-date"></i> {{postedDate}} </span>&#124; -->
                    <span> <i class="ti-comment-alt"></i> {{comments}} </span>&#124;
                    <span> <i class="ti-heart"></i> {{like_count}}</span>
                  </span>
                  <span style="float: right;">
                    <span><a href="#">수정</a></span> &#124;
                    <span><a href="#" onclick="deleteEntry(); return false;">삭제</a></span>
                  </span>
                  </div>
                </div>
              </div>
              <!-- end profile -->
              <!-- start content -->
              <div class="box" style="padding:30px;">
              {{content}}
              </div>
              <!-- end content -->
              <!-- start tag -->
              <div class="box" style="padding:30px;">
                <!-- tags에 태그 리스트 받아온뒤 포문으로 출력-->
                <!-- 사이즈 정돈 필요 -->
                <span v-for="(tag, index) in tags" v-bind:key="index">
                <span class="tag" >#{{tag}} </span>
                </span>
              </div>
              <!-- end tag -->
              <!-- start comment -->
              <div class="comment-nest">
                  <el-input type="textarea" :rows="2" placeholder="바른말 고운말~ 기분 좋아지는 댓글을 작성해주세요 *^^*" v-model="textarea"> </el-input>
                  <br><br>
                  <el-button style="float:right">댓글 등록</el-button><br><br>
                  <hr>
                  <ul class="media-list">
                    <li class="media">
                        <a class="pull-left" href="#"> <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" src="http://api.randomuser.me/portraits/thumb/women/21.jpg" style="width: 40px; height: 40px;"> </a>
                        <div class="media-body">
                          <div class="social-profile">
                              <h3> 
                                <a class="link-comment" href="#">손명묭1호팬</a>
                                <span><i class="entypo-globe"></i>&nbsp;1 hours ago</span>
                              </h3> 
                          </div>
                          <p>잘 보고갑니다~</p>
                        </div>
                    </li>
                  </ul>
                  <hr>
                  <ul class="media-list">
                      <li class="media">
                          <a class="pull-left" href="#"> <img class="media-object img-circle" data-src="holder.js/64x64" alt="64x64" src="http://api.randomuser.me/portraits/thumb/women/9.jpg" style="width: 40px; height: 40px;"> </a>
                          <div class="media-body">
                            <div class="social-profile">
                                <h3> 
                                  <a class="link-comment" href="#">손명묭2호팬</a>
                                  <span><i class="entypo-globe"></i>&nbsp;22 hours ago</span>
                                </h3> 
                            </div>
                            <p>명묭언니 팬이에요~~~~</p>
                          </div>
                      </li>
                  </ul>
              </div>
              <!-- end comment -->
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
  export default {
    name: 'y_blog-main',
    components: {
    },
    created(){
      this.getPostInfo();
    },
    data: function () {
        return { 
          seq:'1',
          seq_blog:'',
          title:'제목은 깃 브랜치 사용 방법',
          regtime: '2000-01-01 01:01:01',
          // 댓글이 disclosure인가요?!
          disclosure: '12',
          comments: '17',
          like_count: '523',
          content:'내용없다',
          // tags : 태그 리스트 불러오는거 없나!?
          tags: ['java', 'spring', 'python', 'aws', 'ml', 'database', 'blockchain', 'javascript', 'tensorflow'],
        }
    },
    methods: {
      getPostInfo(){
        this.$http.get('/api/post/'+this.seq)
        .then(({data}) => {
          alert("들어옴");
            this.seq_blog=data.seq_blog;
            this.title = data.title;
            this.regtime = data.regtime;
            // 댓글이 disclosure인가요?!
            this.disclosure = data.disclosure;
            this.comments = data.comments;
            this.like_count = data.like_count;
            this.content = data.content;
         })
      },//getUserInfo
   },
  }
</script>