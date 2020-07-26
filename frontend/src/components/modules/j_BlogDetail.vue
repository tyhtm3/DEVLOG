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
              <!-- start body -->
                <div class="box" style="padding:30px;">
                {{content}}
                </div>
                <!-- /.content -->
              <!-- end body -->
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