package ru.otus.boot.hw5.service.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.hw5.exception.ExistEntityException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class ShellCommandServiceTest {
    @Autowired
    ShellCommandService shellCommandService;

    @Test
    @DisplayName("Insert book when book not exist")
    public void insertBookWhenNotExist() {
        shellCommandService.insertBook("Test", "Test", "Testovich", "Test");
        assertThat(shellCommandService.checkBook("Test", "Test", "Testovich", "Test")).isTrue();

        shellCommandService.deleteBook("Test", "Test", "Testovich", "Test");
    }

    @Test
    @DisplayName("Insert book when book not exist")
    public void insertBookWhenExist() {
        shellCommandService.insertBook("Test", "Test", "Testovich", "Test");
        assertThat(shellCommandService.checkBook("Test", "Test", "Testovich", "Test")).isTrue();
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                shellCommandService.insertBook("Test", "Test", "Testovich", "Test"))
                .withMessage("Book is exist");

        shellCommandService.deleteBook("Test", "Test", "Testovich", "Test");
    }

    @Test
    @DisplayName("Delete book when is exist")
    void deleteBookWhenExist() {
        shellCommandService.insertBook("bookName", "firstName", "lastName", "genreName");
        assertThat(shellCommandService.checkBook("bookName", "firstName",
                "lastName", "genreName")).isTrue();
        shellCommandService.deleteBook("bookName", "firstName", "lastName", "genreName");
        assertThat(shellCommandService.checkBook("bookName", "firstName",
                "lastName", "genreName")).isFalse();
    }

    @Test
    @DisplayName("Delete book when is not exist")
    void deleteBookWhenNotExist() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                shellCommandService.deleteBook("test", "test", "testovich", "test"))
                .withMessage("Book is not exist");

    }
}