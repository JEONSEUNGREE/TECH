import { createSlice } from "@reduxjs/toolkit";

const cartSlice = createSlice({
  name: "cart",
  initialState: {
    items: [],
    totalQuantity: 0,
    changed: false
  },
  // 리듀서는 비동기함수요청이나 변경을 할수없다(오버라이딩)
  // 혹은 외부에서 강제로 변경할수없다.
  // 따라서 비동기 처리를할경우 useEffect를 사용거나 thunk함수를 사용하는데
  // thunk함수는 특정함수가 동작을 완료할때까지 대기하도록 돕는 함수이다.
  reducers: {
    replaceCart(state, action) {
      state.totalQuantity = action.payload.totalQuantity;
      state.items = action.payload.items;
    },
    addItemToCart(state, action) {
      const newItem = action.payload;
      console.log(newItem.id);
      const existingItem = state.items.find((item) => item.id === newItem.id);
      state.totalQuantity++;
      state.changed = true;
      if (!existingItem) {
        console.log("test");
        state.items.push({
          id: newItem.id,
          price: newItem.price,
          quantity: 1,
          totalPrice: newItem.price,
          name: newItem.title,
        });
      } else {
        console.log(existingItem.quantity);
        existingItem.quantity++;
        existingItem.totalPrice = existingItem.totalPrice + newItem.price;
      }
    },
    removeItemToCart(state, action) {
      const id = action.payload;
      console.log(id);
      const existingItem = state.items.find((item) => item.id === id);
      state.totalQuantity--;
      state.changed = true;
      if (existingItem.quantity === 1) {
        state.items = state.items.filter((item) => item.id !== id);
      } else {
        existingItem.quantity--;
      }
    },
  },
});

// export const sendCartData = (cart) => {
//   return async (dispatch) => {
//     dispatch(
//       uiActions.showNotification({
//         status: "pending",
//         title: "sending...",
//         message: "sending cart data!",
//       })
//     );

//     const sendRequeset = async () => {
//       const res = await fetch(
//         "https://react-http-dd4c9-default-rtdb.firebaseio.com/cart.json",
//         {
//           method: "PUT",
//           body: JSON.stringify(cart),
//         }
//       );

//       if (!res.ok) {
//         throw new Error("Sending cart data failed");
//       }
//     };

//     try {
//       await sendRequeset();
//       dispatch(
//         uiActions.showNotification({
//           status: "Success",
//           title: "Success!",
//           message: "Success receive Data!",
//         })
//       );
//     } catch {
//       dispatch(
//         uiActions.showNotification({
//           status: "error",
//           title: "Error!",
//           message: "Error receive Data!",
//         })
//       );
//     }
//   };
// };

export const cartActions = cartSlice.actions;

export default cartSlice;
