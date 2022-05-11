import React from "react";

import Input from "../../UI/Input";

import classes from "./MealItemForm.module.css";

const Mealitemform = (props) => {
  return (
    <form className={classes.form}>
      <div>
        <Input
          label="Amount"
          input={{
            id: "amount_" + props.id,
            type: "number",
            min: "1",
            max: "5",
            step: "1",
            defaultValue: "2",
          }}
        />
        <button>+ Add</button>
      </div>
    </form>
  );
};

export default Mealitemform;
