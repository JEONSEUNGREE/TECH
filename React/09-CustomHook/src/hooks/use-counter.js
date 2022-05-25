import { useState, useEffect } from "react";

// 커스텀 훅의 시작은 use로 해야됨(기본 룰)
// 위와같이 표기하는 이유는 use로 리액트 훅을 사용함을 리액트 셋업과함께 하기위해 알리고
// 기본적인 리액트 훅 룰에 위배되지않게하기위함
// 리액트 훅은 다른 함수에서 사용이 불가하기때문에 컴포넌트함수에서 사용함.
const useCounter = (forwards = true) => {
  const [counter, setCounter] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      if (forwards) {
        setCounter((prevCounter) => prevCounter + 1);
      } else {
        setCounter((prevCounter) => prevCounter - 1);
      }
    }, 1000);

    return () => clearInterval(interval);
  }, [forwards]);

  return counter;
};

export default useCounter;
