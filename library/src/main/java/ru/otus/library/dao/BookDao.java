package ru.otus.library.dao;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    long count();

    Book insert(Book book);

    void update(long id, String titleNew);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
