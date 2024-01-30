use InventoryManagement

--view for customer table to view name,email and mobile data
CREATE VIEW customerDetailsView AS SELECT name,email,mobile FROM customer;

SELECT * FROM customerDetailsView;

--view for product table
CREATE VIEW productDetailsView AS SELECT name,description,selling_price FROM product;

SELECT * FROM productDetailsView;


--create scaler-valued function for calculate total order 
CREATE FUNCTION dbo.GetTotalOrderedQuantity
(
    @customerId INT
)
RETURNS INT
AS
BEGIN
    DECLARE @totalQuantity INT;

    SELECT @totalQuantity = SUM(quantity)
    FROM orders
    WHERE customer_id = @customerId;

    RETURN ISNULL(@totalQuantity, 0);
END;

--Execute scaler fun
declare @customerId int = 1;
select id, dbo.GetTotalOrderedQuantity(@customerId) AS TotalOrderQuantity
from customer
where id = @customerId

--create scaler valued function for calculate age of customer
CREATE FUNCTION dbo.CalculateCustomerAge
(
    @dob DATE
)
RETURNS INT
AS
BEGIN
    DECLARE @age INT;

    SET @age = DATEDIFF(YEAR, @dob, GETDATE());

    RETURN ISNULL(@age, 0);
END;

--calling age fun 
SELECT
    name AS CustomerName,
    dob AS DateOfBirth,
    dbo.CalculateCustomerAge(dob) AS Age
FROM
    customer;


------------------------------------create stored procedure for select 
create procedure SelectAllProducts
AS 
select * from product
go;


------------------------------------exec stored procedure for select
exec SelectAllProducts;

--------------------------------------------stored procedure for add
-- Add stored procedure
CREATE PROCEDURE dbo.AddNewProduct
    @productName NVARCHAR(50),
	@categoryID INT,
    @buyingPrice REAL,
    @sellingPrice REAL,
    @stock INT
AS
BEGIN
    -- Insert a new product
    INSERT INTO product (name,categories_id,buying_price, selling_price, stock, description, created, modified)
    VALUES (@productName, @categoryID, @buyingPrice, @sellingPrice, @stock, 'New product added', GETDATE(), GETDATE());
END;

---------------------------------------------------exec adding fridge 
exec dbo.AddNewProduct @productName="Fridge",
	@categoryID = 4,
	@buyingPrice = 10000,
	@sellingPrice = 15000,
	@stock = 20;



-- -------------------------------------create update stored procedure
CREATE PROCEDURE dbo.UpdateProductDetails
    @productID INT,
    @newProductName NVARCHAR(50),
    @newBuyingPrice REAL,
    @newSellingPrice REAL,
    @newStock INT
AS
BEGIN
    -- Update product information
    UPDATE product
    SET
        name = @newProductName,
        buying_price = @newBuyingPrice,
        selling_price = @newSellingPrice,
        stock = @newStock,
        modified = GETDATE()
    WHERE
        id = @productID;
END;

-------------------------------------------exec update procedure
exec dbo.UpdateProductDetails @productID = 2,
    @newProductName =  'Mobile',
    @newBuyingPrice  = 8000,
    @newSellingPrice = 10000,
    @newStock =  50;

----------------------------------------------create delete procedure
CREATE PROCEDURE dbo.DeleteProduct
    @productID INT
AS
BEGIN
    -- Delete a product
    DELETE FROM product
    WHERE id = @productID;
END;

--exec procedure delete
exec dbo.DeleteProduct @productID = 4;



----------------------------------------------full procedure 

-- Full stored procedure
CREATE PROCEDURE dbo.ManageProduct
    @action NVARCHAR(10), -- 'SELECT', 'ADD', 'UPDATE', 'DELETE'
    @productID INT = NULL,
    @productName NVARCHAR(50) = NULL,
    @buyingPrice REAL = NULL,
    @sellingPrice REAL = NULL,
    @stock INT = NULL
AS
BEGIN
    IF @action = 'SELECT'
    BEGIN
        -- Retrieve product details
        EXEC dbo.SelectAllProducts;
    END
    ELSE IF @action = 'ADD'
    BEGIN
        -- Add a new product
        EXEC dbo.AddNewProduct @productName,
    @buyingPrice ,
    @sellingPrice ,
    @stock,@stock;
    END
    ELSE IF @action = 'UPDATE'
    BEGIN
        -- Update product information
        EXEC dbo.UpdateProductDetails @productID, @productName, @buyingPrice, @sellingPrice, @stock;
    END
    ELSE IF @action = 'DELETE'
    BEGIN
        -- Delete a product
        EXEC dbo.DeleteProduct @productID;
    END
    ELSE
    BEGIN
        -- Invalid action
        PRINT 'Invalid action specified.';
    END;
END;

--exec full procedure 
exec dbo.ManageProduct @action = 'SELECT';



-- Create a non-clustered index on the productName column of the product table
CREATE NONCLUSTERED INDEX IX_Product_ProductName
ON product (name);

-- Create a non-clustered index on the categoryID column of the product table
CREATE NONCLUSTERED INDEX IX_Product_CategoryID
ON product (categories_id);

--triggers on inventory 

-- Create BEFORE INSERT trigger
CREATE TRIGGER tr_before_insert_customer
ON customer
Instead of INSERT
AS
BEGIN
	Insert into Log Values ('Insert before trigger in customer table ', GETDATE()); 
	INSERT into customer ([name],[email],[mobile] ,[address],[dob],[created] ,[modified])
	select [name],[email],[mobile] ,[address],[dob],[created] ,[modified]
	from inserted;
END;

-- Create AFTER INSERT trigger
CREATE TRIGGER tr_after_insert_customer
ON customer
AFTER INSERT
AS
BEGIN
  PRINT 'After Insert trigger in customer'
END;

--insert query
INSERT into customer values ('Jay', 'jay@gmail.com', '1234567890','Khambhat' ,'2002-10-25',GETDATE(),GETDATE());

DBCC CHECKIDENT ('Log', RESEED, 1)--reset identity in log table


-- Create BEFORE UPDATE trigger
CREATE TRIGGER tr_before_update_customer
ON customer
instead of UPDATE
AS
BEGIN
  PRINT 'Before Update trigger in customer'
  update customer set modified = GETDATE()
  where id in(select id from inserted)
END;

-- Create AFTER UPDATE trigger
CREATE TRIGGER tr_after_update_customer
ON customer
AFTER UPDATE
AS
BEGIN
	PRINT 'After Update trigger in customer'
END;
--update query
update customer set modified = GETDATE()
  where id = 9;



  -- Create BEFORE DELETE trigger
CREATE TRIGGER tr_before_delete_customer
ON customer
INSTEAD OF DELETE
AS
BEGIN
  PRINT 'Before Delete trigger in customer'
  Delete From customer where id in (select id from deleted);
END;

 -- Create after DELETE trigger
CREATE TRIGGER tr_after_delete_customer
ON customer
AFTER DELETE
AS
BEGIN
  PRINT 'After Delete trigger in customer'
END;

--delete query
Delete From customer where id = 8;



--union and union all
-- Example of UNION query on product and customer tables
SELECT id, name, 'Product' AS type
FROM product
UNION
SELECT id, name, 'Customer' AS type
FROM customer;

-- Example of UNION ALL query on product and supplier tables
SELECT id, name, 'Product' AS type
FROM product
UNION ALL
SELECT id, name, 'Supplier' AS type
FROM supplier;

-- join queries

SELECT *
FROM product
INNER JOIN category ON product.categories_id = category.id;

SELECT *
FROM product
LEFT JOIN category ON product.categories_id = category.id;

SELECT *
FROM product
RIGHT JOIN category ON product.categories_id = category.id;

SELECT *
FROM product
FULL JOIN category ON product.categories_id = category.id;
