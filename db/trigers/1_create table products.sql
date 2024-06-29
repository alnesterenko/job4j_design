create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);