package ru.otus.library.dao;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    void insert(Author author);

    void update(Author author, String nameNew, String surnameNew);

    Author getBySurnameAndName(String name, String surname);

    List<Author> getAll();

    void deleteBySurnameAndName(String name, String surname);
}
