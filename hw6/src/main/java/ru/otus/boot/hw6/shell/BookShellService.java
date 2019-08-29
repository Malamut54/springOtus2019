package ru.otus.boot.hw6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Author;
import ru.otus.boot.hw6.model.Book;
import ru.otus.boot.hw6.model.Genre;
import ru.otus.boot.hw6.service.book.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShellService {
    private final BookService bookService;

    @ShellMethod("Get all book")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @ShellMethod("Get all books by genreName")
    public List<Book> getAllBooksByTitle(String title) {
        return bookService.getByTitle(title);
    }

    @ShellMethod("Get all books by first name")
    public List<Book> getAllBooksByFirstName(String fName) {
        return bookService.getByFirstName(fName);
    }

    @ShellMethod("Get all books by last name")
    public List<Book> getAllBooksByLastName(String lName) {
        return bookService.getByLastName(lName);
    }

    @ShellMethod("Is book exist")
    public boolean checkBook(String title, String fNmae, String lName, String genre) {
        return bookService.isExist(new Book(title, new Author(fNmae, lName), new Genre(genre)));
    }

    @ShellMethod("Insert Book")
    public void insertBook(String title, String fName, String lName, String genreName) {
        Author author = new Author(fName, lName);
        Genre genre = new Genre(genreName);
        Book book = new Book(title, author, genre);

        if (bookService.isExist(book)) {
            throw new ExistEntityException("Book is exist");
        }
        bookService.insert(book);
    }

    @ShellMethod("Delete Book")
    public void deleteBook(String title, String fName, String lName, String genre) {
        Book book = new Book(title, new Author(fName, lName), new Genre(genre));
        if (!bookService.isExist(book)) {
            throw new ExistEntityException("Book is not exist");
        }
        bookService.delete(book);
    }
}
