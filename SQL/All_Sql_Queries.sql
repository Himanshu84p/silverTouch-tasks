use InventoryManagement

ALTER TABLE customer ALTER COLUMN dob date

INSERT INTO category (name, description, created, modified)
VALUES 
  ('Electronics', 'Electronic gadgets and devices', '2024-01-21 09:00:00', '2024-01-21 09:00:00'),
  ('Clothing', 'Various clothing items', '2024-01-21 09:15:00', '2024-01-21 09:15:00'),
  ('Books', 'Books of various genres', '2024-01-21 09:30:00', '2024-01-21 09:30:00'),
  ('Home Appliances', 'Appliances for home use', '2024-01-21 10:00:00', '2024-01-21 10:00:00'),
  ('Toys', 'Children toys and games', '2024-01-21 10:30:00', '2024-01-21 10:30:00');


INSERT INTO customer (name, email, mobile, address, dob, created, modified)
VALUES 
  ('John Doe', 'john@example.com', 1234567890, '123 Main St, City', '1990-05-15', '2024-01-21 11:00:00', '2024-01-21 11:00:00'),
  ('Alice Smith', 'alice@example.com', 9876543210, '456 Oak St, Town', '1985-08-22', '2024-01-21 11:30:00', '2024-01-21 11:30:00'),
  ('Bob Johnson', 'bob@example.com', 5551112233, '789 Maple St, Village', '1995-03-10', '2024-01-21 12:00:00', '2024-01-21 12:00:00'),
  ('Emma White', 'emma@example.com', 4442221111, '101 Pine St, Hamlet', '1988-12-05', '2024-01-21 12:30:00', '2024-01-21 12:30:00'),
  ('David Brown', 'david@example.com', 9998887777, '202 Cedar St, Suburb', '1992-07-18', '2024-01-21 13:00:00', '2024-01-21 13:00:00');


INSERT INTO orders (customer_id, supplier_id, date, status, quentity, total_amount, unit_amount, payment_type, payment_date, created, modified)
VALUES 
  (1, 1, '2024-01-21 14:00:00', 'Processing', 3, 150.00, 50.00, 'Credit Card', '2024-01-21 14:30:00', '2024-01-21 14:00:00', '2024-01-21 14:00:00'),
  (2, 2, '2024-01-21 14:15:00', 'Shipped', 2, 120.00, 60.00, 'Cash', '2024-01-21 14:45:00', '2024-01-21 14:15:00','2024-01-21 14:15:00'),
  (3, 3, '2024-01-21 14:30:00', 'Delivered', 1, 30.00, 30.00, 'Credit Card', '2024-01-21 15:00:00', '2024-01-21 14:30:00','2024-01-21 14:30:00'),
  (4, 1, '2024-01-21 14:45:00', 'Processing', 4, 200.00, 50.00, 'Cash', '2024-01-21 15:15:00', '2024-01-21 14:45:00', '2024-01-21 14:45:00'),
  (5, 2, '2024-01-21 15:00:00', 'Shipped', 2, 100.00, 50.00, 'Credit Card', '2024-01-21 15:30:00', '2024-01-21 15:00:00', '2024-01-21 15:00:00');


INSERT INTO product (name, categories_id, buying_price, selling_price, stock, description, created, modified)
VALUES 
  ('Laptop', 1, 800.00, 1200.00, 20, 'High-performance laptop', '2024-01-21 16:00:00', '2024-01-21 16:00:00'),
  ('T-shirt', 2, 10.00, 20.00, 100, 'Cotton t-shirt', '2024-01-21 16:30:00', '2024-01-21 16:30:00'),
  ('Sci-Fi Book', 3, 15.00, 25.00, 50, 'Best-selling science fiction book', '2024-01-21 17:00:00', '2024-01-21 17:00:00'),
  ('Refrigerator', 4, 500.00, 800.00, 10, 'Large capacity refrigerator', '2024-01-21 17:30:00', '2024-01-21 17:30:00'),
  ('Action Figure', 5, 5.00, 10.00, 200, 'Collectible action figure', '2024-01-21 18:00:00', '2024-01-21 18:00:00');


INSERT INTO supplier (name, email, mobile, address, shop_name, dob, created, modified)
VALUES 
  ('Home Appliance Supplier', 'homeappliance@example.com', 3334445555, '202 Home Appliance St, Appliance City', 'Appliance Haven', '1982-02-02', '2024-01-21 20:30:00', '2024-01-21 20:30:00'),
  ('Toy Supplier', 'toys@example.com', 6667778888, '303 Toy St, Play Town', 'Toy Paradise', '1998-08-08', '2024-01-21 21:00:00', '2024-01-21 21:00:00'),
  ('Tech Gadgets Supplier', 'techgadgets@example.com', 9990001111, '404 Tech Gadgets St, Gadget Village', 'Gadget Haven', '1970-12-12', '2024-01-21 21:30:00', '2024-01-21 21:30:00'),
  ('Furniture Supplier', 'furniture@example.com', 1112233444, '505 Furniture St, Comfort City', 'Furniture Paradise', '1985-05-25', '2024-01-21 22:00:00', '2024-01-21 22:00:00'),
  ('Jewelry Supplier', 'jewelry@example.com', 1234567890, '606 Jewelry St, Sparkle Town', 'Jewelry Haven', '1995-10-15', '2024-01-21 22:30:00', '2024-01-21 22:30:00');


-- Inline queries for inventorymanagement

SELECT name, buying_price, selling_price
FROM product;

SELECT SUM(quantity) AS TotalQuantity
FROM orders;

SELECT name AS ProductName, selling_price
FROM product
WHERE stock < 50;

-- sub queries for LibraryManagement


SELECT name AS CustomerName, email
FROM customer
WHERE id IN (SELECT DISTINCT customer_id FROM orders);

SELECT name AS ProductName
FROM product
WHERE categories_id = (SELECT id FROM category WHERE name = 'Electronics');

SELECT name AS ProductName
FROM product
WHERE buying_price > (SELECT AVG(buying_price) FROM product);

use LibraryManagement

INSERT INTO author (first_name, last_name, email, mobile, dob, address, nationality, created, modified)
VALUES 
  ('John', 'Doe', 'john.doe@example.com', 1234567890, '1980-05-15', '123 Main St, City', 'American', '2024-02-01 09:00:00', '2024-02-01 09:00:00'),
  ('Alice', 'Smith', 'alice.smith@example.com', 9876543210, '1975-08-22', '456 Oak St, Town', 'British', '2024-02-01 09:15:00', '2024-02-01 09:15:00'),
  ('Bob', 'Johnson', 'bob.johnson@example.com', 5551112233, '1990-03-10', '789 Maple St, Village', 'Canadian', '2024-02-01 09:30:00', '2024-02-01 09:30:00'),
  ('Emma', 'White', 'emma.white@example.com', 4442221111, '1985-12-05','101 Pine St Hamlet','British',  '2024-02-01 10:00:00', '2024-02-01 10:00:00'),
  ('David', 'Brown', 'david.brown@example.com', 9998887777, '1992-07-18', '202 Cedar St, Suburb','American', '2024-02-01 10:30:00', '2024-02-01 10:30:00');


INSERT INTO publisher (name, address, website, edtablished_date, contact_person, created, modified)
VALUES 
  ('Tech Publisher', '123 Tech St, Tech City', 'www.techpublisher.com', '2000-01-01', 1112223333, '2024-02-01 11:00:00', '2024-02-01 11:00:00'),
  ('Book House', '456 Book St, Literature Town', 'www.bookhouse.com', '1985-05-05', 4445556666, '2024-02-01 11:15:00', '2024-02-01 11:15:00'),
  ('Science Publishers', '789 Science St, Research Village', 'www.sciencepubs.com', '1990-10-10', 7778889999, '2024-02-01 11:30:00', '2024-02-01 11:30:00'),
  ('History Books Ltd.', '101 History St, Past City', 'www.historybooks.com', '1980-12-12', 2223334444, '2024-02-01 12:00:00', '2024-02-01 12:00:00'),
  ('Fiction Printers', '202 Fiction St, Imaginary Town', 'www.fictionprinters.com', NULL, 5556667777, '2024-02-01 12:30:00', '2024-02-01 12:30:00');

INSERT INTO book (title, author_id, publisher_id, genre, language, published_date, avalable_copies, created, modified)
VALUES 
  ('Introduction to SQL', 1, 1, 'Technical', 'English', '2023-01-15', 100, '2024-02-01 13:00:00', '2024-02-01 13:00:00'),
  ('The Art of Fiction', 2, 2, 'Fiction', 'English', '2022-05-20', 75, '2024-02-01 13:15:00', '2024-02-01 13:15:00'),
  ('Data Science Essentials', 3, 3, 'Science', 'English', '2023-08-10', 50, '2024-02-01 13:30:00', '2024-02-01 13:30:00'),
  ('History of Civilization', 4, 4, 'History', 'English', '2021-12-01', 25, '2024-02-01 14:00:00', '2024-02-01 14:00:00'),
  ('The World of Fantasy', 5, 5, 'Fantasy', 'English', '2022-03-05', 50, '2024-02-01 14:15:00', '2024-02-01 14:15:00');

INSERT INTO member (first_name, last_name, mobile, email, address, join_date, created, modified)
VALUES 
  ('Michael', 'Johnson', 1234567890, 'michael.j@example.com', '123 Member St, Club City', '2023-01-01', '2024-02-01 14:30:00', '2024-02-01 14:30:00'),
  ('Sara', 'Miller', 9876543210, 'sara.m@example.com', '456 Library St, Book Town', '2022-05-01', '2024-02-01 15:00:00', '2024-02-01 15:00:00'),
  ('Alex', 'Woods', 5551112233, 'alex.w@example.com', '789 Reading St, Novel Village', '2021-08-01', '2024-02-01 15:30:00', '2024-02-01 15:30:00'),
  ('Sophia', 'Clark', 4442221111, 'sophia.c@example.com', '101 Literature St, Story City', '2020-12-01', '2024-02-01 16:00:00', '2024-02-01 16:00:00'),
  ('Robert', 'Taylor', 9998887777, 'robert.t@example.com', '202 Fiction St, Fantasy Town', '2019-03-01', '2024-02-01 16:30:00', '2024-02-01 16:30:00');



INSERT INTO loan (id, member_id, book_id, loan_date, return_date, returned, fine, created, modified)
VALUES 
  (1, 1, 1, '2024-02-01 17:00:00', '2024-02-15 17:00:00', 0, 0.00, '2024-02-01 17:00:00', '2024-02-01 17:00:00'),
  (2, 2, 3, '2024-02-01 17:15:00', '2024-02-10 17:15:00', 1, 5.00, '2024-02-01 17:15:00', '2024-02-01 17:15:00'),
  (3, 3, 2, '2024-02-01 17:30:00', '2024-02-08 17:30:00', 1, 3.50, '2024-02-01 17:30:00', '2024-02-01 17:30:00'),
  (4, 4, 5, '2024-02-01 17:45:00', '2024-02-20 17:45:00', 0, 0.00, '2024-02-01 17:45:00', '2024-02-01 17:45:00'),
  (5, 5, 4, '2024-02-01 18:00:00', '2024-02-12 18:00:00', 1, 7.50, '2024-02-01 18:00:00', '2024-02-01 18:00:00');


-- inline queries for LibraryManagement

SELECT l.id AS LoanID, m.first_name + ' ' + m.last_name AS MemberName, l.loan_date, l.return_date
FROM loan l
INNER JOIN member m ON l.member_id = m.id;

SELECT title AS BookTitle, genre
FROM book;

SELECT first_name + ' ' + last_name AS AuthorName, email
FROM author;


-- sub queries for LibraryManagement
SELECT title AS BookTitle, 
  (SELECT first_name + ' ' + last_name FROM author WHERE id = book.author_id) AS AuthorName
FROM book;


SELECT first_name + ' ' + last_name AS MemberName
FROM member
WHERE id IN (SELECT member_id FROM loan WHERE fine > 0);

SELECT first_name + ' ' + last_name AS AuthorName
FROM author
WHERE id IN (SELECT DISTINCT author_id FROM book);


-- string fun for InventoryManagement

-- Retrieve product names with their lengths:
SELECT name AS ProductName, LEN(name) AS NameLength
FROM product;

-- Display product names in uppercase:
SELECT name AS OriginalName, UPPER(name) AS UppercaseName
FROM product;
-- concate name and mobile number
SELECT CONCAT(name , '-', mobile) from customer

-- Math fun 

-- Show product prices rounded to two decimal places:
SELECT name AS ProductName, ROUND(selling_price, 2) AS RoundedSellingPrice
FROM product;


-- Calculate the total value of stock for each product:
SELECT name AS ProductName, stock, buying_price * stock AS TotalStockValue
FROM product;

-- Generate a random discount percentage for each product:
SELECT name AS ProductName, RAND() * 10 AS RandomDiscountPercentage
FROM product;

---date functions

-- Retrieve order dates and the day of the week:
SELECT id AS OrderID, date AS OrderDate, DATENAME(WEEKDAY, date) AS DayOfWeek
FROM orders;

-- Calculate the difference in days between order date and today:
SELECT id AS OrderID, DATEDIFF(DAY, date, GETDATE()) AS DaysAgo
FROM orders;

-- Retrieve orders placed in the current year:
SELECT id AS OrderID, date
FROM orders
WHERE YEAR(date) = YEAR(GETDATE());

--advanced fun

-- Use CASE to categorize products based on stock levels:
SELECT name AS ProductName,
       stock,
       CASE
         WHEN stock > 50 THEN 'High Stock'
         WHEN stock > 20 THEN 'Moderate Stock'
         ELSE 'Low Stock'
       END AS StockCategory
FROM product;
-- Use IIF to classify orders as large or small based on total amount:
SELECT id AS OrderID, IIF(total_amount > 1000, 'Large Order', 'Small Order') AS OrderSize
FROM orders;
-- Use COALESCE to handle null values in order quantities:
SELECT id AS OrderID, COALESCE(quantity, 0) AS ActualQuantity
FROM orders;

--string fun for library

-- Retrieve book titles with their lengths:
SELECT title AS BookTitle, LEN(title) AS TitleLength
FROM book;
-- Display book titles in uppercase:
SELECT title AS OriginalTitle, UPPER(title) AS UppercaseTitle
FROM book;
-- Concatenate book titles and genres:
SELECT title, genre, CONCAT(title, ' - Genre: ', genre) AS BookWithGenre
FROM book;

--math functions
-- Calculate the total value of books in the library using available copies:
SELECT title AS BookTitle, avalable_copies, avalable_copies * 10 AS TotalBookValue
FROM book;

-- Generate a random discount percentage for each book:
SELECT title AS BookTitle, RAND() * 10 AS RandomDiscountPercentage
FROM book;

-- Generate a random discount percentage for each book based on published date:
SELECT title AS BookTitle, RAND() * (YEAR(GETDATE()) - YEAR(published_date) + 1) AS RandomDiscountPercentage
FROM book;

--Date functions

-- Retrieve loan dates and the day of the week:
SELECT id AS LoanID, loan_date AS LoanDate, DATENAME(WEEKDAY, loan_date) AS DayOfWeek
FROM loan;

-- Calculate the difference in days between loan date and today:
SELECT id AS LoanID, DATEDIFF(DAY, loan_date, GETDATE()) AS DaysAgo
FROM loan;

-- Display loans with formatted return dates:
SELECT id AS LoanID, return_date, FORMAT(return_date, 'yyyy-MM-dd') AS FormattedReturnDate
FROM loan;


--advanced fun

-- Use CASE to categorize books based on available copies:
SELECT title AS BookTitle,
       avalable_copies,
       CASE
         WHEN avalable_copies > 60 THEN 'High Availability'
         WHEN avalable_copies > 30 THEN 'Moderate Availability'
         ELSE 'Low Availability'
       END AS AvailabilityStatus
FROM book;

--handle null values in returned col
SELECT id AS LoanID, COALESCE(returned, 0) AS returned
FROM loan;

-- Use IIF to classify loans as overdue or not based on return dates:
SELECT id AS LoanID, IIF(return_date < GETDATE(), 'Overdue', 'Not Overdue') AS LoanStatus
FROM loan;





