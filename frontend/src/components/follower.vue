<template>
  <transition name="el-zoom-in-top">
      <!-- Main content -->
      <!-- <section class="content"> -->
        <div class="container-chat clearfix">
          <div class="col-md-12">
            <div style="text-align:center">
            <h2>이웃 관리</h2></div>
                <!-- <input class="delete-box" :id=index type="checkbox" :value=post.seq " /> -->
                    <!-- <label :for=index></label>전체선택 -->
              <el-button :plain="true" @click='unfollow' type="info">이웃끊기</el-button>
              <el-button :plain="true" @click='block' type="danger">차단</el-button>
            </div>

          <div class="people-list" id="people-list">
            <div class="search">
              <input type="text" placeholder="search" /> <i class="fa fa-search"></i> </div>
            <ul style="text-align:center" class="list">
              <li @click="selected(index) " style="cursor:pointer  " class="clearfix" v-for="(neighbor, index) in neighborList2" :key="index">
                <img v-if="neighbor.profile_img_url" :src="neighbor.profile_img_url" alt="avatar" />
                <img v-else src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" alt="avatar" />
                <div class="about">
                  <div class="name">{{neighbor.name}}</div>
                  <div class="status"> <i class="fa fa-circle online"></i> online </div>
                </div>
              </li>

              
            </ul>
          </div>
          <div class="chat">
            <div class="chat-header clearfix">
              <img v-if="selectedImg" :src="selectedImg" style="height: 60px;" alt="avatar" />
              <img v-else src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" style="height: 60px;" alt="avatar" />
              <div class="chat-about">
                  <div class="chat-with">{{selectedName}}</div>
                  <div class="chat-num-messages">{{selectedRegtime}}</div>
              </div>
            </div>
            <!-- end chat-header -->
            <div class="inbox-nest">
              <ul>
                <li> <b>David L. Moore</b><i class="icon-attachment"></i><i class="text-orange fontello-record"></i><small>Today, 09:21 PM</small>
                    <h5>Will You Be Our Valentine?</h5>
                    <p>Hi, John i just want to know about my email that i,ve sending yesterday. Dou you read it? ... </p>
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
              </ul>
            </div>
            <!-- end chat-history -->

            <!-- end chat-message -->
          </div>
          <!-- end chat -->
        </div>
        <!-- end container -->
      <!-- </section> -->
  </transition>
</template>
<script>
import http from '../util/http-common'
export default {
  name: 'Follower',
  created() {
    http
    .get('/userneighbor/' + this.$store.state.userInfo.seq)
    .then(({data}) => {
      this.neighborList = data
      for (var i =0; i<data.length; i++){
        http
        .get('/user/' + this.neighborList[i].seq_neighbor)
        .then(({data}) => {
        //  console.log(data)
          this.neighborList2.push(data)
        })
      }
    })
  },
  data: function () {
        return { 
          selectedName: '',
          neighborList: [],
          neighborList2: [],
          selectedImg: '',
          selectedRegtime: '',
          select: false,
        }
    },
  methods: {
    selected(index) {
      this.selectedName=this.neighborList2[index].name
      this.selectedImg=this.neighborList2[index].profile_img_url
      this.selectedRegtime=this.neighborList[index].regtime
     // console.log(this.selectedImg)
    },
    unfollow() {
      
    },
  },
  

}
</script>
