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
              <el-button :plain="true" @click="unfollowmode" type="info" v-bind:class="{ active: isActive}">이웃끊기</el-button>
              <el-button :plain="true" type="danger">차단</el-button>
              
            </div>

          <div class="people-list" id="people-list">
            <div class="search">
              <input type="text" v-model="search" placeholder="search follower" /> <i class="fa fa-search"></i> </div>
            <ul style="text-align:center" class="list" >
              <li @click="selected(index)" style="cursor:pointer" class="clearfix" v-for="(neighbor, index) in requestneighborinfoList" :key="index">
              <li @click="selected(index)" style="cursor:pointer" class="clearfix" v-for="(neighbor, index) in requestneighborinfoList" :key="index" v-if="neighbor.name.includes(search)">
                <img v-if="neighbor.profile_img_url" :src="neighbor.profile_img_url" alt="avatar" />
                <img v-else src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" alt="avatar" />
                <div class="about">
                  <div class="name">{{neighbor.name}}</div>
                  <div class="comments">  댓글 수 </div>
                    <el-button :plain="true" @click="deleteneighbor(index)" v-if="isActive" type="danger">이웃 삭제</el-button>
                  
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
                <li> <b>Django 게시글</b><i class="icon-attachment"></i><i class="text-orange fontello-record"></i><small>Today, 09:21 PM</small>
                  
                    <p>댓글 내용</p>
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
          this.requestneighborinfoList.push(data)
        })
      }
    })
  },
  data: function () {
        return { 
          selectedName: '',
          neighborList: [],
          requestneighborinfoList: [],
          selectedImg: '',
          selectedRegtime: '',
          select: false,
          search: '',
          isActive : false,
        }
    },
  methods: {
    selected(index) {
      this.selectedName=this.requestneighborinfoList[index].name
      this.selectedImg=this.requestneighborinfoList[index].profile_img_url
      this.selectedRegtime=this.neighborList[index].regtime
      
     // console.log(this.selectedImg)
    },
    unfollowmode() {
      this.isActive = !this.isActive
    },
    deleteneighbor(index) {
      for (var i =0; i<this.neighborList.length; i++){
        if ( this.requestneighborinfoList[index].seq == this.neighborList[i].seq_neighbor ) {
          http.delete('/userneighbor/' + this.neighborList[i].seq)
          // console.log(this.requestneighborinfoList[index].seq)
          // console.log(this.neighborList[i].seq)
        }
      }
    }
  },
}
</script>
