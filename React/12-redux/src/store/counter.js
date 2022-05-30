import { createSlice } from "@reduxjs/toolkit";

const initialCounterState = { counter: 0, showCounter: true , isAuthenticated: false };

// redux가 커짐에따라 쪼개기위해 redux Toolkit 라이브러리를 사용한다.
// 툴킷사용시 이미 툴킷에 redux가 포함되어있기에 기존 라이브러리를 삭제해야한다.
const counterSlice = createSlice( {
    name: 'counter',
    initialState: initialCounterState,
    // 위 state는 reducer함수가 필요하다
    // 아래 함수들은 나중에 리덕스에 의해 호출됨
    reducers: {
        // 호출한곳에만 사용됨으로 if문이 따로 필요없음
        // 고유 액션 식별자가 자동으로 설정되었기때문
        increment(state) {
            // 아래와같이두면 immer라는 다른패키지를 사용해 기존 코드를 자동복사해서 새로운 객체를 생성함 (변경불가하게하고 새로운 객체를 생성함)
            state.counter++
        },
        decrement(state) {
            state.counter--;
        },
        increase(state, action) {
            // action을 listen하는 리듀서 payload, data를 가지고있음
            state.counter = state.counter + action.payload;
        },
        toggleCounter(state) {
            state.showCounter = !state.showCounter;
        }
    }
});

export const counterActions = counterSlice.actions;

export default counterSlice.reducer;