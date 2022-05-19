import { Component, useState } from "react";
import User from "./User";

import classes from "./Users.module.css";

// const DUMMY_USERS = [
//   { id: "u1", name: "Max" },
//   { id: "u2", name: "Manuel" },
//   { id: "u3", name: "Julie" },
// ];

class Users extends Component {
  constructor() {
    // 함수형의 state의 경우 원시타입등이 가능했지만
    // 클래스의 경우 항상 객체타입이어야한다.
    // setState로 활용시 함수형과 다르게 오버라이딩하지않고 병합함으로 덮어써질우려가없다.
    super();
    this.state = {
      showUsers: true,
      more: "Test",
    };
  }

  toggleUsersHandler() {
    this.setState((curState) => {
      return { showUsers: !curState.showUsers };
    });
  }

  componentDidUpdate() {
    if (this.props.users.length === 0) {
      throw new Error("No users proivded!");
    }
  }
  render() {
    const usersList = (
      <ul>
        {this.props.users.map((user) => (
          <User key={user.id} name={user.name} />
        ))}
      </ul>
    );

    return (
      <div className={classes.users}>
        {/* 주위를 둘러싼 클래스를 참조하는지 여부를 bind로 사용해 지정한다. */}
        <button onClick={this.toggleUsersHandler.bind(this)}>
          {this.state.showUsers ? "Hide" : "Show"} Users
        </button>
        {this.state.showUsers && usersList}
      </div>
    );
  }
}

// const Users = () => {
//   const [showUsers, setShowUsers] = useState(true);

//   const toggleUsersHandler = () => {
//     setShowUsers((curState) => !curState);
//   };

//   return (
//     <div className={classes.users}>
//       <button onClick={toggleUsersHandler}>
//         {showUsers ? "Hide" : "Show"} Users
//       </button>
//       {showUsers && usersList}
//     </div>
//   );
// };

export default Users;

// 클래스 컴포넌트의 생명주기
// componentDidMount -> useEffect(...,[])에 빈배열의 형태 의존성이없는경우
// componentDidUpdate -> 재평가 재호출시 useEffect(...,[someValue]) 특정 의존성배열이 존재하는경우
// componentWillUnmmount -> 컴포넌트가 DOM에서 삭제되기 직전에 호출되며 useEffect()에 cleanup함수와 같다.
