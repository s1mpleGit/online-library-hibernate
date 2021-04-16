CREATE TABLE ROLES
(
    id   int not null auto_increment,
    role varchar(20),
    PRIMARY KEY (id)
);

CREATE TABLE STATUSES
(
    id     int not null auto_increment,
    status varchar(30),
    PRIMARY KEY (id)
);

CREATE TABLE USERS
(
    id           int         not null auto_increment,
    login        varchar(20) NOT NULL,
    password     varchar(20) NOT NULL,
    name         varchar(20) NOT NULL,
    surname      varchar(20) NOT NULL,
    email        varchar(30) NOT NULL,
    phone        varchar(12) NOT NULL,
    role         varchar(10) NOT NULL,
    status       varchar(10) NOT NULL,
    message      CLOB,
    PRIMARY KEY (id)
);

CREATE TABLE AUTHORS
(
    id   int         not null auto_increment,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE BOOKS
(
    id          int          not null auto_increment,
    title       varchar(100) NOT NULL,
    author_id   int          NOT NULL,
    description CLOB         NOT NULL,
    imageUri    CLOB         NOT NULL,
    return_date date         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES AUTHORS (id)
);

-- CREATE TABLE CARDS
-- (
--     id          int          not null auto_increment,
--     user_id     int          NOT NULL,
--     book_id     int          NOT NULL,
--     return_date date         NOT NULL,
--     PRIMARY KEY (id),
--     FOREIGN KEY (user_id) REFERENCES USERS (id),
--     FOREIGN KEY (book_id) REFERENCES BOOKS (id)
-- );