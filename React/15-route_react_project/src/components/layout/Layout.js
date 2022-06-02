import React from "react";
import { Fragment } from "react/cjs/react.production.min";

import Mainnavigation from "./MainNavigation";
import classes from "./Layout.module.css";

const Layout = (props) => {
  return (
    <Fragment>
      <Mainnavigation />
      <main classes={classes.main}>{props.children}</main>
    </Fragment>
  );
};

export default Layout;
