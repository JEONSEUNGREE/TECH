import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { registerUser } from '../../../_actions/user_action';


function RegisterPage(props) {
  const dispatch = useDispatch();

  const [Email, setEmail] = useState("")
  const [Password, setPassword] = useState("")
  const [Name, setName] = useState("")
  const [Confirm, setConfirm] = useState("")

  const onEamilHandler = (event) => {
    
    setEmail(event.currentTarget.value)
  }

  const onPasswordHandler = (event) => {

    setPassword(event.currentTarget.value)
  }

  const onNameHandler = (event) => {

    setName(event.currentTarget.value)
  }

  const onConfirmPassword = (event) => {

    setConfirm(event.currentTarget.value)
  }

  const onSubmitHandler = (event) => {
    // 리프레쉬 방지
    event.preventDefault();

    if(Password !== Confirm) {
      return alert("비밀번호와 비밀번호 확인은 같아야 합니다.")
    }

    console.log("Email", Email)
    console.log("Password", Password)

    let body = {
      email: Email,
      password: Password,
      name: Name
    }

    dispatch(registerUser(body))
    // .then(res => {
    //   if (res.payload.loginSuccess) {
    //     props.history.push('/')
    //   }
    // })

    

  }

  return (
    
    <div style={{
      display: 'flex', justifyContent: 'center', alignItems: 'center'
      , width: '100%', height: '100vh'
    }}>
      <form style={{ display: 'flex', flexDirection: 'column'}}
            onSubmit={onSubmitHandler}
      >
        <label>Name</label>
        <input type="name" value={Name} onChange={onNameHandler} />

        <label>Email</label>
          <input type="email" value={Email} onChange={onEamilHandler} />

        <label>Password</label>
        <input type="password" value={Password} onChange={onPasswordHandler} />

        <label>Confirm Password</label>
        <input type="password" value={Confirm} onChange={onConfirmPassword} />

        <br />
        <button>
          회원가입
        </button>
      </form>
    
    </div>
  )
}

export default RegisterPage;

