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
                          <div class="col-md-12">
                                <div class="inbox-nest">
                                    <ul>
                                        <li> <img alt="" class="img-circle star" src="http://api.randomuser.me/portraits/thumb/men/22.jpg"> <b>David L. Moore</b><i class="icon-attachment"></i><i class="text-orange fontello-record"></i><small>Today, 09:21 PM</small>
                                            <h5>carousel 만들기</h5>
                                            <p>캐러셀(carousel)은 스트리밍 사이트와 e-커머스 사이트에서 중요한 요소입니다. 아마존과 넷플릭스 모두 캐러셀을 중요한 내비게이션 도구로 사용하고 있습니다. 이 튜토리얼에서 두 사이트의 인터랙션 디자인을 평가하고, 그 평가 결과를 완벽한 캐러셀을 실행하는데 활용해 보겠습니다. </p>
                                        </li>
                                        <li> <img alt="" class="img-circle star" src="http://api.randomuser.me/portraits/thumb/women/23.jpg"> <b>Helen D. Murray</b><i class="text-blue fontello-record"></i><small>20 Minute ago</small>
                                            <h5>World Recod</h5>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam erat nunc, vestibulum eu venenatis ut, feugiat sit amet elit. Donec convallis ornare odio. Donec ac est eget lacus molestie imperdiet vitae ac nunc. Suspendisse odio justo ... </p>
                                        </li>
                                        <li>
                                            <a class="star bg-red" href="#"> <span class="fa  fa-google-plus"></span> </a> <b>Google+ (DesignByHuman)</b><i class="text-aqua fontello-record"></i><small>1 Hour Ago</small>
                                            <h5>Berbagi pos - "This #DOTD by Viviana González makes us ..</h5>
                                            <p>Donec sed mi sem. Maecenas posuere justo dapibus fermentum porttitor. Phasellus ornare lectus a neque molestie sodales. Pellentesque consectetur sed nisl vitae tempor. Duis et odio ut ligula accumsan interdum. ... </p>
                                        </li>
                                        <li> <img alt="" class="img-circle star" src="http://api.randomuser.me/portraits/thumb/women/25.jpg"> <b>Deanna G. Rumph</b><i class=" icon-attachment"></i><i class="text-red fontello-record"></i><small>Yesterday, 09:21 PM</small>
                                            <h5>A new rating has been received for Aurelo Resume</h5>
                                            <p>Etiam cursus feugiat nibh eu efficitur. Praesent lobortis imperdiet neque in tempor. Praesent eu molestie urna, nec dapibus mi. Etiam blandit convallis ligula, sodales sodales nulla scelerisque ac ... </p>
                                        </li>
                                        <li>
                                            <a class="star bg-blue" href="#"> <span class="fa  fa-twitter"></span> </a> <b>Twitter (@Themesmile)</b><i class="text-aqua fontello-record"></i><small>11 November</small>
                                            <h5>Latest tweet from Beyonce</h5>
                                            <p>Suspendisse cursus purus eget magna molestie, a finibus odio malesuada. Maecenas sodales quam a egestas maximus. Donec ultricies nibh eros, non sagittis odio convallis posuere. Nam dolor velit, tincidunt in libero vel ... </p>
                                        </li>
                                        <li>
                                            <a class="star bg-dark-blue" href="#"> <span class="fa  fa-facebook"></span> </a> <b>Facebook ( Gifta Kamilia Putri)</b><i class="text-aqua fontello-record"></i><small>10 November</small>
                                            <h5>People that may you know!</h5>
                                            <p>Fusce eros lectus, hendrerit sit amet nibh et, accumsan sollicitudin urna. Maecenas ac ex id velit imperdiet tincidunt consectetur vel neque. Morbi ornare nibh sapien, sit amet tempus risus dapibus vitae. Donec volutpat, libero sit amet faucibus placerat ... </p>
                                        </li>
                                        <li> <img alt="" class="img-circle star" src="http://api.randomuser.me/portraits/thumb/men/40.jpg"> <b>James B. Miller</b><i class="icon-attachment"></i><i class="text-orange fontello-record"></i><small>10 November</small>
                                            <h5>Will You Be Our Valentine?</h5>
                                            <p>Hi, John i just want to know about my email that i,ve sending yesterday. Dou you read it? ... </p>
                                        </li>
                                        <li> <img alt="" class="img-circle star" src="http://api.randomuser.me/portraits/thumb/men/41.jpg"> <b>John P. McCarthy</b><i class="text-blue fontello-record"></i><small>09 November</small>
                                            <h5>World Recod</h5>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam erat nunc, vestibulum eu venenatis ut, feugiat sit amet elit. Donec convallis ornare odio. Donec ac est eget lacus molestie imperdiet vitae ac nunc. Suspendisse odio justo ... </p>
                                        </li>
                                        <li>
                                            <a class="star bg-yellow" href="#"> <span class="fa  fa-dribbble"></span> </a> <b>Dribbble (Matt Wulleang)</b><i class="text-aqua fontello-record"></i><small>09 November</small>
                                            <h5>Berbagi pos - "This #DOTD by Viviana González makes us ..</h5>
                                            <p>Donec sed mi sem. Maecenas posuere justo dapibus fermentum porttitor. Phasellus ornare lectus a neque molestie sodales. Pellentesque consectetur sed nisl vitae tempor. Duis et odio ut ligula accumsan interdum. ... </p>
                                        </li>
                                    </ul>
                                </div>
                            </div>

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