CREATE TABLE USERS
(
    id           SERIAL      not null,
    login        varchar(20) NOT NULL,
    password     varchar(20) NOT NULL,
    name         varchar(20) NOT NULL,
    surname      varchar(20) NOT NULL,
    email        varchar(30) NOT NULL,
    phone        varchar(13) NOT NULL,
    role         varchar(10) NOT NULL,
    status       varchar(10) NOT NULL,
    date_penalty date,
    message      text,
    PRIMARY KEY (id)
);

CREATE TABLE AUTHORS
(
    id   SERIAL      not null,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE BOOKS
(
    id          SERIAL       not null,
    title       varchar(100) NOT NULL,
    author_id   int          NOT NULL,
    description text         NOT NULL,
    imageUri    text         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES AUTHORS (id)
);

CREATE TABLE CARDS
(
    id          SERIAL       not null,
    user_id     int          NOT NULL,
    book_id     int          NOT NULL,
    return_date date         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES USERS (id),
    FOREIGN KEY (book_id) REFERENCES BOOKS (id)
);