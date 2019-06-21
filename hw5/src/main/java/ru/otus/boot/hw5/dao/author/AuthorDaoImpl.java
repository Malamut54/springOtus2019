package ru.otus.boot.hw5.dao.author;

import ru.otus.boot.hw5.model.Author;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Author author) {

    }

    @Override
    public List<Author> getByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public Author getById(Integer id) {
        return null;
    }

    @Override
    public void delete(String firstName, String lastName) {

    }

    @Override
    public boolean isExist(String firstName, String lastName) {
        return false;
    }
}
