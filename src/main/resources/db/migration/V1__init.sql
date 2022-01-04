CREATE TABLE IF NOT EXISTS user
(
    id       bigint   NOT NULL AUTO_INCREMENT,
    login    tinytext not null,
    password tinytext not null,
    name     tinytext not null,
    surname  tinytext not null,
    age      int      null,
    sex      boolean  null,
    hobby    text     null,
    city     tinytext null,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS friend
(
    user_id   bigint not null,
    friend_id bigint not null,
    PRIMARY KEY (`user_id`, `friend_id`)
);