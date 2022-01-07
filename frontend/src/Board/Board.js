import React, { useState, useEffect } from "react";
import axios from "axios";

import "./Board.css";
import Write from "./Write";

function Board() {
  const [products, setProducts] = useState([]);

  const fetchProducts = async () => {
    const { data } = await axios.get(
      "/board/list"
    );
    const products = data;
    setProducts(products);
    console.log(products);
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  return (
    <div className="Board">
      <h1>Simple Board</h1>
      <div className="movie-container">
        <h2>제목</h2>
        {products.map((product) => (
          <p key={Math.random() * 10000}>
            {product.title}
          </p>
        ))}
      </div>
      <Write />
    </div>
  );
}

export default Board;
