package ru.otus.boot.hw7.service.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.hw7.model.Author;
import ru.otus.boot.hw7.model.Book;
import ru.otus.boot.hw7.model.Comment;
import ru.otus.boot.hw7.model.Genre;
import ru.otus.boot.hw7.service.book.BookService;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Transactional
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BookService bookService;
    private Book testBook = new Book("Some Book", new Author("Some", "Author"), new Genre("genre"));

    @Test
    public void createTwoCommentsAndGetIt() {
        bookService.insert(testBook);
        commentService.add("first comment", testBook.getId());
        commentService.add("second comment", testBook.getId());
        assertThat(commentService.getAll(testBook.getId())
                .stream()
                .map(Comment::getComment)
                .collect(Collectors.toList()))
                .containsExactlyInAnyOrder("first comment", "second comment");
    }
}