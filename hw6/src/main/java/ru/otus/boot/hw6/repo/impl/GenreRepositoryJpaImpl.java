package ru.otus.boot.hw6.repo.impl;

import org.springframework.stereotype.Repository;
import ru.otus.boot.hw6.model.Genre;
import ru.otus.boot.hw6.repo.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GenreRepositoryJpaImpl implements GenreRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Genre getByTitle(String genreName) {
        String sql = "select g from Genre g where g.genreName = :genreName";
        TypedQuery<Genre> query = em.createQuery(sql, Genre.class);
        return query.setParameter("genreName", genreName).getSingleResult();
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void delete(String genreName) {
        String sql = "delete from Genre g where g.genreName = :genreName";
        em.createQuery(sql).setParameter("genreName", genreName).executeUpdate();

    }

    @Override
    public boolean isExist(String genreName) {
        String sql = "select case when (count(*) > 0) then true else false end " +
                "from Genre g where g.genreName = :genreName";
        return em.createQuery(sql, Boolean.class).setParameter("genreName", genreName).getSingleResult();
    }
}
