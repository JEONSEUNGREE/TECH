import { useEffect, useState, useRef } from "react";

import useInput from "../hooks/use-input";

const SimpleInput = (props) => {
  const {
    value: enteredName,
    isValid: enteredNameIsValid,
    hasError: nameInputHasError,
    valueChangeHandler: nameChangeHandler,
    inputBlurHandler: nameBlurHanlder,
    reset: resetNameInput
    // 인라인함수로 매개변수 전달
  } = useInput(value => value.trim() !== '');

  const formSubmissionHandler = (event) => {
    event.preventDefault();

    if (!enteredNameIsValid) {
      return;
    }
    resetNameInput();
  };

  const nameInputClasses = nameInputHasError
    ? "form-control invalid"
    : "form-control";

  return (
    <form onSubmit={formSubmissionHandler}>
      <div className={nameInputClasses}>
        <label htmlFor="name">Your Name</label>
        <input
          type="text"
          id="name"
          onChange={nameChangeHandler}
          onBlur={nameBlurHanlder}
          value={enteredName}
        />
        {nameInputHasError && <p>Name must not be empty.</p>}
      </div>
      <div className="form-actions">
        <button disabled={!enteredNameIsValid}>Submit</button>
      </div>
    </form>
  );
};

export default SimpleInput;
