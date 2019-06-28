package ru.otus.boot.hw5.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw5.dao.author.AuthorDao;
import ru.otus.boot.hw5.exception.EmptyListException;
import ru.otus.boot.hw5.model.Author;
import ru.otus.boot.hw5.model.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao dao;
    private final static String AUTHORS_NOT_FOUND = "Authors not found";

    @Override
    public List<Author> getAll() {
        List<Author> authors = dao.getAll();

        if (authors.isEmpty()) {
            throwException(AUTHORS_NOT_FOUND);
        }

        return authors;
    }

    @Override
    public List<Author> getByFirstName(String firstName) {
        List<Author> authors = dao.getByFirstName(firstName);
        if (authors.isEmpty()) {
            throwException(AUTHORS_NOT_FOUND + " for first name" + firstName);
        }
        return authors;
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        List<Author> authors = dao.getByLastName(lastName);
        if (authors.isEmpty()) {
            throwException(AUTHORS_NOT_FOUND + " for last name" + lastName);
        }
        return authors;
    }

    @Override
    public Author getByid(Long id) {
        return null;
    }

    @Override
    public boolean isExist(String firstName, String lastName) {
        return false;
    }

    @Override
    public void insert(Author author) {

    }

    @Override
    public void delete(Author author) {

    }

    private void throwException(String ex) {
        throw new EmptyListException(ex);
    }
}
