package ru.otus.library.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;


import javax.persistence.*;
import java.util.*;

@Transactional
@Service
public class BookDaoORM implements BookDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public long count() {
        Query query = em.createQuery("select count(b) from Book b");
        return (long) query.getSingleResult();
    }

    @Override
    public Book insert(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void update(long id, String titleNew) {
        Query query = em.createQuery("update Book b " +
                "set b.title = :titleNew " +
                "where b.id = :id");
        query.setParameter("titleNew", titleNew);
        query.setParameter("id", id);
        query.executeUpdate();
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

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
