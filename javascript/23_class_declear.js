// 자바스크립트 클래스 - 2가지 방식

// [1] : 생성자 함수

function Person ( name, age) {
    this.name = name; 
    this.age = age;
}

Person.prototype.say = function() {console.log("안녕하세요")};

const p1 = new Person("홍길동", 20);
console.log(p1.name)
p1.say()

// [2] : class

class Person2 {
    constructor( name, age) {
        this.name = name;
        this.age = age;
    }
    say() {
        console.log("안녕")
    }
}

const p2 = new Person2("전승리", 20);

console.clear()
console.log(p2.name)
console.log(p2.age)
p2.say()


// 클래스는 기본적으로 엄격모드 (use strict) --> 자동 적용
// 클래스 메서드는 열거 대상이 X. --> 클래스와 같은 이름의 "프로토 타입 객체"의 속성에 추가된 메서드를 열거 X.

for (let i in p2) {
    console.log("P1의 함수들 : " + i)
}

// 자바스크립트 클래스 - getOwnPropertyNames()
// 어떤 객체를 다룰시 그 객체의 모든 특성을 보고자하는 경우 사용
// 정적 메서드 --> Object.getOwnPropertyNames(객체명)
//            --> Object.getPrototypeOf() 지정된 객체의 내부 Prototype 속성  

console.clear()

const p4 = {
    eat() {},
    run() {},
    rest(){},
};

class Person3 {
    constructor( name, age) {
        this.name = name;
        this.age = age;
    }
    eat() {}
    run() {}
    rest() {}
}

const p5 = new Person3("홍길동", 20);

// [2]
// 열거 대상 X --> 메서드가 보이지 X
for(let i in p2) {
    console.log("p2의 멤버들 = " + i); // name, age (eat, run , rest)
}

console.clear();

// [3]
console.log("-------------------------------------------------")
console.log(p4)
console.log(p5) // Person3 {} ==> 메서드는 보이지않음

console.log(Object.keys(p4))
console.log(Object.getPrototypeOf(p5))  // 프로토타입으로 클래스가 가진 객체를 속성들을 확인할수있다.
console.log(Object.getOwnPropertyNames(Object.getPrototypeOf(p5))) // key로 찍으면 보이지않음 하지만 prototypeOf로 출력가능


