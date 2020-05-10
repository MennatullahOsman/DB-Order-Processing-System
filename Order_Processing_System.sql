-- create schema order_processing_system;

create table Publisher(
Publisher_name varchar(100) primary key);

create table Publisher_Address(
Publisher_name varchar(100),
Address varchar(100),
constraint primary key (Publisher_name, Address),
constraint foreign key (Publisher_name) references Publisher(Publisher_name)
);

create table Publisher_Phones(
Publisher_name varchar(100),
Phone varchar(14),
constraint primary key (Publisher_name, Phone),
constraint foreign key (Publisher_name) references Publisher(Publisher_name)
);
create table book(
ISBN_number char(10) primary key,
title varchar(100),
publisher_name varchar(100),
publication_year year,
selling_price double,
category enum('Science','Art','Religion','History','Geography'),
available_copies int unsigned,
threshold int unsigned,
order_quantity int unsigned,
constraint foreign key (Publisher_name) references Publisher(Publisher_name));


create table Book_Authors(
ISBN_number char(10),
Author varchar(100),
constraint primary key (ISBN_number, Author),
constraint foreign key (ISBN_number) references Book(ISBN_number));

create table Orders(
ISBN_number char(10),
order_date datetime,
quantity int unsigned,
constraint primary key(ISBN_number,order_date),
constraint foreign key (ISBN_number) references Book(ISBN_number));

create table Users(
username varchar(20) primary key,
first_name varchar(20),
second_name varchar(20),
user_password char(40),
user_phone varchar(14),
user_address varchar(100),
user_email varchar(30),
user_privilege enum('customer', 'manager'));

DELIMITER $$
create trigger negative_copies_check before update on Book
for each row
begin
	if new.available_copies < 0 then
		signal sqlstate '45000' set message_text = 'Out of coppies';
    end if;
end;
DELIMITER;

DELIMITER $$
create trigger place_order after update on Book
for each row 
begin
	if new.available_copies < new.threshold then
		insert into Orders values(new.ISBN_number, NOW(), new.order_quantity);
    end if;
end;
DELIMITER;

DELIMITER $$
create trigger confirm_orders before delete on Orders
for each row
begin
	update Book set available_copies=available_copies + quantity 
    where Book.ISBN_number = Orders.ISBN_number;
end;
DELIMITER;
