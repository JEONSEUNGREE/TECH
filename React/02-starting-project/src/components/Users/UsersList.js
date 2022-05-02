import React from "react";

import Card from "../UI/Card";
import styles from "./UsersList.module.css"

const Userslist = (props) => {

  const users = props.users.length !== 0 ? props.users : []

  return (
    <Card className={styles.users}>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            {user.name} ({user.age} years old)
          </li>
        ))}
      </ul>
    </Card>
  );
};

export default Userslist;
