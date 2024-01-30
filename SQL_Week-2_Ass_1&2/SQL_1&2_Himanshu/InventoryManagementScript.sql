USE [master]
GO
/****** Object:  Database [InventoryManagement]    Script Date: 22-01-2024 14:30:17 ******/
CREATE DATABASE [InventoryManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'InventoryManagement_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\InventoryManagement.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'InventoryManagement_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\InventoryManagement.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [InventoryManagement] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [InventoryManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [InventoryManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [InventoryManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [InventoryManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [InventoryManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [InventoryManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [InventoryManagement] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [InventoryManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [InventoryManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [InventoryManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [InventoryManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [InventoryManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [InventoryManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [InventoryManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [InventoryManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [InventoryManagement] SET  DISABLE_BROKER 
GO
ALTER DATABASE [InventoryManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [InventoryManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [InventoryManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [InventoryManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [InventoryManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [InventoryManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [InventoryManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [InventoryManagement] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [InventoryManagement] SET  MULTI_USER 
GO
ALTER DATABASE [InventoryManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [InventoryManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [InventoryManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [InventoryManagement] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [InventoryManagement] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [InventoryManagement] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [InventoryManagement] SET QUERY_STORE = OFF
GO
USE [InventoryManagement]
GO
/****** Object:  Table [dbo].[category]    Script Date: 22-01-2024 14:30:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [text] NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 22-01-2024 14:30:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[email] [nvarchar](320) NOT NULL,
	[mobile] [bigint] NOT NULL,
	[address] [ntext] NOT NULL,
	[dob] [date] NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_customer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 22-01-2024 14:30:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[customer_id] [int] NOT NULL,
	[supplier_id] [int] NOT NULL,
	[date] [datetime] NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
	[total_amount] [real] NOT NULL,
	[unit_amount] [real] NOT NULL,
	[payment_type] [nvarchar](50) NOT NULL,
	[payment_date] [datetime] NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 22-01-2024 14:30:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[categories_id] [int] NOT NULL,
	[buying_price] [real] NOT NULL,
	[selling_price] [real] NOT NULL,
	[stock] [int] NOT NULL,
	[description] [ntext] NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[supplier]    Script Date: 22-01-2024 14:30:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[supplier](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[email] [nvarchar](320) NOT NULL,
	[mobile] [bigint] NOT NULL,
	[address] [ntext] NOT NULL,
	[shop_name] [nvarchar](50) NOT NULL,
	[dob] [date] NOT NULL,
	[created] [datetime] NOT NULL,
	[modified] [datetime] NOT NULL,
 CONSTRAINT [PK_supplier] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [name], [description], [created], [modified]) VALUES (1, N'Electronics', N'Electronic gadgets and devices', CAST(N'2024-01-21T09:00:00.000' AS DateTime), CAST(N'2024-01-21T09:00:00.000' AS DateTime))
INSERT [dbo].[category] ([id], [name], [description], [created], [modified]) VALUES (2, N'Clothing', N'Various clothing items', CAST(N'2024-01-21T09:15:00.000' AS DateTime), CAST(N'2024-01-21T09:15:00.000' AS DateTime))
INSERT [dbo].[category] ([id], [name], [description], [created], [modified]) VALUES (3, N'Books', N'Books of various genres', CAST(N'2024-01-21T09:30:00.000' AS DateTime), CAST(N'2024-01-21T09:30:00.000' AS DateTime))
INSERT [dbo].[category] ([id], [name], [description], [created], [modified]) VALUES (4, N'Home Appliances', N'Appliances for home use', CAST(N'2024-01-21T10:00:00.000' AS DateTime), CAST(N'2024-01-21T10:00:00.000' AS DateTime))
INSERT [dbo].[category] ([id], [name], [description], [created], [modified]) VALUES (5, N'Toys', N'Children toys and games', CAST(N'2024-01-21T10:30:00.000' AS DateTime), CAST(N'2024-01-21T10:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[category] OFF
GO
SET IDENTITY_INSERT [dbo].[customer] ON 

INSERT [dbo].[customer] ([id], [name], [email], [mobile], [address], [dob], [created], [modified]) VALUES (1, N'John Doe', N'john@example.com', 1234567890, N'123 Main St, City', CAST(N'1990-05-15' AS Date), CAST(N'2024-01-21T11:00:00.000' AS DateTime), CAST(N'2024-01-21T11:00:00.000' AS DateTime))
INSERT [dbo].[customer] ([id], [name], [email], [mobile], [address], [dob], [created], [modified]) VALUES (2, N'Alice Smith', N'alice@example.com', 9876543210, N'456 Oak St, Town', CAST(N'1985-08-22' AS Date), CAST(N'2024-01-21T11:30:00.000' AS DateTime), CAST(N'2024-01-21T11:30:00.000' AS DateTime))
INSERT [dbo].[customer] ([id], [name], [email], [mobile], [address], [dob], [created], [modified]) VALUES (3, N'Bob Johnson', N'bob@example.com', 5551112233, N'789 Maple St, Village', CAST(N'1995-03-10' AS Date), CAST(N'2024-01-21T12:00:00.000' AS DateTime), CAST(N'2024-01-21T12:00:00.000' AS DateTime))
INSERT [dbo].[customer] ([id], [name], [email], [mobile], [address], [dob], [created], [modified]) VALUES (4, N'Emma White', N'emma@example.com', 4442221111, N'101 Pine St, Hamlet', CAST(N'1988-12-05' AS Date), CAST(N'2024-01-21T12:30:00.000' AS DateTime), CAST(N'2024-01-21T12:30:00.000' AS DateTime))
INSERT [dbo].[customer] ([id], [name], [email], [mobile], [address], [dob], [created], [modified]) VALUES (5, N'David Brown', N'david@example.com', 9998887777, N'202 Cedar St, Suburb', CAST(N'1992-07-18' AS Date), CAST(N'2024-01-21T13:00:00.000' AS DateTime), CAST(N'2024-01-21T13:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[customer] OFF
GO
SET IDENTITY_INSERT [dbo].[orders] ON 

INSERT [dbo].[orders] ([id], [customer_id], [supplier_id], [date], [status], [quantity], [total_amount], [unit_amount], [payment_type], [payment_date], [created], [modified]) VALUES (2, 1, 1, CAST(N'2024-01-21T14:00:00.000' AS DateTime), N'Processing', 3, 150, 50, N'Credit Card', CAST(N'2024-01-21T14:30:00.000' AS DateTime), CAST(N'2024-01-21T14:00:00.000' AS DateTime), CAST(N'2024-01-21T14:00:00.000' AS DateTime))
INSERT [dbo].[orders] ([id], [customer_id], [supplier_id], [date], [status], [quantity], [total_amount], [unit_amount], [payment_type], [payment_date], [created], [modified]) VALUES (3, 2, 2, CAST(N'2024-01-21T14:15:00.000' AS DateTime), N'Shipped', 2, 120, 60, N'Cash', CAST(N'2024-01-21T14:45:00.000' AS DateTime), CAST(N'2024-01-21T14:15:00.000' AS DateTime), CAST(N'2024-01-21T14:15:00.000' AS DateTime))
INSERT [dbo].[orders] ([id], [customer_id], [supplier_id], [date], [status], [quantity], [total_amount], [unit_amount], [payment_type], [payment_date], [created], [modified]) VALUES (4, 3, 3, CAST(N'2024-01-21T14:30:00.000' AS DateTime), N'Delivered', 1, 30, 30, N'Credit Card', CAST(N'2024-01-21T15:00:00.000' AS DateTime), CAST(N'2024-01-21T14:30:00.000' AS DateTime), CAST(N'2024-01-21T14:30:00.000' AS DateTime))
INSERT [dbo].[orders] ([id], [customer_id], [supplier_id], [date], [status], [quantity], [total_amount], [unit_amount], [payment_type], [payment_date], [created], [modified]) VALUES (5, 4, 1, CAST(N'2024-01-21T14:45:00.000' AS DateTime), N'Processing', 4, 200, 50, N'Cash', CAST(N'2024-01-21T15:15:00.000' AS DateTime), CAST(N'2024-01-21T14:45:00.000' AS DateTime), CAST(N'2024-01-21T14:45:00.000' AS DateTime))
INSERT [dbo].[orders] ([id], [customer_id], [supplier_id], [date], [status], [quantity], [total_amount], [unit_amount], [payment_type], [payment_date], [created], [modified]) VALUES (6, 5, 2, CAST(N'2024-01-21T15:00:00.000' AS DateTime), N'Shipped', 2, 100, 50, N'Credit Card', CAST(N'2024-01-21T15:30:00.000' AS DateTime), CAST(N'2024-01-21T15:00:00.000' AS DateTime), CAST(N'2024-01-21T15:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[orders] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [name], [categories_id], [buying_price], [selling_price], [stock], [description], [created], [modified]) VALUES (1, N'Laptop', 1, 800, 1200, 20, N'High-performance laptop', CAST(N'2024-01-21T16:00:00.000' AS DateTime), CAST(N'2024-01-21T16:00:00.000' AS DateTime))
INSERT [dbo].[product] ([id], [name], [categories_id], [buying_price], [selling_price], [stock], [description], [created], [modified]) VALUES (2, N'T-shirt', 2, 10, 20, 100, N'Cotton t-shirt', CAST(N'2024-01-21T16:30:00.000' AS DateTime), CAST(N'2024-01-21T16:30:00.000' AS DateTime))
INSERT [dbo].[product] ([id], [name], [categories_id], [buying_price], [selling_price], [stock], [description], [created], [modified]) VALUES (3, N'Sci-Fi Book', 3, 15, 25, 50, N'Best-selling science fiction book', CAST(N'2024-01-21T17:00:00.000' AS DateTime), CAST(N'2024-01-21T17:00:00.000' AS DateTime))
INSERT [dbo].[product] ([id], [name], [categories_id], [buying_price], [selling_price], [stock], [description], [created], [modified]) VALUES (4, N'Refrigerator', 4, 500, 800, 10, N'Large capacity refrigerator', CAST(N'2024-01-21T17:30:00.000' AS DateTime), CAST(N'2024-01-21T17:30:00.000' AS DateTime))
INSERT [dbo].[product] ([id], [name], [categories_id], [buying_price], [selling_price], [stock], [description], [created], [modified]) VALUES (5, N'Action Figure', 5, 5, 10, 200, N'Collectible action figure', CAST(N'2024-01-21T18:00:00.000' AS DateTime), CAST(N'2024-01-21T18:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[supplier] ON 

INSERT [dbo].[supplier] ([id], [name], [email], [mobile], [address], [shop_name], [dob], [created], [modified]) VALUES (1, N'Home Appliance Supplier', N'homeappliance@example.com', 3334445555, N'202 Home Appliance St, Appliance City', N'Appliance Haven', CAST(N'1982-02-02' AS Date), CAST(N'2024-01-21T20:30:00.000' AS DateTime), CAST(N'2024-01-21T20:30:00.000' AS DateTime))
INSERT [dbo].[supplier] ([id], [name], [email], [mobile], [address], [shop_name], [dob], [created], [modified]) VALUES (2, N'Toy Supplier', N'toys@example.com', 6667778888, N'303 Toy St, Play Town', N'Toy Paradise', CAST(N'1998-08-08' AS Date), CAST(N'2024-01-21T21:00:00.000' AS DateTime), CAST(N'2024-01-21T21:00:00.000' AS DateTime))
INSERT [dbo].[supplier] ([id], [name], [email], [mobile], [address], [shop_name], [dob], [created], [modified]) VALUES (3, N'Tech Gadgets Supplier', N'techgadgets@example.com', 9990001111, N'404 Tech Gadgets St, Gadget Village', N'Gadget Haven', CAST(N'1970-12-12' AS Date), CAST(N'2024-01-21T21:30:00.000' AS DateTime), CAST(N'2024-01-21T21:30:00.000' AS DateTime))
INSERT [dbo].[supplier] ([id], [name], [email], [mobile], [address], [shop_name], [dob], [created], [modified]) VALUES (4, N'Furniture Supplier', N'furniture@example.com', 1112233444, N'505 Furniture St, Comfort City', N'Furniture Paradise', CAST(N'1985-05-25' AS Date), CAST(N'2024-01-21T22:00:00.000' AS DateTime), CAST(N'2024-01-21T22:00:00.000' AS DateTime))
INSERT [dbo].[supplier] ([id], [name], [email], [mobile], [address], [shop_name], [dob], [created], [modified]) VALUES (5, N'Jewelry Supplier', N'jewelry@example.com', 1234567890, N'606 Jewelry St, Sparkle Town', N'Jewelry Haven', CAST(N'1995-10-15' AS Date), CAST(N'2024-01-21T22:30:00.000' AS DateTime), CAST(N'2024-01-21T22:30:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[supplier] OFF
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_customer] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_customer]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_supplier] FOREIGN KEY([supplier_id])
REFERENCES [dbo].[supplier] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_supplier]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([categories_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK_Product_Category]
GO
USE [master]
GO
ALTER DATABASE [InventoryManagement] SET  READ_WRITE 
GO
