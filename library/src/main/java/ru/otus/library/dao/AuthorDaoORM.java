package ru.otus.library.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;


import javax.persistence.*;
import java.util.*;


@Service
public class AuthorDaoORM implements AuthorDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public long count() {
        Query query = em.createQuery("select count(a) from Author a");
        return (long) query.getSingleResult();
    }

    @Transactional
    @Override
    public Author insert(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Transactional
    @Override
    public void update(Author author, String nameNew, String surnameNew) {
        Author editedAuthor = em.find(Author.class, author.getId());
        editedAuthor.setName(nameNew);
        editedAuthor.setSurname(surnameNew);
    }

    @Override
    public Optional<Author> getBySurnameAndName(String name, String surname) {
        TypedQuery<Author> query = em.createQuery("select a from Author a " +
                "where a.name = :name and a.surname = :surname", Author.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteBySurnameAndName(String name, String surname) {

        Optional<Author> author = getBySurnameAndName(name, surname);
        if (author.isPresent()) {
            em.remove(author.get());
        }
    }
}
