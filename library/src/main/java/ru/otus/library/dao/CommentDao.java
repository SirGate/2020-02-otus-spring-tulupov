package ru.otus.library.dao;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    long count();

    Optional<List<Comment>> getAll(Book book);

    Comment insert(Comment comment);

    Optional<Comment> getById(long id);

    void deleteById(long id);
}
