package ru.otus.library.dao;

import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    long count();

    Book insert(Book book);

    void update(long id, String titleNew);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    Optional<List<Comment>> getAllComments(Book book);
}
