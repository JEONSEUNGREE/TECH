// (문제) 아래의 코드를 "프로토타입 체인"을 이용하여 상속이 구현되도록 바꿔보시오.
// 이때, 상속을 계속해서 여러 단계까지 구현하기

// [1]
// const apt = {
//     color: "red",
//     rooms: 4,
//     toilet: 1,
//     turnon() {
//         console.log("turn on..");
//     }
// }

// const villa = {
//     color: "black",
//     rooms: 3,
//     toilet: 1,
//     turnon() {
//         console.log("turn on..");
//     }
// }

// const oneroom = {
//     color: "blue",
//     rooms: 1,
//     toilet: 1,
//     turnon() {
//         console.log("turn on..");
//     }
// }

// 프로토타입 체인 연습
// oneroom.__proto__ = apt;
// apt.__proto__ = villa;

// console.log(oneroom.__proto__.__proto__.color) //

// oneroom.__proto__.__proto__.color = "white";

// console.log(oneroom.__proto__.__proto__)

// [2]

const inProperty = {
    name: "property",
    turnon() {
                console.log("turn on..");
    }
}

const apt = {
    color: "red",   // __proto__ ==> Object(최상위)
    rooms: 4,       // 불가능
}

const villa = {
    color: "black",
    rooms: 3,
}

const oneroom = {
    color: "blue",
    rooms: 1,
}

apt.__proto__ = inProperty;
villa.__proto__ = inProperty;
oneroom.__proto__ = inProperty;

apt.turnon()
villa.turnon()
oneroom.turnon()

// [3] : 여러 단계까지 상속을 구현
const rainbow = {
    name : "무지개 아파트",
    rooms : 5
}

rainbow.__proto__ = apt;

rainbow.turnon()

console.log(rainbow.name)
console.log(rainbow.__proto__.name)


// for in 반복문으로 출력하게 된다면??
// 위코드에서 마지막에 생성한 객체 --> rainbow 객체를 --> for ..in 반복문으로 순회 --> 결과
console.clear();

for(let i in rainbow) {
    console.log("info : " + i);
}

// 위와 같이 프로토타입 체인이 연결되어 있는 원형(부모)의 멤버(속성, 메서드)들이 다나온다.

// Object.keys() vs Object.values()
console.log("==================================")
console.log( Object.keys(rainbow)); // name, rooms
console.log( Object.values(rainbow)) // 무지개아파트, 5

// 해당 rainbow 객체에서 만들어진 속성들의 키와 값이 출력.

// Object.entries() --> 배열로 리턴.
console.log( Object.entries(rainbow))

// [5] : if 조건문과 hasOwnProperty() 메서드를 같이 사용.
console.clear();

for(let i in rainbow) {
    // console.log(i)
    if(rainbow.hasOwnProperty(i)){
        console.log(i, " -->", rainbow[i])
    }else{
        console.log(i, "-->", "");
    }
}

// 이를 통해서 알 수 있듯이 --> hasOwnProperty() 메서드는 해당 객체가 가지고 있는 속성에 대해서만 true를 반환.