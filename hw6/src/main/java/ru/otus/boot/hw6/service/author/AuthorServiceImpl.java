package ru.otus.boot.hw6.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Author;
import ru.otus.boot.hw6.repo.AuthorRepository;
import ru.otus.boot.hw6.util.Checker;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public List<Author> getAll() {
        return Checker.checkEmptyList(authorRepository.getAll());
    }

    @Override
    public List<Author> getByFirstName(String firstName) {
        return Checker.checkEmptyList(authorRepository.getByFirstName(firstName));
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        return Checker.checkEmptyList(authorRepository.getByLastName(lastName));
    }

    @Override
    public boolean isExist(String firstName, String lastName) {
        return authorRepository.isExist(firstName, lastName);
    }

    @Override
    public void insert(Author author) {
        if (isExist(author.getFirstName(), author.getLastName())) {
            throw new ExistEntityException("This author is exist");
        }
        authorRepository.insert(author);
    }

    @Override
    @Transactional
    public void delete(Author author) {
        if (!isExist(author.getFirstName(), author.getLastName())) {
            throw new ExistEntityException((author.getFirstName() +
                    " " + author.getLastName() + " is not exist!"));
        }
        authorRepository.delete(author.getFirstName(), author.getLastName());
    }
}
