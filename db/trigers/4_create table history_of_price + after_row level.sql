/*
3) Создайте таблицу: history_of_price ....
     Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
 будет заносить имя, цену и текущую дату в таблицу history_of_price. 
*/

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function save_history_to_table()
    returns trigger as
$$
    BEGIN
        insert into history_of_price
        (name, price, date)
        values(new.name, new.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger save_history
    after insert
    on products
    for each row
    execute procedure save_history_to_table();

/*
И конечно же проверка:
*/

insert into products (name, producer, count, price)
VALUES ('product_15', 'producer_15', 1, 100);

select * from history_of_price;