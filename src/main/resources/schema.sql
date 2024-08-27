CREATE TABLE if not exists USERS
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    username     VARCHAR(255),
    email        VARCHAR(255),
    enabled      boolean            default true,
    authority_id BIGINT    NOT NULL,
    password     VARCHAR(255),
    phone        VARCHAR(15),
    authority    VARCHAR(255),
    creationDate TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP(),
    foreign key (authority_id) references AUTHORITIES (id)
);

CREATE TABLE IF NOT EXISTS AUTHORITIES
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    authority VARCHAR(255)
)