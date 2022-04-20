import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Userlist from '../components/UserList';
import Spinner from '../components/Spinner';

const Users = () => {

    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios.get('https://jsonplaceholder.typicode.com/users')
            .then(res => {
                console.log(res)
                setUsers(res.data)
                setLoading(false);
            })
    }, []);

    return (
        <div>
            <h1>Users</h1>
            {loading ? <Spinner /> :
                <Userlist users={users} />}
        </div>
    );
}

export default Users;
