// 클래스 상속 호이스팅 개념
class Parents {}
class Child extends Parents {}

// class Child2 extends Parents2 {}
// class Parents2 {} 호이스팅 되지만 초기화 오류 발생 

// 상속(Inheritance)
// 부모의 자원 그대로 상속받아 자식 클래스를 생성 및 확장해서 만들 수 있다. - extends

// [1] : 실습
// 부모 (공통)
class Animal {
    constructor( name, age) {
        this.name = name;
        this.age = age;
    }
    eat() {
        console.log( this.name + "먹다")
    }
}

// 자식 클래스
class Tiger extends Animal {
     howling() {
        console.log("호랑이 소리")
     }
     eat() { // 오버 라이딩
        console.log("먹기")
     }
}

let tiger = new Tiger( "호랑이", 30);
console.log( `이름 : ${tiger.name} 나이 : ${tiger.age}`)

tiger.howling()
tiger.eat()







