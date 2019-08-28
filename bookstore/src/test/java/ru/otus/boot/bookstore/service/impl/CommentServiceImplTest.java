package ru.otus.boot.bookstore.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.bookstore.model.Author;
import ru.otus.boot.bookstore.model.Book;
import ru.otus.boot.bookstore.model.Comment;
import ru.otus.boot.bookstore.model.Genre;
import ru.otus.boot.bookstore.service.BookService;
import ru.otus.boot.bookstore.service.CommentService;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BookService bookService;
    private Book testBook = new Book("Some Book", new Author("Some", "Author"), new Genre("genre"));

    @BeforeEach
    public void prepare() {
        bookService.insert(testBook);
    }

    @AfterEach
    public void del() {
        bookService.delete(testBook);
    }

    @Test
    public void createTwoCommentsAndGetIt() {
        commentService.add("first comment", testBook.getId());
        commentService.add("second comment", testBook.getId());
        assertThat(commentService.getAll(testBook.getId())
                .stream()
                .map(Comment::getComment)
                .collect(Collectors.toList()))
                .containsExactlyInAnyOrder("first comment", "second comment");
    }
}