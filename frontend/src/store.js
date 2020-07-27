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
    mutateLoginFormVisible(state, loginFormVisible){
      state.loginFormVisible = loginFormVisible
    },
    mutateUserInfo(state, userInfo){
      state.userInfo = userInfo
    }
  },
  actions: {
    login(context, {id, password, url}){
      context.commit('mutateLoginFormVisible', false)
      context.commit('mutateIsLogin', true)
      var userInfo = {
        seq: 13,
        id: 'test',
        password: 'test',
        name: 'test',
        nickname: '',
        email: 'test@test.com',
        tel: '',
        github_url: 'https://www.devlog.com',
        profile_img_url: '',
      }
      context.commit('mutateUserInfo', userInfo)
      alert("routes")
      routes.push('/blog-main') //????
      // http
      // .post('/user/login', { 
      //   id: this.id,
      //   password: this.password
      // })
      // .then(({data}) => {
      //   console.log(data);
      //   context.commit('mutateIsLogin', true)
      //   context.commit('mutateUserInfo', data)
      //   context.commit('mutateLoginFormVisible', true)
      // })
      // .catch((error) =>  {
      //   if(error.response.status == '404'){
      //       alert('아이디와 비밀번호를 확인해주세요.')
      //   }
      // });
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
        alert("success")
        routes.push('/')
      })
      .catch((error) => {
        if(error.response.status=='404'){
          alert("아이디와 비밀번호를 확인해 주세요.")
        }
      })
    },
    modify(context, {password, name, nickname, email, tel, birth, url, imageUrl}) {
      // console.log(this.state.userInfo.seq);
      // console.log(this.state.userInfo.id);
      // console.log(name);
      // console.log(nickname);
      
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
        routes.push('/')
      })
      .catch((error) => {
        if(error.response.status=='404'){
          alert("404")
        }
      })
    },
  }
})
