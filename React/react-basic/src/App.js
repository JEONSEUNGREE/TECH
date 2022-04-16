import './App.css';
import React, { useState, useEffect } from 'react';
import Counter from './components/Counter'

function App() {

  const [ buttonName, setButtonName ] = useState("클릭");

  const clickButton = () => {
    setButtonName("click");
  }



// //  state가 변경될때마다 리렌더링됨
//   const [ count, setCount ] = useState(0);
//   const [ count1, setCount1 ] = useState(0);
//   const [ count2, setCount2 ] = useState(0);

//   const increment = () => {
//     setCount(count + 1)
//   }

//   const increment1 = () => {
//     setCount1(count1 + 1)
//   }

//   const increment2 = () => {
//     setCount2(count2 + 1) 
//   }

  return (
    <div className="App">
      <h1>KossieCoder</h1>
      <Counter name="seungRee"/>
      <Counter name={buttonName}/>
      <Counter/>
      <button onClick={clickButton}>click</button>
    </div>
  );
}

export default App;
