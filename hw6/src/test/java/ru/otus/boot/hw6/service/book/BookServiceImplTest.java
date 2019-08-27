package ru.otus.boot.hw6.service.book;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.hw6.exception.EmptyListException;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Author;
import ru.otus.boot.hw6.model.Book;
import ru.otus.boot.hw6.model.Genre;

import javax.transaction.Transactional;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Transactional
class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    @BeforeEach
    public void prepare() {
        bookService.insert(new Book("Sunshine", new Author("Stephen", "King"), new Genre("horrors")));
        bookService.insert(new Book("Gunslinger", new Author("Stephen", "King"), new Genre("roman")));
        bookService.insert(new Book("The Idiot", new Author("Fedor", "Dostoevskiy"), new Genre("classic")));
        bookService.insert(new Book("Crime and Punishment", new Author("Fedor", "Dostoevskiy"), new Genre("classic")));
        bookService.insert(new Book("Omon Ra", new Author("Viktor", "Pelevin"), new Genre("roman")));
        bookService.insert(new Book("Generation P", new Author("Viktor", "Pelevin"), new Genre("roman")));
    }

    @AfterEach
    public void del() {
        bookService.delete(new Book("Sunshine", new Author("Stephen", "King"), new Genre("horrors")));
        bookService.delete(new Book("Gunslinger", new Author("Stephen", "King"), new Genre("roman")));
        bookService.delete(new Book("The Idiot", new Author("Fedor", "Dostoevskiy"), new Genre("classic")));
        bookService.delete(new Book("Crime and Punishment", new Author("Fedor", "Dostoevskiy"), new Genre("classic")));
        bookService.delete(new Book("Omon Ra", new Author("Viktor", "Pelevin"), new Genre("roman")));
        bookService.delete(new Book("Generation P", new Author("Viktor", "Pelevin"), new Genre("roman")));
    }

    @Test
    @DisplayName("Get All book")
    public void testGetAllBooks() {
        assertThat(bookService.getAll().stream()
                .map(Book::getBookName)
                .collect(Collectors.toList()))
                .containsOnly("Sunshine",
                        "Gunslinger",
                        "The Idiot",
                        "Crime and Punishment",
                        "Omon Ra",
                        "Generation P");

    }

    @Test
    @DisplayName("Get book by genreName")
    public void testGetBooksByTitle() {
        assertThat(bookService.getByTitle("Sunshine").stream()
                .map(Book::getAuthor)
                .map(Author::getLastName)
                .collect(Collectors.toList()))
                .containsOnly("King");
    }

    @Test
    @DisplayName("Get book by wrong genreName")
    public void testGetBooksByWrongTitle() {
        assertThatExceptionOfType(EmptyListException.class).isThrownBy(() -> bookService.getByTitle("asd"))
                .withMessage("Nothing to found");
    }

    @Test
    @DisplayName("Get book by first Name")
    public void testGetBookByFirstName() {
        assertThat(bookService.getByFirstName("Stephen").stream()
                .map(Book::getBookName)
                .collect(Collectors.toList()))
                .containsOnly("Sunshine",
                        "Gunslinger");
    }

    @Test
    @DisplayName("Get book by wrong first name")
    public void testGetBooksByWrongFirstName() {
        assertThatExceptionOfType(EmptyListException.class).isThrownBy(() -> bookService.getByFirstName("asd"))
                .withMessage("Nothing to found");
    }

    @Test
    @DisplayName("Get book by Last Name")
    public void testGetBookByLastName() {
        assertThat(bookService.getByLastName("King").stream()
                .map(Book::getBookName)
                .collect(Collectors.toList())).containsOnly("Sunshine", "Gunslinger");
    }

    @Test
    @DisplayName("Get book by wrong last name")
    public void testGetBooksByWrongLastName() {
        assertThatExceptionOfType(EmptyListException.class).isThrownBy(() -> bookService.getByLastName("asd"))
                .withMessage("Nothing to found");
    }

    @Test
    @DisplayName("When Book is exist")
    public void testBookIsExist() {
        assertThat(bookService.isExist
                (new Book("Sunshine", new Author("Stephen", "King"), new Genre("horrors")))).isTrue();
    }

    @Test
    @DisplayName("When Book is not exist")
    public void testBookIsNotExist() {
        assertThat(bookService.isExist
                (new Book("aaa", new Author("Stephen", "King"), new Genre("horrors")))).isFalse();
    }

    @Test
    @DisplayName("Delete when book is exist")
    public void testDeleteBookWhenExists() {
        Book book = new Book("Dark Tower", new Author("Stephen", "King"), new Genre("horrors"));
        bookService.insert(book);
        assertThat(bookService.isExist(book)).isTrue();
        bookService.delete(book);
        assertThat(bookService.isExist(book)).isFalse();
    }

    @Test
    @DisplayName("Delete when book is not exist")
    public void testDeleteBookWhenNotExists() {
        Book book = new Book("Dark Tower", new Author("Stephen", "King"), new Genre("horrors"));
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() -> bookService.delete(book)).withMessage("Dark Tower is not exist!");
    }
}