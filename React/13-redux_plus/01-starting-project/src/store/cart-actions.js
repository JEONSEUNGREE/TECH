import { uiActions } from "./ui-slice";
import { cartActions } from "./cart-slice";

const BASE_API = 'https://react-http-dd4c9-default-rtdb.firebaseio.com/'

export const fetchCartData = () => {
    return async (dispatch) => {
        const fetchData = async () => {
            const res = await fetch(`${BASE_API}cart.json`);
            
            if (!res.ok) {
                throw new Error('Could not fetch cart data!')
            }
            const data = await res.json();

            return data;
        };

        try {
            const cartData = await fetchData();
            console.log(cartData)
            dispatch(cartActions.replaceCart({
                items: cartData.items || [],
                totalQuantity: cartData.totalQuantity
            }));
        } catch(error) {

        }
    }
}


export const sendCartData = (cart) => {
    return async (dispatch) => {
      dispatch(
        uiActions.showNotification({
          status: "pending",
          title: "sending...",
          message: "sending cart data!",
        })
      );
  
      const sendRequeset = async () => {
        const res = await fetch(
          `${BASE_API}cart.json`,
          {
            method: "PUT",
            body: JSON.stringify(cart),
          }
        );
  
        if (!res.ok) {
          throw new Error("Sending cart data failed");
        }
      };
  
      try {
        await sendRequeset();
        dispatch(
          uiActions.showNotification({
            status: "Success",
            title: "Success!",
            message: "Success receive Data!",
          })
        );
      } catch {
        dispatch(
          uiActions.showNotification({
            status: "error",
            title: "Error!",
            message: "Error receive Data!",
          })
        );
      }
    };
  };
  