// 자바스크립트 클래스 - 상속과 super

class Animal {
    constructor( group) {
        this.group = group;
    }
    getGroup() {
        return this.group
    }
    eat() {}
    run() {}
    sleep() {return "자다"}


}

class Mammal extends Animal{
    constructor( name, age ,nail, spd, race) {
        super(race);  // this보다 super가 먼저 호출되어야함. 그렇지않을시 오류
        this.name = name;
        this.age = age;
        this.nail = nail;
        this.spd = spd;
    }
    run() {
        return `${this.name} (${this.group}) 뛴다.`
    }
    move() {}
}

let m = new Mammal("호랑이", 10, 10, 1.5, "mammal");
m.testing = function test () {
    console.log("Add test")
}
console.log(m.name) // 부모클래스의 생성자를 호출해야만 에러가 발생하지않는다.

console.log(m.getGroup()) // super에 넣은 파라미터가 출력

console.log(m.name)
console.log(m.nail)
console.log(m.spd)
console.log(m.run())
console.log(m.sleep())

m.testing()

console.log(Object.getOwnPropertyNames(Object.getPrototypeOf(m)))
let test = Object.getOwnPropertyNames(Object.getPrototypeOf(m))

for (let i of test) {
    console.log("객체가 가진 속성 : " + i)
}