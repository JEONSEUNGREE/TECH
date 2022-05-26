import { useState, useRef } from 'react';

const SimpleInput = (props) => {
  const nameInputRef = useRef();
  const [enteredName, setEnteredName] = useState('');

  const nameInputChangeHandler = event => {
    setEnteredName(event.target.value);
  };

  const formSubmissionHandler = event => {
    event.preventDefault();

    console.log(enteredName);
    const enteredValue = nameInputRef.current.value;
    console.log(enteredValue);

    // useState 와 ref 중에 선택해서 사용한다면
    // 즉각적으로 유효성 검증에 사용하는경우 useState로 읽는것이 좋다 매순간 체크하기때문이기도하고 마지막 제출시 useState를 null로 두어 비우기도 되기때문이다.
    // ref의 경우 한번만 제출하는 양식에 좋다. 매순간 굳이 업데이트가 필요하지않은경우에 사용하면 효율적으로 연산이 한번수행되기때문
    nameInputRef.current.value = '';
    // 직접 바닐라 자바스크립트로 DOM에 접근하는 방법은 지양하도록해야한다.
    setEnteredName('');
  };


  return (
    <form onSubmit={formSubmissionHandler}>
      <div className='form-control'>
        <label htmlFor='name'>Your Name</label>
        <input ref={nameInputRef} type='text' id='name' onChange={nameInputChangeHandler} value={enteredName}/>
      </div>
      <div className="form-actions">
        <button>Submit</button>
      </div>
    </form>
  );
};

export default SimpleInput;
