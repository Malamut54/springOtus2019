package ru.otus.boot.hw6.repo.impl;

import org.springframework.stereotype.Repository;
import ru.otus.boot.hw6.model.Book;
import ru.otus.boot.hw6.repo.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookRepositoryJpaImpl implements BookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public List<Book> getByFirstName(String firstName) {
        String sql = "select b from Book b where b.author.firstName = :firstName";
        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        return query.setParameter("firstName", firstName).getResultList();
    }

    @Override
    public List<Book> getByLastName(String lastName) {
        String sql = "select b from Book b where b.author.lastName = :lastName";
        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        return query.setParameter("lastName", lastName).getResultList();
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public List<Book> getByTitle(String bookName) {
        String sql = "select b from Book b where b.bookName = :bookName";
        TypedQuery<Book> query = em.createQuery(sql, Book.class);
        return query.setParameter("bookName", bookName).getResultList();

    }

    @Override
    public void delete(String bookName) {
        String sql = "delete from Book b where b.bookName = :bookName";
        Query query = em.createQuery(sql);
        query.setParameter("bookName", bookName).executeUpdate();
    }

    @Override
    public boolean isExist(String bookName, String firstName, String lastName, String genre) {
        String sql = "select case when (count(*) > 0) then true else false end" +
                " from Book b where b.bookName = :bookName " +
                "and b.author.firstName = :firstName " +
                "and b.author.lastName = :lastName " +
                "and b.genre.genreName = :genreName";
        TypedQuery<Boolean> query = em.createQuery(sql, Boolean.class)
                .setParameter("bookName", bookName)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("genreName", genre);
        return query.getSingleResult();
    }

    @Override
    public Book getById(Long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.id = :id", Book.class);
        return query.setParameter("id", id).getSingleResult();
    }
}
