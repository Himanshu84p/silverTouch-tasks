// Header.jsx
import React, { useContext, useMemo } from "react";
import { CartContext } from "../context/CartContext";
import { Link } from "react-router-dom";

const Header = () => {
  const { state } = useContext(CartContext);
  const { items } = state;

  const totalItems = useMemo(() => {
    return items.reduce((total, item) => total + item.quantity, 0);
  }, [items]);

  return (
    <header className="header rounded-md">
      <div className="header-container">
        <Link to="/">
          <h1 className="header-title">Shopping Cart</h1>
        </Link>
        <div className="header-cart">
          <Link to="/cart" className="relative header-cart-link">
            <span className="header-cart-icon text-2xl">🛒</span>
            {totalItems > 0 && (
              <span className="absolute -top-1 right-0 bg-blue-500 text-white px-2 py-1 rounded-full text-xs">
                {totalItems}
              </span>
            )}
          </Link>
        </div>
      </div>
    </header>
  );
};

export default Header;
