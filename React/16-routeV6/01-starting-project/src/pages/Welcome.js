import { Route, Routes, Outlet } from "react-router-dom";

const Welcome = () => {
  return (
    <section>
      <h1>The Welcome Page</h1>
      {/* <Routes> */}
      {/* <Route path="new-user" element={<p>Wleome, New User!</p>} /> */}
      {/* </Routes> */}
      <Outlet />
    </section>
  );
};

export default Welcome;
