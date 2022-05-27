import { useEffect, useState, useRef } from "react";

const SimpleInput = (props) => {
  const [enteredName, setEnteredName] = useState("");
  const [enteredNameTouched, setEenteredNameTouched] = useState(false);
  // const [formIsValid, setFormIsValid] = useState(false);

  const enteredNameIsValid = enteredName.trim() !== '';
  const nameInputIsInvalid = !enteredNameIsValid && enteredNameTouched;

  // 아래 useEffect의 경우 재평가가 일어나기에 이를 좀더 효율적으로 작성한코드
  // useEffect(() => {
  //   console.log("test")
  //   if (enteredNameIsValid) {
  //     setFormIsValid(true);
  //   } else {
  //     setFormIsValid(false);
  //   }
  // },[enteredNameIsValid]);
console.log("finish")
  let formIsValid = false;

  if (enteredNameIsValid) {
    formIsValid = true;
  } else {
    formIsValid = false;
  }


  const nameInputChangeHandler = (event) => {
    setEnteredName(event.target.value);
  };
  const nameInputBlurHandler = event => {
    setEenteredNameTouched(true);
  }

  const formSubmissionHandler = (event) => {
    event.preventDefault();

    setEenteredNameTouched(true);

    if (!enteredNameIsValid) {
      return;
    }

    // console.log(enteredName);
    // const enteredValue = nameInputRef.current.value;
    // console.log(enteredValue);

    // useState 와 ref 중에 선택해서 사용한다면
    // 즉각적으로 유효성 검증에 사용하는경우 useState로 읽는것이 좋다 매순간 체크하기때문이기도하고 마지막 제출시 useState를 null로 두어 비우기도 되기때문이다.
    // ref의 경우 한번만 제출하는 양식에 좋다. 매순간 굳이 업데이트가 필요하지않은경우에 사용하면 효율적으로 연산이 한번수행되기때문
    // nameInputRef.current.value = "";
    // 직접 바닐라 자바스크립트로 DOM에 접근하는 방법은 지양하도록해야한다.
    setEnteredName("");
    setEenteredNameTouched(false);
  };


  const nameInputClasses = nameInputIsInvalid
    ? "form-control invalid"
    : "form-control";

  return (
    <form onSubmit={formSubmissionHandler}>
      <div className={nameInputClasses}>
        <label htmlFor="name">Your Name</label>
        <input
          type="text"
          id="name"
          onChange={nameInputChangeHandler}
          onBlur={nameInputBlurHandler}
          value={enteredName}
        />
        {nameInputIsInvalid && <p>Name must not be empty.</p>}
      </div>
      <div className="form-actions">
        <button disabled={!formIsValid}>Submit</button>
      </div>
    </form>
  );
};

export default SimpleInput;
