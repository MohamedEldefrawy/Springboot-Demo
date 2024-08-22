CREATE TABLE if not exists USERS
(
    id           identity  not null PRIMARY KEY,
    name         VARCHAR(255),
    email        VARCHAR(255),
    password     VARCHAR(255),
    phone        VARCHAR(15),
    authority    VARCHAR(255),
    creationDate TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP()
);