import React, { useState, useRef } from "react";
import Button from "../UI/Button";
import Card from "../UI/Card";
import ErrorModal from "../UI/ErrorModal";
import Wrapper from "../Helpers/Wrapper";

import styles from "./AddUser.module.css";

const AddUser = (props) => {
  const nameInputRef = useRef();
  const ageInputRef = useRef();

  const [enteredUsername, setEnteredUsername] = useState("");
  const [enteredUserAge, setEnteredUserAge] = useState("");
  const [error, setError] = useState();

  const addUserHandler = (event) => {
    event.preventDefault();
    console.log(nameInputRef.current.value)
    if (
      enteredUsername.trim().length === 0 ||
      enteredUserAge.trim().length === 0
    ) {
      setError({
        title: "Invalid Input",
        message: "Please enter a valid name and age (non-empty values)",
      });
      return;
    }
    if (+enteredUserAge < 1) {
      setError({
        title: "Invalid Input",
        message: "Please enter a valid age ( > 0 ).",
      });
      return;
    }
    props.onAddUser(enteredUsername, enteredUserAge);
    setEnteredUsername("");
    setEnteredUserAge("");
  };

  const usernameChangeHandler = (event) => {
    setEnteredUsername(event.target.value);
  };

  const userAgeChangeHandler = (event) => {
    setEnteredUserAge(event.target.value);
  };

  const errorHandler = () => {
    setError(null);
  };

  return (
    <React.Fragment>
      <Wrapper>
        {error && (
          <ErrorModal
            onConfirm={errorHandler}
            title={error.title}
            message={error.message}
          />
        )}
        <Card className={styles.input}>
          <form onSubmit={addUserHandler}>
            <label htmlFor="username">UserName</label>
            <input
              id="username"
              value={enteredUsername}
              type="text"
              onChange={usernameChangeHandler}
              ref={nameInputRef}
            />
            <label htmlFor="age">Age (Years)</label>
            <input
              id="age"
              value={enteredUserAge}
              type="number"
              onChange={userAgeChangeHandler}
              ref={ageInputRef}
            />
            <Button type="submit">Add User</Button>
          </form>
        </Card>
      </Wrapper>
    </React.Fragment>
  );
};

export default AddUser;
