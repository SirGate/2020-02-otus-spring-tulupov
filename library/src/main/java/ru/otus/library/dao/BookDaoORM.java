package ru.otus.library.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;


import javax.persistence.*;
import java.util.*;

@Service
public class BookDaoORM implements BookDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public long count() {
        Query query = em.createQuery("select count(b) from Book b");
        return (long) query.getSingleResult();
    }

    @Transactional
    @Override
    public Book insert(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional
    @Override
    public void update(long id, String titleNew) {
        Book editedBook = em.find(Book.class, id);
        editedBook.setTitle(titleNew);
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Optional<Book> book = getById(id);
        if (book.isPresent()) {
            em.remove(book.get());
        }
    }

    @Transactional
    @Override
    public Optional<List<Comment>> getAllComments(Book book) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book = :book",
                Comment.class);
        query.setParameter("book", book);
        return Optional.ofNullable(query.getResultList());
    }
}
