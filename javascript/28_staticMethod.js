// 자바스크립트 클래스 - 정적 메서드

// 1. 정적메서드 구현방법
// 2. 정적 메서드가 호출되는 케이스와 되지않는 케이스
// 3. 인스턴스가 정적 메서드를 호출할 수 있는지 없는지

// [1] : 정적 메서드 --> Static(고정된) Method
// 메서드 앞에 static 키워드 붙여주면 --> 따로 인스턴스를 생성하지 않아도 --> 메서드 호출 가능 
// 자바와 유사하다고 볼 수 있겠다.

// 가능 케이스
// 인스턴스 생성없이 사용 가능

// 불가능 케이스
// 반대로 인스턴스로 호출 불가능


class Animal {
    constructor( name) {
        this.name = name
    }
    getName() {
        return this.name
    }
    sleep() {
        console.log("잠자다.")
    }
    static sleep2() {  // 정적메서드는 프로토타입객체에 있지않고 Animal클래스에 있다. 따라서 인스턴스 객체에서 사용하고자한다면 인스턴스.constructor.sleep2()를 사용해야한다.
        console.log("잠자다.")
    }
}

const ani = new Animal("호랑이")

ani.sleep()
console.log(ani.getName());

// ani.sleep2() //Err

Animal.sleep2();

ani.constructor.sleep2() // 사용가능

console.clear()
// [2] : 상속 관계에서의 정적 메서드

class Add {
    static plus(x) {
        x = x || 100; // x가 없으면 default가 100이다.
        return x + 1000;
    }
}

class ChildAdd extends Add {
    plus(x) {
        return super.plus(x) + super.plus(x) + super.plus(x) // 부모 메서드 호출하기
    }
}

// 참고로 자식 클래스의 static 메서드로 부모 static 메서드로도 호출은 가능하지만
// 자식클래스가 메서드가 static이 아니면서 부모를 호출하는건 불가능함


console.log(Add.plus(500)); // 1500
console.log(ChildAdd.plus());

const cadd = new ChildAdd();

console.log(cadd.constructor.plus(40)) // 인스턴스로 static 호출해보기