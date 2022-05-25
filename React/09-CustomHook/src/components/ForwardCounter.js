// import { useState, useEffect } from 'react';

import Card from './Card';

import useCounter from '../hooks/use-counter';

const ForwardCounter = () => {

  // 커스텀 훅이 공유된다고해서 동일한것이아닌 
  // 각자의 인스턴스가 존재함
  const counter = useCounter();
  // const [counter, setCounter] = useState(0);

  // useEffect(() => {
  //   const interval = setInterval(() => {
  //     setCounter((prevCounter) => prevCounter + 1);
  //   }, 1000);

  //   return () => clearInterval(interval);
  // }, []);

  return <Card>{counter}</Card>;
};

export default ForwardCounter;
