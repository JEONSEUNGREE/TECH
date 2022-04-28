import React, { useState } from "react";

import ExpenseForm from "./ExpenseForm";
import "./NewExpens.css";

const NewExpense = (props) => {
  const [isEditing, setEditing] = useState(false);

  const saveExpenseDataHandler = (enteredExpenseData) => {
    const expenseData = {
      ...enteredExpenseData,
      id: Math.random().toString()
    }
    props.onSaveExpenseData(expenseData)
  }

  const startEditingHandler = () => {
    setEditing(true);
  }

  const stopEditingHandler = () => {
    setEditing(false);
  }

  return (
    <div className="new-expense">
      {!isEditing && <button onClick={startEditingHandler}>Add new Expense</button>}
      {isEditing && <ExpenseForm onCancel={stopEditingHandler} onSaveExpenseData={saveExpenseDataHandler}/>}
    </div>
  );
};

export default NewExpense;
