<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 포스트출력 -->
            <div class="row">
                    <div class="col-md-4" v-for="(project,index) in projectList" :key="index">
                    <div class="well-media">
                    <a href="#" @click="goDetail(project.seq)">
                        <div class="vendor">
                            <img class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                            <!-- <a class="fancybox" rel="group" href="#"> <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt=""> </a> -->
                        </div>
                        <div class="video-text">
                            <!-- {{project}} -->
                            <h2 class="title-1line" style="font-weight: bold; margin-bottom:10px;">{{project.title}}{{project.seq}}</h2>
                            <p class="content-3line" style="color:black;">{{project.summary}}</p>
                        </div>
                        <div class="tag-nest" style="block:inline"> 
                           <!-- 태그 3개만 갖고오기--> 
                            <span v-for="(tag,index) in tag[index].slice(0,3)" :key="index">
                            <span class="tag">#{{tag.tag}}</span>
                            </span>
                            <!-- 여백 -->
                            <span class="tag"></span>

                            <!-- 좋아요, 코멘트 수 -->
                            <span class="tag-copy" style="float:right"> <i class="ti-heart"></i> {{project.like_count}} </span>
                            <span class="tag-copy" style="float:right"> <i class="ti-comment-alt"></i> {{comment[index]}} </span> 
                        </div>
                        <!-- <div class="video-category-bg">
                            <h3>FRONT-END</h3>
                            <a class="link-media pull-right" href="#"> <span class="fontawesome-picture"></span> </a>
                            <div class="triangle-white"></div>
                            <div class="triangle-photo-right"></div>
                        </div> -->
                    </a>
                    </div>
                </div>
            </div>
            <!-- infinite-loading 스피너형식 : default/spiral/circles/bubbles/waveDots-->
            <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
        </section>
    </transition>
</template>
<script>
  import http from '../../util/http-common'
  import InfiniteLoading from 'vue-infinite-loading'
  export default {
    name: 'ji_ProjectPage',
    components: {
        InfiniteLoading
    },
    data(){
        return{
            // 방문한 블로그 일단은 무조건 현재 블로그번호로 지정, 이후에 방문 블로그 번호로 설정하는거 해야함
            seq_blog: this.$store.state.userInfo.seq,
            seq_user: this.$store.state.userInfo.seq,
            projectList: [],
            comment: [],
            tag:[],
            // 페이지네이션
            limit: 0,
            page: 6, //한 페이지에 불러올 카드 숫자. 추후 수정 가능(3배수)
        }
    },
    created(){	 
      this.getprojectList();
    },
    methods:{
        goDetail(seq){
            this.$router.push(`/project-detail/${seq}`)
        },
        // 페이지네이션 하기 전 처음 페이지에 뿌려줄 카드 불러오기
        getprojectList(){
            http.post('project/blog', { seq_user:this.seq_user , seq_blog:this.seq_blog, offset:0, limit:this.page } )
            .then(({ data }) => {
                this.projectList = data;
                this.getprojectCommentTag(data)
            })
        },
        // 인피니트로딩
        infiniteHandler($state){
            http.post('project/blog', { seq_user:this.seq_user , seq_blog:this.seq_blog, offset:this.limit+this.page, limit:this.page })
            .then(({ data }) => {
                // 스크롤 페이징을 띄우기 위한 시간 1초
                setTimeout(()=>{
                    if(data.length){
                        this.getprojectCommentTag(data)
                        this.projectList = this.projectList.concat(data);
                        $state.loaded()
                        this.limit +=this.page
                        if(this.projectList.length/this.page == 0){
                            $state.complete();
                        }
                    }else{
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
                this.comment.push(data);
                });
                // 태그
                http.get('posttag/'+data[i].seq)
                .then(({data}) => {
                this.tag.push(data);
                });
            }   
        },
    }
  }
</script>
<style scoped>
.tag-copy{
    border-radius: 10px;
    font-size: 14px;
    margin-right: 4px;
    line-height: 35px;
    cursor: pointer;
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
</style>