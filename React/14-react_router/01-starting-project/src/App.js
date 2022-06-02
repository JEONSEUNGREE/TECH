import { Route, Switch, Redirect } from "react-router-dom";

import Welcome from "./pages/Welcome";

import Product from "./pages/Products";

import MainHeader from "./components/MainHeader";

import Productdeatil from "./pages/ProductDetail";

function App() {
  return (
    <div>
      <MainHeader />
      <main>
        <Switch>
          <Route path='/' exact>
            <Redirect to='/welcome' />
          </Route>
          <Route path="/welcome">
            <Welcome />
          </Route>
          <Route path="/product" exact>
            <Product />
          </Route>
          {/* /:productId는 다이나믹 세그먼트이다. 여러값을 이어서 갖을수도있음 
        또한 url 중간에 중첩되면 같은 화면이 위에 출력되면서 아래 출력된다 엔드포인트 여부에 상괍없이
      이러한 현상 방지를 v5 swich v6 route를 이용해서 엔드포인트 차이에 포인트를 두어 화면 표시하나만 활성화 하도록 설정할 수 있디.*/}

      {/* 먼저 출력될 순서에 맞게 위에서부터 나열하던가 아니면 exat props를 활용해서 정확히 매칭되는 화면만 표시할수있다. */}
          <Route path="/product/:productId">
            <Productdeatil />
          </Route>
        </Switch>
      </main>
    </div>
  );
}

export default App;
