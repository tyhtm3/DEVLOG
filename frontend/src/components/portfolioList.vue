<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 포스트출력 -->
            <div class="delete" @click="deletePortfolio" v-show="this.$store.state.settingButtonVisible">
                <i class="ti-trash"></i> 삭제
            </div>
            <div class="row">
                <div class="col-md-4" v-for="(portfolio,index) in portfolioList" :key="index">
                    <span v-show="$store.state.settingButtonVisible">
                        <input class="delete-box" :id=portfolio.seq type="checkbox" :value=portfolio.seq v-model="deleteList" />
                        <label :for=portfolio.seq></label>
                    </span>
                    <div class="well-media"  style="cursor:pointer;">
                        <div class="vendor">
                            <img class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                            <!-- <a class="fancybox" rel="group" href="#"> <img class="img-responsive-media" src="https://www.bloter.net/wp-content/uploads/2014/05/unreal_1_600.jpg" alt=""> </a> -->
                        </div>
                        <div class="video-text">
                            <!-- {{portfolio}} -->
                            <h2 class="title-1line" style="font-weight: bold; margin-bottom:10px;">{{portfolio.title}}</h2>
                            <p class="content-3line" style="color:black;">{{ removeTag(portfolio.content) }}</p>
                        </div>
                        <div class="tag-nest" style="block:inline"> 
                            <span class="tag">#SpringBoot</span>
                            <span class="tag">#Vue.js</span>
                            <span class="tag">#css</span>
                            <span class="tag-copy" style="float:right"> <i class="ti-heart"></i> {{portfolio.like_count}} </span>
                            <!-- <span class="tag-copy" style="float:right"> <i class="ti-comment-alt"></i> {{comment[index]}} </span>  -->
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </transition>
</template>
<script>
  import http from '../util/http-common'
  export default {
    name: 'portfolioList',
    data(){
        return{
            // 방문한 블로그 일단은 무조건 현재 블로그번호로 지정, 이후에 방문 블로그 번호로 설정하는거 해야함
            seq_blog: this.$store.state.userInfo.seq,
            seq_user: this.$store.state.userInfo.seq,
            offset:0,
            limit:10,
            portfolioList: [],
            comment: [],
            counter: 0,
            deleteList: [],
            deleteSuccess: true
        }
    },
    created(){	 
      this.getportfolioList();
    },
    methods:{
        goDetail(seq){
            this.$router.push(`/blog/portfolio/${seq}`)
        },
        removeTag(text){
        text = text.replace(/<br\/>/ig, "\n");
        text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
        return text
        },
        getportfolioList(){
            // alert(data[0].title)
            http.get('portfolio/blog/'+this.seq_user+'/'+this.seq_blog+'/'+this.offset+'/'+this.limit)
            .then(({ data }) => {
                this.portfolioList = data;
                for(var i=0; i<this.portfolioList.length; i++){
                    // console.log(this.portfolioList[i].seq);
                    http.get('postcomment/'+this.portfolioList[i].seq)
                     .then(({data}) => {
                        // console.log(data.length);
                        this.comment.push(data.length);
                    });
                }
            })
        },
        deletePortfolio(){
             if(this.deleteList.length === 0){
                this.$message({
                    type: 'info',
                    message: '선택한 포트폴리오가 없습니다.',
                });
            }
            else{
                this
                .$confirm('삭제하시겠습니까?', {
                    confirmButtonText: '삭제',
                    cancelButtonText: '취소',
                    type: 'warning'
                })
                .then(() => {
                    for(var i=0; i<this.deleteList.length; i++){
                        http
                        .delete('portfolio/'+this.deleteList[i])
                        .then(({data}) => {
                            this.deleteList = []
                            this.getportfolioList()
                        })
                        .catch((error) => {
                            this.deleteSuccess = false
                        })
                    }
                    if(this.deleteSuccess){
                        this.$message({
                            type: 'success',
                            message: '선택한 프로젝트가 삭제되었습니다.',
                        });
                    }
                    this.deleteSuccess = true
                })
                .catch(() => {
                });
            }
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

.delete{
    font-size: 20px;
    cursor: pointer;
    text-align: right;
    margin-bottom: 50px;
    margin-right: 20px;
}
/* checkbox 디자인 */
input[type="checkbox"] { 
  display: none;
 }
 
input[type="checkbox"] + label {
  display: inline;
  position: absolute;
  top: 25px;
  left: 40px;
  z-index: 1;
  font: 14px/20px 'Open Sans', Arial, sans-serif;
  color: #222;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}
 
input[type="checkbox"] + label:last-child { margin-bottom: 0; }
 
input[type="checkbox"] + label:before {
  content: '';
  display: block;
  width: 20px;
  height: 20px;
  border: 1px solid #6cc0e5;
  position: absolute;
  left: 0;
  top: 0;
  opacity: .6;
  -webkit-transition: all .12s, border-color .08s;
  transition: all .12s, border-color .08s;
}
 
input[type="checkbox"]:checked + label:before {
  width: 10px;
  top: -5px;
  left: 5px;
  border-radius: 0;
  opacity: 1;
  border-top-color: transparent;
  border-left-color: transparent;
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
}
</style>