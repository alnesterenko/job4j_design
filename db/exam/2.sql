/*
2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
Нужно учесть, что таких компаний может быть несколько.
*/

select c.name, count(p.company_id) from 
company c join person p on p.company_id = c.id
group by c.name
order by count(p.company_id) desc
limit 1;