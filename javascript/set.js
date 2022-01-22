// ES6 set() 자료구조

// [!]
// ES6에서 새롭게 도입한 데이터 자료구조 ---> map, set
// map은 key와 value를 한 쌍으로 묶는다는 점에서 객체(Object)와 비슷하고, set은 중복을 허용하지 않는다는 특징을 빼면 --> 배열과 유사.

// [1] : set
// set --> 집합 --> key, value의 쌍이 있다면 value의 집합 또는 컬렉션.

// [2] : 특징
// 배열(array)은 같은 값을 가질 수 있지만, set(집합)은 같은 값을 중복해서 가질 수 없다.

let ar2 = [ 1, 2, 3, 4, 5, 5]

console.log(ar2)
console.log(ar2[4])
console.log(ar2[5])

// [3] : set 사용법
// 생성 ---> new
// 추가 ---> add
// 삭제 ---> delete

// 생성 
let ar3 = new Set(); // 비어있는 set(집합)을 하나 생성
// console.log(ar3); //object set 객체 반환.
ar3.add("A");
ar3.add("B");
ar3.add("C");
console.log(ar3[0]) //A ??? ---> undefined --> 뭔가 배열과는 틀리다. 라는 것을 알 수 있음

// 사이즈
console.log(ar3.size);



