import React, { useRef, useImperativeHandle } from "react";
// useImperativeHandle
// 컴포넌트나 컴포넌트 내부에서 오는 기능을 명령적으로 실행함다.
// 잘 사용하지않는 방법
import classes from "./Input.module.css";

// ref를 사용하기위해서 forwardRef함수 사용 
// forwardRef는 리액트 컴포넌트를 반환한다.
const Input = React.forwardRef((props, ref) => {

    const inputRef = useRef();

    const activate = () => {
        inputRef.current.focus();
    }

    useImperativeHandle(ref, () => {
        return {
            focus: activate
        }
    });

  return (
    <div
      className={`${classes.control} ${
        props.isValid === false ? classes.invalid : ""
      }`}
    >
      <label htmlFor={props.id}>{props.label}</label>
      <input
      ref={inputRef}
        type={props.type}
        id={props.id}
        value={props.value}
        onChange={props.onChange}
        onBlur={props.onBlur}
      />
    </div>
  );
});

export default Input;
