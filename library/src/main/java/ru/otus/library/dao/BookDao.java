package ru.otus.library.dao;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    void insert(Book book);

    void update(Author author, String titleNew);

    Book getById(long id);

    List<Book> getAll();

    void deleteByTitleAndAuthor(String title, Author author);
}
