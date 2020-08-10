<template>
    <transition name="el-zoom-in-top">
        <section class="content"  style="padding-top:30px">
        <!-- 포스트출력 -->
            <div class="delete" @click="deletePortfolio" v-show="adminMode">
                <i class="ti-trash"></i> 삭제
            </div>
            <div class="row">
                <div class="col-md-4" v-for="(portfolio,index) in portfolioList" :key="index">
                    <span v-show="adminMode">
                        <input class="delete-box" :id=portfolio.seq type="checkbox" :value=portfolio.seq v-model="deleteList" />
                        <label :for=portfolio.seq></label>
                    </span>
                    <div class="well-media" @click="selectDialogVisible=true;" style="cursor:pointer;">
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
                    <!-- 템플릿 선택 dialog 시작 -->
                    <el-dialog
                    title="select template"
                    :visible.sync="selectDialogVisible"
                    width="40%"
                    center>
                    <div style="text-align:center">
                        <!-- <div class="wrap"> -->
                            <button class="fill" @click="goTemplate00(portfolio.seq)">resume</button>
                            <button class="fill" @click="goTemplate01">4 cards</button>
                        <!-- </div> -->
                    </div>
                    </el-dialog>
                    <!-- 템플릿 선택 dialog 끝 -->
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
			offset: 0,
			limit: 10,
			portfolioList: [],
			comment: [],
			counter: 0,
			deleteList: [],
			deleteSuccess: true,
			selectDialogVisible: false
		}
    },
    created(){
		this.getportfolioList();
    },
    computed: {
		adminMode() {
    		return this.$store.getters.getIsAdminMode
		},
	  	visitBlogSeq() {
			return this.$store.getters.getvisitBlogSeq 
		}
    },
    methods:{
        goTemplate00(seq){
            this.$router.push(`/blog/portfolio/${seq}`)
        },
        goTemplate01(seq){
            this.$router.push(`/template01`)
        },
        goTemplate02(seq){
        },
        goTemplate04(seq){
        },
        removeTag(text){
        text = text.replace(/<br\/>/ig, "\n");
        text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
        return text
        },
        getportfolioList(){
			http.get('user/id/'+this.$route.params.id)
            .then(({data})=>{
                http.get('portfolio/blog/'+data.seq+'/'+data.seq+'/'+this.offset+'/'+this.limit)
				.then(({ data }) => {
					this.portfolioList = data;
					for(var i=0; i<this.portfolioList.length; i++){
						http.get('postcomment/'+this.portfolioList[i].seq)
						.then(({data}) => {
							this.comment.push(data.length);
						});
					}
				})
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
<style lang="scss" scoped>



.fill:hover,
.fill:focus {
  box-shadow: inset 0 0 0 2em var(--hover);
}

// Animate the size, outside
.pulse:hover,
.pulse:focus {
  animation: pulse 1s;
  box-shadow: 0 0 0 2em rgba(#fff, 0);
}

@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 var(--hover);
  }
}

// Stack multiple shadows, one from the left, the other from the right
.close:hover,
.close:focus {
  box-shadow: inset -3.5em 0 0 0 var(--hover), inset 3.5em 0 0 0 var(--hover);
}

// Size can also be negative; see how it's smaller than the element
.raise:hover,
.raise:focus {
  box-shadow: 0 0.5em 0.5em -0.4em var(--hover);
  transform: translateY(-0.25em);
}

// Animating from the bottom
.up:hover,
.up:focus {
  box-shadow: inset 0 -3.25em 0 0 var(--hover);
}

// And from the left
.slide:hover,
.slide:focus {
  box-shadow: inset 6.5em 0 0 0 var(--hover);
}

// Multiple shadows, one on the outside, another on the inside
.offset {
  box-shadow: 0.3em 0.3em 0 0 var(--color), inset 0.3em 0.3em 0 0 var(--color);

  &:hover,
  &:focus {
    box-shadow: 0 0 0 0 var(--hover), inset 6em 3.5em 0 0 var(--hover);
  }
}

//=== Set button colors
// If you wonder why use Sass vars or CSS custom properties...
// Make a map with the class names and matching colors
$colors: (
  fill: gray,
  pulse: #ef6eae,
  close: #ff7f82,
  raise: #ffa260,
  up: #e4cb58,
  slide: #8fc866,
  offset: #19bc8b
);

// Sass variables compile to a static string; CSS variables are dynamic and inherited
// Loop through the map and set CSS custom properties using Sass variables
@each $button, $color in $colors {
  .#{$button} {
    --color: #{$color};
    --hover: #{adjust-hue($color, 45deg)};
  }
}

// Now every button will have different colors as set above. We get to use the same structure, only changing the custom properties.
button {
  color: var(--color);
  transition: 0.25s;

  &:hover,
  &:focus {
    border-color: var(--hover);
    color: #fff;
  }
}


// Basic button styles
button {
  background: none;
  border: 2px solid;
  font: inherit;
  line-height: 1;
  margin: 0.5em;
  padding: 1em 2em;
}

// h1 {
//   font-weight: 400;
// }

// code {
//   color: #e4cb58;
//   font: inherit;
// }

</style>

