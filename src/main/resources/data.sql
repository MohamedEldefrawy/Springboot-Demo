insert into AUTHORITIES(id, authority)
values (1, 'can-read-users');

insert into USERS (id, username, email, password, authority_id, enabled, phone, creation_date)
values (1, 'Mohamed', 'modafro', '12345', 1, true, '01111111111', CURRENT_TIMESTAMP());
