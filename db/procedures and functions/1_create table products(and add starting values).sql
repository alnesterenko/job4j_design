create table products
(
     id       serial primary key,
     name     varchar(50),
     producer varchar(50),
     count    integer default 0,
     price    integer
);
insert into products(name, producer, count, price) values ('product_88', 'some_producer', 88, 100);
insert into products(name, producer, count, price) values ('product_99', 'some_producer', 1, 99);
insert into products(name, producer, count, price) values ('product_77', 'some_producer', 3, 101);
insert into products(name, producer, count, price) values ('product_66', 'some_producer', 67, 102);