<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 포스트 출력 -->
            <div class="delete" @click="deletePost" v-show="this.$store.state.settingButtonVisible">
                <i class="ti-trash"></i> 삭제
            </div>
            <div class="row">
                <div class="col-md-4" v-for="(post,index) in postList" :key="index">
                    <span v-show="$store.state.settingButtonVisible">
                        <input class="delete-box" :id=post.seq type="checkbox" :value=post.seq v-model="deleteList" />
                        <label :for=post.seq></label>
                    </span>
                    <div class="well-media" @click="goDetail(post.seq)" style="cursor:pointer;">
                        <div class="vendor">
                            <img v-if="post.img_url" class="img-responsive-media" :src="post.img_url" alt="">
                            <img v-else class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                        </div>
                        <div class="video-text">
                            <h2 class="title-1line" style="font-weight: bold; margin-bottom:10px;">{{post.title}}</h2>
                            <p class="content-3line" style="color:black;">{{ removeTag(post.content) }}</p>
                        </div>
                        <div class="tag-nest" style="block:inline">
                            
                            <!-- 태그 3개만 갖고오기--> 
                            <span v-for="(tag,index) in tag[index]" :key="index">
                            <span class="tag">#{{tag.tag}}</span>
                            </span>
                            <!-- 여백 -->
                            <span class="tag"></span>

                            <!-- 좋아요, 코멘트 수 -->
                            <span class="tag-copy" style="float:right"> <i class="ti-heart"></i> {{post.like_count}} </span>
                            <span class="tag-copy" style="float:right"> <i class="ti-comment-alt"></i> {{comment[index]}} </span> 
                        </div>
                    </div>
                </div>
            </div>
            <!-- infinite-loading 스피너형식 : default/spiral/circles/bubbles/waveDots-->
            <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
        </section>
    </transition>
</template>
<script>
import http from '../util/http-common'
import InfiniteLoading from 'vue-infinite-loading'
import store from '../store'
export default {
    name: 'postList',
    components: {
        InfiniteLoading
    },
    data(){
        return{
            // 방문한 블로그 일단은 무조건 현재 블로그번호로 지정, 이후에 방문 블로그 번호로 설정
            seq_blog: this.$store.state.userInfo.seq,
            seq_user: this.$store.state.userInfo.seq,
            postList: [],
            comment: [],
            tag:[],
            counter: 0,
            // 페이지네이션
            limit: 0,
            page: 6, //한 페이지에 불러올 카드 숫자. 추후 수정 가능(3배수)
            deleteList: [],
            postVisible: [
            ],
            deleteSuccess: true,
        }
    },
    created(){	 
      this.getpostList();
    },
    methods:{
        removeTag(text){
        text = text.replace(/<br\/>/ig, "\n");
        text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
        return text
        },
        goDetail(seq){
            this.$router.push(`/blog/post/${seq}`)
        },
        // 페이지네이션 하기 전 처음 페이지에 뿌려줄 카드 불러오기
        getpostList(){
            http.post('post/blog', { seq_blog:this.seq_blog, offset:0, limit:this.page})
            .then(({ data }) => {
                if(data.length){
                this.postList = data;
                this.getpostCommentTag(data)}
            })
        },
        // 인피니트로딩
        infiniteHandler($state){
            http.post('post/blog', { seq_blog:this.seq_blog, offset:this.limit+this.page, limit:this.page})
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
                    }else{
                        $state.complete();
                    }
                },1000)
            })
        },
        // 포스트로부터 코멘트 개수와 태그 불러오기
        getpostCommentTag(data){
            for(var i=0; i<data.length; i++){
                this.postVisible[i] = true;
                // 코멘트
                http.get('postcomment/count/'+data[i].seq)
                .then(({data}) => {
                this.comment.push(data);
                });
                // 태그
                http.get('posttag/'+data[i].seq)
                .then(({data}) => {
                this.tag.push(data.slice(0,3));
                });
            }   
        },
        deletePost(){
            if(this.deleteList.length === 0){
                this.$message({
                    type: 'info',
                    message: '선택한 포스트가 없습니다.',
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
                        .delete('post/'+this.deleteList[i])
                        .then(({data}) => {
                            this.deleteList = []
                            this.getpostList()
                        })
                        .catch((error) => {
                            this.deleteSuccess = false
                        })
                    }
                    if(this.deleteSuccess){
                        this.$message({
                            type: 'success',
                            message: '선택한 포스트가 삭제 되었습니다.'
                        });
                    }
                    this.deleteSuccess = true
                })
                .catch(() => {
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
    word-spacing: 2px;
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