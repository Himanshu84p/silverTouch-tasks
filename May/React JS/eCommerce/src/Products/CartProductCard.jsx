import React, { useContext } from "react";
import Rating from "../components/Rating.jsx";
import { CartContext } from "../context/CartContext.jsx";
import ErrorNotification from "../toast/ErrorNotification.jsx";


function CartProductCard({ items, item }) {
  const { dispatch } = useContext(CartContext);

  //remove cart functionality
  const removeFromCart = (id) => {
    console.log("remove cart id", id);
    dispatch({ type: "REMOVE_FROM_CART", payload: id });
    const productName = items.map((item) => (item.id === id ? item.name : ""));
    ErrorNotification({ message: `${productName} removed from cart` });
  };

  //increament quantity
  const incrementQuantity = (id) => {
    dispatch({ type: "INCREMENT_QUANTITY", payload: id });
  };

  //decrement quantity
  const decrementQuantity = (id) => {
    dispatch({ type: "DECREMENT_QUANTITY", payload: id });
  };
  return (
    <div key={item.id} className="bg-white p-4 rounded-lg shadow-md">
      <div>
        <img
          src={item.image}
          alt={item.name}
          className="w-full h-64 object-cover mb-4 rounded-lg"
        />
        <h3 className="text-lg font-semibold text-left">{item.name}</h3>
        <p className="text-gray-600 text-left">${item.price}</p>
        <Rating value={item.rating} />
      </div>
      <div className="flex items-center justify-between mt-2">
        <div>
          <p className="text-gray-600 my-2 text-left">
            Quantity: {item.quantity}
          </p>
          <p className="text-gray-600 my-2 text-left font-semibold">
            Total: {item.quantity * item.price}$
          </p>
          <div className="flex">
            <button
              onClick={() => decrementQuantity(item.id)}
              className="text-2xl text-black font-semibold px-4 py-2  bg-red-300 rounded-md hover:bg-red-400"
            >
              -
            </button>
            <span className="text-center p-4">{item.quantity}</span>
            <button
              onClick={() => incrementQuantity(item.id)}
              className="text-black font-semibold px-4 py-2  bg-green-300 rounded-md hover:bg-green-400"
            >
              +
            </button>
          </div>
        </div>
        <button
          onClick={() => removeFromCart(item.id)}
          className="text-black-500 bg-red-200 px-3 py-2 font-semibold rounded-lg hover:bg-red-400 "
        >
          Remove
        </button>
      </div>
    </div>
  );
}

export default CartProductCard;
