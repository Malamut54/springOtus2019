package ru.otus.boot.hw6.repo.impl;

import org.springframework.stereotype.Repository;
import ru.otus.boot.hw6.model.Book;
import ru.otus.boot.hw6.model.Comment;
import ru.otus.boot.hw6.repo.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentRepositoryJpaImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Comment comment) {
        em.persist(comment);
    }


    @Override
    public List<Comment> getAll(Book book) {
        String sql = "select c from Comment c inner join c.book where c.book.id = :id";
        TypedQuery<Comment> query = em.createQuery(sql, Comment.class);
        query.setParameter("id", book.getId());
        List<Comment> resultList = query.getResultList();
        return resultList;
    }
}
