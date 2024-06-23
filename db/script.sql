create table animals(
	id serial primary key,
	name varchar(255),
	weight int,
	area text,
	eatspeople bool
);
insert into animals(name, weight, area, eatspeople) values('white shark', 1000, 'all the major oceans', true);
update animals set area = 'all the major oceans(except the northern ocean)';
delete from animals;