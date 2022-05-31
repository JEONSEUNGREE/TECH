import { createSlice } from "@reduxjs/toolkit";

const cartSlice = createSlice({
  name: "cart",
  initialState: {
    items: [],
    totalQuantity: 0,
  },
  reducers: {
    addItemToCart(state, action) {
      const newItem = action.payload;
      console.log(newItem.id)
      const existingItem = state.items.find((item) => item.id === newItem.id);
      state.totalQuantity++;
      if (!existingItem) {
        console.log("test")
        state.items.push({
          id: newItem.id,
          price: newItem.price,
          quantity: 1,
          totalPrice: newItem.price,
          name: newItem.title,
        });
      } else {
        console.log(existingItem.quantity)
        existingItem.quantity++;
        existingItem.totalPrice = existingItem.totalPrice + newItem.price;
      }
  },
    removeItemToCart(state, action) {
      const id = action.payload;
      console.log(id)
      const existingItem = state.items.find(item => item.id === id);
      state.totalQuantity--;

      if (existingItem.quantity === 1) {
        state.items = state.items.filter(item => item.id !== id);
      } else {
        existingItem.quantity--;
      }
    },
  },
});

export const cartActions = cartSlice.actions;

export default cartSlice;