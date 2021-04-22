package ru.otus.library.service;

import ru.otus.library.domain.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public Book getBookById(String id);

    public Book createBook(Book entity);

    public Book updateBook(Book entity);

    public void deleteBook(String id);
}
