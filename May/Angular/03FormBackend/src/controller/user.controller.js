import { asyncHandler } from "../utils/asyncHandler.js";
import { ApiError } from "../utils/ApiError.js";
import { User } from "../model/user.model.js";
import { ApiResponse } from "../utils/ApiResponse.js";

//function to generate access and refresh token
const generateAccessAndRefreshToken = async (userId) => {
  try {
    const user = await User.findById(userId);
    console.log(user);
    const accessToken = user.generateAccessToken();
    const refreshToken = user.generateRefreshToken();
    console.log(accessToken);
    console.log(refreshToken);

    //saving the refreshToken only in db
    user.refreshToken = refreshToken;
    await user.save({ ValidateBeforeSave: false }); //false because validation needs to off while saving otherwise it gives an error

    return { accessToken, refreshToken };
  } catch (error) {
    throw new ApiError(
      500,
      "Internal Server Error while generating refresh token and access token"
    );
  }
};

//function to register user
const registerUser = asyncHandler(async (req, res) => {
  //get user from frontend
  //validation not-empty
  //check if user already exists : email
  //create user object and save to the db
  //remove password and refreshToken from response
  //check for user creation
  //return res

  const {
    firstname: firstName,
    lastname: lastName,
    email,
    phone,
    password,
    gender,
  } = req.body;
  console.log("email: ", req.body);

  if (
    [firstName, lastName, email, password, gender].some(
      (feild) => feild?.trim() === ""
    )
  ) {
    throw new ApiError(
      400,
      "firstName, lastName, email, phone, password and gender is required"
    );
  }

  const existedUser = await User.findOne({
    $or: [{ email }], //add another object if want to check on that also
  });

  if (existedUser) {
    throw new ApiError(409, "User already exists");
  }

  const user = await User.create({
    firstName,
    lastName,
    email,
    phone,
    password,
    gender,
  });

  const createdUser = await User.findById(user._id).select(
    "-password -refreshToken"
  );

  if (!createdUser) {
    throw new ApiError(500, "User creation failed");
  }

  return res
    .status(201)
    .json(new ApiResponse(200, createdUser, "User created successfully"));
});

//function to login user
const loginUser = asyncHandler(async (req, res) => {
  //user login steps
  //req body data
  //email validations
  //find user
  //check for password
  //generate refresh and access token
  //send cookies
  const { email, password } = req.body;

  if (!email) {
    throw new ApiError(400, "Username or email is required");
  }

  const user = await User.findOne({
    $or: [{ email }], //add another object if want to check on that also
  });

  if (!user) {
    throw new ApiError(404, "User not found");
  }

  const isPasswordValid = await user.isPasswordCorrect(password);

  if (!isPasswordValid) {
    throw new ApiError(400, "Password is incorrect");
  }

  const { refreshToken, accessToken } = await generateAccessAndRefreshToken(
    user._id
  );

  const loggedInUser = await User.findById(user._id).select(
    "-password -refreshToken"
  );

  const options = {
    httpOnly: true,
    secure: true,
  };

  return res
    .status(200)
    .cookie("accessToken", accessToken, options)
    .cookie("refreshToken", refreshToken, options)
    .json(
      new ApiResponse(
        200,
        {
          user: loggedInUser,
          accessToken,
          refreshToken,
        },
        "User logged in successfully"
      )
    );
});

//function to logout user
const logoutUser = asyncHandler(async (req, res) => {
  await User.findByIdAndUpdate(
    req.user._id,
    {
      $set: {
        refreshToken: undefined,
      },
    },
    {
      new: true,
    }
  );

  const options = {
    httpOnly: true,
    secure: true,
  };
  return res
    .status(200)
    .clearCookie("accessToken", options)
    .clearCookie("refreshToken", options)
    .json(new ApiResponse(200, {}, "User Loggedout Successfully"));
});

export { registerUser, loginUser, logoutUser };
