USE [master]
GO
/****** Object:  Database [LibraryManagement]    Script Date: 22-01-2024 14:34:26 ******/
CREATE DATABASE [LibraryManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LibraryManagement_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\LibraryManagement.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'LibraryManagement_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\LibraryManagement.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [LibraryManagement] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LibraryManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LibraryManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LibraryManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LibraryManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LibraryManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LibraryManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [LibraryManagement] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LibraryManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LibraryManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LibraryManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LibraryManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LibraryManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LibraryManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LibraryManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LibraryManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LibraryManagement] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LibraryManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LibraryManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LibraryManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LibraryManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LibraryManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LibraryManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LibraryManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LibraryManagement] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LibraryManagement] SET  MULTI_USER 
GO
ALTER DATABASE [LibraryManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LibraryManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LibraryManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LibraryManagement] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LibraryManagement] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LibraryManagement] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [LibraryManagement] SET QUERY_STORE = OFF
GO
USE [LibraryManagement]
GO
/****** Object:  Table [dbo].[author]    Script Date: 22-01-2024 14:34:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[author](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[email] [nvarchar](320) NOT NULL,
	[mobile] [bigint] NOT NULL,
	[dob] [date] NOT NULL,
	[address] [ntext] NOT NULL,
	[nationality] [nvarchar](50) NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_author] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[book]    Script Date: 22-01-2024 14:34:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[book](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](50) NOT NULL,
	[author_id] [int] NOT NULL,
	[publisher_id] [int] NOT NULL,
	[genre] [nvarchar](50) NOT NULL,
	[language] [text] NOT NULL,
	[published_date] [date] NOT NULL,
	[avalable_copies] [int] NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_book] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[loan]    Script Date: 22-01-2024 14:34:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[loan](
	[id] [int] NOT NULL,
	[member_id] [int] NOT NULL,
	[book_id] [int] NOT NULL,
	[loan_date] [datetime] NOT NULL,
	[return_date] [datetime] NOT NULL,
	[returned] [bit] NOT NULL,
	[fine] [decimal](10, 2) NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[member]    Script Date: 22-01-2024 14:34:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[member](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[mobile] [bigint] NOT NULL,
	[email] [nvarchar](320) NOT NULL,
	[address] [ntext] NOT NULL,
	[join_date] [date] NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_member] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[publisher]    Script Date: 22-01-2024 14:34:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[publisher](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[address] [text] NOT NULL,
	[website] [text] NOT NULL,
	[edtablished_date] [date] NULL,
	[contact_person] [bigint] NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_publisher] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[author] ON 

INSERT [dbo].[author] ([id], [first_name], [last_name], [email], [mobile], [dob], [address], [nationality], [created], [modified]) VALUES (1, N'John', N'Doe', N'john.doe@example.com', 1234567890, CAST(N'1980-05-15' AS Date), N'123 Main St, City', N'American', CAST(N'2024-02-01T09:00:00.000' AS DateTime), CAST(N'2024-02-01T09:00:00.000' AS DateTime))
INSERT [dbo].[author] ([id], [first_name], [last_name], [email], [mobile], [dob], [address], [nationality], [created], [modified]) VALUES (2, N'Alice', N'Smith', N'alice.smith@example.com', 9876543210, CAST(N'1975-08-22' AS Date), N'456 Oak St, Town', N'British', CAST(N'2024-02-01T09:15:00.000' AS DateTime), CAST(N'2024-02-01T09:15:00.000' AS DateTime))
INSERT [dbo].[author] ([id], [first_name], [last_name], [email], [mobile], [dob], [address], [nationality], [created], [modified]) VALUES (3, N'Bob', N'Johnson', N'bob.johnson@example.com', 5551112233, CAST(N'1990-03-10' AS Date), N'789 Maple St, Village', N'Canadian', CAST(N'2024-02-01T09:30:00.000' AS DateTime), CAST(N'2024-02-01T09:30:00.000' AS DateTime))
INSERT [dbo].[author] ([id], [first_name], [last_name], [email], [mobile], [dob], [address], [nationality], [created], [modified]) VALUES (4, N'Emma', N'White', N'emma.white@example.com', 4442221111, CAST(N'1985-12-05' AS Date), N'101 Pine St Hamlet', N'British', CAST(N'2024-02-01T10:00:00.000' AS DateTime), CAST(N'2024-02-01T10:00:00.000' AS DateTime))
INSERT [dbo].[author] ([id], [first_name], [last_name], [email], [mobile], [dob], [address], [nationality], [created], [modified]) VALUES (5, N'David', N'Brown', N'david.brown@example.com', 9998887777, CAST(N'1992-07-18' AS Date), N'202 Cedar St, Suburb', N'American', CAST(N'2024-02-01T10:30:00.000' AS DateTime), CAST(N'2024-02-01T10:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[author] OFF
GO
SET IDENTITY_INSERT [dbo].[book] ON 

INSERT [dbo].[book] ([id], [title], [author_id], [publisher_id], [genre], [language], [published_date], [avalable_copies], [created], [modified]) VALUES (1, N'Introduction to SQL', 1, 1, N'Technical', N'English', CAST(N'2023-01-15' AS Date), 100, CAST(N'2024-02-01T13:00:00.000' AS DateTime), CAST(N'2024-02-01T13:00:00.000' AS DateTime))
INSERT [dbo].[book] ([id], [title], [author_id], [publisher_id], [genre], [language], [published_date], [avalable_copies], [created], [modified]) VALUES (2, N'The Art of Fiction', 2, 2, N'Fiction', N'English', CAST(N'2022-05-20' AS Date), 75, CAST(N'2024-02-01T13:15:00.000' AS DateTime), CAST(N'2024-02-01T13:15:00.000' AS DateTime))
INSERT [dbo].[book] ([id], [title], [author_id], [publisher_id], [genre], [language], [published_date], [avalable_copies], [created], [modified]) VALUES (3, N'Data Science Essentials', 3, 3, N'Science', N'English', CAST(N'2023-08-10' AS Date), 50, CAST(N'2024-02-01T13:30:00.000' AS DateTime), CAST(N'2024-02-01T13:30:00.000' AS DateTime))
INSERT [dbo].[book] ([id], [title], [author_id], [publisher_id], [genre], [language], [published_date], [avalable_copies], [created], [modified]) VALUES (4, N'History of Civilization', 4, 4, N'History', N'English', CAST(N'2021-12-01' AS Date), 25, CAST(N'2024-02-01T14:00:00.000' AS DateTime), CAST(N'2024-02-01T14:00:00.000' AS DateTime))
INSERT [dbo].[book] ([id], [title], [author_id], [publisher_id], [genre], [language], [published_date], [avalable_copies], [created], [modified]) VALUES (5, N'The World of Fantasy', 5, 5, N'Fantasy', N'English', CAST(N'2022-03-05' AS Date), 50, CAST(N'2024-02-01T14:15:00.000' AS DateTime), CAST(N'2024-02-01T14:15:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[book] OFF
GO
INSERT [dbo].[loan] ([id], [member_id], [book_id], [loan_date], [return_date], [returned], [fine], [created], [modified]) VALUES (1, 1, 1, CAST(N'2024-02-01T17:00:00.000' AS DateTime), CAST(N'2024-02-15T17:00:00.000' AS DateTime), 0, CAST(0.00 AS Decimal(10, 2)), CAST(N'2024-02-01T17:00:00.000' AS DateTime), CAST(N'2024-02-01T17:00:00.000' AS DateTime))
INSERT [dbo].[loan] ([id], [member_id], [book_id], [loan_date], [return_date], [returned], [fine], [created], [modified]) VALUES (2, 2, 3, CAST(N'2024-02-01T17:15:00.000' AS DateTime), CAST(N'2024-02-10T17:15:00.000' AS DateTime), 1, CAST(5.00 AS Decimal(10, 2)), CAST(N'2024-02-01T17:15:00.000' AS DateTime), CAST(N'2024-02-01T17:15:00.000' AS DateTime))
INSERT [dbo].[loan] ([id], [member_id], [book_id], [loan_date], [return_date], [returned], [fine], [created], [modified]) VALUES (3, 3, 2, CAST(N'2024-02-01T17:30:00.000' AS DateTime), CAST(N'2024-02-08T17:30:00.000' AS DateTime), 1, CAST(3.50 AS Decimal(10, 2)), CAST(N'2024-02-01T17:30:00.000' AS DateTime), CAST(N'2024-02-01T17:30:00.000' AS DateTime))
INSERT [dbo].[loan] ([id], [member_id], [book_id], [loan_date], [return_date], [returned], [fine], [created], [modified]) VALUES (4, 4, 5, CAST(N'2024-02-01T17:45:00.000' AS DateTime), CAST(N'2024-02-20T17:45:00.000' AS DateTime), 0, CAST(0.00 AS Decimal(10, 2)), CAST(N'2024-02-01T17:45:00.000' AS DateTime), CAST(N'2024-02-01T17:45:00.000' AS DateTime))
INSERT [dbo].[loan] ([id], [member_id], [book_id], [loan_date], [return_date], [returned], [fine], [created], [modified]) VALUES (5, 5, 4, CAST(N'2024-02-01T18:00:00.000' AS DateTime), CAST(N'2024-02-12T18:00:00.000' AS DateTime), 1, CAST(7.50 AS Decimal(10, 2)), CAST(N'2024-02-01T18:00:00.000' AS DateTime), CAST(N'2024-02-01T18:00:00.000' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[member] ON 

INSERT [dbo].[member] ([id], [first_name], [last_name], [mobile], [email], [address], [join_date], [created], [modified]) VALUES (1, N'Michael', N'Johnson', 1234567890, N'michael.j@example.com', N'123 Member St, Club City', CAST(N'2023-01-01' AS Date), CAST(N'2024-02-01T14:30:00.000' AS DateTime), CAST(N'2024-02-01T14:30:00.000' AS DateTime))
INSERT [dbo].[member] ([id], [first_name], [last_name], [mobile], [email], [address], [join_date], [created], [modified]) VALUES (2, N'Sara', N'Miller', 9876543210, N'sara.m@example.com', N'456 Library St, Book Town', CAST(N'2022-05-01' AS Date), CAST(N'2024-02-01T15:00:00.000' AS DateTime), CAST(N'2024-02-01T15:00:00.000' AS DateTime))
INSERT [dbo].[member] ([id], [first_name], [last_name], [mobile], [email], [address], [join_date], [created], [modified]) VALUES (3, N'Alex', N'Woods', 5551112233, N'alex.w@example.com', N'789 Reading St, Novel Village', CAST(N'2021-08-01' AS Date), CAST(N'2024-02-01T15:30:00.000' AS DateTime), CAST(N'2024-02-01T15:30:00.000' AS DateTime))
INSERT [dbo].[member] ([id], [first_name], [last_name], [mobile], [email], [address], [join_date], [created], [modified]) VALUES (4, N'Sophia', N'Clark', 4442221111, N'sophia.c@example.com', N'101 Literature St, Story City', CAST(N'2020-12-01' AS Date), CAST(N'2024-02-01T16:00:00.000' AS DateTime), CAST(N'2024-02-01T16:00:00.000' AS DateTime))
INSERT [dbo].[member] ([id], [first_name], [last_name], [mobile], [email], [address], [join_date], [created], [modified]) VALUES (5, N'Robert', N'Taylor', 9998887777, N'robert.t@example.com', N'202 Fiction St, Fantasy Town', CAST(N'2019-03-01' AS Date), CAST(N'2024-02-01T16:30:00.000' AS DateTime), CAST(N'2024-02-01T16:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[member] OFF
GO
SET IDENTITY_INSERT [dbo].[publisher] ON 

INSERT [dbo].[publisher] ([id], [name], [address], [website], [edtablished_date], [contact_person], [created], [modified]) VALUES (1, N'Tech Publisher', N'123 Tech St, Tech City', N'www.techpublisher.com', CAST(N'2000-01-01' AS Date), 1112223333, CAST(N'2024-02-01T11:00:00.000' AS DateTime), CAST(N'2024-02-01T11:00:00.000' AS DateTime))
INSERT [dbo].[publisher] ([id], [name], [address], [website], [edtablished_date], [contact_person], [created], [modified]) VALUES (2, N'Book House', N'456 Book St, Literature Town', N'www.bookhouse.com', CAST(N'1985-05-05' AS Date), 4445556666, CAST(N'2024-02-01T11:15:00.000' AS DateTime), CAST(N'2024-02-01T11:15:00.000' AS DateTime))
INSERT [dbo].[publisher] ([id], [name], [address], [website], [edtablished_date], [contact_person], [created], [modified]) VALUES (3, N'Science Publishers', N'789 Science St, Research Village', N'www.sciencepubs.com', CAST(N'1990-10-10' AS Date), 7778889999, CAST(N'2024-02-01T11:30:00.000' AS DateTime), CAST(N'2024-02-01T11:30:00.000' AS DateTime))
INSERT [dbo].[publisher] ([id], [name], [address], [website], [edtablished_date], [contact_person], [created], [modified]) VALUES (4, N'History Books Ltd.', N'101 History St, Past City', N'www.historybooks.com', CAST(N'1980-12-12' AS Date), 2223334444, CAST(N'2024-02-01T12:00:00.000' AS DateTime), CAST(N'2024-02-01T12:00:00.000' AS DateTime))
INSERT [dbo].[publisher] ([id], [name], [address], [website], [edtablished_date], [contact_person], [created], [modified]) VALUES (5, N'Fiction Printers', N'202 Fiction St, Imaginary Town', N'www.fictionprinters.com', NULL, 5556667777, CAST(N'2024-02-01T12:30:00.000' AS DateTime), CAST(N'2024-02-01T12:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[publisher] OFF
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_Book_Author] FOREIGN KEY([author_id])
REFERENCES [dbo].[author] ([id])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_Book_Author]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_Book_Publisher] FOREIGN KEY([publisher_id])
REFERENCES [dbo].[publisher] ([id])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_Book_Publisher]
GO
ALTER TABLE [dbo].[loan]  WITH CHECK ADD  CONSTRAINT [FK_Loan_Book] FOREIGN KEY([book_id])
REFERENCES [dbo].[book] ([id])
GO
ALTER TABLE [dbo].[loan] CHECK CONSTRAINT [FK_Loan_Book]
GO
ALTER TABLE [dbo].[loan]  WITH CHECK ADD  CONSTRAINT [FK_Loan_Member] FOREIGN KEY([member_id])
REFERENCES [dbo].[member] ([id])
GO
ALTER TABLE [dbo].[loan] CHECK CONSTRAINT [FK_Loan_Member]
GO
USE [master]
GO
ALTER DATABASE [LibraryManagement] SET  READ_WRITE 
GO
