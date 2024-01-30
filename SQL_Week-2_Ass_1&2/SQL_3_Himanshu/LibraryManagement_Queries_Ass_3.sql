use LibraryManagement

-----------------------------------------------------------------------------------------------user defined views 

CREATE VIEW authorDetails AS SELECT first_name, last_name, email, mobile FROM author;

SELECT * FROM authorDetails;

CREATE VIEW memberDetails AS SELECT first_name, last_name, email, mobile FROM member;

SELECT * FROM memberDetails;


---- -----------------------------------------------------------------------------------------------2 Scalar-valued functions
--to get full name
CREATE FUNCTION GetAuthorFullName
(
    @authorId INT
)
RETURNS NVARCHAR(100)
AS
BEGIN
    DECLARE @fullName NVARCHAR(100);

    SELECT @fullName = CONCAT(first_name, ' ', last_name)
    FROM author
    WHERE id = @authorId;

    RETURN @fullName;
END;

--exec scaler fun for authorfullname
DECLARE @authorId INT = 2;
SELECT dbo.GetAuthorFullName(@authorId) AS AuthorFullName;


--fun for get available copies

CREATE FUNCTION GetAvailableCopies
(
    @bookId INT
)
RETURNS INT
AS
BEGIN
	DECLARE @availableCopies INT;
	SELECT @availableCopies = avalable_copies FROM book WHERE id = @bookId;
    RETURN @availableCopies;
END;

-- Example usage of GetAvailableCopies function

SELECT title As name,
	language as lang,
	dbo.GetAvailableCopies(id) as AvailableCopies
FROM book;

--Create 5 store procedures (select, add, update, delete and full).

--select procedure
CREATE PROCEDURE SelectBook
AS
BEGIN
    SELECT *
    FROM book
    
END;

exec SelectBook;


----------------------------------------------------- add procedure

CREATE PROCEDURE AddBook
    @title NVARCHAR(50),
    @authorId INT,
    @publisherId INT,
    @genre NVARCHAR(50),
    @language TEXT,
    @publishedDate DATE,
    @availableCopies INT
AS
BEGIN
    INSERT INTO book (title, author_id, publisher_id, genre, language, published_date, avalable_copies, created, modified)
    VALUES (@title, @authorId, @publisherId, @genre, @language, @publishedDate, @availableCopies, GETDATE(), GETDATE());
END;

exec AddBook @title = "Atomic Habits",
    @authorId = 2,
    @publisherId = 3 ,
    @genre = "Self help",
    @language = "English",
    @publishedDate = '2023-11-25',
    @availableCopies = 200;


-- -------------------------------------------------update procedure 

CREATE PROCEDURE UpdateBook
    @bookId INT,
    @title NVARCHAR(50),
    @genre NVARCHAR(50),
    @language TEXT,
    @publishedDate DATE,
    @availableCopies INT
AS
BEGIN
    UPDATE book
    SET
        title = @title,
        genre = @genre,
        language = @language,
        published_date = @publishedDate,
        avalable_copies = @availableCopies,
        modified = GETDATE()
    WHERE
        id = @bookId;
END;

exec UpdateBook @bookId = 4,
    @title = "Rich Dad Poor Dad",
    @genre = "Self Help",
    @language = "English" ,
    @publishedDate = '2023-11-25',
    @availableCopies = 150;

-- -----------------------------------------------------------------delete procedure 

CREATE PROCEDURE DeleteBook
    @bookId INT
AS
BEGIN
    DELETE FROM book
    WHERE id = @bookId;
END;

exec DeleteBook @bookId = 6;

----------------------------------------------------------------------full  procedure 

CREATE PROCEDURE ManageBook
    @action VARCHAR(10),  -- 'SELECT', 'ADD', 'UPDATE', 'DELETE'
    @bookId INT = NULL,
    @title NVARCHAR(50) = NULL,
    @authorId INT = NULL,
    @publisherId INT = NULL,
    @genre NVARCHAR(50) = NULL,
    @language TEXT = NULL,
    @publishedDate DATE = NULL,
    @availableCopies INT = NULL
AS
BEGIN
    IF @action = 'SELECT'
    BEGIN
        -- SELECT Procedure
        SELECT *
        FROM book
    END
    ELSE IF @action = 'ADD'
    BEGIN
        -- ADD Procedure
        INSERT INTO book (title, author_id, publisher_id, genre, language, published_date, avalable_copies, created, modified)
        VALUES (@title, @authorId, @publisherId, @genre, @language, @publishedDate, @availableCopies, GETDATE(), GETDATE());
    END
    ELSE IF @action = 'UPDATE'
    BEGIN
        -- UPDATE Procedure
        UPDATE book
        SET
            title = @title,
            genre = @genre,
            language = @language,
            published_date = @publishedDate,
            avalable_copies = @availableCopies,
            modified = GETDATE()
        WHERE
            id = @bookId;
    END
    ELSE IF @action = 'DELETE'
    BEGIN
        -- DELETE Procedure
        DELETE FROM book
        WHERE id = @bookId;
    END
    ELSE
    BEGIN
        -- Invalid action
                PRINT 'Invalid action specified.';
    END
END;

exec ManageBook @action ="SELECT";


-- non-clustered index 
CREATE NONCLUSTERED INDEX IX_Book_AuthorId
ON book (author_id);

CREATE NONCLUSTERED INDEX IX_Book_PublishedDate
ON book (published_date);



--triggers on Library 

-- Create BEFORE INSERT trigger
Create TRIGGER tr_before_insert_member
ON member
Instead of INSERT
AS
BEGIN
	Insert into Log Values ('Insert before trigger in member table ', GETDATE()); 

	INSERT into member ([first_name],[last_name],[mobile],[email],[address],[join_date],[created],[modified])
	select [first_name],[last_name],[mobile],[email],[address],[join_date],[created],[modified]
	from inserted;
END;

-- Create AFTER INSERT trigger
CREATE TRIGGER tr_after_insert_member
ON member
AFTER INSERT
AS
BEGIN
  PRINT 'After Insert trigger in member table'
END;

--insert query
INSERT into member values ('Himanshu', 'Prajapati','1234567890', 'hp@gmail.com', 'Khambhat' ,'2022-10-25',GETDATE(),GETDATE());




-- Create BEFORE UPDATE trigger
CREATE TRIGGER tr_before_update_member
ON member
instead of UPDATE
AS
BEGIN
  PRINT 'Before Update trigger in member'
  update member set modified = GETDATE()
  where id in(select id from inserted)
END;

-- Create AFTER UPDATE trigger
CREATE TRIGGER tr_after_update_member
ON member
AFTER UPDATE
AS
BEGIN
	PRINT 'After Update trigger in member'
END;
--update query
update member set modified = GETDATE()
  where id = 10;



  -- Create BEFORE DELETE trigger
CREATE TRIGGER tr_before_delete_member
ON member
INSTEAD OF DELETE
AS
BEGIN
  PRINT 'Before Delete trigger in member'
  Delete From member where id in (select id from deleted);
END;

 -- Create after DELETE trigger
CREATE TRIGGER tr_after_delete_member
ON member
AFTER DELETE
AS
BEGIN
  PRINT 'After Delete trigger in member'
END;

--delete query
Delete From member where id = 10;



--union & union all queries 
-- Using UNION
SELECT first_name, last_name FROM author
UNION
SELECT first_name, last_name FROM member;

-- Using UNION ALL
SELECT first_name, last_name FROM author
UNION ALL
SELECT first_name, last_name FROM member;


--join queries
SELECT *
FROM book
INNER JOIN author ON book.author_id = author.id;

SELECT *
FROM book
LEFT JOIN author ON book.author_id = author.id;

SELECT *
FROM book
RIGHT JOIN author ON book.author_id = author.id;

SELECT *
FROM book
FULL JOIN author ON book.author_id = author.id;





