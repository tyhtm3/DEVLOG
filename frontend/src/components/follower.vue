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
              <el-button :plain="true" @click="unfollowmode" v-bind:class="{ active: isActive}">구독 끊기</el-button>
              <!-- <el-button :plain="true" type="danger">차단</el-button> -->
              <el-button :plain="true" @click="follower2" style="float:right">구독 관리 종료</el-button>
            </div>

          <div class="people-list" id="people-list">
            <div class="search">
              <input type="text" v-model="search" placeholder="search follower" /> <i class="fa fa-search"></i> </div>
           
            <ul style="text-align:center" class="list" >
              <li @click="selected(index)" style="cursor:pointer" class="clearfix" v-for="(neighbor, index) in requestneighborinfoList" :key="index" v-show="neighbor.name.includes(search)">
                <img v-if="neighbor.profile_img_url" :src="neighbor.profile_img_url" style="height:60px;" alt="avatar" />
                <img v-else src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" alt="avatar" />
                <span class="about">
                  <span class="name">
                    <p>{{neighbor.name}}
                    </p>댓글 수 {{neighborComment[index]}}
                  </span>
                </span>
                <el-button :plain="true" @click="deleteneighbor(index)" v-if="isActive" type="danger" style="margin-top:10px;">구독 취소</el-button>
              </li>
            </ul>

          </div>
          <div class="chat">
            <div class="chat-header clearfix">
              <img v-if="selectedImg" :src="selectedImg" style="height: 60px;" alt="avatar" />
              <img v-else-if="!selectedImg&&selectedName" src="upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" style="height: 60px;" alt="avatar" />
              <div v-else style="height:54px;"></div>
              <div class="chat-about">
                <div class="chat-with">{{selectedName}}</div>
                <div class="chat-num-messages">{{selectedRegtime}}</div>
              </div>
            </div>
            <!-- end chat-header -->
            <div class="chat-history">
              <ul v-for="(comment,index) in neighborCommentList" :key="index">
                <li class="clearfix">
                  <div class="message-data align-right">
                    <span class="message-data-time">{{comment.regtime}}</span> &nbsp; &nbsp; 
                    <span class="message-data-name">{{selectedName}}</span> <i class="fa fa-circle me"></i>
                  </div>
                  <div class="message other-message float-right"><span v-html="comment.content"></span> </div>
                </li>
              </ul>
              <!-- {{ neighborCommentData }} -->
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
      neighborCommentData: [],
      neighborCommentList: [],
    }
  },
  created() {
    this.getNeighborList();
  },
  methods: {
    follower2() {
       this.$parent.follower();
    },
    getNeighborList(){
      http
      .get('/userneighbor/')
      .then(({data}) => {
        this.neighborList = data
        for (let i=0; i<data.length; i++){
          
          // 구독 정보 불러오기
          http
          .get('/user/' + this.neighborList[i].seq_neighbor)
          .then(({data}) => { 
            this.requestneighborinfoList[i] = data

            // 구독 댓글목록과 댓글수 불러오기
            http
            .get('postcomment/neighbor/' +data.seq)
            .then(({data}) => {
              this.neighborComment[i] = data.length
              this.neighborCommentData[i] = data
              this.$forceUpdate();
            })
          })
        }
      })
    },
    selected(index) {
      this.selectedName = this.requestneighborinfoList[index].name
      this.selectedImg = this.requestneighborinfoList[index].profile_img_url
      this.selectedRegtime = this.neighborList[index].regtime
      // 선택된 구독 댓글 정보
      this.neighborCommentList = this.neighborCommentData[index]
    },
    unfollowmode() {
      this.isActive = !this.isActive
    },
    deleteneighbor(index) {
      console.log(index)
      console.log(this.requestneighborinfoList)
      http.delete('/userneighbor', {
        data:{
            seq_neighbor: this.requestneighborinfoList[index].seq
          }
      })
      .then(({ data }) => {
        this.$message({
          type: 'error',
          message: '구독을 취소합니다.',
        });
        // 구독 삭제 후 구독 리스트 갱신
        this.neighborList = []
        this.requestneighborinfoList = []
        this.neighborComment = []
        this.neighborCommentData = []
        this.neighborCommentList = []
        this.getNeighborList();
      })
    }
  }
}
</script>
