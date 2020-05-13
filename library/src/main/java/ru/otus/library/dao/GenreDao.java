package ru.otus.library.dao;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    void insert(Genre genre);

    void update(Genre genre, String descriptioNew);

   Genre getByDescription(String description);

    List<Genre> getAll();

    void deleteByDescription(String description);
}
