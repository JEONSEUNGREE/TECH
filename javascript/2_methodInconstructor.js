//  객체 생성시 생성자 안에서 메서드를 정의하면 안되는 이유

// [1] : 실습
function Add( a, b) {
    this.a = a;
    this.b = b;
    this.plus = function() {
        return this.a + this.b
    }
}

// 아래는 잘못된 예 
// 계속해서 method가 new 될때마다 생성되기에 메모리 낭비됨
let add1 = new Add( 100, 20);

console.log(add1.plus());
console.log(add1)


// 다음과 같이 prototype객체쪽에 생성되도록 하여 메모리 낭비를 방지한다.
// prototype 객체에 Add 메서드를 선언
function Add2( a, b ) {
    this.a = a;
    this.b = b;
}

Add2.prototype.plus = function(){
    return this.a + this.b;
}

let add2 = new Add2( 100, 200)

let add3 = new Add2( 10, 20)
console.log(add2.plus()); // 300 
console.log(add3.plus()); // 30

// 프로토타입에 메서드를 두어 new 생성시 포함되지않음
