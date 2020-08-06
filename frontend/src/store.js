import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

import http from './util/http-common'

// const state = {
//   token: null
// }

// const mutations = {
//   SET_TOKEN (state, token) {
//     state.token = token
//   },
//   REMOVE_TOKEN (state) {
//     state.token = null
//   }
// }

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
      token2:null,
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
    SET_TOKEN (state, token2) {
          state.userInfo.token2 = token2
          localStorage.setItem('token2',token2)
        },
    // REMOVE_TOKEN (state) {
    //       state.token = null
    // }
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
        context.commit('SET_TOKEN',data)
        // localStorage.setItem('token',data) 
        // context.commit('mutateUserInfo', data)
        context.commit('mutateLoginFormVisible', false)
        http
        .get('/user/me')
        .then(({data}) =>{
            // console.log(data)
            
            context.commit('mutateUserInfo',data)
            context.commit('SET_TOKEN',localStorage.getItem('token2')) 
            console.log(this.state.userInfo.token2)
            console.log(this.state.userInfo.seq)
        });
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
  }
})
