import React, { useContext, useEffect, useState } from "react";

import classes from "./HeaderCartButton.module.css";
import CartIcon from "../Cart/CartIcon";
import CartContext from "../../store/Cart-Context";

const Headercartbutton = (props) => {

  const [btnIsHightlighted, setbtnIsHightlighted] = useState(false);

  const cartCtx = useContext(CartContext);

  const numberOfCartItems = cartCtx.item.reduce((curNumber, item) => {
    return curNumber + item.amount;
  }, 0);

  const btnClasses = `${classes.button} ${btnIsHightlighted ? classes.bump : ''}`;

  const { item } = cartCtx;

  useEffect(() => {
    if (cartCtx.item.legnth === 0) {
      return;
    }
    setbtnIsHightlighted(true);

    const timer = setTimeout(() => {
      setbtnIsHightlighted(false);
    }, 300)

    return() => {
      clearTimeout(timer);
    }
  }, [item]);


  return (
    <button className={btnClasses} onClick={props.onClick}>
      <span className={classes.icon}>
        <CartIcon />
      </span>
      <span>Your Cart</span>
      <span className={classes.badge}>{numberOfCartItems}</span>
    </button>
  );
};

export default Headercartbutton;
