package ru.otus.boot.hw5.dao.author;

import ru.otus.boot.hw5.model.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    void insert(String firstName, String lastName);

    List<Author> getByFirstName(String firstName);

    List<Author> getByLastName(String lastName);

    List<Author> getAll();

    Author getById(Integer id);

    Author getByFirstAndLastName(String firstName, String lastname);

    void delete(String firstName, String lastName);

    boolean isExist(String firstName, String lastName);

}

