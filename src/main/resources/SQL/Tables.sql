
--Create Supermarket table
CREATE TABLE Supermarket
(
	store_id int not null generated always as identity,
	name varchar(255) NOT NULL,
	phoneNumber varchar(255),
	address varchar(255),
	city varchar(255),
	email varchar(255),
	PRIMARY KEY (store_id)
);

--Create Supplier table
CREATE TABLE Supplier
(
	supplier_id int not null generated always as identity,
	name varchar(255) NOT NULL,
	phoneNumber varchar(255),
	address varchar(255),
	city varchar(255),
	email varchar(255),
	PRIMARY KEY (supplier_id)
);

--Create Items table 
CREATE TABLE items (
  item_number int NOT NULL generated always as identity,
  quantity int,
  barcode int,
  name varchar(32),
  item_type varchar(32),
  PRIMARY KEY (item_number));
  
  
--Create Users tables
CREATE TABLE store_users (
	username varchar (32) not null,
	password varchar (32) not null, 
	store_id int, 
	PRIMARY KEY (username),
	FOREIGN KEY (store_id) REFERENCES Supermarket(store_id)
	);

CREATE TABLE supplier_users (
	username varchar (32) not null,
	password varchar (32) not null, 
	supplier_id int, 
	PRIMARY KEY (username),
	FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
	);
	
INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com');
	
INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Aliments Reinhart Foods','1-905-264-0072','235 Yorkland Blvd, Suite 1101','Toronto','www.reinhartfoods.com');

INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Ammmazing Donuts','(416) 398-7546','3772 Bathurst Street','Toronto','');

INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Aron Streit Inc.','1-212-475-7000','148-154 Rivington Street','New York','www.streitsmatzos.com');
	
INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Bayer','1-800-622-2937 (62BAYER)','77 Belfield Road','Toronto','www.bayer.com');