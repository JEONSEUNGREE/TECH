import './App.css';


function App() {

  const onSubmit = () => {
    alert("submitted");
  }

  const onKeyUp = (event) => {
    // 기본적으로 event인자를 지원 13이 엔터의 키코드
    if (event.keyCode === 13) {
      onSubmit();
    }
    console.log();
  }

  return (
    <div className="App">
      <input onKeyUp={onKeyUp} />
      <button onClick={onSubmit}>
        Submit</button>
    </div>
  );
}

export default App;
