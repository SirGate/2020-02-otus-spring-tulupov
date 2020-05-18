DROP TABLE IF EXISTS Genres;
CREATE TABLE Genres(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
Description VARCHAR(255) UNIQUE
);
DROP TABLE IF EXISTS Authors;
CREATE TABLE Authors(
ID BIGINT PRIMARY KEY AUTO_INCREMENT ,
Name VARCHAR(255),
Surname VARCHAR (255)
);
DROP TABLE IF EXISTS Books;
CREATE TABLE Books(
ID BIGINT PRIMARY KEY AUTO_INCREMENT,
AuthorID BIGINT,
AuthorName VARCHAR(255),
AuthorSurname VARCHAR(255),
GenreID BIGINT,
GenreDescription VARCHAR(255),
Title VARCHAR(255),
FOREIGN KEY (AuthorID, AuthorName, AuthorSurname) references Authors (ID, Name, Surname) ON DELETE SET NULL,
FOREIGN KEY (GenreID, GenreDescription) references Genres (ID, Description) ON DELETE SET NULL
 );