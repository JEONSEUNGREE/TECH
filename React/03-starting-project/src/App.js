import React, { useState, useEffect } from "react";

import Login from "./components/Login/Login";
import Home from "./components/Home/Home";
import MainHeader from "./components/MainHeader/MainHeader";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  // 다음과같이 호출시 로컬저장소에 정보로 useState 값 변경시 함수를 재호출함으로 무한 루프에 빠질수있다.
  // const storedUserLoggedInInformation = localStorage.getItem("isLoggedIn");

  // if (storedUserLoggedInInformation === "1") {
  //   setIsLoggedIn(true);
  // }

  // useEffect를 사용시 모든 컴포넌트 재평가 후에 실행되며
  // 리액트에 의해서 실행된다.
  // 처음 앱이 실행될때 변경된것으로 간주하며서 useEffect의 의존성이 변경된것으로 보며 실행된다.
  // 한번 실행되며 변경확인시 실행됨
  useEffect(() => {
    const storedUserLoggedInInformation = localStorage.getItem("isLoggedIn");

    if (storedUserLoggedInInformation === "1") {
      setIsLoggedIn(true);
    }
  }, []);

  const loginHandler = (email, password) => {
    // We should of course check email and password
    // But it's just a dummy/ demo anyways
    localStorage.setItem("isLoggedIn", "1");
    setIsLoggedIn(true);
  };

  const logoutHandler = () => {
    localStorage.removeItem('isLoggedIn')
    setIsLoggedIn(false);
  };

  return (
    <React.Fragment>
      <MainHeader isAuthenticated={isLoggedIn} onLogout={logoutHandler} />
      <main>
        {!isLoggedIn && <Login onLogin={loginHandler} />}
        {isLoggedIn && <Home onLogout={logoutHandler} />}
      </main>
    </React.Fragment>
  );
}

export default App;
