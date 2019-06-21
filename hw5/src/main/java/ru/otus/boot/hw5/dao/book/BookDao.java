package ru.otus.boot.hw5.dao.book;

import ru.otus.boot.hw5.model.Book;

import java.util.List;

public interface BookDao {
    int count();

    void insert(Book book);

    List<Book> getByFirstName(String firstName);

    List<Book> getByLastName(String lastName);

    List<Book> getAll();

    List<Book> getByTitle(String title);

    void delete(String title);

    boolean isExist(String title, String firstName, String lastName, String genre);

}