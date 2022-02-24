import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../../_actions/user_action';


function LoginPage(props) {
  const dispatch = useDispatch();

  const [Email, setEmail] = useState("")
  const [Password, setPassword] = useState("")

  const onEamilHandler = (event) => {
    
    setEmail(event.currentTarget.value)
  }

  const onPasswordHandler = (event) => {

    setPassword(event.currentTarget.value)
  }

  const onSubmitHandler = (event) => {
    // 리프레쉬 방지
    event.preventDefault();

    console.log("Email", Email)
    console.log("Password", Password)

    let body = {
      email: Email,
      password: Password
    }

    dispatch(loginUser(body))
    .then(res => {
      if (res.payload.loginSuccess) {
        props.history.push('/')
      }
    })

    

  }

  return (
    
    <div style={{
      display: 'flex', justifyContent: 'center', alignItems: 'center'
      , width: '100%', height: '100vh'
    }}>
      <form style={{ display: 'flex', flexDirection: 'column'}}
            onSubmit={onSubmitHandler}
      >
        <label>email</label>
          <input type="email" value={Email} onChange={onEamilHandler} />
        <label>Password</label>
        <input type="password" value={Password} onChange={onPasswordHandler} />
        <br />
        <button>
          Login
        </button>
      </form>
    
    </div>
  )
}

export default LoginPage;