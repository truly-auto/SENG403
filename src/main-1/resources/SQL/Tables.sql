
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
  unit_price decimal,
  unit varchar(32),
  PRIMARY KEY (item_number),
  FOREIGN KEY (supplier_id) references Supplier(supplier_id)
	);

--Create Items table 
CREATE TABLE supermarket_inventory (
  inventory_number int NOT NULL generated always as identity,
  name varchar(32),
  inventory_type varchar(32),
  supermarket_id int,
  quantity int,
  supplier_item_number int,
  unit_price decimal,
  unit varchar(32),
  PRIMARY KEY (inventory_number),
  FOREIGN KEY (supermarket_id) references Supermarket(store_id)
);
  
--Create Users tables
CREATE TABLE store_users (
	username varchar (32) not null,
	password varchar (32) not null, 
	store_id int, 
	manager varchar (32),
	PRIMARY KEY (username),
	FOREIGN KEY (store_id) REFERENCES Supermarket(store_id)
	);

CREATE TABLE supplier_users (
	username varchar (32) not null,
	password varchar (32) not null, 
	supplier_id int, 
	manager varchar (32),
	PRIMARY KEY (username),
	FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
	);
	
--Create Order table
CREATE TABLE order_history(
	invoice_number int NOT NULL generated always as identity,
	total_cost decimal,
	date_time_created timestamp,
	status varchar(32),
	PRIMARY KEY (invoice_number)
	);

CREATE TABLE automatic_orders(
	id int not null generated always as identity,
	threshold int,
	quantity int,
	supermarket_item int,
	primary key (id),
	foreign key (supermarket_item) references supermarket_inventory(inventory_number)
	);


	
--adding some sample stores
INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Jane Tops','403-989-3214','1 14 ST NW.','Calgary','www.yellowpages.com/janetops');

INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Saferway','403-934-0324','114  3 ST SW.','Calgary','thesaferway@riskyworld.com');

INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Corner Convinience','587-439-7523','3 24 AVE NW.','Calgary','cronerstore@theblock.ca');

INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('The Big Store','403-954-2333','13 12 AVE NE.','Calgary','bigstore@emailus.ca');

INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('WorstBuy','403-435-2543','13 14 AVE NE.','Calgary','worstbuye@bestbuy.ca');

INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('Save-On-Foods','403-384-9780','225 Panatella Hill N.W ','Calgary','http://www.saveonfoods.com');

INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('No Frills','587-296-3508','882 85th St. SW, AB T3H 0J5','Calgary','http://www.nofrills.com');

INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL)
VALUES ('T&T Supermarket', '403-569-6888', '#800 - 999 36th Street N.E.','Calgary','http://www.tnt-supermarket.com');


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
INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Apple Pies','Bakery',1, 1000, 4, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Cinnamon Blueberry Pies','Bakery',1, 1500, 40, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Lemon Cakes','Bakery',1, 1300, 20, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Blueberry Muffins (6-pack)','Bakery',1, 3140, 14, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Brownies','Bakery',1, 1320, 34, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Quadruple Chocolate Cake','Bakery',1, 1780, 14, '20LBS');

--adding some default users	
INSERT INTO SUPPLIER_USERS (USERNAME, PASSWORD, SUPPLIER_ID, MANAGER)
VALUES ('John_Doe','password',1, 'true');	

INSERT INTO SUPPLIER_USERS (USERNAME, PASSWORD, SUPPLIER_ID, MANAGER)
VALUES ('June_Doe','password',1, 'false');

INSERT INTO STORE_USERS (USERNAME, PASSWORD, STORE_ID)
VALUES ('Jane_Doe','password',1);	

INSERT INTO SUPPLIER_USERS (USERNAME, PASSWORD, SUPPLIER_ID, MANAGER)
VALUES ('Josh_Senior','password',2, 'true');

INSERT INTO SUPPLIER_USERS (USERNAME, PASSWORD, SUPPLIER_ID, MANAGER)
VALUES ('Josh_Doe','password',2, 'false');

--adding some store users

INSERT INTO STORE_USERS (USERNAME, PASSWORD, STORE_ID, MANAGER)
VALUES ('Jane_Doe','password',1, 'true');	

INSERT INTO STORE_USERS (USERNAME, PASSWORD, STORE_ID, MANAGER)
VALUES ('Jane_Daughter','password',1, 'false');	

INSERT INTO STORE_USERS (USERNAME, PASSWORD, STORE_ID, MANAGER)
VALUES ('Princess_Doe','password',2, 'true');	

INSERT INTO STORE_USERS (USERNAME, PASSWORD, STORE_ID, MANAGER)
VALUES ('Prince_Doe','password',2, 'false');	

--add some more items
INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,UNIT_PRICE, UNIT)
VALUES ('Beets','Vegetable',2, 1050, 10, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Broccoli','Vegetable',2, 2000, 9, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Brussels Sprouts','Vegetable',2, 500, 15, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Cabbage','Vegetable',2, 3000, 8, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Cucumber','Vegetable',2, 2050, 8.50, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Eggplant','Vegetable',2, 2000, 10, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Onion','Vegetable',2, 3000, 7.50, '20LBS');

INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY, UNIT_PRICE, UNIT)
VALUES ('Spinach','Vegetable',2, 2000, 8, '20LBS');

INSERT INTO ORDER_HISTORY (TOTAL_COST, DATE_TIME_CREATED, STATUS)
VALUES (500.50, CURRENT_TIMESTAMP, 'Submitted');

INSERT INTO ORDER_HISTORY (TOTAL_COST, DATE_TIME_CREATED, STATUS)
VALUES (15600.99, CURRENT_TIMESTAMP, 'Shipped');

INSERT INTO ORDER_HISTORY (TOTAL_COST, DATE_TIME_CREATED, STATUS)
VALUES (10600.49, CURRENT_TIMESTAMP, 'Completed');
