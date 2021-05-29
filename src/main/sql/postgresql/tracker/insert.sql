insert into role(name) values('Student');
insert into users(name, role_id) values('Kirill Malikov', 1);
insert into rules(rule) values('SOME_TEXT');
insert into state(name) values('completed');
insert into category(name) values('urgent');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into item(name, users_id, category_id, state_id) values('item1', 1, 2, 3);
insert into comments(text, item_id) values('comment', 1);
insert into attachs(name, item_id) values('comment', 1);