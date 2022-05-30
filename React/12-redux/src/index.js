import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";

import "./index.css";
import App from "./App";
import store from './store/index'

ReactDOM.render(
    // contextAPI와같이 사용하고자하는 상위 컴포넌트에 감싼다.
    // store props를 설정
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);

// local state 컴포넌트 내의 state를 의미
// cross-component state 여러 컴포넌트간의 state 전달 값 props-chain
// app-wide state 앱 전체 컴포넌트에 영향을 주는 state - contextAPI

// ContextAPI Vs Redux
// ContextAPI 단점 :
// 복잡한 설정 <XXXXProvider>의 형태가 app.js에서 메인 컴포넌트를 xml형태로 지나치게 감싸고 있어서 복잡해질 수 있다는 의미
// 빈번히 일어나는 변경에 성능이 좋지 못하다.

// 리덕스 작동 방식
// 컴포넌트에서 직접적으로 store의 정보를 조작하지않는다.
// 그렇기때문에 store에서 subscription을 통해서 component에 state를 전달한다. 리듀서로 함수 변형을 담당하게된다.
// 리듀서함수는 useReducer훅과 다른 함수이며 입력값을 받아서 다른 값으로 변환해주는 함수이다.

//       dispatch    forwarded to        mutates(change state)
// comonent => action => reducer function => controlData(store)

// 1. npm i redux
// 2. npm i redux react-redux
