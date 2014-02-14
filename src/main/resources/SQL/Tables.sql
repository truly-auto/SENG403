
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
  barcode int ,
  name varchar(32),
  item_type varchar(32),
  PRIMARY KEY (item_number));
  
  
--Create Users tables
CREATE TABLE store_users (
	username varchar (32) not null,
	password varchar (32), 
	store_id int, 
	PRIMARY KEY (username),
	FOREIGN KEY (store_id) references Supermarket(store_id)
	);

CREATE TABLE supplier_users (
	username varchar (32) not null,
	password varchar (32), 
	supplier_id int, 
	PRIMARY KEY (username),
	FOREIGN KEY (supplier_id) references Supplier(supplier_id)
	);