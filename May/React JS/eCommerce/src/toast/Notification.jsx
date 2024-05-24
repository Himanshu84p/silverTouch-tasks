import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

//notification will display when message will be set

const Notification = ({ message }) => {
  toast.success(message);
  return null;
};

export default Notification;
