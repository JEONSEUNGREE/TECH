import React, { useState } from "react";

import "./ExpenseForm.css";

const ExpenseForm = () => {
  const [enteredTitle, setEnteredTitle] = useState("");
  const [enteredAmount, setEnterdAmount] = useState("");
  const [enteredDate, setEnterdDate] = useState("");

// const [userInput, setUserInput] = useState ({
//     enteredTitle: '',
//     enteredAmount: '',
//     entneredDate: ''
// });


  const titleChangeHandler = (event) => {
    setEnteredTitle(event.target.value);
    // setUserInput({
    //     // state가 덮어써지기에 기존 배열을 복붙하고 뒤에 덮어쓰는 형태를 취한다.
    //     ...userInput,
    //     enteredTitle: event.target.value
    // })
    // 위의 함수는 잘못될 수 있다. 무결성이 깨질수있다는 의미
    // 여러값에서 동시에 변경시 최신이 아닌 기존값에서 덮어써질 수있다.

    // 아래와같이 최신의 스냅샷을 통한 값을 지정해야 무결성이 보장된다.
    // setUserInput((prevState) => {
    //     return { ...prevState, enteredTitle: event.target.value}
    // })
  };

  const amountChanedHandler = (event) => {
    setEnterdAmount(event.target.value);
    // setUserInput({
    //     ...userInput,
    //     enteredAmount: event.target.value
    // })
  };

  const dateChangeHandler = (event) => {
      setEnterdDate(event.target.value)
    // setUserInput({
    //     ...userInput,
    //     entneredDate: event.target.value
    // })
  };

  return (
    <form>
      <div className="new-expense__controls">
        <div className="new-expense__control">
          <label>Title</label>
          <input type="text" onChange={titleChangeHandler} />
        </div>
        <div className="new-expense__control">
          <label>Amount</label>
          <input
            type="number"
            min="0.01"
            step="0.01"
            onChange={amountChanedHandler}
          />
        </div>
        <div className="new-expense__control">
          <label>Date</label>
          <input
            type="date"
            min="2019-01-01"
            max="2022-12-31"
            onChange={dateChangeHandler}
          />
        </div>
        <div className="new-expense__actions">
          <button type="submit">Add Expense</button>
        </div>
      </div>
    </form>
  );
};

export default ExpenseForm;
