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
      // context.commit('mutateLoginFormVisible', false)
      // context.commit('mutateIsLogin', true)
      // var userInfo = {
      //   seq: 8,
      //   id: 'test',
      //   password: 'test',
      //   name: '감자',
      //   nickname: '',
      //   email: 'test@test.com',
      //   tel: '',
      //   github_url: 'www.devlog.com',
      //   profile_img_url: '',
      // }
      // context.commit('mutateUserInfo', userInfo)
      // alert("test")
      // routes.push('/blog-main') //????
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
        routes.push('/')
      })
      .catch((error) => {
        console.log(error.response.status)
        if(error.response.status=='404'){
          alert("이미 존재하는 아이디입니다.")
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
    signout(context, {seq} ) {
      http
      .delete('/user/'+seq)
      .then(({ data }) => {
        context.commit('mutateIsLogin', false)
        alert("탈퇴 처리 되었습니다.")
        this.$router.push('/')
      })
    }
  }
})
