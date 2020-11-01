package ru.otus.library.service;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.rest.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public Book getBookById(String id);

    public Book createBook(Book entity);

    public Book updateBook(Book entity);

    public void deleteBook(String id);
}
