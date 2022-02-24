// 자바스크립트 프로토 타입 상속

// [1] : 자바스크립트는 객체지향 언어이다.
// --> 다만 다른 언어와는 달리 프로토타입 상속에 기반을 둔 OOP 언어. --> Class 문법이 지원. --> 다른 언어와 비슷
// 프로토타입 상속을 이해하기 위해서는 --> 앞서 배운 내용들에 선학습이 잘 기억되어 있어야한다.

// [2] : 상속의 필요성
// 가장 큰 목적 --> 재사용 --> 프로토타입 체이닝이 되어있는 경우에 사용가능
// 이미 부모쪽에서 정의된 속성과 메서드를 그대로 물려받아 재사용, 확장 가능


// [3] : 프로토타입 체인
// __proto__ --> 상속을 해준 부모 (원형)를 가리킴(참조).
// 이 말은 자식 객체가 __proto__가 가리키는 부모 객체의 멤버(속성, 메서드)를 사용할 수 있다. --> 즉, 상속 받았다.


let obj1 = {
    name: "홍길동",
    age: 20,
    sayHi: function() { console.log("Hi~ " + this.name);}
};

let obj2 = {
    name: "이순신"
};

// 여기서 문제? --> obj2 객체의 __proto__ 속성이 가리키는 것은?

obj2.__proto__ = obj1;
console.log(obj2); // obj1 (부모)
console.log(obj2.age); // 바로 상위에 없기때문에 부모로 부터 가져온 값

// [4] : 빈 객체 생성 후 --> __proto__ 속성에 원하는 객체(부모가 될 또는 원형이 될)를 할당 --> 상속
console.clear();
let obj3 = {};

// 지금 상태의 obj3 객체의 원형(부모)은? --> Object(최상위, Object.prototype) --> null
console.log(obj3)

// hasOwnProperty 속성존재여부 
console.log(obj3.hasOwnProperty("age"))

obj3.__proto__ = obj2;
console.log(obj3.age) // obj3 -> obj2 -> obj1 순으로 가르킨다.
obj3.sayHi() // obj1의 sayHi 함수내에 this.name은 obj2의 name을 가르켜 Hi~ 이순신으로 출력된다. (Hi~ 홍길동이 아니다.)


