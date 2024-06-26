create table type
(
	id serial primary key,
	name text
);
create table product
(
    id serial primary key,
    name text,
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values
('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ'), ('БУХЛО'), ('ХЛЕБ'); 

insert into product(name, type_id, expired_date, price) values
('Сыр плавленный "Дружба"', 1, '20.06.2024', 20);
insert into product(name, type_id, expired_date, price) values
('Сыр моцарелла', 1, '25.06.2024', 200);
insert into product(name, type_id, expired_date, price) values
('Сыр французский', 1, '27.06.2024', 300);
insert into product(name, type_id, expired_date, price) values
('Кефир', 2, '24.06.2024', 80);
insert into product(name, type_id, expired_date, price) values
('Молоко пастеризованное', 2, '30.06.2024', 100);
insert into product(name, type_id, expired_date, price) values
('Ряженка', 2, '26.06.2024', 80);
insert into product(name, type_id, expired_date, price) values
('Фруктовое мороженое', 3, '01.07.2024', 35);
insert into product(name, type_id, expired_date, price) values
('мороженое "Рожок"', 3, '02.07.2024', 40);
insert into product(name, type_id, expired_date, price) values
('Шмурдяк', 4, '01.07.2027', 100);
insert into product(name, type_id, expired_date, price) values
('"Смирнoff"', 4, '03.07.2028', 200);
insert into product(name, type_id, expired_date, price) values
('"Старина Джек"', 4, '04.07.2028', 300);
insert into product(name, type_id, expired_date, price) values
('Хлеб "Стахановский"', 5, '28.06.2024', 20);
insert into product(name, type_id, expired_date, price) values
('Хлеб "Горожанин"', 5, '29.06.2024', 30);
insert into product(name, type_id, expired_date, price) values
('Хлеб для тостера', 5, '30.06.2024', 50);

/*
1) Написать запрос получение всех продуктов с типом "СЫР"
*/
select p.name, t.name
from product p join type t on p.type_id = t.id
where t.name = 'СЫР'; 

/*
2) Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
*/
select * from product where name like '%мороженое%';

/*
3) Написать запрос, который выводит все продукты, срок годности которых уже истек
*/
select * from product where expired_date < current_date;

/*
4)  Написать запрос, который выводит самый дорогой продукт. 
    Запрос должен быть универсальный и находить все продукты с максимальной ценой
*/
select * from product where price = (select max(price) from product); 

/*
5)  Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. 
    В виде имя_типа, количество
*/
select t.name, count(p.type_id)
from product p join type t on p.type_id = t.id
group by t.name;

/*
6)  Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
*/
select p.name
from product p join type t on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

/*
7)  Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук
*/
select t.name from type t where t.id < 10;

/*
8)  Вывести все продукты и их тип.
*/
select p.name, t.name
from product p join type t on p.type_id = t.id;