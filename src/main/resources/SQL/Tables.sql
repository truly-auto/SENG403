
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
  name varchar(32),
  item_type varchar(32),
  supplier_id int,
  quantity int,
  price varchar(32),
  PRIMARY KEY (item_number),
  FOREIGN KEY (supplier_id) references Supplier(supplier_id)
	);
  
  
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
--adding sample data

	
--adding some sample stores
INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Jane Tops','403-989-3214','1 14 ST NW.','Calgary','www.yellowpages.com/janetops');

--adding sample suppliers
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

--adding some sample inventory
INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, PRICE)
VALUES ('Apple Pies','Bakery',1, 1000, '$4/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, PRICE)
VALUES ('Cinnamon Blueberry Pies','Bakery',1, 1500, '$40/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, PRICE)
VALUES ('Lemon Cakes','Bakery',1, 1300, '$20/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, PRICE)
VALUES ('Blueberry Muffins (6-pack)','Bakery',1, 3140, '$14/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Brownies','Bakery',1, 1320, '$34/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Quadruple Chocolate Cake','Bakery',1, 1780, '$14/20LBS');

<<<<<<< HEAD

=======
<<<<<<< HEAD
INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Beets','Vegetable',2, 1050, '$10/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Broccoli','Vegetable',2, 2000, '$9/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Brussels Sprouts','Vegetable',2, 500, '$15/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Cabbage','Vegetable',2, 3000, '$8/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Cucumber','Vegetable',2, 2050, '$8.50/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Eggplant','Vegetable',2, 2000, '$10/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Onion','Vegetable',2, 3000, '$7.50/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Spinach','Vegetable',2, 2000, '$8/20LBS');
=======
>>>>>>> FETCH_HEAD
--adding some default users	
INSERT INTO SUPPLIER_USERS (USERNAME, PASSWORD, SUPPLIER_ID)
VALUES ('John_Doe','password',1);	

INSERT INTO STORE_USERS (USERNAME, PASSWORD, STORE_ID)
VALUES ('Jane_Doe','password',1);	

<<<<<<< HEAD
INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Beets','Vegetable',2, 1050, '$10/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Broccoli','Vegetable',2, 2000, '$9/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Brussels Sprouts','Vegetable',2, 500, '$15/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Cabbage','Vegetable',2, 3000, '$8/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Cucumber','Vegetable',2, 2050, '$8.50/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Eggplant','Vegetable',2, 2000, '$10/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Onion','Vegetable',2, 3000, '$7.50/20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,PRICE)
VALUES ('Spinach','Vegetable',2, 2000, '$8/20LBS');

=======
>>>>>>> origin/master
>>>>>>> FETCH_HEAD
