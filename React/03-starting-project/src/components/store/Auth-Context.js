import React, { useState, useEffect } from "react";

const AuthContext = React.createContext({
  isLoggedIn: false,
  onLogout: () => {},
  onLogin: (email, password) => {},
});

export const AuthContextProvider = (props) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // 한번 실행되며 변경확인시 실행됨
  useEffect(() => {
    const storedUserLoggedInInformation = localStorage.getItem("isLoggedIn");

    if (storedUserLoggedInInformation === "1") {
      setIsLoggedIn(true);
    }
  }, []);

  const logoutHandler = () => {
    localStorage.removeItem("isLoggedIn");
    setIsLoggedIn(false);
  };

  const loginHandler = () => {
    localStorage.setItem("isLoggedIn", "1");
    setIsLoggedIn(true);
  };

  return (
    <AuthContext.Provider
      value={{
        isLoggedIn: isLoggedIn,
        onLogin: loginHandler,
        onLogout: logoutHandler,
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthContext;

// 리액트 규칙
// 리액트 컨텍스트는 자주 바뀌는 화면에서 사용하지않는게 좋다. (최적화되어있지않음)
// 값이 자주 바뀌는 화면의 경우 state를 사용한다.

// 리액트 훅은 (use로 시작하는 모든함수 useEffect,useState,useContext....), 혹은 커스터훅은 리액트 함수 내에서만 호출해야한다.
// nested 불가, 리액트 훅은 콜백함수나 블록문에서 사용 불가(최상위 컴포넌트 함수에서 호출 가능)