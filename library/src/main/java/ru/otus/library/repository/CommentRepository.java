package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import java.util.Optional;


public interface CommentRepository extends MongoRepository<Comment, String> {
   public Optional<Comment> findByBook(Book book);
}
