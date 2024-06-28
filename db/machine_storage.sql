create table car_bodies
(
	id serial primary key,
	name varchar(255)
);
create table car_engines
(
	id serial primary key,
	name varchar(255)
);
create table car_transmissions
(
	id serial primary key,
	name varchar(255)
);
create table cars
(
	id serial primary key,
	name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);
insert into car_bodies (name) values ('седан'), ('хэтчбек'), ('пикап'), ('универсал'), ('минивэн'), ('кабриолет');
insert into car_engines (name) values ('V образный'), ('линейный'), ('прямой'), ('VR и W'), ('плоский'), ('ротационный'), ('сердце -- пламенный мотор');
insert into car_transmissions (name) values ('механическая'), ('автоматическая'), ('роботизированная'), ('вариативная (бесступенчатая)');

insert into cars (name, body_id, engine_id, transmission_id) values ('BMW', 1, 1, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Запорожец', 1, 7, 1);
insert into cars (name, body_id, engine_id, transmission_id) values ('Корпус от Mersedes', 1, null, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('Движок и трансмиссия от Mazda', null, 2, 2);
insert into cars (name, body_id, engine_id, transmission_id) values ('Москвич без трансмиссии', 1, 7, null);
insert into cars (name, body_id, engine_id, transmission_id) values ('Bentley без движка', 6, null, 3);

/*
1) Вывести список всех машин и все привязанные к ним детали.
 Нужно учесть, что каких-то деталей машина может и не содержать.
 В таком случае значение может быть null при выводе (например, название двигателя null);
Пример "шапки" при выводе:
id, car_name, body_name, engine_name, transmission_name
*/
select car.id, car.name, body.name, eng.name, transm.name 
    from cars car
    left join car_bodies body on car.body_id = body.id
    left join car_engines eng on car.engine_id = eng.id
    left join car_transmissions transm on car.transmission_id = transm.id;

/*
2) Вывести кузова, которые не используются НИ в одной машине.
 "Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи,
 ссылающие на таблицу car_bodies.
*/
select body.name from car_bodies body
    left join cars car on car.body_id = body.id
    where car.name is null;

/*
3) Вывести двигатели, которые не используются НИ в одной машине, аналогично п.2;
*/
select eng.name from car_engines eng
    left join cars car on car.engine_id = eng.id
    where car.name is null;

/*
4) Вывести коробки передач, которые не используются НИ в одной машине, аналогично п.2.
*/
select transm.name from car_transmissions transm
    left join cars car on car.transmission_id = transm.id
    where car.name is null;

