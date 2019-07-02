package ru.otus.boot.hw5.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw5.dao.book.BookDao;
import ru.otus.boot.hw5.exception.EmptyListException;
import ru.otus.boot.hw5.model.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao dao;
    private final static String BOOKS_NOT_FOUND = "Books not found";

    @Override
    public List<Book> getAll() {
        List<Book> books = dao.getAll();

        if (books.isEmpty()) {
            throwException(BOOKS_NOT_FOUND);
        }

        return books;
    }

    @Override
    public List<Book> getByTitle(String title) {
        List<Book> books = dao.getByTitle(title);

        if (books.isEmpty()) {
            throwException(BOOKS_NOT_FOUND + " " + title);
        }

        return books;
    }

    @Override
    public List<Book> getByFirstName(String firstName) {
        List<Book> books = dao.getByFirstName(firstName);

        if (books.isEmpty()) {
            throwException(BOOKS_NOT_FOUND + " " + firstName);
        }

        return books;
    }

    @Override
    public List<Book> getByLastName(String lastName) {
        List<Book> books = dao.getByLastName(lastName);

        if (books.isEmpty()) {
            throwException(BOOKS_NOT_FOUND + " " + lastName);
        }

        return books;
    }

    @Override
    public boolean isExist(Book book) {
        return dao.isExist(book.getBookName(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(), book.getGenre().getGenreName());
    }

    @Override
    public void insert(Book book) {
        dao.insert(book.getBookName(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(), book.getGenre().getGenreName());
    }

    @Override
    public void delete(Book book) {
        dao.delete(book.getBookName(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(), book.getGenre().getGenreName());
    }

    private void throwException(String ex) {
        throw new EmptyListException(ex);
    }
}
