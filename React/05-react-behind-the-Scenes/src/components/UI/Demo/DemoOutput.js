import React from 'react';

const Demooutput = (props) => {

    console.log("DEMOOUTPUT APP RUNNING")

    return (
        <div>
            <p>{props.show ? 'This is new!' : ''}</p>
        </div>
    );
}

export default React.memo(Demooutput);
// momo인자로 들어간 인자에 props의 신규값을 확인 후 기존 props와 비교후
// 바뀐 경우에만 컴포넌트를 재실행 및 재평가하게된다.
// 그럼 왜 전역설정을 하지않는것인가?
// 최적화에는 비용이 따르기 떄문이다.
// memo 메서드는 변경이 발생할 때마다 해당 컴포넌트로 이동해서 기존 props값과 새로운 값을 비교하기 떄문이다.
// 따라서 각각 컴포넌트 작업에 따라서 효율성이 달라진다고 볼 수있다.
// props의 수 부모,자식관계에서 여러 관계를 고려해야한다.
