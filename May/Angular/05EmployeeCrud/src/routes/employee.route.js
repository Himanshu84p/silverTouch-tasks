import { Router } from "express";
import {
  deleteEmployee,
  editEmployee,
  getAllEmployee,
  getEmployee,
  loginEmployee,
  logoutEmployee,
  registerEmployee,
} from "../controllers/employee.controller.js";
import { verifyJWT } from "../middlewares/authMiddleware.js";

const router = Router();

//employee routes
router.route("/register").post(registerEmployee);
router.route("/login").post(loginEmployee);
router.route("/logout").post(verifyJWT, logoutEmployee);
router.route("/curr-employee").get(verifyJWT, getEmployee);
router.route("/all").get(verifyJWT, getAllEmployee);
router.route("/update-details").put(verifyJWT, editEmployee);
router.route("/delete-employee").delete(verifyJWT, deleteEmployee);

export default router;
