package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {
    public Optional<Author> findBySurnameAndName(String surname, String name);

    public Optional<List<Book>> removeBookArrayElementsById();
}
