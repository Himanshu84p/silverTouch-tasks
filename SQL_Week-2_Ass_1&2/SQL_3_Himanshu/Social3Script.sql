USE [master]
GO
/****** Object:  Database [SocialMedia]    Script Date: 23-01-2024 16:47:44 ******/
CREATE DATABASE [SocialMedia]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SocialMedia_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SocialMedia.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SocialMedia_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SocialMedia.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SocialMedia] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SocialMedia].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SocialMedia] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SocialMedia] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SocialMedia] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SocialMedia] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SocialMedia] SET ARITHABORT OFF 
GO
ALTER DATABASE [SocialMedia] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [SocialMedia] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SocialMedia] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SocialMedia] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SocialMedia] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SocialMedia] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SocialMedia] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SocialMedia] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SocialMedia] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SocialMedia] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SocialMedia] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SocialMedia] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SocialMedia] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SocialMedia] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SocialMedia] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SocialMedia] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SocialMedia] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SocialMedia] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SocialMedia] SET  MULTI_USER 
GO
ALTER DATABASE [SocialMedia] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SocialMedia] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SocialMedia] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SocialMedia] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SocialMedia] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SocialMedia] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SocialMedia] SET QUERY_STORE = OFF
GO
USE [SocialMedia]
GO
/****** Object:  UserDefinedFunction [dbo].[GetUserAge]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[GetUserAge](@UserID int)
RETURNS int
AS
BEGIN
    DECLARE @Age int;

    SELECT @Age = DATEDIFF(YEAR, BirthDate, GETDATE())
    FROM [User]
    WHERE UserID = @UserID;

    RETURN @Age;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[GetUserFullName]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[GetUserFullName](@UserID int)
RETURNS varchar(100)
AS
BEGIN
    DECLARE @FullName varchar(100);

    SELECT @FullName = FirstName + ' ' + LastName
    FROM [User]
    WHERE UserID = @UserID;

    RETURN @FullName;
END;
GO
/****** Object:  Table [dbo].[Notification]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[NotificationID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[NotificationText] [text] NOT NULL,
	[IsRead] [bit] NOT NULL,
	[NotificationDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[NotificationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  View [dbo].[UnreadNotificationsView]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[UnreadNotificationsView] AS
SELECT
    n.NotificationID,
    n.UserID,
    n.NotificationText,
    n.NotificationDate
FROM
    Notification n
WHERE
    n.IsRead = 0;
GO
/****** Object:  Table [dbo].[Post]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post](
	[PostID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[Content] [text] NOT NULL,
	[PostDate] [datetime] NOT NULL,
	[LikesCount] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PostID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  View [dbo].[postDetails]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[postDetails] AS SELECT PostID, UserID,content FROM Post;
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[CommentID] [int] IDENTITY(1,1) NOT NULL,
	[PostID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[CommentText] [text] NOT NULL,
	[CommentDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CommentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Friendship]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Friendship](
	[FriendshipID] [int] IDENTITY(1,1) NOT NULL,
	[UserID1] [int] NOT NULL,
	[UserID2] [int] NOT NULL,
	[Status] [varchar](20) NOT NULL,
	[RequestDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[FriendshipID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [varchar](50) NOT NULL,
	[LastName] [varchar](50) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[BirthDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Comment] ON 

INSERT [dbo].[Comment] ([CommentID], [PostID], [UserID], [CommentText], [CommentDate]) VALUES (1, 1, 2, N'Looks amazing! Where is this?', CAST(N'2022-01-15T11:30:00.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [PostID], [UserID], [CommentText], [CommentDate]) VALUES (2, 1, 3, N'I love that place too! Great choice.', CAST(N'2022-01-15T12:00:00.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [PostID], [UserID], [CommentText], [CommentDate]) VALUES (3, 2, 1, N'Cant it to see the final result! ', CAST(N'2022-02-20T15:30:00.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [PostID], [UserID], [CommentText], [CommentDate]) VALUES (4, 3, 4, N'Lazy Sundays are the best! ?', CAST(N'2022-03-05T09:00:00.000' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [PostID], [UserID], [CommentText], [CommentDate]) VALUES (5, 4, 5, N'Happy Birthday! ????', CAST(N'2022-04-10T12:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Comment] OFF
GO
SET IDENTITY_INSERT [dbo].[Friendship] ON 

INSERT [dbo].[Friendship] ([FriendshipID], [UserID1], [UserID2], [Status], [RequestDate]) VALUES (1, 1, 2, N'Accepted', CAST(N'2022-01-15T10:30:00.000' AS DateTime))
INSERT [dbo].[Friendship] ([FriendshipID], [UserID1], [UserID2], [Status], [RequestDate]) VALUES (2, 2, 3, N'Pending', CAST(N'2022-02-20T14:45:00.000' AS DateTime))
INSERT [dbo].[Friendship] ([FriendshipID], [UserID1], [UserID2], [Status], [RequestDate]) VALUES (3, 3, 4, N'Accepted', CAST(N'2022-03-05T08:15:00.000' AS DateTime))
INSERT [dbo].[Friendship] ([FriendshipID], [UserID1], [UserID2], [Status], [RequestDate]) VALUES (4, 4, 5, N'Pending', CAST(N'2022-04-10T12:00:00.000' AS DateTime))
INSERT [dbo].[Friendship] ([FriendshipID], [UserID1], [UserID2], [Status], [RequestDate]) VALUES (5, 5, 1, N'Accepted', CAST(N'2022-05-20T16:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Friendship] OFF
GO
SET IDENTITY_INSERT [dbo].[Notification] ON 

INSERT [dbo].[Notification] ([NotificationID], [UserID], [NotificationText], [IsRead], [NotificationDate]) VALUES (1, 1, N'You have a new friend request!', 0, CAST(N'2022-01-15T10:30:00.000' AS DateTime))
INSERT [dbo].[Notification] ([NotificationID], [UserID], [NotificationText], [IsRead], [NotificationDate]) VALUES (2, 2, N'Your post has received 10 likes!', 1, CAST(N'2022-02-20T14:45:00.000' AS DateTime))
INSERT [dbo].[Notification] ([NotificationID], [UserID], [NotificationText], [IsRead], [NotificationDate]) VALUES (3, 3, N'New comment on your post: "Looks amazing!"', 0, CAST(N'2022-03-05T08:15:00.000' AS DateTime))
INSERT [dbo].[Notification] ([NotificationID], [UserID], [NotificationText], [IsRead], [NotificationDate]) VALUES (4, 4, N'Reminde Your friends birthday is tomorrow!', 0, CAST(N'2022-04-10T12:00:00.000' AS DateTime))
INSERT [dbo].[Notification] ([NotificationID], [UserID], [NotificationText], [IsRead], [NotificationDate]) VALUES (5, 5, N'You have been tagged in a photo!', 0, CAST(N'2022-05-20T16:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Notification] OFF
GO
SET IDENTITY_INSERT [dbo].[Post] ON 

INSERT [dbo].[Post] ([PostID], [UserID], [Content], [PostDate], [LikesCount]) VALUES (1, 1, N'Just visited a beautiful place!', CAST(N'2022-01-15T10:30:00.000' AS DateTime), 25)
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [PostDate], [LikesCount]) VALUES (2, 2, N'Working on a new project. Excited!', CAST(N'2022-02-20T14:45:00.000' AS DateTime), 10)
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [PostDate], [LikesCount]) VALUES (3, 3, N'Enjoying a cup of coffee on a lazy Sunday.', CAST(N'2022-03-05T08:15:00.000' AS DateTime), 15)
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [PostDate], [LikesCount]) VALUES (4, 4, N'Celebrating my birthday today!', CAST(N'2022-04-10T12:00:00.000' AS DateTime), 50)
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [PostDate], [LikesCount]) VALUES (5, 5, N'Completed a challenging workout. Feeling accomplished!', CAST(N'2022-05-20T16:30:00.000' AS DateTime), 30)
SET IDENTITY_INSERT [dbo].[Post] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [BirthDate]) VALUES (1, N'Sara', N'Khan', N'sara.khan@email.com', CAST(N'1990-03-15' AS Date))
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [BirthDate]) VALUES (2, N'Rahul', N'Sharma', N'rahul.sharma@email.com', CAST(N'1985-08-20' AS Date))
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [BirthDate]) VALUES (3, N'Preeti', N'Patel', N'preeti.patel@email.com', CAST(N'1995-05-10' AS Date))
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [BirthDate]) VALUES (4, N'Raj', N'Singh', N'raj.singh@email.com', CAST(N'1980-11-25' AS Date))
INSERT [dbo].[User] ([UserID], [FirstName], [LastName], [Email], [BirthDate]) VALUES (7, N'Himanshu', N'Prajapati', N'hp@gmail.com', CAST(N'2002-11-25' AS Date))
SET IDENTITY_INSERT [dbo].[User] OFF
GO
/****** Object:  Index [IX_Notification_IsRead]    Script Date: 23-01-2024 16:47:44 ******/
CREATE NONCLUSTERED INDEX [IX_Notification_IsRead] ON [dbo].[Notification]
(
	[IsRead] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Post_UserID]    Script Date: 23-01-2024 16:47:44 ******/
CREATE NONCLUSTERED INDEX [IX_Post_UserID] ON [dbo].[Post]
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[AddNewUser]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[AddNewUser]
    @FirstName varchar(50),
    @LastName varchar(50),
    @Email varchar(100),
    @BirthDate date
AS
BEGIN
    INSERT INTO [User] (FirstName, LastName, Email, BirthDate)
    VALUES (@FirstName, @LastName, @Email, @BirthDate);
END;
GO
/****** Object:  StoredProcedure [dbo].[DeleteUser]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[DeleteUser]
    @UserID int
AS
BEGIN
    DELETE FROM [User]
    WHERE UserID = @UserID;
END;
GO
/****** Object:  StoredProcedure [dbo].[GetUserById]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GetUserById]
    @UserID int
AS
BEGIN
    SELECT *
    FROM [User]
    WHERE UserID = @UserID;
END;
GO
/****** Object:  StoredProcedure [dbo].[ManageUser]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[ManageUser]
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
GO
/****** Object:  StoredProcedure [dbo].[UpdateUserName]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[UpdateUserName]
    @UserID int,
    @NewFirstName varchar(50),
    @NewLastName varchar(50)
AS
BEGIN
    UPDATE [User]
    SET FirstName = @NewFirstName, LastName = @NewLastName
    WHERE UserID = @UserID;
END;
GO
/****** Object:  Trigger [dbo].[tr_after_delete_user]    Script Date: 23-01-2024 16:47:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[tr_after_delete_user]
ON [dbo].[User]
AFTER DELETE
AS
BEGIN
  PRINT 'After Delete trigger in User'
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [tr_after_delete_user]
GO
/****** Object:  Trigger [dbo].[tr_after_insert_user]    Script Date: 23-01-2024 16:47:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[tr_after_insert_user]
ON [dbo].[User]
AFTER INSERT
AS
BEGIN
  PRINT 'After Insert trigger in user table'
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [tr_after_insert_user]
GO
/****** Object:  Trigger [dbo].[tr_after_update_user]    Script Date: 23-01-2024 16:47:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[tr_after_update_user]
ON [dbo].[User]
AFTER UPDATE
AS
BEGIN
	PRINT 'After Update trigger in User'
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [tr_after_update_user]
GO
/****** Object:  Trigger [dbo].[tr_before_delete_user]    Script Date: 23-01-2024 16:47:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[tr_before_delete_user]
ON [dbo].[User]
FOR DELETE
AS
BEGIN
  PRINT 'Before Delete trigger in User'
  
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [tr_before_delete_user]
GO
/****** Object:  Trigger [dbo].[tr_before_insert_user]    Script Date: 23-01-2024 16:47:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[tr_before_insert_user]
ON [dbo].[User]
Instead of INSERT
AS
BEGIN
	PRINT ' Before insert in user table' 

	INSERT into [User] ([FirstName],[LastName],[Email],[BirthDate])
	select [FirstName],[LastName],[Email],[BirthDate]
	from inserted;
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [tr_before_insert_user]
GO
/****** Object:  Trigger [dbo].[tr_before_update_user]    Script Date: 23-01-2024 16:47:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[tr_before_update_user]
ON [dbo].[User]
FOR UPDATE
AS
BEGIN
  PRINT 'Before Update trigger in user'
END;
GO
ALTER TABLE [dbo].[User] ENABLE TRIGGER [tr_before_update_user]
GO
USE [master]
GO
ALTER DATABASE [SocialMedia] SET  READ_WRITE 
GO
