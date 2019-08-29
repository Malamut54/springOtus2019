package ru.otus.boot.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.bookstore.exception.EmptyListException;
import ru.otus.boot.bookstore.exception.ExistEntityException;
import ru.otus.boot.bookstore.model.Book;
import ru.otus.boot.bookstore.repo.BookRepository;
import ru.otus.boot.bookstore.service.BookService;
import ru.otus.boot.bookstore.util.Checker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return Checker.checkEmptyList(bookRepository.findAll());
    }

    @Override
    public List<Book> getByTitle(String bookName) throws EmptyListException {
        return Checker.checkEmptyList(bookRepository.getBooksByBookName(bookName));
    }

    @Override
    public List<Book> getByFirstName(String firstName) {
        return Checker.checkEmptyList(bookRepository.getBooksByAuthor_FirstName(firstName));
    }

    @Override
    public List<Book> getByLastName(String lastName) {
        return Checker.checkEmptyList(bookRepository.getBooksByAuthor_LastName(lastName));
    }

    @Override
    public boolean isExist(Book book) {
        return bookRepository.findBookByAllParam(book.getBookName(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(),
                book.getGenre().getGenreName()) != null;
    }

    @Override
    public void insert(Book book) {
        bookRepository.insert(book);
    }

    @Override
    public void delete(Book book) {
        if (!isExist(book)) {
            throw new ExistEntityException(book.getBookName() + " is not exist!");
        }
        bookRepository.delete(book);
    }
}
