import React, { useEffect } from 'react';
import axios from 'axios';

const Users = () => {

    useEffect(() => {
        axios.get('https://jsonplaceholder.typicode.com/users')
        .then(res => {
            console.log(res)
        })
    }, []);

    return (
        <div>
            <h1>Users</h1>
        </div>
    );
}

export default Users;
