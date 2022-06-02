import { Route } from "react-router-dom";

const Welcome = () => {
  return (
    <section>
      <h1>The Welocom Page</h1>
      <Route path='/welcome/new-users'>
        <p>Wleome, New User!</p>
      </Route>
    </section>
  );
};

export default Welcome;
