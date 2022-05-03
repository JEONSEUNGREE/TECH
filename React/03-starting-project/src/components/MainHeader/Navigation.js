import React, { useContext } from "react";

import AuthContext from "../store/Auth-Context";
import classes from "./Navigation.module.css";

const Navigation = (props) => {
  const ctx = useContext(AuthContext);
  return (
    // <AuthContext.Consumer>
      // {/* context를 사용하기위해서는 Provider가 필요가 없다. 
      // default가 있다면 공급자가 필요 없다는 의미 */}
      // {(ctx) => {
        // return (
          <nav className={classes.nav}>
            <ul>
              {ctx.isLoggedIn && (
                <li>
                  <a href="/">Users</a>
                </li>
              )}
              {ctx.isLoggedIn && (
                <li>
                  <a href="/">Admin</a>
                </li>
              )}
              {ctx.isLoggedIn && (
                <li>
                  <button onClick={props.onLogout}>Logout</button>
                </li>
              )}
            </ul>
          </nav>
        // );
      // }}
    // </AuthContext.Consumer>
  );
};

export default Navigation;
