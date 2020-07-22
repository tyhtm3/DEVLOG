<template>
    <transition name="el-zoom-in-top">
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <ol class="breadcrumb">
                </ol>
            </section>
            <!-- Main content -->
            <section class="content">
                <div class="box">
                    <div class="box-body" style="min-height:400px;">
                        <div class="col-sm-12">
                        <!-- 메인 피드의 인포메이션 캐러셀 (2초마다 자동 슬라이드) 
                             https://mdbootstrap.com/docs/vue/advanced/carousel/ 참고
                             사용시 npm install mdbvue 설치 필요 -->
                        <mdb-carousel
                        :items='imgs'
                        :interval='2000'
                        indicators
                        controlls
                        ></mdb-carousel>
                        <br><br>

                        <div>
                            <!-- 돋보기 클릭시 검색 입력창 생성 -->
                            <a href="javascript:void(0);" @click='toggleSearchBar()' class="ti-search ">&nbsp;</a>
                            <el-input placeholder="Please input"
                                      size="small"
                                      v-model="input"
                                      v-if="isSearchOpen"></el-input>
                                      
                            <!-- tags에 태그 리스트 받아온뒤 포문으로 출력-->          
                            <span v-for="(tag, index) in tags" v-bind:key="index">
                            <span class="tag" style="font-size:20px; margin:10px;">#{{tag}}</span>
                            </span>
                        </div>
                       <br><br>


                            <!-- 프로젝트 -->



                            <!-- 포스트 -->


                        </div>
                    </div>
                </div>
            </section>
        </div>
    </transition>
</template>
<script>
 import { mdbCarousel } from 'mdbvue'
 export default {
   name: 'Main',
   components: {
     mdbCarousel
   },
   methods: {
     // 돋보기 버튼 클릭시 isSearchOpen 값 토글
     toggleSearchBar () {
       this.isSearchOpen = !this.isSearchOpen
     }
   },
   data () {
     return {
       // imgs : 이미지 리스트
       imgs: [
         {
           img: true,
           src:
           'https://mdbootstrap.com/img/Photos/Slides/img%20(4).jpg'
         },
         {
           img: true,
           src:
           'https://mdbootstrap.com/img/Photos/Slides/img%20(5).jpg'
         },
         {
           img: true,
           src:
           'https://mdbootstrap.com/img/Photos/Slides/img%20(6).jpg'
         }
       ],
       // tags : 태그 리스트
       tags: ['java', 'spring', 'python', 'aws', 'ml', 'database', 'blockchain', 'javascript', 'tensorflow'],
       // isSearchOpen, input : 추가 검색용 변수
       isSearchOpen: false,
       input: ''
     }
   }
 }
</script>