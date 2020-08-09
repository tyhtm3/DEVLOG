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
    setToken (state, payload) {
      state.token = payload
    },
  },
  actions: {
  }
})
