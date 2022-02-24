// 비동기처리에 이용할수 있음.

// [!] : 이게 가능한 이유?
// next() 메서드와 yield가 중간에 번갈아가면서 데이터를 주고 받기 떄문 
// next() 메서드 호출 --> yield 키워드까지 실행 (stop) --> 제어권을 함수 외부로 양도(yield) --> 이때 yield 뒤의 값이 반환.

console.clear();
console.log("------------------------------------")

function* testGen() {
    const a = yield 1;
    const b = yield (a * 1);
    const c = yield (b + 2);
    return a * b * c;
}

const iter2 = testGen();
console.log(iter2.next())
console.log(iter2.next(100))       //a = 100   --> value : 100, done : false
console.log(iter2.next(48))        //b = 48    -->  value : 50, done : false
console.log(iter2.next(2))         //c = 2    --> value : 2, done : true

// 정리
// 위 코드의 값을 바꿔가면서 next() 메서드 실행시 어떤 값이 반환될지를 연습.
// 위 실습을 통해서 알 수 있듯이, 제너레이터 함수와 이터레이터 객체의 next() 메서드는 서로 데이터를 주고 받을 수 있다라는 점.