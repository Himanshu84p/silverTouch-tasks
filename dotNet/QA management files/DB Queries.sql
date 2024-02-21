create database QAManagement;
use QAManagement;

create TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) NOT NULL,
    PasswordHash NVARCHAR(200) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Role NVARCHAR(50) NOT NULL,
    
);


INSERT INTO Users(Username, PasswordHash, Email, Role)
VALUES 
('john_doe', 'hash123', 'john.doe@example.com', 'user'),
('jane_smith', 'hash456', 'jane.smith@example.com', 'user'),
('admin1', 'adminhash1', 'admin1@example.com', 'admin'),
('user2', 'userhash2', 'user2@example.com', 'user'),
('user3', 'userhash3', 'user3@example.com', 'user'),
('user4', 'userhash4', 'user4@example.com', 'user'),
('user5', 'userhash5', 'user5@example.com', 'user'),
('user6', 'userhash6', 'user6@example.com', 'user'),
('user7', 'userhash7', 'user7@example.com', 'user'),
('user8', 'userhash8', 'user8@example.com', 'user');



