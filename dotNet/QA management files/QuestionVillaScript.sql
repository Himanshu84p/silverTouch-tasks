USE [master]
GO
/****** Object:  Database [QAManagement]    Script Date: 20-02-2024 19:34:40 ******/
CREATE DATABASE [QAManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QAManagement', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\QAManagement.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QAManagement_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\QAManagement_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QAManagement] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QAManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QAManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QAManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QAManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QAManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QAManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [QAManagement] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QAManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QAManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QAManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QAManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QAManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QAManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QAManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QAManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QAManagement] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QAManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QAManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QAManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QAManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QAManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QAManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QAManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QAManagement] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QAManagement] SET  MULTI_USER 
GO
ALTER DATABASE [QAManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QAManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QAManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QAManagement] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QAManagement] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QAManagement] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QAManagement] SET QUERY_STORE = OFF
GO
USE [QAManagement]
GO
/****** Object:  Table [dbo].[Answers]    Script Date: 20-02-2024 19:34:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answers](
	[AnswerID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[QuestionID] [int] NOT NULL,
	[SubmittedAnswer] [nvarchar](max) NOT NULL,
	[SubmissionTimestamp] [datetime2](7) NOT NULL,
	[QuestionPaperID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[AnswerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Approvals]    Script Date: 20-02-2024 19:34:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Approvals](
	[ApprovalID] [int] IDENTITY(1,1) NOT NULL,
	[QuestionPaperID] [int] NOT NULL,
	[ApproverID] [int] NOT NULL,
	[ApprovalStatus] [nvarchar](50) NOT NULL,
	[ApprovalTimestamp] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ApprovalID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuestionPapers]    Script Date: 20-02-2024 19:34:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuestionPapers](
	[QuestionPaperID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](100) NOT NULL,
	[Description] [nvarchar](max) NULL,
	[CreationDate] [datetime2](7) NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
	[CreatorID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[QuestionPaperID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Questions]    Script Date: 20-02-2024 19:34:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Questions](
	[QuestionID] [int] IDENTITY(1,1) NOT NULL,
	[QuestionText] [nvarchar](max) NOT NULL,
	[Option1] [nvarchar](max) NOT NULL,
	[Option2] [nvarchar](max) NOT NULL,
	[Option3] [nvarchar](max) NOT NULL,
	[Option4] [nvarchar](max) NOT NULL,
	[CorrectAnswer] [nvarchar](max) NOT NULL,
	[QuestionPaperID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[QuestionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 20-02-2024 19:34:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[PasswordHash] [nvarchar](200) NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[Role] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[QuestionPapers] ON 

INSERT [dbo].[QuestionPapers] ([QuestionPaperID], [Title], [Description], [CreationDate], [Status], [CreatorID]) VALUES (5, N'Question Paper 4', N'Question Paper 4', CAST(N'2024-02-19T11:53:28.6584800' AS DateTime2), N'Approved', 14)
INSERT [dbo].[QuestionPapers] ([QuestionPaperID], [Title], [Description], [CreationDate], [Status], [CreatorID]) VALUES (6, N'Question Paper 1e', N'Question Paper 1e', CAST(N'2024-02-20T14:40:29.2269720' AS DateTime2), N'Approved', 14)
INSERT [dbo].[QuestionPapers] ([QuestionPaperID], [Title], [Description], [CreationDate], [Status], [CreatorID]) VALUES (10, N'English', N'English Grammer', CAST(N'2024-02-19T19:25:10.8611344' AS DateTime2), N'Pending', 14)
SET IDENTITY_INSERT [dbo].[QuestionPapers] OFF
GO
SET IDENTITY_INSERT [dbo].[Questions] ON 

INSERT [dbo].[Questions] ([QuestionID], [QuestionText], [Option1], [Option2], [Option3], [Option4], [CorrectAnswer], [QuestionPaperID]) VALUES (10, N'new', N'n', N'n', N'n', N'n', N'n', 6)
SET IDENTITY_INSERT [dbo].[Questions] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Role]) VALUES (14, N'Admin1', N'OT0K/pW5dPELvvCB7JPGbw==', N'admin@gmail.com', N'Admin')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Role]) VALUES (1013, N'TestUser', N'nxgQWhH6vyJ+qM0gFS/h3A==', N'Test@gmail.com', N'Student')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Role]) VALUES (1014, N'Teacher', N'qb45O6AcD64TxsI+/M5pAQ==', N'teacher1@gmail.com', N'Teacher')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__A9D10534663A8A77]    Script Date: 20-02-2024 19:34:40 ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Answers] ADD  DEFAULT (getdate()) FOR [SubmissionTimestamp]
GO
ALTER TABLE [dbo].[Approvals] ADD  DEFAULT (getdate()) FOR [ApprovalTimestamp]
GO
ALTER TABLE [dbo].[QuestionPapers] ADD  DEFAULT (getdate()) FOR [CreationDate]
GO
ALTER TABLE [dbo].[QuestionPapers] ADD  DEFAULT ('Draft') FOR [Status]
GO
ALTER TABLE [dbo].[Answers]  WITH CHECK ADD FOREIGN KEY([QuestionID])
REFERENCES [dbo].[Questions] ([QuestionID])
GO
ALTER TABLE [dbo].[Answers]  WITH CHECK ADD FOREIGN KEY([QuestionPaperID])
REFERENCES [dbo].[QuestionPapers] ([QuestionPaperID])
GO
ALTER TABLE [dbo].[Answers]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Approvals]  WITH CHECK ADD FOREIGN KEY([ApproverID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Approvals]  WITH CHECK ADD FOREIGN KEY([QuestionPaperID])
REFERENCES [dbo].[QuestionPapers] ([QuestionPaperID])
GO
ALTER TABLE [dbo].[QuestionPapers]  WITH CHECK ADD FOREIGN KEY([CreatorID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[Questions]  WITH CHECK ADD FOREIGN KEY([QuestionPaperID])
REFERENCES [dbo].[QuestionPapers] ([QuestionPaperID])
GO
USE [master]
GO
ALTER DATABASE [QAManagement] SET  READ_WRITE 
GO
