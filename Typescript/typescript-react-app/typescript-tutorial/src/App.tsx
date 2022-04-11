import React from 'react';
import './App.css';

let name : string = "Piysush";

// let age : number = "28";

let isStudent : boolean;
let hobbies : string[];
let role : [string, string];

role = ["college", "student"];

let printTestName : (name : string) => never

// type Person = {
//   name : string;
//   age?: number;
// }

// let printName : (name: string) => number;

type X = {
  a: string;
  b: number;
}


interface Person {
  name: string;
  age?: number;
}

interface Guy extends Person, X {
  professoion : string
}


type Y = Person & {
  a: string;
  b: number;
}





// type Y = X & {
//   c: string,
//   d: 42
// }

// let y: Y = {
//   c: "test",
//   b: 28,
//   a: "HiHi",
//   d: 42
// }

// console.log(y)
// function personName(name : string) {
//   console.log(name);
// }

let printName : (name : string) => never;

function App() {
  return (
    <div className="App">
      Hello World {name}

    </div>
  );
}

export default App;
