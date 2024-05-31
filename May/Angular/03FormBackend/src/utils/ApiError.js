//api error class for reusability of code
class ApiError extends Error {
    constructor(
      statusCode,
      message = "Api Error occured whith custom class",
      errors = [],
      stack = ""
    ) {
      super(message);
      this.statusCode = statusCode;
      this.errors = errors;
      this.message = message;
      this.success = false;
      this.data = null;
  
      if (stack) {
        this.stack = stack;
      } else {
        Error.captureStackTrace(this, this.constructor);
      }
    }
  }
  
  export { ApiError };