CREATE PROCEDURE `add_order_procedure` (ISBN char(10), quantity int unsigned)
BEGIN
	insert into Orders(ISBN_number, order_date, quantity) values (ISBN, NOW(), quantity);
END
