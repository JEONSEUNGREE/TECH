import { useParams } from 'react-router-dom';
// 라우터의 다이나믹 세그먼트 파람 받기
import React from 'react';

const Productdeatil = () => {
    const params = useParams();

    console.log(params) 
    // 맵형태의 값이 존재

    return (
        <div>
            <h1>Product detail</h1>
            {params.productId}
        </div>
    );
}

export default Productdeatil;
