CREATE TABLE Users
(
    ID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(100),
    Email NVARCHAR(100),
    Age INT,
    Address NVARCHAR(255),
    Phone NVARCHAR(15),
    Gender NVARCHAR(10),
);

INSERT INTO Users (Name, Email, Age, Address, Phone, Gender)
VALUES
    ('John Doe', 'john@example.com', 30, '123 Main St, City, Country', '123-456-7890', 'Male'),
    ('Jane Smith', 'jane@example.com', 25, '456 Elm St, City, Country', '987-654-3210', 'Female'),
    ('Michael Johnson', 'michael@example.com', 35, '789 Oak St, City, Country', '456-789-0123', 'Male'),
    ('Emily Brown', 'emily@example.com', 28, '321 Maple St, City, Country', '789-012-3456', 'Female'),
    ('Christopher Lee', 'chris@example.com', 40, '567 Pine St, City, Country', '012-345-6789', 'Male'),
    ('Amanda Wilson', 'amanda@example.com', 32, '901 Cedar St, City, Country', '345-678-9012', 'Female'),
    ('Matthew Taylor', 'matthew@example.com', 27, '234 Birch St, City, Country', '678-901-2345', 'Male'),
    ('Jessica Martinez', 'jessica@example.com', 29, '890 Spruce St, City, Country', '901-234-5678', 'Female'),
    ('Daniel Garcia', 'daniel@example.com', 33, '678 Walnut St, City, Country', '234-567-8901', 'Male'),
    ('Sarah Thomas', 'sarah@example.com', 31, '345 Ash St, City, Country', '567-890-1234', 'Female');


use CoreScaffoldDB;