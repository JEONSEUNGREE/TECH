// Promise와 Generator 함수를 이용한 비동기 처리(1)
// 비동기 처리에는 다양한 방법들이 있다.

//  [1] : 콜백함수
setTimeout(
    (x) => {
        let result = x;
        console.log(result);

        // 2
        setTimeout(
            (x) => {
                result *= x
                console.log(result) // 200
                
                // 3
                setTimeout(
                    (x) => {
                        result *= x;
                        console.log(result)
                    },
                    1000,
                    60
                )
            },
            1000,
            20
        )
    },
    1000,
    10
)