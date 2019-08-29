package ru.otus.boot.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.bookstore.exception.ExistEntityException;
import ru.otus.boot.bookstore.model.Comment;
import ru.otus.boot.bookstore.repo.BookRepository;
import ru.otus.boot.bookstore.repo.CommentRepository;
import ru.otus.boot.bookstore.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void add(String comment, String bookId) {
        commentRepository.insert(new Comment(comment, Optional.ofNullable(bookRepository
                .getBooksById(bookId))
                .orElseThrow(() -> new ExistEntityException("Book with bookId = " + bookId + "not found"))));
    }

    @Override
    public List<Comment> getAll(String bookId) {
        return commentRepository.findAllByBook(Optional.ofNullable(bookRepository.getBooksById(bookId))
                .orElseThrow(() -> new ExistEntityException("Book with bookId = " + bookId + "not found")));
    }
}
