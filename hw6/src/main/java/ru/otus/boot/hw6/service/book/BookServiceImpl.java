package ru.otus.boot.hw6.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw6.exception.EmptyListException;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Book;
import ru.otus.boot.hw6.repo.BookRepository;
import ru.otus.boot.hw6.util.Checker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return Checker.checkEmptyList(bookRepository.getAll());
    }

    @Override
    public List<Book> getByTitle(String title) throws EmptyListException {
        return Checker.checkEmptyList(bookRepository.getByTitle(title));
    }

    @Override
    public List<Book> getByFirstName(String firstName) {
        return Checker.checkEmptyList(bookRepository.getByFirstName(firstName));
    }

    @Override
    public List<Book> getByLastName(String lastName) {
        return Checker.checkEmptyList(bookRepository.getByLastName(lastName));
    }

    @Override
    public boolean isExist(Book book) {
        return bookRepository.isExist(book.getBookName(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(), book.getGenre().getGenreName());
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
        bookRepository.delete(book.getBookName());
    }
}
