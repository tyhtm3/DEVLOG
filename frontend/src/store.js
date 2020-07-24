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
  state: {
    loginFormVisible: false,
    isLogin: false
  },
  mutations: {
    mutateIsLogin(state, isLogin){
      state.isLogin = isLogin
    },
  },
  actions: {
    login(context, {id, password}){
      http
      .post('/account/login', { id: this.id, password: this.password })
      .then(({data}) => {
        console.log(data);
        context.commit('mutateIsLogin', true);
        // this.state.userinfo 셋팅
        // this.state.uid = data.object.uid;
        // this.state.email = data.object.email;
        // this.state.nickname = data.object.nickname;
        // console.log(this.state.uid);
        // console.log(this.state.nickname);
        // console.log(this.state.email);
      })
      .catch((error) =>  {
        if(error.response.status == '404'){
            alert('아이디와 비밀번호를 확인해주세요.')
        }
      });
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
