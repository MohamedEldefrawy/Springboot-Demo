insert into AUTHORITIES(id, authority)
values (1, 'can-read-users'),
       (2, 'can-create-users');

insert into USERS (id, username, email, password, enabled, phone, creation_date)
values (1, 'Mohamed', 'modafro', '12345', true, '01111111111', CURRENT_TIMESTAMP()),
       (2, 'Ahmed', 'mo', '12345', true, '022222222222', CURRENT_TIMESTAMP());

insert into USERS_AUTHORITIES(user_id, authority_id)
values (1, 1),
       (1, 2),
       (2, 1);

insert into products(id, name,user_id)
values (1,'glasses', 1),
       (2, 'book',2);
