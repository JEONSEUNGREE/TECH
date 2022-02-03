// 계속 증가하는 값을 clearInterval 사용하여 중지

// [1] : 실습
// let cnt = 0;

// let tid = setInterval( () => {
//     console.log(tid)
//     cnt++;
//     if( cnt == 10) clearInterval(tid);
// }, 1000 
// )

// [2] 2초단위로 흘러가는 시계

// 1. 익명함수로 처리
// setInterval(() => {

//     let clock = document.getElementById("clock");
//     let d = new Date();

//     // 월 return 값 --> number, 월, 0~11, 0부터 시작 --> 주의!!
//     clock.innerText = `현재 시각은 ${d.getMonth()+1}월 ${d.getDate()}일 ${d.getHours()}시 ${d.getMinutes()}분 ${d.getSeconds()}초 입니다.`;
//     // alert(d.getTime())
// }
// , 2000)

// 2. 외부 함수로 처리 setInterval
// function myClock() {

//     let clock = document.getElementById("clock");
//     let d = new Date();
    
//     clock.innerText = `현재 시각은 ${d.getMonth()+1}월 ${d.getDate()}일 ${d.getHours()}시 ${d.getMinutes()}분 ${d.getSeconds()}초 입니다.`;
// }

// HTML 로딩 완료시 함수 호출
// window.onload = setInterval(() => myClock(), 2000)

// 3. 재귀 호출로 처리 setTimeout

function myClock() {

    let clock = document.getElementById("clock");
    let d = new Date();
    
    clock.innerText = `현재 시각은 ${d.getMonth()+1}월 ${d.getDate()}일 ${d.getHours()}시 ${d.getMinutes()}분 ${d.getSeconds()}초 입니다.`;

    setTimeout(myClock, 2000);
}

window.onload =() => myClock();

