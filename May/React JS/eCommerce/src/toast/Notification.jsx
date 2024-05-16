import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Notification = ({ message }) => {
  toast.success(message);
  return null;
};

export default Notification;
