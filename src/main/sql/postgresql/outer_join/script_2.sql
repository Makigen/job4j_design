create table body (
	id serial primary key,
	name varchar(255)
);

create table engine (
	id serial primary key,
	name varchar(255)
);

create table transmission (
	id serial primary key,
	name varchar(255)
);

create table car (
	id serial primary key,
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values ('sedan'), ('universal'), ('hatchback'), ('pickup');
insert into engine(name) values ('gas'), ('diesel'), ('hybrid'), ('electric');
insert into transmission(name) values ('manual'), ('automatic'), ('robot');

insert into car(body_id, engine_id, transmission_id) values (1, 1, 1);
insert into car(body_id, engine_id, transmission_id) values (1, 2, 1);
insert into car(body_id, engine_id, transmission_id) values (2, 1, 1);
insert into car(body_id, engine_id, transmission_id) values (3, 3, 2);
insert into car(body_id, engine_id, transmission_id) values (2, 2, 2);
insert into car(body_id, engine_id, transmission_id) values (3, 2, 1);

select c.id, b.name, e.name, t.name
	from car c 
	join body as b 
		on c.body_id = b.id
	join engine as e 
		on c.engine_id = e.id
	join transmission as t 
		on c.transmission_id = t.id;
		
select * from body b left join car c on c.body_id = b.id where c.body_id is null;
select * from engine e left join car c on c.engine_id = e.id where c.engine_id is null;
select * from transmission t left join car c on c.transmission_id = t.id where c.transmission_id is null;