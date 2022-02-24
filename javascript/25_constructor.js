// 자바스크립트 클래스 - 생성자 메서드
// 생성자 메서드 인스턴스를 생성할때 제일 먼저 실행되는 메서드
// 초기화 담당
// 클래스내에 constructor라는 이름을 가진 생성자 메서드는 하나여야한다. (자바와 달리)

class Person {
    constructor( name, age) {
        this.name = name;
        this.age = age;
    }
}

const p1 = new Person("홍길동", 20);
console.log(p1.name)
console.log(p1.age)

// [2] : 생략
// constructor 생략 가능 
// 생성된 객체에는 어떤 속성을 추가하려면 --> 객체를 생성한 이후에 별도로 속성을 추가
// 객체 생성시 초기화해야 한다면 --> constructor를 생략하는 것은 X

class Animal {}

const ani = new Animal()

ani.name = "악어"
ani.age = 4;

console.log(ani.name)
console.log(ani.age)