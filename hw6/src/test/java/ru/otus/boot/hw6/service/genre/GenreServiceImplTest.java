package ru.otus.boot.hw6.service.genre;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Genre;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Transactional
class GenreServiceImplTest {
    @Autowired
    private GenreService genreService;

    @BeforeEach
    public void prepare() {
        genreService.insert(new Genre("classic"));
        genreService.insert(new Genre("horrors"));
        genreService.insert(new Genre("roman"));
    }

    @AfterEach
    public void del() {
        genreService.delete(new Genre("classic"));
        genreService.delete(new Genre("horrors"));
        genreService.delete(new Genre("roman"));
    }

    @Test
    @DisplayName("Get all genres")
    public void testGetAllGenres() {
        List<String> collect = genreService.getAll().stream().map(Genre::getGenreName).collect(Collectors.toList());
        assertThat(collect).contains("classic", "horrors", "roman");
    }

    @Test
    @DisplayName("Get genre by title")
    public void testGetGenreByTitle() {
        assertThat(genreService.getByTitle("roman").getGenreName()).isEqualTo("roman");
    }

    @Test
    @DisplayName("Get genre by wrong title")
    public void testGetGenreByWrongTitle() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                genreService.getByTitle("asd")).withMessage("asd is not exist!");
    }

    @Test
    @DisplayName("When Genre is exist")
    public void testGenreIsExit() {
        assertThat(genreService.isExist("roman")).isTrue();
    }

    @Test
    @DisplayName("When Genre is not exist")
    public void testGenreIsNotExit() {
        assertThat(genreService.isExist("asdf")).isFalse();
    }

    @Test
    @DisplayName("Insert genre")
    public void testInsertGenre() {
        genreService.insert(new Genre("computer science"));
        assertThat(genreService.isExist("computer science")).isTrue();
        genreService.delete(new Genre("computer science"));
    }

    @Test
    @DisplayName("Delete genre when genre is exist")
    public void testDeleteGenreWhenExist() {
        genreService.insert(new Genre("sdfg"));
        assertThat(genreService.isExist("sdfg"));

        genreService.delete(new Genre("sdfg"));
        assertThat(genreService.isExist("sdfg")).isFalse();
    }

    @Test
    @DisplayName("Delete genre when genre is not exist")
    public void testDeleteGenreWhenNotExist() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                genreService.delete(new Genre("Asa"))).withMessage("Asa is not exist!");
    }
}