package ru.otus.boot.hw6.repo.impl;

import org.springframework.stereotype.Repository;
import ru.otus.boot.hw6.model.Author;
import ru.otus.boot.hw6.repo.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorRepositoryJpaImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public List<Author> getByFirstName(String firstName) {
        String sql = "select a from Author a where a.firstName = :firstName";
        TypedQuery<Author> query = em.createQuery(sql, Author.class);
        return query.setParameter("firstName", firstName).getResultList();
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        String sql = "select a from Author a where a.lastName = :lastName";
        TypedQuery<Author> query = em.createQuery(sql, Author.class);
        return query.setParameter("lastName", lastName).getResultList();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }


    @Override
    public void delete(String firstName, String lastName) {
        String sql = "delete from Author a where a.firstName = :firstName and a.lastName = :lastName";
        Query query = em.createQuery(sql);
        query.setParameter("firstName", firstName).setParameter("lastName", lastName).executeUpdate();
    }

    @Override
    public boolean isExist(String firstName, String lastName) {
        String sql = "select case when (count(*) > 0) then true else false end " +
                "from Author a where a.firstName = :firstName and a.lastName = :lastName";
        TypedQuery<Boolean> query = em.createQuery(sql, Boolean.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);
        return query.getSingleResult();
    }
}
