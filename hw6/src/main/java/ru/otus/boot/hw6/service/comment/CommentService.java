package ru.otus.boot.hw6.service.comment;

import ru.otus.boot.hw6.model.Comment;

import java.util.List;

public interface CommentService {
    void add(String comment, Long id);

    List<Comment> getAll(Long bookId);
}
