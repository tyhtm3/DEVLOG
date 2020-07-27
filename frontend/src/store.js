import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import http from './util/http-common'
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
      console.log("임시로그인")
      console.log(id)
      console.log(password)
      this.state.isLogin = true
      this.state.loginFormVisible = false
      this.state.userInfo = {
        id: id,
        password: password,
        name: '',
        nickname: '',
        email: '',
        tel: '',
        birth: '',
        profileImage: ''
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

    signup({}){
      http
      .post('/account/signup', { nickname: this.nickname, email: this.email, password: this.password })
      .then(({ data }) => {
        alert("success")
        router.push(url)
      })
    },
  },
})
