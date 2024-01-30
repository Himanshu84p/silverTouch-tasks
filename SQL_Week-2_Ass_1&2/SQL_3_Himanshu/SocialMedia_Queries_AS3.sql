use SocialMedia

--views for social media 

CREATE VIEW UnreadNotificationsView AS
SELECT
    n.NotificationID,
    n.UserID,
    n.NotificationText,
    n.NotificationDate
FROM
    Notification n
WHERE
    n.IsRead = 0;

Select * from UnreadNotificationsView;

--view for post details
CREATE VIEW postDetails AS SELECT PostID, UserID,content FROM Post;

SELECT * FROM postDetails;


--two scaler valued function 
--for full name 
CREATE FUNCTION dbo.GetUserFullName(@UserID int)
RETURNS varchar(100)
AS
BEGIN
    DECLARE @FullName varchar(100);

    SELECT @FullName = FirstName + ' ' + LastName
    FROM [User]
    WHERE UserID = @UserID;

    RETURN @FullName;
END;

--calling userfullname function
select UserID,
	dbo.GetUserFullName(UserID) as UserFullName
From [User];


--likes ratio 
CREATE FUNCTION dbo.GetUserAge(@UserID int)
RETURNS int
AS
BEGIN
    DECLARE @Age int;

    SELECT @Age = DATEDIFF(YEAR, BirthDate, GETDATE())
    FROM [User]
    WHERE UserID = @UserID;

    RETURN @Age;
END;

--calling age function
select UserID,FirstName,
	dbo.GetUserAge(UserID) as UserAge
From [User];


-- 5 stored procedure

CREATE PROCEDURE dbo.GetUserById
    @UserID int
AS
BEGIN
    SELECT *
    FROM [User]
    WHERE UserID = @UserID;
END;

exec GetUserById @UserID = 1;

--add new user

CREATE PROCEDURE dbo.AddNewUser
    @FirstName varchar(50),
    @LastName varchar(50),
    @Email varchar(100),
    @BirthDate date
AS
BEGIN
    INSERT INTO [User] (FirstName, LastName, Email, BirthDate)
    VALUES (@FirstName, @LastName, @Email, @BirthDate);
END;

exec AddNewUser @FirstName = "Himanshu",
    @LastName= "Prajapati",
    @Email= "hp@123gmail.com",
    @BirthDate ='2023-11-11'


--update user 
CREATE PROCEDURE dbo.UpdateUserName
    @UserID int,
    @NewFirstName varchar(50),
    @NewLastName varchar(50)
AS
BEGIN
    UPDATE [User]
    SET FirstName = @NewFirstName, LastName = @NewLastName
    WHERE UserID = @UserID;
END;

exec UpdateUserName @UserID = 5,
    @NewFirstName = "Kevin",
    @NewLastName = "Patel"


-- delete  user
CREATE PROCEDURE dbo.DeleteUser
    @UserID int
AS
BEGIN
    DELETE FROM [User]
    WHERE UserID = @UserID;
END;

exec DeleteUser @UserID = 5;


--full contains all 4 operation

CREATE PROCEDURE dbo.ManageUser
    @Action int,
    @UserID int = NULL,
    @FirstName varchar(50) = NULL,
    @LastName varchar(50) = NULL,
    @Email varchar(100) = NULL,
    @BirthDate date = NULL
AS
BEGIN
    IF @Action = 1
    BEGIN
        -- Select
        EXEC dbo.GetUserById @UserID;
    END
    ELSE IF @Action = 2
    BEGIN
        -- Add
        EXEC dbo.AddNewUser @FirstName, @LastName, @Email, @BirthDate;
    END
    ELSE IF @Action = 3
    BEGIN
        -- Update
        EXEC dbo.UpdateUserName @UserID, @FirstName, @LastName;
    END
    ELSE IF @Action = 4
    BEGIN
        -- Delete
        EXEC dbo.DeleteUser @UserID;
    END
    ELSE
    BEGIN
        PRINT 'Invalid Action';
    END;
END;

exec ManageUser @Action = 1, @UserID = 1

--non clustered index
CREATE NONCLUSTERED INDEX IX_Post_UserID
ON Post (UserID);

CREATE NONCLUSTERED INDEX IX_Notification_IsRead
ON Notification (IsRead);




-----------------------------------------------triggers on Socialmedia

-- Create BEFORE INSERT trigger
CREATE TRIGGER tr_before_insert_user
ON [User]
Instead of INSERT
AS
BEGIN
	PRINT ' Before insert in user table' 

	INSERT into [User] ([FirstName],[LastName],[Email],[BirthDate])
	select [FirstName],[LastName],[Email],[BirthDate]
	from inserted;
END;

-- Create AFTER INSERT trigger
CREATE TRIGGER tr_after_insert_user
ON [User]
AFTER INSERT
AS
BEGIN
  PRINT 'After Insert trigger in user table'
END;

--insert query
INSERT into [User] values ('Himanshu', 'Prajapati', 'hp@gmail.com','2002-11-25');




-- Create BEFORE UPDATE trigger
CREATE TRIGGER tr_before_update_user
ON [User]
FOR UPDATE
AS
BEGIN
  PRINT 'Before Update trigger in user'
END;

-- Create AFTER UPDATE trigger
ALTER TRIGGER tr_after_update_user
ON [User]
AFTER UPDATE
AS
BEGIN
	PRINT 'After Update trigger in User'
END;
--update query
update [User] set Email = 'Himanshu@gmail.com'
  where UserID = 6;



  -- Create BEFORE DELETE trigger
CREATE TRIGGER tr_before_delete_user
ON [User]
FOR DELETE
AS
BEGIN
  PRINT 'Before Delete trigger in User'
  
END;
 -- Create after DELETE trigger
CREATE TRIGGER tr_after_delete_user
ON [User]
AFTER DELETE
AS
BEGIN
  PRINT 'After Delete trigger in User'
END;

--delete query
Delete From [User] where UserID = 6;


--union and union all
-- Using UNION to get unique user IDs
SELECT UserID FROM [User]
UNION
SELECT UserID FROM Post;

-- Using UNION ALL to get all user IDs (including duplicates)
SELECT UserID FROM [User]
UNION ALL
SELECT UserID FROM Post;

--join queries
SELECT *
FROM [User]
INNER JOIN Friendship ON [User].UserID = Friendship.UserID1;

SELECT *
FROM [User]
LEFT JOIN Friendship ON [User].UserID = Friendship.UserID1;

SELECT *
FROM [User]
RIGHT JOIN Friendship ON [User].UserID = Friendship.UserID1;

SELECT *
FROM [User]
FULL JOIN Friendship ON [User].UserID = Friendship.UserID1;



