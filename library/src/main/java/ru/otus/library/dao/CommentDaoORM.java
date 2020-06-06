package ru.otus.library.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import javax.persistence.*;
import java.util.*;

@Transactional
@Service
public class CommentDaoORM implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count() {
        Query query = em.createQuery("select count(c.id) from Comment c");
        return (long) query.getSingleResult();
    }

    @Override
    public Optional<List<Comment>> getAll(Book book) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book = :book", Comment.class);
        query.setParameter("book", book);
        return Optional.ofNullable(query.getResultList());
    }

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

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
