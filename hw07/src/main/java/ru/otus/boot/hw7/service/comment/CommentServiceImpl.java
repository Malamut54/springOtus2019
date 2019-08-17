package ru.otus.boot.hw7.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw7.exception.ExistEntityException;
import ru.otus.boot.hw7.model.Comment;
import ru.otus.boot.hw7.repo.BookRepository;
import ru.otus.boot.hw7.repo.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void add(String comment, Long bookId) {
        commentRepository.save(new Comment(comment, bookRepository
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
