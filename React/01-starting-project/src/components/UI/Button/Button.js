import React from "react";

import styles from './Button.module.css';
// module.css 임포트
// import styled from "styled-components";

// import "./Button.css";
// // styled Component는 해당 컴포너트에만 Css 영향을 주고 다른 컴포넌트에 영향을 주지않음
// // 따라서 중복된 이름의 Css의 문제를 피할수있다.
// // 일반함수형 선언 대신 아래와같이 선언한다.
// // ``백틱으로 태그드 템플릿 리터럴 선언
// // 아래 버튼을 개발자도구로확인하면 클래스네임이 특이하게 생성되는데 고유한 이름을 갖게되는 형태이기때문이다.
// const Button = styled.button`
//   font: inherit;
//   padding: 0.5rem 1.5rem;
//   border: 1px solid #8b005d;
//   color: white;
//   background: #8b005d;
//   box-shadow: 0 0 4px rgba(0, 0, 0, 0.26);
//   cursor: pointer;
//   width: 100%

//   @media (min-width: 768px) {
//     width: auto;
//   }

//   &:focus {
//     outline: none;
//   }


//   &:hover,
//   &:active {
//     background: #ac0e77;
//     border-color: #ac0e77;
//     box-shadow: 0 0 8px rgba(0, 0, 0, 0.26);
//   }
// `;

const Button = props => {
  return (
    // css 모듈적용
    <button type={props.type} className={styles.button} onClick={props.onClick}>
      {props.children}
    </button>
  );
};

export default Button;
