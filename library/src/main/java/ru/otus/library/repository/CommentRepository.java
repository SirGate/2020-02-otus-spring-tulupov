package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Comment;


public interface CommentRepository  extends CrudRepository<Comment, String> {
   }
