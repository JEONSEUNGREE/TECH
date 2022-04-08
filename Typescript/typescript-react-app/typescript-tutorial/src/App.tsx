import React from 'react';
import './App.css';

let name : string = "Piysush";

let age : number = "28";

let isStudent : boolean;
let hobbies : string[];
let role : [string, string];

role = ["college", "student"];

type Person = {
  name : string;
  age?: number;
}

let person : Person = {
  name: "seungree",
}

let person1 : Person = {
  name: "bomi",
  age: 27

}

function App() {
  return (
    <div className="App">
      Hello World {name}

    </div>
  );
}

export default App;
