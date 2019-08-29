package ru.otus.boot.bookstore.service;

import ru.otus.boot.bookstore.model.Comment;

import java.util.List;

public interface CommentService {
    void add(String comment, String id);

    List<Comment> getAll(String bookId);
}
