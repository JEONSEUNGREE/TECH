import ProductItem from "./ProductItem";
import classes from "./Products.module.css";

const DUMMY_PRODUCTS = [
  {
    id: "p1",
    price: 6.00,
    title: "My First Book",
    description: "The first book I ever Wrote",
  },
  {
    id: "p2",
    price: 5.00,
    title: "My Second Book",
    description: "The Second book I ever Wrote",
  },
  {
    id: "p3",
    price: 4.00,
    title: "My Third Book",
    description: "The Third book I ever Wrote",
  },
];

const Products = (props) => {
  return (
    <section className={classes.products}>
      <h2>Buy your favorite products</h2>
      <ul>
        {DUMMY_PRODUCTS.map((product) => (
          <ProductItem
            key={product.id}
            id={product.id}
            title={product.title}
            price={product.price}
            description={product.description}
          />
        ))}
      </ul>
    </section>
  );
};

export default Products;
