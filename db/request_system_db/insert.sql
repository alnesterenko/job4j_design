insert into roles(name) values('customer');

insert into users(name, roles_id) values('customer', 1);

insert into rules(name) values('customer rights');

insert into states(name) values('in progres');

insert into categories(name) values('immediately');

insert into items(name, users_id, categories_id, states_id) values('buy tables', 1, 1, 1);

insert into comments_table(name, items_id) values('"Гадость! Подлость! Ненавижу! Убирайтесь!"(В.В.Жириновский)', 1);

insert into attachs(name, items_id) values('Фото: В.В.Жириновский в ГД', 1);

insert into roles_rules(roles_id, rules_id) values(1, 1);