package ru.otus.boot.hw6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Author;
import ru.otus.boot.hw6.service.author.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellService {

    private final AuthorService authorService;

    @ShellMethod("Get all authors")
    public List<Author> getAllAuthor() {
        return authorService.getAll();
    }

    @ShellMethod("Get all authors by first name")
    public List<Author> getAuthorByFirstName(String fName) {
        return authorService.getByFirstName(fName);
    }

    @ShellMethod("Get all authors by last name")
    public List<Author> getAuthorByLastName(String lName) {
        return authorService.getByLastName(lName);
    }

    @ShellMethod("Is author exist")
    public boolean checkAuthor(String fName, String lName) {
        return authorService.isExist(fName, lName);
    }

    @ShellMethod("Insert Author")
    public void insertAuthor(String fName, String lName) {
        if (authorService.isExist(fName, lName)) {
            throw new ExistEntityException("Author is exist");
        }
        authorService.insert(new Author(fName, lName));
    }
}
