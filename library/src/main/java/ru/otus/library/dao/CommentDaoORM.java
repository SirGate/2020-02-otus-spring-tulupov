package ru.otus.library.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Comment;

import javax.persistence.*;
import java.util.*;

@Service
public class CommentDaoORM implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        Query query = em.createQuery("select count(c.id) from Comment c");
        return (long) query.getSingleResult();
    }

    @Transactional
    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> getById(long id) {

        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Optional<Comment> comment = getById(id);
        if (comment.isPresent()) {
            em.remove(comment.get());
        }
    }
}