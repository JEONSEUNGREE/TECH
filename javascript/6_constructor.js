// 생성자 함수와 내부에서 처리되는 동작들

// [1] : 객체 리터럴
const ainmal = {
    name : 'tiger',
    age : 20
}

console.log(ainmal.name, ainmal.age);

// 위와 비슷한 객체의 동물을 수십, 수백개 만들어야 하는 상황이라면?
// 이런 상황을 대비하여 --> 이와 같은 비슷한 객체를 생성해주는 생성자 함수를 만들어놓고 사용.
// new 연산자를 사용해서 새로운 객체 --> 생성 --> 만약 new를 안붙이면 undefined 처리 --> 객체 생성시에는 new 연산자를 꼭 사용!
function Person(name, age) {

    // this = {}; // 실제 생성자 함수가 실행될때 ---> this라는 빈 객체를 하나 생성한 후에 속성을 추가.
    this.name = name;
    this.age = age;

}

let p1 = new Person("홍길동", 20);
let p2 = new Person("이순신", 20);
let p3 = new Person("강감찬", 20);

console.log(p1);
console.log(p2);
console.log(p3);