create table offices(
    id serial primary key,
    office_area_in_meters int
);

create table clerks(
    id serial primary key,
    name varchar(255),
    office_id int references offices(id)
);