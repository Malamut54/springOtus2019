package ru.otus.boot.hw7.service.author;

import ru.otus.boot.hw7.model.Author;

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
