package ru.otus.boot.bookstore.service;

import ru.otus.boot.bookstore.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    List<Author> getByFirstName(String firstName);

    List<Author> getByLastName(String lastName);

    Author getByFirstNameAndLastName(String LastName, String firstName);

    boolean isExist(String firstName, String lastName);

    void insert(Author author);

    void delete(Author author);
}
