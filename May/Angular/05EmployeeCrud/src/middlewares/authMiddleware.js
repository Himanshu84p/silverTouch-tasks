import { Employee } from "../model/employee.model.js";
import { ApiError } from "../utils/ApiError.js";
import { asyncHandler } from "../utils/asyncHandler.js";
import jwt from "jsonwebtoken";

export const verifyJWT = asyncHandler(async (req, res, next) => {
  try {
    const token =
      req.cookies?.accessToken ||
      req.header("Authorization")?.replace("Bearer ", "");

    if (!token) {
      return res.status(401).json(new ApiError(401, "Unauthorized access"));
    }

    const decodeToken = jwt.verify(token, process.env.TOKEN_SECRET);

    const employee = await Employee.findById(decodeToken?._id).select(
      "-password"
    );

    if (!employee) {
      throw new ApiError(404, "Invalid access token");
    }
    req.employee = employee;
    next();
  } catch (error) {
    throw new ApiError(401, error?.message || "Invalid access token");
  }
});
