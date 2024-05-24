// ErrorNotification.jsx
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

//notification will display when message will be set
const ErrorNotification = ({ message }) => {
  toast.error(message);
  return null;
};

export default ErrorNotification;
