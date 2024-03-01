USE [master]
GO
/****** Object:  Database [OrderManagementAPI]    Script Date: 29-02-2024 20:29:33 ******/
CREATE DATABASE [OrderManagementAPI]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'OrderManagementAPI', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\OrderManagementAPI.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'OrderManagementAPI_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\OrderManagementAPI_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [OrderManagementAPI] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [OrderManagementAPI].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [OrderManagementAPI] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET ARITHABORT OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [OrderManagementAPI] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [OrderManagementAPI] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [OrderManagementAPI] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET  ENABLE_BROKER 
GO
ALTER DATABASE [OrderManagementAPI] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [OrderManagementAPI] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [OrderManagementAPI] SET  MULTI_USER 
GO
ALTER DATABASE [OrderManagementAPI] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [OrderManagementAPI] SET DB_CHAINING OFF 
GO
ALTER DATABASE [OrderManagementAPI] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [OrderManagementAPI] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [OrderManagementAPI] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [OrderManagementAPI] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [OrderManagementAPI] SET QUERY_STORE = OFF
GO
USE [OrderManagementAPI]
GO
/****** Object:  Table [dbo].[Customers]    Script Date: 29-02-2024 20:29:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[CustomerId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Address] [varchar](255) NULL,
	[IsDeleted] [bit] NULL,
	[Password] [varchar](100) NULL,
	[Role] [varchar](50) NULL,
 CONSTRAINT [PK__Customer__A4AE64D8F18446D1] PRIMARY KEY CLUSTERED 
(
	[CustomerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderItems]    Script Date: 29-02-2024 20:29:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderItems](
	[OrderItemId] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NULL,
	[ProductId] [int] NULL,
	[Quantity] [int] NULL,
	[UnitPrice] [decimal](10, 2) NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK__OrderIte__57ED068150C96D0C] PRIMARY KEY CLUSTERED 
(
	[OrderItemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 29-02-2024 20:29:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderId] [int] IDENTITY(1,1) NOT NULL,
	[CustomerId] [int] NULL,
	[OrderDate] [datetime] NULL,
	[TotalAmount] [decimal](10, 2) NULL,
	[Status] [varchar](50) NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK__Orders__C3905BCFA283200E] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 29-02-2024 20:29:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) NULL,
	[Description] [varchar](255) NULL,
	[Price] [decimal](10, 2) NULL,
	[StockQuantity] [int] NULL,
	[IsDeleted] [bit] NULL,
 CONSTRAINT [PK__Products__B40CC6CD7DE39BF5] PRIMARY KEY CLUSTERED 
(
	[ProductId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Customers] ON 

INSERT [dbo].[Customers] ([CustomerId], [Name], [Email], [Address], [IsDeleted], [Password], [Role]) VALUES (1, N'John Doe', N'john@example.com', N'123 Main St Mumbai', 0, N'hash', N'Customer')
INSERT [dbo].[Customers] ([CustomerId], [Name], [Email], [Address], [IsDeleted], [Password], [Role]) VALUES (2, N'Jane Smith', N'jane@example.com', N'456 Elm St', 0, N'hashed_password_2', N'Customer')
INSERT [dbo].[Customers] ([CustomerId], [Name], [Email], [Address], [IsDeleted], [Password], [Role]) VALUES (3, N'string', N'user@example.com', N'string', 1, N'string', N'string')
INSERT [dbo].[Customers] ([CustomerId], [Name], [Email], [Address], [IsDeleted], [Password], [Role]) VALUES (4, N'Himanshu', N'hp1234@gmail.com', N'Mehsana', 1, N'123', N'Customer')
INSERT [dbo].[Customers] ([CustomerId], [Name], [Email], [Address], [IsDeleted], [Password], [Role]) VALUES (5, N'string', N'user@example.com', N'string', 1, N'string', N'string')
INSERT [dbo].[Customers] ([CustomerId], [Name], [Email], [Address], [IsDeleted], [Password], [Role]) VALUES (6, N'Himanshu', N'hp1234@gmail.com', N'Mehsana ', 0, N'123', N'Customer')
SET IDENTITY_INSERT [dbo].[Customers] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderItems] ON 

INSERT [dbo].[OrderItems] ([OrderItemId], [OrderId], [ProductId], [Quantity], [UnitPrice], [IsDeleted]) VALUES (1, 1, 1, 10, CAST(200.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[OrderItems] ([OrderItemId], [OrderId], [ProductId], [Quantity], [UnitPrice], [IsDeleted]) VALUES (2, 3, 1, 1, CAST(48000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[OrderItems] ([OrderItemId], [OrderId], [ProductId], [Quantity], [UnitPrice], [IsDeleted]) VALUES (3, 3, 3, 2, CAST(2200.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[OrderItems] ([OrderItemId], [OrderId], [ProductId], [Quantity], [UnitPrice], [IsDeleted]) VALUES (4, 3, 1, 1, CAST(48000.00 AS Decimal(10, 2)), 0)
SET IDENTITY_INSERT [dbo].[OrderItems] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderId], [CustomerId], [OrderDate], [TotalAmount], [Status], [IsDeleted]) VALUES (1, 1, CAST(N'2024-02-28T14:57:29.127' AS DateTime), CAST(1500.00 AS Decimal(10, 2)), N'Delivered', 1)
INSERT [dbo].[Orders] ([OrderId], [CustomerId], [OrderDate], [TotalAmount], [Status], [IsDeleted]) VALUES (2, 1, CAST(N'2024-02-29T16:16:23.597' AS DateTime), CAST(0.00 AS Decimal(10, 2)), N'Pending', 1)
INSERT [dbo].[Orders] ([OrderId], [CustomerId], [OrderDate], [TotalAmount], [Status], [IsDeleted]) VALUES (3, 1, CAST(N'2024-02-29T16:51:59.023' AS DateTime), CAST(0.00 AS Decimal(10, 2)), N'Pending', 0)
INSERT [dbo].[Orders] ([OrderId], [CustomerId], [OrderDate], [TotalAmount], [Status], [IsDeleted]) VALUES (4, 1, CAST(N'2024-02-29T17:10:16.233' AS DateTime), CAST(0.00 AS Decimal(10, 2)), N'Pending', 0)
INSERT [dbo].[Orders] ([OrderId], [CustomerId], [OrderDate], [TotalAmount], [Status], [IsDeleted]) VALUES (5, 1, CAST(N'2024-02-29T17:20:07.623' AS DateTime), CAST(0.00 AS Decimal(10, 2)), N'Pending', 1)
INSERT [dbo].[Orders] ([OrderId], [CustomerId], [OrderDate], [TotalAmount], [Status], [IsDeleted]) VALUES (6, 6, CAST(N'2024-02-29T18:29:31.860' AS DateTime), CAST(0.00 AS Decimal(10, 2)), N'Pending', 0)
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([ProductId], [Name], [Description], [Price], [StockQuantity], [IsDeleted]) VALUES (1, N'Tv', N'Sony', CAST(48000.00 AS Decimal(10, 2)), 14, 0)
INSERT [dbo].[Products] ([ProductId], [Name], [Description], [Price], [StockQuantity], [IsDeleted]) VALUES (2, N'Oneplus Buds 2', N'earphone', CAST(2200.00 AS Decimal(10, 2)), 10, 1)
INSERT [dbo].[Products] ([ProductId], [Name], [Description], [Price], [StockQuantity], [IsDeleted]) VALUES (3, N'Oneplus Buds 2', N'earphone', CAST(2200.00 AS Decimal(10, 2)), 10, 0)
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
ALTER TABLE [dbo].[Customers] ADD  CONSTRAINT [DF__Customers__IsDel__36B12243]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[OrderItems] ADD  CONSTRAINT [DF__OrderItem__IsDel__440B1D61]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[Orders] ADD  CONSTRAINT [DF__Orders__IsDelete__403A8C7D]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF__Products__IsDele__398D8EEE]  DEFAULT ((0)) FOR [IsDeleted]
GO
ALTER TABLE [dbo].[OrderItems]  WITH CHECK ADD  CONSTRAINT [FK__OrderItem__Order__44FF419A] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Orders] ([OrderId])
GO
ALTER TABLE [dbo].[OrderItems] CHECK CONSTRAINT [FK__OrderItem__Order__44FF419A]
GO
ALTER TABLE [dbo].[OrderItems]  WITH CHECK ADD  CONSTRAINT [FK__OrderItem__Produ__45F365D3] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Products] ([ProductId])
GO
ALTER TABLE [dbo].[OrderItems] CHECK CONSTRAINT [FK__OrderItem__Produ__45F365D3]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__Customer__412EB0B6] FOREIGN KEY([CustomerId])
REFERENCES [dbo].[Customers] ([CustomerId])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__Customer__412EB0B6]
GO
USE [master]
GO
ALTER DATABASE [OrderManagementAPI] SET  READ_WRITE 
GO
