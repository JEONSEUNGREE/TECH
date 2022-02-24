// 자바스크립트 클래스 - 체크문제

// [1]

class Animal {
    constructor( name ) {
        this.name = name;
    }

    cry() {
        console.log( this.name + "가 웁니다.")
    }

    move() {
        console.log( this.name + "가 이동합니다.")
    }
}

let tiger = new Animal("호랑이")

tiger.cry()
tiger.move()

// [2] : 클래스 타입은?
console.log(typeof(tiger)) // Object 이며 클래스는 function에 해당됨

// 2. 속성과 메서드 추가
Animal.prototype.age = 4;
Animal.prototype.run = function() {
    console.log(this.name + "가 뛰어 갑니다.")
}

tiger.run()


// [3] : 클래스 이름
console.log(Animal == Animal.prototype.constructor)

// [4] : Animal 클래스 속성을 전부 확인하고자할때 (funciton 포함)

console.log(Object.getOwnPropertyNames(Animal.prototype)) // Animal 클래스
console.log(Object.getOwnPropertyNames(Object.getPrototypeOf(tiger))) // tiger 클래스 즉 인스턴스객체
