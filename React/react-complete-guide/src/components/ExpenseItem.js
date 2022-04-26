import "./ExpenseItem.css";
import ExpenseDate from "./ExpenseDate";

function ExpenseItem({ title, amount, date }) {
  

  return (  
    <div className="expense-item">
      <ExpenseDate date={date}/>
      <div className="expense-item__description">
        <h2>{title}</h2>
        {/* <h2>{1+1}</h2> */}
        {/* <h2>{expenseAmount}</h2> */}
        <div className="expense-item__price">${amount}</div>
      </div>
    </div>
  );
  // jsx코드 조각마다 반드시 한개의 루트 요소를 갖는다
}

export default ExpenseItem;
