/*
[1] : 프로토타입이란 무엇인가?
자바스크립트를 공부하는 과정에서 흔히 들어보게되는 용어중 하나 --> 이 말은 다른 언어 공부할 때는 별로 들어보지 X.
자바 스크립트는 프로토타입 기반의 언어이다. --> 이를 기반으로 확장과 재사용성 증가
사전적의미 Prototype: 원형
쉽게 말해 객체의 프로토타입을 가지고 새로운 객체를 생성해가는 프로그래밍 방식
즉, 생성된 객체는 자기자신의 프로토 타입을 갖는다. -> 자기 자신이 만들어지게 된 원형을 안다. (무슨 뜻인지 애매한데 let으로 선언 싱글턴처럼? new연산자로 매번 다른 메모리 주소에 올리지않고)

[2] : Prototype Vs Class
지금은 자바스크립트도 Class를 문법적으로 지원하기 시작했지만 원래 자바스크립트는 프로토타입 기반의 언어이다.
즉, 기존에는 Class라는게 없었다.
그래서, 객체의 원형인 프로토타입을 이용하여 객체의 확장과 재사용, 상속 등을 구현해나갔다.
Prototype 객체는 new 연산자에 의해서 생성된 객체 --> 공유 프로퍼티, 메서드 등을 제공하기 위해서 사용된다.

*/

// 예
const fruit = { name: 'apple'};
console.log(fruit.name) //apple

// 속성 추가
fruit.expiration = '20220128'
console.log(fruit.expiration); // 20220128

// 속성이 있는지 없는지 체크? --> hasOwnProperty() 메서드 사용.
console.log(fruit.hasOwnProperty('expiration')); // true
console.log(fruit.hasOwnProperty('test')) // false

// hasOwnProperty() 메서드는 fruit 객체가 어떻게 쓰는 건지??

console.log(fruit) // fruit를 개발자옵션으로 보면 __proto__: object로 나오는데 자세히보면 constructor, toString, valueof...등 여러 메서드들이 존재한다.

const aaa = {};

console.log(aaa) // 빈 객체도 기본 속성이 존재함

const fruit2 = {
    name: 'apple',
    expiration: '20241231',
    hasOwnProperty: function() {
        console.log("Hello Method")
    }
}

fruit2.hasOwnProperty(); // 안녕이 출력됨 - 기존 메서드 오버라이딩됨

// [1] : 함수가 만들어지고 수행이 되어지면..
// 내부에서는
//  1. 함수 자신과 예를 들면 "Animal 함수" 자신과 그리고 자신과 같은 이름의 "Animal프로토타입 객체"가 생성.
//  2. Animal 함수 멤버로 --> Prototype 속성이 생성 --> 다른 곳에 생성된 같은 함수 이름의 "Animal 프로토 타입 객체"룰 가르킴 (참조)

// 프로토타입 객체 생성의 장점
// 예를 들어 new 연산자로 매번 객체생성후 메서드를 실행할경우 단 run이라 공통 메서드가 존재하면 매번 공통메서드를 사용하는 낭비가 된다.
// 이를 프록시 객체에 공통 메서드를 넣어 메모리 낭비를 방지함.
// 프로토타입 객체에 메서드를 추가하면 new 생성한 객체들에 자동반영되고 초기 생성시 초기화할 필요가없기때문에 (static하기때문?) 공통 메서드에서는 이점이 있다. 상속개념과 유사한것 같다.

function Animal() {}

// Animal 함수                                          Animal 프로토타입 객체
// + prototype --> Animal 프로토타입 객체(참조)          +constructor --> Animal함수 (참조) 
// + run()
// 프로토타입 객체 (부모격) 
// 정리하면 
// 프로토타입은 new 연산자를 통해서 만들어내는 모든 객체의 원형이 되는 객체 (부모 격)


let tiger = new Animal();
let lione = new Animal();
// 이렇게 new 연산자와 생성자 함수를 이용하여 객체 생성시 각 객체에는 --> __proto__ 속성이 자동으로 생성.
// 이 속성(__proto__)은 뭔가를 가리키는데 --> 이 객체가 만들어질 수 있도록 해준 원형 --> "프로토 타입 객체"를 숨은 링크로 가르킨다(참조).

console.log(tiger); // constructor: 생성자(aninal()) __proto__:object - 최상위 부모 를 가르킴

// Animal 프로토타입 객체 --> tiger, lion과 같은 객체들의 원형이 되는 객체.
// 따라서, tiger, lion과 같은 객체들이 모두 이 "Animal 프로토타입 객체"에 접근이 가능하고, 동시에
// 이 "Animal 프로토타입 객체에"멤버 한개를 추가하면 --> tiger, lion객체들도 동시에 이멤버를 공유해서 모두가 사용이 가능.

// "Animal 프로토타입 객체"에 멤버 한 개를 추가?
Animal.prototype.aniRun = function() {
    return "animal run!";
};

let rabbit = new Animal();

console.log(rabbit.aniRun());
// 만일 Animal 자체에 aniRun()이 존재하면 사용하고 없으면 부모격 proto_type에 찾으러 올라가게된다.

tiger.aniRun = function(){
    return "tiger run!"
}

console.log(tiger.aniRun());


