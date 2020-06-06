package ru.otus.library.dao;

import ru.otus.library.domain.Comment;

import java.util.Optional;

public interface CommentDao {
    long count();

    Comment insert(Comment comment);

    Optional<Comment> getById(long id);

    void deleteById(long id);
}
