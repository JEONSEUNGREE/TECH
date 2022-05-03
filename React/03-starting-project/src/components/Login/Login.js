import React, { useState, useEffect, useReducer } from "react";

import Card from "../UI/Card/Card";
import classes from "./Login.module.css";
import Button from "../UI/Button/Button";

// reducer 함수는 외부에 선언한다 이유는 함수 내부에서 어떤 데이터도 필요로하지않기때문
const emailReducer = (state, action) => {
  if (action.type === "USER_INPUT") {
    return {
      value: action.val,
      isValid: action.val.includes("@")
    };
  }
  if (action.type === "INPUT_BLUR") {
    return { value: state.value, isValid: state.value.includes("@") }
  }

  return {
    value: "",
    isValid: false,
  };
};

const Login = (props) => {
  // const [enteredEmail, setEnteredEmail] = useState("");
  // const [emailIsValid, setEmailIsValid] = useState();
  const [enteredPassword, setEnteredPassword] = useState("");
  const [passwordIsValid, setPasswordIsValid] = useState();
  const [formIsValid, setFormIsValid] = useState(false);

  const [emailState, dispatchEmail] = useReducer(emailReducer, {
    value: '',
    isValid: null
  });

  // useEffect(() => {
  //   console.log("EFFECT RUNNING");

  //   return () => {
  //     console.log("first CleanUp");
  //   };
  // }, [enteredEmail]);

  // useEffect(() => {
  //   console.log("Checking form validity")

  //   // 타이머를 설정하면 타이머가 여러개 설정되었을시 마지막 타이머만 작동한다.
  //   const identifier = setTimeout(() => {
  //     setFormIsValid(
  //       enteredEmail.includes("@") && enteredPassword.trim().length > 6
  //     );
  //   }, 500);

  //   // 클린업 함수 클린업 프로세스로 실행함
  //   // 클린업 함수는 첫번째 컴포넌트 실행시 실행을 제외하고 이후에 실행시에 발생하는
  //   // useEffect 실행 이전에 실행된다.
  //   // DOM에 마운트가 해제될 때마다 실행된다.
  //   // 정리하자면 모든 새로운 사이드 이펙트함수가 실행되기전 (처음실행 되기전에는 실행되지 않음)
  //   // 컴포넌트가 제거되기 전에 실행된다.
  //   return () => {
  //     console.log("CLEAN UP")
  //     clearTimeout(identifier)
  //   };
  //   // 앱 초기 한번만 실행되거나 컴포넌트 변화 감지에서 작동함으로
  //   // 의존성부분을 추가하여 변화에 동작할수있게 작성하면된다.
  //   // useEffect는 주로 사이드이펙트에 부분에서 사용하며 http 리퀘스트, 키입력 데이터 저장등에 사용된다.
  //   // 단 어떤 하나하나의 동작마다 useEffect가 작동한다면 불필요한 작업이되기에
  //   // 필요한부분에서 작동하도록 하는 조치가 필요하다.
  //   // http요청에대해서는 더더욱 트래픽이 증가하기에 알맞는 트리거를 설정할필요가있다.
  //   // setFormIsValid(
  //   //   enteredEmail.includes("@") && enteredPassword.trim().length > 6
  //   // );
  // }, [setFormIsValid, enteredEmail, enteredPassword]);

  const emailChangeHandler = (event) => {
    dispatchEmail({type: 'USER_INPUT', val: event.target.value})

    // 아래부분에서 발생할 수 있는문제는 다음과같다.
    // 일전에 봤듯이 기존 스냅샷에 () => 와 같이 최산 스냅샷을 가져와서
    // 업데이트를 해야하는데 아래 코드는 업데이트 state가 이전 state에 의존할수없는
    // 두개의 스냅샷이 존재하기에 불가능한것이다.
    // 리액트의 state 업데이트를 스케쥴링하는 방식에 주의하자.
    // 비밀번호가 최신의 비밀번호로 업데이트 된 state가 아닐수있다는 의미이다.
    setFormIsValid(
      emailState.isValid && enteredPassword.trim().length > 6
    );
  };

  const passwordChangeHandler = (event) => {
    setEnteredPassword(event.target.value);

    setFormIsValid(
      emailState.isValid && event.target.value.trim().length > 6
    );
  };

  const validateEmailHandler = () => {
    // setEmailIsValid(emailState.isValid);
    dispatchEmail({type: 'INPUT_BLUR'})
  };

  const validatePasswordHandler = () => {
    setPasswordIsValid(enteredPassword.trim().length > 6);
  };

  const submitHandler = (event) => {
    event.preventDefault();
    props.onLogin(emailState.value, enteredPassword);
  };

  return (
    <Card className={classes.login}>
      <form onSubmit={submitHandler}>
        <div
          className={`${classes.control} ${
            emailState.isValid === false ? classes.invalid : ""
          }`}
        >
          <label htmlFor="email">E-Mail</label>
          <input
            type="email"
            id="email"
            value={emailState.value}
            onChange={emailChangeHandler}
            onBlur={validateEmailHandler}
          />
        </div>
        <div
          className={`${classes.control} ${
            passwordIsValid === false ? classes.invalid : ""
          }`}
        >
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={enteredPassword}
            onChange={passwordChangeHandler}
            onBlur={validatePasswordHandler}
          />
        </div>
        <div className={classes.actions}>
          <Button type="submit" className={classes.btn} disabled={!formIsValid}>
            Login
          </Button>
        </div>
      </form>
    </Card>
  );
};

export default Login;
