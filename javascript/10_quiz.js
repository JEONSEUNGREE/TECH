// 생성자 함수내의 속성 값을 외부에서 수정하지 못하도록 변경하시오.
// 방법
// 1. 생성자 함수내에서 const로 name 변수를 만든다.
// 2. 해당 값을 외부에서 불러올 수 있도록 전용 getName() 메서드를 만든다.
// 3. 기존 코드에서 name 불러오는 부분을 모두 getName() 메서드로 바꿔 호출한다.

// [1] : 생성자 함수

const Housing = function( name_, color, rooms) {
    // this.name = name;
    const name = name_;
    this.getName = () => console.log(name);

    this.color = color;
    this.rooms = rooms;
}

// [2] (Housing) 프로토타입 객체
Housing.prototype.toilet = 1;
Housing.prototype.turnon = function() {
    console.log("turn on..")
}

// [3] : 객체 생성 --> new

const apt = new Housing( "apt", "red", 4);
apt.getName();

console.log(apt.color);
console.log(apt.rooms);
apt.turnon()