package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Book;

public interface BookRepository extends CrudRepository<Book, String> {
}
