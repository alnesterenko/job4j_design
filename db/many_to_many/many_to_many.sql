create table substance(
     id serial primary key,
     name varchar(255)
 );
 
 create table chemical_element(
     id serial primary key,
     name varchar(255)
 );
 
 create table substance_chimical_element(
     id serial primary key,
     substance_id int references substance(id),
     chemical_element_id int references chemical_element(id)
 );