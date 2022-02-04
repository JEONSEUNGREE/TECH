// Promise와 Generator 함수를 이용한 비동기 처리 - Generator로 변환


// 실습

function clac ( x, y) {
    setTimeout(
        () => {
            iter.next( x * y);
        },
        1000
    )
};

function* testGen() {

    const a = yield clac( 1, 10);
    console.log(a);

    const b = yield clac( a, 20);
    console.log(b);

    const c = yield clac( b, 30);
    console.log(c);

    const d = yield clac( c, 40);
    console.log(d);

}

const iter = testGen();

iter.next();

// iter.next(); // 값 출력x 첫번째 실행이기에 yield에 걸림
// iter.next(101); // value 속성값이 1이다. done은 false
// iter.next(202); 