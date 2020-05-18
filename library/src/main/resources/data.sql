insert into genres (id, `Description`) values (1, 'Science fiction');
insert into genres (id, `Description`) values (2, 'Fairytales');
insert into genres (id, `Description`) values (3, 'Adventures');
insert into authors (id, `Name`, `Surname`) values (1, 'Stanislav', 'Lem');
insert into authors (id, `Name`, `Surname`) values (2, 'Gianni', 'Rodari');
insert into authors (id, `Name`, `Surname`) values (3, 'Mark', 'Twain');
insert into books (id, `Title`, `AuthorID`,`AuthorName`, `AuthorSurname`, `GenreID`, `GenreDescription`)
values (1, 'Solaris', 1, 'Stanislav', 'Lem', 1, 'Science fiction');
insert into books (id, `Title`, `AuthorID`,`AuthorName`, `AuthorSurname`, `GenreID`, `GenreDescription`)
values (3, 'Huckleberry Finn', 3, 'Mark', 'Twain', 3, 'Adventures');