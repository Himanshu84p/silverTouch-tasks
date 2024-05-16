// ErrorNotification.jsx
import React from "react";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ErrorNotification = ({ message }) => {
  toast.error(message);
  return null;
};

export default ErrorNotification;
