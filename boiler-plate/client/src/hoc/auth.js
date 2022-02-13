import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { auth } from '../_actions/user_action';



export default function (SpecificComponent, option, admingRoute = null) {

    // admingRoute = null 파라미터 없다면 기본값이 null이라는 ES6문법
    // option
    // null 아무나 출입가능한 페이지
    // true 로그인한 유저만 출입가능
    // false 로그인한 유저는 출입 불가능

    function AuthenticationCheck(props) {

        const dispatch = useDispatch();

        useEffect(() => {

        dispatch(auth()).
        then((res) => {
            console.log(res)

            if(!res.payload.isAuth) {
                if(option) {
                    props.history.push('/login')
                }
            } else {
                if(admingRoute && !res.payload.isAdmin) {
                    props.history.push('/')
                } else {
                   if(option === false) {
                       props.history.push("/")
                   }

                }
            }
        })

        }, [])

        return (
            <SpecificComponent />
        )

    }

    return AuthenticationCheck
}