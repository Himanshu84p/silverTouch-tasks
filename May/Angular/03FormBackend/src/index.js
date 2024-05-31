import dotenv from "dotenv";
import connectDB from "./config/db/index.js";
import app from "./app.js";

dotenv.config({
  path: "./env",
});

//calling the db function for connection
connectDB()
  .then(() => {
    app.on("error", (err) => {
      console.error("Error Occured while running the server", err);
    });
    app.listen(process.env.PORT, () => {
      console.log(`Server is running on port ${process.env.PORT}`);
    });
  })
  .catch((err) => {
    console.log("Server problem", err);
  });
