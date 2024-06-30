create
or replace procedure delete_last_string()
language 'plpgsql'
as $$
    BEGIN
        if  count(*) > 0 THEN
            delete from products
            where id = (select max(id) from products);
        end if;
    END;
$$;

call delete_last_string();