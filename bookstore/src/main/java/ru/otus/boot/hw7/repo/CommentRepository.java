package ru.otus.boot.hw7.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.boot.hw7.model.Book;
import ru.otus.boot.hw7.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);
}
