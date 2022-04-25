import "./ExpenseItem.css";

function ExpenseItem() {
  const expenseDate = new Date(2021, 2, 28);
  const expenseTitle = "Car Insurance";
  const expenseAmount = 294.67;

  return (
    <div className="expense-item">
      <div>{expenseDate.toISOString()}</div>
      <div className="expense-item__description">
        <h2>{expenseTitle}</h2>
        {/* <h2>{1+1}</h2> */}
        {/* <h2>{expenseAmount}</h2> */}
        <div className="expense-item__price">${expenseAmount}</div>
      </div>
    </div>
  );
  // jsx코드 조각마다 반드시 한개의 루트 요소를 갖는다
}

export default ExpenseItem;
