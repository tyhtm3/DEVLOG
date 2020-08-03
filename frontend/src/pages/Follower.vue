<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <!-- Main content -->
      <section class="content">
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
              <li @click="selected(index)" style="cursor:pointer  " class="clearfix" v-for="(neighbor, index) in neighborList2" :key="index">
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
              <img v-else src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" alt="avatar" />
              <div class="chat-about">
                  <div class="chat-with">{{selectedName}}</div>
                  <div class="chat-num-messages">{{selectedRegtime}}</div>
              </div>
            </div>
            <!-- end chat-header -->
           
            <div class="chat-history">
              <ul>
                <li class="clearfix">
                  <div class="message-data align-right"> <span class="message-data-time">10:10 AM, Today</span> &nbsp; &nbsp; <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i> </div>
                  <div class="message other-message float-right"> Hi Vincent, how are you? How is the project coming along? </div>
                </li>
                <li>
                  <div class="message-data"> <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span> <span class="message-data-time">10:12 AM, Today</span> </div>
                  <div class="message my-message"> Are we meeting today? Project has been already finished and I have results to show you. </div>
                </li>
                <li class="clearfix">
                  <div class="message-data align-right"> <span class="message-data-time">10:14 AM, Today</span> &nbsp; &nbsp; <span class="message-data-name">Olia</span> <i class="fa fa-circle me"></i> </div>
                  <div class="message other-message float-right"> Well I am not sure. The rest of the team is not here yet. Maybe in an hour or so? Have you faced any problems at the last phase of the project? </div>
                </li>
                <li>
                  <div class="message-data"> <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span> <span class="message-data-time">10:20 AM, Today</span> </div>
                  <div class="message my-message"> Actually everything was fine. I'm very excited to show this to our team. </div>
                </li>
                <li>
                  <div class="message-data"> <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span> <span class="message-data-time">10:31 AM, Today</span> </div> <i class="fa fa-circle online"></i> <i class="fa fa-circle online" style="color: #AED2A6"></i> <i class="fa fa-circle online" style="color:#DAE9DA"></i>
                </li>
              </ul>
            </div>
            <!-- end chat-history -->
            <div class="chat-message clearfix">
              <textarea name="message-to-send" id="message-to-send" placeholder="Type your message" rows="3"></textarea> <i class="fa fa-file-o"></i> &nbsp;&nbsp;&nbsp; <i class="fa fa-file-image-o"></i>
              <button>Send</button>
            </div>
            <!-- end chat-message -->
          </div>
          <!-- end chat -->
        </div>
        <!-- end container -->
      </section>
      <!-- /.content -->
    </div>
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
          console.log(data)
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
        }
    },
  methods: {
    selected(index) {
      this.selectedName=this.neighborList2[index].name
      this.selectedImg=this.neighborList2[index].profile_img_url
      this.selectedRegtime=this.neighborList[index].regtime
     // console.log(this.selectedImg)
    }
  }
}
</script>
