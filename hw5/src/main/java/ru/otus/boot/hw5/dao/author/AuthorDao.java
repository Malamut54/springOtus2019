package ru.otus.boot.hw5.dao.author;

import ru.otus.boot.hw5.model.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    void insert(Author author);

    List<Author> getByFirstName(String firstName);

    List<Author> getByLastName(String lastName);

    List<Author> getAll();

    Author getById(Integer id);

    void delete(String firstName, String lastName);

    boolean isExist(String firstName, String lastName);

}

