package ru.otus.boot.hw5.service.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.boot.hw5.exception.ExistEntityException;
import ru.otus.boot.hw5.model.Author;
import ru.otus.boot.hw5.model.Book;
import ru.otus.boot.hw5.model.Genre;
import ru.otus.boot.hw5.service.author.AuthorService;
import ru.otus.boot.hw5.service.book.BookService;
import ru.otus.boot.hw5.service.genre.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommandService {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @ShellMethod("Get all genres")
    public List<Genre> getAllGenre() {
        return genreService.getAll();
    }

    @ShellMethod("Get genres by title")
    public Genre getGenreByTitle(String title) {
        return genreService.getByTitle(title);
    }

    @ShellMethod("Is genre exist")
    public boolean checkGenre(String title) {
        return genreService.isExist(title);
    }

    @ShellMethod("Insert Genre")
    public void insertGenre(String title) {
        if (genreService.isExist(title)) {
            throw new ExistEntityException("Genre is exist");
        }
        genreService.insert(new Genre(title));
    }

    @ShellMethod("Get all authors")
    public List<Author> getAllAuthor() {
        return authorService.getAll();
    }

    @ShellMethod("Get all authors by first name")
    public List<Author> getAuthorByFirstName(String fName) {
        return authorService.getByFirstName(fName);
    }

    @ShellMethod("Get all authors by last name")
    public List<Author> getAuthorByLastName(String lName) {
        return authorService.getByLastName(lName);
    }

    @ShellMethod("Is author exist")
    public boolean checkAuthor(String fName, String lName) {
        return authorService.isExist(fName, lName);
    }

    @ShellMethod("Insert Author")
    public void insertAuthor(String fName, String lName) {
        if (authorService.isExist(fName, lName)) {
            throw new ExistEntityException("Author is exist");
        }
        authorService.insert(new Author(fName, lName));
    }

    @ShellMethod("Get all book")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @ShellMethod("Get all books by title")
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
        return bookService.isExist(new Book(new Author(fNmae, lName), new Genre(genre), title));
    }

    @ShellMethod("Insert Book")
    public void insertBook(String title, String fName, String lName, String genreName) {
        Author author = new Author(fName, lName);
        Genre genre = new Genre(genreName);
        Book book = new Book(author, genre, title);

        if (!authorService.isExist(fName, lName)) {
            authorService.insert(author);
        }

        if (!genreService.isExist(genreName)) {
            genreService.insert(genre);
        }

        if (bookService.isExist(book)) {
            throw new ExistEntityException("Book is exist");
        }

        bookService.insert(book);
    }

    @ShellMethod("Delete Book")
    public void deleteBook(String title, String fName, String lName, String genre) {
        Book book = new Book(new Author(fName, lName), new Genre(genre), title);
        if (!bookService.isExist(book)) {
            throw new ExistEntityException("Book is not exist");
        }
        bookService.delete(book);
    }
}
