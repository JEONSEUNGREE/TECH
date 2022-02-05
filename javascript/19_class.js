// 자바스크립트 클래스 (class)

// [1] : 클래스의 지원
// 기존에는 자바스크립트 언어에 클래스 문법이 존재하지않핬고 프로토 타입 체인으로 객체 지향 언어의 상속과 캡슐화(은닉화) 등의 OOP 문법을 구현할 수 있었다.

// [2] : 프로토타입 기반의 OOP문법은 너무 어렵다.

// [3] : ES5, 6 --> 거치면서 --> 클래스 문법을 지원 --> 보다 편리하게 쉽게 OOP 문법을 구현할 수 있도록 지원

// [4] : 클래스 선언과 객체 생성
class Person {

    // 생성자 (Constructor)
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }

    // testHello()
    testHello() {
        console.log(`안녕하세요~ ${this.name}(${this.age}) 회원님~ ^_^`);
    }
    
}

const p1 = new Person("홍길동", 20);

console.log(p1.name)
console.log(p1.age)
p1.testHello();

console.log("================================")

const p2 = new Person("전승리", 28);

console.log(p2.name)
console.log(p2.age)
p2.testHello();

console.log("================================")