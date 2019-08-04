package ru.otus.boot.hw6.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw6.model.Comment;
import ru.otus.boot.hw6.repo.BookRepository;
import ru.otus.boot.hw6.repo.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void add(String comment, Long id) {
        commentRepository.add(new Comment(comment, bookRepository.getById(id)));
    }

    @Override
    public List<Comment> getAll(Long bookId) {
        return commentRepository.getAll(bookRepository.getById(bookId));
    }
}
