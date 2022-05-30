// 여러개의 slice를 합치기위함으로 기존 리덕스에 combineReducers를 사용해도 되지만
// 툴킷의 함수를 사용한다.
// import { createStore, combineReducers } from "redux";
import { configureStore } from "@reduxjs/toolkit";

import counterReducer from "./counter";
import authReducer from "./auth";

// const initialCounterState = { counter: 0, showCounter: true , isAuthenticated: false };

// // redux가 커짐에따라 쪼개기위해 redux Toolkit 라이브러리를 사용한다.
// // 툴킷사용시 이미 툴킷에 redux가 포함되어있기에 기존 라이브러리를 삭제해야한다.
// const counterSlice = createSlice( {
//     name: 'counter',
//     initialState: initialCounterState,
//     // 위 state는 reducer함수가 필요하다
//     // 아래 함수들은 나중에 리덕스에 의해 호출됨
//     reducers: {
//         // 호출한곳에만 사용됨으로 if문이 따로 필요없음
//         // 고유 액션 식별자가 자동으로 설정되었기때문
//         increment(state) {
//             // 아래와같이두면 immer라는 다른패키지를 사용해 기존 코드를 자동복사해서 새로운 객체를 생성함 (변경불가하게하고 새로운 객체를 생성함)
//             state.counter++
//         },
//         decrement(state) {
//             state.counter--;
//         },
//         increase(state, action) {
//             // action을 listen하는 리듀서 payload, data를 가지고있음
//             state.counter = state.counter + action.payload;
//         },
//         toggleCounter(state) {
//             state.showCounter = !state.showCounter;
//         }
//     }
// });

// const initialAuthState = {
//     isAuthenticated: false
// }

// const authSlice = createSlice({
//     name: 'authentication',
//     initialState: initialAuthState,
//     reducers: {
//       login(state) {
//           state.isAuthenticated = true;
//       },
//       logout(state) {
//           state.isAuthenticated = false;
//       }
//     }
// });

// const counterReducer = (state = { counter: 0, showCounter: true }, action) => {
//   if (action.type === "increment") {
//     return {
//       // 리덕스도 기존의 것을 대체하지않고 덮어쓰기에 주의해야한다.
//       // 항상 새로운 객체로 생성되도록 해야하며 기존의 값에 대체하는 방법이 있어서는 안된다.
//       // 이유는 기존 객체를 계속사용하면 참조된 주소값을 알아내어 재정의하기 쉽기때문에
//       //   이를 방지하기위해서 매번 새로운 값을 생성하도록 하는것이다.
//       counter: state.counter + 1,
//       showCounter: state.showCounter,
//     };
//   }

//   if (action.type === "increase") {
//     return {
//       counter: state.counter + action.amount,
//       showCounter: state.showCounter,
//     };
//   }

//   if (action.type === "decrement") {
//     return {
//       counter: state.counter - 1,c
//       showCounter: state.showCounter,
//     };
//   }

//   if (action.type === "toggle") {
//     return {
//       showCounter: !state.showCounter,
//       counter: state.counter,
//     };
//   }

//   return state;
// };.

// 툴킷으로 자동생성된 메서드 접근이 가능함 (액션 객체 생성자)

const store = configureStore({
    // 맵형태로 slice를 합친다.
    // reducer: { counter: counterSlice.reducer }
    reducer: { counter: counterReducer, auth: authReducer }
});


export default store;
