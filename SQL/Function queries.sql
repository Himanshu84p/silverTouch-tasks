SELECT l.id AS LoanID, m.first_name + ' ' + m.last_name AS MemberName, l.loan_date, l.return_date
FROM loan l
INNER JOIN member m ON l.member_id = m.id;

SELECT title AS BookTitle, genre
FROM book;

SELECT first_name + ' ' + last_name AS AuthorName, email
FROM author;



SELECT title AS BookTitle, 
  (SELECT first_name + ' ' + last_name FROM author WHERE id = book.author_id) AS AuthorName
FROM book;


SELECT first_name + ' ' + last_name AS MemberName
FROM member
WHERE id IN (SELECT member_id FROM loan WHERE fine > 0);

SELECT first_name + ' ' + last_name AS AuthorName
FROM author
WHERE id IN (SELECT DISTINCT author_id FROM book);

use Inventory

SELECT name AS ProductName, LEN(name) AS NameLength
FROM product;

SELECT name AS OriginalName, UPPER(name) AS UppercaseName
FROM product;

SELECT CONCAT(name , '-', mobile) from customer

SELECT name AS ProductName, ROUND(selling_price, 2) AS RoundedSellingPrice
FROM product;

SELECT name AS ProductName, stock, buying_price * stock AS TotalStockValue
FROM product;

SELECT name AS ProductName, RAND() * 10 AS RandomDiscountPercentage
FROM product;


SELECT id AS OrderID, date AS OrderDate, DATENAME(WEEKDAY, date) AS DayOfWeek
FROM orders;

SELECT id AS OrderID, DATEDIFF(DAY, date, GETDATE()) AS DaysAgo
FROM orders;

SELECT id AS OrderID, date
FROM orders
WHERE YEAR(date) = YEAR(GETDATE());

SELECT name AS ProductName,
       stock,
       CASE
         WHEN stock > 50 THEN 'High Stock'
         WHEN stock > 20 THEN 'Moderate Stock'
         ELSE 'Low Stock'
       END AS StockCategory
FROM product;

SELECT id AS OrderID, IIF(total_amount > 1000, 'Large Order', 'Small Order') AS OrderSize
FROM orders;

SELECT id AS OrderID, COALESCE(quantity, 0) AS ActualQuantity
FROM orders;

use Library

SELECT title AS BookTitle, LEN(title) AS TitleLength
FROM book;

SELECT title AS OriginalTitle, UPPER(title) AS UppercaseTitle
FROM book;

SELECT title, genre, CONCAT(title, ' - Genre: ', genre) AS BookWithGenre
FROM book;

SELECT title AS BookTitle, RAND() * 10 AS RandomDiscountPercentage
FROM book;






CREATE TABLE dbo.Comment(
	CommentID int IDENTITY(1,1) NOT NULL,
	PostID int NOT NULL,
	UserID int NOT NULL,
	CommentText text NOT NULL,
	CommentDate datetime NOT NULL,
)

CREATE TABLE Friendship(
	FriendshipID int IDENTITY(1,1) NOT NULL,
	UserID1 int NOT NULL,
	UserID2 int NOT NULL,
	Status varchar(20) NOT NULL,
	RequestDate datetime NOT NULL,
)

CREATE TABLE Notification(
	NotificationID int IDENTITY(1,1) NOT NULL,
	UserID int NOT NULL,
	NotificationText text NOT NULL,
	IsRead bit NOT NULL,
	NotificationDate datetime NOT NULL,
)

CREATE TABLE Post(
	PostID int IDENTITY(1,1) NOT NULL,
	UserID int NOT NULL,
	Content text NOT NULL,
	PostDate datetime NOT NULL,
	LikesCount int NOT NULL,
)

CREATE TABLE User(
	UserID int IDENTITY(1,1) NOT NULL,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	Email varchar(100) NOT NULL,
	BirthDate date NULL,
)














