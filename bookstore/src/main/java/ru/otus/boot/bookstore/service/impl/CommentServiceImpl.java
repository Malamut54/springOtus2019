package ru.otus.boot.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.bookstore.exception.ExistEntityException;
import ru.otus.boot.bookstore.model.Comment;
import ru.otus.boot.bookstore.repo.BookRepository;
import ru.otus.boot.bookstore.repo.CommentRepository;
import ru.otus.boot.bookstore.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void add(String comment, Long bookId) {
        commentRepository.insert(new Comment(comment, bookRepository
                .findById(bookId)
                .orElseThrow(() -> new ExistEntityException("Book with bookId = " + bookId + "not found"))));
    }

    @Override
    public List<Comment> getAll(Long bookId) {
        return commentRepository.findAllByBook(bookRepository
                .findById(bookId)
                .orElseThrow(() -> new ExistEntityException("Book with bookId = " + bookId + "not found")));
    }
}
