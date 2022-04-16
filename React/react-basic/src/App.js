import './App.css';
import React, { useState, useEffect } from 'react';

function App() {
//  state가 변경될때마다 리렌더링됨
  const [ count, setCount ] = useState(0);
  const [ kossie, setKossie ] = useState(0);

  useEffect(() => {
    console.log(count)
  }, [count,kossie]);

  useEffect(() => {
    console.log("first Rendering")
  }, [])

  const increment = () => {
    setCount(count + 1)
  }

  return (
    <div className="App">
      <h1>KossieCoder</h1>
      <div value={count}></div>
      <button onClick={increment}>Click</button>
      <button onClick={() => setKossie(kossie + 1)}>Click1</button>
    </div>
  );
}

export default App;
