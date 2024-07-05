/*
1)
*/
CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers (first_name, last_name, age, country) 
values('Сергей', 'Петров', 35, 'united_kingdom');
insert into customers (first_name, last_name, age, country) 
values('Иван', 'Баширов', 37, 'usa');
insert into customers (first_name, last_name, age, country) 
values('Андрей', 'Ермолаев', 25, 'russian federation');
insert into customers (first_name, last_name, age, country) 
values('Николай', 'Голопупенко', 27, 'ukraine');
insert into customers (first_name, last_name, age, country) 
values('Sari', 'Hangu', 23, 'india');

select * from customers where age = (select min(age) from customers);

/*
2)
*/
CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders (amount, customer_id) 
values(300, 1);
insert into orders (amount, customer_id) 
values(350, 2);

select * from customers where customers.id not in (select customer_id from orders);