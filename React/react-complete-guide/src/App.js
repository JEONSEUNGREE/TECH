import React, { useState } from "react";
import Expenses from "./components/Exoenses";
import NewExpense from "./components/NewExpense";


function App() {
  const DUMMY_EXPENSES = [

  ];

  const [expenses, setExpense] = useState(DUMMY_EXPENSES);

  const addExpenseHandler = (expense) => {
    setExpense((prevExpense) => {
      return[expense, ...prevExpense]
    });
  };

  return (
    <div className="App">
      <NewExpense onSaveExpenseData={addExpenseHandler}/>
      <Expenses items={expenses}/>
    </div>
  );
}

export default App;
