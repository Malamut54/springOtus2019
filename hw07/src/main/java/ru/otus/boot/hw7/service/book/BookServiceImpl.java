package ru.otus.boot.hw7.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw7.exception.EmptyListException;
import ru.otus.boot.hw7.exception.ExistEntityException;
import ru.otus.boot.hw7.model.Book;
import ru.otus.boot.hw7.repo.BookRepository;
import ru.otus.boot.hw7.util.Checker;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return Checker.checkEmptyList(bookRepository.findAll());
    }

    @Override
    public List<Book> getByTitle(String bookName) throws EmptyListException {
        return Checker.checkEmptyList(bookRepository.getAllByBookName(bookName));
    }

    @Override
    public List<Book> getByFirstName(String firstName) {
        return Checker.checkEmptyList(bookRepository.getAllByAuthor_FirstName(firstName));
    }

    @Override
    public List<Book> getByLastName(String lastName) {
        return Checker.checkEmptyList(bookRepository.getAllByAuthor_LastName(lastName));
    }

    @Override
    public boolean isExist(Book book) {
        return bookRepository.existsByBookNameAndAuthorAndGenre(book.getBookName(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(),
                book.getGenre().getGenreName());
    }

    @Override
    public void insert(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        if (!isExist(book)) {
            throw new ExistEntityException(book.getBookName() + " is not exist!");
        }
        bookRepository.delete(book);
    }
}
