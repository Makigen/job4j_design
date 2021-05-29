create table insurances(
 id serial primary key,
 name varchar(255),
 exp_date timestamp
);

create table cars(
 id serial primary key,
 model varchar(255),
 number varchar(6),
 insurance_id int references insurances(id)
);

insert into insurances(name, exp_date) values ('AB', date '2020-09-01');
insert into cars(model, number, insurance_id) values ('VOLVO', '962EUR', 1);