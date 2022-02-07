// example : https://v5.reactrouter.com/web/example/basic

import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import LoginPage from './components/views/LoginPage/LoginPage';
import RegisterPage from './components/views/RegisterPage/RegisterPage';
import LadingPage from "./components/views/LandingPage/LadingPage";

function App() {
  return (
    <Router>
      <div>
        {/* <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/LoginPage">LoginPage</Link>
          </li>
          <li>
            <Link to="/RegisterPage">RegisterPage</Link>
          </li>
        </ul> */}

        <Routes>
          <Route path="/" element={<LadingPage />}>
          </Route>
          <Route path="/LoginPage" element={<LoginPage />}>
          </Route>
          <Route path="/RegisterPage" element={<RegisterPage />}>
          </Route>
        </Routes>
      </div>
    </Router>
  );
}

// You can think of these components as "pages"
// in your app.

// function Home() {
//   return (
//     <div>
//       <h2>Home</h2>
//     </div>
//   );
// }

// function About() {
//   return (
//     <div>
//       <h2>About</h2>
//     </div>
//   );
// }

// function Dashboard() {
//   return (
//     <div>
//       <h2>Dashboard</h2>
//     </div>
//   );
// }


export default App;
