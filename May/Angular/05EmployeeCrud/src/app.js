import express from "express";

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: "true" }));
app.use(express.static("public"));

//importing routers
import employeeRouter from "../src/routes/employee.route.js"

app.use("/api/v1/employee", employeeRouter)










export default app

