package ru.otus.boot.hw7.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.hw7.exception.EmptyListException;
import ru.otus.boot.hw7.exception.ExistEntityException;
import ru.otus.boot.hw7.model.Author;
import ru.otus.boot.hw7.service.AuthorService;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Transactional
class AuthorServiceImplTest {
    @Autowired
    private AuthorService authorService;

    @BeforeEach
    public void prepare() {
        authorService.insert(new Author("Stephen", "King"));
        authorService.insert(new Author("Stephen", "Balmer"));
        authorService.insert(new Author("Fedor", "Dostoevskiy"));
        authorService.insert(new Author("Viktor", "Pelevin"));

    }

    @AfterEach
    public void del() {
        authorService.delete(new Author("Stephen", "King"));
        authorService.delete(new Author("Stephen", "Balmer"));
        authorService.delete(new Author("Fedor", "Dostoevskiy"));
        authorService.delete(new Author("Viktor", "Pelevin"));
    }

    @Test
    @DisplayName("Get All authors")
    public void testGetAllAuthors() {
        assertThat(authorService.getAll().stream().map(Author::getLastName).collect(Collectors.toList()))
                .containsOnly("King", "Balmer", "Dostoevskiy", "Pelevin");
    }

    @Test
    @DisplayName("Get authors by first name")
    public void testGetAuthorByFirstName() {
        assertThat(authorService.getByFirstName("Stephen").stream()
                .map(Author::getLastName)
                .collect(Collectors.toList()))
                .containsOnly("King", "Balmer");
    }

    @Test
    @DisplayName("Get authors by wrong first name")
    public void testGetAuthorByWrongFirstName() {
        assertThatExceptionOfType(EmptyListException.class).isThrownBy(() -> authorService.getByFirstName("asd"))
                .withMessage("Nothing to found");
    }

    @Test
    @DisplayName("Get authors by last name")
    public void testGetAuthorByLastName() {
        assertThat(authorService.getByLastName("King").stream()
                .map(Author::getFirstName)
                .collect(Collectors.toList()))
                .containsOnly("Stephen");
    }

    @Test
    @DisplayName("Get authors by wrong last name")
    public void testGetAuthorByWrongLastName() {
        assertThatExceptionOfType(EmptyListException.class).isThrownBy(() -> authorService.getByLastName("asd"))
                .withMessage("Nothing to found");
    }

    @Test
    @DisplayName("When Author is exist")
    public void testIsAuthorIsExist() {
        assertThat(authorService.isExist("Stephen", "King")).isTrue();
    }

    @Test
    @DisplayName("When Author is not exist")
    public void testIsAuthorIsNotExist() {
        assertThat(authorService.isExist("Test", "testovich")).isFalse();
    }

    @Test
    @DisplayName("Insert new Author")
    public void testInsertNewAuthor() {
        authorService.insert(new Author("Author", "Author"));
        assertThat(authorService.isExist("Author", "Author")).isTrue();

        authorService.delete(new Author("Author", "Author"));
    }

    @Test
    @DisplayName("Insert Author duplicate")
    public void tsetInsertAuthorWithDuplicate() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                authorService.insert(new Author("Stephen", "King"))).withMessage("This author is exist");
    }

    @Test
    @DisplayName("Delete author when author is exist")
    public void testDeleteAuthorWhenExist() {
        Author author = authorService.getByFirstNameAndLastName("Stephen", "King");
        authorService.delete(author);
        assertThat(authorService.isExist("Stephen", "King")).isFalse();
        authorService.insert(new Author("Stephen", "King"));
    }

    @Test
    @DisplayName("Delete author when author is not exist")
    void testDeleteAuthorWhenNotExist() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                authorService.delete(new Author("asdd", "adwq")))
                .withMessage("asdd adwq is not exist!");
    }
}