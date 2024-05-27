import React, { useContext } from "react";
import { CartContext } from "../context/CartContext";
import Notification from "../toast/Notification";
import Rating from "./Rating.jsx";

const ProductList = () => {
  //accessing the actions in context using dispatch
  const { dispatch } = useContext(CartContext);

  //initial data
  const products = [
    {
      id: 1,
      name: "Watch",
      price: 10,
      rating: 4.2,
      image:
        "https://images.unsplash.com/photo-1523275335684-37898b6baf30?q=80&w=1999&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 2,
      name: "Headphone",
      price: 20,
      rating: 3.5,
      image:
        "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 3,
      name: "Car",
      price: 300,
      rating: 3,
      image:
        "https://images.unsplash.com/photo-1581235720704-06d3acfcb36f?q=80&w=2080&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 4,
      name: "Bleu de Chanel",
      price: 40,
      rating: 4,
      image:
        "https://images.unsplash.com/photo-1541643600914-78b084683601?q=80&w=2008&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 5,
      name: "Watch Classic",
      price: 25,
      rating: 4.5,
      image:
        "https://images.unsplash.com/photo-1555487505-8603a1a69755?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 6,
      name: "Sunglasses",
      price: 5,
      rating: 5,
      image:
        "https://images.unsplash.com/photo-1511499767150-a48a237f0083?q=80&w=2080&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 7,
      name: "Camera Nikon 750D",
      price: 100,
      rating: 5,
      image:
        "https://images.unsplash.com/photo-1564466809058-bf4114d55352?q=80&w=1972&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 8,
      name: "Alexa",
      price: 10,
      rating: 3.5,
      image:
        "https://images.unsplash.com/photo-1543512214-318c7553f230?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 9,
      name: "Rayban glasses",
      price: 10,
      rating: 3.8,
      image:
        "https://images.unsplash.com/photo-1572635196237-14b3f281503f?q=80&w=2080&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 10,
      name: "Sun Screen",
      price: 5,
      rating: 4.9,
      image:
        "https://plus.unsplash.com/premium_photo-1675431443027-ad1f46c93c8d?q=80&w=2032&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 11,
      name: "Dove Light Moisturiser",
      price: 40,
      rating: 3.8,
      image:
        "https://images.unsplash.com/photo-1625772452859-1c03d5bf1137?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      id: 12,
      name: "Water Bottle",
      price: 15,
      rating: 4,
      image:
        "https://images.unsplash.com/photo-1610824352934-c10d87b700cc?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
  ];

  //add to cart function : product will be added in the cart
  const addToCart = (product) => {
    dispatch({ type: "ADD_TO_CART", payload: { ...product, quantity: 1 } });
    Notification({ message: `${product.name} added to cart` });
  };

  return (
    <div className="container mx-auto">
      <h2 className="text-4xl font-bold mb-4 mt-8">Buy Products</h2>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {products.map((product) => (
          <div key={product.id} className="bg-white p-4 rounded-lg shadow-md">
            <img
              src={product.image}
              alt={product.name}
              className="w-full h-64 object-cover mb-4 rounded-lg"
            />
            <h3 className="text-lg font-semibold text-left">{product.name}</h3>
            <p className="text-gray-600 text-left">${product.price}</p>
            <Rating value={product.rating} />
            <button
              onClick={() => addToCart(product)}
              className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-full mt-4 focus:outline-none active:bg-blue-900"
            >
              Add to Cart
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductList;
