package ru.otus.boot.bookstore.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boot.bookstore.model.Book;
import ru.otus.boot.bookstore.model.Comment;

import java.util.List;

@Transactional
@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);
}
