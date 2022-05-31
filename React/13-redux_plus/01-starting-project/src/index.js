import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import store from "./store";

import "./index.css";
import App from "./App";

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);

// redux의 reducer함수는 순수함수여야하며 부수효과 없이 동기식이어야한다.
// 리덕스에 비동기 방식의 요청등이 있어야하는 부분은 useEffect를 사용하거나 actions creator 툴킷 함수를 사용하지않는것등이있다.
