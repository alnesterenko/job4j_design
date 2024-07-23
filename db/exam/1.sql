/*
1. В одном запросе получить

- имена всех person, которые не состоят в компании с id = 5;

- название компании для каждого человека.
*/

select p.name, c.name from 
company c join person p on p.company_id = c.id
where c.id != 5;