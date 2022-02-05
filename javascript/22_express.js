// 자바스크립트 클래스 - 표현식 정의


// [1] : 무명 표현식 (클래스이름 없음)
const Person = class {
    constructor( name, age) {
        this.name = name;
        this.age = age;
    }
}

const p1 = new Person("홍길동", 20);
console.log(p1.name);
console.log(p1.age);
console.log("---------------------------------------------------------------");
console.log(Person.name); //Person --> 클래스명이 없으므로 암묵적으로 변수명이 name 속성의 값이 된다.

// [2] : 유명 표현식
const Persons = class namedPerson {
    constructor( name, age) {
        this.name = name;
        this.age = age;
    }
}

const p2 = new Persons( "이순신", 30);
console.log(p2.name);
console.log(p2.age);
console.log("--------------------------------------------------")
console.log(Person.name);  //namedPerson --> 클래스명이 있으므로 name 속성의 값은 클래스명이 된다.

const p3 = new Persons("강감찬", 40); // OK
const p4 = new namedPerson("강감찬", 40); // Err 

// Err 메시지 
// namedPerson is not defined
