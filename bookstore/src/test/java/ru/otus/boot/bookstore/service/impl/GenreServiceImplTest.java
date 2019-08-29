package ru.otus.boot.bookstore.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.boot.bookstore.exception.ExistEntityException;
import ru.otus.boot.bookstore.model.Genre;
import ru.otus.boot.bookstore.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class GenreServiceImplTest {
    @Autowired
    private GenreService genreService;
    private Genre classic = new Genre("classic");
    private Genre horrors = new Genre("horrors");
    private Genre roman = new Genre("roman");

    @BeforeEach
    public void prepare() {
        genreService.insert(classic);
        genreService.insert(horrors);
        genreService.insert(roman);
    }

    @AfterEach
    public void del() {
        genreService.delete(classic);
        genreService.delete(horrors);
        genreService.delete(roman);
    }

    @Test
    @DisplayName("Get all genres")
    public void testGetAllGenres() {
        List<String> collect = genreService.getAll().stream().map(Genre::getGenreName).collect(Collectors.toList());
        assertThat(collect).contains("classic", "horrors", "roman");
    }

    @Test
    @DisplayName("Get genre by genreName")
    public void testGetGenreByTitle() {
        assertThat(genreService.getByTitle("roman").getGenreName()).isEqualTo("roman");
    }

    @Test
    @DisplayName("Get genre by wrong genreName")
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
        Genre computerScience = new Genre("computer science");
        genreService.insert(computerScience);
        assertThat(genreService.isExist("computer science")).isTrue();
        genreService.delete(computerScience);
    }

    @Test
    @DisplayName("Delete genre when genre is exist")
    public void testDeleteGenreWhenExist() {
        Genre genre = new Genre("genre");
        genreService.insert(genre);
        assertThat(genreService.isExist("genre"));

        genreService.delete(genre);
        assertThat(genreService.isExist("genre")).isFalse();
    }

    @Test
    @DisplayName("Delete genre when genre is not exist")
    public void testDeleteGenreWhenNotExist() {
        assertThatExceptionOfType(ExistEntityException.class).isThrownBy(() ->
                genreService.delete(new Genre("Asa"))).withMessage("Asa is not exist!");
    }
}