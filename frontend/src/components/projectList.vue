<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 프로젝트 출력 -->
            <div class="delete" @click="deleteProject" v-show="adminMode">
                <i class="ti-trash"></i> 삭제
            </div>
            <div class="row">
                <div class="col-md-4" v-for="(project,index) in projectList" :key="index" style="height:534px;">
                    <span v-show="adminMode">
                        <input class="delete-box" :id=project.seq type="checkbox" :value=project.seq v-model="deleteList" />
                        <label :for=project.seq></label>
                    </span>
                    <div class="well-media" style="cursor:pointer;">
                        <div class="vendor" style="text-align:center;" @click="goDetail(project.seq)">
                            <div style="height:250px;line-height:250px;vertical-align:middle; text-align:middle; display:inline-flex;">
                                <img v-if="project.img_url" style="width:auto; height:auto; max-width:100%; max-height:250px; display:relative; margin:auto;" class="img-responsive-media" :src="project.img_url" alt="">
                                <img v-else class="img-responsive-media" style="width:auto; height:auto; max-width:100%; max-height:250px; display:relative; margin:auto;" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                            </div>
                        </div>
                        <div class="video-text" @click="goDetail(project.seq)">
                            <h2 class="title-1line" style="font-weight: bold; margin-bottom:10px;">{{project.title}}</h2>
                            <p class="content-3line" style="color:black;">{{project.summary}}</p>
                        </div>
                        
                        <div class="tag-nest" style="block:inline; padding:10px 5px 10px 5px; ">
                        <span class = "tag-nest-detail">

                            <span v-for="(tag,index) in project.tags" :key="index" class="tag" @click="tagSearch(tag.tag)" :class="{'active': itemsContains(tag.tag)}" style="font-size:17px; margin-right:8px;">
                                #{{tag.tag}}
                            </span>
                            <span class="tag donotshow"></span>
                        </span>
                        <span class="tag-copy" style="display:inline-block; float:right;"><i class="ti-heart"></i> {{ project.like_count }} </span>
                        <span class="tag-copy" style="display:inline-block;  float:right;"><i class="ti-comment-alt"></i> {{ project.comment_count }} </span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- infinite-loading 스피너형식 : default/spiral/circles/bubbles/waveDots-->
            <infinite-loading ref="infiniteLoading" @infinite="infiniteHandler" spinner="waveDots">
                <div v-if="projectList.length>0" slot="no-results" style="color:#11212E; padding:50px">마지막 프로젝트입니다.</div>
                <div v-else slot="no-results" style="color:#11212E; padding:50px">조건에 일치하는 프로젝트가 존재하지 않습니다.</div>
                <div slot="no-more" style="color:#11212E; padding:50px">마지막 프로젝트입니다.</div>
            </infinite-loading>
           
        </section>
    </transition>
</template>
<script>
  import http from '../util/http-common'
  import InfiniteLoading from 'vue-infinite-loading'
  export default {
    name: 'projectList',
    components: {
        InfiniteLoading
    },
    props: ['searchTags'],
    watch: { 
      	searchTags(){
            // 선택한 태그로 재검색 (합집합)
            this.limit=0
            this.getprojectList();
        }
    },
    data(){
        return{
            projectList: [],
            // 페이지네이션
            limit: 0,
            page: 6, //한 페이지에 불러올 카드 숫자. 추후 수정 가능(3배수)
            deleteList: [],
            deleteSuccess: true,
            activeIndex: [],
        }
    },
    created() {
        this.getprojectList()
    },
    computed: {
        adminMode() {
            return this.$store.getters.getIsAdminMode
        },
    },
    methods:{
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
        itemsContains(tag) {
        return this.searchTags.indexOf(tag) > -1
        },
        goDetail(seq){
            this.$router.push(`/blog/project/${seq}`)
        },
        // 페이지네이션 하기 전 처음 페이지에 뿌려줄 카드 불러오기
        getprojectList(){
            this.projectList= []
            this.comment= []
            this.tag=[]
            if(this.$refs.infiniteLoading){
            this.$refs.infiniteLoading.stateChanger.reset(); 
            }
            http.get('user/id/'+this.$route.params.id)
            .then(({data})=>{
                http.post('project/blog', { seq_user:data.seq , seq_blog:data.seq, offset:0, limit:this.page , tag:(this.searchTags.length==0?null:this.searchTags) } )
                .then(({ data }) => {
                    this.projectList = data;
                })
            })
        },
        // 인피니트로딩
        infiniteHandler($state){
            http.get('user/id/'+this.$route.params.id)
            .then(({data})=>{
                http.post('project/blog', { seq_user:data.seq , seq_blog:data.seq, offset:this.limit+this.page, limit:this.page,tag:(this.searchTags.length==0?null:this.searchTags) })
                .then(({ data }) => {
                    // 스크롤 페이징을 띄우기 위한 시간 1초
                    setTimeout(()=>{
                        if(data.length){
                            // this.getprojectCommentTag(data)
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
            })
        },
        
        deleteProject(){
             if(this.deleteList.length === 0){
                this.$message({
                    type: 'info',
                    message: '선택한 프로젝트가 없습니다.',
                });
            }
            else{
                this
                .$confirm('총 '+this.deleteList.length+'개의 프로젝트를 삭제하시겠습니까?', {
                    confirmButtonText: '삭제',
                    cancelButtonText: '취소',
                    type: 'warning'
                })
                .then(() => {
                    for(var i=0; i<this.deleteList.length; i++){
                        http
                        .delete('project/'+this.deleteList[i])
                        .then(({data}) => {
                            this.deleteList = []
                            this.getprojectList()
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
.active {
  background-color:    #DDD;
}
</style>
<style lang="scss" >

.col-md-4{
  margin-bottom:20px;
  border-radius: 5px;
}
.col-md-4:hover{
  box-shadow: 15px 15px 15px rgba(134, 134, 134, 0.096);
}
</style>