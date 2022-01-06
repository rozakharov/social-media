CREATE TABLE IF NOT EXISTS user
(
    id        bigint      NOT NULL AUTO_INCREMENT,
    email     varchar(45) not null,
    password  varchar(64) not null,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    age       int         null,
    sex       varchar(20)  null,
    hobby     text        null,
    city      varchar(255) null,
    PRIMARY KEY (`id`),
    UNIQUE (`email`)
);

CREATE TABLE IF NOT EXISTS user_friend
(
    user_id   bigint not null,
    friend_id bigint not null,
    PRIMARY KEY (`user_id`, `friend_id`)
);