CREATE TABLE if not exists USERS
(
    id         identity not null PRIMARY KEY,
    name       VARCHAR(255),
    email      VARCHAR(255),
    phone      VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);