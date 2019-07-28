package ru.otus.boot.hw6.repo.author;

import ru.otus.boot.hw6.model.Author;

import java.util.List;

public interface AuthorRepository {
    void insert(Author author);

    List<Author> getByFirstName(String firstName);

    List<Author> getByLastName(String lastName);

    List<Author> getAll();

    void delete(String firstName, String lastName);

    boolean isExist(String firstName, String lastName);
}
