use Library

SELECT title AS BookTitle, avalable_copies, avalable_copies * 10 AS TotalBookValue
FROM book;

SELECT title AS BookTitle, RAND() * (YEAR(GETDATE()) - YEAR(published_date) + 1) AS RandomDiscountPercentage
FROM book;


SELECT title AS BookTitle, RAND() * 10 AS RandomDiscountPercentage
FROM book;

SELECT id AS LoanID, loan_date AS LoanDate, DATENAME(WEEKDAY, loan_date) AS DayOfWeek
FROM loan;

SELECT id AS LoanID, DATEDIFF(DAY, loan_date, GETDATE()) AS DaysAgo
FROM loan;

SELECT id AS LoanID, return_date, FORMAT(return_date, 'yyyy-MM-dd') AS FormattedReturnDate
FROM loan;

SELECT title AS BookTitle,
       avalable_copies,
       CASE
         WHEN avalable_copies > 60 THEN 'High Availability'
         WHEN avalable_copies > 30 THEN 'Moderate Availability'
         ELSE 'Low Availability'
       END AS AvailabilityStatus
FROM book;

SELECT id AS LoanID, COALESCE(returned, 0) AS returned
FROM loan;

SELECT id AS LoanID, IIF(return_date < GETDATE(), 'Overdue', 'Not Overdue') AS LoanStatus
FROM loan;




