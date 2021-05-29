create table crypto(
	id serial primary key,
	name text,
	ticker varchar(5),
	price money
);
insert into crypto(name, ticker, price) values('Bitcoin', 'BTC', 38537.63);
update crypto set price = 38490.05;
delete from crypto;
