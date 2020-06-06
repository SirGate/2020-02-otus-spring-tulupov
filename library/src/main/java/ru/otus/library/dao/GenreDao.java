package ru.otus.library.dao;

import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    long count();

    Genre insert(Genre genre);

    void update(Genre genre, String descriptioNew);

    Optional<Genre> getByDescription(String description);

    List<Genre> getAll();

    void deleteByDescription(String description);
}
