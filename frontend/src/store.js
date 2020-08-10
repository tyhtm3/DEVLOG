import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

import http from './util/http-common'

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
    },
    token : null,
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
    SET_TOKEN (state, token) {
          state.token = token
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
        context.commit('mutateIsLogin', true)
        context.commit('SET_TOKEN',data)
        context.commit('mutateLoginFormVisible', false)
        http
        .get('/user/me',{headers : {'Authorization' : data,}})
        .then(({data}) =>{
            context.commit('mutateUserInfo',data)
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
    naverLogin(context, {access_token}){
    
      http
      .get('/user/naver', {headers :{'Authorization' : access_token,}
      })
      .then(({data}) => {
        console.log(data)
        context.commit('mutateIsLogin', true)
        context.commit('SET_TOKEN',data)
        context.commit('mutateLoginFormVisible', false)
        http
        .get('/user/me',{headers : {'Authorization' : data,}
        })
        .then(({data}) =>{
            context.commit('mutateUserInfo',data)
        });
      })
    }

  }
})
