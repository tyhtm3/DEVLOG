<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 포스트출력 -->
            <div class="row">
                    <div class="col-md-4" v-for="(project,index) in projectList" :key="index">
                    <div class="well-media">
                    <a href="#">
                        <div class="vendor">
                            <img class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                            <!-- <a class="fancybox" rel="group" href="#"> <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt=""> </a> -->
                        </div>
                        <div class="video-text">
                            <!-- {{project}} -->
                            <h2 style="font-weight: bold; margin-bottom:10px;">{{project.title}}</h2>
                            <p class="content-3line" style="color:black;">{{project.content}}</p>
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
        </section>
    </transition>
</template>
<script>
  import http from '../../util/http-common'
  export default {
    name: 'ji_ProjectPage',
    data(){
        return{
            // 방문한 블로그 일단은 무조건 현재 블로그번호로 지정, 이후에 방문 블로그 번호로 설정하는거 해야함
            seq_blog: this.$store.state.userInfo.seq,
            seq_user: this.$store.state.userInfo.seq,
            projectList: [],
            comment: [],
            tag:[],
            counter: 0
        }
    },
    created(){	 
      this.getprojectList();
    },
    methods:{
        getprojectList(){
            http.post('project/blog', { seq_user:this.seq_user , seq_blog:this.seq_blog, offset:0, limit:10})
            .then(({ data }) => {
                this.projectList = data;
                for(var i=0; i<this.projectList.length; i++){
                    // 코멘트
                    http.get('postcomment/'+this.projectList[i].seq)
                     .then(({data}) => {
                        this.comment.push(data.length);
                    });
                    // 태그
                    http.get('posttag/'+this.projectList[i].seq)
                     .then(({data}) => {
                        this.tag.push(data);
                    });
                }
            })
        }
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