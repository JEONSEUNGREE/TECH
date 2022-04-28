import React, { useState } from "react";
import ExpenseItem from "./ExpenseItem";
import ExpensesFilter from "./ExpensesFilter";
import "./Expenses.css";
import "./Card.css";
import Card from "./Card";

function Expenses(props) {
  const [filteredYear, setFilteredYear] = useState("2020");

  const filterChangeHandler = (selectedYear) => {
    setFilteredYear(selectedYear);
  };

  const fileteredExpenses = props.items.filter((expense) => {
    return expense.date.getFullYear().toString() === filteredYear;
  });

  let expenseContent = <p>No expense found.</p>;

  if (fileteredExpenses.length > 0) {
    expenseContent = fileteredExpenses.map((expense) => (
      <ExpenseItem key={expense.id}
        title={expense.title}
        amount={expense.amount}
        date={expense.date}
      />
    ))
  }

  return (
    <div>
      <Card className="expenses">
        <ExpensesFilter
          selected={filteredYear}
          onChangedFilter={filterChangeHandler}
        />
        {/* {fileteredExpenses.length === 0 && <p>No expenses found.</p> }
        {fileteredExpenses.map((expense) => (
          <ExpenseItem key={expense.id}
            title={expense.title}
            amount={expense.amount}
            date={expense.date}
          /> */}
        {/* ))} */}
        {expenseContent}
      </Card>
    </div>
  );
}

export default Expenses;
