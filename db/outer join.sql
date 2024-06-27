create table departments
(
id serial primary key,
	name text
);
create table employees
(
	id serial primary key,
	name text,
    departments_id int references departments(id)
);
insert into departments(name) values ('медицина'), ('образование'), ('наука'), ('департамент бездельников-негодяев');

insert into employees(name, departments_id) values ('Ерёменко Евгений', 1);
insert into employees(name, departments_id) values ('Шереметьев Дмитрий', 2);
insert into employees(name, departments_id) values ('Филлимонов Олег', 3);
insert into employees(name, departments_id) values ('Курчатов Сергей', 3);
insert into employees(name, departments_id) values ('Романов Пётр', 2);
insert into employees(name, departments_id) values ('Пушкин Александр', 2);
insert into employees(name, departments_id) values ('Склифасовский Сергей', 1);
insert into employees(name, departments_id) values ('Яцына Павел', 2);
insert into employees(name, departments_id) values ('Бездельник Василий', null);

/*
2. Выполнить запросы с left, right, full, cross соединениями
*/
select * from employees e
left join departments d on e.departments_id = d.id;

select * from departments d
right join employees e on e.departments_id = d.id;

select * from employees e
full join departments d on e.departments_id = d.id;

select * from employees e
cross join departments d;

/*
3. Используя left join найти департаменты, у которых нет работников
*/
select * from departments d
left join employees e on e.departments_id = d.id
where e.name is null;

/*
4. Используя left и right join написать запросы, которые давали бы одинаковый результат 
(порядок вывода колонок в эти запросах также должен быть идентичный). 
*/
select * from employees e
left join departments d on e.departments_id = d.id
where d.name is not null;

select * from employees e
right join departments d on e.departments_id = d.id
where e.name is not null;

/*
5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
 Используя cross join составить все возможные разнополые пары.
 Исключите дублирование пар вида Вася-Маша и Маша-Вася.
*/
create table teens
(
    name text,
    gender text
);

insert into teens(name, gender) values ('Вася', 'man');
insert into teens(name, gender) values ('Маша', 'woman');
insert into teens(name, gender) values ('Некифор', 'man');
insert into teens(name, gender) values ('Матрёна', 'woman');

select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender = 'man' and t2.gender = 'woman';

