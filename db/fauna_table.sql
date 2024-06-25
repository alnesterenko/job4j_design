create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values('Муха-цокотуха', 100, '01.09.1923');

insert into fauna(name, avg_age, discovery_date)
values('swordfish', 6000, '01.01.1758');

insert into fauna(name, avg_age, discovery_date)
values('blobfish', 5000, '01.01.1926');

insert into fauna(name, avg_age, discovery_date)
values('шимпанза', 20000, null);

insert into fauna(name, avg_age, discovery_date)
values('черепадло', 11000, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '01.01.1950';