import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

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
  state,
  mutations
})
// import Vue from 'vue'
// import Vuex from 'vuex'

// Vue.use(Vuex)

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

// export default new Vuex.Store({
//   state:{
//     isLogin: false,
//     isLoginAdmin: false,
//     userid:'',
//     username:'',
//   },
//   mutations:{
//     mutateIsLoginAdmin(state, isLogin){
//       state.isLoginAdmin = isLogin
//     },
//     mutateIsLogin(state, isLogin){
//       state.isLogin = isLogin
//     },
//   },
//   actions:{
//     templogin(){
//       context.commit('mutateIsLogin', true);
//       this.userid='user';
//       this.username='방문자';
//     },
//     tempadminlogin(){
//       context.commit('mutateIsLoginAdmin', true);
//       context.commit('mutateIsLogin', true);
//       this.userid='admin';
//       this.username='관리자';
//     },
//   }
// })
