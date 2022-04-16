import './App.css';
import React, { useState, useEffect } from 'react';
import Counter from './components/Counter'

function App() {

  const [condition, setConditon] = useState(false);

  const clickButton = () => {
    setConditon(!condition);
  }

  const renderCondition = condition
    ? "True"
    : "False"

  useEffect(() => {
    console.log(condition)
  })

  return (
    <div className='app'>
      <div>Kossie Coder</div>
      <div>
        {renderCondition}
      </div>
      <button onClick={clickButton}>change</button>
    </div>
  )
}

export default App;
