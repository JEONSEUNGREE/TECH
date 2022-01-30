// 제너레이터 (Generator)

// [1] : 제너레이터란?
// 첫째 : 함수다.
// 둘째 : 보통 함수라하면 한개를 반환하거나 없는데 --> 여러 값을 반환함.
//        그것도 함수 외부에서 함수가 실행되는 중간에 --> 특정 부분에서 멈추고 --> 값을 외부에서 받아 하나씩 반환.  
// 셋째 : 외적인 특징 --> *를 함수에 붙이면 제너레이터 함수.
// 넷째 : 함수의 실행을 --> 특정 키워드에서(yield) 멈추었다가 필요한 시점에서 재개 (yield란 양보의 뜻)

function* testGen() {
    yield 1;
    yield 2;
    yield 3;
    return 4;
}

// 제너레이터 실행시 바로 코드가 실행되지않음 -> 호출시 iterator 객체 반환
// iterator 반복자 객체를 반환 --> 보통 it 또는 iter 라는 이름으로 받음.
// 객체기에 내부적으로 어떤 메서들르 가지게 되는데 -->next() 메서드(제너레이터의 주요 메서드)

const iter = testGen(); // iterator 객체를 반환 --> 이 객체에는 --> next() 메서드가 존재 --> next() 실행될때 마다.
                        // 처음 나오는 yield 부분까지 실행하고 멈춘다. (또는 제어권을 호출자에게 양도한다.)
                        // yield에서 멈췄을때 --> yield 뒤의 "값(value)"을 반환.
                        // 만약, yield 키워드 뒤에 아무런 "값"이 없다면 --> undeifed 반환.
                        // 결론 --> next() 메서드 --> 항상 value, done 2개의 속성을 가진 "객체"를 반환
                        // 어떻게? --> value: 1, done: false;
                        // value는 yield 뒤의 값, done은 함수 코드가 실행이 끝나면 true, 아니면 false반환.

console.log(iter.next()); // {value : 1, done: false}
console.log(iter.next()); // {value : 2, done: false}
console.log(iter.next()); // {value : 3, done: false}
console.log(iter.next()); // {value : 4, done: true} 코드 실행완료 done : true
