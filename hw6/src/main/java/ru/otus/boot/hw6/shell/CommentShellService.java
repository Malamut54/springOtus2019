package ru.otus.boot.hw6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.boot.hw6.model.Comment;
import ru.otus.boot.hw6.service.comment.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellService {
    private final CommentService commentService;

    @ShellMethod("Add Comment")
    public void addComment(Long idBook, String comment) {
        commentService.add(comment, idBook);
    }

    @ShellMethod("Get comments for book")
    public List<Comment> getComment(Long id) {
        return commentService.getAll(id);
    }

}
