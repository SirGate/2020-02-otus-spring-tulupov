package ru.otus.library.dao;

import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    long count();

    Author insert(Author author);

    void update(Author author, String nameNew, String surnameNew);

    Optional<Author> getBySurnameAndName(String name, String surname);

    List<Author> getAll();

    void deleteBySurnameAndName(String name, String surname);
}
