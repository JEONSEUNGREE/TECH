// ES6 심볼(Symbol) 타입

// [1] : 심볼(Symbol) 타입이란 무엇인가?
// 타입이란 말에서 알 수 있듯이 ES6에서 새롭게 도입된 원시형 타입 중 하나 --> 객체의 속성으로 사용.
// 심볼의 사전적 의미 --> 상징 --> 심볼형을 사용하면 --> 유일무이한 값, 고유한 값을 가진다. (덮어쓰기 방지)
// 동시에 심볼형 값은 --> 변경 불가능한 불변값.


// [2] : 기존 타입들 --> 원시형과 참조형 --> 이중 원시형에 새롭게 추가된 타입.
//         1. 원시형 - Number, String, Null, Undefined, Boolean, Symbol(ES6)
//         2. 참조형 - Object(object, function, array..)

// [3] : 왜 객체의 속성으로 심볼을 사용하는지? --> 충돌을 피하기위함
// 예를 들어, 배열 객체를 만들어서 "배열명.length"하면 --> 배열의 길이 --> 같은 이름의 length로 덮어쓰면 덮어써지는 문제가 발생.
// 이때, 배열의 길이 값은 그대로 유지하면서 같은 이름의 length 속성을 추가하고자 할 때 --> 심볼을 사용하면 --> 문제 해결.
// 예)
let ar = [1, 2, 3, 4, 5];

console.log(ar.length); // 5 -> 배열의 길이 즉 요소의 수를 알수있는 내장된 속성 (length속성덕분에)
console.log(ar[0]);


ar.length = 50;
console.log(ar.length); // 50 --> 이렇게 덮어써짐
console.log(ar[0]);

// 출력 결과 값
// 5
// 1
// 50
// 1
// 길이가 50으로 덮어써짐 선언된 초기화 배열값을 넘어가는 경우 undefined와 같이 출력됨

console.log("==================================================")
let ar2 = [ 1, 2, 3, 4, 5];
const length = Symbol('length'); // 내장된 속성과 별개로 length라는 심볼을 새김

ar2[length] = 50; //ar2 배열의 length 속성에 50할당.

console.log("ar2.length : " + ar2.length); // 5
console.log("ar2[length] : "+ar2[length]); // 50

// 출력 값
// ar2.length : 5
// ar2[length] : 50


// [4] : 심볼(Symbol) 사용법(생성법)
// 심볼은 new 연산자를 사용하지않는다. Symbol()함수를 사용하여 생성
let symbol = Symbol();

// 2. 괄호안은 비어두고 생성해도 되고, 문자열을 넣어서 생성해도 된다.
// 보통 이때의 문자열은 --> 단순 디버깅 용도이거나 단순 설명일 뿐, 고유한 값을 가지는데 있어서 어떤 영향을 끼치거나 하지 않는다.
// 따라서, description 인자는 심볼의 고유값을 구분하지 못한다. 이유는 --> 심볼은 매번 실볼함수 호출시 새로운 심볼 값을 생성해내기 때문.
console.log("==================================================")
let symbol2 = Symbol("personName");
let symbol3 = Symbol("personName");

console.log(symbol2 === symbol3); // false
console.log(symbol2) // Symbol(personName)

// 심볼형은 for...in 구문으로 반복시 출력되지않음 (은닉성 보유)

// 배열 객체에 속성을 추가하면 --> for ..in 반복문으로 출력시 해당 속성도 같이 출력됨

let ar6 = [ 1, 2, 3, 4, 5,];
ar6.someProperty = 10;

for(let x in ar6) {
    console.log(x); // 0, 1, 2, 3, 4, someProperty
}
// 출력 값
// 1
// 2
// 3
// 4
// someProperty

// 위와같은 상황을 방지하기위한 Symbol
console.log("==================================================")
let ar7 = [ 1, 2, 3, 4, 5,];
someProperty2 = Symbol("SomeProperty");

ar7[someProperty2] = 10;

for(let i in ar7){
    console.log(i);
}
console.log(ar7[someProperty2]) // 10

// 출력 값
// 1
// 2
// 3
// 4

// 결론 속성을 은닉화 하고자한다면 사용한다.