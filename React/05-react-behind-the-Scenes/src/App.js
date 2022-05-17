import React, { useState, useCallback } from "react";

import "./App.css";

import Button from "./components/UI/Button/Button";
import Demooutput from "./components/UI/Demo/DemoOutput";

function App() {
  const [showParagraph, setShowParagraph] = useState(false);
  const [allowToggle, setAllowToggle] = useState(false);

  console.log("APP RUNNING");

  const toggleParagrahHandler = useCallback(() => {
    // 2)
    // 아래와 같이 토글을 두었을때 아무 콘솔도 출력되지않는다.
    // 이유는 자바스크립트에서 함수는 클로저이고 useCallback을 제대로 사용하지 않아서이다.
    // 자바스크립트는 함수 사용시 이 안에서 사용되는 모든 변수를 잠그게 된다.(함수 블록안에서)
    // 초기에 toggleParagraphHandler가 실행시 인자인 allowToggle 상수를 그대로 사용하게된다.
    // 따라서 이변수가 저장된 시점의 값을 사용하게된다.(그대로 유지된다는의미)
    // 이 토글함수 실행시 리액트는 이  함수를 재생성하지 않는다. 이미 useCallback으로 막아놨기때문이다. 
    // 따라서 allowToggle이라는 인자는 최신의 것이아닌 예전의 것이 그대로 입려되어서 재실행되지않는다.(useCallback의 특성)
    // 바라보는 값을 [] 두번째 인자 자리에 두고 변경값에 따라서 새로운 함수를 생성하게 하는 것이 솔루션이 될 수 있다.
    if (allowToggle) {
      setShowParagraph((prevShowParagraph) => !prevShowParagraph);
    }
  }, [allowToggle]);

  // 1)
  // 버튼에 react.memo를 적용햇음에도 console이 찍힘을 확인할수있는데
  // 즉 toggleParagrahHandler가 여전히 일반 함수처럼 실행되는데
  // app 함수 내부에 함수는 매번 다시 새로운 함수로 재실행된다는 의미이다.
  // 새로운 함수 주소값이 전달됨으로 원시값이 아닌 참조값 비교로 이루어진 의미이다.
  // 방법은 useCallback에 있다.
  // useCallBack은 컴포넌트 실행 전반에 걸쳐 함수를 저장할 수 있게 하는 훅으로
  // 리액트에 이 함수를 저장하고 매번 실행할때마다 이 함수를 재생성할 필요가 없다는 걸 알릴수있다.
  // 동일한 함수객체가 메모리의 동일한 위치에 저장되므로 비교를 할 수 있다.
  // useEffect와 동일하게 두번째 인자로 의존성 추가할수있다.

  const allowToggleHandler = () => {
    setAllowToggle(true);
  };
  return (
    <div className="app">
      <h1>Hi there!</h1>
      <Demooutput show={showParagraph} />
      <Button onClick={allowToggleHandler}>Allow Toggling !</Button>
      <Button onClick={toggleParagrahHandler}>Toggle Paragraph!</Button>
    </div>
  );
}
// 위의 showParagraph가 boolean으로 사용되며 DemoOutput 컴포넌트에 전달된다.
// app에 상태가 바뀔때마다 포함된 컴포넌트들 또한 재평가가 되기때문에 하위 노드도 재 호출됨을 인지해야한다.
// 여기서 의문인부분은 과연 관련 함수가 변경되면 하위 트리도 모두 재실행될텐데 성능에 영향을 어느정도 미치는가 이다.
// props가 없다면 상태변화가 없기에 출력결과도 바뀌는것이 없다.
// 있다면 불필요한 함수의 재실행을 막을 필요가있다.
// React.memo가 이에 해당한다.
export default App;
