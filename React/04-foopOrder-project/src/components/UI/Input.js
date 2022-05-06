import React from 'react';

import classes from './Input.module.css';

const Input = (props) => {
    return (
        <div className={classes.input}>
            <label htmlFor={props.input.id}>{props.label}</label>
            {/* type: text 이런식의 배열형태가 아래와같이 스프레드 형태로 입력시 동작한다. */}
            <input id={props.input.id} {...props.input}/>
        </div>
    );
}

export default Input;
