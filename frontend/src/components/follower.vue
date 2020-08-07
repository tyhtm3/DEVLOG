<template>
  <transition name="el-zoom-in-top">
      <!-- Main content -->
      <!-- <section class="content"> -->
        <div class="container-chat clearfix">
          <div class="col-md-12">
            <br>
            <div style="text-align:center"></div>
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
              <!-- <li @click="selected(index)" style="cursor:pointer" class="clearfix" v-for="(neighbor, index) in requestneighborinfoList" :key="index" v-if="neighbor.name.includes(search)"> -->
                <img v-if="neighbor.profile_img_url" :src="neighbor.profile_img_url" alt="avatar" />
                <img v-else src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" alt="avatar" />
               
               <div class="about">
                  <div class="name">{{neighbor.name}}</div>
                  <div class="comments">  댓글 수 {{neighborComment[index]}}</div>
                    <el-button :plain="true" @click="deleteneighbor(index)" v-if="isActive" type="danger">이웃 삭제</el-button>
                </div>
            
              </li>
            </ul>

          </div>
          <div class="chat">
            <div class="chat-header clearfix">
              <img v-if="selectedImg" :src="selectedImg" style="height: 60px;" alt="avatar" />
              <img v-else-if="!selectedImg&&selectedName" src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" style="height: 60px;" alt="avatar" />
              <div v-else style="height:60px;"></div>
              <div class="chat-about">
                  <div class="chat-with">{{selectedName}}</div>
                  <div class="chat-num-messages">{{selectedRegtime}}</div>
              </div>
            </div>
            <!-- end chat-header -->
            <div class="chat-history">
                            <ul v-for="(comment,index) in neighborCommentList" :key="index">
                                <li class="clearfix">
                                    <div class="message-data align-right"> <span class="message-data-time">{{comment.regtime}}</span> &nbsp; &nbsp; <span class="message-data-name">{{selectedName}}</span> <i class="fa fa-circle me"></i> </div>
                                    <div class="message other-message float-right"><span v-html="comment.content"></span> </div>
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
  data: function () {
        return { 
          seq_user: this.$store.state.userInfo.seq,
          selectedName: '',
          neighborList: [],
          requestneighborinfoList: [],
          selectedImg: '',
          selectedRegtime: '',
          select: false,
          search: '',
          isActive : false,
          // 상대방이 나에게 작성한 댓글 개수, 리스트
          neighborComment:[],
          neighborCommentList: [],
        }
  },
  created() {
    this.getNeighborList();
  },
  methods: {
    getNeighborList(){
    http
    .get('/userneighbor/' + this.seq_user)
    .then(({data}) => {
      this.neighborList = data
      for (var i =0; i<data.length; i++){
        // 이웃의 정보
        http
        .get('/user/' + this.neighborList[i].seq_neighbor)
        .then(({data}) => { 
          this.requestneighborinfoList.push(data)
        // 이웃의 댓글 개수
        http
        .get('postcomment/neighbor/' +data.seq)
         .then(({data}) => { 
          this.neighborComment.push(data.length)
        })
        })
      }
    })
},
    selected(index) {
      this.selectedName=this.requestneighborinfoList[index].name
      this.selectedImg=this.requestneighborinfoList[index].profile_img_url
      this.selectedRegtime=this.neighborList[index].selectedRegtime
      // 선택된 이웃의 댓글 정보
      http
        .get('postcomment/neighbor/' +this.requestneighborinfoList[index].seq)
        .then(({data}) => { 
          this.neighborCommentList = data
        })
      
    },
    unfollowmode() {
      this.isActive = !this.isActive
    },
    deleteneighbor(index) {
      for (var i =0; i<this.neighborList.length; i++){
        if ( this.requestneighborinfoList[index].seq == this.neighborList[i].seq_neighbor ) {
          http.delete('/userneighbor/' + this.neighborList[i].seq).then(({data}) => { 
          this.$message({
            type: 'success',
            message: '이웃이 삭제되었습니다.'
          });
            // 이웃 삭제 후 이웃 리스트 업데이트하고 for문탈출
            this.requestneighborinfoList = []
            this.neighborComment = []
            this.neighborList = []
            this.getNeighborList();
            return ;
        })
        }
      }
    }
  },
}
</script>
