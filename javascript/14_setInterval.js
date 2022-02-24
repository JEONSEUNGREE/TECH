// setInterval
// setTimeout과의 차이점 --> setTimeout은 함수를 한번만 실행 --> setInterval은 함수를 주기적으로 실행.
// 주기적인 실행을 중단 --> clearInterval 사용..

// 1초 간격으로 문자열 출력
// const tid = setInterval( () => console.log("korea"), 1000);
// console.log(tid);

// 위와 같은 상황에서 주기적인 실행을 중지.
// const myStop = (tid) => {
//     clearInterval(tid);
// }

// setTimeout( myStop, 4000, tid);
// 9초까지만 실행되고 이후부터는 실행멈춤

// 아래의 함수 실행시 반응 없음, 함수를 호출하고 파라미터를 넣는것이 아닌 함수명만 기재
// setTimeout(myStop(tid), 5000)

// setInterval 동작
// setTimeout( () => console.log("aaa"), 3000); // 1번만 출력

// 함수 아닌경우 한번만 실행하고 함수인경우 여러번 실행함.
setInterval( console.log("setInterval"), 6000);