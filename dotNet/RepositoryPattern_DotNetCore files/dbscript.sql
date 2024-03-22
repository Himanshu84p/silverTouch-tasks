USE [master]
GO
/****** Object:  Database [CoreScaffoldDB]    Script Date: 15-03-2024 15:39:11 ******/
CREATE DATABASE [CoreScaffoldDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CoreScaffoldDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\CoreScaffoldDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CoreScaffoldDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\CoreScaffoldDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [CoreScaffoldDB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CoreScaffoldDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CoreScaffoldDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CoreScaffoldDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CoreScaffoldDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CoreScaffoldDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CoreScaffoldDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CoreScaffoldDB] SET  MULTI_USER 
GO
ALTER DATABASE [CoreScaffoldDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CoreScaffoldDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CoreScaffoldDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CoreScaffoldDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CoreScaffoldDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CoreScaffoldDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [CoreScaffoldDB] SET QUERY_STORE = OFF
GO
USE [CoreScaffoldDB]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 15-03-2024 15:39:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[firstName] [varchar](50) NULL,
	[lastName] [varchar](50) NULL,
	[jobTitle] [varchar](100) NULL,
	[department] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[log]    Script Date: 15-03-2024 15:39:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[log](
	[logId] [int] IDENTITY(1,1) NOT NULL,
	[MachineName] [nvarchar](255) NULL,
	[Logged] [datetime] NULL,
	[Level] [nvarchar](50) NULL,
	[Message] [nvarchar](max) NULL,
	[Logger] [nvarchar](255) NULL,
	[Callsite] [nvarchar](255) NULL,
	[Exception] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[logId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 15-03-2024 15:39:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Email] [nvarchar](100) NULL,
	[Age] [int] NULL,
	[Address] [nvarchar](255) NULL,
	[Phone] [nvarchar](15) NULL,
	[Gender] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (3, N'Michael', N'Johnson', N'Sales Representative', N'Sales')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (4, N'Emily', N'Williams', N'HR Specialist', N'Human Resources')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (5, N'Chris', N'Brown', N'Financial Analyst', N'Finance')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (6, N'Himanshu', N'Prajapati', N'Software Engineer ', N'Information Technology')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (9, N'M6BURSoTV', N'BzjBDjWrB', N'VheynrXtB', N'qqlxrtRZj')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (11, N'Salvador', N'Cline', N'Velit commodi laboriosam eos quo ea', N'Assumennon ')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (12, N'Joel', N'Berry', N'modo enim duis enim', N'tate quis cumque nulla')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (13, N'Magee', N'Wilcox', N'lores dolorum ipsa in ex', N'm in eos')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (14, N'Reece', N'Blevins', N' iusto do mollit deleniti est', N'm')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (15, N'Reece', N'Blevins', N' iusto do mollit deleniti est', N'mfdfgdsg')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (16, N'Lev', N'Castillo', N'nobis aut non ratione facere sint itaque ullamco', N'Aita rerum')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (17, N'Daryl', N'Hinton', N'Haruue atqu exrem ut do ', N'Aut aliquip in praesentium alias')
INSERT [dbo].[Employee] ([id], [firstName], [lastName], [jobTitle], [department]) VALUES (18, N'Neil', N'Whitney', N'rovident et sed veniam de voluptas sapiente se', N'ati cum ut ac minus soluta')
SET IDENTITY_INSERT [dbo].[Employee] OFF
GO
SET IDENTITY_INSERT [dbo].[log] ON 

INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (1, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:58.767' AS DateTime), N'Debug', N'Registered model binder providers, in the following order: Microsoft.AspNetCore.Mvc.ModelBinding.Binders.BinderTypeModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.ServicesModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.BodyModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.HeaderModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.FloatingPointTypeModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.EnumTypeModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.DateTimeModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.SimpleTypeModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.TryParseModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.CancellationTokenModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.ByteArrayModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.FormFileModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.FormCollectionModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.KeyValuePairModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.DictionaryModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.ArrayModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.CollectionModelBinderProvider, Microsoft.AspNetCore.Mvc.ModelBinding.Binders.ComplexObjectModelBinderProvider', N'Microsoft.AspNetCore.Mvc.ModelBinding.ModelBinderFactory', N'Microsoft.Extensions.DependencyInjection.ServiceLookup.CallSiteRuntimeResolver.VisitConstructor', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (2, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.190' AS DateTime), N'Debug', N'Hosting starting', N'Microsoft.Extensions.Hosting.Internal.Host', N'Microsoft.Extensions.Hosting.Internal.HostingLoggerExtensions.Starting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (3, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.407' AS DateTime), N'Info', N'Now listening on: http://localhost:5273', N'Microsoft.Hosting.Lifetime', N'Microsoft.AspNetCore.Hosting.GenericWebHostService.StartAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (4, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.407' AS DateTime), N'Debug', N'Loaded hosting startup assembly CoreThreeTier', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.GenericWebHostService.StartAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (5, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.447' AS DateTime), N'Debug', N'Loaded hosting startup assembly Microsoft.AspNetCore.Watch.BrowserRefresh', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.GenericWebHostService.StartAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (6, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.447' AS DateTime), N'Debug', N'Loaded hosting startup assembly Microsoft.WebTools.BrowserLink.Net', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.GenericWebHostService.StartAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (7, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.453' AS DateTime), N'Info', N'Application started. Press Ctrl+C to shut down.', N'Microsoft.Hosting.Lifetime', N'Microsoft.Extensions.Hosting.Internal.ConsoleLifetime.OnApplicationStarted', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (8, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.453' AS DateTime), N'Info', N'Hosting environment: Development', N'Microsoft.Hosting.Lifetime', N'Microsoft.Extensions.Hosting.Internal.ConsoleLifetime.OnApplicationStarted', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (9, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.453' AS DateTime), N'Info', N'Content root path: D:\All_Silvertouch_Tasks\dotNet\CoreThreeTierWebApi\CoreThreeTier', N'Microsoft.Hosting.Lifetime', N'Microsoft.Extensions.Hosting.Internal.ApplicationLifetime.NotifyStarted', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (10, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.453' AS DateTime), N'Debug', N'Hosting started', N'Microsoft.Extensions.Hosting.Internal.Host', N'Microsoft.Extensions.Hosting.Internal.HostingLoggerExtensions.Started', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (11, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.513' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T2" received FIN.', N'Microsoft.AspNetCore.Server.Kestrel.Transport.Sockets', N'Microsoft.AspNetCore.Server.Kestrel.Transport.Sockets.Internal.SocketConnection.DoReceive', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (12, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.513' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T2" accepted.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.ConnectionDispatcher`1.StartAcceptingConnectionsCore', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (13, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.513' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T2" started.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Infrastructure.KestrelConnection`1.ExecuteAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (14, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.543' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T2" sending FIN because: "The Socket transport''s send loop completed gracefully."', N'Microsoft.AspNetCore.Server.Kestrel.Transport.Sockets', N'Microsoft.AspNetCore.Server.Kestrel.Transport.Sockets.Internal.SocketConnection.Shutdown', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (15, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.547' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T2" disconnecting.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Http.Http1OutputProducer.Abort', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (16, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.547' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T2" stopped.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Infrastructure.KestrelConnection`1.ExecuteAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (17, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.703' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T3" accepted.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.ConnectionDispatcher`1.StartAcceptingConnectionsCore', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (18, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.703' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T3" started.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Infrastructure.KestrelConnection`1.ExecuteAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (19, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.770' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T4" accepted.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.ConnectionDispatcher`1.StartAcceptingConnectionsCore', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (20, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.770' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T4" started.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Infrastructure.KestrelConnection`1.ExecuteAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (21, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.803' AS DateTime), N'Info', N'Request starting HTTP/1.1 GET http://localhost:5273/swagger/index.html - - -', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestStarting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (22, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.987' AS DateTime), N'Debug', N'Wildcard detected, all requests with hosts will be allowed.', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware.Configure', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (23, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:42:59.987' AS DateTime), N'Trace', N'All hosts are allowed.', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware.Invoke', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (24, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.017' AS DateTime), N'Debug', N'No candidates found for the request path ''/swagger/index.html''', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher.MatchAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (25, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.017' AS DateTime), N'Debug', N'Request did not match any endpoints', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware.SetRoutingAndContinue', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (26, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.110' AS DateTime), N'Debug', N'Response markup is scheduled to include browser refresh script injection.', N'Microsoft.AspNetCore.Watch.BrowserRefresh.BrowserRefreshMiddleware', N'Microsoft.AspNetCore.Watch.BrowserRefresh.BrowserRefreshMiddleware+Log.SetupResponseForBrowserRefresh', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (27, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.187' AS DateTime), N'Debug', N'Response markup was updated to include browser refresh script injection.', N'Microsoft.AspNetCore.Watch.BrowserRefresh.BrowserRefreshMiddleware', N'Microsoft.AspNetCore.Watch.BrowserRefresh.BrowserRefreshMiddleware+Log.BrowserConfiguredForRefreshes', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (28, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.187' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T3" completed keep alive response.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Http.HttpProtocol.WriteSuffix', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (29, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.187' AS DateTime), N'Info', N'Request starting HTTP/1.1 GET http://localhost:5273/_framework/aspnetcore-browser-refresh.js - - -', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestStarting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (30, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.207' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T5" accepted.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.ConnectionDispatcher`1.StartAcceptingConnectionsCore', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (31, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.217' AS DateTime), N'Info', N'Request finished HTTP/1.1 GET http://localhost:5273/swagger/index.html - 200 - text/html;charset=utf-8 412.3825ms', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestFinished', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (32, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.217' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T5" started.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Infrastructure.KestrelConnection`1.ExecuteAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (33, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.217' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T4" completed keep alive response.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Http.HttpProtocol.WriteSuffix', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (34, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.217' AS DateTime), N'Info', N'Request starting HTTP/1.1 GET http://localhost:5273/_vs/browserLink - - -', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestStarting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (35, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.217' AS DateTime), N'Info', N'Request finished HTTP/1.1 GET http://localhost:5273/_framework/aspnetcore-browser-refresh.js - 200 13762 application/javascript;+charset=utf-8 27.6810ms', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestFinished', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (36, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.267' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T5" completed keep alive response.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Http.HttpProtocol.WriteSuffix', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (37, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.267' AS DateTime), N'Info', N'Request finished HTTP/1.1 GET http://localhost:5273/_vs/browserLink - 200 - text/javascript;+charset=UTF-8 45.6812ms', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestFinished', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (38, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.377' AS DateTime), N'Info', N'Request starting HTTP/1.1 GET http://localhost:5273/swagger/v1/swagger.json - - -', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestStarting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (39, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.377' AS DateTime), N'Trace', N'All hosts are allowed.', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware.Invoke', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (40, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.377' AS DateTime), N'Debug', N'No candidates found for the request path ''/swagger/v1/swagger.json''', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher.MatchAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (41, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.377' AS DateTime), N'Debug', N'Request did not match any endpoints', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware.SetRoutingAndContinue', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (42, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.493' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T5" completed keep alive response.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Http.HttpProtocol.WriteSuffix', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (43, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:00.493' AS DateTime), N'Info', N'Request finished HTTP/1.1 GET http://localhost:5273/swagger/v1/swagger.json - 200 - application/json;charset=utf-8 122.2443ms', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestFinished', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (44, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.320' AS DateTime), N'Info', N'Request starting HTTP/1.1 GET http://localhost:5273/api/User/GetAllUsers - - -', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestStarting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (45, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.320' AS DateTime), N'Trace', N'All hosts are allowed.', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware', N'Microsoft.AspNetCore.HostFiltering.HostFilteringMiddleware.Invoke', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (46, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.337' AS DateTime), N'Debug', N'1 candidate(s) found for the request path ''/api/User/GetAllUsers''', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher.MatchAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (47, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.337' AS DateTime), N'Debug', N'Endpoint ''CoreThreeTier.Controllers.UserController.GetUsers (CoreThreeTier)'' with route pattern ''api/User/GetAllUsers'' is valid for the request path ''/api/User/GetAllUsers''', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher', N'Microsoft.AspNetCore.Routing.Matching.DfaMatcher.MatchAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (48, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.337' AS DateTime), N'Debug', N'Request matched endpoint ''CoreThreeTier.Controllers.UserController.GetUsers (CoreThreeTier)''', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware.SetRoutingAndContinue', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (49, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.337' AS DateTime), N'Trace', N'The endpoint does not specify the IRequestSizeLimitMetadata.', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware', N'Microsoft.AspNetCore.Routing.EndpointRoutingMiddleware.SetMaxRequestBodySize', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (50, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.350' AS DateTime), N'Debug', N'Static files was skipped as the request already matched an endpoint.', N'Microsoft.AspNetCore.StaticFiles.StaticFileMiddleware', N'Microsoft.AspNetCore.StaticFiles.StaticFileMiddleware.Invoke', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (51, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.350' AS DateTime), N'Warn', N'Failed to determine the https port for redirect.', N'Microsoft.AspNetCore.HttpsPolicy.HttpsRedirectionMiddleware', N'Microsoft.AspNetCore.HttpsPolicy.HttpsRedirectionMiddleware.TryGetHttpsPort', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (52, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.350' AS DateTime), N'Info', N'Executing endpoint ''CoreThreeTier.Controllers.UserController.GetUsers (CoreThreeTier)''', N'Microsoft.AspNetCore.Routing.EndpointMiddleware', N'Microsoft.AspNetCore.Routing.EndpointMiddleware.Invoke', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (53, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.377' AS DateTime), N'Info', N'Route matched with {action = "GetUsers", controller = "User"}. Executing controller action with signature System.Threading.Tasks.Task`1[Microsoft.AspNetCore.Mvc.IActionResult] GetUsers() on controller CoreThreeTier.Controllers.UserController (CoreThreeTier).', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker+Log.ExecutingAction', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (54, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.377' AS DateTime), N'Debug', N'Execution plan of authorization filters (in the following order): None', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.MvcCoreLoggerExtensions.LogFilterExecutionPlan', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (55, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.383' AS DateTime), N'Debug', N'Execution plan of resource filters (in the following order): None', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.MvcCoreLoggerExtensions.LogFilterExecutionPlan', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (56, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.383' AS DateTime), N'Debug', N'Execution plan of action filters (in the following order): Microsoft.AspNetCore.Mvc.ModelBinding.UnsupportedContentTypeFilter (Order: -3000), Microsoft.AspNetCore.Mvc.Infrastructure.ModelStateInvalidFilter (Order: -2000)', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.MvcCoreLoggerExtensions.LogFilterExecutionPlan', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (57, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.383' AS DateTime), N'Debug', N'Execution plan of exception filters (in the following order): None', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.MvcCoreLoggerExtensions.LogFilterExecutionPlan', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (58, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.383' AS DateTime), N'Debug', N'Execution plan of result filters (in the following order): Microsoft.AspNetCore.Mvc.Infrastructure.ClientErrorResultFilter (Order: -2000)', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.MvcCoreLoggerExtensions.LogFilterExecutionPlan', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (59, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.383' AS DateTime), N'Debug', N'Executing controller factory for controller CoreThreeTier.Controllers.UserController (CoreThreeTier)', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker+Log.ExecutingControllerFactory', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (60, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.527' AS DateTime), N'Debug', N'An ''IServiceProvider'' was created for internal use by Entity Framework.', N'Microsoft.EntityFrameworkCore.Infrastructure', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (61, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.550' AS DateTime), N'Debug', N'Executed controller factory for controller CoreThreeTier.Controllers.UserController (CoreThreeTier)', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker+Log.ExecutedControllerFactory', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (62, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.550' AS DateTime), N'Trace', N'Action Filter: Before executing OnActionExecuting on filter Microsoft.AspNetCore.Mvc.ModelBinding.UnsupportedContentTypeFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (63, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.550' AS DateTime), N'Trace', N'Action Filter: After executing OnActionExecuting on filter Microsoft.AspNetCore.Mvc.ModelBinding.UnsupportedContentTypeFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (64, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.550' AS DateTime), N'Trace', N'Action Filter: Before executing OnActionExecuting on filter Microsoft.AspNetCore.Mvc.Infrastructure.ModelStateInvalidFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (65, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.550' AS DateTime), N'Trace', N'Action Filter: After executing OnActionExecuting on filter Microsoft.AspNetCore.Mvc.Infrastructure.ModelStateInvalidFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (66, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.567' AS DateTime), N'Info', N'Executing action method CoreThreeTier.Controllers.UserController.GetUsers (CoreThreeTier) - Validation state: Valid', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker+Log.ActionMethodExecuting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (67, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:06.567' AS DateTime), N'Info', N'Gettting all users', N'CoreThreeTier.Controllers.UserController', N'CoreThreeTier.Controllers.UserController.GetUsers', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (68, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.027' AS DateTime), N'Debug', N'Entity Framework Core 8.0.2 initialized ''CoreScaffoldDbContext'' using provider ''Microsoft.EntityFrameworkCore.SqlServer:8.0.2'' with options: None', N'Microsoft.EntityFrameworkCore.Infrastructure', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`5.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (69, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.060' AS DateTime), N'Debug', N'Compiling query expression: 
''DbSet<User>()''', N'Microsoft.EntityFrameworkCore.Query', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (70, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.213' AS DateTime), N'Debug', N'Generated query execution expression: 
''queryContext => new SingleQueryingEnumerable<User>(
    (RelationalQueryContext)queryContext, 
    RelationalCommandCache.QueryExpression(
        Projection Mapping:
            EmptyProjectionMember -> Dictionary<IProperty, int> { [Property: User.Id (int) Required PK AfterSave:Throw ValueGenerated.OnAdd, 0], [Property: User.Address (string) MaxLength(255), 1], [Property: User.Age (int?), 2], [Property: User.Email (string) MaxLength(100), 3], [Property: User.Gender (string) MaxLength(10), 4], [Property: User.Name (string) MaxLength(100), 5], [Property: User.Phone (string) MaxLength(15), 6] }
        SELECT u.ID, u.Address, u.Age, u.Email, u.Gender, u.Name, u.Phone
        FROM Users AS u), 
    null, 
    Func<QueryContext, DbDataReader, ResultContext, SingleQueryResultCoordinator, User>, 
    DataAccessLayer_DAL_.DataAccess.CoreScaffoldDbContext, 
    False, 
    False, 
    True
)''', N'Microsoft.EntityFrameworkCore.Query', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (71, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.243' AS DateTime), N'Debug', N'Creating DbConnection.', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (72, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.253' AS DateTime), N'Debug', N'Created DbConnection. (4ms).', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`1.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (73, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.253' AS DateTime), N'Debug', N'Opening connection to database ''CoreScaffoldDB'' on server ''DESKTOP-LI40AFI\SQLEXPRESS''.', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (74, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.280' AS DateTime), N'Debug', N'Opened connection to database ''CoreScaffoldDB'' on server ''DESKTOP-LI40AFI\SQLEXPRESS''.', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (75, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.287' AS DateTime), N'Debug', N'Creating DbCommand for ''ExecuteReader''.', N'Microsoft.EntityFrameworkCore.Database.Command', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`1.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (76, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.287' AS DateTime), N'Debug', N'Created DbCommand for ''ExecuteReader'' (4ms).', N'Microsoft.EntityFrameworkCore.Database.Command', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (77, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.287' AS DateTime), N'Debug', N'Initialized DbCommand for ''ExecuteReader'' (10ms).', N'Microsoft.EntityFrameworkCore.Database.Command', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (78, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.303' AS DateTime), N'Debug', N'Executing DbCommand [Parameters=[], CommandType=''Text'', CommandTimeout=''30'']
SELECT [u].[ID], [u].[Address], [u].[Age], [u].[Email], [u].[Gender], [u].[Name], [u].[Phone]
FROM [Users] AS [u]', N'Microsoft.EntityFrameworkCore.Database.Command', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`5.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (79, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.327' AS DateTime), N'Info', N'Executed DbCommand (24ms) [Parameters=[], CommandType=''Text'', CommandTimeout=''30'']
SELECT [u].[ID], [u].[Address], [u].[Age], [u].[Email], [u].[Gender], [u].[Name], [u].[Phone]
FROM [Users] AS [u]', N'Microsoft.EntityFrameworkCore.Database.Command', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`6.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (80, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.363' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (81, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.390' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (82, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.390' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (83, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.390' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (84, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.390' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (85, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.390' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (86, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.400' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (87, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.400' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (88, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.400' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (89, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.400' AS DateTime), N'Debug', N'Context ''CoreScaffoldDbContext'' started tracking ''User'' entity. Consider using ''DbContextOptionsBuilder.EnableSensitiveDataLogging'' to see key values.', N'Microsoft.EntityFrameworkCore.ChangeTracking', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (90, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.400' AS DateTime), N'Debug', N'Closing data reader to ''CoreScaffoldDB'' on server ''DESKTOP-LI40AFI\SQLEXPRESS''.', N'Microsoft.EntityFrameworkCore.Database.Command', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (91, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.400' AS DateTime), N'Debug', N'A data reader for ''CoreScaffoldDB'' on server ''DESKTOP-LI40AFI\SQLEXPRESS'' is being disposed after spending 81ms reading results.', N'Microsoft.EntityFrameworkCore.Database.Command', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`3.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (92, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.420' AS DateTime), N'Debug', N'Closing connection to database ''CoreScaffoldDB'' on server ''DESKTOP-LI40AFI\SQLEXPRESS''.', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (93, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.420' AS DateTime), N'Debug', N'Closed connection to database ''CoreScaffoldDB'' on server ''DESKTOP-LI40AFI\SQLEXPRESS'' (2ms).', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`3.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (94, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.420' AS DateTime), N'Info', N'Executed action method CoreThreeTier.Controllers.UserController.GetUsers (CoreThreeTier), returned result Microsoft.AspNetCore.Mvc.OkObjectResult in 855.2465ms.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker+Log.ActionMethodExecuted', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (95, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.420' AS DateTime), N'Trace', N'Action Filter: Before executing OnActionExecuted on filter Microsoft.AspNetCore.Mvc.Infrastructure.ModelStateInvalidFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (96, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.433' AS DateTime), N'Trace', N'Action Filter: After executing OnActionExecuted on filter Microsoft.AspNetCore.Mvc.Infrastructure.ModelStateInvalidFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (97, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.433' AS DateTime), N'Trace', N'Action Filter: Before executing OnActionExecuted on filter Microsoft.AspNetCore.Mvc.ModelBinding.UnsupportedContentTypeFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (98, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.433' AS DateTime), N'Trace', N'Action Filter: After executing OnActionExecuted on filter Microsoft.AspNetCore.Mvc.ModelBinding.UnsupportedContentTypeFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Next', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (99, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.433' AS DateTime), N'Trace', N'Result Filter: Before executing OnResultExecuting on filter Microsoft.AspNetCore.Mvc.Infrastructure.ClientErrorResultFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker.ResultNext', N'')
GO
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (100, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.433' AS DateTime), N'Trace', N'Result Filter: After executing OnResultExecuting on filter Microsoft.AspNetCore.Mvc.Infrastructure.ClientErrorResultFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker.ResultNext', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (101, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.433' AS DateTime), N'Trace', N'Before executing action result Microsoft.AspNetCore.Mvc.OkObjectResult.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker.InvokeResultAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (102, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.433' AS DateTime), N'Debug', N'List of registered output formatters, in the following order: Microsoft.AspNetCore.Mvc.Formatters.HttpNoContentOutputFormatter, Microsoft.AspNetCore.Mvc.Formatters.StringOutputFormatter, Microsoft.AspNetCore.Mvc.Formatters.StreamOutputFormatter, Microsoft.AspNetCore.Mvc.Formatters.SystemTextJsonOutputFormatter', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector.SelectFormatter', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (103, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.450' AS DateTime), N'Debug', N'No information found on request to perform content negotiation.', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector.SelectFormatter', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (104, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.450' AS DateTime), N'Debug', N'Attempting to select an output formatter without using a content type as no explicit content types were specified for the response.', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector.SelectFormatter', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (105, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.450' AS DateTime), N'Debug', N'Attempting to select the first formatter in the output formatters list which can write the result.', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector.SelectFormatterNotUsingContentType', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (106, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.450' AS DateTime), N'Debug', N'Selected output formatter ''Microsoft.AspNetCore.Mvc.Formatters.SystemTextJsonOutputFormatter'' and content type ''application/json'' to write the response.', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector', N'Microsoft.AspNetCore.Mvc.Infrastructure.DefaultOutputFormatterSelector+Log.FormatterSelected', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (107, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.450' AS DateTime), N'Info', N'Executing OkObjectResult, writing value of type ''System.Collections.Generic.List`1[[DataAccessLayer_DAL_.DataAccess.User, DataAccessLayer(DAL), Version=1.0.0.0, Culture=neutral, PublicKeyToken=null]]''.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ObjectResultExecutor', N'Microsoft.AspNetCore.Mvc.Infrastructure.ObjectResultExecutor+Log.ObjectResultExecuting', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (108, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.473' AS DateTime), N'Trace', N'After executing action result Microsoft.AspNetCore.Mvc.OkObjectResult.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker.InvokeResultAsync', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (109, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.473' AS DateTime), N'Trace', N'Result Filter: Before executing OnResultExecuted on filter Microsoft.AspNetCore.Mvc.Infrastructure.ClientErrorResultFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker.ResultNext', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (110, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.473' AS DateTime), N'Trace', N'Result Filter: After executing OnResultExecuted on filter Microsoft.AspNetCore.Mvc.Infrastructure.ClientErrorResultFilter.', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker.ResultNext', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (111, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.473' AS DateTime), N'Info', N'Executed action CoreThreeTier.Controllers.UserController.GetUsers (CoreThreeTier) in 1088.7435ms', N'Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker', N'Microsoft.AspNetCore.Mvc.Infrastructure.ResourceInvoker+Log.ExecutedAction', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (112, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.487' AS DateTime), N'Info', N'Executed endpoint ''CoreThreeTier.Controllers.UserController.GetUsers (CoreThreeTier)''', N'Microsoft.AspNetCore.Routing.EndpointMiddleware', N'Microsoft.AspNetCore.Routing.EndpointMiddleware.Invoke', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (113, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.487' AS DateTime), N'Debug', N'Connection id "0HN1MOH2VG4T5" completed keep alive response.', N'Microsoft.AspNetCore.Server.Kestrel.Connections', N'Microsoft.AspNetCore.Server.Kestrel.Core.Internal.Http.HttpProtocol.WriteSuffix', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (114, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.487' AS DateTime), N'Debug', N'''CoreScaffoldDbContext'' disposed.', N'Microsoft.EntityFrameworkCore.Infrastructure', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`1.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (115, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.487' AS DateTime), N'Debug', N'Disposing connection to database ''CoreScaffoldDB'' on server ''DESKTOP-LI40AFI\SQLEXPRESS''.', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`2.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (116, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.503' AS DateTime), N'Debug', N'Disposed connection to database '''' on server '''' (4ms).', N'Microsoft.EntityFrameworkCore.Database.Connection', N'Microsoft.EntityFrameworkCore.Diagnostics.EventDefinition`3.Log', N'')
INSERT [dbo].[log] ([logId], [MachineName], [Logged], [Level], [Message], [Logger], [Callsite], [Exception]) VALUES (117, N'DESKTOP-LI40AFI', CAST(N'2024-02-26T17:43:07.503' AS DateTime), N'Info', N'Request finished HTTP/1.1 GET http://localhost:5273/api/User/GetAllUsers - 200 - application/json;+charset=utf-8 1186.7834ms', N'Microsoft.AspNetCore.Hosting.Diagnostics', N'Microsoft.AspNetCore.Hosting.HostingApplicationDiagnostics.LogRequestFinished', N'')
SET IDENTITY_INSERT [dbo].[log] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (4, N'Emily Brown', N'emily@example.com', 30, N'321 Maple St, City, Country', N'789-012-3456', N'Female')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (5, N'Christopher Lee', N'chris@example.com', 40, N'567 Pine St, City, Country', N'012-345-6789', N'Male')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (6, N'Amanda Wilson', N'amanda@example.com', 32, N'901 Cedar St, City, Country', N'345-678-9012', N'Female')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (7, N'Kevin', N'kevin@gmail.com', 21, N'Mehsana', N'9348509483', N'Male')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (8, N'Jessica Martinez', N'jessica@example.com', 29, N'890 Spruce St, City, Country', N'901-234-5678', N'Female')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (9, N'Daniel Garcia', N'daniel@example.com', 33, N'678 Walnut St, City, Country', N'234-567-8901', N'Male')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (10, N'Sarah Thomas', N'sarah@example.com', 31, N'345 Ash St, City, Country', N'567-890-1234', N'Female')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (1002, N'Himanshu', N'hp@gmail.com', 21, N'Mehsana', N'840123654', N'Male')
INSERT [dbo].[Users] ([ID], [Name], [Email], [Age], [Address], [Phone], [Gender]) VALUES (2005, N'Rutul', N'rutul1234@gmail.com', 232, N'Mehsana', N'', N'Male')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
USE [master]
GO
ALTER DATABASE [CoreScaffoldDB] SET  READ_WRITE 
GO
