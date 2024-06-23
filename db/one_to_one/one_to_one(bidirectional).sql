create table car(
    id serial primary key,
    serial_number int,
    number varchar(255)
);

create table owner(
    id serial primary key,
    name varchar(255)
);

create table car_owner(
    id serial primary key,
    car_id int references car(id) unique,
    owner_id int references owner(id) unique
);