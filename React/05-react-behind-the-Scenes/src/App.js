import React,{ useState, useCallback } from 'react';

import './App.css';

import Button from './components/UI/Button/Button'
import Demooutput from './components/UI/Demo/DemoOutput';

function App() {

  const [showParagraph, setShowParagraph] = useState(false);

  console.log("APP RUNNING")

  const toggleParagrahHandler = useCallback(() => {
    setShowParagraph(prevShowParagraph => !prevShowParagraph);
  }, []);

  // 버튼에 react.memo를 적용햇음에도 console이 찍힘을 확인할수있는데
  // 즉 toggleParagrahHandler가 여전히 일반 함수처럼 실행되는데
  // app 함수 내부에 함수는 매번 다시 새로운 함수로 재실행된다는 의미이다.
  // 새로운 함수 주소값이 전달됨으로 원시값이 아닌 참조값 비교로 이루어진 의미이다.
  // 방법은 useCallback에 있다.
  // useCallBack은 컴포넌트 실행 전반에 걸쳐 함수를 저장할 수 있게 하는 훅으로
  // 리액트에 이 함수를 저장하고 매번 실행할때마다 이 함수를 재생성할 필요가 없다는 걸 알릴수있다.
  // 동일한 함수객체가 메모리의 동일한 위치에 저장되므로 비교를 할 수 있다.
  // useEffect와 동일하게 두번째 인자로 의존성 추가할수있다.
  return (
    <div className="app">
      <h1>Hi there!</h1>
      <Demooutput show={false} />
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
