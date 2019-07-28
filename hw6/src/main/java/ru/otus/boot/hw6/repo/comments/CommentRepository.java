package ru.otus.boot.hw6.repo.comments;

import ru.otus.boot.hw6.model.Book;
import ru.otus.boot.hw6.model.Comment;

import java.util.List;

public interface CommentRepository {
    void add(Comment comment);

    List<Comment> getAll(Book book);
}
