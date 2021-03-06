import React, { useEffect } from 'react';
import axios from 'axios'

function LadingPage(props) {

    useEffect(() => {
      axios.get("/api/hello")
      .then(res => console.log(res.data))
    }, [])

    const onClickHandler = () => {
        axios.get('/api/users/logout')
        .then(res => {
            console.log(res)
            if(res.data.success) {
                if(res.data.success) {
                    props.history.push('LoginPage')
                } else {
                    alert("로그아웃 실패 했습니다.")
                }
            }
        })
    }
    
    return(
    <div style={{
        display: 'flex', justifyContent: 'center', alignItems: 'center'
        , width: '100%', height: '100vh'
    }}>
        <h2>시작 페이지</h2>

        <button onClick={onClickHandler}>
            로그아웃
        </button>
    </div>
    )
}

export default LadingPage;
