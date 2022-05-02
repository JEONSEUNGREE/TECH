import React, { useState } from "react";
import Button from "../UI/Button";
import Card from "../UI/Card";

import styles from "./AddUser.module.css";


const AddUser = (props) => {
    const [enteredUsername, setEnteredUsername] = useState('');
    const [enteredUserAge, setEnteredUserAge] = useState('');

  const addUserHandler = (event) => {
    event.preventDefault();


  };

  const usernameChangeHandler = (event) => {
      setEnteredUsername(event.target.value);
  }

  const userAgeChangeHandler = (event) => {
      setEnteredUserAge(event.target.value);
  }

  return (
    <Card className={styles.input}>
      <form onSubmit={addUserHandler}>
        <label htmlFor="username">UserName</label>
        <input id="username" type="text" onChange={usernameChangeHandler}/>
        <label htmlFor="age">Age (Years)</label>
        <input id="age" type="number" onChange={userAgeChangeHandler}/>
        <Button type="submit">Add User</Button>
      </form>
    </Card>
  );
};

export default AddUser;
