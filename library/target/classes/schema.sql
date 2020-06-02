DROP TABLE IF EXISTS genres;
CREATE TABLE genres(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
description VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
surname VARCHAR (255)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
author_id BIGINT,
genre_id BIGINT,
title VARCHAR(255),
FOREIGN KEY (author_id) references authors (ID) ON DELETE SET NULL,
FOREIGN KEY (genre_id) references genres (ID) ON DELETE SET NULL
 );

DROP TABLE IF EXISTS comments;
CREATE TABLE comments(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
book_id BIGINT,
text VARCHAR(400),
FOREIGN KEY (book_id) references books (ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS books_authors;
CREATE TABLE books_authors(
book_id BIGINT,
author_id BIGINT,
FOREIGN KEY (book_id) references books (id) ON DELETE SET NULL,
FOREIGN KEY (author_id) references authors (id) ON DELETE SET NULL
);