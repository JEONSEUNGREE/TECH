import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

// vue에 vuex를 사용하다는 use(Vuex)
Vue.use(Vuex)

export default new Vuex.Store({
  //component와 비교하자면 state가 데이터
  //actions 메서드 getters computed 같은 느낌 mutaions는 동기화
  state: {
    todos:[
      { id: 1, text: 'Buy computer', checked: false }
      ],
      users: []
      },
  mutations: {
    SET_USERS(state, users) {
      state.users = users;
    },
    ADD_TODO(state, value) {
      state.todos.push(
        {
          id: Math.random(),
          text: value,
          checked: false
        });
    },
    TOGGEL_TODO(state, {checked , id}) {
      const index = state.todos.findIndex(v => {
        return v.id === id
      })
      state.todos[index].checked = checked
      console.log(checked)
    },
    DELETE_TODO(state, todoId) {
      const index = state.todos.findIndex(v => {
        return v.id === todoId
      })
      state.todos.splice(index, 1)

    }
  },
  actions: {
    getUsers({ commit }) {
      axios.get('https://jsonplaceholder.typicode.com/users').then(res => {
          commit('SET_USERS', res.data)
      })
      },
    addTodo( {commit}, value) {
      setTimeout(function() {
        commit('ADD_TODO', value);
      }, 2000);
    },
    toggleTodo({commit}, payload){
      setTimeout(function() {
        commit('TOGGEL_TODO', payload)
      }, 2000);
    },
    deleteTodo({commit}, todoId) {
      setTimeout(function() {
        commit('DELETE_TODO', todoId)
      }, 2000);

    }
  },
  modules: {
  }
})
