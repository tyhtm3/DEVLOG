<template>
  <transition name="el-zoom-in-top">
      <!-- Main content -->
      <!-- <section class="content"> -->
        <div class="container-chat clearfix"> 

          <div class="col-md-12">
            <br>
            <div style="text-align:center"></div>
              <el-button :plain="true" @click="unfollowmode" v-bind:class="{ active: isActive}">구독 해제</el-button>
              <el-button :plain="true" @click="follower2" style="float:right">구독 관리 종료</el-button>
            </div>

          <div class="people-list" id="people-list">
            <div class="search">
              <input type="text" v-model="search" placeholder="search follower" /> <i class="fa fa-search"></i> </div>
          <div class="pull-right">
              {{ followtext }}
              
              <el-switch v-model="flag"
              active-color="#11212E"
              inactive-color="#9EBBCD"
              >
              </el-switch>
          </div>
            <ul style="text-align:center; clear:both" class="list">
              <li style="cursor:pointer" class="clearfix" v-for="(neighbor, index) in requestneighborinfoList" :key="index" v-show="neighbor.name.includes(search)">
                <img @click ="goBlog(neighbor.id)" v-if="neighbor.profile_img_url" :src="neighbor.profile_img_url" style="height:60px;" alt="avatar" />
                <img @click ="goBlog(neighbor.id)" v-else src="http://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" alt="avatar" />
                <span class="about">
                  <span class="name" @click="selected(index)">
                    <p v-if=neighbor.nickname>{{neighbor.nickname}}</p>
                    <p v-else>{{neighbor.name}}</p>
                    댓글 수 {{neighborComment[index]}}
                  </span>
                </span>
                <el-button :plain="true" @click="deleteneighbor(index)" v-if="isActive" type="danger" style="margin-top:10px;">구독 취소</el-button>
              </li>
            </ul>

          </div>
          <div class="chat">
            <div class="chat-header clearfix">
              <img v-if="selectedImg" :src="selectedImg" style="height: 60px;" alt="avatar" />
              <img v-else-if="!selectedImg&&selectedName" src="http://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/450px-No_image_available.svg.png" style="height: 60px;" alt="avatar" />
              <div v-else style="height:54px;"></div>
              <div class="chat-about">
                <div class="chat-with" @click="move" style="cursor:pointer">{{selectedName}}</div>
                <div class="chat-num-messages" v-if="selectedRegtime">구독한 날짜 : {{selectedRegtime}}</div>
              </div>
            </div>
            <!-- end chat-header -->
            <div class="chat-history">
              <ul v-for="(comment,index) in neighborCommentList" :key="index">
                <li class="clearfix">
                  <div class="message-data align-right">
                    <span class="message-data-name">{{comment.title}}</span>
                    <span class="message-data-time">{{comment.regtime}}</span> &nbsp; &nbsp; 
                    <!-- <span class="message-data-name">{{selectedName}}</span> <i class="fa fa-circle me"></i> -->
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
      neighborList1: [],
      neighborList2: [],
      requestneighborinfoList: [],
      selectedImg: '',
      selectedRegtime: '',
      selectedId: '',
      select: false,
      search: '',
      isActive: false,
      followtext: "나를 구독한 사람",
      // 상대방이 나에게 작성한 댓글 개수, 리스트
      neighborComment:[],
      neighborCommentData: [],
      neighborCommentList: [],
      flag: ''
    }
  },
  created() {
    this.getNeighbormeList();
  },
  watch:{
    flag: function(){
      if(this.flag){
        this.requestneighborinfoList = []
        this.neighborComment = []
        this.neighborCommentData = []
        this.neighborCommentList = []
        this.getNeighborList();
        this.followtext = "내가 구독한 사람"
      }
      else{
        this.requestneighborinfoList = []
        this.neighborComment = []
        this.neighborCommentData = []
        this.neighborCommentList = []
        this.getNeighbormeList();
        this.followtext = "나를 구독한 사람"
      }
    }
  },
  methods: {
    goBlog(id){
      this.$router.push('/blog/'+id);
    },
    follower2() {
       this.$parent.follower();
    },
    getNeighborList(){

      // 내가 구독한 유저 정보
      http
      .get('/userneighbor/'
      ,{headers: { Authorization : this.$store.state.token,}}
      )
      .then(({data}) => {
        this.neighborList = data
        for (let i=0; i<data.length; i++){
          
          // 유저 정보 불러오기
          http
          .get('/user/' + this.neighborList[i].seq_neighbor
          ,{headers: { Authorization : this.$store.state.token,}})
          .then(({data}) => {
            console.log(data)
            this.requestneighborinfoList[i] = data

            // 구독 댓글목록과 댓글수 불러오기
            http
            .get('postcomment/neighbor/me/' +data.seq
            ,{headers: { Authorization : this.$store.state.token,}})
            .then(({data}) => {
              console.log(data)
              this.neighborComment[i] = data.length
              this.neighborCommentData[i] = data
              this.$forceUpdate();
            })
          })
        }
      })
    },
    getNeighbormeList(){
      // 나를 구독한 유저 정보
      http
      .get('/userneighbor/'+this.$store.getters.getUserInfo.seq
      ,{headers: { Authorization : this.$store.state.token,}}
      )
      .then(({data}) => {
        this.neighborList = data
        for (let i=0; i<data.length; i++){
          
          // 유저 정보 불러오기
          http
          .get('/user/' + this.neighborList[i].seq_user
          ,{headers: { Authorization : this.$store.state.token,}})
          .then(({data}) => {
            this.requestneighborinfoList[i] = data

            // 구독 댓글목록과 댓글수 불러오기
            http
            .get('postcomment/neighbor/' +data.seq
            ,{headers: { Authorization : this.$store.state.token,}})
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
      if(this.requestneighborinfoList[index].nickname)
        this.selectedName = this.requestneighborinfoList[index].nickname
      else
        this.selectedName = this.requestneighborinfoList[index].id
      this.selectedImg = this.requestneighborinfoList[index].profile_img_url
      this.selectedRegtime = this.neighborList[index].regtime
      this.selectedRegtime = this.selectedRegtime.slice(0,10)
      this.selectedId = this.requestneighborinfoList[index].id
      // 선택된 구독 댓글 정보
      this.neighborCommentList = this.neighborCommentData[index]
    },
    unfollowmode() {
      this.isActive = !this.isActive
    },
    deleteneighbor(index) {
      http.delete('/userneighbor', {
        data:{
            seq_neighbor: this.requestneighborinfoList[index].seq
          }
      },{headers: { Authorization : this.$store.state.token,}}
      )
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
    },
    move(){
      this.$router.push(''+this.selectedId)
    }
  }
}
</script>
