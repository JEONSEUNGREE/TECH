// import { Component } from "react";

// react-redux팀에서 만든 커스텀 훅사용
// connect는 클래스형기반에서 사용
import { useSelector, connect, useDispatch } from "react-redux";

import classes from "./Counter.module.css";

import { counterActions } from '../store/index';

const Counter = () => {
  //   // 스토어에서 어떤 데이터를 추출할지 선택함
  //   // 아래와같이 useSelector를 설정할경우 리액트리덕스가 자동으로 서브스크립션을 설정한다.
  //   // 그렇기떄문에 리덕스 스토어가 바뀐다면 자동으로 컴포넌트 함수가 다시 실행될것이다.
  //   // 만약 UNMOUNT시에는 자동으로 리액트 리덕스가 서브스크립션을 클리어한다.
  const counter = useSelector((state) => state.counter);

  const dispatch = useDispatch();

  const show = useSelector(state => state.showCounter);

  const incrementHandler = () => {
    // dispatch({ type: "increment" });
    dispatch(counterActions.increment()); // { type: 'someuniqueId', payload: 10} payload는 자동으로 지정된 변수명임 오브젝트형태로 전달가능
  };

  const increaseHandler = () => {
    // dispatch({ type: "increase", amount: 5 });
    dispatch(counterActions.increase(10));
  };

  const decrementHandler = () => {
    // dispatch({ type: "decrement" });
    dispatch(counterActions.decrement());
  };

  const toggleCounterHandler = () => {
    // dispatch({ type: "toggle" });
    dispatch(counterActions.toggleCounter());
  };

  return (
    <main className={classes.counter}>
      <h1>Redux Counter</h1>
      {show && <div className={classes.value}>{counter}</div>}
      <div>
        <button onClick={incrementHandler}>Increment</button>
        <button onClick={increaseHandler}>Increase by 5</button>
        <button onClick={decrementHandler}>Decrement</button>
      </div>
      <button onClick={toggleCounterHandler}>Toggle Counter</button>
    </main>
  );
};

// class Counter extends Component {
//   incrementHandler() {
//     this.props.increment();
//   }

//   decrementHandler() {
//     this.props.decrement();
//   }

//   toggleCounterHandler() {
//   }

//   render() {
//     return (
//       <main className={classes.counter}>
//       <h1>Redux Counter</h1>
//       <div className={classes.value}>{this.props.counter}</div>
//       <div>
//         <button onClick={this.incrementHandler.bind(this)}>Increment</button>
//         <button onClick={this.decrementHandler.bind(this)}>Decrement</button>
//       </div>
//       <button onClick={this.toggleCounterHandler}>Toggle Counter</button>
//     </main>
//     )

//   }
// }

export default Counter;

// const mapStateToProps = (state) => {
//   return {
//     counter: state.counter,
//   };
// };

// const mapDispatchToProps = (dispatch) => {
//   return {
//     increment: () => dispatch({ type: "increment" }),
//     decrement: () => dispatch({ type: "decrement" }),
//   };
// };
//  connect시 리액트 리덕스가 서브스크립션을 설정함
// export default connect(mapStateToProps, mapDispatchToProps)(Counter);
