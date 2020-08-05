import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

import http from './util/http-common'
import routes from './routes'

const state = {
  token: null
}

const mutations = {
  SET_TOKEN (state, token) {
    state.token = token
  },
  REMOVE_TOKEN (state) {
    state.token = null
  }
}

export default new Vuex.Store({
  plugins: [
    createPersistedState()
  ],
  state: {
    loginFormVisible: false,
    settingButtonVisible: false,
    isLogin: false,
    userInfo: {
      seq:0,
    }
  },
  getters: {
    userInfo: state => state,
  },
  mutations: {
    mutateIsLogin(state, isLogin){
      state.isLogin = isLogin
    },
    mutateLoginFormVisible(state, loginFormVisible){
      state.loginFormVisible = loginFormVisible
    },
    mutateUserInfo(state, userInfo){
      state.userInfo = userInfo
    },
  },
  actions: {
    login(context, {id, password, url}){
      http
      .post('/user/login', { 
        id: id,
        password: password
      })
      .then(({data}) => {
        console.log(data);
        context.commit('mutateIsLogin', true)
        context.commit('mutateUserInfo', data)
        context.commit('mutateLoginFormVisible', false)
        routes.push('/blog-main') // 작동 안됨...
      })
      .catch((error) =>  {
        if(error.response.status == '404')
            alert('존재하지 않는 아이디입니다.')
        else if(error.response.status == '401')
            alert('비밀번호가 틀렸습니다.')
        else
            alert('로그인 도중 에러가 발생했습니다.')
      });
    },
    signup(context, {id, password, name, nickname, email, tel, birth, url, imageUrl}) {
   
      http
      .post('/user', {
        id: id,
        password: password,
        name: name,
        nickname: nickname,
        email: email,
        tel: tel,
        birthday: birth,
        github_url: url,
        profile_img_url: imageUrl
      })
      .then(({ data }) => {
        console.log(data)
        routes.push(`/`)
      })
      .catch((error) => {
        console.log(error.response.status)
        if(error.response.status=='404'){
          alert("이미 존재하는 아이디입니다.")
        }
      })
      routes.push(`/`)
    },
    modify(context, {password, name, nickname, email, tel, birth, url, imageUrl, target}) {

      http
      .put('/user', {
        seq: this.state.userInfo.seq,
        id: this.state.userInfo.id,
        password: password,
        name: name,
        nickname: nickname,
        email: email,
        tel: tel,
        birthday: birth,
        github_url: url,
        profile_img_url: imageUrl
      })
      .then(({ data }) => {
        alert("success")
        // state 정보 업데이트
        this.commit('mutateUserInfo', 
        {
          seq: this.state.userInfo.seq,
          id: this.state.userInfo.id,
          password:password,
          name:name,
          nickname:nickname,
          email:email,
          tel:tel,
          birthday:birth,
          github_url:url,
          profile_img_url:imageUrl
        })

        this.$message({
          type: 'success',
          message: '정보가 수정되었습니다.'
        });
        console.log(this)
        console.log("라우터되나??")
        routes.push('/')
      })
      .catch((error) => {
        if(error.response.status=='404'){
          alert("404")
        }
      })
    },
    signout(context, {seq} ) {
      http
      .delete('/user/'+seq)
      .then(({ data }) => {
        context.commit('mutateIsLogin', false)
        $message({
          type: 'success',
          message: '탈퇴 처리 되었습니다.'
        });
        $router.push(`/`)
      })
    }
  }
})
