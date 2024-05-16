// Cart.jsx
import React, { useContext, useMemo } from "react";
import { CartContext } from "../context/CartContext";
import ErrorNotification from "../toast/ErrorNotification";

const Cart = () => {
  const { state, dispatch } = useContext(CartContext);
  const { items } = state;
  console.log("items", items);

  const totalBill = useMemo(() => {
    return items.reduce((total, item) => total + item.price * item.quantity, 0);
  }, [items]);

  const removeFromCart = (id) => {
    console.log("remove cart id", id);
    dispatch({ type: "REMOVE_FROM_CART", payload: id });
    const productName = items.map((item) => (item.id === id ? item.name : ""));
    ErrorNotification({ message: `${productName} removed from cart` });
  };

  const incrementQuantity = (id) => {
    dispatch({ type: "INCREMENT_QUANTITY", payload: id });
  };

  const decrementQuantity = (id) => {
    dispatch({ type: "DECREMENT_QUANTITY", payload: id });
  };

  return (
    <div className="container mx-auto">
      <h2 className="text-4xl font-bold mb-4 mt-8">Cart</h2>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {items.map((item) => (
          <div key={item.id} className="bg-white p-4 rounded-lg shadow-md">
            <div>
              <img
                src={item.image}
                alt={item.name}
                className="w-full h-64 object-cover mb-4 rounded-lg"
              />
              <h3 className="text-lg font-semibold text-left">{item.name}</h3>
              <p className="text-gray-600 text-left">${item.price}</p>
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
        ))}
      </div>
      <div className="text-3xl font-semibold mt-4 text-black">
        {totalBill == 0
          ? "Oops no item in the cart add items in the Cart"
          : `Total Bill : ${totalBill}$`}
      </div>
    </div>
  );
};

export default Cart;
