package ru.otus.library.dao;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Genre;

import javax.persistence.*;
import java.util.*;

@Service
public class GenreDaoORM implements GenreDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public long count() {
        Query query = em.createQuery("select count(g.id) from Genre g");
        return (long) query.getSingleResult();
    }

    @Transactional
    @Override
    public Genre insert(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Transactional
    @Override
    public void update(Genre genre, String descriptionNew) {
        Genre editedGenre = em.find(Genre.class, genre.getId());
        editedGenre.setDescription(descriptionNew);
    }

    @Override
    public Optional<Genre> getByDescription(String description) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g " +
                "where g.description = :description", Genre.class);
        query.setParameter("description", description);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteByDescription(String description) {
        Optional<Genre> genre = getByDescription(description);
        if (genre.isPresent()) {
            em.remove(genre.get());
        }
    }
}
