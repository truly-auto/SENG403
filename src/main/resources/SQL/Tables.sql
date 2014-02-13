
--Create Supermarket table
CREATE TABLE Supermarket
(
	id int not null generated always as identity,
	name varchar(255) NOT NULL,
	phoneNumber varchar(255),
	address varchar(255),
	city varchar(255),
	email varchar(255),
	PRIMARY KEY (id)
);

--Create Supplier table
CREATE TABLE Supplier
(
	id int not null generated always as identity,
	name varchar(255) NOT NULL,
	phoneNumber varchar(255),
	address varchar(255),
	city varchar(255),
	email varchar(255),
	PRIMARY KEY (id)
);

--Create Items table 
create table items (
  item_number int NOT NULL,
  barcode int ,
  name varchar(32),
  item_type varchar(32),
  primary key (item_number));
  
  
