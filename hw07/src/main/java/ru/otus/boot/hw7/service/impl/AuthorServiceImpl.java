package ru.otus.boot.hw7.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw7.exception.ExistEntityException;
import ru.otus.boot.hw7.model.Author;
import ru.otus.boot.hw7.repo.AuthorRepository;
import ru.otus.boot.hw7.service.AuthorService;
import ru.otus.boot.hw7.util.Checker;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
        authorRepository.save(author);
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
