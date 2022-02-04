// [2] : Promise 반환
// 1. new Promise() 호출하면 --> 대기 (pending) 상태
// 2. 이때, 콜백 함수를 선언할 수 있고 --> 인자는 resolve, reject.
// 3. 콜백 함수내에서 처리할거 처리한 후 --> resolve() 메서드 호출 --> 이행(Fullfilled) 상태.
// 4. 즉, 성공이면 --> 리턴 값을 --> then() 이 받아서 계속 처리 수행.
new Promise( (resolve, reject) => {
    // 처리
    setTimeout(
     (x) => {
         let result = x;
         console.log(result);
         resolve(result);
     },
     1000,
     10
    );
})
.then((result) => {
    return new Promise((resolve, reject) => {
        setTimeout(
            (x) => {
                result *= x;
                console.log(result);
                resolve(result);
            },
            1000,
            20
        )
    })
})
.then((result) => {
    return new Promise((resolve, reject) => {
        setTimeout(
            (x) => {
                result *= x;
                console.log(result);
                resolve(result);
            },
            1000,
            30
        )
    })
})
.catch(() => {
    console.log("error")
})
