import React from "react";
import Expenses from "./components/Exoenses";
import NewExpense from "./components/NewExpense";


function App() {
  const expenses = [
    {
      id: "e1",
      title: "Toilet Paper",
      amount: 294.67,
      date: new Date(2021, 2, 28),
    },
    {
      id: "e2",
      title: "Car Inusurance",
      amount: 294.67,
      date: new Date(2021, 2, 28),
    },
    {
      id: "e3",
      title: "new Desk",
      amount: 294.67,
      date: new Date(2021, 2, 28),
    },
    {
      id: "e4",
      title: "Car Inusurance",
      amount: 294.67,
      date: new Date(2021, 2, 28),
    },
  ];

  const addExpenseHandler = (expense) => {
    console.log("In App.js")
    console.log(expense)
    
  }

  return (
    <div className="App">
      <NewExpense onSaveExpenseData={addExpenseHandler}/>
      <Expenses items={expenses}/>
    </div>
  );
}

export default App;
