package ru.otus.library.service;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreService {
    public Genre getGenreById(String id);

    public List<Genre> getAllGenres();

    public Genre saveGenre(String description);

    public Genre updateGenre(Genre entity);

    public void deleteGenre(String id);
}
