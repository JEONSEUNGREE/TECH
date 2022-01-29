// 객체의 프로토타입 출력

// [1] : __proto__ Vs Object.getPrototypeOf()
// 둘다 같은 프로토타입출력 가능 __proto__가 지원안되는 브라우저도 있음

function A () {}

let obj = new A();

console.log(obj.__proto__); // constructor A()
console.log(Object.getPrototypeOf(obj));

// [2] : ===
function B() {}
let obj2 = new B();

console.log(obj2.__proto__ === Object.getPrototypeOf(obj2)); //true