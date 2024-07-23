CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name)
values (1, 'Samsung'), (2, 'Sony'), (3, 'LG'), (4, 'Huavey'), (5, 'ZTE'), (6, 'HP');

insert into person (id, name, company_id) 
values (1, 'John Doe', 1), (2, 'Jax Bridg', 1), (3, 'Emmet Broun', 2), (4, 'Kung Lao', 3), (5, 'Edd Boon', 4), 
(6, 'John Tobias', 5), (7, 'Eddy Cordo', 5), (8, 'Sall Divita', 6);