import React, { useState } from 'react';

import AddUser from './components/Users/AddUser';
import Userslist from './components/Users/UsersList';

function App() {
  const [usersList, setUsersList] = useState([]);

  const addUserHandler = (uName, uAge) => {
    setUsersList((prevUsersList) => {
      return [...prevUsersList, 
        {name: uName, age: uAge, id: Math.random().toString() }];
    });
  };

  return (
    <div>
      <AddUser onAddUser={addUserHandler}/>
      <Userslist users={usersList}/>
    </div>
  );
}

export default App;
