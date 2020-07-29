package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Genre;

import java.util.Optional;


public interface GenreRepository extends CrudRepository<Genre, String> {
    public Optional<Genre> getByDescription(String description);
   }
