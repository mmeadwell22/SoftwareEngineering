Use OrderingSystem;

IF OBJECT_ID('Customer') IS NOT NULL
    BEGIN
        DROP TABLE Customer;
    END

IF OBJECT_ID('Order_Info') IS NOT NULL
    BEGIN
        DROP TABLE Order_Info;
    END

IF OBJECT_ID('Product') IS NOT NULL
    BEGIN
        DROP TABLE Product;
    END

CREATE TABLE Product
(
    Product_Name     VARCHAR(50)     NOT NULL,
    Item_Price            DECIMAL(15, 2),
    Product_ID              VARCHAR(9)      NOT NULL,
    Stock            VARCHAR(9)      NOT NULL,
    Order_ID             VARCHAR(9),
    Supplier_Address VARCHAR(50),
    PRIMARY KEY (Product_ID)
);

CREATE TABLE Customer
(
    Customer_Name     VARCHAR (50) NOT NULL,
    Customer_ID       VARCHAR(9)   NOT NULL,
    Contact           VARCHAR(10),
    Business_Address  VARCHAR(50)  NOT NULL,
    Payment_Info      CHAR(16),
    Company_Name      VARCHAR(50),

    PRIMARY KEY (Customer_ID),
);

CREATE TABLE Order_Info
(
    Order_ID         VARCHAR(9)  NOT NULL,
    Customer_ID              VARCHAR(9)  NOT NULL,
    Product_ID              VARCHAR(9)  NOT NULL,
    Delivery_Address VARCHAR(50) NOT NULL,
    Order_Date       DATE,

    PRIMARY KEY (Order_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID),
    FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID)
);

INSERT INTO Customer VALUES ('Noel Mulcahy', '123456789', '8138381715', '123 Bay Dr', '4060000000000000', 'theRuniversity');
INSERT INTO Product VALUES ('Doors', 1230.23, '1111111', '12', '432', '555 add lane')
INSERT INTO Order_Info VALUES ('12', '123456', '6542', '510 address lane', '12-Jan-2020')