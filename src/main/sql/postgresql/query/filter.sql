select * from product as p join type t on t.id = p.type_id and t.name = 'СЫР';
select * from product as p join type t on t.id = p.type_id and p.name LIKE '%мороженое%';
select * from product where expired_date < current_date;
select * from product where price = (select max(price) from product);
select t.name, count(p.type_id) from product as p right outer join type as t on p.type_id = t.id group by t.name;
select * from product as p join type t on t.id = p.type_id and (t.name = 'СЫР' or t.name = 'МОЛОКО');
select t.name from product as p right outer join type as t on p.type_id = t.id group by t.name having count(p.type_id) < 10;
select * from product as p join type t on t.id = p.type_id;