package ru.otus.library.service;

import ru.otus.library.domain.Author;
import java.util.List;

public interface AuthorService {
    public List<Author> getAllAuthors();

    public Author getAuthorById(String id);

    public Author saveAuthor(String name, String surname);

    public Author updateAuthor(Author entity);

    public void deleteAuthor(String id);
}
