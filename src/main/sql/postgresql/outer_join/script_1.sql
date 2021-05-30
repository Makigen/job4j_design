create table departments (
	id serial primary key,
	name varchar(255)
);

create table employees (
	employees_id serial primary key,
	name varchar(255),
	department_id INT references departments(id)
);

insert into departments(name)
values ('A'), ('B'), ('C'), ('D');

insert into employees(name, department_id)
values ('Kirill', 1), ('Michael', 2), ('Peter', 3),
('Paul', 2), ('Simon', 1), ('John', 3), ('Andrew', null), ('Thomas', null);

select * from employees e left join departments d on e.department_id = d.id;

select * from employees e right join departments d on e.department_id = d.id;

select * from employees e full join departments d on e.department_id = d.id;

select * from employees e cross join departments d;

select * from departments d left join employees e on e.department_id = d.id where e.department_id is null;

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on d.id = e.department_id;

create table teens(
	id serial primary key,
	name varchar(20),
	gender char(1)
);

insert into teens(name, gender)
values ('Kirill', 'M'), ('Michael', 'M'), ('Peter', 'M'), ('Kristina', 'F'), ('Karolina', 'F'), ('Maria', 'F');

select t1.name, t2.name from teens as t1 cross join teens as t2 where t1.gender > t2.gender;