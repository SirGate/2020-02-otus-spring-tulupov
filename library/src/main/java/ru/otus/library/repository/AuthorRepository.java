package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, String> {
    public Optional<Author> findBySurnameAndName(String surname, String name);
}
