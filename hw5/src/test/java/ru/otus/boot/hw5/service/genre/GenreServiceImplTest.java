package ru.otus.boot.hw5.service.genre;

import org.junit.jupiter.api.Test;
import ru.otus.boot.hw5.dao.genre.GenreDao;
import ru.otus.boot.hw5.exception.EmptyListException;
import ru.otus.boot.hw5.exception.ExistEntityException;
import ru.otus.boot.hw5.model.Genre;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GenreServiceImplTest {
    private GenreDao genreDao = mock(GenreDao.class);
    private GenreService genreService = new GenreServiceImpl(genreDao);

    @Test
    public void testGetAllSuccess() {
        when(genreDao.getAll()).thenReturn(Collections.singletonList(new Genre("horror")));
        assertThat(genreService.getAll()).hasSize(1).contains(new Genre("horror"));
    }

    @Test
    public void testGetAllExceptionWhenEmptyList() {
        when(genreDao.getAll()).thenReturn(Collections.EMPTY_LIST);
        assertThatThrownBy(() -> genreService.getAll()).isInstanceOf(EmptyListException.class)
                .hasMessage("Genres Not Found");
    }

    @Test
    public void testGetByTitleSuccess() {
        when(genreDao.isExist("horror")).thenReturn(true);
        when(genreDao.getByTitle("horror")).thenReturn(new Genre("horror"));
        assertThat(genreService.getByTitle("horror")).isEqualTo(new Genre("horror"));
    }

    @Test
    public void testGetByTitleWhenNotExist() {
        when(genreDao.isExist("genre")).thenReturn(false);
        assertThatThrownBy(() -> genreService.getByTitle("genre")).isInstanceOf(ExistEntityException.class)
                .hasMessage("genre not found");
    }
}