package ru.otus.boot.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.bookstore.exception.ExistEntityException;
import ru.otus.boot.bookstore.model.Author;
import ru.otus.boot.bookstore.repo.AuthorRepository;
import ru.otus.boot.bookstore.service.AuthorService;
import ru.otus.boot.bookstore.util.Checker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public List<Author> getAll() {
        return Checker.checkEmptyList(authorRepository.findAll());
    }

    @Override
    public List<Author> getByFirstName(String firstName) {
        return Checker.checkEmptyList(authorRepository.findAllByFirstName(firstName));
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        return Checker.checkEmptyList(authorRepository.findAllByLastName(lastName));
    }

    @Override
    public Author getByFirstNameAndLastName(String lastName, String firstName) {
        return authorRepository.findAuthorByFirstNameAndLastName(lastName, firstName);
    }

    @Override
    public boolean isExist(String firstName, String lastName) {
        return authorRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void insert(Author author) {
        if (isExist(author.getFirstName(), author.getLastName())) {
            throw new ExistEntityException("This author is exist");
        }
        authorRepository.insert(author);
    }

    @Override
    public void delete(Author author) {
        if (!isExist(author.getFirstName(), author.getLastName())) {
            throw new ExistEntityException((author.getFirstName() +
                    " " + author.getLastName() + " is not exist!"));
        }
        authorRepository.delete(author);
    }
}
