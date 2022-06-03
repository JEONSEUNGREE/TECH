import { NavLink } from "react-router-dom";

import React from "react";

import classes from './MainHeader.module.css'

const Mainheader = () => {
  return (
    <header className={classes.header} >
      <nav>
        <ul>
          <li>
            {/* 기존에 a태그를 사용한 화면 전황은 spa 규약에 어긋나도록 되어 새로고침이 일어나지만
            Link to 를 설정함으로써 라우터가 내부적으로 관리하도록 설정함으로 spa에 적합한 설정이 가능하다. 
            앵커태그를 제공하며 클릭이 브라우저 기본값을 방지하고 활성태그에 css클래스를 설정할수있음*/}
            <NavLink to="/welcome" activeClassName={classes.active} >Welcome</NavLink>
          </li>
          <li>
            <NavLink to="/product" activeClassName={classes.active}>Products</NavLink>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Mainheader;
