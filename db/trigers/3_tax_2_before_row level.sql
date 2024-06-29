/*
2) Триггер должен срабатывать до вставки данных 
и насчитывать налог на товар (нужно прибавить налог к цене товара).
Здесь используем row уровень.
*/

create
or replace function tax_2()
    returns trigger as
$$
    BEGIN
	new.price = new.price + new.price * 0.5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create trigger tax_2
    before insert
    on products
    for each row
    execute procedure tax_2();



