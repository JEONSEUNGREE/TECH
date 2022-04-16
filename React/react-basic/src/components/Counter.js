import React, { useState } from "react";

const Counter = (props) => {

  const [ count, setCount ] = useState(0);

  const nameString = props.name || 'default';

  const increment = () => {
    setCount(count + 1) ;
  };

  return (
      <button onClick={increment}> 
        {nameString} {count}
      </button>
  )

}

export default Counter;