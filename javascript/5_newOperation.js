// new 연산자의 내부 동작

// [1] : 내부적으로는 빈 객체를 생성한 후에 --> 같은 이름의 "프로토타입 객체"를 새로운 객체의 원형(프로토타입)으로 설정.

function Add( a, b) {
    this.a = a;
    this.b = b;
}

Add.prototype.plus = function() {
    return this.a + this.b;
}

let plusTest = new Add( 1, 2);
console.log(plusTest.plus()); // 출력값 3


// [3] 내부 동작
const newobj = {};

newobj.__proto__ = Add.prototype;

console.log(newobj);

Add.apply( newobj, [111, 222]); // new연산자없이 apply를 통해서 사용가능 --> 단 인자 값들을 하나로 묶어서 적용해줘야 함. [ 1, 2] 이런식으로

console.log(newobj.plus()); // 333