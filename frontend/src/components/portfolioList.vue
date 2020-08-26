<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 포스트출력 -->
            <div class="delete" @click="deletePortfolio" v-show="adminMode">
                <i class="ti-trash"></i> 삭제
            </div>
            <div class="row">
                <div class="col-md-4" v-for="(portfolio, index) in portfolioList" :key="index">
                    <span v-show="adminMode">
                        <input class="delete-box" :id=portfolio.seq type="checkbox" :value=portfolio.seq v-model="deleteList" />
                        <label :for=portfolio.seq></label>
                    </span>
                    <div class="well-media" @click="selectDialogVisible=true;setPortfolioSeq(portfolio.seq);" style="cursor:pointer;">
                        <div class="vendor">
                            <!-- <img class="img-responsive-media" src="https://www.overseaspropertyforum.com/wp-content/themes/realestate-7/images/no-image.png" alt="">
                             -->
                             <div class="header-block">
                               <div class="header-image">
                                  <img src="../assets/portfolio-banner.jpg" height="100%" style="opacity:60%;max-height: initial;margin-top: -20px;"/>
                               </div>
                               <div class="header-text"><p>{{portfolio.title}}</p></div>
                            </div>
                        </div>
                        <div class="video-text">
                            <h2 class="title-1line" style="font-weight: bold; margin-bottom:10px;">{{portfolio.title}}</h2>
                            <p class="content-3line" style="color:black;">{{ removeTag(portfolio.content) }}</p>
                        </div>
                        <div class="tag-nest" style="block:inline"> 
                            <span class="tag">#Portfolio</span>
                            <span class="tag-copy" style="float:right"> <i class="ti-heart"></i> {{portfolio.like_count}} </span>
                        </div>
                    </div>
                    
                    <!-- 템플릿 선택 dialog 시작 -->
                    <el-dialog
                    id="select-template"
                    title="마음에 드는 템플릿을 골라보세요."
                    :visible.sync="selectDialogVisible"
                    width="85%"
                    center>
                        <div v-if="seq_blog==seq_user" class="pull-right pull top" style="margin-top:-40px;margin-right:20px; ">
                            <router-link :to="{name:'portfolio-update', params:{seq:clickedSeq}}">
                                <el-tooltip content="포트폴리오 수정" placement="top">
                                    <el-button icon="el-icon-edit" type="success" style="background-color:#9EBBCD; border-color:#9EBBCD;">
                                    </el-button> 
                                </el-tooltip>
                            </router-link>
                            <el-tooltip :content="clickedSeq!=seq_rep?'대표 설정':'대표 포트폴리오'" placement="top">
                                <el-button v-if="clickedSeq!=seq_rep" @click="updateRepresentation(clickedSeq)" type="warning" icon="el-icon-star-off" circle></el-button>
                                <el-button v-else v-on:click.prevent.self type="warning" icon="el-icon-star-on"></el-button>
                            </el-tooltip>
                        </div>
                        

                        <portfolio-detail v-bind:clickedSeq="clickedSeq" style="overflow:auto"></portfolio-detail>
                    </el-dialog>
                    <!-- 템플릿 선택 dialog 끝 -->
                </div>
            </div>
        </section>
    </transition>
</template>
<script>
import http from '../util/http-common'
import portfolioDetail from './portfolioDetail.vue'
export default {
    name: 'portfolioList',
    components: {
        'portfolio-detail':portfolioDetail
    },
    data() {
        return{
            offset: 0,
            limit: 10,
            portfolioList: [],
            comment: [],
            counter: 0,
            deleteList: [],
            deleteSuccess: true,
            selectDialogVisible: false,
            clickedSeq:'',
            seq_blog:'',
            seq_user:this.$store.getters.getUserInfo.seq,
            seq_rep: '',
        }
    },
    created() { 
        this.getportfolioList();
    },
    computed: {
        adminMode() {
            return this.$store.getters.getIsAdminMode
        },
    },
    methods:{
        setPortfolioSeq(seq){
          this.clickedSeq = seq;
        },
        getportfolioList(){
            http.get('user/id/'+this.$route.params.id)
            .then(({data})=>{
                this.seq_blog = data.seq
                http.get('portfolio/blog/'+this.$store.getters.getUserInfo.seq+'/'+data.seq+'/'+this.offset+'/'+this.limit)
                .then(({ data }) => {
                    this.portfolioList = data;
                    for(var i=0; i<this.portfolioList.length; i++){
                        http.get('postcomment/'+this.portfolioList[i].seq)
                        .then(({data}) => {
                            this.comment.push(data.length);
                        });
                    }
                })
                if(data.length>0){
                    http.get('portfolio/representation/'+data.seq).then(({data}) => {
                        this.seq_rep = data
                    })
                }
            })
        },
        removeTag(text){
        text = text.replace(/<br\/>/ig, "\n");
        text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
        return text
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
                .$confirm('총 '+this.deleteList.length+'개의 포트폴리오를 삭제하시겠습니까?', {
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
        },
        updateRepresentation(seq){
        http
          .put('portfolio/representation', {
            seq_blog: this.seq_blog,
            seq: seq
          }) .then(() => {
              this.seq_rep = seq
               this.$message({
                    type: 'success',
                    message: '선택한 포트폴리오를 대표로 설정하였습니다.',
                });
          })
        }
    }
}
</script>
<style scoped>
.header-block{
  overflow: hidden;
  position: relative;
}

.header-image{
  	vertical-align: middle;
}

.header-text{
  text-align: center;
  position: absolute;
  top: 53%;
  left: 50%;
  transform: translate( -50%, -50% );
  color:white;
  font-size:22px;
  font-family: "Noto Sans KR";
  font-weight: 500;
  
}
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

<style>
    
#select-template .el-dialog__title{
    font-size: 30px!important;
    font-family: "Noto Sans KR";
    font-weight: 700;
}
</style>