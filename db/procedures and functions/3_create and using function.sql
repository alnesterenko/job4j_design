create
or replace function delete_if_not_enough(u_limiter integer)
returns integer
language 'plpgsql'
as
$$
    declare
        start_count integer;
    begin
	select count(*)
	into start_count from products;
        if u_limiter > 0 THEN
        delete from products
        where count <= u_limiter;
        end if;        
        return start_count - count(*);
    end;
$$;

select delete_if_not_enough(20);