import { DB_NAME } from "../../constant.js";
import mongoose from "mongoose";

//function to connect db
const connectDB = async () => {
  try {
    //connected instance for reffering which host is connected
    const connectionInstance = await mongoose.connect(
      `${process.env.MONGO_URI}/${DB_NAME}`
    );
    console.log(`\n Successfully Connected to DB, HOST : ${connectionInstance.connection.host}`);
  } catch (error) {
    console.log("MonogDB connection FAILED", error);
    process.exit(1);
  }
};

export default connectDB