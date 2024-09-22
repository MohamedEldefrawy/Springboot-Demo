CREATE TABLE if not exists USERS
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    username     VARCHAR(255),
    email        VARCHAR(255),
    enabled      boolean            default true,
    password     VARCHAR(255),
    phone        VARCHAR(15),
    csrfToken    VARCHAR(15),
    creationDate TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE IF NOT EXISTS AUTHORITIES
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    authority VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS CSRF_TOKENS
(
    token        VARCHAR(255) PRIMARY KEY,
    creationDate TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE IF NOT EXISTS USERS_AUTHORITIES
(
    user_id      BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    primary key (user_id, authority_id),
    foreign key (authority_id) references AUTHORITIES (id),
    foreign key (user_id) references USERS (id)
);

CREATE TABLE IF NOT EXISTS PRODUCTS
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT       NOT NULL,
    foreign key (user_id) references USERS (id)
);

