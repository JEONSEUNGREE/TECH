import { NavLink } from "react-router-dom";

const Product = () => {
  return (
    <section>
      <h1>The Product Page</h1>
      <ul>
        <NavLink to='/product/p1'>
          <li>A Book</li>
        </NavLink>
        <NavLink to='/product/p2'>
          <li>A Carpet</li>
        </NavLink>
        <NavLink to='/product/p3'>
          <li>An Online Course</li>
        </NavLink>
      </ul>
    </section>
  );
};

export default Product;
