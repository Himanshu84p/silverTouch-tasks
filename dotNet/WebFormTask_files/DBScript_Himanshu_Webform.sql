USE [master]
GO
/****** Object:  Database [ServiceData]    Script Date: 08-02-2024 19:24:56 ******/
CREATE DATABASE [ServiceData]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ServiceData', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ServiceData.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ServiceData_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\ServiceData_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ServiceData] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ServiceData].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ServiceData] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ServiceData] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ServiceData] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ServiceData] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ServiceData] SET ARITHABORT OFF 
GO
ALTER DATABASE [ServiceData] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [ServiceData] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ServiceData] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ServiceData] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ServiceData] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ServiceData] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ServiceData] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ServiceData] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ServiceData] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ServiceData] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ServiceData] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ServiceData] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ServiceData] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ServiceData] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ServiceData] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ServiceData] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ServiceData] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ServiceData] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ServiceData] SET  MULTI_USER 
GO
ALTER DATABASE [ServiceData] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ServiceData] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ServiceData] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ServiceData] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ServiceData] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ServiceData] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [ServiceData] SET QUERY_STORE = OFF
GO
USE [ServiceData]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 08-02-2024 19:24:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerID] [int] NOT NULL,
	[CustomerName] [varchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Phone] [varchar](20) NULL,
	[City] [varchar](50) NULL,
 CONSTRAINT [PK__Customer__A4AE64B89B6536F2] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Department]    Script Date: 08-02-2024 19:24:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Department](
	[DepartmentID] [int] NOT NULL,
	[DepartmentName] [varchar](100) NULL,
	[Location] [varchar](100) NULL,
	[ManagerID] [int] NULL,
	[Budget] [decimal](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[DepartmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 08-02-2024 19:24:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[InvoiceID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[AmountPaid] [decimal](18, 2) NULL,
	[PaymentDate] [date] NULL,
	[PaymentMethod] [varchar](20) NULL,
 CONSTRAINT [PK__Invoice__D796AAD51E97F0DD] PRIMARY KEY CLUSTERED 
(
	[InvoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 08-02-2024 19:24:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NULL,
	[ServiceID] [int] NULL,
	[OrderDate] [date] NULL,
	[TotalAmount] [decimal](18, 2) NULL,
	[Pending] [varchar](5) NULL,
 CONSTRAINT [PK__Order__C3905BAFD4155D73] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Service]    Script Date: 08-02-2024 19:24:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Service](
	[ServiceID] [int] IDENTITY(1,1) NOT NULL,
	[ServiceName] [varchar](100) NULL,
	[DepartmentID] [int] NULL,
	[Description] [text] NULL,
	[Price] [decimal](18, 2) NULL,
	[Date] [datetime] NULL,
	[Available] [varchar](5) NULL,
 CONSTRAINT [PK__Service__C51BB0EA9C7BA545] PRIMARY KEY CLUSTERED 
(
	[ServiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Email], [Phone], [City]) VALUES (202, N'XYZ Ltd', N'xyz@example.com', N'987-654-3210', N'Delhi')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Email], [Phone], [City]) VALUES (203, N'Acme Inc', N'acme@example.com', N'8745964580', N'Surat')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Email], [Phone], [City]) VALUES (204, N'GHI Enterprises', N'ghi@example.com', N'444-555-6666', N'Mehsana')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Email], [Phone], [City]) VALUES (205, N'PQR Company', N'pqr@example.com', N'777-888-9999', N'Vadodara')
INSERT [dbo].[Customer] ([CustomerID], [CustomerName], [Email], [Phone], [City]) VALUES (206, N'Kevin Patel', N'kevin@gmail.com', N'9845421121', N'Mumbai')
GO
INSERT [dbo].[Department] ([DepartmentID], [DepartmentName], [Location], [ManagerID], [Budget]) VALUES (2, N'IT', N'New Yor', 101, CAST(200.00 AS Decimal(18, 2)))
INSERT [dbo].[Department] ([DepartmentID], [DepartmentName], [Location], [ManagerID], [Budget]) VALUES (3, N'Finance', N'Chicago', 105, CAST(120000.00 AS Decimal(18, 2)))
INSERT [dbo].[Department] ([DepartmentID], [DepartmentName], [Location], [ManagerID], [Budget]) VALUES (4, N'HR', N'Houston', 106, CAST(90120.00 AS Decimal(18, 2)))
INSERT [dbo].[Department] ([DepartmentID], [DepartmentName], [Location], [ManagerID], [Budget]) VALUES (5, N'Operations', N'San Francisco', 103, CAST(110000.00 AS Decimal(18, 2)))
INSERT [dbo].[Department] ([DepartmentID], [DepartmentName], [Location], [ManagerID], [Budget]) VALUES (9, N'Tester', N'New York', 103, CAST(10031.00 AS Decimal(18, 2)))
INSERT [dbo].[Department] ([DepartmentID], [DepartmentName], [Location], [ManagerID], [Budget]) VALUES (10, N'Devops', N'Ahmedabad', 106, CAST(10000.00 AS Decimal(18, 2)))
GO
SET IDENTITY_INSERT [dbo].[Invoice] ON 

INSERT [dbo].[Invoice] ([InvoiceID], [OrderID], [AmountPaid], [PaymentDate], [PaymentMethod]) VALUES (401, 301, CAST(5000.00 AS Decimal(18, 2)), CAST(N'2024-01-02' AS Date), N'Credit Card')
INSERT [dbo].[Invoice] ([InvoiceID], [OrderID], [AmountPaid], [PaymentDate], [PaymentMethod]) VALUES (402, 302, CAST(8000.00 AS Decimal(18, 2)), CAST(N'2024-01-25' AS Date), N'Bank Transfer')
INSERT [dbo].[Invoice] ([InvoiceID], [OrderID], [AmountPaid], [PaymentDate], [PaymentMethod]) VALUES (403, 303, CAST(10000.00 AS Decimal(18, 2)), CAST(N'2024-02-10' AS Date), N'Cheque')
INSERT [dbo].[Invoice] ([InvoiceID], [OrderID], [AmountPaid], [PaymentDate], [PaymentMethod]) VALUES (404, 304, CAST(6000.00 AS Decimal(18, 2)), CAST(N'2024-02-15' AS Date), N'Cash')
INSERT [dbo].[Invoice] ([InvoiceID], [OrderID], [AmountPaid], [PaymentDate], [PaymentMethod]) VALUES (413, 314, CAST(1200.00 AS Decimal(18, 2)), CAST(N'2024-01-10' AS Date), N'Cheque')
INSERT [dbo].[Invoice] ([InvoiceID], [OrderID], [AmountPaid], [PaymentDate], [PaymentMethod]) VALUES (415, 305, CAST(45000.00 AS Decimal(18, 2)), CAST(N'2024-02-13' AS Date), N'Cheque')
SET IDENTITY_INSERT [dbo].[Invoice] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderID], [CustomerID], [ServiceID], [OrderDate], [TotalAmount], [Pending]) VALUES (303, 203, 3, CAST(N'2024-02-05' AS Date), CAST(10000.00 AS Decimal(18, 2)), N'NO')
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [ServiceID], [OrderDate], [TotalAmount], [Pending]) VALUES (304, 204, 4, CAST(N'2024-02-10' AS Date), CAST(6000.00 AS Decimal(18, 2)), N'YES')
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [ServiceID], [OrderDate], [TotalAmount], [Pending]) VALUES (305, 205, 5, CAST(N'2024-02-15' AS Date), CAST(7000.00 AS Decimal(18, 2)), N'NO')
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [ServiceID], [OrderDate], [TotalAmount], [Pending]) VALUES (315, 206, 4, CAST(N'2024-01-09' AS Date), CAST(4500.00 AS Decimal(18, 2)), N'NO')
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [ServiceID], [OrderDate], [TotalAmount], [Pending]) VALUES (316, 210, 6, CAST(N'2024-01-16' AS Date), CAST(45000.00 AS Decimal(18, 2)), N'NO')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Service] ON 

INSERT [dbo].[Service] ([ServiceID], [ServiceName], [DepartmentID], [Description], [Price], [Date], [Available]) VALUES (2, N'Marketing Campaign', 2, N'Social media and digital marketing campaign', CAST(8000.00 AS Decimal(18, 2)), CAST(N'2024-02-06T00:00:00.000' AS DateTime), N'no')
INSERT [dbo].[Service] ([ServiceID], [ServiceName], [DepartmentID], [Description], [Price], [Date], [Available]) VALUES (3, N'Financial Consulting', 3, N'Financial planning and analysis services', CAST(10000.00 AS Decimal(18, 2)), CAST(N'2024-02-06T00:00:00.000' AS DateTime), N'yes')
INSERT [dbo].[Service] ([ServiceID], [ServiceName], [DepartmentID], [Description], [Price], [Date], [Available]) VALUES (4, N'Recruitment', 4, N'Human resources recruitment services', CAST(6000.00 AS Decimal(18, 2)), CAST(N'2024-02-06T00:00:00.000' AS DateTime), N'no')
INSERT [dbo].[Service] ([ServiceID], [ServiceName], [DepartmentID], [Description], [Price], [Date], [Available]) VALUES (5, N'Logistics Management', 5, N'Supply chain and logistics management', CAST(7000.00 AS Decimal(18, 2)), CAST(N'2024-02-06T00:00:00.000' AS DateTime), N'no')
INSERT [dbo].[Service] ([ServiceID], [ServiceName], [DepartmentID], [Description], [Price], [Date], [Available]) VALUES (8, N'Web Services ', 2, N'you will get best web pages ', CAST(800.00 AS Decimal(18, 2)), CAST(N'2024-02-05T00:00:00.000' AS DateTime), N'YES')
INSERT [dbo].[Service] ([ServiceID], [ServiceName], [DepartmentID], [Description], [Price], [Date], [Available]) VALUES (10, N'Web Services ', 2, N'you will get best web pages ', CAST(54500.00 AS Decimal(18, 2)), CAST(N'2024-02-11T00:00:00.000' AS DateTime), N'YES')
SET IDENTITY_INSERT [dbo].[Service] OFF
GO
USE [master]
GO
ALTER DATABASE [ServiceData] SET  READ_WRITE 
GO
