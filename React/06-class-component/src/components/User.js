import { Component } from 'react';

import classes from './User.module.css';
// 모던 자바스크립트 형태의 클래스 형식
class User extends Component{
  // 리액트에서 jsx형태의 코드를 반환하기위한 형태를 redner()함수에 담음 (리액트용)
  // this로 props에 접근함
  render() {
    return <li className={classes.user}>{this.props.name}</li>
  }
}

// const User = (props) => {
//   return <li className={classes.user}>{props.name}</li>;
// };

export default User;
