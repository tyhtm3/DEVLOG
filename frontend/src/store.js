import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [
    createPersistedState()
  ],
  state: {
    userInfo: {
      seq:0,
    },
    isLogin: false,
    isAdminMode: false,
    loginFormVisible: false,
    token : null,
  },
  getters: {
    getUserInfo: function(state) {
      return state.userInfo
    },
    getIsLogin: function(state) {
      return state.isLogin
    },
    getIsAdminMode: function(state) {
      return state.isAdminMode
    },
    getLoginFormVisible: function(state) {
      return state.loginFormVisible
    },
    getToken: function(state) {
      return state.token
    }
  },
  mutations: {
    setUserInfo(state, payload) {
      state.userInfo = payload
    },
    setIsLogin(state, payload) {
      state.isLogin = payload
    },
    setIsAdminMode(state, payload) {
      state.isAdminMode = payload
    },
    setLoginFormVisible(state, payload) {
      state.loginFormVisible = payload
    },
    setToken(state, payload) {
      state.token = payload
    },
    removeToken(state){
      state.token = null
    }
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
        context.commit('setToken',data)
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
    kakaoLogin(context,{access_token}){
      http
      .get('/user/kakao', {headers :{'Authorization' : access_token,}
      })
      .then(({data}) => {
        context.commit('mutateIsLogin', true)
        context.commit('setToken',data)
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
