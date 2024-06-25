create table users(
	id serial primary key,
	name text
);
create table requests(
	id serial primary key,
	name text,
    users_id int references users(id)
);
insert into users (name) values ('Василий');
insert into users (name) values ('Дмитрий');
insert into users (name) values ('Анастасия');

insert into requests(name, users_id) values('починить кофе-машину', 1);
insert into requests(name, users_id) values('починить стул', 1);
insert into requests(name, users_id) values('заправить принтер', 2);
insert into requests(name, users_id) values('купить тонер для принтера', 3);
insert into requests(name, users_id) values('досмотреть сериал. узнать чем он закончится, чтобы рассказать потом боссу.', 3);

select usr.name, req.name 
 from requests as req join users as usr on req.users_id = usr.id;

select r.name, lusers.name
 from requests as r join users as lusers on r.users_id = lusers.id order by lusers.name asc;

select lusers.name as "Имя сотрудника", r.name as "поставленная задача"
 from requests r join users lusers on r.users_id = lusers.id order by "Имя сотрудника" asc;