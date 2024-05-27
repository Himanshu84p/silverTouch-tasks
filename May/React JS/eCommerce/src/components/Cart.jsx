// Cart.jsx
import React, { useContext, useMemo } from "react";
import { CartContext } from "../context/CartContext";
import CartProductCard from "../Products/CartProductCard.jsx";

const Cart = () => {
  //accessing the global state using context hook
  const { state } = useContext(CartContext);
  const { items } = state;

  //calculate total bill using useMemo for optimization
  const totalBill = useMemo(() => {
    return items.reduce((total, item) => total + item.price * item.quantity, 0);
  }, [items]);

  return (
    <div className="container mx-auto">
      <h2 className="text-4xl font-bold mb-4 mt-8">Cart</h2>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {items.map((item) => (
          <CartProductCard items={items} item={item} />
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
