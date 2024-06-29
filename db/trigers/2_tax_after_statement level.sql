/*
1) Триггер должен срабатывать после вставки данных,
для любого товара и просто насчитывать налог на товар
(нужно прибавить налог к цене товара). 
Действовать он должен не на каждый ряд,
а на запрос (statement уровень)
*/

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();


/*
И обязательная проверка работы скрипта
*/
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 50);

select * from products;
/*
Как и ожидалось, на "старую" запись, налог не был насчитан. Отлично! 
*/
