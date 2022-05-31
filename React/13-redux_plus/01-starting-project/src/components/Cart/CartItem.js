import classes from './CartItem.module.css';

import { cartActions } from '../../store/cart-slice';

import { useDispatch } from 'react-redux'

const CartItem = (props) => {
  const { title, quantity, totalPrice, price, id } = props.item;
  console.log(id)
  console.log(title)
  const dispatch = useDispatch();

  const removeItemHandler = () => {
    dispatch(cartActions.removeItemToCart(id));
  }

  const addItemHandler = () => {
    dispatch(cartActions.addItemToCart({
      id,
      title,
      price
    }))
  }

  return (
    <li className={classes.item}>
      <header>
        <h3>{title}</h3>
        <div className={classes.price}>
          ${totalPrice}{' '}
          <span className={classes.itemprice}>(${price}/item)</span>
        </div>
      </header>
      <div className={classes.details}>
        <div className={classes.quantity}>
          x <span>{quantity}</span>
        </div>
        <div className={classes.actions}>
          <button onClick={removeItemHandler}>-</button>
          <button onClick={addItemHandler}>+</button>
        </div>
      </div>
    </li>
  );
};

export default CartItem;
