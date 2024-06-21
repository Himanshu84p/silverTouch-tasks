import { asyncHandler } from "../utils/asyncHandler.js";
import { ApiError } from "../utils/ApiError.js";
import { ApiResponse } from "../utils/ApiResponse.js";
import { Employee } from "../model/employee.model.js";

//function to return an api error which take three para
const returnApiError = (res, statusCode, message) => {
  return res.status(statusCode).json(new ApiError(statusCode, message));
};

//function to return api response which take four para
const returnApiResponse = (res, statusCode, data, message) => {
  return res
    .status(statusCode)
    .json(new ApiResponse(statusCode, message, data));
};

//function to generate the token
const generateToken = async (employeeId) => {
  try {
    const employee = await Employee.findById(employeeId);

    const token = employee.generateToken();
    employee.token = token;
    await employee.save({ validateBeforeSave: false }); //false because validation needs to off while saving otherwise it gives an error

    return { token };
  } catch (error) {
    console.log("Error occured in generating token", error);
  }
};
//********************************************************************************api functions here

//register employee
const registerEmployee = asyncHandler(async (req, res) => {
  //take user from frontend
  //check for empty value
  //check user already exist or not
  //create user object and save in db
  //remove password and token from response
  //check for user creation
  //return response

  const { name, username, email, phoneNumber, password, gender, profile } =
    req.body;
  console.log("data", req.body);
  if (
    [name, username, email, password, gender].some(
      (feild) => feild?.trim() === ""
    ) ||
    !phoneNumber
  ) {
    return returnApiError(res, 400, "All the fields are required");
  }

  //checking if user existed or not
  const existedUser = await Employee.findOne({ $or: [{ email, username }] });

  if (existedUser) {
    return returnApiError(res, 409, "User already exist");
  }

  const employee = await Employee.create({
    name,
    username,
    email,
    phoneNumber,
    password,
    gender,
    profile,
  });

  const createdEmployee = await Employee.findById(employee._id).select(
    "-password"
  );

  if (!createdEmployee) {
    return returnApiError(res, 500, "User creation failed");
  }

  return returnApiResponse(
    res,
    201,
    createdEmployee,
    "User created successfully"
  );
});

// login employee
const loginEmployee = asyncHandler(async (req, res) => {
  const { username, email, password } = req.body;

  if (!username || (!email && !password)) {
    return returnApiError(
      res,
      400,
      "email / username and password is required"
    );
  }

  console.log(username);
  const employee = await Employee.findOne({
    $or: [{ username }, { email }],
  });

  if (!employee) {
    return returnApiError(res, 404, "employee not found");
  }

  const isPasswordCorrect = await employee.isPasswordCorrect(password);

  if (!isPasswordCorrect) {
    return returnApiError(res, 400, "Password is incorrect");
  }

  const { token } = await generateToken(employee._id);

  const loggedInEmployee = await Employee.findById(employee._id).select(
    "-password -token"
  );

  return returnApiResponse(
    res,
    200,
    { loggedInEmployee, token },
    "User Logged in successfully"
  );
});

//logout employee
const logoutEmployee = asyncHandler(async (req, res) => {
  console.log(req.employee._id);
  await Employee.findOneAndUpdate(
    req.employee._id,
    {
      $unset: {
        token: "",
      },
    },
    {
      new: true,
    }
  );

  return returnApiResponse(res, 200, {}, "Logout Successfully");
});

//getting user
const getEmployee = asyncHandler(async (req, res) => {
  const { id } = req.employee;

  const employee = await Employee.findById(id).select("-password -token");
  return returnApiResponse(res, 200, employee, "Employee fetched successfully");
});

//getting user
const getAllEmployee = asyncHandler(async (req, res) => {
  const allEmployees = await Employee.find({}).select("-password");
  return returnApiResponse(
    res,
    200,
    allEmployees,
    "All Employees fetched successfully"
  );
});

//edit employee details
const editEmployee = asyncHandler(async (req, res) => {
  //take inputs from frontend
  //check for empty values
  //check already exist or not
  //update user and save in db
  //send user updated in res
  console.log("user is ", req.user);
  const { name, username, email, phoneNumber, gender } = req.body;

  if (
    [name, username, email, gender].some((field) => field?.trim() === "") ||
    !phoneNumber
  ) {
    return returnApiError(res, 400, "All Fields are required");
  }

  if (phoneNumber.toString().length != 10) {
    return returnApiError(res, 400, "Enter Valid Mobile Number");
  }

  const existedEmployee = await Employee.findOne({
    $or: [{ email }, { username }],
    _id: { $ne: req.user?._id },
  });
  if (existedEmployee) {
    return returnApiError(res, 401, "User alredy exist with username or email");
  }

  const updatedEmployee = await Employee.findOneAndUpdate(
    //! You should set the new option to true to return the document after update was applied.
    req.user?._id,
    {
      $set: {
        name,
        username,
        email,
        phoneNumber,
        gender,
      },
    },
    {
      new: true,
    }
  ).select("-password");

  return returnApiResponse(
    res,
    200,
    updatedEmployee,
    "User Details updated successfully"
  );
});

//delete employee
const deleteEmployee = asyncHandler(async (req, res) => {
  const employee = await Employee.deleteOne(req.employee._id).select("-password -token");
  return returnApiResponse(res, 200, employee, "Employee deleted successfully");
});

export {
  registerEmployee,
  getEmployee,
  deleteEmployee,
  editEmployee,
  loginEmployee,
  logoutEmployee,
  getAllEmployee,
};
