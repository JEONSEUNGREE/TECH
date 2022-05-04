import React, { Fragment } from "react";

import Availablemeals from "./AvailableMeals";
import MealsSummary from "./MealsSummary";

const Meals = () => {
  return (
    <Fragment>
      <MealsSummary />
      <Availablemeals />
    </Fragment>
  );
};

export default Meals;
