// 8번 큊를 new 연산자를 사용하는 예제로 바꾸기

// [1] : 생성자 함수
const Housing = function( name, color, rooms){
    this.name = name;
    this.color = color;
    this.rooms = rooms;
}

// [2] : (Housing) 프로토타입 객체
Housing.prototype.toilet = 1
Housing.prototype.turnon = function() {
    console.log("turn on..");
}

// [3] : 객체 생성 --> new
const apt = new Housing( "apt", "red", 4);

console.log(apt.name);
console.log(apt.color);
console.log(apt.rooms);
apt.turnon()

// 수정이 안되게끔 하는방법