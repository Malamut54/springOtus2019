package ru.otus.boot.bookstore.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.bookstore.exception.EmptyListException;
import ru.otus.boot.bookstore.exception.ExistEntityException;
import ru.otus.boot.bookstore.model.Author;
import ru.otus.boot.bookstore.service.AuthorService;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class AuthorServiceImplTest {
    @Autowired
    private AuthorService authorService;

    private Author stephenKing = new Author("Stephen", "King");
    private Author stephenBalmer = new Author("Stephen", "Balmer");
    private Author fedorDostaevski = new Author("Fedor", "Dostoevskiy");
    private Author viktorPelevin = new Author("Viktor", "Pelevin");

    @BeforeEach
    public void prepare() {
        authorService.insert(stephenKing);
        authorService.insert(stephenBalmer);
        authorService.insert(fedorDostaevski);
        authorService.insert(viktorPelevin);

    }

    @AfterEach
    public void del() {
        authorService.delete(stephenKing);
        authorService.delete(stephenBalmer);
        authorService.delete(fedorDostaevski);
        authorService.delete(viktorPelevin);
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
        Author author = new Author("Author", "Author");
        authorService.insert(author);
        assertThat(authorService.isExist("Author", "Author")).isTrue();

        authorService.delete(author);
    }

    @Test
    @DisplayName("Insert Author duplicate")
    public void tsetInsertAuthorWithDuplicate() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                authorService.insert(stephenKing)).withMessage("This author is exist");
    }

    @Test
    @DisplayName("Delete author when author is exist")
    public void testDeleteAuthorWhenExist() {
        Author author = authorService.getByFirstNameAndLastName("Stephen", "King");
        authorService.delete(author);
        assertThat(authorService.isExist("Stephen", "King")).isFalse();
        authorService.insert(author);
    }

    @Test
    @DisplayName("Delete author when author is not exist")
    void testDeleteAuthorWhenNotExist() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                authorService.delete(new Author("asdd", "adwq")))
                .withMessage("asdd adwq is not exist!");
    }
}