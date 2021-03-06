import axios from "axios";
import {
    LOGIN_USER,
    REGISTER_USER,
    AUTH_USER
} from '../_reducers/types'

export function loginUser(dataToSubmit) {

    const request = axios.post("/api/user/login", dataToSubmit)
    .then((res) => res.data)
    .catch((err) => {
        console.log(err.data)
    });

    return {
        type: LOGIN_USER,
        payload: request
    }
}

export function registerUser(dataToSubmit) {

    const request = axios.post("/api/user/register", dataToSubmit)
    .then((res) => res.data)
    .catch((err) => {
        console.log(err.data)
    });

    return {
        type: REGISTER_USER,
        payload: request
    }
}

export function auth() {

    const request = axios.get("api/user/auth")
    .then((res) => res.data)
    .catch((err) => {
        console.log(err.data)
    });

    return {
        type: AUTH_USER,
        payload: request
    }
}



