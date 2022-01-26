//  자바의 set 처럼 key value 형태아니고 그냥 리스트의 일종
let ar = [ "a", "b", "c", "d", "e"]

for(let i = 0; i < ar.length; i++) {
    console.log(ar[i]);
}

console.log("---------------------------");

ar.forEach(function(x){
    console.log(x)
})

console.log("---------------------------");

ar.forEach(val => console.log(val))

// [3] : Set 자료구조의 다양한 메서드 --> Keys(), values()
let testSet3 = new Set(["tiger", "lion", "dog", "cat"]);
testSet3.add("hippo");

console.log(testSet3);

console.log(testSet3.entries())

// [Set Entries] {
//     [ 'tiger', 'tiger' ],
//     [ 'lion', 'lion' ],
//     [ 'dog', 'dog' ],
//     [ 'cat', 'cat' ],
//     [ 'hippo', 'hippo' ]
//   }

console.log(testSet3[0]); // tiger --> undifined

for (let i of testSet3) {
    console.log(i)
}

// for 결과 in은 undifiend
// tiger
// lion
// dog
// cat
// hippo

// 배열로 초기화후 console.log 가능
let arr = [...testSet3];

console.log(arr[0]);

console.log(arr[0]); // tiger
console.log(arr[4]); // hippo

// Keys() 메서드 --> Iterator(반복자) 객체를 반환 --> next() 메서드
const key_itr = testSet3.keys();

console.log("---------------------------");

console.log(key_itr.next().value);
console.log(key_itr.next().value);
console.log(key_itr.next().value);
console.log(key_itr.next().value);
console.log(key_itr.next().value);

console.log("---------------------------");

// values() 메서드 --> Iterator(반복자) 객체를 반환 --> next() 메서드
const val_itr = testSet3.values();

console.log(val_itr.next().value);
console.log(val_itr.next().value);
console.log(val_itr.next().value);
console.log(val_itr.next().value);
console.log(val_itr.next().value);

console.log("---------------------------");

for (let i of testSet3) {
    console.log(i)
}

// [5] : entries() 메서드

let testSet5 = new Set()

testSet5.add("홍길동")
testSet5.add("이순신")
testSet5.add("강감찬")

const entries = testSet5.entries();
// 밸류 밸류 형태의 배열로 반환해줌
for (let i of entries) {
    console.log(i)
}

// 결과 값
// [ '홍길동', '홍길동' ]
// [ '이순신', '이순신' ]
// [ '강감찬', '강감찬' ]

// 삽입 순으로 Set 요소 각각에 대해서 [value, value]
