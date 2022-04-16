import './App.css';
import React, { useState } from 'react';

function App() {


  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  // const [ text, setText ] = useState("Kossie");
  // 1번째 text의 값은 Kossie이며 2번째 setText의 값은 변경에 사용함

  const onSubmit = (event) => {
    event.preventDefault();
    console.log(username, password)
  }

  // let text = "Hello";

  // const updateText = () => {
  //   // let text = "ByeBye";
  //   setText("Coder")
  //   console.log(text);
  // 내부에서 로그를 찍으면 기존 text값이 출력되고
  // 밖에서 재 랜더링된값이 출려시 정상변경 확인가능
  // document.getElementById("text").innerHTML = text;
  // }
  // strict 모드시 콘솔로그 두번출력됨
  // console.log(text)
  // const onKeyUp = (event) => {
  //   // 기본적으로 event인자를 지원 13이 엔터의 키코드
  //   if (event.keyCode === 13) {
  //     onSubmit();
  //   }
  //   console.log();
  // }

  return (
    <div className="App">
      <form onSubmit={onSubmit}>
        <input placeholder='Username'
          value={username}
          onChange={(e) => setUsername(e.target.value)} /><br />
        <input placeholder='Password' value={password}
          onChange={(e) => setPassword(e.target.value)} /><br />
        <button type='submit'>Login</button>
      </form>
    </div>
  );
}

export default App;
