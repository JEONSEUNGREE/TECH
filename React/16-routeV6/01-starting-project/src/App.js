import { Route, Switch, Routes, Navigate } from "react-router-dom";

import Welcome from "./pages/Welcome";

import Product from "./pages/Products";

import MainHeader from "./components/MainHeader";

import Productdeatil from "./pages/ProductDetail";

function App() {
  return (
    <div>
      <MainHeader />
      <main>
        <Routes>
          <Route path="/" element={<Navigate replace to="/welcome" />} />
          <Route path="/welcome/*" element={<Welcome />} />
          <Route path="/product" element={<Product />} />
          <Route path="/product/:productId" element={<Productdeatil />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;
