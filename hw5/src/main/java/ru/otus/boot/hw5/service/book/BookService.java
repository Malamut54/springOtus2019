package ru.otus.boot.hw5.service.book;

import ru.otus.boot.hw5.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    List<Book> getByTitle(String title);

    List<Book> getByFirstName(String firstName);

    List<Book> getByLastName(String lastName);

    boolean isExist(Book book);

    void insert(Book book);

    void delete(Book book);
}
