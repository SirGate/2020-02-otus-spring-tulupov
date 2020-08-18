package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Genre;

import java.util.Optional;


public interface GenreRepository extends MongoRepository<Genre, String> {
    public Optional<Genre> getByDescription(String description);
}
