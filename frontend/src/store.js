import Vue from 'vue'
import Vuex from 'vuex'

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
  state: {
    loginFormVisible: false,
    isLogin: false,
    userInfo: {}
  },
  mutations: {
    mutateIsLogin(state, isLogin){
      state.isLogin = isLogin
    },
    mutateUserInfo(state, userInfo){
      state.userInfo = userInfo
    }
  },
  actions: {
    login(context, {id, password}){
      
      // 임시 로그인
      this.state.isLogin = true
      this.state.loginFormVisible = false
      this.state.userInfo = {
        id: id,
        password: password,
        name: '임시이름',
        nickname: '임시닉네임',
        email: 'ssafy@ssafy.com',
        tel: '010-1234-1234',
        birth: '2000-01-01',
        profileImage: '',
        url: 'www.devlog.com'
      }
      console.log(this.state.isLogin)
      console.log(this.state.loginFormVisible)
      console.log(this.state.userInfo)

      // http
      // .post('/account/login', { 
      //   id: this.id,
      //   password: this.password
      // })
      // .then(({data}) => {
      //   console.log(data);
      //   context.commit('mutateIsLogin', true)
      //   context.commit('mutateUserInfo', data)
      // })
      // .catch((error) =>  {
      //   if(error.response.status == '404'){
      //       alert('아이디와 비밀번호를 확인해주세요.')
      //   }
      // });
    },
    signup(context, {id, password, name, nickname, email, tel, birth, url, imageUrl}) {
      console.log(id)
      alert("dd")
      http
      .post('/account/signup', {
        id: id,
        password: password,
        name: name,
        nickname: nickname,
        email: email,
        tel: tel,
        birth: birth,
        url: url,
        imageUrl: imageUrl
      })
      .then(({ data }) => {
        alert("success")
        routes.push('/')
      });
    },
    modify(context, {password, name, nickname, email, tel, birth, url, imageUrl}) {
      http
      .put('/account/modify', {
        id: this.state.id,
        password: password,
        name: name,
        nickname: nickname,
        email: email,
        tel: tel,
        birth: birth,
        url: url,
        imageUrl: imageUrl
      })
      .then(({ data }) => {
        alert("success")
        routes.push('/')
      })
    }
  }
})
