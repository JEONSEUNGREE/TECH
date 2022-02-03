// setTimeout Vs setInterval - 두번째 이야기
// 비동기 처리에는 다양한 방법들이 존재 --> Promise와 Generator 함수를 이용한 비동기 처리

// [-] : 호출 스케쥴링 함수 --> setTimeout(), setInterval()
// setTimeout                                                    setInterval
// 일정 시간 "후에" 함수를 한 번만 실행.                            일정 시간 "마다" 함수를 한 번만 실행.


// setTimeout 예
function testHello() {
    console.log("Hello~~");
}

testHello(); // 그냥 호출
setTimeout(testHello, 2000); // 지연 호출 --> 시간단위는 밀리세컨드(1000 = 1초)

// setTimeout 예2
function testHello2(t) {
    console.log(t);
}

testHello2("Hello, World~"); // 그냥 호출
setTimeout(testHello2, 3000, "Hello, World~"); // 주의! --> 함수를 호출하지 말고 함수명만 기재.

// setTimeout 예3
setTimeout((a) => console.log(a), 4000, "안녕하세요~")

// setTimeout 예4
setTimeout(() => console.log("aabbccdd"), 4000)

// 호출 스케쥴링 취소 --> clearTimeout
// setTimeout 호출 --> 타이머 식별자 반환. --> 숫자(number)
const tid = setTimeout((a) => console.log(a), 4000, "취소여부~")
console.log("setTimeout 예3 호출 = " + tid)

// 취소 반환되는 식별자로 취소할수있음
clearTimeout(tid)