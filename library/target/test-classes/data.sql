insert into genres (id, `Description`) values (1, 'Science fiction');
insert into genres (id, `Description`) values (2, 'Fairytales');
insert into genres (id, `Description`) values (3, 'Adventures');

insert into authors (id, `Name`, `Surname`) values (1, 'Stanislav', 'Lem');
insert into authors (id, `Name`, `Surname`) values (2, 'Gianni', 'Rodari');
insert into authors (id, `Name`, `Surname`) values (3, 'Mark', 'Twain');

insert into books (id, `title`, `author_id`, `genre_id`)
values (1, 'Solaris', 1, 1);
insert into books (id, `title`, `author_id`, `genre_id`)
values (2, 'Huckleberry Finn', 3, 3);

insert into books_authors (book_id, author_id)
values (1, 1), (2, 3);

insert into comments (id, `text`, `book_id`) values (1, 'It is  a good book', 1);
insert into comments (id, `text`, `book_id`) values (2, 'It is a bad book', 1);
insert into comments (id,  `text`, `book_id`) values (3, 'Brilliant book!', 1);
