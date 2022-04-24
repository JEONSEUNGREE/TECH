// ===============================================================
// 1. arrow function
// arrow function의 경우 function을 붙이지않고
// 또한 this가 객체 내를 가르키기에 또 다른 객체와 혼동하는 부분을 막을 수 있다.

// const test = (number) => {
// return number * 2 
// }

// 인자가 하나인 경우 number => number * 2 로 생략가능

// ===============================================================
// 2. Exports & Import (modules)
// defalt는 파일에서 어떤것을 가져오면 항상 defalt export가 내보낸 것 을 기본값으로 가져온다는 의미

// 예)
// const person = {
    // name : "max"
// }
// export default person

// import person from './person'

// 여러 함수를 사용하는 경우
// export const clean = () => {}
// export const baseData = 10;
// import { clean, baseData } from './clean'
// import * as clean from './clean'

// ===============================================================

// 3. 클래스 생성자 
// ES6
// 클래스 내부에 constructor가 필요했고 extends한 경우 부모 생성자도 초기화 해주어야했다.
// ES7
// 생성자 함수 호출없이 바로 필드 레벨에 적용 (생성자 함수가 생략됨)
 
// 4. 스프레드 연산자
// const number = [1,2,3]
// const newNumber = [...number, 4,5];
// newNumber = [1,2,3,4,5]

// const person ] {
    // name : 'max'
// }

// const new Person = {
    // ...person,
    // age: 28
// }
// console.log(newPerson)

// 5. 레스트 연산자
// const filter = (...args) => {
    // return args.filter(el => el === 1);
// }
// console.log(filter(1,2,3)) ==> 1
// 레스트연산자는 배열을 여러개 받아서 하나의 배열로 취급한다.


