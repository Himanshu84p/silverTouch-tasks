create database OrderManagementAPI;

use OrderManagementAPI;

-- Create Order table
CREATE TABLE Orders (
    OrderId INT PRIMARY KEY,
    CustomerId INT,
    OrderDate DATETIME,
    TotalAmount DECIMAL(10, 2),
    Status VARCHAR(50),
    IsDeleted BIT DEFAULT 0,
    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId)
);

-- Create OrderItem table
CREATE TABLE OrderItems (
    OrderItemId INT PRIMARY KEY,
    OrderId INT,
    ProductId INT,
    Quantity INT,
    UnitPrice DECIMAL(10, 2),
    IsDeleted BIT DEFAULT 0,
    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId)
);

-- Create Product table
CREATE TABLE Products (
    ProductId INT PRIMARY KEY,
    Name VARCHAR(100),
    Description VARCHAR(255),
    Price DECIMAL(10, 2),
    StockQuantity INT,
    IsDeleted BIT DEFAULT 0
);

-- Create Customer table
CREATE TABLE Customers (
    CustomerId INT PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Address VARCHAR(255),
    IsDeleted BIT DEFAULT 0
);

-- Add Password and Role columns to the Customers table
ALTER TABLE Customers
ADD Password VARCHAR(100),
    Role VARCHAR(50); 


-- Inserting two rows into the Customers table
INSERT INTO Customers (Name, Email, Address, Password, Role)
VALUES
    ('John Doe', 'john@example.com', '123 Main St', 'hashed_password_1', 'Customer'),
    ('Jane Smith', 'jane@example.com', '456 Elm St', 'hashed_password_2', 'Customer');
