// 호이스팅 (hosting)

// [1] : 함수 레벨 스코프 vs 블록 레벨 스코프

var a = 11;     // 전역 변수
console.log(a); // 111

{
    var a = 333;    // 전역 변수 --> 함수 레벨 스코프, var 키워드는 중복이 허용(선언이 가능), 호이스팅O, 함수가 아닌 변수 선언은 모두 전역.
}

console.log(a);     // 333 --> 11이 출력되게끔 하려면? function으로 설정

// [2] : 호이스팅이란?
// Hoisting 사전적의미 --> 끌어 올리기(scope) --> 국기 계양식
// 자바스립트는 기본적으로 모든 선언문(var, let, const, function, class)을 --> 호이스팅.
// 호이스팅 == 스코프 안의 어디에서든 변수 선언은 최상위에서 선언한 것과 --> 동일
// var 키워드와 let, const 키워드로 선언한 변수가 차이가 있다.

console.log("testA 값은 = " + testA ); //undefined
var testA; //위와 현재 라인의 코드는 에러를 발생하지 않음 

// console.log("testB 값은 = " + testB ); // Err
// let testB; // 엄밀히 말하면 호이스팅을 하지만 var처럼 초기화가 동시에 되지않았기 때문에 문제가 발생
           // 따라서 Cannot access 'testB' before initialization 와 같은 에러가 발생함

// [3] : 클래스 호이스팅
// console.log(Person) //마찬가지로 클래스 호이스팅도 하지만 초기화문제 발생 Cannot access 'Person' before initialization
class Person {}

// var vs let, const == class 호이스팅 에러 비교
console.clear();
var str1 = "hello, World";

const testFun = function() {

    console.log(str1)
    var str1 = "hello, Korea"; 

}

testFun(); // 실행결과 undefined
           // 이유 str1이 호이스팅 되지만 초기화는 실제 코드라인에 와야지 되기때문에 초기화 전에 값은 출력해도 undefined가 된다.

// 같은 맥락에서 클래스 상속 또한 마찬가지다.           
// 클래스 상속
class Parents {}
class Child extends Parents {}

class Child2 extends Parents2 {}
class Parents2 {}


