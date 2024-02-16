create database ServiceData;

use ServiceData;

-- Create Department Table
CREATE TABLE Department (
    DepartmentID INT PRIMARY KEY,
    DepartmentName VARCHAR(100),
    Location VARCHAR(100),
    ManagerID INT,
    Budget DECIMAL(18, 2)
);

-- Insert dummy data into Department table
INSERT INTO Department (DepartmentID, DepartmentName, Location, ManagerID, Budget)
VALUES 
(1, 'IT', 'New York', 101, 100000),
(2, 'Marketing', 'Los Angeles', 102, 80000),
(3, 'Finance', 'Chicago', 103, 120000),
(4, 'HR', 'Houston', 104, 90000),
(5, 'Operations', 'San Francisco', 105, 110000);

-- Create Service Table
CREATE TABLE Service (
    ServiceID INT PRIMARY KEY,
    ServiceName VARCHAR(100),
    DepartmentID INT,
    Description TEXT,
    Price DECIMAL(18, 2),
    FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID)
);

-- Insert dummy data into Service table
INSERT INTO Service (ServiceID, ServiceName, DepartmentID, Description, Price)
VALUES 
(1, 'Web Development', 1, 'Full-stack web development services', 5000),
(2, 'Marketing Campaign', 2, 'Social media and digital marketing campaign', 8000),
(3, 'Financial Consulting', 3, 'Financial planning and analysis services', 10000),
(4, 'Recruitment', 4, 'Human resources recruitment services', 6000),
(5, 'Logistics Management', 5, 'Supply chain and logistics management', 7000);

-- Create Customer Table
CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY,
    CustomerName VARCHAR(100),
    Email VARCHAR(100),
    Phone VARCHAR(20),
	City VARCHAR(50),
);

-- Insert dummy data into Customer table
INSERT INTO Customer (CustomerID, CustomerName, Email, Phone)
VALUES 
(201, 'ABC Corp', 'abc@example.com', '123-456-7890', 'Ahmedabad'),
(202, 'XYZ Ltd', 'xyz@example.com', '987-654-3210','Delhi'),
(203, 'Acme Inc', 'acme@example.com', '555-123-4567','Surat'),
(204, 'GHI Enterprises', 'ghi@example.com', '444-555-6666','Mehsana'),
(205, 'PQR Company', 'pqr@example.com', '777-888-9999','Vadodara');

-- Create Order Table
CREATE TABLE [Orders] (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    ServiceID INT,
    OrderDate DATE,
    TotalAmount DECIMAL(18, 2),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ServiceID) REFERENCES Service(ServiceID)
);


-- Insert dummy data into Order table
INSERT INTO [Orders] (OrderID, CustomerID, ServiceID, OrderDate, TotalAmount)
VALUES 
(301, 201, 1, '2024-01-15', 5000),
(302, 202, 2, '2024-01-20', 8000),
(303, 203, 3, '2024-02-05', 10000),
(304, 204, 4, '2024-02-10', 6000),
(305, 205, 5, '2024-02-15', 7000);


-- Create Invoice Table
CREATE TABLE Invoice (
    InvoiceID INT PRIMARY KEY,
    OrderID INT,
    AmountPaid DECIMAL(18, 2),
    PaymentDate DATE,
    PaymentMethod VARCHAR(20),
    FOREIGN KEY (OrderID) REFERENCES [Order](OrderID)
);


-- Insert dummy data into Invoice table
INSERT INTO Invoice (InvoiceID, OrderID, AmountPaid, PaymentDate, PaymentMethod)
VALUES 
(401, 301, 5000, '2024-01-20', 'Credit Card'),
(402, 302, 8000, '2024-01-25', 'Bank Transfer'),
(403, 303, 10000, '2024-02-10', 'Cheque'),
(404, 304, 6000, '2024-02-15', 'Cash'),
(405, 305, 7000, '2024-02-20', 'Credit Card');