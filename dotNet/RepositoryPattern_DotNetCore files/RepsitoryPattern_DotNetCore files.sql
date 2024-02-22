use CoreScaffoldDB;

CREATE TABLE Employee (
    id INT PRIMARY KEY IDENTITY(1,1),
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    jobTitle VARCHAR(100),
    department VARCHAR(100),
);

INSERT INTO Employee (firstName, lastName, jobTitle, department)
VALUES 
('John', 'Doe', 'Software Engineer', 'Engineering'),
('Jane', 'Smith', 'Marketing Manager', 'Marketing'),
('Michael', 'Johnson', 'Sales Representative', 'Sales'),
('Emily', 'Williams', 'HR Specialist', 'Human Resources'),
('Chris', 'Brown', 'Financial Analyst', 'Finance');
