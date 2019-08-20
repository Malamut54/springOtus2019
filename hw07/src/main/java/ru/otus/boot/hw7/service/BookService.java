package ru.otus.boot.hw7.service;

import ru.otus.boot.hw7.model.Book;

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